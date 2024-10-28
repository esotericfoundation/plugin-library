package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

abstract class CustomItem(private val plugin: CustomItemPlugin, private val itemId: String, private val material: Material) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)

        plugin.customItemManager.addCustomItem(itemId, this)
    }

    protected abstract fun generateCustomItem(baseCustomItem: ItemStack?, player: Player?): ItemStack

    fun getCustomItem(player: Player?): ItemStack {
        val item = ItemStack(material)
        item.editMeta { meta: ItemMeta ->
            meta.persistentDataContainer.set(
                plugin.customItemManager.customItemIdKey,
                PersistentDataType.STRING,
                itemId
            )
        }
        return generateCustomItem(item, player)
    }

    fun isItem(itemStack: ItemStack?): Boolean {
        if (itemStack == null) {
            return false
        }

        if (!itemStack.hasItemMeta()) {
            return false
        }

        val dataContainerItemIdValue =
            itemStack.itemMeta.persistentDataContainer.get(plugin.customItemManager.customItemIdKey, PersistentDataType.STRING)
                ?: return false

        return try {
            itemId === dataContainerItemIdValue
        } catch (exception: IllegalArgumentException) {
            false
        }
    }

    fun give(player: Player) {
        player.inventory.addItem(getCustomItem(player))
    }
}
