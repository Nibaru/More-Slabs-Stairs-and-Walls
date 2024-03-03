package games.twinhead.moreslabsstairsandwalls.fabric.mixin;



import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeSlab;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {


    @Inject(method = "isBlockSticky", at = @At("HEAD"), cancellable = true)
    private static void injected(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(SlimeSlab.isStateHoney(state) || SlimeSlab.isStateSlime(state));
    }


    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void injected(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        if(SlimeSlab.isStateHoney(state) && SlimeSlab.isStateSlime(adjacentState)){
            cir.setReturnValue(false);
        }

        if(SlimeSlab.isStateHoney(adjacentState) && SlimeSlab.isStateSlime(state)){
            cir.setReturnValue(false);
        }
    }


}
