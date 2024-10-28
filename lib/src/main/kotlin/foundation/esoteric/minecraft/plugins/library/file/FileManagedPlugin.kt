package foundation.esoteric.minecraft.plugins.library.file

import org.bukkit.plugin.Plugin

interface FileManagedPlugin : Plugin {
    fun getFileManager(): FileManager
}
