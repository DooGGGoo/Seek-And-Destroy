package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.dynamic.Codecs;

public class ModComponents
{
	public static final ComponentType<Integer> CURRENT_AMMO = ComponentType.<Integer>builder()
			.codec(Codecs.NONNEGATIVE_INT)
			.packetCodec(PacketCodecs.VAR_INT)
			.build();


	public static void registerModComponents()
	{
		Registry.register(Registries.DATA_COMPONENT_TYPE, SeekAndDestroy.ID("current_ammo"), CURRENT_AMMO);
	}
}
