package foundation.esoteric.minecraft.plugins.library

import foundation.esoteric.minecraft.plugins.library.item.CustomItemManager
import foundation.esoteric.minecraft.plugins.library.item.CustomItemPlugin
import foundation.esoteric.minecraft.plugins.library.pack.resource.ResourcePackManager
import org.bukkit.plugin.java.JavaPlugin

open class TestPlugin : JavaPlugin(), CustomItemPlugin {

    override lateinit var customItemManager: CustomItemManager

    override fun onEnable() {
        customItemManager = CustomItemManager(this)
        ResourcePackManager(this)
    }
}
