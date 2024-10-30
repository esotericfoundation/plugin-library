package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class CustomItemManager(plugin: JavaPlugin) {

    private val itemMap: MutableMap<String, CustomItem> = HashMap()

    internal val itemIdKey: NamespacedKey = NamespacedKey(plugin, "custom_item_id")

    fun addItem(itemId: String, customItem: CustomItem) {
        itemMap[itemId] = customItem
    }

    fun getItem(itemId: String): CustomItem? {
        return itemMap[itemId]
    }

    fun getItemIds(): MutableSet<String> {
        return itemMap.keys
    }

    fun giveItem(itemId: String, player: Player) {
        itemMap[itemId]!!.give(player)
    }
}
