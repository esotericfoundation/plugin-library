package foundation.esoteric.minecraft.plugins.library.file

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.*

class FileManagerTest {

    private lateinit var plugin: TestPlugin
    private lateinit var fileManager: FileManager

    @BeforeTest fun mockFileManager() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
        fileManager = FileManager(plugin)
    }

    @Test fun savingFolderWorks() {
        fileManager.saveResourceFolder("file/FileManagerTest/1")

        val fileDir = File(plugin.dataFolder, "file")
        val fileManagerTestDir = File(fileDir, "FileManagerTest")
        val testOneDir = File(fileManagerTestDir, "1")

        val testFileOne = File(testOneDir, "Test File 1.txt")
        val testFileTwo = File(testOneDir, "Test File 2.txt")

        val subFolder = File(testOneDir, "Subfolder")

        val testFileThree = File(subFolder, "Test File 3.txt")

        assertTrue(fileDir.exists())
        assertTrue(fileDir.isDirectory)
        assertNotNull(fileDir.listFiles())
        assertEquals(fileDir.listFiles()!!.size, 1)

        assertTrue(fileManagerTestDir.exists())
        assertTrue(fileManagerTestDir.isDirectory)
        assertNotNull(fileManagerTestDir.listFiles())
        assertEquals(fileManagerTestDir.listFiles()!!.size, 1)

        assertTrue(testOneDir.exists())
        assertTrue(testOneDir.isDirectory)
        assertNotNull(testOneDir.listFiles())
        assertEquals(testOneDir.listFiles()!!.size, 3)

        assertTrue(testFileOne.exists())
        assertFalse(testFileOne.isDirectory)
        assertEquals(testFileOne.readText(), "This file is used to test the FileManager.\r\n")

        assertTrue(testFileTwo.exists())
        assertFalse(testFileTwo.isDirectory)
        assertEquals(testFileTwo.readText(), "This file is used to test the FileManager.\r\n")

        assertTrue(subFolder.exists())
        assertTrue(subFolder.isDirectory)
        assertNotNull(subFolder.listFiles())
        assertEquals(subFolder.listFiles()!!.size, 1)

        assertTrue(testFileThree.exists())
        assertFalse(testFileThree.isDirectory)
        assertEquals(testFileThree.readText(), "A third test file for the FileManagerTest.\r\n")
    }
}
