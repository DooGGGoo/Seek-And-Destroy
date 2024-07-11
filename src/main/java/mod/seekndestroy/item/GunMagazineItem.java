package mod.seekndestroy.item;

import mod.seekndestroy.register.ModComponents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class GunMagazineItem extends Item
{
	private final int maxRounds;

	public GunMagazineItem(Settings settings, int maxRounds)
	{
		super(settings.maxCount(1));
		this.maxRounds = maxRounds;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(Text.translatable("itemTooltip.seekndestroy.mag.currentAmmo").append(" " + getCurrentRounds(stack) + "/" + getMaxRounds()));
	}

	@Override
	public boolean isItemBarVisible(ItemStack stack) {
		return getCurrentRounds(stack) > 0;
	}

	@Override
	public int getItemBarStep(ItemStack stack)
	{
		return MathHelper.clamp(Math.round(10.0F - (float)getCurrentRounds(stack) * 10.0F / (float)getMaxRounds()), 0, 13);
	}

	@Override
	public int getItemBarColor(ItemStack stack)
	{
		return MathHelper.packRgb(0.4F, 0.4F, 1.0F);
	}



	public int getCurrentRounds(ItemStack stack)
	{
		return stack.getOrDefault(ModComponents.CURRENT_AMMO, setCurrentRounds(stack, 0));
	}

	public int setCurrentRounds(ItemStack stack, int rounds)
	{
		rounds = Math.clamp(rounds, 0, getMaxRounds());
		ComponentChanges changes = ComponentChanges.builder().add(ModComponents.CURRENT_AMMO, rounds).build();
		stack.applyChanges(changes);
		return rounds;
	}

	public int getMaxRounds() {
		return maxRounds;
	}
}
