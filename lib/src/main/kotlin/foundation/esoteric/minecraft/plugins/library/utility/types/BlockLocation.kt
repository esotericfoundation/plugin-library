package foundation.esoteric.minecraft.plugins.library.utility.types

import org.bukkit.Location
import org.bukkit.block.Block

/**
 * A utility class that represents the location of one block.
 * @param worldName The name of the `World` that this block is located in.
 * @param x The x location of this block.
 * @param y The y location of this block.
 * @param z The z location of this block.
 * @property worldName The name of the `World` that this block is located in.
 * @property x The x location of this block.
 * @property y The y location of this block.
 * @property z The z location of this block.
 * @author Esoteric Enderman
 */
class BlockLocation(val worldName: String, val x: Int, val y: Int, val z: Int) {
    /**
     * Gets the location of the block at this `Location`.
     * @param location The `Location` to get the `BlockLocation` of.
     * @author Esoteric Enderman
     */
    constructor(location: Location) : this(
        location.world.name,
        location.x.toInt(),
        location.y.toInt(),
        location.z.toInt()
    )

    /**
     * Gets the location of this `Block`.
     * @param block The `Block` to get the `BlockLocation` of.
     * @author Esoteric Enderman
     */
    constructor(block: Block) : this(block.location)
}
