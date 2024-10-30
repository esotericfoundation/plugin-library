package foundation.esoteric.minecraft.plugins.library.resourcepack

import foundation.esoteric.minecraft.plugins.library.http.server.HttpServerManager
import foundation.esoteric.utility.file.FileUtility
import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.net.URI

/**
 * This class listens to players joining the server, at which point it sends the plugin's resource pack to the player.
 * @param plugin The plugin class. This must implement the ResourcePackPlugin interface.
 * @param httpServerManager The HTTP server manager associated with this plugin.
 */
class ResourcePackListener(plugin: ResourcePackPlugin, httpServerManager: HttpServerManager) : Listener {
    private val resourcePackInfo = ResourcePackInfo.resourcePackInfo()
        .hash(FileUtility.Companion.getSha1Hash(plugin.resourcePackManager.resourcePackZipFile!!))
        .uri(URI.create("http://" + httpServerManager.socketAddress + "/")).build()

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendResourcePacks(
            ResourcePackRequest.resourcePackRequest().packs(resourcePackInfo).required(true).build()
        )
    }
}
