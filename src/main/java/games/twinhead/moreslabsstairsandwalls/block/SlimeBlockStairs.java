package games.twinhead.moreslabsstairsandwalls.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SlimeBlockStairs extends StairsBlock {


    public SlimeBlockStairs(Settings settings) {
        super(Blocks.SLIME_BLOCK.getDefaultState(), settings);
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

        if(blockState_2.getBlock() == ModBlocks.SLIME_BLOCK_SLAB.get())
            if(isInvisibleToSlimeSlab(blockState_1, blockState_2, direction_1))
                return true;

        if(blockState_2.getBlock() == this)
            if(isInvisibleToSlimeStairs(blockState_1, blockState_2,
                    direction_1))
                return true;

        return super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }

    private boolean isInvisibleToSlimeStairs(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        BlockHalf half1 = blockState_1.get(StairsBlock.HALF);
        BlockHalf half2 = blockState_2.get(StairsBlock.HALF);
        Direction facing1 = blockState_1.get(StairsBlock.FACING);
        Direction facing2 = blockState_2.get(StairsBlock.FACING);
        StairShape shape1 = blockState_1.get(StairsBlock.SHAPE);
        StairShape shape2 = blockState_2.get(StairsBlock.SHAPE);

        // up
        if(direction_1 == Direction.UP)
        {
            if(half2 == BlockHalf.BOTTOM)
                return true;

            if(half1 != half2)
            {
                if(facing1 == facing2 && shape1 == shape2)
                    return true;

                switch(shape1)
                {
                    case STRAIGHT:
                        if(shape2 == StairShape.INNER_LEFT && (facing2 == facing1
                                || facing2 == facing1.rotateYClockwise()))
                            return true;
                        if(shape2 == StairShape.INNER_RIGHT && (facing2 == facing1
                                || facing2 == facing1.rotateYCounterclockwise()))
                            return true;
                        break;

                    case INNER_LEFT:
                        if(shape2 == StairShape.INNER_RIGHT
                                && facing2 == facing1.rotateYCounterclockwise())
                            return true;
                        break;

                    case INNER_RIGHT:
                        if(shape2 == StairShape.INNER_LEFT
                                && facing2 == facing1.rotateYClockwise())
                            return true;
                        break;

                    case OUTER_LEFT:
                        if(shape2 == StairShape.OUTER_RIGHT
                                && facing2 == facing1.rotateYCounterclockwise())
                            return true;
                        if(shape2 == StairShape.STRAIGHT && (facing2 == facing1
                                || facing2 == facing1.rotateYCounterclockwise()))
                            return true;
                        break;

                    case OUTER_RIGHT:
                        if(shape2 == StairShape.OUTER_LEFT
                                && facing2 == facing1.rotateYClockwise())
                            return true;
                        if(shape2 == StairShape.STRAIGHT && (facing2 == facing1
                                || facing2 == facing1.rotateYClockwise()))
                            return true;
                        break;
                }
            }
        }

        // down
        if(direction_1 == Direction.DOWN)
        {
            if(half2 == BlockHalf.TOP)
                return true;

            switch(shape1)
            {
                case STRAIGHT:
                    if(shape2 == StairShape.INNER_LEFT && (facing2 == facing1
                            || facing2 == facing1.rotateYClockwise()))
                        return true;
                    if(shape2 == StairShape.INNER_RIGHT && (facing2 == facing1
                            || facing2 == facing1.rotateYCounterclockwise()))
                        return true;
                    break;

                case INNER_LEFT:
                    if(shape2 == StairShape.INNER_RIGHT
                            && facing2 == facing1.rotateYCounterclockwise())
                        return true;
                    break;

                case INNER_RIGHT:
                    if(shape2 == StairShape.INNER_LEFT
                            && facing2 == facing1.rotateYClockwise())
                        return true;
                    break;

                case OUTER_LEFT:
                    if(shape2 == StairShape.OUTER_RIGHT
                            && facing2 == facing1.rotateYCounterclockwise())
                        return true;
                    if(shape2 == StairShape.STRAIGHT && (facing2 == facing1
                            || facing2 == facing1.rotateYCounterclockwise()))
                        return true;
                    break;

                case OUTER_RIGHT:
                    if(shape2 == StairShape.OUTER_LEFT
                            && facing2 == facing1.rotateYClockwise())
                        return true;
                    if(shape2 == StairShape.STRAIGHT && (facing2 == facing1
                            || facing2 == facing1.rotateYClockwise()))
                        return true;
                    break;
            }
        }

        // other stairs rear
        if(facing2 == direction_1.getOpposite())
            return true;

        // rear
        if(direction_1 == facing1 && half1 == half2
                && shape1 != StairShape.STRAIGHT)
        {
            if(facing2 == facing1.rotateYCounterclockwise()
                    && shape2 != StairShape.OUTER_RIGHT)
                return true;

            if(facing2 == facing1.rotateYClockwise()
                    && shape2 != StairShape.OUTER_LEFT)
                return true;
        }

        // front
        if(direction_1 == facing1.getOpposite() && half1 == half2)
        {
            if(facing2 == facing1.rotateYCounterclockwise()
                    && shape2 != StairShape.OUTER_LEFT)
                return true;

            if(facing2 == facing1.rotateYClockwise()
                    && shape2 != StairShape.OUTER_RIGHT)
                return true;

            if(facing2 == facing1.getOpposite())
                return true;
        }

        // left
        if(direction_1 == facing1.rotateYCounterclockwise() && half1 == half2)
        {
            if(facing2 == direction_1 && shape1 != StairShape.INNER_LEFT
                    && shape2 == StairShape.INNER_RIGHT)
                return true;

            if(facing2 == facing1 && shape2 != StairShape.OUTER_LEFT)
                return true;

            if(facing2 == facing1.getOpposite()
                    && shape1 == StairShape.OUTER_RIGHT)
                return true;
        }

        // right
        if(direction_1 == facing1.rotateYClockwise() && half1 == half2)
        {
            if(facing2 == direction_1 && shape1 != StairShape.INNER_RIGHT
                    && shape2 == StairShape.INNER_LEFT)
                return true;

            if(facing2 == facing1 && shape2 != StairShape.OUTER_RIGHT)
                return true;

            return facing2 == facing1.getOpposite()
                    && shape1 == StairShape.OUTER_LEFT;
        }

        return false;
    }


    private boolean isInvisibleToSlimeSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1){
            BlockHalf half1 = blockState_1.get(StairsBlock.HALF);
            Direction facing1 = blockState_1.get(StairsBlock.FACING);
            StairShape shape1 = blockState_1.get(StairsBlock.SHAPE);
            SlabType type2 = blockState_2.get(SlabBlock.TYPE);

            if(direction_1 == Direction.UP)
                if(type2 != SlabType.TOP)
                    return true;

            if(direction_1 == Direction.DOWN)
                if(type2 != SlabType.BOTTOM)
                    return true;

            if(type2 == SlabType.DOUBLE)
                return true;

            // front
            if(direction_1 == facing1.getOpposite())
            {
                if(type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                    return true;

                if(type2 == SlabType.TOP && half1 == BlockHalf.TOP)
                    return true;
            }

            // right
            if(direction_1 == facing1.rotateYClockwise()
                    && shape1 == StairShape.OUTER_LEFT)
            {
                if(type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                    return true;

                if(type2 == SlabType.TOP && half1 == BlockHalf.TOP)
                    return true;
            }

            // left
            if(direction_1 == facing1.rotateYCounterclockwise()
                    && shape1 == StairShape.OUTER_RIGHT)
            {
                if(type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                    return true;

                return type2 == SlabType.TOP && half1 == BlockHalf.TOP;
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
