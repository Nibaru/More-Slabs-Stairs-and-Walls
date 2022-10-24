package games.twinhead.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ModelGenerator::new);
        fabricDataGenerator.addProvider(TagGenerator::new);
        fabricDataGenerator.addProvider(LootTableGenerator::new);
        fabricDataGenerator.addProvider(RecipeGenerator::new);
    }
}
