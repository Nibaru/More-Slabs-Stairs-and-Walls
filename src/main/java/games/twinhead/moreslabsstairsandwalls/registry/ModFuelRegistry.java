package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModFuelRegistry {

    public static void register(){
        for (ModBlocks block: ModBlocks.values()) {
            int burntime = switch (block){
                case WHITE_WOOL, YELLOW_WOOL, BLACK_WOOL, RED_WOOL, PURPLE_WOOL, PINK_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIME_WOOL, LIGHT_GRAY_WOOL, LIGHT_BLUE_WOOL, GREEN_WOOL, GRAY_WOOL, CYAN_WOOL, BROWN_WOOL, BLUE_WOOL -> 100;
                case OAK_WOOD, SPRUCE_LOG, SPRUCE_WOOD, STRIPPED_SPRUCE_WOOD, BIRCH_WOOD, JUNGLE_WOOD, DARK_OAK_WOOD, ACACIA_WOOD, CRIMSON_HYPHAE, WARPED_HYPHAE, STRIPPED_OAK_WOOD, STRIPPED_BIRCH_WOOD, STRIPPED_JUNGLE_WOOD, STRIPPED_DARK_OAK_WOOD, STRIPPED_ACACIA_WOOD, STRIPPED_CRIMSON_HYPHAE, STRIPPED_WARPED_HYPHAE, CRIMSON_NYLIUM, WARPED_NYLIUM, BOOKSHELF, OAK_LOG, DARK_OAK_LOG, ACACIA_LOG, BIRCH_LOG, JUNGLE_LOG, CRIMSON_STEM, WARPED_STEM -> 300;
                case DRIED_KELP_BLOCK -> 4000;
                case COAL_BLOCK -> 16000;
                default -> 0;
            };

            if(block.hasSlab()){
                FuelRegistry.INSTANCE.add(block.getSlabBlock(), burntime / 2);
            }

            if(block.hasStairs()){
                FuelRegistry.INSTANCE.add(block.getStairsBlock(), burntime);
            }

            if(block.hasWall()){
                FuelRegistry.INSTANCE.add(block.getWallBlock(), burntime);
            }
        }
    }
}
