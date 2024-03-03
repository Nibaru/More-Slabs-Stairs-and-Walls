package games.twinhead.moreslabsstairsandwalls.block.fabric;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.fabric.ModRegistry;
import net.minecraft.block.Block;

public class ModBlocksImpl {

    public static Block getBlock(ModBlocks block, ModBlocks.BlockType type){
        return ModRegistry.getBlock(block.getId(type));
    }
}
