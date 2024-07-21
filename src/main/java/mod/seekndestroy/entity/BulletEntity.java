package mod.seekndestroy.entity;

import mod.seekndestroy.register.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BulletEntity extends BaseWeaponEntity
{

	public BulletEntity(EntityType<? extends BaseWeaponEntity> entityType, World world)
	{
		super(entityType, world);
	}

	public BulletEntity(World world, double x, double y, double z)
	{
		super(ModEntities.BULLET, world);
		this.dataTracker.set(AGE, 0);
		this.dataTracker.set(MAX_AGE, 100 * this.random.nextInt(6) + this.random.nextInt(7));
		this.setPosition(x, y, z);
	}

	@Override
	public void tick()
	{
		super.tick();

		Vec3d vec3d = this.getVelocity();


		double e = vec3d.getX(),
				f = vec3d.getY(),
				g = vec3d.getZ();

		double h = this.getX() + e;
		double j = this.getY() + f;
		double k = this.getZ() + g;
		double l = vec3d.horizontalLength();


		if (this.prevPitch == 0.0F && this.prevYaw == 0.0F)
		{
			double d = vec3d.horizontalLength();
			this.setYaw((float)(MathHelper.atan2(vec3d.getX(), vec3d.getZ()) * 180.0F / (float)Math.PI));
			this.setPitch((float)(MathHelper.atan2(vec3d.getY(), d) * 180.0F / (float)Math.PI));
			this.prevYaw = this.getYaw();
			this.prevPitch = this.getPitch();
		}

		this.setYaw((float)(MathHelper.atan2(e, g) * 180.0F / (float)Math.PI));
		this.setPitch((float)(MathHelper.atan2(f, l) * 180.0F / (float)Math.PI));

		this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
		this.setYaw(updateRotation(this.prevYaw, this.getYaw()));

		int age = this.dataTracker.get(AGE);
		int maxAge = this.dataTracker.get(MAX_AGE);

		this.move(MovementType.SELF, vec3d);
		this.setVelocity(vec3d);

		this.setPosition(h, j, k);
		this.checkBlockCollision();

		HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
		if (!this.noClip)
		{
			this.hitOrDeflect(hitResult);
			this.velocityDirty = true;
		}

		this.updateRotation();

		this.dataTracker.set(AGE, age + 1);

		if (!this.getWorld().isClient && age > maxAge) {
			this.discard();
		}
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult)
	{
		super.onEntityHit(entityHitResult);
		double damage = 2;
		entityHitResult.getEntity().damage(this.getDamageSources().generic(), (float) damage);
		this.discard();
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);

		if (this.getWorld().isClient)
		{
			Vec3d pos = blockHitResult.getPos();
			Vec3d vel = this.getVelocity().normalize();
			getWorld().playSoundAtBlockCenter(blockHitResult.getBlockPos(), SoundEvents.BLOCK_ANVIL_HIT, SoundCategory.BLOCKS, 0.4f, 1.2f, true);
			getWorld().addParticle(DustParticleEffect.DEFAULT, pos.getX(), pos.getY(), pos.getZ(), vel.getX(), vel.getY(), vel.getZ());
		}

		this.discard();
	}

}
