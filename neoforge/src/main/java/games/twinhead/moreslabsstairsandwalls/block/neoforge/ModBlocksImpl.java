package games.twinhead.moreslabsstairsandwalls.block.neoforge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

public class ModBlocksImpl {

    public static Block getBlock(ModBlocks block, ModBlocks.BlockType type){
        return Registries.BLOCK.get(block.getId(type));
    }
}
