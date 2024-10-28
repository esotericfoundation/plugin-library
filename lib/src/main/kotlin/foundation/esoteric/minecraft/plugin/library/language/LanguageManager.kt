package foundation.esoteric.minecraft.plugin.library.language

import foundation.esoteric.utility.resource.ResourceUtility
import org.bukkit.plugin.java.JavaPlugin

/**
 * The language manager class provides utility for dealing with
 * internationalising your plugin, which means allowing messages
 * to be in a language that the player has selected.
 *
 * If you intend to use the language manager, create a "languages"
 * folder in your "resources" directory.
 */
class LanguageManager(plugin: JavaPlugin) {

    init {
        ResourceUtility.getResourceFilePaths("messages").forEach {
            path -> plugin.saveResource(path.toString(), !plugin.config.getBoolean("messages.enable-customisation", false))
        }
    }
}
