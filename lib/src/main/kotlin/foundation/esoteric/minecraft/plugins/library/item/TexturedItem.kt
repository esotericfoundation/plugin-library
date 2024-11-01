package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

open class TexturedItem(plugin: CustomItemPlugin, itemId: String, material: Material) : CustomItem(plugin, itemId, material) {
    override fun toItem(item: ItemStack): ItemStack {
        super.toItem(item)

        item.editMeta {
            meta ->
            meta.setCustomModelData(this::class.qualifiedName.hashCode())
        }

        return item
    }
}
