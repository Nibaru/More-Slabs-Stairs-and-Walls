package games.twinhead.moreslabsstairsandwalls.block.slime;

import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SlimeBlockWall extends WallBlock {



    public SlimeBlockWall(Settings arg) {
        super(arg);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!world.isClient())
            if (entity.bypassesLandingEffects()) {
                super.onLandedUpon(world, state, pos, entity, fallDistance);
            } else {
                entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
            }

    }

    public boolean isStickyBlock(BlockState state)
    {
        return isStateHoney(state) || isStateSlime(state);
    }

    public boolean canStickTo(BlockState state, BlockState other)
    {
        if(isStateHoney(state) && isStateSlime(other)){
            return false;
        }

        if(isStateHoney(other) && isStateSlime(state)){
            return false;
        }

        return state.isStickyBlock() || other.isStickyBlock();
    }

    private static boolean isStateHoney(BlockState state){
        if(state.isOf(ModBlocks.HONEY_BLOCK_SLAB.get())){
            return true;
        } else if(state.isOf(ModBlocks.HONEY_BLOCK_STAIRS.get())){
            return true;
        } else if(state.isOf(ModBlocks.HONEY_BLOCK_WALL.get())){
            return true;
        } else return state.isOf(Blocks.HONEY_BLOCK);
    }

    private static boolean isStateSlime(BlockState state){
        if(state.isOf(ModBlocks.SLIME_BLOCK_SLAB.get())){
            return true;
        } else if(state.isOf(ModBlocks.SLIME_BLOCK_STAIRS.get())){
            return true;
        } else if(state.isOf(ModBlocks.SLIME_BLOCK_WALL.get())){
            return true;
        } else return state.isOf(Blocks.SLIME_BLOCK);
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
}
