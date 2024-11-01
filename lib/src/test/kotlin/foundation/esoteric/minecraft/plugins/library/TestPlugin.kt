package foundation.esoteric.minecraft.plugins.library

import foundation.esoteric.minecraft.plugins.library.file.FileManagedPlugin
import foundation.esoteric.minecraft.plugins.library.file.FileManager
import foundation.esoteric.minecraft.plugins.library.item.CustomItemManager
import foundation.esoteric.minecraft.plugins.library.item.CustomItemPlugin
import foundation.esoteric.minecraft.plugins.library.pack.resource.ResourcePackManager
import foundation.esoteric.minecraft.plugins.library.pack.resource.ResourcePackPlugin
import org.bukkit.plugin.java.JavaPlugin

open class TestPlugin : JavaPlugin(), FileManagedPlugin, CustomItemPlugin, ResourcePackPlugin {

    override lateinit var resourcePackManager: ResourcePackManager
    override lateinit var customItemManager: CustomItemManager
    override lateinit var fileManager: FileManager

    override fun onEnable() {
        fileManager = FileManager(this)
        customItemManager = CustomItemManager(this)
        resourcePackManager = ResourcePackManager(this)
    }
}
