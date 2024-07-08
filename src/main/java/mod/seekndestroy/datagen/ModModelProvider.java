package mod.seekndestroy.datagen;

import mod.seekndestroy.register.ModBlocks;
import mod.seekndestroy.register.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider
{
	public ModModelProvider(FabricDataOutput output)
	{
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
	{
		blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TEST_BLOCK);
		blockStateModelGenerator.registerSimpleState(ModBlocks.GROUND_RADAR);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator)
	{
		itemModelGenerator.register(ModItems.TEST_ITEM, Models.GENERATED);
		itemModelGenerator.register(ModItems.TEST_GUN, Models.HANDHELD);
		itemModelGenerator.register(ModItems.TEST_MAG, Models.GENERATED);
	}
}
