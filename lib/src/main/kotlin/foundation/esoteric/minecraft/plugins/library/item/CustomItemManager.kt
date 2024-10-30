package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * This is a manager class that helps to deal with custom items.
 * @param plugin The plugin that implements the custom items.
 */
class CustomItemManager(plugin: JavaPlugin) {

    private val itemMap: MutableMap<String, CustomItem> = HashMap()

    internal val itemIdKey: NamespacedKey = NamespacedKey(plugin, "custom_item_id")

    /**
     * This method adds a custom item instance to the item registry.
     * @param itemId The custom ID of this custom item. This should be in snake_case.
     * @param customItem The instance of the CustomItem class that will be added to the registry.
     */
    fun addItem(itemId: String, customItem: CustomItem) {
        itemMap[itemId] = customItem
    }

    /**
     * Gets a custom item by its ID.
     * @param itemId The ID of the custom item to get.
     */
    fun getItem(itemId: String): CustomItem? {
        return itemMap[itemId]
    }

    /**
     * Returns a set of IDs of every item in the registry.
     */
    fun getItemIds(): MutableSet<String> {
        return itemMap.keys
    }

    /**
     * Gives a custom item to a player.
     * @param itemId The ID of the custom item to give to the player.
     * @param player The player to give the custom item to.
     */
    fun giveItem(itemId: String, player: Player) {
        itemMap[itemId]!!.give(player)
    }
}
