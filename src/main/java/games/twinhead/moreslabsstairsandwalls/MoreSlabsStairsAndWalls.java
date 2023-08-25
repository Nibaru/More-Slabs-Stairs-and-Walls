package games.twinhead.moreslabsstairsandwalls;

import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class MoreSlabsStairsAndWalls implements ModInitializer {

    public static final String MOD_ID = "more_slabs_stairs_and_walls";


    @Override
    public void onInitialize() {
        ModRegistry.registerBlocks();
    }
}
