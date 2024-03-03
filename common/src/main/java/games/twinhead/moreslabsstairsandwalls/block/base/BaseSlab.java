package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.SlabBlock;

public class BaseSlab extends SlabBlock {

    ModBlocks modBlock;

    public BaseSlab(ModBlocks block, Settings settings) {
        super(settings);
        this.modBlock = block;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.SLAB;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }
}
