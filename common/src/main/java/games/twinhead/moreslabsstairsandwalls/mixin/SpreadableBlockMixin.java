package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SpreadingSnowyDirtBlock.class)
public abstract class SpreadableBlockMixin {

    /** Mojmap: {@code BlockState#is(Block)}; Yarn mapped this as {@code isOf}. */
    @Inject(
            method = "randomTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci, BlockState defaultState, int j, BlockPos spreadPos) {
        SpreadableSlab.trySpread(world, defaultState.getBlock(), spreadPos);
    }
}
