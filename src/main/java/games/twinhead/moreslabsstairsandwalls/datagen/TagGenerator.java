package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class TagGenerator extends FabricTagProvider<Block> {

    public TagGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        for (ModBlocks block: ModBlocks.values()){
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(!block.hasBlock(type)) continue;
                getOrCreateTagBuilder(switch (type){
                    case SLAB -> BlockTags.SLABS;
                    case STAIRS -> BlockTags.STAIRS;
                    case WALL -> BlockTags.WALLS;
                }).add(block.getId(type));
                for (TagKey<Block> tag: block.blockTags) {
                    if(!(tag == BlockTags.BEACON_BASE_BLOCKS && type != ModBlocks.BlockType.SLAB))
                        getOrCreateTagBuilder(tag).add(block.getId(type));
                }
            }
        }
    }
}
