package games.twinhead.moreslabsstairsandwalls.block;

import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SlimeBlockSlab extends SlabBlock {


    public SlimeBlockSlab(Settings settings) {
        super(settings);
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


    @SuppressWarnings("deprecation")
    @Override
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        if(blockState_2.getBlock() == Blocks.SLIME_BLOCK)
            return true;

        if(blockState_2.getBlock() == this)
            if(isInvisibleToSlimeSlab(blockState_1, blockState_2, direction_1))
                return true;


        if(blockState_2.getBlock() == ModBlocks.SLIME_BLOCK_STAIRS.get())
            if(isInvisibleToSlimeStairs(blockState_1, blockState_2,
                    direction_1))
                return true;


        return super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }

    private boolean isInvisibleToSlimeStairs(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        SlabType type1 = blockState_1.get(SlabBlock.TYPE);
        BlockHalf half2 = blockState_2.get(StairsBlock.HALF);
        Direction facing2 = blockState_2.get(StairsBlock.FACING);

        // up
        if(direction_1 == Direction.UP)
            if(half2 == BlockHalf.BOTTOM)
                return true;

        // down
        if(direction_1 == Direction.DOWN)
            if(half2 == BlockHalf.TOP)
                return true;

        // other stairs rear
        if(facing2 == direction_1.getOpposite())
            return true;

        // sides
        if(direction_1.getHorizontal() != -1)
        {
            if(type1 == SlabType.BOTTOM && half2 == BlockHalf.BOTTOM)
                return true;

            return type1 == SlabType.TOP && half2 == BlockHalf.TOP;
        }

        return false;
    }

    private boolean isInvisibleToSlimeSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        SlabType type1 = blockState_1.get(SlabBlock.TYPE);
        SlabType type2 = blockState_2.get(SlabBlock.TYPE);

        if(type2 == SlabType.DOUBLE)
            return true;

        switch(direction_1) {
            case UP, DOWN:
                if(type1 != type2)
                    return true;
                break;

            case NORTH,EAST,SOUTH,WEST:
                if(type1 == type2)
                    return true;
                break;
        }

        return false;
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
