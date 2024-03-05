package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }



    public static final List<ModBlocks> planks = List.of(ModBlocks.ACACIA_PLANKS,
            ModBlocks.BIRCH_PLANKS,
            ModBlocks.CRIMSON_PLANKS,
            ModBlocks.DARK_OAK_PLANKS,
            ModBlocks.JUNGLE_PLANKS,
            ModBlocks.OAK_PLANKS,
            ModBlocks.SPRUCE_PLANKS,
            ModBlocks.WARPED_PLANKS,
            ModBlocks.MANGROVE_PLANKS,
            ModBlocks.CHERRY_PLANKS,
            ModBlocks.BAMBOO_PLANKS);

    private final List<ModBlocks> copper = List.of(
            ModBlocks.COPPER_BLOCK,
            ModBlocks.OXIDIZED_COPPER,
            ModBlocks.WEATHERED_COPPER,
            ModBlocks.EXPOSED_COPPER,
            ModBlocks.WAXED_COPPER_BLOCK,
            ModBlocks.WAXED_OXIDIZED_COPPER,
            ModBlocks.WAXED_WEATHERED_COPPER,
            ModBlocks.WAXED_EXPOSED_COPPER
    );

    private final List<ModBlocks> waxedCopper = List.of(
            ModBlocks.WAXED_COPPER_BLOCK,
            ModBlocks.WAXED_OXIDIZED_COPPER,
            ModBlocks.WAXED_WEATHERED_COPPER,
            ModBlocks.WAXED_EXPOSED_COPPER,
            ModBlocks.WAXED_CUT_COPPER,
            ModBlocks.WAXED_OXIDIZED_CUT_COPPER,
            ModBlocks.WAXED_WEATHERED_CUT_COPPER,
            ModBlocks.WAXED_EXPOSED_CUT_COPPER
    );

    private final List<ModBlocks> glass = List.of(ModBlocks.GLASS,
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



    public static final List<ModBlocks> logBlocks = List.of(
            ModBlocks.ACACIA_LOG,
            ModBlocks.BIRCH_LOG,
            ModBlocks.CRIMSON_STEM,
            ModBlocks.DARK_OAK_LOG,
            ModBlocks.JUNGLE_LOG,
            ModBlocks.OAK_LOG,
            ModBlocks.SPRUCE_LOG,
            ModBlocks.WARPED_STEM,
            ModBlocks.MANGROVE_LOG,
            ModBlocks.CHERRY_LOG,
            ModBlocks.BAMBOO_BLOCK,
            ModBlocks.STRIPPED_ACACIA_LOG,
            ModBlocks.STRIPPED_BIRCH_LOG,
            ModBlocks.STRIPPED_CRIMSON_STEM,
            ModBlocks.STRIPPED_DARK_OAK_LOG,
            ModBlocks.STRIPPED_JUNGLE_LOG,
            ModBlocks.STRIPPED_OAK_LOG,
            ModBlocks.STRIPPED_SPRUCE_LOG,
            ModBlocks.STRIPPED_WARPED_STEM,
            ModBlocks.STRIPPED_MANGROVE_LOG,
            ModBlocks.STRIPPED_CHERRY_LOG,
            ModBlocks.STRIPPED_BAMBOO_BLOCK,
            ModBlocks.ACACIA_WOOD,
            ModBlocks.BIRCH_WOOD,
            ModBlocks.CRIMSON_HYPHAE,
            ModBlocks.DARK_OAK_WOOD,
            ModBlocks.JUNGLE_WOOD,
            ModBlocks.OAK_WOOD,
            ModBlocks.SPRUCE_WOOD,
            ModBlocks.WARPED_HYPHAE,
            ModBlocks.MANGROVE_WOOD,
            ModBlocks.CHERRY_WOOD,
            ModBlocks.STRIPPED_ACACIA_WOOD,
            ModBlocks.STRIPPED_BIRCH_WOOD,
            ModBlocks.STRIPPED_CRIMSON_HYPHAE,
            ModBlocks.STRIPPED_DARK_OAK_WOOD,
            ModBlocks.STRIPPED_JUNGLE_WOOD,
            ModBlocks.STRIPPED_OAK_WOOD,
            ModBlocks.STRIPPED_SPRUCE_WOOD,
            ModBlocks.STRIPPED_WARPED_HYPHAE,
            ModBlocks.STRIPPED_MANGROVE_WOOD,
            ModBlocks.STRIPPED_CHERRY_WOOD
    );

    List<Item> dyes = List.of(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE);

    private final List<ModBlocks> wool = List.of(
            ModBlocks.BLACK_WOOL,
            ModBlocks.BLUE_WOOL,
            ModBlocks.BROWN_WOOL,
            ModBlocks.CYAN_WOOL,
            ModBlocks.GRAY_WOOL,
            ModBlocks.GREEN_WOOL,
            ModBlocks.LIGHT_BLUE_WOOL,
            ModBlocks.LIGHT_GRAY_WOOL,
            ModBlocks.LIME_WOOL,
            ModBlocks.MAGENTA_WOOL,
            ModBlocks.ORANGE_WOOL,
            ModBlocks.PINK_WOOL,
            ModBlocks.PURPLE_WOOL,
            ModBlocks.RED_WOOL,
            ModBlocks.YELLOW_WOOL,
            ModBlocks.WHITE_WOOL
    );

    private final List<ModBlocks> terracotta = List.of(
            ModBlocks.BLACK_TERRACOTTA,
            ModBlocks.BLUE_TERRACOTTA,
            ModBlocks.BROWN_TERRACOTTA,
            ModBlocks.CYAN_TERRACOTTA,
            ModBlocks.GRAY_TERRACOTTA,
            ModBlocks.GREEN_TERRACOTTA,
            ModBlocks.LIGHT_BLUE_TERRACOTTA,
            ModBlocks.LIGHT_GRAY_TERRACOTTA,
            ModBlocks.LIME_TERRACOTTA,
            ModBlocks.MAGENTA_TERRACOTTA,
            ModBlocks.ORANGE_TERRACOTTA,
            ModBlocks.PINK_TERRACOTTA,
            ModBlocks.PURPLE_TERRACOTTA,
            ModBlocks.RED_TERRACOTTA,
            ModBlocks.YELLOW_TERRACOTTA,
            ModBlocks.WHITE_TERRACOTTA
    );

    private final List<ModBlocks> glazedTerracotta = List.of(
            ModBlocks.BLACK_GLAZED_TERRACOTTA,
            ModBlocks.BLUE_GLAZED_TERRACOTTA,
            ModBlocks.BROWN_GLAZED_TERRACOTTA,
            ModBlocks.CYAN_GLAZED_TERRACOTTA,
            ModBlocks.GRAY_GLAZED_TERRACOTTA,
            ModBlocks.GREEN_GLAZED_TERRACOTTA,
            ModBlocks.LIGHT_BLUE_GLAZED_TERRACOTTA,
            ModBlocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
            ModBlocks.LIME_GLAZED_TERRACOTTA,
            ModBlocks.MAGENTA_GLAZED_TERRACOTTA,
            ModBlocks.ORANGE_GLAZED_TERRACOTTA,
            ModBlocks.PINK_GLAZED_TERRACOTTA,
            ModBlocks.PURPLE_GLAZED_TERRACOTTA,
            ModBlocks.RED_GLAZED_TERRACOTTA,
            ModBlocks.YELLOW_GLAZED_TERRACOTTA,
            ModBlocks.WHITE_GLAZED_TERRACOTTA
    );

    @Override
    public void generate(RecipeExporter exporter) {

        addWaxedCopperRecipes(exporter);
        addLogToPlankRecipes(exporter);
        addSmeltingRecipes(exporter);
        addUncraftingRecipes(exporter);
        addMiscRecipes(exporter);

        for (ModBlocks block: ModBlocks.values()) {
            if(block.hasBlock(ModBlocks.BlockType.SLAB)){
                if (block.equals(ModBlocks.SNOW_BLOCK)) {
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.SLAB), 1).criterion(hasItem(Blocks.SNOW), conditionsFromItem(Blocks.SNOW)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.SLAB)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.SLAB))).input(Blocks.SNOW).input(Blocks.SNOW).group(getRecipeGroup(block, ModBlocks.BlockType.SLAB)).offerTo(exporter);
                } else {
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.SLAB), 6).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.SLAB)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.SLAB))).input('#', block.parentBlock).pattern("###").group(getRecipeGroup(block, ModBlocks.BlockType.SLAB)).offerTo(exporter);
                }
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.SLAB), block.parentBlock, 2);

            }

            if(block.hasBlock(ModBlocks.BlockType.STAIRS)){
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.STAIRS), block.parentBlock, 1);
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.STAIRS), 4).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.STAIRS)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.STAIRS))).input('#', block.parentBlock).pattern("#  ").pattern("## ").pattern("###").group(getRecipeGroup(block, ModBlocks.BlockType.STAIRS)).offerTo(exporter);
            }

            if(block.hasBlock(ModBlocks.BlockType.WALL)){
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
                        case CHERRY_PLANKS -> Blocks.CHERRY_FENCE;
                        case BAMBOO_PLANKS -> Blocks.BAMBOO_FENCE;
                        default -> Blocks.OAK_FENCE;
                    };
                    //RecipeProvider.offerShapelessRecipe(exporter, block.getWallBlock(), fence, "plank_walls", 1);
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.WALL), 1).criterion(hasItem(fence), conditionsFromItem(fence)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL))).input(fence).group(getRecipeGroup(block, ModBlocks.BlockType.WALL)).offerTo(exporter);
                } else if(copper.contains(block)){
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.WALL), 2).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL))).input('#', block.parentBlock).pattern("#").pattern("#").group(getRecipeGroup(block, ModBlocks.BlockType.WALL)).offerTo(exporter);
                } else if(!glass.contains(block)){
                    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.WALL), 6).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL))).input('#', block.parentBlock).pattern("###").pattern("###").group(getRecipeGroup(block, ModBlocks.BlockType.WALL)).offerTo(exporter);
                }
                RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.WALL), block.parentBlock);
            }
        }
    }

    private void addMiscRecipes(RecipeExporter exporter){
        addCoarseDirtRecipe(exporter, ModBlocks.BlockType.SLAB);
        addCoarseDirtRecipe(exporter, ModBlocks.BlockType.STAIRS);
        addCoarseDirtRecipe(exporter, ModBlocks.BlockType.WALL);


        offerDyeableRecipes(exporter, dyes, List.copyOf(wool).stream().map(block -> block.getBlock(ModBlocks.BlockType.SLAB).asItem()).toList(), "wool_slabs_dying");
        offerDyeableRecipes(exporter, dyes, List.copyOf(wool).stream().map(block -> block.getBlock(ModBlocks.BlockType.STAIRS).asItem()).toList(), "wool_stairs_dying");
        offerDyeableRecipes(exporter, dyes, List.copyOf(wool).stream().map(block -> block.getBlock(ModBlocks.BlockType.WALL).asItem()).toList(), "wool_walls_dying");

        offerTerracottaSlabStairAndWalls(exporter);
    }

    private void offerTerracottaSlabStairAndWalls(RecipeExporter exporter){
        for (int i = 0; i < dyes.size(); i++) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()){
                offerTerracottaDyeingRecipe(exporter, type, terracotta.get(i), dyes.get(i));
            }
        }
    }

    private void offerTerracottaDyeingRecipe(RecipeExporter exporter, ModBlocks.BlockType type, ModBlocks output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output.getBlock(type), 8)
                .input('#', ModBlocks.TERRACOTTA.getBlock(type))
                .input('X', input)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("stained_terracotta_" + type.toString().toLowerCase())
                .criterion("has_terracotta_" + type.toString().toLowerCase(), RecipeProvider.conditionsFromItem(ModBlocks.TERRACOTTA.getBlock(type)))
                .offerTo(exporter, output.toString().toLowerCase() + "_" + type.toString().toLowerCase() + "_from_terracotta_and_dye");
    }

    private void addCoarseDirtRecipe(RecipeExporter exporter, ModBlocks.BlockType type){
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COARSE_DIRT.getBlock(type), 4)
                .criterion(hasItem(ModBlocks.GRAVEL.getBlock(type)), conditionsFromItem(ModBlocks.GRAVEL.getBlock(type)))
                .criterion(hasItem(ModBlocks.DIRT.getBlock(type)), conditionsFromItem(ModBlocks.DIRT.getBlock(type)))
                .input('#', ModBlocks.GRAVEL.getBlock(type))
                .input('&', ModBlocks.DIRT.getBlock(type))
                .pattern("&#")
                .pattern("#&")
                .offerTo(exporter , ModBlocks.COARSE_DIRT.toString().toLowerCase() + "_" + type.toString().toLowerCase() + "_from_gravel_and_dirt");
    }

    private void addSmeltingRecipes(RecipeExporter exporter){
        addSmeltingRecipe(exporter,null, ModBlocks.CRACKED_STONE_BRICKS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_WALL);
        addSmeltingRecipe(exporter,null, ModBlocks.CRACKED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_WALL);
        addSmeltingRecipe(exporter,null, ModBlocks.CRACKED_DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_WALL);
        addSmeltingRecipe(exporter,null, ModBlocks.CRACKED_NETHER_BRICKS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_WALL);


        addSmeltingRecipe(exporter,null, ModBlocks.SMOOTH_BASALT, ModBlocks.BASALT);
        addSmeltingRecipe(exporter,null, ModBlocks.TERRACOTTA, ModBlocks.CLAY);
        addSmeltingRecipe(exporter,"_sand", ModBlocks.GLASS, ModBlocks.SAND);
        addSmeltingRecipe(exporter,"_red_sand", ModBlocks.GLASS, ModBlocks.RED_SAND);

        addSmeltingRecipe(exporter,"_sandstone", ModBlocks.SMOOTH_SANDSTONE, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE_WALL);
        addSmeltingRecipe(exporter,"_red_sandstone", ModBlocks.SMOOTH_RED_SANDSTONE, Blocks.RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE_WALL);


        for (int i = 0; i < terracotta.size(); i++) {
            addSmeltingRecipe(exporter, "_" + glazedTerracotta.get(i).toString().toLowerCase(), glazedTerracotta.get(i), terracotta.get(i));
        }


    }

    private void addSmeltingRecipe(RecipeExporter exporter, @Nullable String suffix, ModBlocks output, ModBlocks input){
        addSmeltingRecipe(exporter, suffix, output, input.getBlock(ModBlocks.BlockType.SLAB), input.getBlock(ModBlocks.BlockType.STAIRS), input.getBlock(ModBlocks.BlockType.WALL));
    }

    private void addSmeltingRecipe(RecipeExporter exporter, @Nullable String suffix, ModBlocks block, Block inputSlab, Block inputStairs, Block inputWall){
        if (block.hasBlock(ModBlocks.BlockType.SLAB))
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(inputSlab), RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.SLAB), 0.1F, 200)
                .criterion(hasItem(inputSlab), conditionsFromItem(inputSlab)).offerTo(exporter, block.getId(ModBlocks.BlockType.SLAB).toString()+"_from_smelting"+ suffix);
        if (block.hasBlock(ModBlocks.BlockType.STAIRS))
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(inputStairs), RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.STAIRS), 0.1F, 200)
                .criterion(hasItem(inputStairs), conditionsFromItem(inputStairs)).offerTo(exporter, block.getId(ModBlocks.BlockType.STAIRS).toString()+"_from_smelting"+ suffix);
        if (block.hasBlock(ModBlocks.BlockType.WALL))
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(inputWall), RecipeCategory.BUILDING_BLOCKS, block.getBlock(ModBlocks.BlockType.WALL), 0.1F, 200)
                .criterion(hasItem(inputWall), conditionsFromItem(inputWall)).offerTo(exporter, block.getId(ModBlocks.BlockType.WALL).toString()+"_from_smelting"+ suffix);
    }

    private void addUncraftingRecipes(RecipeExporter exporter){
        addRawBlock(ModBlocks.RAW_COPPER_BLOCK, Items.RAW_COPPER, exporter);
        addRawBlock(ModBlocks.RAW_IRON_BLOCK, Items.RAW_IRON, exporter);
        addRawBlock(ModBlocks.RAW_GOLD_BLOCK, Items.RAW_GOLD, exporter);

        addRawBlock(ModBlocks.COPPER_BLOCK, Items.COPPER_INGOT, exporter);
        addRawBlock(ModBlocks.IRON_BLOCK, Items.IRON_INGOT, exporter);
        addRawBlock(ModBlocks.GOLD_BLOCK, Items.GOLD_INGOT, exporter);
        addRawBlock(ModBlocks.DIAMOND_BLOCK, Items.DIAMOND, exporter);
        addRawBlock(ModBlocks.EMERALD_BLOCK, Items.EMERALD, exporter);
        addRawBlock(ModBlocks.LAPIS_BLOCK, Items.LAPIS_LAZULI, exporter);
        addRawBlock(ModBlocks.COAL_BLOCK, Items.COAL, exporter);
        addRawBlock(ModBlocks.REDSTONE_BLOCK, Items.REDSTONE, exporter);
        addRawBlock(ModBlocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT, exporter);
        addRawBlock(ModBlocks.HAY_BLOCK, Items.WHEAT, exporter);
        addRawBlock(ModBlocks.DRIED_KELP_BLOCK, Items.DRIED_KELP, exporter);
        addRawBlock(ModBlocks.BONE_BLOCK, Items.BONE_MEAL, exporter);
        addRawBlock(ModBlocks.SLIME_BLOCK, Items.SLIME_BALL, exporter);
    }

    private void addRawBlock(ModBlocks block, Item ouput, RecipeExporter exporter){

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ouput, 9)
                .input(block.getBlock(ModBlocks.BlockType.SLAB)).group(getRecipeGroup(block, ModBlocks.BlockType.SLAB))
                .input(block.getBlock(ModBlocks.BlockType.SLAB)).group(getRecipeGroup(block, ModBlocks.BlockType.SLAB))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.SLAB)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.SLAB)))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.SLAB)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.SLAB)))
                .offerTo(exporter, block.getId(ModBlocks.BlockType.SLAB).toString()+"_uncrafting");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ouput, 9)
                .input(block.getBlock(ModBlocks.BlockType.STAIRS)).group(getRecipeGroup(block, ModBlocks.BlockType.STAIRS))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.STAIRS)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.STAIRS)))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.STAIRS)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.STAIRS)))
                .offerTo(exporter,block.getId(ModBlocks.BlockType.STAIRS).toString()+"_uncrafting");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ouput, 9)
                .input(block.getBlock(ModBlocks.BlockType.WALL)).group(getRecipeGroup(block, ModBlocks.BlockType.WALL))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL)))
                .criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL)))
                .offerTo(exporter, block.getId(ModBlocks.BlockType.WALL).toString()+"_uncrafting");
    }


    public void addWaxedCopperRecipes(RecipeExporter exporter){
        for (ModBlocks block: waxedCopper) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (block.hasBlock(type))
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, block.getBlock(type), 1).criterion(hasItem(block.associatedBlock.parentBlock), conditionsFromItem(block.associatedBlock.parentBlock)).criterion(hasItem(block.associatedBlock.getBlock(type)), conditionsFromItem(block.associatedBlock.getBlock(type))).input(block.associatedBlock.getBlock(type)).input(Items.HONEYCOMB).group("more_waxed_copper").offerTo(exporter, block.toString() + "_" + type.toString().toLowerCase() + "_honeycomb");
            }
        }
    }

    public void addLogToPlankRecipes(RecipeExporter exporter){
        for (ModBlocks block: logTypes) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                if (block.hasBlock(type))
                    addPlankRecipe(block, type, type == ModBlocks.BlockType.SLAB ? 2 : 4, exporter);
        }
    }

    private void addPlankRecipe(ModBlocks block, ModBlocks.BlockType type, int outputCount, RecipeExporter exporter){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, getPlank(block), outputCount).criterion(hasItem(block.getBlock(type)), conditionsFromItem(block.getBlock(type))).input(ModTags.getLogTagKey(block, type)).group("more_planks").offerTo(exporter, block.toString() + "_" + type.toString().toLowerCase() + "_to_planks");
    }

    public static final List<ModBlocks> logTypes = List.of(
            ModBlocks.ACACIA_LOG,
            ModBlocks.BIRCH_LOG,
            ModBlocks.CRIMSON_STEM,
            ModBlocks.DARK_OAK_LOG,
            ModBlocks.JUNGLE_LOG,
            ModBlocks.OAK_LOG,
            ModBlocks.SPRUCE_LOG,
            ModBlocks.WARPED_STEM,
            ModBlocks.MANGROVE_LOG,
            ModBlocks.CHERRY_LOG,
            ModBlocks.BAMBOO_BLOCK

    );

    private Block getPlank(ModBlocks blocks){
        return switch (blocks){
            case ACACIA_LOG, STRIPPED_ACACIA_LOG, ACACIA_WOOD, STRIPPED_ACACIA_WOOD -> Blocks.ACACIA_PLANKS;
            case BIRCH_LOG, STRIPPED_BIRCH_LOG, BIRCH_WOOD, STRIPPED_BIRCH_WOOD -> Blocks.BIRCH_PLANKS;
            case CRIMSON_STEM , STRIPPED_CRIMSON_STEM, CRIMSON_HYPHAE, STRIPPED_CRIMSON_HYPHAE -> Blocks.CRIMSON_PLANKS;
            case DARK_OAK_LOG, STRIPPED_DARK_OAK_LOG, DARK_OAK_WOOD, STRIPPED_DARK_OAK_WOOD -> Blocks.DARK_OAK_PLANKS;
            case JUNGLE_LOG, STRIPPED_JUNGLE_LOG, JUNGLE_WOOD, STRIPPED_JUNGLE_WOOD -> Blocks.JUNGLE_PLANKS;
            case OAK_LOG, STRIPPED_OAK_LOG, OAK_WOOD, STRIPPED_OAK_WOOD -> Blocks.OAK_PLANKS;
            case SPRUCE_LOG, STRIPPED_SPRUCE_LOG, SPRUCE_WOOD, STRIPPED_SPRUCE_WOOD -> Blocks.SPRUCE_PLANKS;
            case WARPED_STEM, STRIPPED_WARPED_STEM, WARPED_HYPHAE, STRIPPED_WARPED_HYPHAE -> Blocks.WARPED_PLANKS;
            case MANGROVE_LOG, STRIPPED_MANGROVE_LOG, MANGROVE_WOOD, STRIPPED_MANGROVE_WOOD -> Blocks.MANGROVE_PLANKS;
            case CHERRY_LOG, STRIPPED_CHERRY_LOG, CHERRY_WOOD, STRIPPED_CHERRY_WOOD -> Blocks.CHERRY_PLANKS;
            case BAMBOO_BLOCK, STRIPPED_BAMBOO_BLOCK -> Blocks.BAMBOO_PLANKS;
            default -> throw new IllegalStateException("Unexpected value: " + blocks);
        };
    }

    private String getRecipeGroup(ModBlocks block, ModBlocks.BlockType blockType){
        String type = blockType.toString().toLowerCase();
        return switch (block){
            case ACACIA_LOG, STRIPPED_ACACIA_LOG,
                BIRCH_LOG, STRIPPED_BIRCH_LOG,
                CRIMSON_STEM , STRIPPED_CRIMSON_STEM,
                DARK_OAK_LOG, STRIPPED_DARK_OAK_LOG,
                JUNGLE_LOG, STRIPPED_JUNGLE_LOG,
                OAK_LOG, STRIPPED_OAK_LOG,
                SPRUCE_LOG, STRIPPED_SPRUCE_LOG,
                WARPED_STEM, STRIPPED_WARPED_STEM,
                MANGROVE_LOG, STRIPPED_MANGROVE_LOG,
                CHERRY_LOG, STRIPPED_CHERRY_LOG,
                BAMBOO_BLOCK, STRIPPED_BAMBOO_BLOCK -> "log_" + type;
            case ACACIA_WOOD, STRIPPED_ACACIA_WOOD,
                BIRCH_WOOD, STRIPPED_BIRCH_WOOD,
                CRIMSON_HYPHAE, STRIPPED_CRIMSON_HYPHAE,
                DARK_OAK_WOOD, STRIPPED_DARK_OAK_WOOD,
                JUNGLE_WOOD, STRIPPED_JUNGLE_WOOD,
                OAK_WOOD, STRIPPED_OAK_WOOD,
                SPRUCE_WOOD, STRIPPED_SPRUCE_WOOD,
                WARPED_HYPHAE, STRIPPED_WARPED_HYPHAE,
                MANGROVE_WOOD, STRIPPED_MANGROVE_WOOD,
                CHERRY_WOOD, STRIPPED_CHERRY_WOOD  -> "bark_" + type;
            case ACACIA_LEAVES,
                    BIRCH_LEAVES,
                    DARK_OAK_LEAVES,
                    JUNGLE_LEAVES,
                    OAK_LEAVES,
                    SPRUCE_LEAVES,
                    MANGROVE_LEAVES,
                    CHERRY_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES-> "leaves_" + type;
            case BLACK_STAINED_GLASS,
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
                WHITE_STAINED_GLASS,
                YELLOW_STAINED_GLASS -> "stained_glass_"+type;
            case BLACK_WOOL,
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
                WHITE_WOOL,
                YELLOW_WOOL -> "wool_"+type;
            case BLACK_CONCRETE,
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
                WHITE_CONCRETE,
                YELLOW_CONCRETE -> "concrete_"+type;
            case BLACK_TERRACOTTA,
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
                    WHITE_TERRACOTTA,
                    YELLOW_TERRACOTTA -> "terracotta_"+type;
            case BLACK_GLAZED_TERRACOTTA,
                    BLUE_GLAZED_TERRACOTTA,
                    BROWN_GLAZED_TERRACOTTA,
                    CYAN_GLAZED_TERRACOTTA,
                    GRAY_GLAZED_TERRACOTTA,
                    GREEN_GLAZED_TERRACOTTA,
                    LIGHT_BLUE_GLAZED_TERRACOTTA,
                    LIGHT_GRAY_GLAZED_TERRACOTTA,
                    LIME_GLAZED_TERRACOTTA,
                    MAGENTA_GLAZED_TERRACOTTA,
                    ORANGE_GLAZED_TERRACOTTA,
                    PINK_GLAZED_TERRACOTTA,
                    PURPLE_GLAZED_TERRACOTTA,
                    RED_GLAZED_TERRACOTTA,
                    WHITE_GLAZED_TERRACOTTA,
                    YELLOW_GLAZED_TERRACOTTA -> "glazed_terracotta_"+type;
            case
                BLACK_CONCRETE_POWDER,
                BLUE_CONCRETE_POWDER,
                BROWN_CONCRETE_POWDER,
                CYAN_CONCRETE_POWDER,
                GRAY_CONCRETE_POWDER,
                GREEN_CONCRETE_POWDER,
                LIGHT_BLUE_CONCRETE_POWDER,
                LIGHT_GRAY_CONCRETE_POWDER,
                LIME_CONCRETE_POWDER,
                MAGENTA_CONCRETE_POWDER,
                ORANGE_CONCRETE_POWDER,
                PINK_CONCRETE_POWDER,
                PURPLE_CONCRETE_POWDER,
                RED_CONCRETE_POWDER,
                WHITE_CONCRETE_POWDER,
                YELLOW_CONCRETE_POWDER -> "concrete_powder_"+type;
            case
                WARPED_WART,
                CRIMSON_WART-> "wart_"+type;
            case
                RAW_COPPER_BLOCK,
                RAW_GOLD_BLOCK,
                RAW_IRON_BLOCK -> "raw_metal_"+type;
            case
                    BRAIN_CORAL_BLOCK,
                    BUBBLE_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> "coral_block_"+type;
            case
                    DEAD_BRAIN_CORAL_BLOCK,
                    DEAD_BUBBLE_CORAL_BLOCK,
                    DEAD_FIRE_CORAL_BLOCK,
                    DEAD_HORN_CORAL_BLOCK,
                    DEAD_TUBE_CORAL_BLOCK -> "dead_coral_block_"+type;
            case
                    OCHRE_FROGLIGHT,
                    PEARLESCENT_FROGLIGHT,
                    VERDANT_FROGLIGHT -> "froglight_"+type;
            case
                    ICE,
                    PACKED_ICE,
                    BLUE_ICE -> "ice_"+type;
            case
                    ACACIA_PLANKS,
                    BIRCH_PLANKS,
                    CRIMSON_PLANKS,
                    DARK_OAK_PLANKS,
                    JUNGLE_PLANKS,
                    OAK_PLANKS,
                    SPRUCE_PLANKS,
                    WARPED_PLANKS,
                    MANGROVE_PLANKS,
                    CHERRY_PLANKS,
                    BAMBOO_PLANKS -> "planks_"+type;


            default -> block+"_"+type;
        };
    }
}
