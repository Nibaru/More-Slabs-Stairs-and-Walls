package games.twinhead.moreslabsstairsandwalls.block.slime;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SlimeSlab extends TranslucentSlab {


    public SlimeSlab(ModBlocks block,Properties settings) {
        super(block, settings);
    }



    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!world.isClientSide())
            if (entity.isSuppressingBounce()) {
                super.fallOn(world, state, pos, entity, fallDistance);
            } else {
                entity.causeFallDamage(fallDistance, 0.0F, world.damageSources().fall());
            }

    }

    public void updateEntityAfterFallOn(BlockGetter world, Entity entity) {
            if (entity.isSuppressingBounce()) {
                super.updateEntityAfterFallOn(world, entity);
            } else {
                this.bounce(entity);
            }

    }

    private void bounce(Entity entity) {
        Vec3 vec3d = entity.getDeltaMovement();
        if (vec3d.y < 0.0) {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setDeltaMovement(vec3d.x, -vec3d.y * d, vec3d.z);
        }

    }

    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getDeltaMovement().y);
        if (d < 0.1 && !entity.isSteppingCarefully()) {
            double e = 0.4 + d * 0.2;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(e, 1.0, e));
        }

        super.stepOn(world, pos, state, entity);
    }


    public boolean isSlimeBlock(BlockState state) {
        return isStateSlime(state);
    }

    /**
     * @return true if the block is sticky block which used for pull or push adjacent blocks (use by piston)
     */
    public boolean isStickyBlock(BlockState state) {
        return isStateHoney(state) || isStateSlime(state);
    }

    /**
     * Determines if this block can stick to another block when pushed by a piston.
     *
     * @param other Other block
     * @return True to link blocks
     */
    public boolean canStickTo(BlockState state, BlockState other) {
        if (isStateSlime(state) && isStateHoney(other)) return false;
        if (isStateSlime(other) && isStateHoney(state)) return false;
        return isStickyBlock(state) || isStickyBlock(other);
    }


    public static boolean isStateHoney(BlockState state){
        for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
            if(state.is(ModBlocks.HONEY_BLOCK.getBlock(type))){
                return true;
            }
        }
        return state.is(Blocks.HONEY_BLOCK);
    }

    public static boolean isStateSlime(BlockState state){
        for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
            if(state.is(ModBlocks.SLIME_BLOCK.getBlock(type))){
                return true;
            }
        }
        return state.is(Blocks.SLIME_BLOCK);
    }


}
