package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.SlabBlock;

public class BaseSlab extends SlabBlock {

    public BaseSlab(Settings settings) {
        super(settings);
    }


    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.SLAB;
    }
}
