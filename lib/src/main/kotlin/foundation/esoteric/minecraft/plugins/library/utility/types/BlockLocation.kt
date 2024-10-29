package foundation.esoteric.minecraft.plugins.library.utility.types

import org.bukkit.Location
import org.bukkit.block.Block

/**
 * A utility class that represents the location of one block.
 * @param worldName The name of the world that this block is located in.
 * @param x The x location of this block.
 * @param y The y location of this block.
 * @param z The z location of this block.
 */
class BlockLocation(val worldName: String, val x: Int, val y: Int, val z: Int) {
    /**
     * Gets the location of the block at this Location.
     * @param location The location to get the block location of.
     */
    constructor(location: Location) : this(
        location.world.name,
        location.x.toInt(),
        location.y.toInt(),
        location.z.toInt()
    )

    /**
     * Gets the location of this block.
     * @param block The block to get the BlockLocation of.
     */
    constructor(block: Block) : this(block.location)
}
