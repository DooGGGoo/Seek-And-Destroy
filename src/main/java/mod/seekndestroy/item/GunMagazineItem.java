package mod.seekndestroy.item;

import mod.seekndestroy.register.ModComponents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GunMagazineItem extends Item {
	private final int maxRounds;

	public GunMagazineItem(Settings settings, int maxRounds) {
		super(settings.maxCount(1));
		this.maxRounds = maxRounds;
	}

	public int getCurrentRounds(ItemStack stack)
	{
		return stack.getOrDefault(ModComponents.CURRENT_AMMO, 0);
	}

	public void setCurrentRounds(ItemStack stack, int rounds)
	{
		rounds = Math.clamp(rounds, 0, getMaxRounds());
		ComponentChanges changes = ComponentChanges.builder().add(ModComponents.CURRENT_AMMO, rounds).build();
		stack.applyChanges(changes);
	}

	public int getMaxRounds() {
		return maxRounds;
	}
}
