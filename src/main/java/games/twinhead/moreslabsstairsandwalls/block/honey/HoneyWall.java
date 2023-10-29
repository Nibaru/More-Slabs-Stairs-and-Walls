package games.twinhead.moreslabsstairsandwalls.block.honey;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class HoneyWall extends BaseWall {

    public HoneyWall(Settings settings) {
        super(settings);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 1.0f);
        if (!world.isClient) {
            world.sendEntityStatus(entity, EntityStatuses.DRIP_RICH_HONEY);
        }
        if (entity.handleFallDamage(fallDistance, 0.2f, world.getDamageSources().fall())) {
            entity.playSound(this.soundGroup.getFallSound(), this.soundGroup.getVolume() * 0.5f, this.soundGroup.getPitch() * 0.75f);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isSliding(pos, entity)) {
            HoneySlab.triggerAdvancement(entity, pos);
            HoneySlab.updateSlidingVelocity(entity);
            HoneySlab.addCollisionEffects(world, entity);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    private boolean isSliding(BlockPos pos, Entity entity) {
        if (entity.isOnGround()) {
            return false;
        }
        if (entity.getY() > (double)pos.getY() + 1.4375 - 1.0E-7) {
            return false;
        }
        if (entity.getVelocity().y >= -0.08) {
            return false;
        }
        VoxelShape shape = this.getCollisionShape(entity.getWorld().getBlockState(pos), entity.getWorld(), pos, ShapeContext.absent());
        double entityRadius = entity.getWidth() / 2.0f;
        return entity.getX() - entityRadius + 1.0E-7 > shape.getMax(Direction.Axis.X) ||
                entity.getZ() - entityRadius + 1.0E-7 > shape.getMax(Direction.Axis.Z) ||
                entity.getX() + entityRadius - 1.0E-7 < shape.getMin(Direction.Axis.X) ||
                entity.getZ() + entityRadius - 1.0E-7 < shape.getMin(Direction.Axis.Z);
    }
}
