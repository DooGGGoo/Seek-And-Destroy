package mod.seekndestroy.item;


import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class GunItem extends Item implements FabricItem
{
	private final float recoil;
	private final SoundEvent  fireSound;

	public GunItem(Item.Settings settings, GunConfig gunConfig)
	{
		super(settings.maxCount(1));

		this.recoil = gunConfig.recoil;
		this.fireSound = gunConfig.fireSound;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		ItemStack stack = user.getStackInHand(hand);

		if (hand == Hand.OFF_HAND)
		{
			return TypedActionResult.pass(stack);
		}

		return TypedActionResult.success(stack);
	}

	public static void spawnShootParticles(World world, Vec3d pos, Vec3d dir) {
		Random random = world.getRandom();

		for (int i = 0; i < 6; i++) {
			double spread = 0.1;
			double offsetX = random.nextGaussian() * spread;
			double offsetY = random.nextGaussian() * spread;
			double offsetZ = random.nextGaussian() * spread;

			Vec3d particlePos = pos.add(offsetX, offsetY, offsetZ);
			world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, particlePos.x, particlePos.y, particlePos.z, dir.x, dir.y, dir.z);
		}
	}

	public void reload(PlayerEntity player)
	{}

	public void consumeRound(ItemStack itemStack)
	{}

	public boolean isEmpty(ItemStack itemStack)
	{
		return getRoundCount(itemStack) <= 0;
	}

	public int getRoundCount(ItemStack itemStack)
	{
		return 0;
	}

	@Override
	public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack)
	{
		return false;
	}
}

