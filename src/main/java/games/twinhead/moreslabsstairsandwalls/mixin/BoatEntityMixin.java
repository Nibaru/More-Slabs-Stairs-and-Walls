package games.twinhead.moreslabsstairsandwalls.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public class BoatEntityMixin extends Entity {

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "method_7548", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getFriction(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)F"))
    public float addIceSlipperiness(BlockState instance, WorldView world, BlockPos pos, Entity entity){
        if(((BoatEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof SlabBlock carpetBlock){
            if(world.getBlockState(this.getVelocityAffectingPos().up()).get(SlabBlock.TYPE) == SlabType.BOTTOM)
                return carpetBlock.getSlipperiness();
        }
        if(((BoatEntity)(Object)this).world.getBlockState(this.getVelocityAffectingPos().up()).getBlock() instanceof StairsBlock carpetBlock){
            if(world.getBlockState(this.getVelocityAffectingPos().up()).get(StairsBlock.HALF) == BlockHalf.BOTTOM)
                return carpetBlock.getSlipperiness();
        }
        return instance.getFriction(((BoatEntity)(Object)this).world, getVelocityAffectingPos(), ((BoatEntity)(Object)this));
    }

    protected BlockPos getVelocityAffectingPos() {
        return new BlockPos(((BoatEntity)(Object)this).getPos().x, ((BoatEntity)(Object)this).getBoundingBox().minY - 0.5000001, ((BoatEntity)(Object)this).getPos().z);
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

    @Shadow
    public Packet<?> createSpawnPacket() {
        return null;
    }
}
