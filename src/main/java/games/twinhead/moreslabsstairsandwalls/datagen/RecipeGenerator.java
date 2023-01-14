package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    public final List<ModBlocks> planks = List.of(new ModBlocks[]{
            ModBlocks.ACACIA_PLANKS,
            ModBlocks.BIRCH_PLANKS,
            ModBlocks.CRIMSON_PLANKS,
            ModBlocks.DARK_OAK_PLANKS,
            ModBlocks.JUNGLE_PLANKS,
            ModBlocks.OAK_PLANKS,
            ModBlocks.SPRUCE_PLANKS,
            ModBlocks.WARPED_PLANKS,
            ModBlocks.MANGROVE_PLANKS});

    public final List<ModBlocks> glass = List.of(ModBlocks.GLASS,
            ModBlocks.WHITE_STAINED_GLASS,
            ModBlocks.YELLOW_STAINED_GLASS,
            ModBlocks.BLACK_STAINED_GLASS,
            ModBlocks.RED_STAINED_GLASS,
            ModBlocks.PURPLE_STAINED_GLASS,
            ModBlocks.PINK_STAINED_GLASS,
            ModBlocks.ORANGE_STAINED_GLASS,
            ModBlocks.MAGENTA_STAINED_GLASS,
            ModBlocks.LIME_STAINED_GLASS,
            ModBlocks.LIGHT_GRAY_STAINED_GLASS,
            ModBlocks.LIGHT_BLUE_STAINED_GLASS,
            ModBlocks.GREEN_STAINED_GLASS,
            ModBlocks.GRAY_STAINED_GLASS,
            ModBlocks.CYAN_STAINED_GLASS,
            ModBlocks.BROWN_STAINED_GLASS,
            ModBlocks.BLUE_STAINED_GLASS);

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (ModBlocks block: ModBlocks.values()) {
            if(block != ModBlocks.SNOW_BLOCK){

            if(block.hasSlab()){
                //RecipeProvider.offerSlabRecipe(exporter, block.getSlabBlock(), block.getCopyBlock());
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getSlabBlock(), block.getCopyBlock(), 2);
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getSlabBlock(), 6).criterion(hasItem(block.getCopyBlock()), conditionsFromItem(block.getCopyBlock())).criterion(hasItem(block.getSlabBlock()), conditionsFromItem(block.getSlabBlock())).input('#', block.getCopyBlock()).pattern("###").group(getRecipeGroup(block, BlockType.SLAB)).offerTo(exporter);

            }
            if(block.hasStairs()){
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,block.getStairsBlock(), 4).criterion(hasItem(block.getCopyBlock()), conditionsFromItem(block.getCopyBlock())).criterion(hasItem(block.getStairsBlock()), conditionsFromItem(block.getStairsBlock())).input('#', block.getCopyBlock()).pattern("#  ").pattern("## ").pattern("###").group(getRecipeGroup(block, BlockType.STAIR)).offerTo(exporter);
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getStairsBlock(), block.getCopyBlock());
            }

            if(block.hasWall()){
                if(planks.contains(block)){
                    Block fence = switch (block){
                        case ACACIA_PLANKS -> Blocks.ACACIA_FENCE;
                        case BIRCH_PLANKS -> Blocks.BIRCH_FENCE;
                        case CRIMSON_PLANKS -> Blocks.CRIMSON_FENCE;
                        case DARK_OAK_PLANKS -> Blocks.DARK_OAK_FENCE;
                        case JUNGLE_PLANKS -> Blocks.JUNGLE_FENCE;
                        case OAK_PLANKS -> Blocks.OAK_FENCE;
                        case SPRUCE_PLANKS -> Blocks.SPRUCE_FENCE;
                        case WARPED_PLANKS -> Blocks.WARPED_FENCE;
                        case MANGROVE_PLANKS -> Blocks.MANGROVE_FENCE;
                        default -> Blocks.OAK_FENCE;
                    };
                    //RecipeProvider.offerShapelessRecipe(exporter, block.getWallBlock(), fence, "plank_walls", 1);
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getWallBlock(), 1).criterion(hasItem(block.getCopyBlock()), conditionsFromItem(block.getCopyBlock())).criterion(hasItem(block.getWallBlock()), conditionsFromItem(block.getWallBlock())).input(fence).group(getRecipeGroup(block, BlockType.WALL)).offerTo(exporter);
                } else if(!glass.contains(block)){
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getWallBlock(), 6).criterion(hasItem(block.getCopyBlock()), conditionsFromItem(block.getCopyBlock())).criterion(hasItem(block.getWallBlock()), conditionsFromItem(block.getWallBlock())).input('#', block.getCopyBlock()).pattern("###").pattern("###").group(getRecipeGroup(block, BlockType.WALL)).offerTo(exporter);
                }
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getWallBlock(), block.getCopyBlock());
            }
            }
        }
    }


    public String getRecipeGroup(ModBlocks block, BlockType blockType){
        return switch (block){
            case BLACK_WOOL,
                    WHITE_WOOL,
                    BLUE_WOOL,
                    BROWN_WOOL,
                    CYAN_WOOL,
                    GRAY_WOOL,
                    GREEN_WOOL,
                    LIGHT_BLUE_WOOL,
                    LIGHT_GRAY_WOOL,
                    LIME_WOOL,
                    MAGENTA_WOOL,
                    ORANGE_WOOL,
                    PINK_WOOL,
                    PURPLE_WOOL,
                    RED_WOOL,
                    YELLOW_WOOL-> "wool_" + blockType+ "s";
            case GLASS,
                    BLACK_STAINED_GLASS,
                    WHITE_STAINED_GLASS,
                    BLUE_STAINED_GLASS,
                    BROWN_STAINED_GLASS,
                    CYAN_STAINED_GLASS,
                    GRAY_STAINED_GLASS,
                    GREEN_STAINED_GLASS,
                    LIGHT_BLUE_STAINED_GLASS,
                    LIGHT_GRAY_STAINED_GLASS,
                    LIME_STAINED_GLASS,
                    MAGENTA_STAINED_GLASS,
                    ORANGE_STAINED_GLASS,
                    PINK_STAINED_GLASS,
                    PURPLE_STAINED_GLASS,
                    RED_STAINED_GLASS,
                    YELLOW_STAINED_GLASS-> "glass_"+ blockType+ "s";
            case TERRACOTTA,
                    BLACK_TERRACOTTA,
                    WHITE_TERRACOTTA,
                    BLUE_TERRACOTTA,
                    BROWN_TERRACOTTA,
                    CYAN_TERRACOTTA,
                    GRAY_TERRACOTTA,
                    GREEN_TERRACOTTA,
                    LIGHT_BLUE_TERRACOTTA,
                    LIGHT_GRAY_TERRACOTTA,
                    LIME_TERRACOTTA,
                    MAGENTA_TERRACOTTA,
                    ORANGE_TERRACOTTA,
                    PINK_TERRACOTTA,
                    PURPLE_TERRACOTTA,
                    RED_TERRACOTTA,
                    YELLOW_TERRACOTTA-> "terracotta_"+ blockType+ "s";
            case BLACK_CONCRETE,
                    WHITE_CONCRETE,
                    BLUE_CONCRETE,
                    BROWN_CONCRETE,
                    CYAN_CONCRETE,
                    GRAY_CONCRETE,
                    GREEN_CONCRETE,
                    LIGHT_BLUE_CONCRETE,
                    LIGHT_GRAY_CONCRETE,
                    LIME_CONCRETE,
                    MAGENTA_CONCRETE,
                    ORANGE_CONCRETE,
                    PINK_CONCRETE,
                    PURPLE_CONCRETE,
                    RED_CONCRETE,
                    YELLOW_CONCRETE-> "concrete_"+ blockType+ "s";
            case ACACIA_LEAVES,
                    AZALEA_LEAVES,
                    BIRCH_LEAVES,
                    DARK_OAK_LEAVES,
                    FLOWERING_AZALEA_LEAVES,
                    JUNGLE_LEAVES,
                    MANGROVE_LEAVES,
                    OAK_LEAVES,
                    SPRUCE_LEAVES,
                    WARPED_WART_BLOCK,
                    NETHER_WART_BLOCK -> "leaves_"+ blockType+ "s";
            case
                    WARPED_HYPHAE,
                    CRIMSON_HYPHAE,
                    ACACIA_WOOD,
                    BIRCH_WOOD,
                    DARK_OAK_WOOD,
                    JUNGLE_WOOD,
                    MANGROVE_WOOD,
                    OAK_WOOD,
                    SPRUCE_WOOD -> "wood_"+ blockType+ "s";
            case
                    STRIPPED_WARPED_HYPHAE,
                    STRIPPED_CRIMSON_HYPHAE,
                    STRIPPED_ACACIA_WOOD,
                    STRIPPED_BIRCH_WOOD,
                    STRIPPED_DARK_OAK_WOOD,
                    STRIPPED_JUNGLE_WOOD,
                    STRIPPED_MANGROVE_WOOD,
                    STRIPPED_OAK_WOOD,
                    STRIPPED_SPRUCE_WOOD -> "stripped_wood_"+ blockType+ "s";
            case
                    STRIPPED_WARPED_STEM,
                            STRIPPED_CRIMSON_STEM,
                            STRIPPED_ACACIA_LOG,
                            STRIPPED_BIRCH_LOG,
                            STRIPPED_DARK_OAK_LOG,
                            STRIPPED_JUNGLE_LOG,
                            STRIPPED_MANGROVE_LOG,
                            STRIPPED_OAK_LOG,
                            STRIPPED_SPRUCE_LOG -> "stripped_logs_"+ blockType+ "s";
            case
                    WARPED_STEM,
                    CRIMSON_STEM,
                    ACACIA_LOG,
                    BIRCH_LOG,
                    DARK_OAK_LOG,
                    JUNGLE_LOG,
                    MANGROVE_LOG,
                    OAK_LOG,
                    SPRUCE_LOG -> "log_"+ blockType+ "s";

            case OCHRE_FROGLIGHT, PEARLESCENT_FROGLIGHT, VERDANT_FROGLIGHT -> "froglight_"+ blockType+ "s";
            case CUT_COPPER,
                    EXPOSED_CUT_COPPER,
                    OXIDIZED_CUT_COPPER,
                    WAXED_CUT_COPPER,
                    WAXED_EXPOSED_CUT_COPPER,
                    WAXED_OXIDIZED_CUT_COPPER,
                    WAXED_WEATHERED_CUT_COPPER,
                    WEATHERED_CUT_COPPER,
                    COPPER_BLOCK,
                    EXPOSED_COPPER,
                    WEATHERED_COPPER,
                    OXIDIZED_COPPER,
                    WAXED_COPPER,
                    WAXED_EXPOSED_COPPER,
                    WAXED_WEATHERED_COPPER,
                    WAXED_OXIDIZED_COPPER -> "copper_"+ blockType+ "s";
            case MUSHROOM_STEM, BROWN_MUSHROOM_BLOCK, RED_MUSHROOM_BLOCK-> "mushroom_"+ blockType+ "s";
            case RAW_GOLD_BLOCK,RAW_IRON_BLOCK, RAW_COPPER_BLOCK-> "raw_"+ blockType+ "s";
            case SOUL_SAND,SOUL_SOIL ->  "soul_"+ blockType+ "s";
            case BRAIN_CORAL_BLOCK,
                    BUBBLE_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK,
                    DEAD_BRAIN_CORAL_BLOCK,
                    DEAD_BUBBLE_CORAL_BLOCK,
                    DEAD_FIRE_CORAL_BLOCK,
                    DEAD_HORN_CORAL_BLOCK,
                    DEAD_TUBE_CORAL_BLOCK-> "coral_"+ blockType+ "s";

            case GRASS_BLOCK,
                    MYCELIUM,
                    PODZOL,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> "grass_"+ blockType+ "s";
            case ACACIA_PLANKS,
                    BIRCH_PLANKS,
                    CRIMSON_PLANKS,
                    DARK_OAK_PLANKS,
                    JUNGLE_PLANKS,
                    MANGROVE_PLANKS,
                    OAK_PLANKS,
                    SPRUCE_PLANKS,
                    WARPED_PLANKS -> "plank_"+ blockType+ "s";
            case COAL_BLOCK,
                    IRON_BLOCK,
                    GOLD_BLOCK,
                    DIAMOND_BLOCK,
                    EMERALD_BLOCK,
                    LAPIS_BLOCK,
                    NETHERITE_BLOCK -> "metal_"+ blockType+ "s";
            case ICE, BLUE_ICE, PACKED_ICE -> "ice_"+ blockType+ "s";


            default -> block +"_"+ blockType;
        };
    }

    enum BlockType{
        SLAB,
        STAIR,
        WALL;

        public String toString(){
            return this.name().toLowerCase();
        }
    }


}
