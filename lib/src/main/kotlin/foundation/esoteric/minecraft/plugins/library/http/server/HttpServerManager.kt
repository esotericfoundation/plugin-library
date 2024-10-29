package foundation.esoteric.minecraft.plugins.library.http.server

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import foundation.esoteric.minecraft.plugins.library.resourcepack.ResourcePackListener
import foundation.esoteric.minecraft.plugins.library.resourcepack.ResourcePackManager
import foundation.esoteric.minecraft.plugins.library.resourcepack.ResourcePackPlugin
import org.bukkit.Bukkit
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.InetSocketAddress

class HttpServerManager(private val plugin: ResourcePackPlugin) {

    private val hostName: String
    private val port: Int

    private val successResponseCode = 200
    private val notFoundResponseCode = 404

    private var server: HttpServer? = null

    fun getPort(): Int {
        return server!!.address.port
    }

    fun getHostName(): String {
        return server!!.address.hostName
    }

    val socketAddress: String
        get() = getHostName() + ":" + getPort()

    init {
        hostName = Bukkit.getServer().ip
        port = plugin.getConfig().getInt("http-server.port")

        try {
            server = HttpServer.create(InetSocketAddress(hostName, port), 0)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }

        server!!.createContext("/", ResourcePackDownloadHandler())

        server!!.executor = null
        server!!.start()

        Bukkit.getPluginManager().registerEvents(ResourcePackListener(plugin, this), plugin)
    }

    internal inner class ResourcePackDownloadHandler : HttpHandler {
        @Throws(IOException::class)
        override fun handle(exchange: HttpExchange) {
            val resourcePackManager: ResourcePackManager = plugin.resourcePackManager

            val file = File(resourcePackManager.resourceZipFilePath)

            if (file.exists()) {
                exchange.responseHeaders["Content-Type"] = resourcePackManager.resourcePackFileMimeType
                exchange.responseHeaders["Content-Disposition"] = "attachment; filename=\"" + resourcePackManager.resourcePackResourceFolderName + "." + resourcePackManager.resourcePackFileExtension + "\""

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
                val response = "404 (Not Found)\n"
                exchange.sendResponseHeaders(notFoundResponseCode, response.length.toLong())
                val outputStream = exchange.responseBody
                outputStream.write(response.toByteArray())
                outputStream.close()
            }
        }
    }
}
