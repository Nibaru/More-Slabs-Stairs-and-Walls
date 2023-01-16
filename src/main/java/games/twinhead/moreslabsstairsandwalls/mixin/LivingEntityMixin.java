package games.twinhead.moreslabsstairsandwalls.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "isOnSoulSpeedBlock", at = @At(value = "HEAD"), cancellable = true)
    public void isOnSoulBlock(CallbackInfoReturnable<Boolean> cir){
        if(((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof SlabBlock ||
                ((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof StairsBlock ||
                ((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof WallBlock){
            if( ((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock().getDefaultState().isIn(BlockTags.SOUL_SPEED_BLOCKS)){
                cir.setReturnValue(true);
            }

        }
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    public float addIceSlipperiness(Block instance){
        if(((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof SlabBlock carpetBlock){
            if(world.getBlockState(this.getVelocityAffectingPos().up()).get(SlabBlock.TYPE) == SlabType.BOTTOM)
                return carpetBlock.getSlipperiness();
        }
        if(((LivingEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof StairsBlock carpetBlock){
            if(world.getBlockState(this.getVelocityAffectingPos().up()).get(StairsBlock.HALF) == BlockHalf.BOTTOM)
                return carpetBlock.getSlipperiness();
        }
        return instance.getSlipperiness();
    }


    @Shadow
    protected void initDataTracker() {

    }

    @Shadow
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Shadow
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Shadow
    public Packet<?> createSpawnPacket() {
        return null;
    }
}
