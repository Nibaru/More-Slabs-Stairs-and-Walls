package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.WallBlock;

public class BaseWall extends WallBlock {

    ModBlocks modBlock;

    public BaseWall(ModBlocks modBlock,Settings settings) {
        super(settings);
        this.modBlock = modBlock;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.WALL;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }
}
