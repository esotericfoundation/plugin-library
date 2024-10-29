package foundation.esoteric.minecraft.plugins.library.block

import foundation.esoteric.minecraft.plugins.library.utility.types.BlockLocation


class StoredCustomBlock(val blockLocations: List<List<BlockLocation>>, multiblockId: String) {
    private val multiblockId: String = multiblockId

    fun getMultiblockId(): String {
        return multiblockId
    }

    constructor(multiblockInfo: Map.Entry<String, CustomBlock>) : this(
        multiblockInfo.value.getMultiblocks(),
        multiblockInfo.key
    )
}
