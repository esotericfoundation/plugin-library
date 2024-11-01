package foundation.esoteric.minecraft.plugins.library.item

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TexturedItemTest {

    private var plugin: TestPlugin? = null
    private var testItem: TestTexturedItem? = null

    @BeforeTest fun mockItemStack() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)

        testItem = TestTexturedItem(plugin!!)
    }

    @Test fun usesCorrectModelData() {
        val item = testItem!!.createItem()

        assertEquals(testItem!!::class.qualifiedName, "foundation.esoteric.minecraft.plugins.library.item.TestTexturedItem")
        assertEquals(testItem!!::class.qualifiedName.hashCode(), "foundation.esoteric.minecraft.plugins.library.item.TestTexturedItem".hashCode())
        assertEquals(item.itemMeta.customModelData, "foundation.esoteric.minecraft.plugins.library.item.TestTexturedItem".hashCode())
    }

    @AfterTest fun unmockItemStack() {
        MockBukkit.unmock()
    }
}
