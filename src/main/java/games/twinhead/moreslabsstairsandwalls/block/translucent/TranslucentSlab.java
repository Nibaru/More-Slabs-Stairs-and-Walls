package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;

public class TranslucentSlab extends BaseSlab {

    final ModBlocks modBlock;
    public TranslucentSlab(ModBlocks block, Settings settings) {
        super(settings);
        this.modBlock = block;
    }

    public ModBlocks getModBlock() {
        return modBlock;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState state2, Direction dir) {
        if (state2.getBlock() == modBlock.parentBlock) return true;



        if (state2.getBlock() instanceof TranslucentSlab slab){
            if (slab.getModBlock() == getModBlock())
                if (isInvisibleToGlassSlab(state, state2, dir)) return true;

        }

        if (state2.getBlock() instanceof TranslucentStairs stairs){
            if (stairs.getModBlock() == getModBlock())
                if (isInvisibleToGlassStairs(state, state2, dir)) return true;
        }



        return super.isSideInvisible(state, state2, dir);
    }

    private boolean isInvisibleToGlassSlab(BlockState state, BlockState state2, Direction dir) {
        SlabType type1 = state.get(SlabBlock.TYPE);
        SlabType type2 = state2.get(SlabBlock.TYPE);

        if (type2 == SlabType.DOUBLE) return true;

        switch (dir) {
            case UP, DOWN -> {
                if (type1 != type2) return true;
            }
            case NORTH, EAST, SOUTH, WEST -> {
                if (type1 == type2) return true;
            }
        }
        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState state, BlockState state2, Direction dir) {
        SlabType type1 = state.get(SlabBlock.TYPE);
        BlockHalf half2 = state2.get(StairsBlock.HALF);
        Direction facing2 = state2.get(StairsBlock.FACING);

        // up
        if( dir == Direction.UP && half2 == BlockHalf.BOTTOM) return true;

        // down
        if(dir == Direction.DOWN && half2 == BlockHalf.TOP) return true;

        // other stairs rear
        if(facing2 == dir.getOpposite()) return true;

        // sides
        if(dir.getHorizontal() != -1) {
            if(type1 == SlabType.BOTTOM && half2 == BlockHalf.BOTTOM) return true;
            return type1 == SlabType.TOP && half2 == BlockHalf.TOP;
        }
        return false;
    }
}