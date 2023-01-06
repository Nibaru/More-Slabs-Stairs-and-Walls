package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin extends Block implements LandingBlock, Waterloggable {

    public PointedDripstoneBlockMixin(Settings settings) {
        super(settings);
    }


    @Inject(method = "canGrow(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private static void injected(BlockState dripstoneBlockState, BlockState waterState, CallbackInfoReturnable<Boolean> cir){
        if(isDripstoneWaterloggable(dripstoneBlockState) && isWaterlogged(dripstoneBlockState)) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(isDripstone(dripstoneBlockState) && waterState.isOf(Blocks.WATER) && waterState.getFluidState().isStill());

        }
    }

    private static boolean isDripstone(BlockState blockState){
        return blockState.isOf(Blocks.DRIPSTONE_BLOCK) || blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_SLAB.get()) || blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_STAIRS.get()) || blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_WALL.get());
    }

    private static boolean isWaterlogged(BlockState blockState){
        if(blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_SLAB.get())){
            return blockState.getFluidState().isIn(FluidTags.WATER);
        } else if (blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_STAIRS.get())) {
            return blockState.getFluidState().isIn(FluidTags.WATER);
        } else if (blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_WALL.get())) {
            return blockState.getFluidState().isIn(FluidTags.WATER);
        }
        return false;
    }

    private static boolean isDripstoneWaterloggable(BlockState blockState){
        return blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_SLAB.get()) || blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_STAIRS.get()) || blockState.isOf(ModBlocks.DRIPSTONE_BLOCK_WALL.get());
    }

}
