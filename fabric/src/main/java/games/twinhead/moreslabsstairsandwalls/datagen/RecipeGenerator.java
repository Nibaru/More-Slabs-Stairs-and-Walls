package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        for (ModBlocks block : ModBlocks.values()) {
            if (block.hasBlock(ModBlocks.BlockType.SLAB)) {
                var slab = block.getBlock(ModBlocks.BlockType.SLAB);
                RecipeProvider.stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, block.parentBlock, 2);
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                        .define('#', block.parentBlock)
                        .pattern("###")
                        .unlockedBy(RecipeProvider.getHasName(block.parentBlock), RecipeProvider.has(block.parentBlock))
                        .unlockedBy(RecipeProvider.getHasName(slab), RecipeProvider.has(slab))
                        .group("more_slabs")
                        .save(recipeOutput, shapedSlabId(slab));
            }

            if (block.hasBlock(ModBlocks.BlockType.STAIRS)) {
                var stairs = block.getBlock(ModBlocks.BlockType.STAIRS);
                RecipeProvider.stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stairs, block.parentBlock, 1);
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                        .define('#', block.parentBlock)
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .unlockedBy(RecipeProvider.getHasName(block.parentBlock), RecipeProvider.has(block.parentBlock))
                        .unlockedBy(RecipeProvider.getHasName(stairs), RecipeProvider.has(stairs))
                        .group("more_stairs")
                        .save(recipeOutput, shapedStairsId(stairs));
            }

            if (block.hasBlock(ModBlocks.BlockType.WALL)) {
                var wall = block.getBlock(ModBlocks.BlockType.WALL);
                if (isPlanksWallBlock(block)) {
                    Block fence = fenceForPlankParent(block.parentBlock);
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, wall, 1)
                            .requires(fence)
                            .unlockedBy(RecipeProvider.getHasName(fence), RecipeProvider.has(fence))
                            .unlockedBy(RecipeProvider.getHasName(wall), RecipeProvider.has(wall))
                            .group("more_walls")
                            .save(recipeOutput, wallFromFenceId(wall));
                } else if (block.modelType != ModBlocks.ModelType.GLASS) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
                            .define('#', block.parentBlock)
                            .pattern("###")
                            .pattern("###")
                            .unlockedBy(RecipeProvider.getHasName(block.parentBlock), RecipeProvider.has(block.parentBlock))
                            .unlockedBy(RecipeProvider.getHasName(wall), RecipeProvider.has(wall))
                            .group("more_walls")
                            .save(recipeOutput, shapedWallId(wall));
                }
                RecipeProvider.stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, block.parentBlock);
            }

            if (getSmeltingOutput(block) != null) {
                buildSmeltingRecipesForSmeltingPair(recipeOutput, block, getSmeltingOutput(block));
            }

            if (getLogPlankOutput(block) != null) {
                buildLogShapeToPlankRecipes(recipeOutput, block);
            }
        }

        buildWoolDyeRecipes(recipeOutput);
    }

    private static String itemPath(net.minecraft.world.level.block.Block block) {
        return BuiltInRegistries.ITEM.getKey(block.asItem()).getPath();
    }

    private ResourceLocation shapedSlabId(net.minecraft.world.level.block.Block slab) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(slab) + "_shaped"));
    }

    private ResourceLocation shapedStairsId(net.minecraft.world.level.block.Block stairs) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(stairs) + "_shaped"));
    }

    private ResourceLocation shapedWallId(net.minecraft.world.level.block.Block wall) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(wall) + "_shaped"));
    }

    private ResourceLocation wallFromFenceId(net.minecraft.world.level.block.Block wall) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(wall) + "_from_fence"));
    }

    /**
     * Plank-derived walls use fence → wall; driven by vanilla {@link BlockTags#PLANKS} so new plank entries
     * (same shape as {@code .wallOnly()} on a planks parent) do not need listing here.
     */
    private static boolean isPlanksWallBlock(ModBlocks block) {
        return !block.hasBlock(ModBlocks.BlockType.SLAB)
                && !block.hasBlock(ModBlocks.BlockType.STAIRS)
                && block.parentBlock.defaultBlockState().is(BlockTags.PLANKS);
    }

    private static Block fenceForPlankParent(Block planks) {
        if (planks == Blocks.ACACIA_PLANKS) return Blocks.ACACIA_FENCE;
        if (planks == Blocks.BIRCH_PLANKS) return Blocks.BIRCH_FENCE;
        if (planks == Blocks.CRIMSON_PLANKS) return Blocks.CRIMSON_FENCE;
        if (planks == Blocks.DARK_OAK_PLANKS) return Blocks.DARK_OAK_FENCE;
        if (planks == Blocks.JUNGLE_PLANKS) return Blocks.JUNGLE_FENCE;
        if (planks == Blocks.OAK_PLANKS) return Blocks.OAK_FENCE;
        if (planks == Blocks.SPRUCE_PLANKS) return Blocks.SPRUCE_FENCE;
        if (planks == Blocks.WARPED_PLANKS) return Blocks.WARPED_FENCE;
        if (planks == Blocks.MANGROVE_PLANKS) return Blocks.MANGROVE_FENCE;
        if (planks == Blocks.CHERRY_PLANKS) return Blocks.CHERRY_FENCE;
        if (planks == Blocks.BAMBOO_PLANKS) return Blocks.BAMBOO_FENCE;
        return Blocks.OAK_FENCE;
    }

    /**
     * Shapeless recipes: log/stem slab, stairs, or wall into vanilla planks (2 from slab, 4 from stairs or wall).
     * Plank type comes from {@link #getLogPlankOutput(ModBlocks)} (parent block of the wall-only plank entry).
     */
    private void buildLogShapeToPlankRecipes(RecipeOutput recipeOutput, ModBlocks logBlock) {
        ModBlocks plankEntry = getLogPlankOutput(logBlock);
        if (plankEntry == null) {
            return;
        }
        Block planks = plankEntry.parentBlock;

        if (logBlock.hasBlock(ModBlocks.BlockType.SLAB)) {
            Block slab = logBlock.getBlock(ModBlocks.BlockType.SLAB);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 2)
                    .requires(slab)
                    .unlockedBy(RecipeProvider.getHasName(slab), RecipeProvider.has(slab))
                    .group("more_log_planks")
                    .save(recipeOutput, logShapeToPlanksId(slab));
        }
        if (logBlock.hasBlock(ModBlocks.BlockType.STAIRS)) {
            Block stairs = logBlock.getBlock(ModBlocks.BlockType.STAIRS);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                    .requires(stairs)
                    .unlockedBy(RecipeProvider.getHasName(stairs), RecipeProvider.has(stairs))
                    .group("more_log_planks")
                    .save(recipeOutput, logShapeToPlanksId(stairs));
        }
        if (logBlock.hasBlock(ModBlocks.BlockType.WALL)) {
            Block wall = logBlock.getBlock(ModBlocks.BlockType.WALL);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                    .requires(wall)
                    .unlockedBy(RecipeProvider.getHasName(wall), RecipeProvider.has(wall))
                    .group("more_log_planks")
                    .save(recipeOutput, logShapeToPlanksId(wall));
        }
    }

    private ResourceLocation logShapeToPlanksId(Block logShape) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(logShape) + "_to_planks"));
    }

    /**
     * Shapeless dye recipes: any wool slab/stairs/wall (see {@link ModTags#WOOL_SLABS_ITEMS} etc.) + matching dye →
     * that colored wool shape (1:1), including re-dyeing and dyeing to white with white dye.
     */
    private void buildWoolDyeRecipes(RecipeOutput recipeOutput) {
        for (ModBlocks coloredWool : ModBlocks.values()) {
            if (!isWoolModBlock(coloredWool)) {
                continue;
            }
            Item dye = getDyeItemForColoredWool(coloredWool);
            if (dye == null) {
                continue;
            }
            buildWoolDyeRecipesForColor(recipeOutput, coloredWool, dye);
        }
    }

    private void buildWoolDyeRecipesForColor(RecipeOutput recipeOutput, ModBlocks wool, Item dye) {
        for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
            if (!wool.hasBlock(type)) {
                continue;
            }
            Block coloredShape = wool.getBlock(type);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, coloredShape, 1)
                    .requires(Ingredient.of(switch (type){
                        case SLAB -> ModTags.WOOL_SLABS_ITEMS;
                        case STAIRS -> ModTags.WOOL_STAIRS_ITEMS;
                        case WALL -> ModTags.WOOL_WALLS_ITEMS;
                            }))
                    .requires(Ingredient.of(dye))
                    .unlockedBy(RecipeProvider.getHasName(coloredShape), RecipeProvider.has(coloredShape))
                    .unlockedBy(RecipeProvider.getHasName(dye), RecipeProvider.has(dye))
                    .group("more_wool_dye")
                    .save(recipeOutput, woolDyeRecipeId(coloredShape));
        }
    }

    private ResourceLocation woolDyeRecipeId(Block coloredWoolShape) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(coloredWoolShape) + "_from_dye"));
    }

    private static boolean isWoolModBlock(ModBlocks block) {
        return ModTags.isVanillaWoolParent(block.parentBlock);
    }

    @Nullable
    private static Item getDyeItemForColoredWool(ModBlocks wool) {
        return switch (wool) {
            case WHITE_WOOL -> Items.WHITE_DYE;
            case ORANGE_WOOL -> Items.ORANGE_DYE;
            case MAGENTA_WOOL -> Items.MAGENTA_DYE;
            case LIGHT_BLUE_WOOL -> Items.LIGHT_BLUE_DYE;
            case YELLOW_WOOL -> Items.YELLOW_DYE;
            case LIME_WOOL -> Items.LIME_DYE;
            case PINK_WOOL -> Items.PINK_DYE;
            case GRAY_WOOL -> Items.GRAY_DYE;
            case LIGHT_GRAY_WOOL -> Items.LIGHT_GRAY_DYE;
            case CYAN_WOOL -> Items.CYAN_DYE;
            case PURPLE_WOOL -> Items.PURPLE_DYE;
            case BLUE_WOOL -> Items.BLUE_DYE;
            case BROWN_WOOL -> Items.BROWN_DYE;
            case GREEN_WOOL -> Items.GREEN_DYE;
            case RED_WOOL -> Items.RED_DYE;
            case BLACK_WOOL -> Items.BLACK_DYE;
            default -> null;
        };
    }

    private void buildSmeltingRecipesForSmeltingPair(RecipeOutput recipeOutput, ModBlocks input, ModBlocks output) {
        for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
            if (!input.hasBlock(type) || !output.hasBlock(type)) {
                continue;
            }
            Block in = input.getBlock(type);
            Block out = output.getBlock(type);
            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(in.asItem()),
                            RecipeCategory.BUILDING_BLOCKS,
                            out.asItem(),
                            0.1f,
                            200)
                    .unlockedBy(RecipeProvider.getHasName(in), RecipeProvider.has(in))
                    .save(recipeOutput, smeltingRecipeId(out, in));
        }
    }



    private ResourceLocation smeltingRecipeId(Block result, Block input) {
        return getRecipeIdentifier(ResourceLocation.fromNamespaceAndPath(this.output.getModId(), itemPath(result) + "_from_smelting_" + itemPath(input)));
    }

    @Nullable
    private static ModBlocks getSmeltingOutput(ModBlocks block) {
        return switch (block){
            case WHITE_TERRACOTTA -> ModBlocks.WHITE_GLAZED_TERRACOTTA;
            case ORANGE_TERRACOTTA -> ModBlocks.ORANGE_GLAZED_TERRACOTTA;
            case MAGENTA_TERRACOTTA -> ModBlocks.MAGENTA_GLAZED_TERRACOTTA;
            case LIGHT_BLUE_TERRACOTTA -> ModBlocks.LIGHT_BLUE_GLAZED_TERRACOTTA;
            case YELLOW_TERRACOTTA -> ModBlocks.YELLOW_GLAZED_TERRACOTTA;
            case LIME_TERRACOTTA -> ModBlocks.LIME_GLAZED_TERRACOTTA;
            case PINK_TERRACOTTA -> ModBlocks.PINK_GLAZED_TERRACOTTA;
            case GRAY_TERRACOTTA -> ModBlocks.GRAY_GLAZED_TERRACOTTA;
            case LIGHT_GRAY_TERRACOTTA -> ModBlocks.LIGHT_GRAY_GLAZED_TERRACOTTA;
            case CYAN_TERRACOTTA -> ModBlocks.CYAN_GLAZED_TERRACOTTA;
            case PURPLE_TERRACOTTA -> ModBlocks.PURPLE_GLAZED_TERRACOTTA;
            case BLUE_TERRACOTTA -> ModBlocks.BLUE_GLAZED_TERRACOTTA;
            case BROWN_TERRACOTTA -> ModBlocks.BROWN_GLAZED_TERRACOTTA;
            case GREEN_TERRACOTTA -> ModBlocks.GREEN_GLAZED_TERRACOTTA;
            case RED_TERRACOTTA -> ModBlocks.RED_GLAZED_TERRACOTTA;
            case BLACK_TERRACOTTA -> ModBlocks.BLACK_GLAZED_TERRACOTTA;

            case SAND, RED_SAND -> ModBlocks.GLASS;

            case CLAY -> ModBlocks.TERRACOTTA;

            default -> null;
        };
    }

    private static ModBlocks getLogPlankOutput(ModBlocks block){
        return switch (block) {
            case OAK_LOG, OAK_WOOD, STRIPPED_OAK_LOG, STRIPPED_OAK_WOOD -> ModBlocks.OAK_PLANKS;
            case SPRUCE_LOG, SPRUCE_WOOD, STRIPPED_SPRUCE_LOG, STRIPPED_SPRUCE_WOOD -> ModBlocks.SPRUCE_PLANKS;
            case BIRCH_LOG, BIRCH_WOOD, STRIPPED_BIRCH_LOG, STRIPPED_BIRCH_WOOD -> ModBlocks.BIRCH_PLANKS;
            case JUNGLE_LOG, JUNGLE_WOOD, STRIPPED_JUNGLE_LOG, STRIPPED_JUNGLE_WOOD -> ModBlocks.JUNGLE_PLANKS;
            case ACACIA_LOG, ACACIA_WOOD, STRIPPED_ACACIA_LOG, STRIPPED_ACACIA_WOOD -> ModBlocks.ACACIA_PLANKS;
            case DARK_OAK_LOG, DARK_OAK_WOOD, STRIPPED_DARK_OAK_LOG, STRIPPED_DARK_OAK_WOOD -> ModBlocks.DARK_OAK_PLANKS;
            case CRIMSON_STEM, STRIPPED_CRIMSON_STEM, STRIPPED_CRIMSON_HYPHAE, CRIMSON_HYPHAE -> ModBlocks.CRIMSON_PLANKS;
            case WARPED_STEM, STRIPPED_WARPED_STEM, STRIPPED_WARPED_HYPHAE, WARPED_HYPHAE -> ModBlocks.WARPED_PLANKS;
            case MANGROVE_LOG, STRIPPED_MANGROVE_LOG, MANGROVE_WOOD, STRIPPED_MANGROVE_WOOD -> ModBlocks.MANGROVE_PLANKS;
            case CHERRY_LOG, STRIPPED_CHERRY_LOG, CHERRY_WOOD, STRIPPED_CHERRY_WOOD -> ModBlocks.CHERRY_PLANKS;
            case BAMBOO_BLOCK, STRIPPED_BAMBOO_BLOCK -> ModBlocks.BAMBOO_PLANKS;
            default -> null;
        };
    }
}
