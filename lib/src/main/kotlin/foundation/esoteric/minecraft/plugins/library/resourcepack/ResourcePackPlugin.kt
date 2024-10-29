package foundation.esoteric.minecraft.plugins.library.resourcepack

import org.bukkit.plugin.Plugin

interface ResourcePackPlugin : Plugin {
    val resourcePackManager : ResourcePackManager
}
