package games.twinhead.moreslabsstairsandwalls.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public enum ModBlocks {

    GRASS_BLOCK(builder(Blocks.GRASS_BLOCK).shovel().modelType(ModelType.GRASS).addBlockTags(BlockTags.DIRT)),
    PODZOL(builder(Blocks.PODZOL).shovel().modelType(ModelType.GRASS).addBlockTags(BlockTags.MUSHROOM_GROW_BLOCK, BlockTags.DIRT)),
    MYCELIUM(builder(Blocks.MYCELIUM).shovel().modelType(ModelType.GRASS).addBlockTags(BlockTags.DIRT, BlockTags.MUSHROOM_GROW_BLOCK)),
    DIRT(builder(Blocks.DIRT).shovel().addBlockTags(BlockTags.DIRT)),
    DIRT_PATH(builder(Blocks.DIRT_PATH).shovel().modelType(ModelType.PATH)),
    COARSE_DIRT(builder(Blocks.COARSE_DIRT).shovel().addBlockTags(BlockTags.DIRT)),
    ROOTED_DIRT(builder(Blocks.ROOTED_DIRT).shovel().addBlockTags(BlockTags.DIRT)),

    STRIPPED_OAK_LOG(builder(Blocks.STRIPPED_OAK_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_OAK_WOOD(builder(Blocks.STRIPPED_OAK_WOOD).axe().setAllTexture("stripped_oak_log")),
    OAK_LOG(builder(Blocks.OAK_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_OAK_LOG)),
    OAK_WOOD(builder(Blocks.OAK_WOOD).axe().setAllTexture("oak_log").associatedBlock(STRIPPED_OAK_WOOD)),
    OAK_LEAVES(builder(Blocks.OAK_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    OAK_PLANKS(builder(Blocks.OAK_PLANKS).axe().wallOnly()),

    STRIPPED_SPRUCE_LOG(builder(Blocks.STRIPPED_SPRUCE_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_SPRUCE_WOOD(builder(Blocks.STRIPPED_SPRUCE_WOOD).axe().setAllTexture("stripped_spruce_log")),
    SPRUCE_LOG(builder(Blocks.SPRUCE_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_SPRUCE_LOG)),
    SPRUCE_WOOD(builder(Blocks.SPRUCE_WOOD).axe().setAllTexture("spruce_log").associatedBlock(STRIPPED_SPRUCE_WOOD)),
    SPRUCE_LEAVES(builder(Blocks.SPRUCE_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    SPRUCE_PLANKS(builder(Blocks.SPRUCE_PLANKS).axe().wallOnly()),

    STRIPPED_BIRCH_LOG(builder(Blocks.STRIPPED_BIRCH_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_BIRCH_WOOD(builder(Blocks.STRIPPED_BIRCH_WOOD).axe().setAllTexture("stripped_birch_log")),
    BIRCH_LOG(builder(Blocks.BIRCH_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_BIRCH_LOG)),
    BIRCH_WOOD(builder(Blocks.BIRCH_WOOD).axe().setAllTexture("birch_log").associatedBlock(STRIPPED_BIRCH_WOOD)),
    BIRCH_LEAVES(builder(Blocks.BIRCH_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    BIRCH_PLANKS(builder(Blocks.BIRCH_PLANKS).axe().wallOnly()),

    STRIPPED_JUNGLE_LOG(builder(Blocks.STRIPPED_JUNGLE_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_JUNGLE_WOOD(builder(Blocks.STRIPPED_JUNGLE_WOOD).axe().setAllTexture("stripped_jungle_log")),
    JUNGLE_LOG(builder(Blocks.JUNGLE_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_JUNGLE_LOG)),
    JUNGLE_WOOD(builder(Blocks.JUNGLE_WOOD).axe().setAllTexture("jungle_log").associatedBlock(STRIPPED_JUNGLE_WOOD)),
    JUNGLE_LEAVES(builder(Blocks.JUNGLE_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    JUNGLE_PLANKS(builder(Blocks.JUNGLE_PLANKS).axe().wallOnly()),

    STRIPPED_ACACIA_LOG(builder(Blocks.STRIPPED_ACACIA_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_ACACIA_WOOD(builder(Blocks.STRIPPED_ACACIA_WOOD).axe().setAllTexture("stripped_acacia_log")),
    ACACIA_LOG(builder(Blocks.ACACIA_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_ACACIA_LOG)),
    ACACIA_WOOD(builder(Blocks.ACACIA_WOOD).axe().setAllTexture("acacia_log").associatedBlock(STRIPPED_ACACIA_WOOD)),
    ACACIA_LEAVES(builder(Blocks.ACACIA_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    ACACIA_PLANKS(builder(Blocks.ACACIA_PLANKS).axe().wallOnly()),

    STRIPPED_DARK_OAK_LOG(builder(Blocks.STRIPPED_DARK_OAK_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_DARK_OAK_WOOD(builder(Blocks.STRIPPED_DARK_OAK_WOOD).axe().setAllTexture("stripped_dark_oak_log")),
    DARK_OAK_LOG(builder(Blocks.DARK_OAK_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_DARK_OAK_LOG)),
    DARK_OAK_WOOD(builder(Blocks.DARK_OAK_WOOD).axe().setAllTexture("dark_oak_log").associatedBlock(STRIPPED_DARK_OAK_WOOD)),
    DARK_OAK_LEAVES(builder(Blocks.DARK_OAK_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    DARK_OAK_PLANKS(builder(Blocks.DARK_OAK_PLANKS).axe().wallOnly()),

    STRIPPED_MANGROVE_LOG(builder(Blocks.STRIPPED_MANGROVE_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_MANGROVE_WOOD(builder(Blocks.STRIPPED_MANGROVE_WOOD).axe().setAllTexture("stripped_mangrove_log")),
    MANGROVE_LOG(builder(Blocks.MANGROVE_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_MANGROVE_LOG)),
    MANGROVE_WOOD(builder(Blocks.MANGROVE_WOOD).axe().setAllTexture("mangrove_log").associatedBlock(STRIPPED_MANGROVE_WOOD)),
    MANGROVE_LEAVES(builder(Blocks.MANGROVE_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    MANGROVE_PLANKS(builder(Blocks.MANGROVE_PLANKS).axe().wallOnly()),

    MANGROVE_ROOTS(builder(Blocks.MANGROVE_ROOTS).axe().modelType(ModelType.ROOTS)),
    MUDDY_MANGROVE_ROOTS(builder(Blocks.MUDDY_MANGROVE_ROOTS).shovel().modelType(ModelType.CUBE_BOTTOM_TOP)),

    STRIPPED_CHERRY_LOG(builder(Blocks.STRIPPED_CHERRY_LOG).axe().modelType(ModelType.LOG)),
    STRIPPED_CHERRY_WOOD(builder(Blocks.STRIPPED_CHERRY_WOOD).axe().setAllTexture("stripped_cherry_log")),
    CHERRY_LOG(builder(Blocks.CHERRY_LOG).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_CHERRY_LOG)),
    CHERRY_WOOD(builder(Blocks.CHERRY_WOOD).axe().setAllTexture("cherry_log").associatedBlock(STRIPPED_CHERRY_WOOD)),
    CHERRY_LEAVES(builder(Blocks.CHERRY_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES)),
    CHERRY_PLANKS(builder(Blocks.CHERRY_PLANKS).axe().wallOnly()),

    STRIPPED_BAMBOO_BLOCK(builder(Blocks.STRIPPED_BAMBOO_BLOCK).axe().modelType(ModelType.LOG)),
    BAMBOO_BLOCK(builder(Blocks.BAMBOO_BLOCK).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_BAMBOO_BLOCK)),
    BAMBOO_PLANKS(builder(Blocks.BAMBOO_PLANKS).axe().wallOnly()),
    BAMBOO_MOSAIC(builder(Blocks.BAMBOO_MOSAIC).axe().wallOnly()),

    AZALEA_LEAVES(builder(Blocks.AZALEA_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES, BlockTags.HOE_MINEABLE)),
    FLOWERING_AZALEA_LEAVES(builder(Blocks.FLOWERING_AZALEA_LEAVES).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES, BlockTags.HOE_MINEABLE)),

    STRIPPED_WARPED_STEM(builder(Blocks.STRIPPED_WARPED_STEM).axe().modelType(ModelType.LOG)),
    STRIPPED_WARPED_HYPHAE(builder(Blocks.STRIPPED_WARPED_HYPHAE).axe().setAllTexture("stripped_warped_stem")),
    WARPED_STEM(builder(Blocks.WARPED_STEM).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_WARPED_STEM)),
    WARPED_HYPHAE(builder(Blocks.WARPED_HYPHAE).axe().setAllTexture("warped_stem").associatedBlock(STRIPPED_WARPED_HYPHAE)),
    WARPED_WART(builder(Blocks.WARPED_WART_BLOCK).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES, BlockTags.HOE_MINEABLE)),
    WARPED_PLANKS(builder(Blocks.WARPED_PLANKS).axe().wallOnly()),

    STRIPPED_CRIMSON_STEM(builder(Blocks.STRIPPED_CRIMSON_STEM).axe().modelType(ModelType.LOG)),
    STRIPPED_CRIMSON_HYPHAE(builder(Blocks.STRIPPED_CRIMSON_HYPHAE).axe().setAllTexture("stripped_crimson_stem")),
    CRIMSON_STEM(builder(Blocks.CRIMSON_STEM).axe().modelType(ModelType.LOG).associatedBlock(STRIPPED_CRIMSON_STEM)),
    CRIMSON_HYPHAE(builder(Blocks.CRIMSON_HYPHAE).axe().setAllTexture("crimson_stem").associatedBlock(STRIPPED_CRIMSON_HYPHAE)),
    CRIMSON_WART(builder(Blocks.NETHER_WART_BLOCK).hoe().modelType(ModelType.LEAVES).addBlockTags(BlockTags.LEAVES, BlockTags.HOE_MINEABLE)),
    CRIMSON_PLANKS(builder(Blocks.CRIMSON_PLANKS).axe().wallOnly()),

    GLASS(builder(Blocks.GLASS).modelType(ModelType.GLASS)),
    WHITE_STAINED_GLASS(builder(Blocks.WHITE_STAINED_GLASS).modelType(ModelType.GLASS)),
    YELLOW_STAINED_GLASS(builder(Blocks.YELLOW_STAINED_GLASS).modelType(ModelType.GLASS)),
    BLACK_STAINED_GLASS(builder(Blocks.BLACK_STAINED_GLASS).modelType(ModelType.GLASS)),
    RED_STAINED_GLASS(builder(Blocks.RED_STAINED_GLASS).modelType(ModelType.GLASS)),
    PURPLE_STAINED_GLASS(builder(Blocks.PURPLE_STAINED_GLASS).modelType(ModelType.GLASS)),
    PINK_STAINED_GLASS(builder(Blocks.PINK_STAINED_GLASS).modelType(ModelType.GLASS)),
    ORANGE_STAINED_GLASS(builder(Blocks.ORANGE_STAINED_GLASS).modelType(ModelType.GLASS)),
    MAGENTA_STAINED_GLASS(builder(Blocks.MAGENTA_STAINED_GLASS).modelType(ModelType.GLASS)),
    LIME_STAINED_GLASS(builder(Blocks.LIME_STAINED_GLASS).modelType(ModelType.GLASS)),
    LIGHT_GRAY_STAINED_GLASS(builder(Blocks.LIGHT_GRAY_STAINED_GLASS).modelType(ModelType.GLASS)),
    LIGHT_BLUE_STAINED_GLASS(builder(Blocks.LIGHT_BLUE_STAINED_GLASS).modelType(ModelType.GLASS)),
    GREEN_STAINED_GLASS(builder(Blocks.GREEN_STAINED_GLASS).modelType(ModelType.GLASS)),
    GRAY_STAINED_GLASS(builder(Blocks.GRAY_STAINED_GLASS).modelType(ModelType.GLASS)),
    CYAN_STAINED_GLASS(builder(Blocks.CYAN_STAINED_GLASS).modelType(ModelType.GLASS)),
    BROWN_STAINED_GLASS(builder(Blocks.BROWN_STAINED_GLASS).modelType(ModelType.GLASS)),
    BLUE_STAINED_GLASS(builder(Blocks.BLUE_STAINED_GLASS).modelType(ModelType.GLASS)),

    WHITE_WOOL(builder(Blocks.WHITE_WOOL).addBlockTags(BlockTags.WOOL)),
    YELLOW_WOOL(builder(Blocks.YELLOW_WOOL).addBlockTags(BlockTags.WOOL)),
    BLACK_WOOL(builder(Blocks.BLACK_WOOL).addBlockTags(BlockTags.WOOL)),
    RED_WOOL(builder(Blocks.RED_WOOL).addBlockTags(BlockTags.WOOL)),
    PURPLE_WOOL(builder(Blocks.PURPLE_WOOL).addBlockTags(BlockTags.WOOL)),
    PINK_WOOL(builder(Blocks.PINK_WOOL).addBlockTags(BlockTags.WOOL)),
    ORANGE_WOOL(builder(Blocks.ORANGE_WOOL).addBlockTags(BlockTags.WOOL)),
    MAGENTA_WOOL(builder(Blocks.MAGENTA_WOOL).addBlockTags(BlockTags.WOOL)),
    LIME_WOOL(builder(Blocks.LIME_WOOL).addBlockTags(BlockTags.WOOL)),
    LIGHT_GRAY_WOOL(builder(Blocks.LIGHT_GRAY_WOOL).addBlockTags(BlockTags.WOOL)),
    LIGHT_BLUE_WOOL(builder(Blocks.LIGHT_BLUE_WOOL).addBlockTags(BlockTags.WOOL)),
    GREEN_WOOL(builder(Blocks.GREEN_WOOL).addBlockTags(BlockTags.WOOL)),
    GRAY_WOOL(builder(Blocks.GRAY_WOOL).addBlockTags(BlockTags.WOOL)),
    CYAN_WOOL(builder(Blocks.CYAN_WOOL).addBlockTags(BlockTags.WOOL)),
    BROWN_WOOL(builder(Blocks.BROWN_WOOL).addBlockTags(BlockTags.WOOL)),
    BLUE_WOOL(builder(Blocks.BLUE_WOOL).addBlockTags(BlockTags.WOOL)),

    WARPED_NYLIUM(builder(Blocks.WARPED_NYLIUM).modelType(ModelType.GRASS).pickaxe()),
    CRIMSON_NYLIUM(builder(Blocks.CRIMSON_NYLIUM).modelType(ModelType.GRASS).pickaxe()),

    SAND(builder(Blocks.SAND).shovel()),
    GRAVEL(builder(Blocks.GRAVEL).shovel()),
    RED_SAND(builder(Blocks.RED_SAND).shovel()),

    BLACK_CONCRETE(builder(Blocks.BLACK_CONCRETE).pickaxe()),
    BLACK_CONCRETE_POWDER(builder(Blocks.BLACK_CONCRETE_POWDER).associatedBlock(BLACK_CONCRETE).shovel()),
    BLUE_CONCRETE(builder(Blocks.BLUE_CONCRETE).pickaxe()),
    BLUE_CONCRETE_POWDER(builder(Blocks.BLUE_CONCRETE_POWDER).associatedBlock(BLUE_CONCRETE).shovel()),
    BROWN_CONCRETE(builder(Blocks.BROWN_CONCRETE).pickaxe()),
    BROWN_CONCRETE_POWDER(builder(Blocks.BROWN_CONCRETE_POWDER).associatedBlock(BROWN_CONCRETE).shovel()),
    CYAN_CONCRETE(builder(Blocks.CYAN_CONCRETE).pickaxe()),
    CYAN_CONCRETE_POWDER(builder(Blocks.CYAN_CONCRETE_POWDER).associatedBlock(CYAN_CONCRETE).shovel()),
    GRAY_CONCRETE(builder(Blocks.GRAY_CONCRETE).pickaxe()),
    GRAY_CONCRETE_POWDER(builder(Blocks.GRAY_CONCRETE_POWDER).associatedBlock(GRAY_CONCRETE).shovel()),
    GREEN_CONCRETE(builder(Blocks.GREEN_CONCRETE).pickaxe()),
    GREEN_CONCRETE_POWDER(builder(Blocks.GREEN_CONCRETE_POWDER).associatedBlock(GREEN_CONCRETE).shovel()),
    LIGHT_BLUE_CONCRETE(builder(Blocks.LIGHT_BLUE_CONCRETE).pickaxe()),
    LIGHT_BLUE_CONCRETE_POWDER(builder(Blocks.LIGHT_BLUE_CONCRETE_POWDER).associatedBlock(LIGHT_BLUE_CONCRETE).shovel()),
    LIGHT_GRAY_CONCRETE(builder(Blocks.LIGHT_GRAY_CONCRETE).pickaxe()),
    LIGHT_GRAY_CONCRETE_POWDER(builder(Blocks.LIGHT_GRAY_CONCRETE_POWDER).associatedBlock(LIGHT_GRAY_CONCRETE).shovel()),
    LIME_CONCRETE(builder(Blocks.LIME_CONCRETE).pickaxe()),
    LIME_CONCRETE_POWDER(builder(Blocks.LIME_CONCRETE_POWDER).associatedBlock(LIME_CONCRETE).shovel()),
    MAGENTA_CONCRETE(builder(Blocks.MAGENTA_CONCRETE).pickaxe()),
    MAGENTA_CONCRETE_POWDER(builder(Blocks.MAGENTA_CONCRETE_POWDER).associatedBlock(MAGENTA_CONCRETE).shovel()),
    ORANGE_CONCRETE(builder(Blocks.ORANGE_CONCRETE).pickaxe()),
    ORANGE_CONCRETE_POWDER(builder(Blocks.ORANGE_CONCRETE_POWDER).associatedBlock(ORANGE_CONCRETE).shovel()),
    PINK_CONCRETE(builder(Blocks.PINK_CONCRETE).pickaxe()),
    PINK_CONCRETE_POWDER(builder(Blocks.PINK_CONCRETE_POWDER).associatedBlock(PINK_CONCRETE).shovel()),
    PURPLE_CONCRETE(builder(Blocks.PURPLE_CONCRETE).pickaxe()),
    PURPLE_CONCRETE_POWDER(builder(Blocks.PURPLE_CONCRETE_POWDER).associatedBlock(PURPLE_CONCRETE).shovel()),
    RED_CONCRETE(builder(Blocks.RED_CONCRETE).pickaxe()),
    RED_CONCRETE_POWDER(builder(Blocks.RED_CONCRETE_POWDER).associatedBlock(RED_CONCRETE).shovel()),
    WHITE_CONCRETE(builder(Blocks.WHITE_CONCRETE).pickaxe()),
    WHITE_CONCRETE_POWDER(builder(Blocks.WHITE_CONCRETE_POWDER).associatedBlock(WHITE_CONCRETE).shovel()),
    YELLOW_CONCRETE(builder(Blocks.YELLOW_CONCRETE).pickaxe()),
    YELLOW_CONCRETE_POWDER(builder(Blocks.YELLOW_CONCRETE_POWDER).associatedBlock(YELLOW_CONCRETE).shovel()),

    TERRACOTTA(builder(Blocks.TERRACOTTA).pickaxe()),

    WHITE_TERRACOTTA(builder(Blocks.WHITE_TERRACOTTA).pickaxe()),
    YELLOW_TERRACOTTA(builder(Blocks.YELLOW_TERRACOTTA).pickaxe()),
    BLACK_TERRACOTTA(builder(Blocks.BLACK_TERRACOTTA).pickaxe()),
    RED_TERRACOTTA(builder(Blocks.RED_TERRACOTTA).pickaxe()),
    PURPLE_TERRACOTTA(builder(Blocks.PURPLE_TERRACOTTA).pickaxe()),
    PINK_TERRACOTTA(builder(Blocks.PINK_TERRACOTTA).pickaxe()),
    ORANGE_TERRACOTTA(builder(Blocks.ORANGE_TERRACOTTA).pickaxe()),
    MAGENTA_TERRACOTTA(builder(Blocks.MAGENTA_TERRACOTTA).pickaxe()),
    LIME_TERRACOTTA(builder(Blocks.LIME_TERRACOTTA).pickaxe()),
    LIGHT_GRAY_TERRACOTTA(builder(Blocks.LIGHT_GRAY_TERRACOTTA).pickaxe()),
    LIGHT_BLUE_TERRACOTTA(builder(Blocks.LIGHT_BLUE_TERRACOTTA).pickaxe()),
    GREEN_TERRACOTTA(builder(Blocks.GREEN_TERRACOTTA).pickaxe()),
    GRAY_TERRACOTTA(builder(Blocks.GRAY_TERRACOTTA).pickaxe()),
    CYAN_TERRACOTTA(builder(Blocks.CYAN_TERRACOTTA).pickaxe()),
    BROWN_TERRACOTTA(builder(Blocks.BROWN_TERRACOTTA).pickaxe()),
    BLUE_TERRACOTTA(builder(Blocks.BLUE_TERRACOTTA).pickaxe()),

    WHITE_GLAZED_TERRACOTTA(builder(Blocks.WHITE_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    YELLOW_GLAZED_TERRACOTTA(builder(Blocks.YELLOW_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    BLACK_GLAZED_TERRACOTTA(builder(Blocks.BLACK_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    RED_GLAZED_TERRACOTTA(builder(Blocks.RED_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    PURPLE_GLAZED_TERRACOTTA(builder(Blocks.PURPLE_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    PINK_GLAZED_TERRACOTTA(builder(Blocks.PINK_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    ORANGE_GLAZED_TERRACOTTA(builder(Blocks.ORANGE_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    MAGENTA_GLAZED_TERRACOTTA(builder(Blocks.MAGENTA_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    LIME_GLAZED_TERRACOTTA(builder(Blocks.LIME_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    LIGHT_GRAY_GLAZED_TERRACOTTA(builder(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    LIGHT_BLUE_GLAZED_TERRACOTTA(builder(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    GREEN_GLAZED_TERRACOTTA(builder(Blocks.GREEN_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    GRAY_GLAZED_TERRACOTTA(builder(Blocks.GRAY_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    CYAN_GLAZED_TERRACOTTA(builder(Blocks.CYAN_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    BROWN_GLAZED_TERRACOTTA(builder(Blocks.BROWN_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),
    BLUE_GLAZED_TERRACOTTA(builder(Blocks.BLUE_GLAZED_TERRACOTTA).modelType(ModelType.GLAZED_TERRACOTTA).pickaxe()),

    QUARTZ_BRICKS(builder(Blocks.QUARTZ_BRICKS).pickaxe()),
    HONEYCOMB_BLOCK(builder(Blocks.HONEYCOMB_BLOCK)),
    SNOW_BLOCK(builder(Blocks.SNOW_BLOCK).setAllTexture("snow").shovel()),

    MUSHROOM_STEM(builder(Blocks.MUSHROOM_STEM).axe()),
    BROWN_MUSHROOM_BLOCK(builder(Blocks.BROWN_MUSHROOM_BLOCK).axe()),
    RED_MUSHROOM_BLOCK(builder(Blocks.RED_MUSHROOM_BLOCK).axe()),

    PURPUR(builder(Blocks.PURPUR_BLOCK).wallOnly().pickaxe()),

    MOSS_BLOCK(builder(Blocks.MOSS_BLOCK).hoe()),
    CALCITE(builder(Blocks.CALCITE).pickaxe()),
    GLOWSTONE(builder(Blocks.GLOWSTONE)),

    CRACKED_STONE_BRICKS(builder(Blocks.CRACKED_STONE_BRICKS).pickaxe()),
    CHISELED_STONE_BRICKS(builder(Blocks.CHISELED_STONE_BRICKS).pickaxe()),

    TUFF(builder(Blocks.TUFF).pickaxe()),
    DRIPSTONE_BLOCK(builder(Blocks.DRIPSTONE_BLOCK).pickaxe()),
    SEA_LANTERN(builder(Blocks.SEA_LANTERN)),
    SHROOMLIGHT(builder(Blocks.SHROOMLIGHT).hoe()),
    END_STONE(builder(Blocks.END_STONE).pickaxe()),

    DEEPSLATE(builder(Blocks.DEEPSLATE).setTextures("deepslate","deepslate_top").pickaxe()),

    CRACKED_NETHER_BRICKS(builder(Blocks.CRACKED_NETHER_BRICKS).pickaxe()),
    CRACKED_DEEPSLATE_BRICKS(builder(Blocks.CRACKED_DEEPSLATE_BRICKS).pickaxe()),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(builder(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS).pickaxe()),
    CRACKED_DEEPSLATE_TILES(builder(Blocks.CRACKED_DEEPSLATE_TILES).pickaxe()),

    CHISELED_POLISHED_BLACKSTONE(builder(Blocks.CHISELED_POLISHED_BLACKSTONE).pickaxe()),
    CHISELED_DEEPSLATE(builder(Blocks.CHISELED_DEEPSLATE).pickaxe()),

    CRYING_OBSIDIAN(builder(Blocks.CRYING_OBSIDIAN).pickaxe().addBlockTags(BlockTags.DRAGON_IMMUNE, BlockTags.NEEDS_DIAMOND_TOOL)),

    NETHERRACK(builder(Blocks.NETHERRACK).pickaxe().addBlockTags(BlockTags.INFINIBURN_OVERWORLD, BlockTags.INFINIBURN_END, BlockTags.INFINIBURN_NETHER)),

    BASALT(builder(Blocks.BASALT).modelType(ModelType.CUBE_BOTTOM_TOP).pickaxe()),
    POLISHED_BASALT(builder(Blocks.POLISHED_BASALT).modelType(ModelType.CUBE_BOTTOM_TOP).pickaxe()),
    SMOOTH_BASALT(builder(Blocks.SMOOTH_BASALT).pickaxe()),

    OXIDIZED_COPPER(builder(Blocks.OXIDIZED_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.OXIDIZED).pickaxe()),
    WEATHERED_COPPER(builder(Blocks.WEATHERED_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.WEATHERED).associatedBlock(OXIDIZED_COPPER).pickaxe()),
    EXPOSED_COPPER(builder(Blocks.EXPOSED_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.EXPOSED).associatedBlock(WEATHERED_COPPER).pickaxe()),
    COPPER_BLOCK(builder(Blocks.COPPER_BLOCK).setOxidationLevel(Oxidizable.OxidationLevel.UNAFFECTED).associatedBlock(EXPOSED_COPPER).pickaxe()),

    WAXED_COPPER_BLOCK(builder(Blocks.WAXED_COPPER_BLOCK).setAllTexture("copper_block").associatedBlock(COPPER_BLOCK).pickaxe()),
    WAXED_EXPOSED_COPPER(builder(Blocks.WAXED_EXPOSED_COPPER).setAllTexture("exposed_copper").associatedBlock(EXPOSED_COPPER).pickaxe()),
    WAXED_WEATHERED_COPPER(builder(Blocks.WAXED_WEATHERED_COPPER).setAllTexture("weathered_copper").associatedBlock(WEATHERED_COPPER).pickaxe()),
    WAXED_OXIDIZED_COPPER(builder(Blocks.WAXED_OXIDIZED_COPPER).setAllTexture("oxidized_copper").associatedBlock(OXIDIZED_COPPER).pickaxe()),

    OXIDIZED_CUT_COPPER(builder(Blocks.OXIDIZED_CUT_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.OXIDIZED).wallOnly().pickaxe()),
    WEATHERED_CUT_COPPER(builder(Blocks.WEATHERED_CUT_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.WEATHERED).associatedBlock(OXIDIZED_CUT_COPPER).wallOnly().pickaxe()),
    EXPOSED_CUT_COPPER(builder(Blocks.EXPOSED_CUT_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.EXPOSED).associatedBlock(WEATHERED_CUT_COPPER).wallOnly().pickaxe()),
    CUT_COPPER(builder(Blocks.CUT_COPPER).setOxidationLevel(Oxidizable.OxidationLevel.UNAFFECTED).associatedBlock(EXPOSED_CUT_COPPER).wallOnly().pickaxe()),

    WAXED_CUT_COPPER(builder(Blocks.WAXED_CUT_COPPER).setAllTexture("cut_copper").associatedBlock(CUT_COPPER).wallOnly().pickaxe()),
    WAXED_EXPOSED_CUT_COPPER(builder(Blocks.WAXED_EXPOSED_CUT_COPPER).setAllTexture("exposed_cut_copper").associatedBlock(EXPOSED_CUT_COPPER).wallOnly().pickaxe()),
    WAXED_WEATHERED_CUT_COPPER(builder(Blocks.WAXED_WEATHERED_CUT_COPPER).setAllTexture("weathered_cut_copper").associatedBlock(WEATHERED_CUT_COPPER).wallOnly().pickaxe()),
    WAXED_OXIDIZED_CUT_COPPER(builder(Blocks.WAXED_OXIDIZED_CUT_COPPER).setAllTexture("oxidized_cut_copper").associatedBlock(OXIDIZED_CUT_COPPER).wallOnly().pickaxe()),

    SMOOTH_QUARTZ(builder(Blocks.SMOOTH_QUARTZ).setAllTexture("quartz_block_bottom").pickaxe()),
    QUARTZ_BLOCK(builder(Blocks.QUARTZ_BLOCK).setAllTexture("quartz_block_side").wallOnly().pickaxe()),
    CHISELED_QUARTZ_BLOCK(builder(Blocks.CHISELED_QUARTZ_BLOCK).setTextures("chiseled_quartz_block", "chiseled_quartz_block_top").pickaxe()),
    QUARTZ_PILLAR(builder(Blocks.QUARTZ_PILLAR).setTextures("quartz_pillar", "quartz_pillar_top").pickaxe()),

    POLISHED_ANDESITE(builder(Blocks.POLISHED_ANDESITE).pickaxe().wallOnly()),
    POLISHED_GRANITE(builder(Blocks.POLISHED_GRANITE).pickaxe().wallOnly()),
    POLISHED_DIORITE(builder(Blocks.POLISHED_DIORITE).pickaxe().wallOnly()),

    SMOOTH_SANDSTONE(builder(Blocks.SMOOTH_SANDSTONE).setAllTexture("sandstone_top").pickaxe().wallOnly()),
    SMOOTH_RED_SANDSTONE(builder(Blocks.SMOOTH_RED_SANDSTONE).setAllTexture("red_sandstone_top").pickaxe().wallOnly()),
    DARK_PRISMARINE(builder(Blocks.DARK_PRISMARINE).pickaxe().wallOnly()),
    PRISMARINE_BRICKS(builder(Blocks.PRISMARINE_BRICKS).pickaxe().wallOnly()),
    STONE(builder(Blocks.STONE).pickaxe().wallOnly()),

    SMOOTH_STONE(builder(Blocks.SMOOTH_STONE).pickaxe().wallOnly()),

    OBSIDIAN(builder(Blocks.OBSIDIAN).pickaxe().addBlockTags(BlockTags.DRAGON_IMMUNE, BlockTags.NEEDS_DIAMOND_TOOL)),

    AMETHYST_BLOCK(builder(Blocks.AMETHYST_BLOCK).pickaxe().addBlockTags(BlockTags.CRYSTAL_SOUND_BLOCKS)),

    RAW_COPPER_BLOCK(builder(Blocks.RAW_COPPER_BLOCK).pickaxe()),
    RAW_GOLD_BLOCK(builder(Blocks.RAW_GOLD_BLOCK).pickaxe()),
    RAW_IRON_BLOCK(builder(Blocks.RAW_IRON_BLOCK).pickaxe()),

    MAGMA_BLOCK(builder(Blocks.MAGMA_BLOCK).setAllTexture("magma").pickaxe()),

    SOUL_SAND(builder(Blocks.SOUL_SAND).shovel().addBlockTags(BlockTags.SOUL_SPEED_BLOCKS, BlockTags.SOUL_FIRE_BASE_BLOCKS)),
    SOUL_SOIL(builder(Blocks.SOUL_SOIL).shovel().addBlockTags(BlockTags.SOUL_SPEED_BLOCKS, BlockTags.SOUL_FIRE_BASE_BLOCKS)),

    CLAY(builder(Blocks.CLAY).shovel()),

    CHISELED_SANDSTONE(builder(Blocks.CHISELED_SANDSTONE).setTextures("chiseled_sandstone", "sandstone_top").pickaxe()),
    CHISELED_RED_SANDSTONE(builder(Blocks.CHISELED_RED_SANDSTONE).setTextures("chiseled_red_sandstone", "red_sandstone_top").pickaxe()),

    CUT_SANDSTONE(builder(Blocks.CUT_SANDSTONE).setTextures("cut_sandstone", "sandstone_top").pickaxe().wallOnly()),
    CUT_RED_SANDSTONE(builder(Blocks.CUT_RED_SANDSTONE).setTextures("cut_red_sandstone", "red_sandstone_top").pickaxe().wallOnly()),

    CHISELED_NETHER_BRICKS(builder(Blocks.CHISELED_NETHER_BRICKS).pickaxe()),

    COAL_BLOCK(builder(Blocks.COAL_BLOCK).pickaxe()),

    IRON_BLOCK(builder(Blocks.IRON_BLOCK).pickaxe().addBlockTags(BlockTags.BEACON_BASE_BLOCKS)),
    GOLD_BLOCK(builder(Blocks.GOLD_BLOCK).pickaxe().addBlockTags(BlockTags.BEACON_BASE_BLOCKS)),
    DIAMOND_BLOCK(builder(Blocks.DIAMOND_BLOCK).pickaxe().addBlockTags(BlockTags.BEACON_BASE_BLOCKS)),
    NETHERITE_BLOCK(builder(Blocks.NETHERITE_BLOCK).pickaxe().addBlockTags(BlockTags.BEACON_BASE_BLOCKS)),
    LAPIS_BLOCK(builder(Blocks.LAPIS_BLOCK).pickaxe()),
    EMERALD_BLOCK(builder(Blocks.EMERALD_BLOCK).pickaxe().addBlockTags(BlockTags.BEACON_BASE_BLOCKS)),

    GILDED_BLACKSTONE(builder(Blocks.GILDED_BLACKSTONE).pickaxe()),

    PUMPKIN(builder(Blocks.PUMPKIN).modelType(ModelType.CUBE_BOTTOM_TOP).axe()),
    MELON(builder(Blocks.MELON).modelType(ModelType.CUBE_BOTTOM_TOP).axe()),

    DRIED_KELP_BLOCK(builder(Blocks.DRIED_KELP_BLOCK).setTextures("dried_kelp_side", "dried_kelp_bottom", "dried_kelp_top").hoe()),

    BONE_BLOCK(builder(Blocks.BONE_BLOCK).modelType(ModelType.CUBE_BOTTOM_TOP).pickaxe()),

    HAY_BLOCK(builder(Blocks.HAY_BLOCK).modelType(ModelType.CUBE_BOTTOM_TOP).hoe()),

    BOOKSHELF(builder(Blocks.BOOKSHELF).setTextures("bookshelf", "oak_planks").axe()),

    SLIME_BLOCK(builder(Blocks.SLIME_BLOCK).modelType(ModelType.SLIME)),
    HONEY_BLOCK(builder(Blocks.HONEY_BLOCK).modelType(ModelType.HONEY)),

    REDSTONE_BLOCK(builder(Blocks.REDSTONE_BLOCK).pickaxe()),

    DEAD_TUBE_CORAL_BLOCK(builder(Blocks.DEAD_TUBE_CORAL_BLOCK).pickaxe()),
    TUBE_CORAL_BLOCK(builder(Blocks.TUBE_CORAL_BLOCK).pickaxe().associatedBlock(DEAD_TUBE_CORAL_BLOCK)),
    DEAD_BUBBLE_CORAL_BLOCK(builder(Blocks.DEAD_BUBBLE_CORAL_BLOCK).pickaxe()),
    BUBBLE_CORAL_BLOCK(builder(Blocks.BUBBLE_CORAL_BLOCK).pickaxe().associatedBlock(DEAD_BUBBLE_CORAL_BLOCK)),
    DEAD_BRAIN_CORAL_BLOCK(builder(Blocks.DEAD_BRAIN_CORAL_BLOCK).pickaxe()),
    BRAIN_CORAL_BLOCK(builder(Blocks.BRAIN_CORAL_BLOCK).pickaxe().associatedBlock(DEAD_BRAIN_CORAL_BLOCK)),
    DEAD_FIRE_CORAL_BLOCK(builder(Blocks.DEAD_FIRE_CORAL_BLOCK).pickaxe()),
    FIRE_CORAL_BLOCK(builder(Blocks.FIRE_CORAL_BLOCK).pickaxe().associatedBlock(DEAD_FIRE_CORAL_BLOCK)),
    DEAD_HORN_CORAL_BLOCK(builder(Blocks.DEAD_HORN_CORAL_BLOCK).pickaxe()),
    HORN_CORAL_BLOCK(builder(Blocks.HORN_CORAL_BLOCK).pickaxe().associatedBlock(DEAD_HORN_CORAL_BLOCK)),

    OCHRE_FROGLIGHT(builder(Blocks.OCHRE_FROGLIGHT).modelType(ModelType.CUBE_BOTTOM_TOP).hoe()),
    VERDANT_FROGLIGHT(builder(Blocks.VERDANT_FROGLIGHT).modelType(ModelType.CUBE_BOTTOM_TOP).hoe()),
    PEARLESCENT_FROGLIGHT(builder(Blocks.PEARLESCENT_FROGLIGHT).modelType(ModelType.CUBE_BOTTOM_TOP).hoe()),

    SCULK(builder(Blocks.SCULK).hoe()),

    PACKED_MUD(builder(Blocks.PACKED_MUD).pickaxe()),
    MUD(builder(Blocks.MUD).shovel()),

    ICE(builder(Blocks.ICE).modelType(ModelType.TRANSLUCENT).pickaxe()),
    PACKED_ICE(builder(Blocks.PACKED_ICE).pickaxe()),
    BLUE_ICE(builder(Blocks.BLUE_ICE).pickaxe()),

    BEDROCK(builder(Blocks.BEDROCK).pickaxe().addBlockTags(BlockTags.DRAGON_IMMUNE, BlockTags.WITHER_IMMUNE)),


    ;



    public final Block parentBlock;
    public final ModelType modelType;
    public final boolean hasSlab;
    public final boolean hasStairs;
    public final boolean hasWall;

    public final List<TagKey<Block>> blockTags;
    //private final List<TagKey<Item>> itemTags;

    public final ModBlocks associatedBlock;

    public final String textureId;
    public final String bottomId;
    public final String topId;

    public final Oxidizable.OxidationLevel oxidationLevel;

    ModBlocks(Builder builder){
        this.parentBlock = builder.parentBlock;
        this.hasSlab = builder.hasSlab;
        this.hasStairs = builder.hasStairs;
        this.hasWall = builder.hasWall;
        this.blockTags = builder.blockTags;
        //this.itemTags = builder.itemTags;
        this.associatedBlock = builder.associatedBlock;
        this.modelType = builder.modelType;

        this.textureId = builder.sideTexture;
        this.bottomId = builder.bottomTexture;
        this.topId = builder.topTexture;

        this.oxidationLevel = builder.oxidationLevel;
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
    
    private static Builder builder(Block block){
        return builder(block);
    }

    private static class Builder {
        private final Block parentBlock;
        private boolean hasSlab = true;
        private boolean hasStairs = true;
        private boolean hasWall = true;
        private ModelType modelType = ModelType.CUBE_ALL;
        private ModBlocks associatedBlock = null;

        private final List<TagKey<Block>> blockTags;
        private final List<TagKey<Item>> itemTags;

        private String sideTexture = "";
        private String bottomTexture = "";
        private String topTexture = "";

        private Oxidizable.OxidationLevel oxidationLevel = null;

        public Builder(Block parentBlock) {
            this.parentBlock = parentBlock;
            this.blockTags = new ArrayList<>();
            this.itemTags = new ArrayList<>();
        }

        public Builder modelType(ModBlocks.ModelType modelType) {
            this.modelType = modelType;
            return this;
        }

        @SafeVarargs
        public final Builder addBlockTags(TagKey<Block>... blockTags) {
            this.blockTags.addAll(List.of(blockTags));
            return this;
        }

        public Builder addBlockTag(TagKey<Block> blockTag) {
            this.blockTags.add(blockTag);
            return this;
        }

        @SafeVarargs
        public final Builder addItemTags(TagKey<Item>... itemTags) {
            this.itemTags.addAll(List.of(itemTags));
            return this;
        }

        public Builder addItemTag(TagKey<Item> itemTag) {
            this.itemTags.add(itemTag);
            return this;
        }

        public Builder hasSlab(boolean hasSlab) {
            this.hasSlab = hasSlab;
            return this;
        }

        public Builder hasStairs(boolean hasStairs) {
            this.hasStairs = hasStairs;
            return this;
        }

        public Builder hasWall(boolean hasWall) {
            this.hasWall = hasWall;
            return this;
        }

        public Builder associatedBlock(ModBlocks associatedBlock) {
            this.associatedBlock = associatedBlock;
            return this;
        }

        public Builder shovel() {
            this.blockTags.add(BlockTags.SHOVEL_MINEABLE);
            return this;
        }

        public Builder pickaxe() {
            this.blockTags.add(BlockTags.PICKAXE_MINEABLE);
            return this;
        }

        public Builder axe() {
            this.blockTags.add(BlockTags.AXE_MINEABLE);
            return this;
        }

        public Builder hoe() {
            this.blockTags.add(BlockTags.HOE_MINEABLE);
            return this;
        }

        public Builder setAllTexture(String textureId) {
            this.sideTexture = textureId;
            this.topTexture = textureId;
            this.bottomTexture = textureId;
            this.modelType = ModelType.CUSTOM;
            return this;
        }

        public Builder setTextures(String sideTexture, String topTexture, String bottomTexture) {
            this.sideTexture = sideTexture;
            this.topTexture = topTexture;
            this.bottomTexture = bottomTexture;
            this.modelType = ModelType.CUSTOM_SIDE_BOTTOM_TOP;
            return this;
        }

        public Builder setTextures(String sideTexture, String topBottomTexture) {
            return this.setTextures(sideTexture, topBottomTexture, topBottomTexture);
        }

        public Builder setBottomTexture(String bottomTexture) {
            this.bottomTexture = bottomTexture;
            return this;
        }

        public Builder setTopTexture(String topTexture) {
            this.topTexture = topTexture;
            return this;
        }

        public Builder setSideTexture(String sideTexture) {
            this.sideTexture = sideTexture;
            return this;
        }

        public Builder setOxidationLevel(Oxidizable.OxidationLevel oxidationLevel) {
            this.oxidationLevel = oxidationLevel;
            return this;
        }

        public Builder wallOnly(){
            this.hasStairs = false;
            this.hasSlab = false;
            return this;
        }
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
        CUSTOM,
        GLAZED_TERRACOTTA,
        ROTATABLE,
        SLIME,
        HONEY,
        TRANSLUCENT,
        ROOTS,
        CUSTOM_SIDE_BOTTOM_TOP
    }
}


