package games.twinhead.moreslabsstairsandwalls.mixin;



import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
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
        cir.setReturnValue(isStateHoney(state) || isStateSlime(state));
    }


    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void injected(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        if(isStateHoney(state) && isStateSlime(adjacentState)){
            cir.setReturnValue(false);
        }

        if(isStateHoney(adjacentState) && isStateSlime(state)){
            cir.setReturnValue(false);
        }
    }

    private static boolean isStateHoney(BlockState state){
        if(state.isOf(ModBlocks.HONEY_BLOCK.getSlabBlock())){
            return true;
        } else if(state.isOf(ModBlocks.HONEY_BLOCK.getStairsBlock())){
            return true;
        } else if(state.isOf(ModBlocks.HONEY_BLOCK.getWallBlock())){
            return true;
        } else return state.isOf(Blocks.HONEY_BLOCK);
    }

    private static boolean isStateSlime(BlockState state){
        if(state.isOf(ModBlocks.SLIME_BLOCK.getSlabBlock())){
            return true;
        } else if(state.isOf(ModBlocks.SLIME_BLOCK.getStairsBlock())){
            return true;
        } else if(state.isOf(ModBlocks.SLIME_BLOCK.getWallBlock())){
            return true;
        } else return state.isOf(Blocks.SLIME_BLOCK);
    }
}
