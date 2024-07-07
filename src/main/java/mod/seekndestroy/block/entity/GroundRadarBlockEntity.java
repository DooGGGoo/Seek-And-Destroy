package mod.seekndestroy.block.entity;

import mod.seekndestroy.register.ModBlockEntities;
import mod.seekndestroy.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


import java.util.List;

public class GroundRadarBlockEntity extends BlockEntity
{
	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private final int maxProgress = 360;

	private final int scanRate = 24;
	private final int scanRadius = 200;
	private final float scanAngle = 60f;
	//private float scanElevationAngle = 25f;

	public GroundRadarBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.GROUND_RADAR_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate()
		{
			@Override
			public int get(int index)
			{
				return switch (index)
				{
					case 0 -> GroundRadarBlockEntity.this.progress;
					//case 1 -> GroundRadarBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value)
			{
				switch (index)
				{
					case 0 -> GroundRadarBlockEntity.this.progress = value;
					//case 1 -> GroundRadarBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int size()
			{
				return 2;
			}
		};
	}

	public static void tick(World world, BlockPos pos, BlockEntity blockEntity)
	{
		if (blockEntity instanceof GroundRadarBlockEntity groundRadarBlockEntity)
		{
			if (world.getTime() % 2 == 0)
			{
				tickRadar(world, pos, groundRadarBlockEntity);
			}

			if (world.getTime() % 20 == 0)
			{
				groundRadarBlockEntity.markDirty();
			}
		}
	}

	// TODO: This can be made into separate interface
	// TODO: Add interface and target display
	private static void tickRadar(World world, BlockPos pos, GroundRadarBlockEntity groundRadarBlockEntity)
	{
		groundRadarBlockEntity.progress += groundRadarBlockEntity.scanRate;

		if (groundRadarBlockEntity.progress >= groundRadarBlockEntity.maxProgress)
		{
			world.playSound(null, pos, SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 0.6f, 1.2f);
			groundRadarBlockEntity.progress = 0;
		}

		Vec3d radarDirection = MathUtils.intToDirectionVector(groundRadarBlockEntity.progress);

		// spawn particle in search direction
		world.addImportantParticle(ParticleTypes.FLAME, pos.getX() + 0.5f + radarDirection.getX() * 2f, pos.getY() + 1f, pos.getZ() + 0.5f + radarDirection.getZ() * 2f, 0.0, 0.0, 0.0);

		final Box scanBox = new Box(pos).offset(0, -10, 0)
				.expand(groundRadarBlockEntity.scanRadius, 0, groundRadarBlockEntity.scanRadius)
				.stretch(0, 50, 0);

		List<Entity> entities = world.getOtherEntities(null, scanBox, entity -> entity instanceof LivingEntity);
		for (Entity entity : entities)
		{
			if (entity != null)
			{
				Vec3d entityPos = entity.getPos();
				final Vec3d originPos = MathUtils.toVec3d(pos);
				Vec3d dir = new Vec3d(radarDirection.getX(), pos.getY() + 1f, radarDirection.getZ());

				if (MathUtils.isPointInsideCone(originPos, entityPos, dir, groundRadarBlockEntity.scanRadius * 1.33f, groundRadarBlockEntity.scanAngle))
				{
					if (world.isClient())
					{
						world.playSoundAtBlockCenter(entity.getBlockPos(), SoundEvents.BLOCK_COPPER_BULB_TURN_ON, SoundCategory.BLOCKS, 0.8f, 1.1f, true);
					}
					world.addParticle(ParticleTypes.NOTE, entityPos.getX(), entityPos.getY(), entityPos.getZ(), 0.0, 0.0, 0.0);
				}
				// System.out.println(dir + " | " + groundRadarBlockEntity.progress + " | " + MathUtils.isPointInsideCone(originPos, entityPos, radarDirection, groundRadarBlockEntity.scanRadius * 1.33f, groundRadarBlockEntity.scanAngle));
			}
		}
	}

	@Override
	public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
	{
		nbt.putInt("scan_progress", this.progress);

		super.writeNbt(nbt, wrapper);
	}

	@Override
	public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
	{
		super.readNbt(nbt, wrapper);

		this.progress = nbt.getInt("scan_progress");
	}
}
