package foundation.esoteric.minecraft.plugins.library

import foundation.esoteric.minecraft.plugins.library.file.FileManagedPlugin
import foundation.esoteric.minecraft.plugins.library.file.FileManager
import foundation.esoteric.minecraft.plugins.library.item.CustomItemManager
import foundation.esoteric.minecraft.plugins.library.item.CustomItemPlugin
import org.bukkit.plugin.java.JavaPlugin

open class TestPlugin : JavaPlugin(), FileManagedPlugin, CustomItemPlugin {

    override lateinit var customItemManager: CustomItemManager
    override lateinit var fileManager: FileManager

    override fun onEnable() {
        fileManager = FileManager(this)
        customItemManager = CustomItemManager(this)
    }
}
