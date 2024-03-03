package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin extends Entity {

    @Inject(method = "getVelocityAffectingPos", at = @At(value = "HEAD"), cancellable = true)
    public void changeCheckedBlock(CallbackInfoReturnable<BlockPos> cir){
        if(((Entity)(Object)this).getWorld().getBlockState(((Entity)(Object)this).getBlockPos()).getBlock() instanceof BaseSlab){
            if(((Entity)(Object)this).getWorld().getBlockState(((Entity)(Object)this).getBlockPos()).get(SlabBlock.TYPE) == SlabType.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).getBlockPos());
            }
        }

        if(((Entity)(Object)this).getWorld().getBlockState(((Entity)(Object)this).getBlockPos()).getBlock() instanceof BaseStairs){
            if(((Entity)(Object)this).getWorld().getBlockState(((Entity)(Object)this).getBlockPos()).get(StairsBlock.HALF) == BlockHalf.BOTTOM){
                cir.setReturnValue(((Entity)(Object)this).getBlockPos());
            }
        }
    }

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    protected void initDataTracker() {

    }

    @Shadow
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Shadow
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}