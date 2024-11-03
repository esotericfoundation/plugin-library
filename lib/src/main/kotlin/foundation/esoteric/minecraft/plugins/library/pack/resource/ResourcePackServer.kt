package foundation.esoteric.minecraft.plugins.library.pack.resource

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.bukkit.Bukkit
import java.io.FileInputStream
import java.io.IOException
import java.net.InetSocketAddress

internal class ResourcePackServer(internal val resourcePackManager: ResourcePackManager) {

    private val successResponseCode = 200
    private val notFoundResponseCode = 404

    private lateinit var server: HttpServer

    private val serverPort: Int
        get() = server.address.port

    private val serverHostName: String
        get() = server.address.hostName

    internal val socketAddress: String
        get() = "$serverHostName:$serverPort"

    init {
        try {
            val hostName = Bukkit.getServer().ip
            val port = resourcePackManager.plugin.config.getInt("http-server.port")

            server = HttpServer.create(InetSocketAddress(hostName, port), 0)

            server.createContext("/", ResourcePackDownloadHandler())

            server.executor = null
            server.start()

            Bukkit.getPluginManager().registerEvents(ResourcePackListener(this), resourcePackManager.plugin)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }

    private inner class ResourcePackDownloadHandler : HttpHandler {
        @Throws(IOException::class)
        override fun handle(exchange: HttpExchange) {
            val file = resourcePackManager.zipFile

            if (file.exists()) {
                exchange.responseHeaders["Content-Type"] = "application/zip"
                exchange.responseHeaders["Content-Disposition"] = "attachment; filename=\"" + resourcePackManager.resourcePath + ".zip" + "\""

                exchange.sendResponseHeaders(successResponseCode, file.length())

                FileInputStream(file).use { fileInputStream ->
                    exchange.responseBody.use { outputStream ->
                        val buffer = ByteArray(1024)
                        var count: Int
                        while ((fileInputStream.read(buffer).also { count = it }) != -1) {
                            outputStream.write(buffer, 0, count)
                        }
                    }
                }
            } else {
                val response = "$notFoundResponseCode (Not Found)\n"
                exchange.sendResponseHeaders(notFoundResponseCode, response.length.toLong())

                val outputStream = exchange.responseBody
                outputStream.write(response.toByteArray())
                outputStream.close()
            }
        }
    }
}
