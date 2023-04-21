package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GlassBlock.class)
public class GlassBlockMixin extends AbstractGlassBlock {

    protected GlassBlockMixin(Settings settings) {
        super(settings);
    }


    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if(stateFrom.getBlock() == ModBlocks.GLASS.getBlock(ModBlocks.BlockType.SLAB) && isInvisibleToGlassSlab(stateFrom, direction))return true;
        if(stateFrom.getBlock() == ModBlocks.GLASS.getBlock(ModBlocks.BlockType.STAIRS) && isInvisibleToGlassStairs(stateFrom, direction))return true;
        return super.isSideInvisible(state, stateFrom, direction);
    }


    private boolean isInvisibleToGlassSlab(BlockState slabState, Direction direction_1) {
        SlabType type2 = slabState.get(SlabBlock.TYPE);
        if(type2 == SlabType.DOUBLE) return true;
        if(direction_1 == Direction.UP && type2 != SlabType.TOP) return true;
        if(direction_1 == Direction.DOWN) return type2 != SlabType.BOTTOM;
        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState stairState, Direction direction_1) {
        BlockHalf half2 = stairState.get(StairsBlock.HALF);
        Direction facing2 = stairState.get(StairsBlock.FACING);
        if(direction_1 == Direction.UP && half2 == BlockHalf.BOTTOM) return true;
        if(direction_1 == Direction.DOWN && half2 == BlockHalf.TOP) return true;
        return facing2 == direction_1.getOpposite();
    }
}
