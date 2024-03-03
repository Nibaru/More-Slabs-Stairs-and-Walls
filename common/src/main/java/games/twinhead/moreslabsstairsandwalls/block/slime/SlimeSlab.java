package games.twinhead.moreslabsstairsandwalls.block.slime;

import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SlimeSlab extends TranslucentSlab {


    public SlimeSlab(ModBlocks modblock,Settings settings) {
        super(modblock, settings);
    }



    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!world.isClient())
            if (entity.bypassesLandingEffects()) {
                super.onLandedUpon(world, state, pos, entity, fallDistance);
            } else {
                entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());
            }

    }

    public void onEntityLand(BlockView world, Entity entity) {
            if (entity.bypassesLandingEffects()) {
                super.onEntityLand(world, entity);
            } else {
                this.bounce(entity);
            }

    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0) {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }

    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1 && !entity.bypassesSteppingEffects()) {
            double e = 0.4 + d * 0.2;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0, e));
        }

        super.onSteppedOn(world, pos, state, entity);
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
            if(state.isOf(ModBlocks.HONEY_BLOCK.getBlock(type))){
                return true;
            }
        }
        return state.isOf(Blocks.HONEY_BLOCK);
    }

    public static boolean isStateSlime(BlockState state){
        for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
            if(state.isOf(ModBlocks.SLIME_BLOCK.getBlock(type))){
                return true;
            }
        }
        return state.isOf(Blocks.SLIME_BLOCK);
    }


}
