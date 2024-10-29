package foundation.esoteric.minecraft.plugins.library.utility.types

import org.bukkit.Location
import org.bukkit.block.Block

class BlockLocation(val worldName: String, val x: Int, val y: Int, val z: Int) {
    constructor(location: Location) : this(
        location.world.name,
        location.x.toInt(),
        location.y.toInt(),
        location.z.toInt()
    )

    constructor(block: Block) : this(block.location)
}
