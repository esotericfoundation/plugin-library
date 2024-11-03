package foundation.esoteric.minecraft.plugins.library.utility.plugin

import foundation.esoteric.utility.resource.saveResources
import org.bukkit.plugin.Plugin
import java.io.File
import java.nio.file.Path

/**
 * Saves the raw contents of any resource embedded with a plugin's .jar
 * file assuming it can be found using getResource(String).
 *
 * The resource is saved into the plugin's data folder using the same
 * hierarchy as the .jar file (subdirectories are preserved).
 *
 * @param resourcePath the embedded resource path to look for within the plugin's .jar file. (No preceding slash).
 * @param replace if true, the embedded resource will overwrite the contents of an existing file.
 * @throws IllegalArgumentException if the resource path is null, empty, or points to a nonexistent resource.
 * @see Plugin.saveResource
 * @see Plugin.saveResources
 * @author Esoteric Enderman
 */
fun Plugin.saveResource(resourcePath: Path, replace: Boolean = true) {
    saveResource(resourcePath.toString(), replace)
}

/**
 * Saves the raw contents of any resource embedded with a plugin's .jar
 * file assuming it can be found using getResource(String).
 *
 * The resource is saved into the plugin's data folder using the same
 * hierarchy as the .jar file (subdirectories are preserved).
 *
 * @param resourcePath the embedded resource path to look for within the plugin's .jar file. (No preceding slash).
 * @throws IllegalArgumentException if the resource path is null, empty, or points to a nonexistent resource.
 * @see Plugin.saveResource
 * @see Plugin.saveResources
 * @author Esoteric Enderman
 */
fun Plugin.saveResource(resourcePath: String) {
    saveResource(resourcePath, true)
}

fun Plugin.saveResources(resourceFolderPath: Path): File {
    val subFolder = File(dataFolder, resourceFolderPath.toString())
    return resourceFolderPath.saveResources(subFolder)
}
