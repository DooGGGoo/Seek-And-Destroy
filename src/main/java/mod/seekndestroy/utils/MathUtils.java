package mod.seekndestroy.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;


public class MathUtils
{

	public static boolean isPointInsideCone(Vec3d origin, Vec3d point, Vec3d direction, float maxDistance, float maxAngleDegrees)
	{
		direction = direction.normalize();
		Vec3d vectorToPoint = point.subtract(origin);
		double distanceToPoint = vectorToPoint.length();

		if (distanceToPoint > maxDistance)
		{
			return false;
		}

		double angleRadians = Math.acos(direction.dotProduct(vectorToPoint) / (direction.length() * distanceToPoint));
		double maxAngleRadians = Math.toRadians(maxAngleDegrees);

		return angleRadians <= maxAngleRadians;
	}

	public static Vec3d intToDirectionVector(int directionDegrees)
	{
		double radians = Math.toRadians(directionDegrees);

		double x = Math.cos(radians);
		double z = Math.sin(radians);

		return new Vec3d(x, 0, z).normalize();
	}


	public static Vec3i toVec3i(Vec3d vec3d)
	{
		return new Vec3i((int)vec3d.getX(), (int)vec3d.getY(), (int)vec3d.getZ());
	}

	public static Vec3i toVec3i(BlockPos blockPos)
	{
		return new Vec3i(blockPos.getX(), blockPos.getY(), blockPos.getZ());
	}

	public static Vec3d toVec3d(BlockPos blockPos)
	{
		return new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
	}
}
