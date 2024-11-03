package foundation.esoteric.minecraft.plugins.library.utility.plugin

import foundation.esoteric.utility.resource.saveResources
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.nio.file.Path

fun Plugin.saveResource(resourcePath: Path, replace: Boolean = true) {
    saveResource(resourcePath.toString(), replace)
}

fun Plugin.saveResource(resourcePath: String) {
    saveResource(resourcePath, true)
}

fun Plugin.saveResources(resourceFolderPath: Path): File {
    val subFolder = File(dataFolder, resourceFolderPath.toString())
    return resourceFolderPath.saveResources(subFolder)
}
