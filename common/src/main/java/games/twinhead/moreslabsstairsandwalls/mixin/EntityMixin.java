package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin{

    public EntityMixin()  {}

    @Inject(method = "getVelocityAffectingPos", at = @At(value = "HEAD"), cancellable = true)
    public void changeCheckedBlock(CallbackInfoReturnable<BlockPos> cir){
        if(((Entity)(Object)this).world.getBlockState(((Entity)(Object)this).getBlockPos()).getBlock() instanceof BaseSlab){
            if(((Entity)(Object)this).world.getBlockState(((Entity)(Object)this).getBlockPos()).get(SlabBlock.TYPE) == SlabType.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).getBlockPos());
            }
        }

        if(((Entity)(Object)this).world.getBlockState(((Entity)(Object)this).getBlockPos()).getBlock() instanceof BaseStairs){
            if(((Entity)(Object)this).world.getBlockState(((Entity)(Object)this).getBlockPos()).get(StairsBlock.HALF) == BlockHalf.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).getBlockPos());
            }
        }
    }
}
