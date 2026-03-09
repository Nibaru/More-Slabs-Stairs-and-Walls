package games.twinhead.moreslabsstairsandwalls.block.honey;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentWall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
@SuppressWarnings("deprecation")
public class HoneyWall extends TranslucentWall {

    public HoneyWall(ModBlocks modBlocks, Properties settings) {
        super(modBlocks, settings);
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f);
        if (!world.isClientSide) {
            world.broadcastEntityEvent(entity, EntityEvent.HONEY_JUMP);
        }
        if (entity.causeFallDamage(fallDistance, 0.2f, world.damageSources().fall())) {
            entity.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5f, this.soundType.getPitch() * 0.75f);
        }
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (isSliding(pos, entity)) {
            HoneySlab.triggerAdvancement(entity, pos);
            HoneySlab.updateSlidingVelocity(entity);
            HoneySlab.addCollisionEffects(world, entity);
        }
        super.entityInside(state, world, pos, entity);
    }

    private boolean isSliding(BlockPos pos, Entity entity) {
        if (entity.onGround()) {
            return false;
        }
        if (entity.getY() > (double)pos.getY() + 1.4375 - 1.0E-7) {
            return false;
        }
        if (entity.getDeltaMovement().y >= -0.08) {
            return false;
        }
        VoxelShape shape = this.getCollisionShape(entity.level().getBlockState(pos), entity.level(), pos, CollisionContext.empty());
        double entityRadius = entity.getBbWidth() / 2.0f;
        return entity.getX() - entityRadius + 1.0E-7 > shape.max(Direction.Axis.X) ||
                entity.getZ() - entityRadius + 1.0E-7 > shape.max(Direction.Axis.Z) ||
                entity.getX() + entityRadius - 1.0E-7 < shape.min(Direction.Axis.X) ||
                entity.getZ() + entityRadius - 1.0E-7 < shape.min(Direction.Axis.Z);
    }

    /**
     * @return true if the block is sticky block which used for pull or push adjacent blocks (use by piston)
     */
    public boolean isStickyBlock(BlockState state) {
        return SlimeSlab.isStateHoney(state) || SlimeSlab.isStateSlime(state);
    }

    /**
     * Determines if this block can stick to another block when pushed by a piston.
     *
     * @param other Other block
     * @return True to link blocks
     */
    public boolean canStickTo(BlockState state, BlockState other) {
        if (SlimeSlab.isStateSlime(state) && SlimeSlab.isStateHoney(other)) return false;
        if (SlimeSlab.isStateSlime(other) && SlimeSlab.isStateHoney(state)) return false;
        return isStickyBlock(state) || isStickyBlock(other);
    }
}