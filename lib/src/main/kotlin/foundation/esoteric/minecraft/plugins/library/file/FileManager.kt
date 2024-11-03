package foundation.esoteric.minecraft.plugins.library.file

import foundation.esoteric.utility.resource.resourceFilePaths
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.nio.file.Path

class FileManager(private val plugin: JavaPlugin) {

    fun saveResourceFolder(resourcePath: Path, replace: Boolean = false): File {
        plugin.dataFolder.mkdir()

        resourcePath.resourceFilePaths().forEach {
            path -> plugin.saveResource(path.toString(), replace)
        }

        return File(plugin.dataFolder, resourcePath.toString())
    }

    fun saveResourceFolder(resourcePath: String, replace: Boolean = false): File {
        return saveResourceFolder(Path.of(resourcePath), replace)
    }
}
