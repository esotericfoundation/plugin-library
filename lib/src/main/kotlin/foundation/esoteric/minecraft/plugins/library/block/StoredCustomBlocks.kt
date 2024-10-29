package foundation.esoteric.minecraft.plugins.library.block


class StoredCustomBlocks {
    private val storedCustomMultiblocks: MutableList<StoredCustomBlock> = ArrayList()

    fun getStoredCustomMultiblocks(): List<StoredCustomBlock> {
        return storedCustomMultiblocks
    }

    fun addCustomMultiblock(addedCustomMultiblocks: StoredCustomBlock) {
        storedCustomMultiblocks.add(addedCustomMultiblocks)
    }
}
