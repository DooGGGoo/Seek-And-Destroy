package mod.seekndestroy.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class WorldUtils
{
	public static boolean isPathObstructed(World world, Vec3d sourcePos, Vec3d targetPos)
	{
		BlockPos pos =  new BlockPos(MathUtils.toVec3i(sourcePos));
		final VoxelShape VOXEL_SHAPE = VoxelShapes.fullCube();
		BlockState blockState = world.getBlockState(pos);

		BlockHitResult result = world.raycastBlock(sourcePos, targetPos, pos, VOXEL_SHAPE, blockState);

		return result != null && result.isInsideBlock();
	}
}
