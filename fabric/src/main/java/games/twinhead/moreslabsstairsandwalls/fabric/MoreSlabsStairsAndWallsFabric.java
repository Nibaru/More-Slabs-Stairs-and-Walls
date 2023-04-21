package games.twinhead.moreslabsstairsandwalls.fabric;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.fabricmc.api.ModInitializer;

public class MoreSlabsStairsAndWallsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MoreSlabsStairsAndWalls.init();
    }
}
