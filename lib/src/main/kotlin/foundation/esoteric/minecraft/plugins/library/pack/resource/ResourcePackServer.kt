package foundation.esoteric.minecraft.plugins.library.pack.resource

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.FileInputStream
import java.io.IOException
import java.net.InetSocketAddress

/**
 * This class handles sending the resource pack to clients.
 *
 * @param plugin The plugin that implements this resource pack.
 */
class ResourcePackServer(resourcePackManager: ResourcePackManager) {

    private val plugin = resourcePackManager.plugin

    private val hostName: String = Bukkit.getServer().ip
    private val port: Int = plugin.config.getInt("http-server.port")

    private val successResponseCode = 200
    private val notFoundResponseCode = 404

    private var server: HttpServer? = null

    fun getPort(): Int? {
        return server?.address?.port
    }

    fun getHostName(): String? {
        return server?.address?.hostName
    }

    val socketAddress: String
        get() = getHostName() + ":" + getPort()

    init {
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
            val resourcePackManager = plugin.resourcePackManager

            val file = resourcePackManager.resourcePackZipFile!!

            if (file.exists()) {
                exchange.responseHeaders["Content-Type"] = "application/zip"
                exchange.responseHeaders["Content-Disposition"] = "attachment; filename=\"" + resourcePackManager.resourcePackResourceFolderName + ".zip" + "\""

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
