package mod.seekndestroy.block.entity;

import mod.seekndestroy.register.ModBlockEntities;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GroundRadarBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory
{
	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 360;

	public GroundRadarBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.GROUND_RADAR_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate()
		{
			@Override
			public int get(int index)
			{
				return switch (index)
				{
					case 0 -> GroundRadarBlockEntity.this.progress;
					case 1 -> GroundRadarBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value)
			{
				switch (index)
				{
					case 0 -> GroundRadarBlockEntity.this.progress = value;
					case 1 -> GroundRadarBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int size()
			{
				return 2;
			}
		};
	}

	@Override
	public Object getScreenOpeningData(ServerPlayerEntity player)
	{
		return null;
	}

	@Override
	public Text getDisplayName()
	{
		return null;
	}

	@Override
	public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
	{
		nbt.putInt("radar_scan.rogress", progress);

		super.writeNbt(nbt, wrapper);
	}

	@Override
	public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
	{
		super.readNbt(nbt, wrapper);

		progress = nbt.getInt("radar_scan.progress");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
	{
		return null;
	}
}
