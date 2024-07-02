package mod.seekndestroy;

import mod.seekndestroy.register.ModBlocks;
import mod.seekndestroy.register.ModItemGroups;
import mod.seekndestroy.register.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeekAndDestroy implements ModInitializer
{
	public static final String MOD_ID = "seekndestroy";
    public static final Logger LOGGER = LoggerFactory.getLogger("seekndestroy");

	@Override
	public void onInitialize()
	{
        ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier ID(String path)
	{
		return Identifier.of(MOD_ID, path);
	}
}