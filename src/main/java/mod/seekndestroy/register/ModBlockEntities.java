package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.block.entity.GroundRadarBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities
{
	public static final BlockEntityType<GroundRadarBlockEntity> GROUND_RADAR_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			SeekAndDestroy.ID("ground_radar_block_entity"),
			FabricBlockEntityTypeBuilder.create(GroundRadarBlockEntity::new,
					ModBlocks.GROUND_RADAR).build());

	public static void registerModBlockEntities()
	{
	}
}
