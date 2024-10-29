package foundation.esoteric.minecraft.plugins.library.file

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class FileManagerTest {
    @Test fun savingResourceWorksCorrectly() {
        MockBukkit.mock()
        val plugin = MockBukkit.load(TestPlugin::class.java)

        plugin.fileManager.saveResourceFolder("file/FileManagerTest")

        assertTrue(File(plugin.dataFolder, "file").exists())
        assertTrue(File(plugin.dataFolder, "file/FileManagerTest").exists())
    }
}
