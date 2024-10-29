package foundation.esoteric.minecraft.plugins.library.entity

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

abstract class CustomEntity<E : Entity?> protected constructor(protected val plugin: CustomEntityPlugin, private val entityId: String) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)

        plugin.customEntityManager.addCustomEntity(entityId, this)
    }

    protected abstract fun generateCustomEntity(spawnLocation: Location?): List<E>

    fun getCustomEntity(spawnLocation: Location?): List<E> {
        val entities = generateCustomEntity(spawnLocation)
        for (entity in entities) {
            entity!!.persistentDataContainer.set(
                plugin.customEntityManager.customEntityIdKey,
                PersistentDataType.STRING,
                entityId
            )
        }

        return entities
    }

    fun isEntity(entity: Entity?): Boolean {
        if (entity == null) {
            return false
        }

        return entityId === entity.persistentDataContainer.get(plugin.customEntityManager.customEntityIdKey, PersistentDataType.STRING)
    }
}
