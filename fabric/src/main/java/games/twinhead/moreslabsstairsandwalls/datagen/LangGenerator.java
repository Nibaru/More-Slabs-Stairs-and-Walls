package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LangGenerator extends FabricLanguageProvider {

    protected LangGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.more_slabs_stairs_and_walls.creative_tab", "More Slabs, Stairs, & Walls");
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(block.hasBlock(type)) {
                    translationBuilder.add(block.getBlock(type), formatName(block.toString().toLowerCase() + "_" + type.toString().toLowerCase()));
                }
            }
        }


        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.oak_slabs", "Oak Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.spruce_slabs", "Spruce Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.birch_slabs", "Birch Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.jungle_slabs", "Jungle Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.acacia_slabs", "Acacia Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.dark_oak_slabs", "Dark Oak Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.crimson_slabs", "Crimson Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.warped_slabs", "Warped Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.mangrove_slabs", "Mangrove Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.cherry_slabs", "Cherry Slabs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.bamboo_slabs", "Bamboo Slabs");

        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.oak_stairs", "Oak Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.spruce_stairs", "Spruce Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.birch_stairs", "Birch Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.jungle_stairs", "Jungle Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.acacia_stairs", "Acacia Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.dark_oak_stairs", "Dark Oak Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.crimson_stairs", "Crimson Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.warped_stairs", "Warped Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.mangrove_stairs", "Mangrove Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.cherry_stairs", "Cherry Stairs");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.bamboo_stairs", "Bamboo Stairs");

        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.oak_walls", "Oak Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.spruce_walls", "Spruce Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.birch_walls", "Birch Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.jungle_walls", "Jungle Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.acacia_walls", "Acacia Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.dark_oak_walls", "Dark Oak Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.crimson_walls", "Crimson Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.warped_walls", "Warped Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.mangrove_walls", "Mangrove Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.cherry_walls", "Cherry Walls");
        translationBuilder.add("tag.item.more_slabs_stairs_and_walls.bamboo_walls", "Bamboo Walls");

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
