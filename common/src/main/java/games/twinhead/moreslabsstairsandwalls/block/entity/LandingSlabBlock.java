package games.twinhead.moreslabsstairsandwalls.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface LandingSlabBlock {
    default public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingSlabBlockEntity fallingBlockEntity) {
    }

    default public void onDestroyedOnLanding(World world, BlockPos pos, FallingSlabBlockEntity fallingBlockEntity) {
    }

    default public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingBlock(attacker);
    }
}
