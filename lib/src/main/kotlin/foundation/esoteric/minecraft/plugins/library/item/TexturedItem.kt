package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class TexturedItem(plugin: CustomItemPlugin, itemId: String, material: Material) : CustomItem(plugin, itemId, material) {

    companion object {
        var startingModelData: Int? = null
        var texturedItems: Int = 0
    }

    init {
        startingModelData = plugin.name.hashCode()
        texturedItems++
    }

    override fun toItem(item: ItemStack): ItemStack {
        super.toItem(item)

        item.editMeta {
            meta ->
            meta.setCustomModelData(startingModelData!! + texturedItems)
        }

        return item
    }
}
