package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;

@SuppressWarnings("deprecation")
public class TranslucentSlab extends BaseSlab {

    public TranslucentSlab(ModBlocks modBlocks, Properties settings) {
        super(modBlocks, settings);
    }


    @Override
    @Environment(EnvType.CLIENT)
    public boolean skipRendering(BlockState state, BlockState state2, Direction dir) {
        if (state2.getBlock() == this.getModBlock().parentBlock) return true;

        if (state2.getBlock() instanceof TranslucentSlab slab){
            if (slab.getModBlock() == getModBlock())
                if (isInvisibleToGlassSlab(state, state2, dir)) return true;
        }

        if (state2.getBlock() instanceof TranslucentStairs stairs){
            if (stairs.getModBlock() == getModBlock())
                if (isInvisibleToGlassStairs(state, state2, dir)) return true;
        }

        return super.skipRendering(state, state2, dir);
    }

    private boolean isInvisibleToGlassSlab(BlockState state, BlockState state2, Direction dir) {
        SlabType type1 = state.getValue(SlabBlock.TYPE);
        SlabType type2 = state2.getValue(SlabBlock.TYPE);

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
        SlabType type1 = state.getValue(SlabBlock.TYPE);
        Half half2 = state2.getValue(StairBlock.HALF);
        Direction facing2 = state2.getValue(StairBlock.FACING);

        // up
        if( dir == Direction.UP && half2 == Half.BOTTOM) return true;

        // down
        if(dir == Direction.DOWN && half2 == Half.TOP) return true;

        // other stairs rear
        if(facing2 == dir.getOpposite()) return true;

        // sides
        if(dir.get2DDataValue() != -1) {
            if(type1 == SlabType.BOTTOM && half2 == Half.BOTTOM) return true;
            return type1 == SlabType.TOP && half2 == Half.TOP;
        }
        return false;
    }
}