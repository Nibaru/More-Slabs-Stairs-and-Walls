package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableSlab;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin extends SnowyBlock {
    public SpreadableBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private static void surviveUnderOwnSlab(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(SpreadableSlab.canSurvive(state, world, pos));
    }

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, BlockState defaultState, int j, BlockPos spreadPos) {
        SpreadableSlab.trySpread(world, defaultState.getBlock(), spreadPos);
        ci.cancel();
    }
}
