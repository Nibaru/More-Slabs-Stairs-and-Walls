package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class ModTags {

    public static final TagKey<Block> GRASS_BLOCKS = TagKey.create(Registries.BLOCK, MoreSlabsStairsAndWalls.id("grass_blocks"));

    public static final TagKey<Block> WOOL_SLABS = TagKey.create(Registries.BLOCK, MoreSlabsStairsAndWalls.id("wool_slabs"));
    public static final TagKey<Block> WOOL_STAIRS = TagKey.create(Registries.BLOCK, MoreSlabsStairsAndWalls.id("wool_stairs"));
    public static final TagKey<Block> WOOL_WALLS = TagKey.create(Registries.BLOCK, MoreSlabsStairsAndWalls.id("wool_walls"));

    /** Item mirrors of {@link #WOOL_SLABS} / {@link #WOOL_STAIRS} / {@link #WOOL_WALLS} (all dye colors). */
    public static final TagKey<Item> WOOL_SLABS_ITEMS = TagKey.create(Registries.ITEM, MoreSlabsStairsAndWalls.id("wool_slabs"));
    public static final TagKey<Item> WOOL_STAIRS_ITEMS = TagKey.create(Registries.ITEM, MoreSlabsStairsAndWalls.id("wool_stairs"));
    public static final TagKey<Item> WOOL_WALLS_ITEMS = TagKey.create(Registries.ITEM, MoreSlabsStairsAndWalls.id("wool_walls"));

    private static final List<Block> VANILLA_WOOL_PARENT_BLOCKS = List.of(
            Blocks.WHITE_WOOL,
            Blocks.ORANGE_WOOL,
            Blocks.MAGENTA_WOOL,
            Blocks.LIGHT_BLUE_WOOL,
            Blocks.YELLOW_WOOL,
            Blocks.LIME_WOOL,
            Blocks.PINK_WOOL,
            Blocks.GRAY_WOOL,
            Blocks.LIGHT_GRAY_WOOL,
            Blocks.CYAN_WOOL,
            Blocks.PURPLE_WOOL,
            Blocks.BLUE_WOOL,
            Blocks.BROWN_WOOL,
            Blocks.GREEN_WOOL,
            Blocks.RED_WOOL,
            Blocks.BLACK_WOOL);

    public static boolean isVanillaWoolParent(Block parent) {
        return parent != null && VANILLA_WOOL_PARENT_BLOCKS.contains(parent);
    }

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
        return TagKey.create(Registries.ITEM, MoreSlabsStairsAndWalls.id(getLogType(blocks) + "_" + type.toString().toLowerCase() + (type == ModBlocks.BlockType.STAIRS ? "" : "s")));
    }


}
