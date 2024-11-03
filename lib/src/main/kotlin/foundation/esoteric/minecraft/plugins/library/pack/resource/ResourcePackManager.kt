package foundation.esoteric.minecraft.plugins.library.pack.resource

import foundation.esoteric.minecraft.plugins.library.utility.plugin.saveResources
import foundation.esoteric.utility.file.zip
import foundation.esoteric.minecraft.plugins.library.item.TexturedItem
import org.apache.commons.io.FileUtils
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * A class that helps your plugin to implement custom resources via a resource pack.
 * @param plugin The plugin that intends to implement a resource pack.
 * This plugin **must** have a resource pack in it's `resources` directory. It is recommended to make your resource pack in a
 * separate Git repository and add it as a submodule. The resource pack directory **MUST** be named `plugin.name + "ResourcePack"`.
 * For example, if your plugin's name (the name that appears in-game when running `/plugins`) is `SCPPlugin`, then the resource pack
 * must be named `SCPPluginResourcePack`.
 * @see TexturedItem
 * @author Esoteric Enderman
 */
class ResourcePackManager(internal val plugin: JavaPlugin) {

    internal val resourcePath = plugin.name + "ResourcePack"
    internal val zipFile = File(plugin.dataFolder, "$resourcePath.zip")

    init {
        val resourcePackFolder = plugin.saveResources(resourcePath)

        try {
            resourcePackFolder.zip(zipFile)

            FileUtils.deleteDirectory(resourcePackFolder)

            ResourcePackServer(this)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
