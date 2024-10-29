package foundation.esoteric.minecraft.plugins.library.commands


import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.commandsenders.BukkitPlayer
import dev.jorel.commandapi.executors.ExecutionInfo
import dev.jorel.commandapi.executors.PlayerExecutionInfo
import foundation.esoteric.minecraft.plugins.library.item.CustomItemPlugin
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class GiveCustomItemCommand(plugin: CustomItemPlugin) : CommandAPICommand("give-custom-item") {
    init {
        val customItemArgumentNodeName = "custom-item-id"

        withArguments(StringArgument(customItemArgumentNodeName).includeSuggestions(ArgumentSuggestions.strings(plugin.customItemManager.getCustomItemIds())))
        withPermission(CommandPermission.OP)

        executesPlayer(PlayerExecutionInfo {
            executionInfo: ExecutionInfo<Player, BukkitPlayer>? ->
            val item = executionInfo!!.args()[0] as String

            plugin.customItemManager.giveCustomItem(item, executionInfo.sender())
        })

        register(plugin as JavaPlugin)
    }
}
