package foundation.esoteric.minecraft.plugins.library.messages

import foundation.esoteric.minecraft.plugins.library.file.FileManagedPlugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.Locale
import kotlin.collections.HashMap

/**
 * The message manager class provides utility for dealing with messages in your
 * plugin, as well as internationalising your plugin, which means allowing messages
 * to be in a language that the player has selected.
 *
 * If you intend to use the message manager, create a "messages"
 * folder in your "resources" directory.
 */
class MessageManager(plugin: FileManagedPlugin) {

    private val messagesFolder: File

    private val messageMap: MutableMap<Locale, Map<String, String>> = HashMap()

    init {
        messagesFolder = plugin.fileManager.saveResourceFolder("messages", !plugin.config.getBoolean("messages.enable-customisation", false))
        loadMessages()
    }

    private fun loadMessages() {
        for (file in messagesFolder.listFiles()!!) {
            val locale = Locale.forLanguageTag(file.name.removeSuffix(".yml"))

            val map = HashMap<String, String>()

            val config = YamlConfiguration.loadConfiguration(file)
            for (messageKey in config.getKeys(false)) {
                map.put(messageKey, config.getString(messageKey)!!)
            }

            messageMap.put(locale, map)
        }
    }

    internal fun getRawMessage(language: Locale, messageKey: String): String {
        val messages = messageMap[language]
        require(messages != null) { "Invalid language." }

        val message = messages[messageKey]
        require(message != null) { "Invalid message key." }

        return message
    }
}
