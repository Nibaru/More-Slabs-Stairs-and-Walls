package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(dataGenerator, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (ModBlocks block: ModBlocks.values()){
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(!block.hasBlock(type)) continue;
                if (RecipeGenerator.logBlocks.contains(block)) {
                    getOrCreateTagBuilder(ModTags.getLogTagKey(block, type)).add(block.getId(type));
                }
            }
        }
    }
}
