package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

/**
 * A class to help manage the creation of custom items.
 *
 * If you want to add a custom item to your plugin, create a class that extends `CustomItem`.
 *
 * Implement all needed methods, after which you must instantiate this class **once** (it is a singleton).
 *
 * @see foundation.esoteric.minecraft.plugins.library.commands.GiveCustomItemCommand If you want a command that allows you to give yourself this custom item.
 */
abstract class CustomItem(protected val plugin: CustomItemPlugin, private val itemId: String, private val material: Material) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)

        plugin.customItemManager.addItem(itemId, this)
    }

    /**
     * This method create a new `ItemStack` instance that is an instance of this custom item.
     * @return Said `ItemStack` instance.
     */
    fun createItem(): ItemStack {
        val item = ItemStack(material)

        return toItem(item)
    }

    /**
     * This method transforms an existing `ItemStack` instance to an instance of this custom item.
     * @param item The existing item. The material of this item must match the material of the custom item.
     * @return The transformed `ItemStack`.
     */
    fun toItem(item: ItemStack): ItemStack {
        require(item.type == material) { "Cannot transform item of material " + item.type + " to item of material " + material.name + "." }

        item.editMeta { meta: ItemMeta ->
            meta.persistentDataContainer.set(
                plugin.customItemManager.itemIdKey,
                PersistentDataType.STRING,
                itemId
            )
        }

        return item
    }

    /**
     * This method checks whether an `ItemStack` instance is also an instance of this custom item.
     * @param itemStack The `ItemStack` to check.
     * @return Whether the `itemStack` is an instance of this custom item.
     */
    fun isItem(itemStack: ItemStack): Boolean {
        if (!itemStack.hasItemMeta()) {
            return false
        }

        val dataContainerItemIdValue = itemStack.itemMeta.persistentDataContainer.get(plugin.customItemManager.itemIdKey, PersistentDataType.STRING) ?: return false

        return itemId == dataContainerItemIdValue
    }

    /**
     * This methods gives an instance of this custom item to the specified `player`.
     * @param player The player to give this custom item to.
     */
    fun give(player: Player) {
        player.inventory.addItem(createItem())
    }
}
