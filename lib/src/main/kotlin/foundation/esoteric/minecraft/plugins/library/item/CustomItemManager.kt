package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class CustomItemManager(plugin: JavaPlugin) {

    private val customItemMap: MutableMap<String, CustomItem> = HashMap()

    val customItemIdKey: NamespacedKey = NamespacedKey(plugin, "custom_item_id")

    fun addCustomItem(itemId: String, customItem: CustomItem) {
        customItemMap[itemId] = customItem
    }

    fun getAbstractCustomItem(itemId: String): CustomItem? {
        return customItemMap[itemId]
    }

    fun giveCustomItem(itemId: String, player: Player) {
        customItemMap[itemId]!!.give(player)
    }
}
