package foundation.esoteric.minecraft.plugins.library.pack.resource

import foundation.esoteric.minecraft.plugins.library.utility.plugin.saveResources
import foundation.esoteric.utility.file.zip
import org.apache.commons.io.FileUtils
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import kotlin.io.path.Path

/**
 * A class that helps your plugin to implement custom resources via a resource pack.
 *
 * @param plugin The plugin that intends to implement a resource pack. Note that it must implement the FileManagedPlugin interface.
 * This plugin must also have a resource pack in it's `resources` directory. It is recommended to make your resource pack in a
 * separate Git repository and add it as a submodule. The resource pack directory **MUST** be named `plugin.name + "ResourcePack"`.
 * For example, if your plugin's name (the name that appears in-game when running `/plugins`) is SCPPlugin, then the resource pack
 * must be named `SCPPluginResourcePack`.
 */
class ResourcePackManager(private val plugin: JavaPlugin) {

    val resourcePackResourceFolderName = Path(plugin.name + "ResourcePack")

    var resourcePackZipFile: File? = null

    init {
        val resourcePackFolder = plugin.saveResources(resourcePackResourceFolderName)

        try {
            resourcePackZipFile = File(plugin.dataFolder, "$resourcePackResourceFolderName.zip")
            resourcePackFolder.zip(resourcePackZipFile!!)

            FileUtils.deleteDirectory(resourcePackFolder)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
