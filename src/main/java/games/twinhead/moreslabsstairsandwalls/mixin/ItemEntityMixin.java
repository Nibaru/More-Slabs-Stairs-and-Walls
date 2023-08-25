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
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public class ItemEntityMixin extends Entity {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    public float addIceSlipperiness(Block instance){
        if(((ItemEntity)(Object)this).getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock() instanceof BaseSlab){
            if(getWorld().getBlockState(this.getVelocityAffectingPos()).get(SlabBlock.TYPE) == SlabType.BOTTOM)
                return getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness();
        }
        if(((ItemEntity)(Object)this).getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock() instanceof BaseStairs){
            if(getWorld().getBlockState(this.getVelocityAffectingPos()).get(StairsBlock.HALF) == BlockHalf.BOTTOM)
                return getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness();
        }
        return instance.getSlipperiness();
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