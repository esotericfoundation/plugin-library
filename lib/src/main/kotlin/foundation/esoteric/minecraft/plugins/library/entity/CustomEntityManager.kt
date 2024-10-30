package foundation.esoteric.minecraft.plugins.library.entity

import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class CustomEntityManager(plugin: JavaPlugin) {

    private val customEntityMap: MutableMap<String, CustomEntity<*>> = HashMap()

    val customEntityIdKey: NamespacedKey = NamespacedKey(plugin, "custom_entity_id")

    fun addEntity(itemId: String, customEntity: CustomEntity<*>) {
        customEntityMap[itemId] = customEntity
    }

    fun getEntity(entityId: String): CustomEntity<*>? {
        return customEntityMap[entityId]
    }

    fun getEntityIds(): Set<String> {
        return customEntityMap.keys
    }

    fun spawnEntity(entityId: String, location: Location): Entity? {
        return getEntity(entityId)?.createEntity(location)
    }
}
