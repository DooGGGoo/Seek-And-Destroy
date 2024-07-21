package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.entity.BulletEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities
{
	public static final EntityType<BulletEntity> BULLET = registerEntity("bullet",
			FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity::new)
					.dimensions(EntityDimensions.fixed(0.5f, 0.5f))
					.build()
	);

	public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entityType)
	{
		return Registry.register(Registries.ENTITY_TYPE, SeekAndDestroy.ID(name), entityType);
	}

	public static void registerModEntities() { }
}
