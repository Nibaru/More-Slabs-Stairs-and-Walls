package games.twinhead.moreslabsstairsandwalls.block.culled;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;

public class CulledSlabBlock extends SlabBlock{
    public CulledSlabBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        if(blockState_2.getBlock() == Blocks.GLASS) return true;

        if(blockState_2.getBlock() == this)
            if(isInvisibleToGlassSlab(blockState_1, blockState_2, direction_1)) return true;


        if(blockState_2.getBlock() == ModBlocks.GLASS.getStairsBlock())
            if(isInvisibleToGlassStairs(blockState_1, blockState_2, direction_1)) return true;


        return super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }

    private boolean isInvisibleToGlassSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1)
    {
        SlabType type1 = blockState_1.get(SlabBlock.TYPE);
        SlabType type2 = blockState_2.get(SlabBlock.TYPE);

        if(type2 == SlabType.DOUBLE)
            return true;

        switch(direction_1)
        {
            case UP:
            case DOWN:
                if(type1 != type2)
                    return true;
                break;

            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                if(type1 == type2)
                    return true;
                break;
        }

        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState blockState_1,
                                             BlockState blockState_2, Direction direction_1)
    {
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

}
