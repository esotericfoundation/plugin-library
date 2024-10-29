package foundation.esoteric.minecraft.plugins.library.entity

import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class CustomEntityManager(private val plugin: JavaPlugin) {

    private val customEntityMap: MutableMap<String, CustomEntity<*>> = HashMap()

    val customEntityIdKey: NamespacedKey = NamespacedKey(plugin, "custom_entity_id")

    fun getPlugin(): JavaPlugin {
        return plugin
    }

    fun addCustomEntity(itemId: String, customEntity: CustomEntity<*>) {
        customEntityMap[itemId] = customEntity
    }

    fun getAbstractCustomEntity(entityId: String): CustomEntity<*>? {
        return customEntityMap[entityId]
    }

    fun spawnEntity(entityId: String, location: Location?): List<Entity?>? {
        return getAbstractCustomEntity(entityId)?.getCustomEntity(location)
    }
}
