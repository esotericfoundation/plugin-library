package foundation.esoteric.minecraft.plugins.library.item

import org.bukkit.plugin.Plugin

interface CustomItemPlugin : Plugin {
    val customItemManager: CustomItemManager
}
