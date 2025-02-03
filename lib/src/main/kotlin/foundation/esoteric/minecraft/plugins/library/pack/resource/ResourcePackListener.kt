package foundation.esoteric.minecraft.plugins.library.pack.resource

import foundation.esoteric.utility.file.sha1
import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.net.URI

internal class ResourcePackListener(resourcePackServer: ResourcePackServer) : Listener {
    private val resourcePackInfo = ResourcePackInfo.resourcePackInfo()
        .hash(resourcePackServer.resourcePackManager.zipFile.sha1())
        .uri(URI.create("https://" + resourcePackServer.socketAddress + "/")).build()

    @EventHandler
    internal fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendResourcePacks(
            ResourcePackRequest.resourcePackRequest().packs(resourcePackInfo).required(true).build()
        )
    }
}
