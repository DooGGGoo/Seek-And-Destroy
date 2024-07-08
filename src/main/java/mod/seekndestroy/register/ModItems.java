package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.item.GunItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));

//    public static final Item GUN_TEST = registerItem("gun_test", new GunItem(new Item.Settings()
//            .maxCount(1),
//            10,
//            3F,
//            2F,
//            true
//    ));

    private static void addToItemGroup(FabricItemGroupEntries entries)
    {
        entries.add(TEST_ITEM);
//        entries.add(GUN_TEST);
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
