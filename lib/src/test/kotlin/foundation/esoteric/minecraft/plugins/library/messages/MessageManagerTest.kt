package foundation.esoteric.minecraft.plugins.library.messages

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class MessageManagerTest {

    private var plugin: TestPlugin? = null
    private var messageManager: MessageManager? = null

    @BeforeTest fun mockMessageManager() {
        MockBukkit.mock()
        plugin = MockBukkit.load(TestPlugin::class.java)

        messageManager = MessageManager(plugin!!)
    }

    @Test fun savesMessagesResource() {
        val messagesFolder = File(plugin!!.dataFolder, "messages")
        assertTrue(messagesFolder.exists())
    }

    @AfterTest fun unmockMessageManager() {
        MockBukkit.unmock()
    }
}
