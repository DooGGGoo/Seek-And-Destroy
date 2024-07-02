package mod.seekndestroy;

import mod.seekndestroy.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeekAndDestroy implements ModInitializer
{

	public static final String MOD_ID = "seekndestroy";
    public static final Logger LOGGER = LoggerFactory.getLogger("seekndestroy");

	@Override
	public void onInitialize()
	{
		ModItems.registerModItems();

		LOGGER.info("Hello Fabric world!");
	}
}