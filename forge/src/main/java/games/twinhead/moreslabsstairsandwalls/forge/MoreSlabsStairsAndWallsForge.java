package games.twinhead.moreslabsstairsandwalls.forge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.registry.forge.ModRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MoreSlabsStairsAndWalls.MOD_ID)
public class MoreSlabsStairsAndWallsForge {

    public MoreSlabsStairsAndWallsForge() {
        MoreSlabsStairsAndWalls.init();
        ModRegistry registry = new ModRegistry();
        FMLJavaModLoadingContext.get().getModEventBus().register(registry);
        ModRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModRegistry.ITEM_GROUPS.register(FMLJavaModLoadingContext.get().getModEventBus());


    }
}