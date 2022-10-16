package games.twinhead.datagen;

import games.twinhead.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;

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

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        for (ModBlocks block: ModBlocks.values()) {
            if(block != ModBlocks.SNOW_BLOCK){



            if(block.hasSlab()){
                RecipeProvider.offerSlabRecipe(exporter, block.getSlabBlock(), block.getCopyBlock());
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getSlabBlock(), block.getCopyBlock(), 2);
            }
            if(block.hasStairs()){
                ShapedRecipeJsonBuilder.create(block.getStairsBlock(), 4).criterion(hasItem(block.getStairsBlock()), conditionsFromItem(block.getStairsBlock())).input('#', block.getCopyBlock()).pattern("#  ").pattern("## ").pattern("###").offerTo(exporter);
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getStairsBlock(), block.getCopyBlock());
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
                    RecipeProvider.offerShapelessRecipe(exporter, block.getWallBlock(), fence, "plank_walls", 1);
                } else {
                    RecipeProvider.offerWallRecipe(exporter, block.getWallBlock(), block.getCopyBlock());
                }
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getWallBlock(), block.getCopyBlock());
            }
            }
        }
    }
}
