package foundation.esoteric.minecraft.plugins.library.resourcepack

import foundation.esoteric.minecraft.plugins.library.file.FileManagedPlugin
import foundation.esoteric.utility.file.FileUtility
import foundation.esoteric.utility.file.FileUtility.Companion.isRecursivelyEmpty
import org.apache.commons.io.FileUtils
import java.io.File

class ResourcePackManager(private val plugin: FileManagedPlugin) {

    val resourcePackResourceFolderName = plugin.name + "ResourcePack"

    val resourcePackFileType: String = "application"
    val resourcePackFileExtension: String = "zip"
    val resourcePackFileMimeType: String = "$resourcePackFileType/$resourcePackFileExtension"

    private val resourcePackAssetsFolderName = "assets"

    var resourceZipFilePath: String? = null
        private set
    var resourcePackZipFile: File? = null
        private set

    init {
        saveResourcepackZipFile()
    }

    private fun saveResourcepackZipFile() {
        val resourcePackFolder: File = plugin.fileManager.saveResourceFolder(resourcePackResourceFolderName, true)

        val resourcePackFiles = resourcePackFolder.list()
        if (resourcePackFiles == null || resourcePackFiles.isEmpty()) {
            return
        }

        if (!listOf(*resourcePackFiles).contains(resourcePackAssetsFolderName)) {
            return
        }

        val assetsFile = File(plugin.dataPath.toString() + File.separator + resourcePackResourceFolderName + File.separator + resourcePackAssetsFolderName)
        if (isRecursivelyEmpty(assetsFile)) {
            return
        }

        resourceZipFilePath = plugin.dataPath.toString() + File.separator + resourcePackResourceFolderName + "." + resourcePackFileExtension

        try {
            resourcePackZipFile = File(resourceZipFilePath!!)
            FileUtility.zipFolder(resourcePackFolder, resourcePackZipFile!!)

            FileUtils.deleteDirectory(resourcePackFolder)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
