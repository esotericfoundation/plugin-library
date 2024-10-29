package foundation.esoteric.minecraft.plugins.library.resourcepack

import foundation.esoteric.minecraft.plugins.library.http.server.HttpServerManager
import foundation.esoteric.utility.file.FileUtility
import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.net.URI

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
