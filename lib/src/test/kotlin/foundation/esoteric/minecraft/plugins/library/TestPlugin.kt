package foundation.esoteric.minecraft.plugins.library

import foundation.esoteric.minecraft.plugins.library.file.FileManager
import org.bukkit.plugin.java.JavaPlugin

open class TestPlugin : JavaPlugin() {

    lateinit var fileManager: FileManager

    override fun onEnable() {
        fileManager = FileManager(this)
    }
}
