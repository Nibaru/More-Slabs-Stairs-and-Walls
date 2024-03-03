package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin extends Block {

    public PlantBlockMixin(Settings settings) {
        super(settings);
    }


//    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
//    private void injected(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
//        System.out.println("PlantBlockMixin");
//
//        if(floor.getBlock() instanceof BaseSlab && floor.get(SlabBlock.TYPE) == SlabType.BOTTOM){
//            System.out.println("PlantBlockMixin: BaseSlab");
//            cir.setReturnValue(false);
//
//        }
//
//        if(floor.getBlock() instanceof BaseStairs && floor.get(StairsBlock.HALF) == BlockHalf.BOTTOM){
//            System.out.println("PlantBlockMixin: BaseStairs");
//            cir.setReturnValue(false);
//        }
//    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockPos = pos.down();
        if (world.getBlockState(blockPos).getBlock() instanceof BaseSlab && world.getBlockState(blockPos).get(SlabBlock.TYPE) == SlabType.BOTTOM) {
            cir.setReturnValue(false);
        }
        if (world.getBlockState(blockPos).getBlock() instanceof BaseStairs && world.getBlockState(blockPos).get(StairsBlock.HALF) == BlockHalf.BOTTOM) {
            cir.setReturnValue(false);
        }
    }

}
