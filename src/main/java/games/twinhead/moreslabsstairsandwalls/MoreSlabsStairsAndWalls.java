package games.twinhead.moreslabsstairsandwalls;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.BlockRegistry;
import games.twinhead.moreslabsstairsandwalls.registry.ModFuelRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MoreSlabsStairsAndWalls implements ModInitializer {

    public static final String mod_id="more_slabs_stairs_and_walls";
    public static ItemGroup modGroup  = FabricItemGroupBuilder.build(
            new Identifier(mod_id, "creative_tab"),
            () -> new ItemStack(ModBlocks.GRASS_BLOCK.getStairsBlock()));

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        ModFuelRegistry.register();
    }
}
