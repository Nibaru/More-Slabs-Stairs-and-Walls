package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.Direction;

public class BaseStairs extends StairsBlock {

    public BaseStairs(BlockState state, AbstractBlock.Settings settings) {
        super(state, settings);
        this.setDefaultState(((((this.getDefaultState()).with(FACING, Direction.NORTH)).with(HALF, BlockHalf.BOTTOM)).with(SHAPE, StairShape.STRAIGHT)).with(WATERLOGGED, false));
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.STAIRS;
    }
}
