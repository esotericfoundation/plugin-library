package foundation.esoteric.minecraft.plugins.library.utility.plugin

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.io.path.Path
import kotlin.test.*

class PluginUtilityTest {

    private lateinit var plugin: TestPlugin

    @BeforeTest fun mockPlugin() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
    }

    @Test fun savingFolderWorks() {
        val resourcesPath = Path("TestPluginResourcePack")
        val file = File(plugin.dataFolder, resourcesPath.toString())

        plugin.saveResources(resourcesPath)
        assertTrue(file.exists())
        assertTrue(file.isDirectory)
        assertFalse(file.isFile)
        assertNotNull(file.listFiles())
        assertEquals(1, file.listFiles()!!.size)

        val packFile = File(file, "pack.mcmeta")
        assertTrue(packFile.exists())
        assertTrue(packFile.isFile)
        assertFalse(packFile.isDirectory)
    }

    @AfterTest fun unmockPlugin() {
        MockBukkit.unmock()
    }
}
