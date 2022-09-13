package games.twinhead;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class BlockRegistry {
    public static final String[] colorNames = new String[]{"white", "yellow", "black", "red", "purple", "pink", "orange", "magenta", "lime", "light_gray", "light_blue", "green", "gray", "cyan", "brown", "blue"};

    public static HashMap<String, Block> blocks = new HashMap<>();

    public static void RegisterAllBlocks(){
        registerColorBlock("concrete");
        registerColorBlock("terracotta");
        registerColorBlock("wool");

        registerColorBlock("stained_glass");
        registerAll("glass", Blocks.GLASS);

        registerAll("sculk", Blocks.SCULK);

        registerAll("smooth_basalt", Blocks.SMOOTH_BASALT);

        registerAll("quartz_bricks", Blocks.QUARTZ_BRICKS);
        registerAll("netherrack", Blocks.NETHERRACK);
        registerAll("packed_mud", Blocks.PACKED_MUD);
        registerAll("mud", Blocks.MUD);

        registerAll("dirt", Blocks.DIRT);
        registerAll("coarse_dirt", Blocks.COARSE_DIRT);
        registerAll("honeycomb", Blocks.HONEYCOMB_BLOCK);
        registerAll("rooted_dirt", Blocks.ROOTED_DIRT);
        registerAll("snow", Blocks.SNOW_BLOCK);

        registerAll("mushroom_stem", Blocks.MUSHROOM_STEM);
        registerAll("brown_mushroom_block", Blocks.BROWN_MUSHROOM_BLOCK);
        registerAll("red_mushroom_block", Blocks.RED_MUSHROOM_BLOCK);

        registerBlock("purpur_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PURPUR_BLOCK)));

        registerBlock("reinforced_deepslate_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.REINFORCED_DEEPSLATE)));


        registerAll("moss", Blocks.MOSS_BLOCK);
        registerAll("calcite", Blocks.CALCITE);
        registerAll("terracotta", Blocks.TERRACOTTA);
        registerAll("glowstone", Blocks.GLOWSTONE, AbstractBlock.Settings.copy(Blocks.GLOWSTONE).luminance((i) -> 15));
        registerAll("cracked_stone_bricks", Blocks.CRACKED_STONE_BRICKS);

        registerAll("tuff", Blocks.TUFF);
        registerAll("dripstone_block", Blocks.DRIPSTONE_BLOCK);

        registerAll("sea_lantern", Blocks.SEA_LANTERN, AbstractBlock.Settings.copy(Blocks.SEA_LANTERN).luminance((i) -> 15));
        registerAll("shroomlight", Blocks.SHROOMLIGHT, AbstractBlock.Settings.copy(Blocks.SHROOMLIGHT).luminance((i) -> 15));

        registerAll("end_stone", Blocks.END_STONE);

        registerAll("oak_wood", Blocks.OAK_WOOD);
        registerAll("birch_wood", Blocks.BIRCH_WOOD);
        registerAll("spruce_wood", Blocks.SPRUCE_WOOD);
        registerAll("jungle_wood", Blocks.JUNGLE_WOOD);
        registerAll("dark_oak_wood", Blocks.DARK_OAK_WOOD);
        registerAll("acacia_wood", Blocks.ACACIA_WOOD);
        registerAll("mangrove_wood", Blocks.MANGROVE_WOOD);
        registerAll("crimson_hyphae", Blocks.CRIMSON_HYPHAE);
        registerAll("warped_hyphae", Blocks.WARPED_HYPHAE);

        registerAll("stripped_oak_wood", Blocks.OAK_WOOD);
        registerAll("stripped_birch_wood", Blocks.BIRCH_WOOD);
        registerAll("stripped_spruce_wood", Blocks.SPRUCE_WOOD);
        registerAll("stripped_jungle_wood", Blocks.JUNGLE_WOOD);
        registerAll("stripped_dark_oak_wood", Blocks.DARK_OAK_WOOD);
        registerAll("stripped_acacia_wood", Blocks.ACACIA_WOOD);
        registerAll("stripped_mangrove_wood", Blocks.MANGROVE_WOOD);
        registerAll("stripped_crimson_hyphae", Blocks.CRIMSON_HYPHAE);
        registerAll("stripped_warped_hyphae", Blocks.WARPED_HYPHAE);


        registerBlock("basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

        registerBlock("basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

        registerBlock("cut_copper_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER)));
        registerBlock("exposed_cut_copper_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.EXPOSED_CUT_COPPER)));
        registerBlock("weathered_cut_copper_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.WEATHERED_CUT_COPPER)));
        registerBlock("oxidized_cut_copper_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.OXIDIZED_CUT_COPPER)));

        registerBlock("smooth_quartz_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ)));
        registerBlock("quartz_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)));

        registerBlock("polished_blackstone_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        registerBlock("polished_andesite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
        registerBlock("polished_granite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)));
        registerBlock("polished_diorite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)));

        registerBlock("smooth_sandstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE)));
        registerBlock("smooth_red_sandstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_RED_SANDSTONE)));
        registerBlock( "dark_prismarine_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE)));
        registerBlock( "prismarine_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICKS)));
        registerBlock( "stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
        registerBlock( "smooth_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
        registerBlock( "smooth_stone_stairs", new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));

        registerAll("obsidian", Blocks.OBSIDIAN, AbstractBlock.Settings.copy(Blocks.OBSIDIAN).strength(50f, 1200f));





    }


    public static void registerColorBlock(String blockName){
        for (String color: colorNames) {
            Block copyBlock = switch (blockName){
                case "concrete" -> Blocks.BLACK_CONCRETE;
                case "wool" -> Blocks.BLACK_WOOL;
                case "terracotta" -> Blocks.TERRACOTTA;
                case "stained_glass" -> Blocks.GLASS;
                default -> Blocks.DIRT;
            };
            registerAll(color + "_" + blockName, copyBlock);
        }
    }

    public static void registerAll(String name, Block block, AbstractBlock.Settings settings){
        registerBlock(name + "_stairs", new StairsBlock(block.getDefaultState(), settings));
        registerBlock(name + "_wall", new WallBlock(settings));
        registerBlock(name + "_slab", new SlabBlock(settings));

    }

    public static void registerAll(String name, Block block){
        registerBlock(name + "_stairs", new StairsBlock(block.getDefaultState(), AbstractBlock.Settings.copy(block)));
        registerBlock(name + "_wall", new WallBlock(AbstractBlock.Settings.copy(block)));
        registerBlock(name + "_slab", new SlabBlock(AbstractBlock.Settings.copy(block)));
    }


    public static void registerBlock(String name, Block block){
        String id = MoreBuildingBlocks.mod_id + ":" + name;
        Registry.register(Registry.BLOCK , id, block);
        registerItem(id, block);
        blocks.put(id, block);
    }

    public static void registerItem(String id, Block item){
        Registry.register(Registry.ITEM, id, new BlockItem(item, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
