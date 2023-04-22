package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.WallBlock;

public class BaseWall extends WallBlock {

    public BaseWall(Settings settings) {
        super(settings);
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.WALL;
    }
}
