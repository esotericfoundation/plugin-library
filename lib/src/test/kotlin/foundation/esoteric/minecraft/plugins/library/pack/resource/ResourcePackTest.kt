package foundation.esoteric.minecraft.plugins.library.pack.resource

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.*

class ResourcePackTest {

    private var plugin: TestPlugin? = null
    private var resourcePackZip: File? = null

    @BeforeTest fun mockResourcePackPlugin() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
        resourcePackZip = File(plugin!!.dataFolder, "TestPluginResourcePack.zip")
    }

    @Test fun resourcePackSavingWorks() {
        assertTrue(resourcePackZip!!.exists())
    }

    @AfterTest fun unmockResourcePackPlugin() {
        MockBukkit.unmock()
    }
}
