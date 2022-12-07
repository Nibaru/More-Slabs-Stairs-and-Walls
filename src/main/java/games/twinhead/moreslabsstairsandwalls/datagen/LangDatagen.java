package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LangDatagen extends FabricLanguageProvider {

    protected LangDatagen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(MoreSlabsStairsAndWalls.modGroup, "More Slabs, Stairs, and Walls");
        for (ModBlocks block: ModBlocks.values()) {
            if(block.hasSlab()) translationBuilder.add(block.getSlabBlock(), formatName(block.toString().toLowerCase() + "_slab"));
            if(block.hasStairs()) translationBuilder.add(block.getStairsBlock(), formatName(block.toString().toLowerCase() + "_stairs"));
            if(block.hasWall()) translationBuilder.add(block.getWallBlock(), formatName(block.toString().toLowerCase() + "_wall"));
        }
    }

    public String formatName(String name){
        String[] parts = name.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: parts) {
            if(!s.contains("block"))
                stringBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase()).append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }
}
