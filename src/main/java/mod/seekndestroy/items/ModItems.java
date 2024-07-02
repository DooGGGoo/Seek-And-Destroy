package mod.seekndestroy.items;

import mod.seekndestroy.SeekAndDestroy;
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

    private static void addToItemGroup(FabricItemGroupEntries entries)
    {
        entries.add(TEST_ITEM);
    }

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(SeekAndDestroy.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToItemGroup);
    }
}
