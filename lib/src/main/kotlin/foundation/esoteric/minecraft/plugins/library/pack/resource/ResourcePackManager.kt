package foundation.esoteric.minecraft.plugins.library.pack.resource

import foundation.esoteric.minecraft.plugins.library.file.FileManagedPlugin
import foundation.esoteric.utility.file.FileUtility
import org.apache.commons.io.FileUtils
import java.io.File

/**
 * A class that helps your plugin to implement custom resources via a resource pack.
 *
 * @param plugin The plugin that intends to implement a resource pack. Note that it must implement the FileManagedPlugin interface.
 * This plugin must also have a resource pack in it's `resources` directory. It is recommended to make your resource pack in a
 * separate Git repository and add it as a submodule. The resource pack directory **MUST** be named `plugin.name + "ResourcePack"`.
 * For example, if your plugin's name (the name that appears in-game when running `/plugins`) is SCPPlugin, then the resource pack
 * must be named `SCPPluginResourcePack`.
 */
class ResourcePackManager(private val plugin: FileManagedPlugin) {

    val resourcePackResourceFolderName = plugin.name + "ResourcePack"

    var resourcePackZipFile: File? = null

    init {
        val resourcePackFolder: File = plugin.fileManager.saveResourceFolder(resourcePackResourceFolderName, true)

        val resourceZipFilePath = plugin.dataPath.toString() + File.separator + resourcePackResourceFolderName + ".zip"

        try {
            resourcePackZipFile = File(resourceZipFilePath)
            FileUtility.zipFolder(resourcePackFolder, resourcePackZipFile!!)

            FileUtils.deleteDirectory(resourcePackFolder)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
