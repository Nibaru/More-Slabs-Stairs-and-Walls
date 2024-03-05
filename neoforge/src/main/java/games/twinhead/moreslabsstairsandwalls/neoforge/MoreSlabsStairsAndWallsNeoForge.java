package games.twinhead.moreslabsstairsandwalls.neoforge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.registry.neoforge.ModRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MoreSlabsStairsAndWalls.MOD_ID)
public class MoreSlabsStairsAndWallsNeoForge {

    public MoreSlabsStairsAndWallsNeoForge(IEventBus modEventBus) {
        MoreSlabsStairsAndWalls.init();
        ModRegistry registry = new ModRegistry();
        modEventBus.register(registry);
        ModRegistry.ENTITIES.register(modEventBus);
        ModRegistry.ITEM_GROUPS.register(modEventBus);


    }
}