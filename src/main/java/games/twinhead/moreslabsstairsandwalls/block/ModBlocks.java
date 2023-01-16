package games.twinhead.moreslabsstairsandwalls.block;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public enum ModBlocks {
    WHITE_CONCRETE(Blocks.WHITE_CONCRETE, ToolType.PICKAXE, DyeColor.WHITE),
    YELLOW_CONCRETE(Blocks.YELLOW_CONCRETE, ToolType.PICKAXE, DyeColor.YELLOW),
    BLACK_CONCRETE(Blocks.BLACK_CONCRETE, ToolType.PICKAXE, DyeColor.BLACK),
    RED_CONCRETE(Blocks.RED_CONCRETE, ToolType.PICKAXE, DyeColor.RED),
    PURPLE_CONCRETE(Blocks.PURPLE_CONCRETE, ToolType.PICKAXE, DyeColor.PURPLE),
    PINK_CONCRETE(Blocks.PINK_CONCRETE, ToolType.PICKAXE, DyeColor.PINK),
    ORANGE_CONCRETE(Blocks.ORANGE_CONCRETE, ToolType.PICKAXE, DyeColor.ORANGE),
    MAGENTA_CONCRETE(Blocks.MAGENTA_CONCRETE, ToolType.PICKAXE, DyeColor.MAGENTA),
    LIME_CONCRETE(Blocks.LIME_CONCRETE, ToolType.PICKAXE, DyeColor.LIME),
    LIGHT_GRAY_CONCRETE(Blocks.LIGHT_GRAY_CONCRETE, ToolType.PICKAXE, DyeColor.LIGHT_GRAY),
    LIGHT_BLUE_CONCRETE(Blocks.LIGHT_BLUE_CONCRETE, ToolType.PICKAXE, DyeColor.LIGHT_BLUE),
    GREEN_CONCRETE(Blocks.GREEN_CONCRETE, ToolType.PICKAXE, DyeColor.GREEN),
    GRAY_CONCRETE(Blocks.GRAY_CONCRETE, ToolType.PICKAXE, DyeColor.GRAY),
    CYAN_CONCRETE(Blocks.CYAN_CONCRETE, ToolType.PICKAXE, DyeColor.CYAN),
    BROWN_CONCRETE(Blocks.BROWN_CONCRETE, ToolType.PICKAXE, DyeColor.BROWN),
    BLUE_CONCRETE(Blocks.BLUE_CONCRETE, ToolType.PICKAXE, DyeColor.BLUE),

    WHITE_TERRACOTTA(Blocks.WHITE_TERRACOTTA, ToolType.PICKAXE, DyeColor.WHITE),
    YELLOW_TERRACOTTA(Blocks.YELLOW_TERRACOTTA, ToolType.PICKAXE, DyeColor.YELLOW),
    BLACK_TERRACOTTA(Blocks.BLACK_TERRACOTTA, ToolType.PICKAXE, DyeColor.BLACK),
    RED_TERRACOTTA(Blocks.RED_TERRACOTTA, ToolType.PICKAXE, DyeColor.RED),
    PURPLE_TERRACOTTA(Blocks.PURPLE_TERRACOTTA, ToolType.PICKAXE, DyeColor.PURPLE),
    PINK_TERRACOTTA(Blocks.PINK_TERRACOTTA, ToolType.PICKAXE, DyeColor.PINK),
    ORANGE_TERRACOTTA(Blocks.ORANGE_TERRACOTTA, ToolType.PICKAXE, DyeColor.ORANGE),
    MAGENTA_TERRACOTTA(Blocks.MAGENTA_TERRACOTTA, ToolType.PICKAXE, DyeColor.MAGENTA),
    LIME_TERRACOTTA(Blocks.LIME_TERRACOTTA, ToolType.PICKAXE, DyeColor.LIME),
    LIGHT_GRAY_TERRACOTTA(Blocks.LIGHT_GRAY_TERRACOTTA, ToolType.PICKAXE, DyeColor.LIGHT_GRAY),
    LIGHT_BLUE_TERRACOTTA(Blocks.LIGHT_BLUE_TERRACOTTA, ToolType.PICKAXE, DyeColor.LIGHT_BLUE),
    GREEN_TERRACOTTA(Blocks.GREEN_TERRACOTTA, ToolType.PICKAXE, DyeColor.GREEN),
    GRAY_TERRACOTTA(Blocks.GRAY_TERRACOTTA, ToolType.PICKAXE, DyeColor.GRAY),
    CYAN_TERRACOTTA(Blocks.CYAN_TERRACOTTA, ToolType.PICKAXE, DyeColor.CYAN),
    BROWN_TERRACOTTA(Blocks.BROWN_TERRACOTTA, ToolType.PICKAXE, DyeColor.BROWN),
    BLUE_TERRACOTTA(Blocks.BLUE_TERRACOTTA, ToolType.PICKAXE, DyeColor.BLUE),
    TERRACOTTA(Blocks.TERRACOTTA, ToolType.PICKAXE),

    WHITE_WOOL(Blocks.WHITE_WOOL, ToolType.NONE, DyeColor.WHITE),
    YELLOW_WOOL(Blocks.YELLOW_WOOL, ToolType.NONE, DyeColor.YELLOW),
    BLACK_WOOL(Blocks.BLACK_WOOL, ToolType.NONE, DyeColor.BLACK),
    RED_WOOL(Blocks.RED_WOOL, ToolType.NONE, DyeColor.RED),
    PURPLE_WOOL(Blocks.PURPLE_WOOL, ToolType.NONE, DyeColor.PURPLE),
    PINK_WOOL(Blocks.PINK_WOOL, ToolType.NONE, DyeColor.PINK),
    ORANGE_WOOL(Blocks.ORANGE_WOOL, ToolType.NONE, DyeColor.ORANGE),
    MAGENTA_WOOL(Blocks.MAGENTA_WOOL, ToolType.NONE, DyeColor.MAGENTA),
    LIME_WOOL(Blocks.LIME_WOOL, ToolType.NONE, DyeColor.LIME),
    LIGHT_GRAY_WOOL(Blocks.LIGHT_GRAY_WOOL, ToolType.NONE, DyeColor.LIGHT_GRAY),
    LIGHT_BLUE_WOOL(Blocks.LIGHT_BLUE_WOOL, ToolType.NONE, DyeColor.LIGHT_BLUE),
    GREEN_WOOL(Blocks.GREEN_WOOL, ToolType.NONE, DyeColor.GREEN),
    GRAY_WOOL(Blocks.GRAY_WOOL, ToolType.NONE, DyeColor.GRAY),
    CYAN_WOOL(Blocks.CYAN_WOOL, ToolType.NONE, DyeColor.CYAN),
    BROWN_WOOL(Blocks.BROWN_WOOL, ToolType.NONE, DyeColor.BROWN),
    BLUE_WOOL(Blocks.BLUE_WOOL, ToolType.NONE, DyeColor.BLUE),

    WHITE_STAINED_GLASS(Blocks.WHITE_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.WHITE),
    YELLOW_STAINED_GLASS(Blocks.YELLOW_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.YELLOW),
    BLACK_STAINED_GLASS(Blocks.BLACK_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.BLACK),
    RED_STAINED_GLASS(Blocks.RED_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.RED),
    PURPLE_STAINED_GLASS(Blocks.PURPLE_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.PURPLE),
    PINK_STAINED_GLASS(Blocks.PINK_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.PINK),
    ORANGE_STAINED_GLASS(Blocks.ORANGE_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.ORANGE),
    MAGENTA_STAINED_GLASS(Blocks.MAGENTA_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.MAGENTA),
    LIME_STAINED_GLASS(Blocks.LIME_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.LIME),
    LIGHT_GRAY_STAINED_GLASS(Blocks.LIGHT_GRAY_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.LIGHT_GRAY),
    LIGHT_BLUE_STAINED_GLASS(Blocks.LIGHT_BLUE_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.LIGHT_BLUE),
    GREEN_STAINED_GLASS(Blocks.GREEN_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.GREEN),
    GRAY_STAINED_GLASS(Blocks.GRAY_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.GRAY),
    CYAN_STAINED_GLASS(Blocks.CYAN_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.CYAN),
    BROWN_STAINED_GLASS(Blocks.BROWN_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.BROWN),
    BLUE_STAINED_GLASS(Blocks.BLUE_STAINED_GLASS, ToolType.NONE, Layer.TRANSLUCENT, DyeColor.BLUE),
    GLASS(Blocks.GLASS, ToolType.NONE, Layer.CUTOUT),

    QUARTZ_BRICKS(Blocks.QUARTZ_BRICKS, ToolType.PICKAXE),
    NETHERRACK(Blocks.NETHERRACK, ToolType.PICKAXE),
    DIRT(Blocks.DIRT, ToolType.SHOVEL),
    COARSE_DIRT(Blocks.COARSE_DIRT, ToolType.SHOVEL),
    HONEYCOMB_BLOCK(Blocks.HONEYCOMB_BLOCK, ToolType.NONE),
    ROOTED_DIRT(Blocks.ROOTED_DIRT, ToolType.SHOVEL),
    SNOW_BLOCK(Blocks.SNOW_BLOCK, ToolType.SHOVEL, "snow"),
    MUSHROOM_STEM(Blocks.MUSHROOM_STEM, ToolType.AXE),
    BROWN_MUSHROOM_BLOCK(Blocks.BROWN_MUSHROOM_BLOCK, ToolType.AXE),
    RED_MUSHROOM_BLOCK(Blocks.RED_MUSHROOM_BLOCK, ToolType.AXE),

    PURPUR(Blocks.PURPUR_BLOCK, ToolType.PICKAXE, false, false, true),

    MOSS_BLOCK(Blocks.MOSS_BLOCK, ToolType.HOE),
    CALCITE(Blocks.CALCITE, ToolType.PICKAXE),
    GLOWSTONE(Blocks.GLOWSTONE, ToolType.NONE),
    CRACKED_STONE_BRICKS(Blocks.CRACKED_STONE_BRICKS, ToolType.PICKAXE),
    TUFF(Blocks.TUFF, ToolType.PICKAXE),
    DRIPSTONE_BLOCK(Blocks.DRIPSTONE_BLOCK, ToolType.PICKAXE),
    SEA_LANTERN(Blocks.SEA_LANTERN, ToolType.NONE),
    SHROOMLIGHT(Blocks.SHROOMLIGHT, ToolType.HOE),
    END_STONE(Blocks.END_STONE, ToolType.PICKAXE),




    BASALT(Blocks.BASALT, ToolType.PICKAXE, true, false, true, "basalt_side", "basalt_top", "basalt_top"),
    SMOOTH_BASALT(Blocks.SMOOTH_BASALT, ToolType.PICKAXE),

    CUT_COPPER(Blocks.CUT_COPPER, ToolType.PICKAXE, false, false, true),
    EXPOSED_CUT_COPPER(Blocks.EXPOSED_CUT_COPPER, ToolType.PICKAXE, false, false, true),
    WEATHERED_CUT_COPPER(Blocks.WEATHERED_CUT_COPPER, ToolType.PICKAXE, false, false, true),
    OXIDIZED_CUT_COPPER(Blocks.OXIDIZED_CUT_COPPER, ToolType.PICKAXE, false, false, true),

    WAXED_CUT_COPPER(Blocks.WAXED_CUT_COPPER, ToolType.PICKAXE, false, false, true,"cut_copper"),
    WAXED_EXPOSED_CUT_COPPER(Blocks.WAXED_EXPOSED_CUT_COPPER, ToolType.PICKAXE, false, false, true,"exposed_cut_copper"),
    WAXED_WEATHERED_CUT_COPPER(Blocks.WAXED_WEATHERED_CUT_COPPER, ToolType.PICKAXE, false, false, true,"weathered_cut_copper"),
    WAXED_OXIDIZED_CUT_COPPER(Blocks.WAXED_OXIDIZED_CUT_COPPER, ToolType.PICKAXE, false, false, true,"oxidized_cut_copper"),


    SMOOTH_QUARTZ(Blocks.SMOOTH_QUARTZ, ToolType.PICKAXE, false, false, true, "quartz_block_bottom"),
    QUARTZ(Blocks.QUARTZ_BLOCK, ToolType.PICKAXE, false, false, true, "quartz_block_side"),

    POLISHED_ANDESITE(Blocks.POLISHED_ANDESITE, ToolType.PICKAXE, false, false, true),
    POLISHED_GRANITE(Blocks.POLISHED_GRANITE, ToolType.PICKAXE, false, false, true),
    POLISHED_DIORITE(Blocks.POLISHED_DIORITE, ToolType.PICKAXE, false, false, true),


    SMOOTH_SANDSTONE(Blocks.SMOOTH_SANDSTONE, ToolType.PICKAXE, false, false, true, "sandstone_top"),
    SMOOTH_RED_SANDSTONE(Blocks.SMOOTH_RED_SANDSTONE, ToolType.PICKAXE, false, false, true, "red_sandstone_top"),
    DARK_PRISMARINE(Blocks.DARK_PRISMARINE, ToolType.PICKAXE, false, false, true),
    PRISMARINE_BRICKS(Blocks.PRISMARINE_BRICKS, ToolType.PICKAXE, false, false, true),
    STONE(Blocks.STONE, ToolType.PICKAXE, false, false, true),

    SMOOTH_STONE(Blocks.SMOOTH_STONE, ToolType.PICKAXE, false, true, true),

    OBSIDIAN(Blocks.OBSIDIAN, ToolType.PICKAXE),


    //2.0.0



    AMETHYST_BLOCK(Blocks.AMETHYST_BLOCK, ToolType.PICKAXE),

    RAW_COPPER_BLOCK(Blocks.RAW_COPPER_BLOCK, ToolType.PICKAXE),
    RAW_GOLD_BLOCK(Blocks.RAW_GOLD_BLOCK, ToolType.PICKAXE),
    RAW_IRON_BLOCK(Blocks.RAW_IRON_BLOCK, ToolType.PICKAXE),

    MAGMA_BLOCK(Blocks.MAGMA_BLOCK, ToolType.PICKAXE, "magma"),

    SOUL_SAND(Blocks.SOUL_SAND, ToolType.SHOVEL),
    SOUL_SOIL(Blocks.SOUL_SOIL, ToolType.SHOVEL),

    CLAY(Blocks.CLAY, ToolType.SHOVEL),

    CRYING_OBSIDIAN(Blocks.CRYING_OBSIDIAN, ToolType.PICKAXE),

    CRACKED_NETHER_BRICKS(Blocks.CRACKED_NETHER_BRICKS, ToolType.PICKAXE),
    CRACKED_DEEPSLATE_BRICKS(Blocks.CRACKED_DEEPSLATE_BRICKS, ToolType.PICKAXE),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, ToolType.PICKAXE),
    CRACKED_DEEPSLATE_TILES(Blocks.CRACKED_DEEPSLATE_TILES, ToolType.PICKAXE),

    DEAD_TUBE_CORAL_BLOCK(Blocks.DEAD_TUBE_CORAL_BLOCK, ToolType.PICKAXE),
    DEAD_BUBBLE_CORAL_BLOCK(Blocks.DEAD_BUBBLE_CORAL_BLOCK, ToolType.PICKAXE),
    DEAD_BRAIN_CORAL_BLOCK(Blocks.DEAD_BRAIN_CORAL_BLOCK, ToolType.PICKAXE),
    DEAD_FIRE_CORAL_BLOCK(Blocks.DEAD_FIRE_CORAL_BLOCK, ToolType.PICKAXE),
    DEAD_HORN_CORAL_BLOCK(Blocks.DEAD_HORN_CORAL_BLOCK, ToolType.PICKAXE),

    TUBE_CORAL_BLOCK(Blocks.TUBE_CORAL_BLOCK, ToolType.PICKAXE, DEAD_TUBE_CORAL_BLOCK),
    BUBBLE_CORAL_BLOCK(Blocks.BUBBLE_CORAL_BLOCK, ToolType.PICKAXE, DEAD_BUBBLE_CORAL_BLOCK),
    BRAIN_CORAL_BLOCK(Blocks.BRAIN_CORAL_BLOCK, ToolType.PICKAXE, DEAD_BRAIN_CORAL_BLOCK),
    FIRE_CORAL_BLOCK(Blocks.FIRE_CORAL_BLOCK, ToolType.PICKAXE, DEAD_FIRE_CORAL_BLOCK),
    HORN_CORAL_BLOCK(Blocks.HORN_CORAL_BLOCK, ToolType.PICKAXE, DEAD_HORN_CORAL_BLOCK),

    ICE(Blocks.ICE, ToolType.PICKAXE, Layer.TRANSLUCENT),
    PACKED_ICE(Blocks.PACKED_ICE, ToolType.PICKAXE),
    BLUE_ICE(Blocks.BLUE_ICE, ToolType.PICKAXE),

    SLIME_BLOCK(Blocks.SLIME_BLOCK, ToolType.NONE, Layer.TRANSLUCENT),
    HONEY_BLOCK(Blocks.HONEY_BLOCK, ToolType.NONE, Layer.TRANSLUCENT, "honey_block_side", "honey_block_top", "honey_block_bottom"),

    CRIMSON_NYLIUM(Blocks.CRIMSON_NYLIUM, ToolType.PICKAXE, Layer.SOLID, "crimson_nylium_side", "crimson_nylium", "netherrack"),
    WARPED_NYLIUM(Blocks.WARPED_NYLIUM, ToolType.PICKAXE, Layer.SOLID, "warped_nylium_side", "warped_nylium", "netherrack"),
    DRIED_KELP_BLOCK(Blocks.DRIED_KELP_BLOCK, ToolType.HOE, Layer.SOLID, "dried_kelp_side", "dried_kelp_top", "dried_kelp_bottom"),

    GRASS_BLOCK(Blocks.GRASS_BLOCK, ToolType.SHOVEL, Layer.CUTOUT, "grass_block_side", "grass_block_top", "dirt"),
    MYCELIUM(Blocks.MYCELIUM, ToolType.SHOVEL, Layer.CUTOUT, "mycelium_side", "mycelium_top", "dirt"),
    PODZOL(Blocks.PODZOL, ToolType.SHOVEL, Layer.CUTOUT, "podzol_side", "podzol_top", "dirt"),

    COAL_BLOCK(Blocks.COAL_BLOCK, ToolType.PICKAXE),
    IRON_BLOCK(Blocks.IRON_BLOCK, ToolType.PICKAXE),
    GOLD_BLOCK(Blocks.GOLD_BLOCK, ToolType.PICKAXE),
    DIAMOND_BLOCK(Blocks.DIAMOND_BLOCK, ToolType.PICKAXE),
    NETHERITE_BLOCK(Blocks.NETHERITE_BLOCK, ToolType.PICKAXE),
    LAPIS_BLOCK(Blocks.LAPIS_BLOCK, ToolType.PICKAXE),
    EMERALD_BLOCK(Blocks.EMERALD_BLOCK, ToolType.PICKAXE),


    GILDED_BLACKSTONE(Blocks.GILDED_BLACKSTONE, ToolType.PICKAXE),



    PUMPKIN(Blocks.PUMPKIN, ToolType.AXE, Layer.SOLID, "pumpkin_side", "pumpkin_top", "pumpkin_top"),
    MELON(Blocks.MELON, ToolType.AXE, Layer.SOLID, "melon_side", "melon_top", "melon_top"),

    BONE_BLOCK(Blocks.BONE_BLOCK, ToolType.PICKAXE, Layer.SOLID, "bone_block_side", "bone_block_top", "bone_block_top"),

    HAY_BLOCK(Blocks.HAY_BLOCK, ToolType.HOE, Layer.SOLID, "hay_block_side", "hay_block_top", "hay_block_top"),

    BOOKSHELF(Blocks.BOOKSHELF, ToolType.AXE, true, true, false, "bookshelf", "oak_planks", "oak_planks"),

    OAK_LOG(Blocks.OAK_LOG, ToolType.AXE,Layer.SOLID,  "oak_log", "oak_log_top", "oak_log_top"),
    STRIPPED_OAK_LOG(Blocks.STRIPPED_OAK_LOG, ToolType.AXE, Layer.SOLID, "stripped_oak_log", "stripped_oak_log_top", "stripped_oak_log_top"),
    OAK_WOOD(Blocks.OAK_WOOD, ToolType.AXE, "oak_log"),
    STRIPPED_OAK_WOOD(Blocks.STRIPPED_OAK_WOOD, ToolType.AXE, "stripped_oak_log"),
    OAK_LEAVES(Blocks.OAK_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "oak_leaves"),
    OAK_PLANKS(Blocks.OAK_PLANKS, ToolType.AXE, false, false, true),

    BIRCH_LOG(Blocks.BIRCH_LOG, ToolType.AXE,Layer.SOLID,  "birch_log", "birch_log_top", "birch_log_top"),
    STRIPPED_BIRCH_LOG(Blocks.STRIPPED_BIRCH_LOG, ToolType.AXE, Layer.SOLID, "stripped_birch_log", "stripped_birch_log_top", "stripped_birch_log_top"),
    BIRCH_WOOD(Blocks.BIRCH_WOOD, ToolType.AXE, "birch_log"),
    STRIPPED_BIRCH_WOOD(Blocks.STRIPPED_BIRCH_WOOD, ToolType.AXE, "stripped_birch_log"),
    BIRCH_LEAVES(Blocks.BIRCH_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "birch_leaves"),
    BIRCH_PLANKS(Blocks.BIRCH_PLANKS, ToolType.AXE, false, false, true),

    SPRUCE_LOG(Blocks.SPRUCE_LOG, ToolType.AXE,Layer.SOLID,  "spruce_log", "spruce_log_top", "spruce_log_top"),
    STRIPPED_SPRUCE_LOG(Blocks.STRIPPED_SPRUCE_LOG, ToolType.AXE, Layer.SOLID, "stripped_spruce_log", "stripped_spruce_log_top", "stripped_spruce_log_top"),
    SPRUCE_WOOD(Blocks.SPRUCE_WOOD, ToolType.AXE, "spruce_log"),
    STRIPPED_SPRUCE_WOOD(Blocks.STRIPPED_SPRUCE_WOOD, ToolType.AXE, "stripped_spruce_log"),
    SPRUCE_LEAVES(Blocks.SPRUCE_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "spruce_leaves"),
    SPRUCE_PLANKS(Blocks.SPRUCE_PLANKS, ToolType.AXE, false, false, true),

    JUNGLE_LOG(Blocks.JUNGLE_LOG, ToolType.AXE,Layer.SOLID,  "jungle_log", "jungle_log_top", "jungle_log_top"),
    STRIPPED_JUNGLE_LOG(Blocks.STRIPPED_JUNGLE_LOG, ToolType.AXE, Layer.SOLID, "stripped_jungle_log", "stripped_jungle_log_top", "stripped_jungle_log_top"),
    JUNGLE_WOOD(Blocks.JUNGLE_WOOD, ToolType.AXE, "jungle_log"),
    STRIPPED_JUNGLE_WOOD(Blocks.STRIPPED_JUNGLE_WOOD, ToolType.AXE, "stripped_jungle_log"),
    JUNGLE_LEAVES(Blocks.JUNGLE_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "jungle_leaves"),
    JUNGLE_PLANKS(Blocks.JUNGLE_PLANKS, ToolType.AXE, false, false, true),

    ACACIA_LOG(Blocks.ACACIA_LOG, ToolType.AXE,Layer.SOLID,  "acacia_log", "acacia_log_top", "acacia_log_top"),
    STRIPPED_ACACIA_LOG(Blocks.STRIPPED_ACACIA_LOG, ToolType.AXE, Layer.SOLID, "stripped_acacia_log", "stripped_acacia_log_top", "stripped_acacia_log_top"),
    ACACIA_WOOD(Blocks.ACACIA_WOOD, ToolType.AXE, "acacia_log"),
    STRIPPED_ACACIA_WOOD(Blocks.STRIPPED_ACACIA_WOOD, ToolType.AXE, "stripped_acacia_log"),
    ACACIA_LEAVES(Blocks.ACACIA_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "acacia_leaves"),
    ACACIA_PLANKS(Blocks.ACACIA_PLANKS, ToolType.AXE, false, false, true),

    DARK_OAK_LOG(Blocks.DARK_OAK_LOG, ToolType.AXE,Layer.SOLID,  "dark_oak_log", "dark_oak_log_top", "dark_oak_log_top"),
    STRIPPED_DARK_OAK_LOG(Blocks.STRIPPED_DARK_OAK_LOG, ToolType.AXE, Layer.SOLID, "stripped_dark_oak_log", "stripped_dark_oak_log_top", "stripped_dark_oak_log_top"),
    DARK_OAK_WOOD(Blocks.DARK_OAK_WOOD, ToolType.AXE, "dark_oak_log"),
    STRIPPED_DARK_OAK_WOOD(Blocks.STRIPPED_DARK_OAK_WOOD, ToolType.AXE, "stripped_dark_oak_log"),
    DARK_OAK_LEAVES(Blocks.DARK_OAK_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "dark_oak_leaves"),
    DARK_OAK_PLANKS(Blocks.DARK_OAK_PLANKS, ToolType.AXE, false, false, true),

    CRIMSON_STEM(Blocks.CRIMSON_STEM, ToolType.AXE,Layer.SOLID,  "crimson_stem", "crimson_stem_top", "crimson_stem_top"),
    STRIPPED_CRIMSON_STEM(Blocks.STRIPPED_CRIMSON_STEM, ToolType.AXE, Layer.SOLID, "stripped_crimson_stem", "stripped_crimson_stem_top", "stripped_crimson_stem_top"),
    CRIMSON_HYPHAE(Blocks.CRIMSON_HYPHAE, ToolType.AXE, "crimson_stem"),
    STRIPPED_CRIMSON_HYPHAE(Blocks.STRIPPED_CRIMSON_HYPHAE, ToolType.AXE, "stripped_crimson_stem"),
    NETHER_WART_BLOCK(Blocks.NETHER_WART_BLOCK, ToolType.HOE, Layer.CUTOUT_MIPPED, "nether_wart_block"),
    CRIMSON_PLANKS(Blocks.CRIMSON_PLANKS, ToolType.AXE, false, false, true),

    WARPED_STEM(Blocks.WARPED_STEM, ToolType.AXE,Layer.SOLID,  "warped_stem", "warped_stem_top", "warped_stem_top"),
    STRIPPED_WARPED_STEM(Blocks.STRIPPED_WARPED_STEM, ToolType.AXE, Layer.SOLID, "stripped_warped_stem", "stripped_warped_stem_top", "stripped_warped_stem_top"),
    WARPED_HYPHAE(Blocks.WARPED_HYPHAE, ToolType.AXE, "warped_stem"),
    STRIPPED_WARPED_HYPHAE(Blocks.STRIPPED_WARPED_HYPHAE, ToolType.AXE, "stripped_warped_stem"),
    WARPED_WART_BLOCK(Blocks.WARPED_WART_BLOCK, ToolType.HOE, Layer.CUTOUT_MIPPED, "warped_wart_block"),
    WARPED_PLANKS(Blocks.WARPED_PLANKS, ToolType.AXE, false, false, true),

    AZALEA_LEAVES(Blocks.AZALEA_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED),
    FLOWERING_AZALEA_LEAVES(Blocks.FLOWERING_AZALEA_LEAVES, ToolType.HOE, Layer.CUTOUT_MIPPED, "flowering_azalea_leaves"),


    //2.1.0
    REDSTONE_BLOCK(Blocks.REDSTONE_BLOCK, ToolType.PICKAXE, true, true, false),

    //2.2.0
    //SAND(Blocks.SAND, ToolType.SHOVEL, true, false, false)

    COPPER_BLOCK(Blocks.COPPER_BLOCK, ToolType.PICKAXE),
    EXPOSED_COPPER(Blocks.EXPOSED_COPPER, ToolType.PICKAXE),
    WEATHERED_COPPER(Blocks.WEATHERED_COPPER, ToolType.PICKAXE),
    OXIDIZED_COPPER(Blocks.OXIDIZED_COPPER, ToolType.PICKAXE),

    WAXED_COPPER(Blocks.WAXED_COPPER_BLOCK, ToolType.PICKAXE, "copper_block"),
    WAXED_EXPOSED_COPPER(Blocks.WAXED_EXPOSED_COPPER, ToolType.PICKAXE, "exposed_copper"),
    WAXED_WEATHERED_COPPER(Blocks.WAXED_WEATHERED_COPPER, ToolType.PICKAXE, "weathered_copper"),
    WAXED_OXIDIZED_COPPER(Blocks.WAXED_OXIDIZED_COPPER, ToolType.PICKAXE, "oxidized_copper"),




    //2.3.0

    DIRT_PATH(Blocks.DIRT_PATH, ToolType.SHOVEL),

    ;




    public final boolean hasSlab, hasStairs, hasWall;
    private final Block copyBlock;
    public ModBlocks deadCoralBlock = null;
    private Layer renderLayer;
    private boolean customTexture;
    public final ToolType toolType;
    private String sideTexture, topTexture, bottomTexture;
    private DyeColor color = null;

    ModBlocks(Block copyBlock, ToolType toolType){
        this.toolType = toolType;
        hasSlab = true;
        hasStairs = true;
        hasWall = true;
        this.copyBlock = copyBlock;
        this.renderLayer = Layer.SOLID;
        customTexture = false;
    }

    ModBlocks(Block copyBlock, ToolType toolType, ModBlocks deadCoralBlock){
        this(copyBlock, toolType);
        this.deadCoralBlock = deadCoralBlock;
    }

    ModBlocks(Block copyBlock, ToolType toolType, String textureAll){
        this(copyBlock, toolType);
        this.sideTexture = textureAll;
        this.topTexture  = textureAll;
        this.bottomTexture = textureAll;
        customTexture = true;
    }

    ModBlocks(Block copyBlock, ToolType toolType, DyeColor color){
        this(copyBlock, toolType);
        this.color = color;
    }

    ModBlocks(Block copyBlock, ToolType toolType, Layer renderLayer){
        this(copyBlock, toolType);
        this.renderLayer = renderLayer;
    }

    ModBlocks(Block copyBlock, ToolType toolType, Layer renderLayer, String textureAll){
        this(copyBlock, toolType, renderLayer);
        this.sideTexture = textureAll;
        this.topTexture  = textureAll;
        this.bottomTexture = textureAll;
        customTexture = true;
    }

    ModBlocks(Block copyBlock, ToolType toolType, Layer renderLayer, String side, String top, String bottom){
        this(copyBlock, toolType, renderLayer);
        this.sideTexture = side;
        this.topTexture  = top;
        this.bottomTexture = bottom;
        customTexture = true;
    }

    ModBlocks(Block copyBlock, ToolType toolType, Layer renderLayer, DyeColor color){
        this(copyBlock, toolType, renderLayer);
        this.color = color;
    }

    ModBlocks(Block copyBlock, ToolType toolType, boolean slab, boolean stairs, boolean wall){
        this.toolType = toolType;
        hasSlab = slab;
        hasStairs = stairs;
        hasWall = wall;
        this.copyBlock = copyBlock;
        this.renderLayer = Layer.SOLID;
    }

    ModBlocks(Block copyBlock, ToolType toolType, boolean slab, boolean stairs, boolean wall, String textureAll){
        this(copyBlock, toolType, slab, stairs, wall);
        this.sideTexture = textureAll;
        this.topTexture  = textureAll;
        this.bottomTexture = textureAll;
        customTexture = true;
    }

    ModBlocks(Block copyBlock, ToolType toolType, boolean slab, boolean stairs, boolean wall, String sideTexture, String topTexture, String bottomTexture){
        this(copyBlock, toolType, slab, stairs, wall);
        this.sideTexture = sideTexture;
        this.topTexture  = topTexture;
        this.bottomTexture = bottomTexture;
        customTexture = true;
    }

    public Block getSlabBlock(){
        if(this.hasSlab){
            return BlockRegistry.SLABS.get(this);
        }
        return null;
    }

    public Block getStairsBlock(){
        if(this.hasStairs){
            return BlockRegistry.STAIRS.get(this);
        }
        return null;
    }
    public Block getWallBlock(){
        if(this.hasWall){
            return BlockRegistry.WALLS.get(this);
        }
        return null;
    }

    public Block getCopyBlock(){
        return copyBlock;
    }

    public String toString(){
        return this.name().toLowerCase();
    }

    public Identifier getIdentifier(BlockType blockType){
        return new Identifier(MoreSlabsStairsAndWalls.mod_id, this + "_" + blockType);
    }

    public Layer getRenderLayer(){
        return renderLayer;
    }

    public boolean hasSlab(){
        return hasSlab && getSlabBlock() != null;
    }

    public boolean hasStairs(){
        return hasStairs && getStairsBlock() != null;
    }

    public boolean hasWall(){
        return hasWall && getWallBlock() != null;
    }

    public DyeColor getDyeColor(){
        return this.color;
    }

    public int getLuminance() {
        return this.getCopyBlock().getDefaultState().getLuminance();
    }

    public String getSideTexture(){
        return sideTexture;
    }

    public String getTopTexture(){
        return topTexture;
    }

    public String getBottomTexture(){
        return bottomTexture;
    }

    public boolean hasCustomTexture(){
        return customTexture;
    }

    public enum ToolType{
        PICKAXE,
        AXE,
        SHOVEL,
        HOE,
        NONE
    }

    public enum BlockType{
        SLAB,
        STAIRS,
        WALL;

        public String toString(){
            return this.name().toLowerCase();
        }
    }

    public enum Layer{
        CUTOUT,
        TRANSLUCENT,
        CUTOUT_MIPPED,
        SOLID,
        NONE
    }
}


