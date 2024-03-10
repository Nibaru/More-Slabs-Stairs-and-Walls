package games.twinhead.moreslabsstairsandwalls.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

@SuppressWarnings("unchecked")
public enum ModBlocks {

    GRASS_BLOCK(Blocks.GRASS_BLOCK, ModelType.GRASS , BlockTags.SHOVEL_MINEABLE, BlockTags.DIRT),
    PODZOL(Blocks.PODZOL, ModelType.GRASS , BlockTags.SHOVEL_MINEABLE, BlockTags.MUSHROOM_GROW_BLOCK, BlockTags.DIRT),
    MYCELIUM(Blocks.MYCELIUM, ModelType.GRASS , BlockTags.SHOVEL_MINEABLE, BlockTags.DIRT),
    DIRT(Blocks.DIRT, ModelType.CUBE_ALL , BlockTags.SHOVEL_MINEABLE, BlockTags.DIRT),
    DIRT_PATH(Blocks.DIRT_PATH, ModelType.PATH , BlockTags.SHOVEL_MINEABLE),
    COARSE_DIRT(Blocks.COARSE_DIRT, BlockTags.SHOVEL_MINEABLE, BlockTags.DIRT),
    ROOTED_DIRT(Blocks.ROOTED_DIRT, BlockTags.SHOVEL_MINEABLE, BlockTags.DIRT),
    STRIPPED_OAK_LOG(Blocks.STRIPPED_OAK_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_OAK_WOOD(Blocks.STRIPPED_OAK_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    OAK_LOG(Blocks.OAK_LOG, ModelType.LOG, ModBlocks.STRIPPED_OAK_LOG ,BlockTags.AXE_MINEABLE),
    OAK_WOOD(Blocks.OAK_WOOD, ModelType.LOG_ALL, STRIPPED_OAK_WOOD , BlockTags.AXE_MINEABLE),
    OAK_LEAVES(Blocks.OAK_LEAVES, ModelType.LEAVES    , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    OAK_PLANKS(Blocks.OAK_PLANKS, true, BlockTags.AXE_MINEABLE),
    STRIPPED_SPRUCE_LOG(Blocks.STRIPPED_SPRUCE_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_SPRUCE_WOOD(Blocks.STRIPPED_SPRUCE_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    SPRUCE_LOG(Blocks.SPRUCE_LOG, ModelType.LOG, ModBlocks.STRIPPED_SPRUCE_LOG ,BlockTags.AXE_MINEABLE),
    SPRUCE_WOOD(Blocks.SPRUCE_WOOD, ModelType.LOG_ALL, STRIPPED_SPRUCE_WOOD , BlockTags.AXE_MINEABLE),
    SPRUCE_LEAVES(Blocks.SPRUCE_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    SPRUCE_PLANKS(Blocks.SPRUCE_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_BIRCH_LOG(Blocks.STRIPPED_BIRCH_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_BIRCH_WOOD(Blocks.STRIPPED_BIRCH_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    BIRCH_LOG(Blocks.BIRCH_LOG, ModelType.LOG, ModBlocks.STRIPPED_BIRCH_LOG ,BlockTags.AXE_MINEABLE),
    BIRCH_WOOD(Blocks.BIRCH_WOOD, ModelType.LOG_ALL,STRIPPED_BIRCH_WOOD , BlockTags.AXE_MINEABLE),
    BIRCH_LEAVES(Blocks.BIRCH_LEAVES, ModelType.LEAVES    , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    BIRCH_PLANKS(Blocks.BIRCH_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_JUNGLE_LOG(Blocks.STRIPPED_JUNGLE_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_JUNGLE_WOOD(Blocks.STRIPPED_JUNGLE_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    JUNGLE_LOG(Blocks.JUNGLE_LOG, ModelType.LOG, ModBlocks.STRIPPED_JUNGLE_LOG ,BlockTags.AXE_MINEABLE),
    JUNGLE_WOOD(Blocks.JUNGLE_WOOD, ModelType.LOG_ALL, STRIPPED_JUNGLE_WOOD , BlockTags.AXE_MINEABLE),
    JUNGLE_LEAVES(Blocks.JUNGLE_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    JUNGLE_PLANKS(Blocks.JUNGLE_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_ACACIA_LOG(Blocks.STRIPPED_ACACIA_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_ACACIA_WOOD(Blocks.STRIPPED_ACACIA_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    ACACIA_LOG(Blocks.ACACIA_LOG, ModelType.LOG, ModBlocks.STRIPPED_ACACIA_LOG ,BlockTags.AXE_MINEABLE),
    ACACIA_WOOD(Blocks.ACACIA_WOOD, ModelType.LOG_ALL,STRIPPED_ACACIA_WOOD , BlockTags.AXE_MINEABLE),
    ACACIA_LEAVES(Blocks.ACACIA_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    ACACIA_PLANKS(Blocks.ACACIA_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_DARK_OAK_LOG(Blocks.STRIPPED_DARK_OAK_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_DARK_OAK_WOOD(Blocks.STRIPPED_DARK_OAK_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    DARK_OAK_LOG(Blocks.DARK_OAK_LOG, ModelType.LOG, ModBlocks.STRIPPED_DARK_OAK_LOG ,BlockTags.AXE_MINEABLE),
    DARK_OAK_WOOD(Blocks.DARK_OAK_WOOD, ModelType.LOG_ALL, STRIPPED_DARK_OAK_WOOD , BlockTags.AXE_MINEABLE),
    DARK_OAK_LEAVES(Blocks.DARK_OAK_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    DARK_OAK_PLANKS(Blocks.DARK_OAK_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_MANGROVE_LOG(Blocks.STRIPPED_MANGROVE_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_MANGROVE_WOOD(Blocks.STRIPPED_MANGROVE_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    MANGROVE_LOG(Blocks.MANGROVE_LOG, ModelType.LOG, ModBlocks.STRIPPED_MANGROVE_LOG ,BlockTags.AXE_MINEABLE),
    MANGROVE_WOOD(Blocks.MANGROVE_WOOD, ModelType.LOG_ALL, STRIPPED_MANGROVE_WOOD , BlockTags.AXE_MINEABLE),
    MANGROVE_LEAVES(Blocks.MANGROVE_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    MANGROVE_PLANKS(Blocks.MANGROVE_PLANKS, true, BlockTags.AXE_MINEABLE),

    MANGROVE_ROOTS(Blocks.MANGROVE_ROOTS, ModelType.ROOTS, BlockTags.AXE_MINEABLE),
    MUDDY_MANGROVE_ROOTS(Blocks.MUDDY_MANGROVE_ROOTS, ModelType.CUBE_BOTTOM_TOP, BlockTags.SHOVEL_MINEABLE),

    STRIPPED_CHERRY_LOG(Blocks.STRIPPED_CHERRY_LOG, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_CHERRY_WOOD(Blocks.STRIPPED_CHERRY_WOOD, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    CHERRY_LOG(Blocks.CHERRY_LOG, ModelType.LOG, ModBlocks.STRIPPED_CHERRY_LOG ,BlockTags.AXE_MINEABLE),
    CHERRY_WOOD(Blocks.CHERRY_WOOD, ModelType.LOG_ALL, STRIPPED_CHERRY_WOOD , BlockTags.AXE_MINEABLE),
    CHERRY_LEAVES(Blocks.CHERRY_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    CHERRY_PLANKS(Blocks.CHERRY_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_BAMBOO_BLOCK(Blocks.STRIPPED_BAMBOO_BLOCK, ModelType.LOG, BlockTags.AXE_MINEABLE),
    BAMBOO_BLOCK(Blocks.BAMBOO_BLOCK, ModelType.LOG, ModBlocks.STRIPPED_BAMBOO_BLOCK, BlockTags.AXE_MINEABLE),
    BAMBOO_PLANKS(Blocks.BAMBOO_PLANKS, true, BlockTags.AXE_MINEABLE),
    BAMBOO_MOSAIC(Blocks.BAMBOO_MOSAIC, true, BlockTags.AXE_MINEABLE),

    AZALEA_LEAVES(Blocks.AZALEA_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    FLOWERING_AZALEA_LEAVES(Blocks.FLOWERING_AZALEA_LEAVES, ModelType.LEAVES  , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),


    STRIPPED_WARPED_STEM(Blocks.STRIPPED_WARPED_STEM, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_WARPED_HYPHAE(Blocks.STRIPPED_WARPED_HYPHAE, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    WARPED_STEM(Blocks.WARPED_STEM, ModelType.LOG, ModBlocks.STRIPPED_WARPED_STEM , BlockTags.AXE_MINEABLE),
    WARPED_HYPHAE(Blocks.WARPED_HYPHAE, ModelType.LOG_ALL, ModBlocks.STRIPPED_WARPED_HYPHAE , BlockTags.AXE_MINEABLE),
    WARPED_WART(Blocks.WARPED_WART_BLOCK, ModelType.LEAVES    , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    WARPED_PLANKS(Blocks.WARPED_PLANKS, true, BlockTags.AXE_MINEABLE),

    STRIPPED_CRIMSON_STEM(Blocks.STRIPPED_CRIMSON_STEM, ModelType.LOG, BlockTags.AXE_MINEABLE),
    STRIPPED_CRIMSON_HYPHAE(Blocks.STRIPPED_CRIMSON_HYPHAE, ModelType.LOG_ALL, BlockTags.AXE_MINEABLE),
    CRIMSON_STEM(Blocks.CRIMSON_STEM, ModelType.LOG, ModBlocks.STRIPPED_CRIMSON_STEM , BlockTags.AXE_MINEABLE),
    CRIMSON_HYPHAE(Blocks.CRIMSON_HYPHAE, ModelType.LOG_ALL, ModBlocks.STRIPPED_CRIMSON_HYPHAE , BlockTags.AXE_MINEABLE),
    CRIMSON_WART(Blocks.NETHER_WART_BLOCK, ModelType.LEAVES   , BlockTags.LEAVES, BlockTags.HOE_MINEABLE),
    CRIMSON_PLANKS(Blocks.CRIMSON_PLANKS, true, BlockTags.AXE_MINEABLE),

    GLASS(Blocks.GLASS, ModelType.GLASS ),

    WHITE_STAINED_GLASS(Blocks.WHITE_STAINED_GLASS, ModelType.GLASS ),
    YELLOW_STAINED_GLASS(Blocks.YELLOW_STAINED_GLASS, ModelType.GLASS   ),
    BLACK_STAINED_GLASS(Blocks.BLACK_STAINED_GLASS, ModelType.GLASS ),
    RED_STAINED_GLASS(Blocks.RED_STAINED_GLASS, ModelType.GLASS ),
    PURPLE_STAINED_GLASS(Blocks.PURPLE_STAINED_GLASS, ModelType.GLASS   ),
    PINK_STAINED_GLASS(Blocks.PINK_STAINED_GLASS, ModelType.GLASS   ),
    ORANGE_STAINED_GLASS(Blocks.ORANGE_STAINED_GLASS, ModelType.GLASS   ),
    MAGENTA_STAINED_GLASS(Blocks.MAGENTA_STAINED_GLASS, ModelType.GLASS ),
    LIME_STAINED_GLASS(Blocks.LIME_STAINED_GLASS, ModelType.GLASS   ),
    LIGHT_GRAY_STAINED_GLASS(Blocks.LIGHT_GRAY_STAINED_GLASS, ModelType.GLASS   ),
    LIGHT_BLUE_STAINED_GLASS(Blocks.LIGHT_BLUE_STAINED_GLASS, ModelType.GLASS   ),
    GREEN_STAINED_GLASS(Blocks.GREEN_STAINED_GLASS, ModelType.GLASS ),
    GRAY_STAINED_GLASS(Blocks.GRAY_STAINED_GLASS, ModelType.GLASS   ),
    CYAN_STAINED_GLASS(Blocks.CYAN_STAINED_GLASS, ModelType.GLASS   ),
    BROWN_STAINED_GLASS(Blocks.BROWN_STAINED_GLASS, ModelType.GLASS ),
    BLUE_STAINED_GLASS(Blocks.BLUE_STAINED_GLASS, ModelType.GLASS   ),

    WHITE_WOOL(Blocks.WHITE_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    YELLOW_WOOL(Blocks.YELLOW_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    BLACK_WOOL(Blocks.BLACK_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    RED_WOOL(Blocks.RED_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    PURPLE_WOOL(Blocks.PURPLE_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    PINK_WOOL(Blocks.PINK_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    ORANGE_WOOL(Blocks.ORANGE_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    MAGENTA_WOOL(Blocks.MAGENTA_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    LIME_WOOL(Blocks.LIME_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    LIGHT_GRAY_WOOL(Blocks.LIGHT_GRAY_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    LIGHT_BLUE_WOOL(Blocks.LIGHT_BLUE_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    GREEN_WOOL(Blocks.GREEN_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    GRAY_WOOL(Blocks.GRAY_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    CYAN_WOOL(Blocks.CYAN_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    BROWN_WOOL(Blocks.BROWN_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),
    BLUE_WOOL(Blocks.BLUE_WOOL, ModelType.CUBE_ALL, BlockTags.WOOL),

    WARPED_NYLIUM(Blocks.WARPED_NYLIUM, ModelType.GRASS),
    CRIMSON_NYLIUM(Blocks.CRIMSON_NYLIUM, ModelType.GRASS),

    SAND(Blocks.SAND, ModelType.CUBE_ALL , BlockTags.SHOVEL_MINEABLE),
    GRAVEL(Blocks.GRAVEL, ModelType.CUBE_ALL , BlockTags.SHOVEL_MINEABLE),
    RED_SAND(Blocks.RED_SAND, ModelType.CUBE_ALL , BlockTags.SHOVEL_MINEABLE),

    BLACK_CONCRETE(Blocks.BLACK_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    BLACK_CONCRETE_POWDER(Blocks.BLACK_CONCRETE_POWDER, ModelType.CUBE_ALL, BLACK_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    BLUE_CONCRETE(Blocks.BLUE_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    BLUE_CONCRETE_POWDER(Blocks.BLUE_CONCRETE_POWDER, ModelType.CUBE_ALL, BLUE_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    BROWN_CONCRETE(Blocks.BROWN_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    BROWN_CONCRETE_POWDER(Blocks.BROWN_CONCRETE_POWDER, ModelType.CUBE_ALL, BROWN_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    CYAN_CONCRETE(Blocks.CYAN_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    CYAN_CONCRETE_POWDER(Blocks.CYAN_CONCRETE_POWDER, ModelType.CUBE_ALL, CYAN_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    GRAY_CONCRETE(Blocks.GRAY_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    GRAY_CONCRETE_POWDER(Blocks.GRAY_CONCRETE_POWDER, ModelType.CUBE_ALL, GRAY_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    GREEN_CONCRETE(Blocks.GREEN_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    GREEN_CONCRETE_POWDER(Blocks.GREEN_CONCRETE_POWDER, ModelType.CUBE_ALL, GREEN_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    LIGHT_BLUE_CONCRETE(Blocks.LIGHT_BLUE_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    LIGHT_BLUE_CONCRETE_POWDER(Blocks.LIGHT_BLUE_CONCRETE_POWDER, ModelType.CUBE_ALL, LIGHT_BLUE_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    LIGHT_GRAY_CONCRETE(Blocks.LIGHT_GRAY_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    LIGHT_GRAY_CONCRETE_POWDER(Blocks.LIGHT_GRAY_CONCRETE_POWDER, ModelType.CUBE_ALL, LIGHT_GRAY_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    LIME_CONCRETE(Blocks.LIME_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    LIME_CONCRETE_POWDER(Blocks.LIME_CONCRETE_POWDER, ModelType.CUBE_ALL, LIME_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    MAGENTA_CONCRETE(Blocks.MAGENTA_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    MAGENTA_CONCRETE_POWDER(Blocks.MAGENTA_CONCRETE_POWDER, ModelType.CUBE_ALL, MAGENTA_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    ORANGE_CONCRETE(Blocks.ORANGE_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    ORANGE_CONCRETE_POWDER(Blocks.ORANGE_CONCRETE_POWDER, ModelType.CUBE_ALL, ORANGE_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    PINK_CONCRETE(Blocks.PINK_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    PINK_CONCRETE_POWDER(Blocks.PINK_CONCRETE_POWDER, ModelType.CUBE_ALL, PINK_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    PURPLE_CONCRETE(Blocks.PURPLE_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    PURPLE_CONCRETE_POWDER(Blocks.PURPLE_CONCRETE_POWDER, ModelType.CUBE_ALL, PURPLE_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    RED_CONCRETE(Blocks.RED_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    RED_CONCRETE_POWDER(Blocks.RED_CONCRETE_POWDER, ModelType.CUBE_ALL, RED_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    WHITE_CONCRETE(Blocks.WHITE_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    WHITE_CONCRETE_POWDER(Blocks.WHITE_CONCRETE_POWDER, ModelType.CUBE_ALL, WHITE_CONCRETE , BlockTags.SHOVEL_MINEABLE),
    YELLOW_CONCRETE(Blocks.YELLOW_CONCRETE, BlockTags.PICKAXE_MINEABLE),
    YELLOW_CONCRETE_POWDER(Blocks.YELLOW_CONCRETE_POWDER, ModelType.CUBE_ALL, YELLOW_CONCRETE , BlockTags.SHOVEL_MINEABLE),

    WHITE_TERRACOTTA(Blocks.WHITE_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    YELLOW_TERRACOTTA(Blocks.YELLOW_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    BLACK_TERRACOTTA(Blocks.BLACK_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    RED_TERRACOTTA(Blocks.RED_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    PURPLE_TERRACOTTA(Blocks.PURPLE_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    PINK_TERRACOTTA(Blocks.PINK_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    ORANGE_TERRACOTTA(Blocks.ORANGE_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    MAGENTA_TERRACOTTA(Blocks.MAGENTA_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    LIME_TERRACOTTA(Blocks.LIME_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    LIGHT_GRAY_TERRACOTTA(Blocks.LIGHT_GRAY_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    LIGHT_BLUE_TERRACOTTA(Blocks.LIGHT_BLUE_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    GREEN_TERRACOTTA(Blocks.GREEN_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    GRAY_TERRACOTTA(Blocks.GRAY_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    CYAN_TERRACOTTA(Blocks.CYAN_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    BROWN_TERRACOTTA(Blocks.BROWN_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    BLUE_TERRACOTTA(Blocks.BLUE_TERRACOTTA, BlockTags.PICKAXE_MINEABLE),
    TERRACOTTA(Blocks.TERRACOTTA, BlockTags.PICKAXE_MINEABLE),

    WHITE_GLAZED_TERRACOTTA(Blocks.WHITE_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    YELLOW_GLAZED_TERRACOTTA(Blocks.YELLOW_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    BLACK_GLAZED_TERRACOTTA(Blocks.BLACK_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    RED_GLAZED_TERRACOTTA(Blocks.RED_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    PURPLE_GLAZED_TERRACOTTA(Blocks.PURPLE_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    PINK_GLAZED_TERRACOTTA(Blocks.PINK_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    ORANGE_GLAZED_TERRACOTTA(Blocks.ORANGE_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    MAGENTA_GLAZED_TERRACOTTA(Blocks.MAGENTA_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    LIME_GLAZED_TERRACOTTA(Blocks.LIME_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    LIGHT_GRAY_GLAZED_TERRACOTTA(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    LIGHT_BLUE_GLAZED_TERRACOTTA(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    GREEN_GLAZED_TERRACOTTA(Blocks.GREEN_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    GRAY_GLAZED_TERRACOTTA(Blocks.GRAY_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    CYAN_GLAZED_TERRACOTTA(Blocks.CYAN_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    BROWN_GLAZED_TERRACOTTA(Blocks.BROWN_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),
    BLUE_GLAZED_TERRACOTTA(Blocks.BLUE_GLAZED_TERRACOTTA, ModelType.GLAZED_TERRACOTTA , BlockTags.PICKAXE_MINEABLE),

    QUARTZ_BRICKS(Blocks.QUARTZ_BRICKS, BlockTags.PICKAXE_MINEABLE),
    HONEYCOMB_BLOCK(Blocks.HONEYCOMB_BLOCK),
    SNOW_BLOCK(Blocks.SNOW_BLOCK, "snow", BlockTags.SHOVEL_MINEABLE),
    MUSHROOM_STEM(Blocks.MUSHROOM_STEM, BlockTags.AXE_MINEABLE),
    BROWN_MUSHROOM_BLOCK(Blocks.BROWN_MUSHROOM_BLOCK, BlockTags.AXE_MINEABLE),
    RED_MUSHROOM_BLOCK(Blocks.RED_MUSHROOM_BLOCK, BlockTags.AXE_MINEABLE),

    PURPUR(Blocks.PURPUR_BLOCK, true, BlockTags.PICKAXE_MINEABLE),

    MOSS_BLOCK(Blocks.MOSS_BLOCK, BlockTags.HOE_MINEABLE),
    CALCITE(Blocks.CALCITE, BlockTags.PICKAXE_MINEABLE),
    GLOWSTONE(Blocks.GLOWSTONE),
    CRACKED_STONE_BRICKS(Blocks.CRACKED_STONE_BRICKS, BlockTags.PICKAXE_MINEABLE),
    CHISELED_STONE_BRICKS(Blocks.CHISELED_STONE_BRICKS, BlockTags.PICKAXE_MINEABLE),


    TUFF(Blocks.TUFF, BlockTags.PICKAXE_MINEABLE),
    DRIPSTONE_BLOCK(Blocks.DRIPSTONE_BLOCK, BlockTags.PICKAXE_MINEABLE),
    SEA_LANTERN(Blocks.SEA_LANTERN),
    SHROOMLIGHT(Blocks.SHROOMLIGHT, BlockTags.HOE_MINEABLE),
    END_STONE(Blocks.END_STONE, BlockTags.PICKAXE_MINEABLE),

    DEEPSLATE(Blocks.DEEPSLATE,"deepslate", "deepslate_top", "deepslate_top", BlockTags.PICKAXE_MINEABLE),

    CRACKED_NETHER_BRICKS(Blocks.CRACKED_NETHER_BRICKS, BlockTags.PICKAXE_MINEABLE),
    CRACKED_DEEPSLATE_BRICKS(Blocks.CRACKED_DEEPSLATE_BRICKS, BlockTags.PICKAXE_MINEABLE),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, BlockTags.PICKAXE_MINEABLE),
    CRACKED_DEEPSLATE_TILES(Blocks.CRACKED_DEEPSLATE_TILES, BlockTags.PICKAXE_MINEABLE),

    CHISELED_POLISHED_BLACKSTONE(Blocks.CHISELED_POLISHED_BLACKSTONE, BlockTags.PICKAXE_MINEABLE),
    CHISELED_DEEPSLATE(Blocks.CHISELED_DEEPSLATE, BlockTags.PICKAXE_MINEABLE),

    CRYING_OBSIDIAN(Blocks.CRYING_OBSIDIAN, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE, BlockTags.DRAGON_IMMUNE, BlockTags.NEEDS_DIAMOND_TOOL),

    NETHERRACK(Blocks.NETHERRACK, ModelType.CUBE_ALL , BlockTags.PICKAXE_MINEABLE, BlockTags.INFINIBURN_OVERWORLD, BlockTags.INFINIBURN_END, BlockTags.INFINIBURN_NETHER),


    BASALT(Blocks.BASALT, ModelType.CUBE_BOTTOM_TOP, BlockTags.PICKAXE_MINEABLE),
    POLISHED_BASALT(Blocks.POLISHED_BASALT, ModelType.CUBE_BOTTOM_TOP, BlockTags.PICKAXE_MINEABLE),
    SMOOTH_BASALT(Blocks.SMOOTH_BASALT,   BlockTags.PICKAXE_MINEABLE),

    OXIDIZED_COPPER(Blocks.OXIDIZED_COPPER, ModelType.CUBE_ALL, Oxidizable.OxidationLevel.OXIDIZED, null,   BlockTags.PICKAXE_MINEABLE),
    WEATHERED_COPPER(Blocks.WEATHERED_COPPER, ModelType.CUBE_ALL, Oxidizable.OxidationLevel.WEATHERED, OXIDIZED_COPPER , BlockTags.PICKAXE_MINEABLE),
    EXPOSED_COPPER(Blocks.EXPOSED_COPPER, ModelType.CUBE_ALL,Oxidizable.OxidationLevel.EXPOSED, WEATHERED_COPPER , BlockTags.PICKAXE_MINEABLE),
    COPPER_BLOCK(Blocks.COPPER_BLOCK,  ModelType.CUBE_ALL ,Oxidizable.OxidationLevel.UNAFFECTED, EXPOSED_COPPER,   BlockTags.PICKAXE_MINEABLE),

    WAXED_COPPER_BLOCK(Blocks.WAXED_COPPER_BLOCK,"copper_block", COPPER_BLOCK , BlockTags.PICKAXE_MINEABLE),
    WAXED_EXPOSED_COPPER(Blocks.WAXED_EXPOSED_COPPER,"exposed_copper", EXPOSED_COPPER , BlockTags.PICKAXE_MINEABLE),
    WAXED_WEATHERED_COPPER(Blocks.WAXED_WEATHERED_COPPER, "weathered_copper", WEATHERED_COPPER , BlockTags.PICKAXE_MINEABLE),
    WAXED_OXIDIZED_COPPER(Blocks.WAXED_OXIDIZED_COPPER, "oxidized_copper", OXIDIZED_COPPER , BlockTags.PICKAXE_MINEABLE),

    OXIDIZED_CUT_COPPER(Blocks.OXIDIZED_CUT_COPPER, ModelType.CUBE_ALL, Oxidizable.OxidationLevel.OXIDIZED, null, false, false, true, BlockTags.PICKAXE_MINEABLE),
    WEATHERED_CUT_COPPER(Blocks.WEATHERED_CUT_COPPER, ModelType.CUBE_ALL, Oxidizable.OxidationLevel.WEATHERED, OXIDIZED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),
    EXPOSED_CUT_COPPER(Blocks.EXPOSED_CUT_COPPER, ModelType.CUBE_ALL,Oxidizable.OxidationLevel.EXPOSED, WEATHERED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),
    CUT_COPPER(Blocks.CUT_COPPER,  ModelType.CUBE_ALL ,Oxidizable.OxidationLevel.UNAFFECTED, EXPOSED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),

    WAXED_CUT_COPPER(Blocks.WAXED_CUT_COPPER,"cut_copper", CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),
    WAXED_EXPOSED_CUT_COPPER(Blocks.WAXED_EXPOSED_CUT_COPPER,"exposed_cut_copper", EXPOSED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),
    WAXED_WEATHERED_CUT_COPPER(Blocks.WAXED_WEATHERED_CUT_COPPER, "weathered_cut_copper", WEATHERED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),
    WAXED_OXIDIZED_CUT_COPPER(Blocks.WAXED_OXIDIZED_CUT_COPPER, "oxidized_cut_copper", OXIDIZED_CUT_COPPER, false, false, true, BlockTags.PICKAXE_MINEABLE),

    SMOOTH_QUARTZ(Blocks.SMOOTH_QUARTZ, "quartz_block_bottom", true, BlockTags.PICKAXE_MINEABLE),
    QUARTZ_BLOCK(Blocks.QUARTZ_BLOCK, "quartz_block_side",true, BlockTags.PICKAXE_MINEABLE),
    CHISELED_QUARTZ_BLOCK(Blocks.CHISELED_QUARTZ_BLOCK, "chiseled_quartz_block", "chiseled_quartz_block_top", "chiseled_quartz_block_top", BlockTags.PICKAXE_MINEABLE),
    QUARTZ_PILLAR(Blocks.QUARTZ_PILLAR,"quartz_pillar", "quartz_pillar_top", "quartz_pillar_top", BlockTags.PICKAXE_MINEABLE),

    POLISHED_ANDESITE(Blocks.POLISHED_ANDESITE, true, BlockTags.PICKAXE_MINEABLE),
    POLISHED_GRANITE(Blocks.POLISHED_GRANITE, true, BlockTags.PICKAXE_MINEABLE),
    POLISHED_DIORITE(Blocks.POLISHED_DIORITE, true, BlockTags.PICKAXE_MINEABLE),

    SMOOTH_SANDSTONE(Blocks.SMOOTH_SANDSTONE, "sandstone_top",true, BlockTags.PICKAXE_MINEABLE),
    SMOOTH_RED_SANDSTONE(Blocks.SMOOTH_RED_SANDSTONE, "red_sandstone_top",true, BlockTags.PICKAXE_MINEABLE),
    DARK_PRISMARINE(Blocks.DARK_PRISMARINE, true, BlockTags.PICKAXE_MINEABLE),
    PRISMARINE_BRICKS(Blocks.PRISMARINE_BRICKS, true, BlockTags.PICKAXE_MINEABLE),
    STONE(Blocks.STONE, true, BlockTags.PICKAXE_MINEABLE),

    SMOOTH_STONE(Blocks.SMOOTH_STONE, ModelType.CUBE_ALL, false, true, true, BlockTags.PICKAXE_MINEABLE),


    OBSIDIAN(Blocks.OBSIDIAN, BlockTags.PICKAXE_MINEABLE, BlockTags.DRAGON_IMMUNE, BlockTags.NEEDS_DIAMOND_TOOL),

    AMETHYST_BLOCK(Blocks.AMETHYST_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.CRYSTAL_SOUND_BLOCKS),

    RAW_COPPER_BLOCK(Blocks.RAW_COPPER_BLOCK, BlockTags.PICKAXE_MINEABLE),
    RAW_GOLD_BLOCK(Blocks.RAW_GOLD_BLOCK, BlockTags.PICKAXE_MINEABLE),
    RAW_IRON_BLOCK(Blocks.RAW_IRON_BLOCK, BlockTags.PICKAXE_MINEABLE),

    MAGMA_BLOCK(Blocks.MAGMA_BLOCK, "magma" , BlockTags.PICKAXE_MINEABLE),

    SOUL_SAND(Blocks.SOUL_SAND, ModelType.CUBE_ALL , BlockTags.SHOVEL_MINEABLE, BlockTags.SOUL_SPEED_BLOCKS, BlockTags.SOUL_FIRE_BASE_BLOCKS),
    SOUL_SOIL(Blocks.SOUL_SOIL, BlockTags.SHOVEL_MINEABLE, BlockTags.SOUL_SPEED_BLOCKS, BlockTags.SOUL_FIRE_BASE_BLOCKS),

    CLAY(Blocks.CLAY, BlockTags.SHOVEL_MINEABLE),

    CHISELED_SANDSTONE(Blocks.CHISELED_SANDSTONE, "chiseled_sandstone", "sandstone_top", "sandstone_top", BlockTags.PICKAXE_MINEABLE),
    CHISELED_RED_SANDSTONE(Blocks.CHISELED_RED_SANDSTONE, "chiseled_red_sandstone", "red_sandstone_top", "red_sandstone_top", BlockTags.PICKAXE_MINEABLE),

    CUT_SANDSTONE(Blocks.CUT_SANDSTONE, "cut_sandstone", "sandstone_top", "sandstone_top", false, true, true, BlockTags.PICKAXE_MINEABLE),
    CUT_RED_SANDSTONE(Blocks.CUT_RED_SANDSTONE, "cut_red_sandstone", "red_sandstone_top", "red_sandstone_top", false, true, true, BlockTags.PICKAXE_MINEABLE),

    CHISELED_NETHER_BRICKS(Blocks.CHISELED_NETHER_BRICKS, BlockTags.PICKAXE_MINEABLE),

    COAL_BLOCK(Blocks.COAL_BLOCK, BlockTags.PICKAXE_MINEABLE),
    IRON_BLOCK(Blocks.IRON_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.BEACON_BASE_BLOCKS),
    GOLD_BLOCK(Blocks.GOLD_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.BEACON_BASE_BLOCKS),
    DIAMOND_BLOCK(Blocks.DIAMOND_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.BEACON_BASE_BLOCKS),
    NETHERITE_BLOCK(Blocks.NETHERITE_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.BEACON_BASE_BLOCKS),
    LAPIS_BLOCK(Blocks.LAPIS_BLOCK, BlockTags.PICKAXE_MINEABLE),
    EMERALD_BLOCK(Blocks.EMERALD_BLOCK, BlockTags.PICKAXE_MINEABLE, BlockTags.BEACON_BASE_BLOCKS),


    GILDED_BLACKSTONE(Blocks.GILDED_BLACKSTONE, BlockTags.PICKAXE_MINEABLE),



    PUMPKIN(Blocks.PUMPKIN, ModelType.CUBE_BOTTOM_TOP, BlockTags.AXE_MINEABLE),
    MELON(Blocks.MELON, ModelType.CUBE_BOTTOM_TOP, BlockTags.AXE_MINEABLE),
    DRIED_KELP_BLOCK(Blocks.DRIED_KELP_BLOCK, "dried_kelp_side", "dried_kelp_top", "dried_kelp_bottom", BlockTags.HOE_MINEABLE),


    BONE_BLOCK(Blocks.BONE_BLOCK, ModelType.CUBE_BOTTOM_TOP, BlockTags.PICKAXE_MINEABLE),

    HAY_BLOCK(Blocks.HAY_BLOCK, ModelType.CUBE_BOTTOM_TOP, BlockTags.HOE_MINEABLE),

    BOOKSHELF(Blocks.BOOKSHELF, "bookshelf", "oak_planks", "oak_planks", BlockTags.AXE_MINEABLE),

    SLIME_BLOCK(Blocks.SLIME_BLOCK , ModelType.SLIME),
    HONEY_BLOCK(Blocks.HONEY_BLOCK, ModelType.HONEY),

    REDSTONE_BLOCK(Blocks.REDSTONE_BLOCK , ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),

    DEAD_TUBE_CORAL_BLOCK(Blocks.DEAD_TUBE_CORAL_BLOCK, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),
    TUBE_CORAL_BLOCK(Blocks.TUBE_CORAL_BLOCK, ModelType.CUBE_ALL, ModBlocks.DEAD_TUBE_CORAL_BLOCK,  BlockTags.PICKAXE_MINEABLE),
    DEAD_BUBBLE_CORAL_BLOCK(Blocks.DEAD_BUBBLE_CORAL_BLOCK, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),
    BUBBLE_CORAL_BLOCK(Blocks.BUBBLE_CORAL_BLOCK, ModelType.CUBE_ALL, ModBlocks.DEAD_BUBBLE_CORAL_BLOCK , BlockTags.PICKAXE_MINEABLE),
    DEAD_BRAIN_CORAL_BLOCK(Blocks.DEAD_BRAIN_CORAL_BLOCK, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),
    BRAIN_CORAL_BLOCK(Blocks.BRAIN_CORAL_BLOCK, ModelType.CUBE_ALL, ModBlocks.DEAD_BRAIN_CORAL_BLOCK , BlockTags.PICKAXE_MINEABLE),
    DEAD_FIRE_CORAL_BLOCK(Blocks.DEAD_FIRE_CORAL_BLOCK, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),
    FIRE_CORAL_BLOCK(Blocks.FIRE_CORAL_BLOCK, ModelType.CUBE_ALL, ModBlocks.DEAD_FIRE_CORAL_BLOCK , BlockTags.PICKAXE_MINEABLE),
    DEAD_HORN_CORAL_BLOCK(Blocks.DEAD_HORN_CORAL_BLOCK, ModelType.CUBE_ALL, BlockTags.PICKAXE_MINEABLE),
    HORN_CORAL_BLOCK(Blocks.HORN_CORAL_BLOCK, ModelType.CUBE_ALL, ModBlocks.DEAD_HORN_CORAL_BLOCK , BlockTags.PICKAXE_MINEABLE),

    OCHRE_FROGLIGHT(Blocks.OCHRE_FROGLIGHT, ModelType.CUBE_BOTTOM_TOP, BlockTags.HOE_MINEABLE),
    VERDANT_FROGLIGHT(Blocks.VERDANT_FROGLIGHT, ModelType.CUBE_BOTTOM_TOP, BlockTags.HOE_MINEABLE),
    PEARLESCENT_FROGLIGHT(Blocks.PEARLESCENT_FROGLIGHT, ModelType.CUBE_BOTTOM_TOP, BlockTags.HOE_MINEABLE),

    SCULK(Blocks.SCULK, BlockTags.HOE_MINEABLE),
    PACKED_MUD(Blocks.PACKED_MUD, BlockTags.SHOVEL_MINEABLE),
    MUD(Blocks.MUD, BlockTags.SHOVEL_MINEABLE),

    ICE(Blocks.ICE, ModelType.TRANSLUCENT, BlockTags.PICKAXE_MINEABLE),
    PACKED_ICE(Blocks.PACKED_ICE, BlockTags.PICKAXE_MINEABLE),
    BLUE_ICE(Blocks.BLUE_ICE, BlockTags.PICKAXE_MINEABLE),

    BEDROCK(Blocks.BEDROCK, BlockTags.DRAGON_IMMUNE, BlockTags.WITHER_IMMUNE),
    ;



    public final Block parentBlock;
    public ModelType modelType;
    public boolean hasSlab = true;
    public boolean hasStairs = true;
    public boolean hasWall = true;

    public final TagKey<Block>[] blockTags;

    public ModBlocks associatedBlock = null;

    public String textureId = "";
    public String bottomId = "";
    public String topId = "";

    public Oxidizable.OxidationLevel oxidationLevel = null;

    ModBlocks(Block parentBlock, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = ModelType.CUBE_ALL;
        this.blockTags = blockTags;
    }

    ModBlocks(Block parentBlock, String textureId, TagKey<Block>... blockTags){
        this(parentBlock, blockTags);
        this.modelType = ModelType.CUSTOM;
        this.textureId = textureId;
    }

    ModBlocks(Block parentBlock, boolean wallOnly, TagKey<Block>... blockTags){
        this(parentBlock, blockTags);
        this.modelType = ModelType.CUBE_ALL;
        this.hasSlab = false;
        this.hasStairs = false;
    }

    ModBlocks(Block parentBlock, String textureId, boolean wallOnly, TagKey<Block>... blockTags){
        this(parentBlock, textureId, blockTags);
        this.modelType = ModelType.CUSTOM;
        this.hasSlab = false;
        this.hasStairs = false;
    }

    ModBlocks(Block parentBlock, String sideId, String topId, String bottomId, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = ModelType.CUSTOM_SIDE_BOTTOM_TOP;
        this.textureId = sideId;
        this.topId = topId;
        this.bottomId = bottomId;
        this.blockTags = blockTags;
    }

    ModBlocks(Block parentBlock, String sideId, String topId, String bottomId, boolean slab, boolean stairs, boolean wall,  TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = ModelType.CUSTOM_SIDE_BOTTOM_TOP;
        this.textureId = sideId;
        this.topId = topId;
        this.bottomId = bottomId;
        this.blockTags = blockTags;
        this.hasSlab = slab;
        this.hasStairs = stairs;
        this.hasWall = wall;
    }

    ModBlocks(Block parentBlock, ModelType modelType, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = modelType;
        this.blockTags = blockTags;
    }

    ModBlocks(Block parentBlock, ModelType modelType, ModBlocks associatedBlock, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = modelType;
        this.blockTags = blockTags;
        this.associatedBlock = associatedBlock;
    }

    ModBlocks(Block parentBlock, String modelId, ModBlocks associatedBlock, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.blockTags = blockTags;
        this.associatedBlock = associatedBlock;
        this.modelType = ModelType.CUSTOM;
        this.textureId = modelId;
    }

    ModBlocks(Block parentBlock, String modelId, ModBlocks associatedBlock, boolean slab, boolean stairs, boolean wall, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.blockTags = blockTags;
        this.associatedBlock = associatedBlock;
        this.modelType = ModelType.CUSTOM;
        this.textureId = modelId;
        this.hasSlab = slab;
        this.hasStairs = stairs;
        this.hasWall = wall;
    }

    ModBlocks(Block parentBlock, ModelType modelType, Oxidizable.OxidationLevel oxidationLevel, ModBlocks associatedBlock, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = modelType;
        this.blockTags = blockTags;
        this.associatedBlock = associatedBlock;
        this.oxidationLevel = oxidationLevel;
    }

    ModBlocks(Block parentBlock, ModelType modelType, Oxidizable.OxidationLevel oxidationLevel, ModBlocks associatedBlock, boolean slab, boolean stairs, boolean wall, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = modelType;
        this.blockTags = blockTags;
        this.associatedBlock = associatedBlock;
        this.oxidationLevel = oxidationLevel;
        this.hasSlab = slab;
        this.hasStairs = stairs;
        this.hasWall = wall;
    }

    ModBlocks(Block parentBlock, ModelType modelType, boolean slab, boolean stairs, boolean wall, TagKey<Block>... blockTags){
        this.parentBlock = parentBlock;
        this.modelType = modelType;
        this.hasSlab = slab;
        this.hasStairs = stairs;
        this.hasWall = wall;
        this.blockTags = blockTags;
    }

    public Boolean hasBlock(BlockType type){
        return switch (type){
            case SLAB -> hasSlab;
            case STAIRS -> hasStairs;
            case WALL -> hasWall;
        };
    }

    public AbstractBlock.Settings getSettings(){
        Block block = this.parentBlock;
        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .sounds(block.getDefaultState().getSoundGroup())
                .luminance(state -> block.getDefaultState().getLuminance())
                .mapColor(block.getDefaultMapColor())
                .hardness(block.getHardness())
                .resistance(block.getBlastResistance())
                .slipperiness(block.getSlipperiness())
                .velocityMultiplier(block.getVelocityMultiplier())
                .pistonBehavior(block.getDefaultState().getPistonBehavior())
                .instrument(block.getDefaultState().getInstrument())
                .jumpVelocityMultiplier(block.getJumpVelocityMultiplier())
                ;

        if (block.getDefaultState().isBurnable()){
            settings = settings.burnable();
        }

        if (!block.getDefaultState().isOpaque()){
            settings = settings.nonOpaque();
        }

        if (block.getDefaultState().isToolRequired()){
            settings = settings.requiresTool();
        }

        if (block.getDefaultState().hasRandomTicks()){
            settings = settings.ticksRandomly();
        }

        return settings;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public Identifier getId(BlockType type){
        return new Identifier(MoreSlabsStairsAndWalls.MOD_ID, this + "_" + type.toString().toLowerCase());
    }


    public Block getBlock(BlockType type){
        return getBlock(this, type);
    }

    @ExpectPlatform
    public static Block getBlock(ModBlocks block, BlockType type){
        throw new AssertionError();
    }

    public enum BlockType{
        SLAB,
        STAIRS,
        WALL
    }

    public enum ModelType {
        CUBE_ALL,
        CUBE_COLUMN,
        CUBE_BOTTOM_TOP,
        LOG,
        GRASS,
        LEAVES,
        GLASS,
        PATH,
        LOG_ALL,
        CUSTOM,
        GLAZED_TERRACOTTA,
        ROTATABLE,
        SLIME, HONEY, TRANSLUCENT, ROOTS, CUSTOM_SIDE_BOTTOM_TOP
    }
}


