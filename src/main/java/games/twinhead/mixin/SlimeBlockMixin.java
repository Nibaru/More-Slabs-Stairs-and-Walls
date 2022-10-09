package games.twinhead.mixin;

import games.twinhead.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlimeBlock.class)
public class SlimeBlockMixin extends TransparentBlock {

    protected SlimeBlockMixin(Settings settings) {
        super(settings);
    }


    @Override
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        if(blockState_2.getBlock() == ModBlocks.SLIME_BLOCK.getSlabBlock())
            if(isInvisibleToGlassSlab(blockState_1, blockState_2, direction_1))
                return true;

        if(blockState_2.getBlock() == ModBlocks.SLIME_BLOCK.getStairsBlock())
            if(isInvisibleToGlassStairs(blockState_1, blockState_2,
                    direction_1))
                return true;

        return super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }


    private boolean isInvisibleToGlassSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        SlabType type2 = blockState_2.get(SlabBlock.TYPE);

        if(type2 == SlabType.DOUBLE)
            return true;

        if(direction_1 == Direction.UP)
            if(type2 != SlabType.TOP)
                return true;

        if(direction_1 == Direction.DOWN)
            if(type2 != SlabType.BOTTOM)
                return true;

        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState blockState_1,
                                             BlockState blockState_2, Direction direction_1)
    {
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



        return false;
    }
}
