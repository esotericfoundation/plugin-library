package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.nio.file.Path

class TexturedItem : CustomItem {

    companion object {
        var startingModelData: Int? = null
        var texturedItems: Int = 0
    }

    constructor(plugin: CustomItemPlugin, itemId: String, material: Material, resourcePath: Path) : super(plugin, itemId, material) {
        startingModelData = plugin.name.hashCode()
        texturedItems++
    }

    constructor(plugin: CustomItemPlugin, itemId: String, material: Material, resourcePath: String) : this(plugin, itemId, material, Path.of(resourcePath))

    override fun toItem(item: ItemStack): ItemStack {
        super.toItem(item)

        item.editMeta {
            meta ->
            meta.setCustomModelData(startingModelData!! + texturedItems)
        }

        return item
    }
}
