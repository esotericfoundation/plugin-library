package foundation.esoteric.minecraft.plugins.library.resourcepack

import org.bukkit.plugin.Plugin

/**
 * Represents a plugin that uses the resource pack features of this library.
 */
interface ResourcePackPlugin : Plugin {
    /**
     * The resource pack manager of this plugin.
     *
     * The plugin must implement ResourcePackPlugin to use the manager.
     */
    val resourcePackManager : ResourcePackManager
}
