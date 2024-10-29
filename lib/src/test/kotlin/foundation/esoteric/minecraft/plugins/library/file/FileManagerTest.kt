package foundation.esoteric.minecraft.plugins.library.file

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class FileManagerTest {

    private lateinit var plugin: TestPlugin
    private lateinit var fileManager: FileManager

    @BeforeTest fun mockFileManager() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
        fileManager = FileManager(plugin)
    }

    @Test fun savingFolderWorks() {
        fileManager.saveResourceFolder("file/FileManagerTest")

        assertTrue(File(plugin.dataFolder, "file").exists())
        assertTrue(File(plugin.dataFolder, "file/FileManagerTest").exists())
    }
}
