package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "getBlockPosBelowThatAffectsMyMovement", at = @At(value = "HEAD"), cancellable = true)
    public void changeCheckedBlock(CallbackInfoReturnable<BlockPos> cir) {
        Entity self = (Entity) (Object) this;
        if (self.level().getBlockState(self.blockPosition()).getBlock() instanceof BaseSlab) {
            if (self.level().getBlockState(self.blockPosition()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM) {
                cir.setReturnValue(self.blockPosition());
            }
        }

        if (self.level().getBlockState(self.blockPosition()).getBlock() instanceof BaseStairs) {
            if (self.level().getBlockState(self.blockPosition()).getValue(StairBlock.HALF) == Half.BOTTOM) {
                cir.setReturnValue(self.blockPosition());
            }
        }
    }
}
