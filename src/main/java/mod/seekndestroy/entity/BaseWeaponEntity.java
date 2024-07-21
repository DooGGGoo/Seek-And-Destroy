package mod.seekndestroy.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;

import java.util.OptionalInt;

public abstract class BaseWeaponEntity extends ProjectileEntity
{
	public static final TrackedData<OptionalInt> SHOOTER_ID = DataTracker.registerData(BaseWeaponEntity.class, TrackedDataHandlerRegistry.OPTIONAL_INT);
	public static final TrackedData<Integer> AGE = DataTracker.registerData(BaseWeaponEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> MAX_AGE = DataTracker.registerData(BaseWeaponEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public BaseWeaponEntity(EntityType<? extends ProjectileEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder)
	{
		builder.add(SHOOTER_ID, OptionalInt.empty());
		builder.add(AGE,  0);
		builder.add(MAX_AGE, 2048);
	}

	public boolean shouldRender(double distance) {
		return distance < 4096;
	}

	public boolean shouldRender(double cameraX, double cameraY, double cameraZ)
	{
		return super.shouldRender(cameraX, cameraY, cameraZ);
	}
}
