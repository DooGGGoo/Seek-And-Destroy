package mod.seekndestroy.block;


import com.mojang.serialization.MapCodec;
import mod.seekndestroy.block.entity.GroundRadarBlockEntity;
import mod.seekndestroy.register.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GroundRadarBlock extends BlockWithEntity implements BlockEntityProvider
{
	private static final VoxelShape VOXEL_SHAPE =  Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public GroundRadarBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		return VOXEL_SHAPE;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
	{
		if (type != ModBlockEntities.GROUND_RADAR_BLOCK_ENTITY)
		{
			return null;
		}

		return (world1, pos, blockState, blockEntity) -> GroundRadarBlockEntity.tick(world1, pos, blockEntity);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new GroundRadarBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec()
	{
		return null;
	}
}
