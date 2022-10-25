package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStainedSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStainedStairsBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StainedGlassBlock.class)
public class StainedGlassBlockMixin extends AbstractGlassBlock {
    @Shadow
    @Final
    private DyeColor color;

    private StainedGlassBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        Block block = stateFrom.getBlock();

        if (block instanceof CulledStainedSlabBlock && ((CulledStainedSlabBlock) block).getColor() == color)
            if (isInvisibleToGlassSlab(stateFrom, direction)) return true;

        if (block instanceof CulledStainedStairsBlock && ((CulledStainedStairsBlock) block).getColor() == color)
            if (isInvisibleToGlassStairs(stateFrom, direction)) return true;

        return super.isSideInvisible(state, stateFrom, direction);
    }

    private boolean isInvisibleToGlassSlab(BlockState state, Direction direction) {
        SlabType type = state.get(SlabBlock.TYPE);
        if (type == SlabType.DOUBLE) return true;
        if (direction == Direction.UP && type != SlabType.TOP) return true;
        return direction == Direction.DOWN && type != SlabType.BOTTOM;
    }

    private boolean isInvisibleToGlassStairs(BlockState state, Direction direction) {
        BlockHalf half2 = state.get(StairsBlock.HALF);
        Direction facing2 = state.get(StairsBlock.FACING);
        if (direction == Direction.UP && half2 == BlockHalf.BOTTOM) return true;
        if (direction == Direction.DOWN && half2 == BlockHalf.TOP) return true;
        return facing2 == direction.getOpposite();
    }
}