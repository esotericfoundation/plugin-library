package foundation.esoteric.minecraft.plugins.library.block

import foundation.esoteric.minecraft.plugins.library.utility.types.BlockLocation
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class CustomBlock(customMultiblockManager: CustomBlockManager, private val customblockId: String) :
    Listener {
    protected val plugin: JavaPlugin = customMultiblockManager.getPlugin()

    private val customBlocks: MutableList<List<BlockLocation>> = ArrayList()

    fun getMultiblocks(): List<List<BlockLocation>> {
        return customBlocks
    }

    fun addMultiblocks(addedMultiblocks: List<List<BlockLocation>>?) {
        customBlocks.addAll(addedMultiblocks!!)
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)

        customMultiblockManager.addCustomMultiblock(customblockId, this)
    }

    protected abstract fun generateCustomMultiblock(placeLocation: Location?): List<Block>

    fun getCustomMultiblock(placeLocation: Location?): List<Block> {
        val multiblock = generateCustomMultiblock(placeLocation)

        customBlocks.add(multiblock.stream().map { block: Block? -> BlockLocation(block!!) }.toList())

        return multiblock
    }

    fun isBlock(block: Block?): Boolean {
        for (multiblock in customBlocks) {
            if (multiblock.contains(BlockLocation(block!!))) {
                return true
            }
        }

        return false
    }
}
