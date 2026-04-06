package games.twinhead.moreslabsstairsandwalls.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface LandingSlabBlock {
    default void onLanding(Level world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingSlabBlockEntity fallingBlockEntity) {
    }

    default void onDestroyedOnLanding(Level world, BlockPos pos, FallingSlabBlockEntity fallingBlockEntity) {
    }

    default DamageSource getDamageSource(Entity attacker) {
        return attacker.damageSources().fallingBlock(attacker);
    }
}
