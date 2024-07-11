package mod.seekndestroy.register;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups
{
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TEST_ITEM))
            .displayName(Text.translatable("itemGroup.seekndestroy.main_group"))
            .entries((context, entries) -> {

                entries.add(ModItems.TEST_ITEM);
                entries.add(ModItems.TEST_GUN);
                entries.add(ModItems.TEST_MAG);

                entries.add(ModBlocks.TEST_BLOCK);
                entries.add(ModBlocks.GROUND_RADAR);

            })
            .build();

    public static void registerItemGroups()
    {
        Registry.register(Registries.ITEM_GROUP, Identifier.of("seekndestroy", "main_group"), ITEM_GROUP);
    }
}
