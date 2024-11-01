package foundation.esoteric.minecraft.plugins.library.pack.resource

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ResourcePackTest {

    private var plugin: TestPlugin? = null

    @BeforeTest fun mockResourcePackPlugin() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
    }

    @Test fun resourcePackSavingWorks() {
        val resourcePackFolder = File(plugin!!.dataFolder, "TestPluginResourcePack.zip")
        assertTrue(resourcePackFolder.exists())
    }

    @AfterTest fun unmockResourcePackPlugin() {
        MockBukkit.unmock()
    }
}
