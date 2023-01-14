package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

import static net.minecraft.block.BubbleColumnBlock.DRAG;

@Mixin(BubbleColumnBlock.class)
public class BubbleColumnBlockMixin  extends Block implements FluidDrainable {
    public BubbleColumnBlockMixin(Settings settings) {
        super(settings);
    }


    @Inject(method = "getBubbleState", at = @At(value = "HEAD"), cancellable = true)
    private static void getState(BlockState state, CallbackInfoReturnable<BlockState> cir){
        if (state.isOf(ModBlocks.SOUL_SAND.getSlabBlock()) || state.isOf(ModBlocks.SOUL_SAND.getStairsBlock()) || state.isOf(ModBlocks.SOUL_SAND.getWallBlock())) {
            cir.setReturnValue((BlockState) Blocks.BUBBLE_COLUMN.getDefaultState().with(DRAG, false));
        }else if (state.isOf(ModBlocks.MAGMA_BLOCK.getSlabBlock()) || state.isOf(ModBlocks.MAGMA_BLOCK.getStairsBlock()) || state.isOf(ModBlocks.MAGMA_BLOCK.getWallBlock())) {
            cir.setReturnValue((BlockState)Blocks.BUBBLE_COLUMN.getDefaultState().with(DRAG, true));
        }
    }


    @Shadow
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        return null;
    }

    @Shadow
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.empty();
    }
}