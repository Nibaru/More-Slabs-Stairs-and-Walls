package games.twinhead.moreslabsstairsandwalls.block.neoforge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public class ModBlocksImpl {

    public static Block getBlock(ModBlocks block, ModBlocks.BlockType type){
        return BuiltInRegistries.BLOCK.get(block.getId(type));
    }
}
