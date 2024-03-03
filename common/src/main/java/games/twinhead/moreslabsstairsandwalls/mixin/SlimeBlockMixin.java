package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlimeBlock.class)
public class SlimeBlockMixin extends TranslucentBlock {

    protected SlimeBlockMixin(Settings settings) {
        super(settings);
    }


    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if(stateFrom.getBlock() == ModBlocks.SLIME_BLOCK.getBlock(ModBlocks.BlockType.SLAB))
            if(isInvisibleToGlassSlab(stateFrom, direction)) return true;
        if(stateFrom.getBlock() == ModBlocks.SLIME_BLOCK.getBlock(ModBlocks.BlockType.STAIRS))
            if(isInvisibleToGlassStairs(stateFrom, direction)) return true;
        return super.isSideInvisible(state, stateFrom, direction);
    }


    private boolean isInvisibleToGlassSlab(BlockState state, Direction direction) {
        SlabType type2 = state.get(SlabBlock.TYPE);
        if(type2 == SlabType.DOUBLE) return true;
        if(direction == Direction.UP && type2 != SlabType.TOP) return true;
        return direction == Direction.DOWN && type2 != SlabType.BOTTOM;
    }

    private boolean isInvisibleToGlassStairs(BlockState state, Direction direction) {
        BlockHalf half2 = state.get(StairsBlock.HALF);
        Direction facing2 = state.get(StairsBlock.FACING);
        if(direction == Direction.UP && half2 == BlockHalf.BOTTOM) return true;
        if(direction == Direction.DOWN && half2 == BlockHalf.TOP) return true;
        return facing2 == direction.getOpposite();
    }
}
