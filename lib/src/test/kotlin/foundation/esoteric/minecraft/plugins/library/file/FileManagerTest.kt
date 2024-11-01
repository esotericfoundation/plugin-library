package foundation.esoteric.minecraft.plugins.library.file

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.*

class FileManagerTest {

    private var plugin: TestPlugin? = null

    private var fileDir: File? = null
    private var fileManagerTestDir: File? = null

    private var testFileOne: File? = null
    private var testFileTwo: File? = null
    private var resourcePack: File? = null

    private var subFolder: File? = null

    private var testFileThree: File? = null

    @BeforeTest fun mockFileManager() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)
        plugin!!.fileManager.saveResourceFolder("file/FileManagerTest")

        fileDir = File(plugin!!.dataFolder, "file")
        fileManagerTestDir = File(fileDir, "FileManagerTest")

        testFileOne = File(fileManagerTestDir, "Test File 1.txt")
        testFileTwo = File(fileManagerTestDir, "Test File 2.txt")
        resourcePack = File(fileManagerTestDir, "TestPluginResourcePack.zip")

        subFolder = File(fileManagerTestDir, "Subfolder")

        testFileThree = File(subFolder, "Test File 3.txt")
    }

    @Test fun savingFolderWorks() {

        assertTrue(fileDir!!.exists())
        assertTrue(fileDir!!.isDirectory)
        assertNotNull(fileDir!!.listFiles())
        assertEquals(fileDir!!.listFiles()!!.size, 1)

        assertTrue(fileManagerTestDir!!.exists())
        assertTrue(fileManagerTestDir!!.isDirectory)
        assertNotNull(fileManagerTestDir!!.listFiles())
        assertEquals(fileManagerTestDir!!.listFiles()!!.size, 4)

        assertTrue(testFileOne!!.exists())
        assertFalse(testFileOne!!.isDirectory)
        assertEquals(testFileOne!!.readText().trimEnd('\r', '\n'), "This file is used to test the FileManager.")

        assertTrue(testFileTwo!!.exists())
        assertFalse(testFileTwo!!.isDirectory)
        assertEquals(testFileTwo!!.readText().trimEnd('\r', '\n'), "This file is used to test the FileManager.")

        assertTrue(resourcePack!!.exists())
        assertFalse(resourcePack!!.isDirectory)

        assertTrue(subFolder!!.exists())
        assertTrue(subFolder!!.isDirectory)
        assertNotNull(subFolder!!.listFiles())
        assertEquals(subFolder!!.listFiles()!!.size, 1)

        assertTrue(testFileThree!!.exists())
        assertFalse(testFileThree!!.isDirectory)
        assertEquals(testFileThree!!.readText().trimEnd('\r', '\n'), "A third test file for the FileManagerTest.")
    }

    @Test fun resourcePackHashWorks() {
        val resourcePack = File(plugin!!.dataFolder, "file/fileManagerTest/TestPluginResourcePack.zip")
        assertTrue(resourcePack.exists())
        assertTrue(resourcePack.isFile)
        assertFalse(resourcePack.isDirectory)
    }

    @AfterTest fun unmockFileManager() {
        MockBukkit.unmock()
    }
}
