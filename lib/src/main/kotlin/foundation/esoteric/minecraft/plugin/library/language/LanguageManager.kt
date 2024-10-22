package foundation.esoteric.minecraft.plugin.library.language

import org.bukkit.configuration.file.FileConfiguration
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

    val config: FileConfiguration = plugin.config
}
