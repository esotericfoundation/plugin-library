package foundation.esoteric.minecraft.plugins.library.file

import foundation.esoteric.utility.resource.ResourceUtility
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Path

class FileManager(private val plugin: JavaPlugin) {

    fun saveResourceFolder(resourcePath: Path, replace: Boolean = false) {
        ResourceUtility.getResourceFilePaths(resourcePath).forEach {
            path -> plugin.saveResource(path.toString(), replace)
        }
    }

    fun saveResourceFolder(resourcePath: String, replace: Boolean = false) {
        saveResourceFolder(Path.of(resourcePath), replace)
    }
}
