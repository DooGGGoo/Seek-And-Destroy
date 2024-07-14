package mod.seekndestroy.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class MagFedGunItem extends GunItem implements FabricItem
{
	private MagContentsComponent currentMagazine;

	public MagFedGunItem(Item.Settings settings, GunConfig gunConfig)
	{
		super(settings, gunConfig);
		this.currentMagazine = new MagContentsComponent(ItemStack.EMPTY);
	}

	public void loadMag(ItemStack gunStack, ItemStack magStack)
	{
		if (currentMagazine.isEmpty())
		{
			this.currentMagazine = new MagContentsComponent(magStack);
			magStack.setCount(0); // Remove the magazine from the player's inventory
		}
		else
		{
			// Optionally: handle the case where there's already a magazine loaded
		}
	}

	public ItemStack unloadMag()
	{
		ItemStack unloadedMag = currentMagazine.toItemStack();
		currentMagazine.clear();
		return unloadedMag;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (hand == Hand.OFF_HAND)
		{
			return TypedActionResult.pass(stack);
		}

		if (currentMagazine.isEmpty() || currentMagazine.isOutOfAmmo())
		{
			// Optionally: Play a sound or show a message that the gun is empty
			return TypedActionResult.fail(stack);
		}

		// Fire the gun, consume a round, and play the fire sound
		currentMagazine.consumeRound();
		if (!world.isClient)
		{
			world.playSound(null, user.getX(), user.getY(), user.getZ(), fireSound, user.getSoundCategory(), 1.0F, 1.0F);
			spawnShootParticles(world, user.getPos(), user.getRotationVec(1.0F));
		}
		return TypedActionResult.success(stack);
	}

	@Override
	public void reload(PlayerEntity player)
	{

	}

	@Override
	public void consumeRound(ItemStack itemStack)
	{
		currentMagazine.consumeRound();
	}

	@Override
	public boolean isEmpty(ItemStack itemStack)
	{
		return currentMagazine.isOutOfAmmo();
	}

	@Override
	public int getRoundCount(ItemStack itemStack)
	{
		return currentMagazine.getCurrentRounds();
	}



	private static class MagContentsComponent
	{
		private ItemStack stack;

		public MagContentsComponent(ItemStack stack)
		{
			this.stack = stack;
		}

		public boolean isEmpty()
		{
			return stack.isEmpty();
		}

		public boolean isOutOfAmmo()
		{
			if (stack.getItem() instanceof GunMagazineItem magItem)
			{
				return magItem.getCurrentRounds(stack) <= 0;
			}
			return true;
		}

		public void consumeRound()
		{
			if (stack.getItem() instanceof GunMagazineItem magItem)
			{
				int currentRounds = magItem.getCurrentRounds(stack);
				if (currentRounds > 0)
				{
					magItem.setCurrentRounds(stack, currentRounds - 1);
				}
			}
		}

		public int getCurrentRounds()
		{
			if (stack.getItem() instanceof GunMagazineItem magItem)
			{
				return magItem.getCurrentRounds(stack);
			}
			return 0;
		}

		public ItemStack toItemStack()
		{
			return stack.copy();
		}

		public void clear()
		{
			stack = ItemStack.EMPTY;
		}
	}
}
