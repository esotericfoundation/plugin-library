package foundation.esoteric.minecraft.plugins.library.block

import com.google.gson.Gson
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.io.*

class CustomBlockManager(plugin: JavaPlugin) {
    private val plugin: JavaPlugin = plugin

    private val multiblocksFileExtension = ".json"
    private val multiblocksResourceFilePath = "data" + File.separator + "multiblocks" + multiblocksFileExtension
    private val multiblocksFilePath: String = plugin.getDataFolder().toString() + File.separator + multiblocksResourceFilePath
    private val multiblocksFile = File(multiblocksFilePath)

    private val customMultiblockMap: MutableMap<String, CustomBlock> = HashMap()

    fun getPlugin(): JavaPlugin {
        return plugin
    }

    init {
        load()
    }

    fun addCustomMultiblock(multiblockId: String, abstractCustomMultiblock: CustomBlock) {
        customMultiblockMap[multiblockId] = abstractCustomMultiblock
    }

    fun getAbstractCustomMultiblock(multiblockId: String): CustomBlock? {
        return customMultiblockMap[multiblockId]
    }

    fun placeCustomMultiblock(multiblockId: String, location: Location?) {
        customMultiblockMap[multiblockId]?.getCustomMultiblock(location)
    }

    val allMultiblocks: List<Any>
        get() {
            val allMultiblocks: MutableList<CustomBlock> = ArrayList()

            for (multiblockInfo in customMultiblockMap.entries) {
                allMultiblocks.add(multiblockInfo.value)
            }

            return allMultiblocks
        }

    private fun load() {
        if (!multiblocksFile.exists()) {
            return
        }

        val multiblocks: StoredCustomBlocks

        val gson = Gson()
        try {
            val reader: Reader = FileReader(multiblocksFile)

            multiblocks = gson.fromJson(reader, StoredCustomBlocks::class.java)

            reader.close()
        } catch (exception: IOException) {
            exception.printStackTrace()
            return
        }

        loadStoredCustomMultiblocks(multiblocks)
    }

    fun save() {
        val dataToSave: StoredCustomBlocks = storedCustomMultiblocks

        if (dataToSave.getStoredCustomMultiblocks().isEmpty()) {
            return
        }

        if (!multiblocksFile.exists()) {
            plugin.saveResource(multiblocksResourceFilePath, false)
        }

        val gson = Gson()

        try {
            val writer: Writer = FileWriter(multiblocksFile)

            val json = gson.toJson(dataToSave)

            writer.write(json)

            writer.flush()
            writer.close()
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }

    fun loadStoredCustomMultiblocks(multiblocks: StoredCustomBlocks) {
        for (multiblock in multiblocks.getStoredCustomMultiblocks()) {
            customMultiblockMap[multiblock.getMultiblockId()]?.addMultiblocks(multiblock.blockLocations)
        }
    }

    val storedCustomMultiblocks: StoredCustomBlocks
        get() {
            val output: StoredCustomBlocks = StoredCustomBlocks()

            for (multiblockInfo in customMultiblockMap.entries) {
                output.addCustomMultiblock(StoredCustomBlock(multiblockInfo))
            }

            return output
        }
}
