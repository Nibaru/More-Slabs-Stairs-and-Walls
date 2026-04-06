package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BushBlock.class)
public class PlantBlockMixin extends Block {

    public PlantBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canPlaceAt(BlockState state, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.LILY_PAD)) {
            return;
        }
        BlockPos blockPos = pos.below();
        if (world.getBlockState(blockPos).getBlock() instanceof BaseSlab && world.getBlockState(blockPos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM) {
            cir.setReturnValue(false);
        }
        if (world.getBlockState(blockPos).getBlock() instanceof BaseStairs && world.getBlockState(blockPos).getValue(StairBlock.HALF) == Half.BOTTOM) {
            cir.setReturnValue(false);
        }
    }

}
