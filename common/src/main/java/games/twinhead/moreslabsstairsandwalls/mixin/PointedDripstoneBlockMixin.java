package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin extends Block implements Fallable, SimpleWaterloggedBlock {

    public PointedDripstoneBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "canGrow", at = @At("HEAD"), cancellable = true)
    private static void injected(BlockState dripstoneBlockState, BlockState waterState, CallbackInfoReturnable<Boolean> cir){
        if(isDripstoneWaterloggable(dripstoneBlockState) && isWaterlogged(dripstoneBlockState)) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(isDripstone(dripstoneBlockState) && waterState.is(Blocks.WATER) && waterState.getFluidState().isSource());

        }
    }

    @Unique
    private static boolean isDripstone(BlockState blockState){
        return blockState.is(Blocks.DRIPSTONE_BLOCK) ||
                blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.SLAB)) ||
                blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)) ||
                blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.WALL));
    }

    @Unique
    private static boolean isWaterlogged(BlockState blockState){
        for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
            if(blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(type))) return blockState.getFluidState().is(FluidTags.WATER);
        }
        return false;
    }

    @Unique
    private static boolean isDripstoneWaterloggable(BlockState blockState){
        return blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.SLAB)) || blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)) || blockState.is(ModBlocks.DRIPSTONE_BLOCK.getBlock(ModBlocks.BlockType.WALL));
    }

}
