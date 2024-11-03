package foundation.esoteric.minecraft.plugins.library.messages

import foundation.esoteric.minecraft.plugins.library.utility.plugin.saveResources
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.Locale
import kotlin.collections.HashMap
import kotlin.io.path.Path

/**
 * The message manager class provides utility for dealing with messages in your
 * plugin, as well as internationalising your plugin, which means allowing messages
 * to be in a language that the player has selected.
 *
 * If you intend to use the message manager, create a "messages"
 * folder in your "resources" directory.
 */
class MessageManager(private val plugin: JavaPlugin) {

    private val miniMessage = MiniMessage.miniMessage()

    private val messagesFolder = plugin.saveResources(Path("messages"))

    private val messageMap = HashMap<Locale, Map<String, String>>()

    init {
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

    internal fun getRawMessage(language: Locale, messageKey: String, fallbackOnDefault: Boolean = true): String {
        var messages = messageMap[language]

        if (messages == null && fallbackOnDefault) {
            val defaultLanguage = Locale.forLanguageTag(plugin.config.getString("messages.default-language"))
            require(defaultLanguage != null) { "No default language configured." }

            messages = messageMap[language]
            require(messages != null) { "The default language has no associated messages." }
        }

        require(messages != null) { "Invalid language." }

        val message = messages[messageKey]
        require(message != null) { "Invalid message key." }

        return message
    }

    fun getMessage(player: Player, messageKey: String, fallbackOnDefault: Boolean = true): Component {
        val rawMessage = getRawMessage(player.locale(), messageKey, fallbackOnDefault)

        return miniMessage.deserialize(rawMessage)
    }
}
