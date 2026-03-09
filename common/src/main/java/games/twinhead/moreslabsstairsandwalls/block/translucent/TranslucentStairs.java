package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;

@SuppressWarnings("deprecation")
public class TranslucentStairs extends BaseStairs {


    public TranslucentStairs(ModBlocks modBlocks, BlockState blockState, Properties settings) {
        super(modBlocks,blockState, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean skipRendering(BlockState state1, BlockState state2, Direction direction) {
        if (state2.getBlock() == getModBlock().parentBlock) return true;

        if (state2.getBlock() instanceof TranslucentSlab slab && slab.getModBlock() == getModBlock())
            if (slabInvisible(state1, state2, direction))
                return true;

        if (state2.getBlock() instanceof TranslucentStairs stairs && stairs.getModBlock() == getModBlock())
            if (stairsInvisible(state1, state2, direction)) return true;

        return super.skipRendering(state1, state2, direction);
    }

    private boolean slabInvisible(BlockState state1, BlockState state2, Direction direction) {
        Half half1 = state1.getValue(StairBlock.HALF);
        Direction facing1 = state1.getValue(StairBlock.FACING);
        StairsShape shape1 = state1.getValue(StairBlock.SHAPE);
        SlabType type2 = state2.getValue(SlabBlock.TYPE);

        if (direction == Direction.UP)
            if (type2 != SlabType.TOP)
                return true;

        if (direction == Direction.DOWN)
            if (type2 != SlabType.BOTTOM)
                return true;

        if (type2 == SlabType.DOUBLE)
            return true;

        if (direction == facing1.getOpposite()) {
            if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == Half.TOP)
                return true;
        }

        if (direction == facing1.getClockWise() && shape1 == StairsShape.OUTER_LEFT) {
            if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == Half.TOP)
                return true;
        }

        if (direction == facing1.getCounterClockWise() && shape1 == StairsShape.OUTER_RIGHT) {
            if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == Half.TOP)
                return true;
        }

        if (direction == facing1.getCounterClockWise() && shape1 == StairsShape.INNER_RIGHT) {
            if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
                return true;

            return type2 == SlabType.TOP && half1 == Half.TOP;
        }





        return false;
    }

    private boolean stairsInvisible(BlockState state1, BlockState state2, Direction direction) {
        Half half1 = state1.getValue(StairBlock.HALF);
        Half half2 = state2.getValue(StairBlock.HALF);
        Direction facing1 = state1.getValue(StairBlock.FACING);
        Direction facing2 = state2.getValue(StairBlock.FACING);
        StairsShape shape1 = state1.getValue(StairBlock.SHAPE);
        StairsShape shape2 = state2.getValue(StairBlock.SHAPE);

        if (direction == Direction.UP) {
            if (half2 == Half.BOTTOM)
                return true;

            if (half1 != half2) {
                if (facing1 == facing2 && shape1 == shape2)
                    return true;
                switch (shape1) {
                    case STRAIGHT -> {
                        if (shape2 == StairsShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.getClockWise()))
                            return true;
                        if (shape2 == StairsShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise()))
                            return true;
                    }
                    case INNER_LEFT -> {
                        if (shape2 == StairsShape.INNER_RIGHT && facing2 == facing1.getCounterClockWise())
                            return true;
                    }
                    case INNER_RIGHT -> {
                        if (shape2 == StairsShape.INNER_LEFT && facing2 == facing1.getClockWise())
                            return true;
                    }
                    case OUTER_LEFT -> {
                        if (shape2 == StairsShape.OUTER_RIGHT && facing2 == facing1.getCounterClockWise())
                            return true;
                        if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise()))
                            return true;
                    }

                    case OUTER_RIGHT -> {
                        if (shape2 == StairsShape.OUTER_LEFT && facing2 == facing1.getClockWise())
                            return true;
                        if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getClockWise()))
                            return true;
                    }
                }
            }
        }

        if (direction == Direction.DOWN) {
            if (half2 == Half.TOP)
                return true;
            switch (shape1) {
                case STRAIGHT -> {
                    if (shape2 == StairsShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.getClockWise()))
                        return true;
                    if (shape2 == StairsShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise()))
                        return true;
                }
                case INNER_LEFT -> {
                    if (shape2 == StairsShape.INNER_RIGHT && facing2 == facing1.getCounterClockWise())
                        return true;
                }
                case INNER_RIGHT -> {
                    if (shape2 == StairsShape.INNER_LEFT && facing2 == facing1.getClockWise())
                        return true;
                }
                case OUTER_LEFT -> {
                    if (shape2 == StairsShape.OUTER_RIGHT && facing2 == facing1.getCounterClockWise())
                        return true;
                    if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise()))
                        return true;
                }
                case OUTER_RIGHT -> {
                    if (shape2 == StairsShape.OUTER_LEFT && facing2 == facing1.getClockWise())
                        return true;
                    if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getClockWise()))
                        return true;
                }
            }
        }

        if (facing2 == direction.getOpposite())
            return true;

        if (direction == facing1 && half1 == half2 && shape1 != StairsShape.STRAIGHT) {
            if (facing2 == facing1.getCounterClockWise() && shape2 != StairsShape.OUTER_RIGHT) return true;
            if (facing2 == facing1.getClockWise() && shape2 != StairsShape.OUTER_LEFT) return true;
        }

        if (direction == facing1.getOpposite() && half1 == half2) {
            if (facing2 == facing1.getCounterClockWise() && shape2 != StairsShape.OUTER_LEFT) return true;
            if (facing2 == facing1.getClockWise() && shape2 != StairsShape.OUTER_RIGHT) return true;
            if (facing2 == facing1.getOpposite()) return true;
        }

        if (direction == facing1.getCounterClockWise() && half1 == half2) {
            if (facing2 == direction && shape1 != StairsShape.INNER_LEFT && shape2 == StairsShape.INNER_RIGHT) return true;
            if (facing2 == facing1 && shape2 != StairsShape.OUTER_LEFT) return true;
            if (facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_RIGHT) return true;
        }


        if (direction == facing1.getClockWise() && half1 == half2) {
            if (facing2 == direction && shape1 != StairsShape.INNER_RIGHT && shape2 == StairsShape.INNER_LEFT) return true;
            if (facing2 == facing1 && shape2 != StairsShape.OUTER_RIGHT) return true;
            return facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_LEFT;
        }

        return false;
    }
}
