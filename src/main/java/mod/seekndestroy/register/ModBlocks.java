package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.block.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks
{
	public static final Block TEST_BLOCK = registerBlockWithItem("test_block",
			new Block(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block GROUND_RADAR = registerBlockWithItem("ground_radar",
			new GroundRadarBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
					.nonOpaque()));

	public static <T extends Block> T registerBlock(final String name, final T block)
	{
		return Registry.register(Registries.BLOCK, SeekAndDestroy.ID(name), block);
	}

	public static <T extends Block> T registerBlockWithItem(String name, T block, Item.Settings settings)
	{
		T registeredBlock = registerBlock(name, block);
		ModItems.registerItem(name, new BlockItem(registeredBlock, settings));
		return registeredBlock;
	}

	public static <T extends Block> T registerBlockWithItem(String name, T block)
	{
		return registerBlockWithItem(name, block, new Item.Settings());
	}

	public static void registerModBlocks()
	{

	}
}
