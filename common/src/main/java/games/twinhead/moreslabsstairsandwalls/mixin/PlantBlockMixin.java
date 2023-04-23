package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtSlab;
import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtStairs;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin extends Block {

    public PlantBlockMixin(Settings settings) {
        super(settings);
    }


    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void injected(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(floor.isIn(BlockTags.DIRT)){
            if(floor.getBlock() instanceof DirtSlab && floor.get(SlabBlock.TYPE) == SlabType.BOTTOM)
                cir.setReturnValue(false);

            if(floor.getBlock() instanceof DirtStairs && floor.get(StairsBlock.HALF) == BlockHalf.BOTTOM)
                cir.setReturnValue(false);
        }
    }
}
