package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.plugin.Plugin

/**
 * Represents a plugin that implements custom items.
 *
 * You must implement this interface if you wish to use custom items in your plugin.
 */
interface CustomItemPlugin : Plugin {
    /**
     * The custom item manager associated with this plugin.
     *
     * A plugin must implement CustomItemPlugin to use the custom item manager.
     */
    val customItemManager: CustomItemManager
}
