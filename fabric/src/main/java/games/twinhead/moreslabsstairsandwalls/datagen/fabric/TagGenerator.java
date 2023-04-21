package games.twinhead.moreslabsstairsandwalls.datagen.fabric;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class TagGenerator extends FabricTagProvider<Block> {

    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     */
    public TagGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        for (ModBlocks block: ModBlocks.values()){
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                getOrCreateTagBuilder(switch (type){
                    case SLAB -> BlockTags.SLABS;
                    case STAIRS -> BlockTags.STAIRS;
                    case WALL -> BlockTags.WALLS;
                }).add(block.getBlock(type));
                for (TagKey<Block> tag: block.blockTags) {
                    if(!(tag == BlockTags.BEACON_BASE_BLOCKS && type != ModBlocks.BlockType.SLAB))
                        getOrCreateTagBuilder(tag).add(block.getBlock(type));
                }
            }
        }
    }
}
