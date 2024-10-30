package foundation.esoteric.minecraft.plugins.library.messages

import be.seeseemelk.mockbukkit.MockBukkit
import foundation.esoteric.minecraft.plugins.library.TestPlugin
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class MessageManagerTest {
    @Test fun savesMessagesResource() {
        MockBukkit.mock()
        val plugin = MockBukkit.load(TestPlugin::class.java)

        val messageManager = MessageManager(plugin)

        val messagesFolder = File(plugin.dataFolder, "messages")
        assertTrue(messagesFolder.exists())
    }
}
