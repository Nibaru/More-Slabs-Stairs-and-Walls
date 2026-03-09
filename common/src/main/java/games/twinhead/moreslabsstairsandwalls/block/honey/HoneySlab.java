package games.twinhead.moreslabsstairsandwalls.block.honey;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
@SuppressWarnings("deprecation")
public class HoneySlab extends TranslucentSlab {

    protected static final VoxelShape FULL_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    public static final VoxelShape BOTTOM_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);
    public static final VoxelShape TOP_SHAPE = Block.box(1.0, 8.0, 1.0, 15.0, 15.0, 15.0);

    public HoneySlab(ModBlocks modBlocks, Properties settings) {
        super(modBlocks, settings);
    }

    public static boolean hasHoneyBlockEffects(Entity entity) {
        return entity instanceof LivingEntity || entity instanceof AbstractMinecart || entity instanceof PrimedTnt || entity instanceof Boat;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        SlabType slabType = state.getValue(TYPE);
        return switch (slabType) {
            case DOUBLE -> FULL_SHAPE;
            case TOP -> TOP_AABB;
            default -> BOTTOM_AABB;
        };
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
            triggerAdvancement(entity, pos);
            updateSlidingVelocity(entity);
            addCollisionEffects(world, entity);
        }
        super.entityInside(state, world, pos, entity);
    }

    private boolean isSliding(BlockPos pos, Entity entity) {
        if (entity.onGround()) {
            return false;
        }
        SlabType slabType = entity.level().getBlockState(pos).getValue(TYPE);
        double maxY = switch (slabType) {
            case DOUBLE, TOP -> 0.9375;
            default -> 0.4375;
        };
        if (entity.getY() > (double)pos.getY() + maxY - 1.0E-7) {
            return false;
        }
        if (entity.getDeltaMovement().y >= -0.08) {
            return false;
        }
        double d = Math.abs((double)pos.getX() + 0.5 - entity.getX());
        double e = Math.abs((double)pos.getZ() + 0.5 - entity.getZ());
        double f = 0.4375 + (double)(entity.getBbWidth() / 2.0f);
        return d + 1.0E-7 > f || e + 1.0E-7 > f;
    }

    public static void triggerAdvancement(Entity entity, BlockPos ignoredPos) {
        if (entity instanceof ServerPlayer && entity.level().getGameTime() % 20L == 0L) {
            CriteriaTriggers.HONEY_BLOCK_SLIDE.trigger((ServerPlayer)entity, Blocks.HONEY_BLOCK.defaultBlockState());
        }
    }

    public static void updateSlidingVelocity(Entity entity) {
        Vec3 vec3d = entity.getDeltaMovement();
        if (vec3d.y < -0.13) {
            double d = -0.05 / vec3d.y;
            entity.setDeltaMovement(new Vec3(vec3d.x * d, -0.05, vec3d.z * d));
        } else {
            entity.setDeltaMovement(new Vec3(vec3d.x, -0.05, vec3d.z));
        }
        entity.resetFallDistance();
    }

    public static void addCollisionEffects(Level world, Entity entity) {
        if (hasHoneyBlockEffects(entity)) {
            if (world.random.nextInt(5) == 0) {
                entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f);
            }
            if (!world.isClientSide && world.random.nextInt(5) == 0) {
                world.broadcastEntityEvent(entity, EntityEvent.HONEY_SLIDE);
            }
        }
    }

    /**
     * used on neoForge to determine if the block is sticky block which used for pull or push adjacent blocks (use by piston)
     * @return true if the block is sticky block which used for pull or push adjacent blocks (use by piston)
     */
    public boolean isStickyBlock(BlockState state) {
        return SlimeSlab.isStateHoney(state) || SlimeSlab.isStateSlime(state);
    }

    /**
     * Used on NeoForge to determine if this block can stick to another block when pushed by a piston.
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
