package games.twinhead.moreslabsstairsandwalls.block.culled;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;

public class CulledStainedSlabBlock extends SlabBlock implements Stainable {


    private final DyeColor color;

    public CulledStainedSlabBlock(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }


    @SuppressWarnings("deprecation")
    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        if(blockState_2.getBlock() instanceof StainedGlassBlock)
            return true;

        if(blockState_2.getBlock() == this)
            if(isInvisibleToGlassSlab(blockState_1, blockState_2, direction_1))
                return true;

        if(blockState_2.getBlock() instanceof CulledStainedStairsBlock
                && ((CulledStainedStairsBlock)blockState_2.getBlock()).getColor() == color)
            if(isInvisibleToGlassStairs(blockState_1, blockState_2,
                    direction_1))
                return true;

        return super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }

    private boolean isInvisibleToGlassSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        SlabType type1 = blockState_1.get(SlabBlock.TYPE);
        SlabType type2 = blockState_2.get(SlabBlock.TYPE);

        if(type2 == SlabType.DOUBLE)
            return true;

        switch(direction_1) {
            case UP, DOWN:
                if(type1 != type2)
                    return true;
                break;

            case NORTH,EAST,SOUTH,WEST:
                if(type1 == type2)
                    return true;
                break;
        }

        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        SlabType type1 = blockState_1.get(SlabBlock.TYPE);
        BlockHalf half2 = blockState_2.get(StairsBlock.HALF);
        Direction facing2 = blockState_2.get(StairsBlock.FACING);

        // up
        if(direction_1 == Direction.UP)
            if(half2 == BlockHalf.BOTTOM)
                return true;

        // down
        if(direction_1 == Direction.DOWN)
            if(half2 == BlockHalf.TOP)
                return true;

        // other stairs rear
        if(facing2 == direction_1.getOpposite())
            return true;

        // sides
        if(direction_1.getHorizontal() != -1)
        {
            if(type1 == SlabType.BOTTOM && half2 == BlockHalf.BOTTOM)
                return true;
            return type1 == SlabType.TOP && half2 == BlockHalf.TOP;
        }

        return false;
    }


    @Override
    public DyeColor getColor() {
        return color;
    }
}
