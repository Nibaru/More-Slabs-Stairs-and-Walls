package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentStairs;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransparentBlock.class)
public class TranslucentBlockMixin extends Block {

    protected TranslucentBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "isSideInvisible", at = @At(value = "HEAD"), cancellable = true)
    public void  isSideInvisible(BlockState state, BlockState stateFrom, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (stateFrom.getBlock() instanceof TranslucentSlab slab){
            if (state.getBlock().equals(slab.getModBlock().parentBlock) && isInvisibleToGlassSlab(stateFrom, direction)){
               cir.setReturnValue(true);
            }
        }
        if (stateFrom.getBlock() instanceof TranslucentStairs stair){
            if (state.getBlock().equals(stair.getModBlock().parentBlock) && isInvisibleToGlassStairs(stateFrom, direction)){
                cir.setReturnValue(true);
            }
        }
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

        if (stairState.get(StairsBlock.SHAPE) == StairShape.INNER_LEFT) {
            if (direction_1 != stairState.get(HorizontalFacingBlock.FACING).rotateYCounterclockwise()){
                return true;
            }
        }
        if (stairState.get(StairsBlock.SHAPE) == StairShape.INNER_RIGHT) {
            if (direction_1 != stairState.get(HorizontalFacingBlock.FACING).rotateYClockwise()){
                return true;
            }
        }

        return facing2 == direction_1.getOpposite();
    }
}
