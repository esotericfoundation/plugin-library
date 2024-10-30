package foundation.esoteric.minecraft.plugins.library.file

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.*

class FileManagerTest {

    private lateinit var plugin: TestPlugin

    @BeforeTest fun mockFileManager() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
    }

    @Test fun savingFolderWorks() {
        plugin.fileManager.saveResourceFolder("file/FileManagerTest")

        val fileDir = File(plugin.dataFolder, "file")
        val fileManagerTestDir = File(fileDir, "FileManagerTest")

        val testFileOne = File(fileManagerTestDir, "Test File 1.txt")
        val testFileTwo = File(fileManagerTestDir, "Test File 2.txt")

        val subFolder = File(fileManagerTestDir, "Subfolder")

        val testFileThree = File(subFolder, "Test File 3.txt")

        assertTrue(fileDir.exists())
        assertTrue(fileDir.isDirectory)
        assertNotNull(fileDir.listFiles())
        assertEquals(fileDir.listFiles()!!.size, 1)

        assertTrue(fileManagerTestDir.exists())
        assertTrue(fileManagerTestDir.isDirectory)
        assertNotNull(fileManagerTestDir.listFiles())
        assertEquals(fileManagerTestDir.listFiles()!!.size, 3)

        assertTrue(testFileOne.exists())
        assertFalse(testFileOne.isDirectory)
        assertEquals(testFileOne.readText().trimEnd('\r', '\n'), "This file is used to test the FileManager.")

        assertTrue(testFileTwo.exists())
        assertFalse(testFileTwo.isDirectory)
        assertEquals(testFileTwo.readText().trimEnd('\r', '\n'), "This file is used to test the FileManager.")

        assertTrue(subFolder.exists())
        assertTrue(subFolder.isDirectory)
        assertNotNull(subFolder.listFiles())
        assertEquals(subFolder.listFiles()!!.size, 1)

        assertTrue(testFileThree.exists())
        assertFalse(testFileThree.isDirectory)
        assertEquals(testFileThree.readText().trimEnd('\r', '\n'), "A third test file for the FileManagerTest.")
    }

    @AfterTest fun unmockFileManager() {
        MockBukkit.unmock()
    }
}
