package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput dataGenerator, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataGenerator, registriesFuture);
    }





    @Override
    protected void addTags(HolderLookup.Provider arg) {
        for (ModBlocks block: ModBlocks.values()){
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(!block.hasBlock(type)) continue;

                getOrCreateTagBuilder(switch (type){
                    case SLAB -> BlockTags.SLABS;
                    case STAIRS -> BlockTags.STAIRS;
                    case WALL -> BlockTags.WALLS;
                }).add(block.getId(type));


                for (TagKey<Block> tag: block.blockTags) {
                    if(tag == BlockTags.BEACON_BASE_BLOCKS && type == ModBlocks.BlockType.SLAB) continue;
                    getOrCreateTagBuilder(tag).add(block.getId(type));
                }
            }
        }
        getOrCreateTagBuilder(ModTags.GRASS_BLOCKS).add(Blocks.GRASS_BLOCK, ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.WALL));
    }
}
