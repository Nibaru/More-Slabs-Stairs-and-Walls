package games.twinhead.datagen;

import games.twinhead.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;

import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        for (ModBlocks block: ModBlocks.values()) {
            if(block.hasSlab()){
                RecipeProvider.offerSlabRecipe(exporter, block.getSlabBlock(), block.getCopyBlock());
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getSlabBlock(), block.getCopyBlock(), 2);
            }
            if(block.hasStairs()){

                ShapedRecipeJsonBuilder.create(block.getStairsBlock(), 4).criterion(hasItem(block.getStairsBlock()), conditionsFromItem(block.getStairsBlock())).input('#', block.getCopyBlock()).pattern("#  ").pattern("## ").pattern("###").offerTo(exporter);
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getStairsBlock(), block.getCopyBlock());
            }

            if(block.hasWall()){


                RecipeProvider.offerWallRecipe(exporter, block.getWallBlock(), block.getCopyBlock());
                RecipeProvider.offerStonecuttingRecipe(exporter, block.getWallBlock(), block.getCopyBlock());


            }
        }
    }
}
