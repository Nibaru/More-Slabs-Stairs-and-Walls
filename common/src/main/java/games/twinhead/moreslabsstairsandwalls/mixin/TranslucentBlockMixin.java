package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentStairs;
import net.minecraft.world.level.block.*;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HalfTransparentBlock.class)
public class TranslucentBlockMixin extends Block {

    protected TranslucentBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "skipRendering", at = @At(value = "HEAD"), cancellable = true)
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
        SlabType type2 = slabState.getValue(SlabBlock.TYPE);
        if(type2 == SlabType.DOUBLE) return true;
        if(direction_1 == Direction.UP && type2 != SlabType.TOP) return true;
        if(direction_1 == Direction.DOWN) return type2 != SlabType.BOTTOM;
        return false;
    }

    private boolean isInvisibleToGlassStairs(BlockState stairState, Direction direction_1) {
        Half half2 = stairState.getValue(StairBlock.HALF);
        Direction facing2 = stairState.getValue(StairBlock.FACING);
        if(direction_1 == Direction.UP && half2 == Half.BOTTOM) return true;
        if(direction_1 == Direction.DOWN && half2 == Half.TOP) return true;

        if (stairState.getValue(StairBlock.SHAPE) == StairsShape.INNER_LEFT) {
            if (direction_1 != stairState.getValue(HorizontalDirectionalBlock.FACING).getCounterClockWise()){
                return true;
            }
        }
        if (stairState.getValue(StairBlock.SHAPE) == StairsShape.INNER_RIGHT) {
            if (direction_1 != stairState.getValue(HorizontalDirectionalBlock.FACING).getClockWise()){
                return true;
            }
        }

        return facing2 == direction_1.getOpposite();
    }
}
