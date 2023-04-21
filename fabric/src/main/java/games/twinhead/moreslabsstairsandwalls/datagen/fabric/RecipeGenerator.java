package games.twinhead.moreslabsstairsandwalls.datagen.fabric;


import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataGenerator dataGenerator) {
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
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        for (ModBlocks block: ModBlocks.values()) {
            if(block.hasBlock(ModBlocks.BlockType.SLAB)){
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getBlock(ModBlocks.BlockType.SLAB), block.parentBlock, 2);
                ShapedRecipeJsonBuilder.create(block.getBlock(ModBlocks.BlockType.SLAB), 6).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.SLAB)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.SLAB))).input('#', block.parentBlock).pattern("###").group("more_slabs").offerTo(exporter);
            }

            if(block.hasBlock(ModBlocks.BlockType.STAIRS)){
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getBlock(ModBlocks.BlockType.STAIRS), block.parentBlock, 1);
                ShapedRecipeJsonBuilder.create(block.getBlock(ModBlocks.BlockType.STAIRS), 4).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.STAIRS)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.STAIRS))).input('#', block.parentBlock).pattern("#  ").pattern("## ").pattern("###").group("more_stairs").offerTo(exporter);
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
                        default -> Blocks.OAK_FENCE;
                    };
                    //RecipeProvider.offerShapelessRecipe(exporter, block.getWallBlock(), fence, "plank_walls", 1);
                    ShapelessRecipeJsonBuilder.create(block.getBlock(ModBlocks.BlockType.WALL), 1).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL))).input(fence).group("more_walls").offerTo(exporter);
                } else if(!glass.contains(block)){
                    ShapedRecipeJsonBuilder.create(block.getBlock(ModBlocks.BlockType.WALL), 6).criterion(hasItem(block.parentBlock), conditionsFromItem(block.parentBlock)).criterion(hasItem(block.getBlock(ModBlocks.BlockType.WALL)), conditionsFromItem(block.getBlock(ModBlocks.BlockType.WALL))).input('#', block.parentBlock).pattern("###").pattern("###").group("more_walls").offerTo(exporter);
                }
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getBlock(ModBlocks.BlockType.WALL), block.parentBlock);
            }
        }
    }
}
