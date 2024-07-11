package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.item.GunConfig;
import mod.seekndestroy.item.GunItem;
import mod.seekndestroy.item.GunMagazineItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));

    public static final GunItem TEST_GUN = registerItem("test_gun", new GunItem(new Item.Settings().maxCount(1), new GunConfig()
            .fireSound(SoundEvents.BLOCK_FIRE_EXTINGUISH)
            .fireRate(15)
            .rangedDamage(4f)
            .recoil(0.4f))
    );

    public static final GunMagazineItem TEST_MAG = registerItem("test_mag", new GunMagazineItem(new Item.Settings().maxCount(1), 30));



    private static void addToItemGroup(FabricItemGroupEntries entries)
    {
        entries.add(TEST_ITEM);
    }

    public static <T extends Item> T registerItem(String name, T item)
    {
        return Registry.register(Registries.ITEM, SeekAndDestroy.ID(name), item);
    }

    public static void registerModItems()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToItemGroup);
    }
}
