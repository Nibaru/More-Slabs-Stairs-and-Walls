package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin{

    public EntityMixin()  {}

    @Inject(method = "getBlockPosBelowThatAffectsMyMovement", at = @At(value = "HEAD"), cancellable = true)
    public void changeCheckedBlock(CallbackInfoReturnable<BlockPos> cir){
        if(((Entity)(Object)this).level().getBlockState(((Entity)(Object)this).blockPosition()).getBlock() instanceof BaseSlab){
            if(((Entity)(Object)this).level().getBlockState(((Entity)(Object)this).blockPosition()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).blockPosition());
            }
        }

        if(((Entity)(Object)this).level().getBlockState(((Entity)(Object)this).blockPosition()).getBlock() instanceof BaseStairs){
            if(((Entity)(Object)this).level().getBlockState(((Entity)(Object)this).blockPosition()).getValue(StairBlock.HALF) == Half.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).blockPosition());
            }
        }
    }
}
