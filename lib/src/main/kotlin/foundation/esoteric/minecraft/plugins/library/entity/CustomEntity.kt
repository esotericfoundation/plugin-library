package foundation.esoteric.minecraft.plugins.library.entity

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.persistence.PersistentDataType

abstract class CustomEntity<E : Entity> protected constructor(protected val plugin: CustomEntityPlugin, private val entityId: String, private val entityType: EntityType) : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)

        plugin.customEntityManager.addEntity(entityId, this)
    }

    fun createEntity(spawnLocation: Location): E {
        return spawnLocation.world.spawnEntity(spawnLocation, entityType, CreatureSpawnEvent.SpawnReason.DEFAULT) { entity ->
            toEntity(entity as E)
        } as E
    }

    open fun toEntity(vararg entities: E): Array<out E> {
        for (entity in entities) {
            entity.persistentDataContainer.set(
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
