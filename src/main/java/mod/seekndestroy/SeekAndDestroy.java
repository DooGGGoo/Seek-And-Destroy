package mod.seekndestroy;

import mod.seekndestroy.register.*;
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
		ModComponents.registerModComponents();
        ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModEntities.registerModEntities();
//		ModRenderers.registerModRenderers(); // See client class

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier ID(String path)
	{
		return Identifier.of(MOD_ID, path);
	}
}