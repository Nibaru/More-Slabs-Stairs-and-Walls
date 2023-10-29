package games.twinhead.moreslabsstairsandwalls;

import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class MoreSlabsStairsAndWalls implements ModInitializer {

    public static final String MOD_ID = "more_slabs_stairs_and_walls";

    /**
     * Allows grass-blocks under grass-slabs and mycelium-blocks under mycelium-slabs.
     * Disabled by default
     */
    public static final GameRules.Key<GameRules.BooleanRule> SPREAD_UNDER_SLABS =
            GameRuleRegistry.register("grassBelowGrassSlabs", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));

    @Override
    public void onInitialize() {
        ModRegistry.registerBlocks();
    }
}
