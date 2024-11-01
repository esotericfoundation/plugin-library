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
    private var resourcePackFolder: File? = null

    @BeforeTest fun mockResourcePackPlugin() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
        resourcePackFolder = File(plugin!!.dataFolder, "TestPluginResourcePack.zip")
    }

    @Test fun resourcePackSavingWorks() {
        assertTrue(resourcePackFolder!!.exists())
    }

    @AfterTest fun unmockResourcePackPlugin() {
        MockBukkit.unmock()
    }
}
