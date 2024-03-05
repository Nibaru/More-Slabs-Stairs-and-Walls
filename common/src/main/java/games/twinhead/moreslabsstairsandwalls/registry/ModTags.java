package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {

    public static final TagKey<Block> GRASS_BLOCKS = TagKey.of(RegistryKeys.BLOCK, MoreSlabsStairsAndWalls.id("grass_blocks"));

    public static String getLogType(ModBlocks block){
        return switch (block) {
            case OAK_LOG, OAK_WOOD, STRIPPED_OAK_LOG, STRIPPED_OAK_WOOD -> "oak";
            case SPRUCE_LOG, SPRUCE_WOOD, STRIPPED_SPRUCE_LOG, STRIPPED_SPRUCE_WOOD -> "spruce";
            case BIRCH_LOG, BIRCH_WOOD, STRIPPED_BIRCH_LOG, STRIPPED_BIRCH_WOOD -> "birch";
            case JUNGLE_LOG, JUNGLE_WOOD, STRIPPED_JUNGLE_LOG, STRIPPED_JUNGLE_WOOD -> "jungle";
            case ACACIA_LOG, ACACIA_WOOD, STRIPPED_ACACIA_LOG, STRIPPED_ACACIA_WOOD -> "acacia";
            case DARK_OAK_LOG, DARK_OAK_WOOD, STRIPPED_DARK_OAK_LOG, STRIPPED_DARK_OAK_WOOD -> "dark_oak";
            case CRIMSON_STEM, CRIMSON_HYPHAE, STRIPPED_CRIMSON_STEM, STRIPPED_CRIMSON_HYPHAE -> "crimson";
            case WARPED_STEM, WARPED_HYPHAE, STRIPPED_WARPED_STEM, STRIPPED_WARPED_HYPHAE -> "warped";
            case MANGROVE_LOG, MANGROVE_WOOD, STRIPPED_MANGROVE_LOG, STRIPPED_MANGROVE_WOOD -> "mangrove";
            case CHERRY_LOG, CHERRY_WOOD, STRIPPED_CHERRY_LOG, STRIPPED_CHERRY_WOOD -> "cherry";
            case BAMBOO_BLOCK, STRIPPED_BAMBOO_BLOCK -> "bamboo";
            default -> throw new IllegalStateException("Unexpected value: " + block);
        };
    }

    public static TagKey<Item> getLogTagKey(ModBlocks blocks, ModBlocks.BlockType type) {
        return TagKey.of(RegistryKeys.ITEM, MoreSlabsStairsAndWalls.id(getLogType(blocks) + "_" + type.toString().toLowerCase() + (type == ModBlocks.BlockType.STAIRS ? "" : "s")));
    }


}
