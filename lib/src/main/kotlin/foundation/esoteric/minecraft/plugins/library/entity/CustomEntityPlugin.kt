package foundation.esoteric.minecraft.plugins.library.entity

import org.bukkit.plugin.Plugin

interface CustomEntityPlugin : Plugin {
    val customEntityManager : CustomEntityManager
}
