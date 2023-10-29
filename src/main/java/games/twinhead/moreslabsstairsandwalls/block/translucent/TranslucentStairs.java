package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public class TranslucentStairs extends BaseStairs {

    final @NotNull ModBlocks modBlock;

    protected static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        return (VoxelShape[]) IntStream.range(0, 16).mapToObj(i -> composeShape(i, base, northWest, northEast, southWest, southEast)).toArray(VoxelShape[]::new);
    }

    protected static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        VoxelShape voxelShape = base;
        if ((i & 1) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, northWest);
        }
        if ((i & 2) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, northEast);
        }
        if ((i & 4) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southWest);
        }
        if ((i & 8) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southEast);
        }
        return voxelShape;
    }

    public TranslucentStairs(BlockState blockState, @NotNull ModBlocks block, Settings settings) {
        super(blockState, settings);
        this.modBlock = block;
    }


    public @NotNull ModBlocks getModBlock() {
        return modBlock;
    }


    @SuppressWarnings("deprecation")
    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state1, BlockState state2, Direction direction) {
        if (state2.getBlock() == modBlock.parentBlock) return true;

        if (state2.getBlock() instanceof TranslucentSlab slab && slab.getModBlock() == getModBlock())
            if (slabInvisible(state1, state2, direction))
                return true;


        if (state2.getBlock() instanceof TranslucentStairs stairs && stairs.getModBlock() == getModBlock())
            if (stairsInvisible(state1, state2, direction)) return true;

        return super.isSideInvisible(state1, state2, direction);
    }

    private boolean slabInvisible(BlockState state1, BlockState state2, Direction direction) {
        BlockHalf half1 = state1.get(StairsBlock.HALF);
        Direction facing1 = state1.get(StairsBlock.FACING);
        StairShape shape1 = state1.get(StairsBlock.SHAPE);
        SlabType type2 = state2.get(SlabBlock.TYPE);

        if (direction == Direction.UP)
            if (type2 != SlabType.TOP)
                return true;

        if (direction == Direction.DOWN)
            if (type2 != SlabType.BOTTOM)
                return true;

        if (type2 == SlabType.DOUBLE)
            return true;

        if (direction == facing1.getOpposite()) {
            if (type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == BlockHalf.TOP)
                return true;
        }

        if (direction == facing1.rotateYClockwise() && shape1 == StairShape.OUTER_LEFT) {
            if (type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == BlockHalf.TOP)
                return true;
        }

        if (direction == facing1.rotateYCounterclockwise() && shape1 == StairShape.OUTER_RIGHT) {
            if (type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                return true;

            if (type2 == SlabType.TOP && half1 == BlockHalf.TOP)
                return true;
        }

        if (direction == facing1.rotateYCounterclockwise() && shape1 == StairShape.INNER_RIGHT) {
            if (type2 == SlabType.BOTTOM && half1 == BlockHalf.BOTTOM)
                return true;

            return type2 == SlabType.TOP && half1 == BlockHalf.TOP;
        }





        return false;
    }

    private boolean stairsInvisible(BlockState state1, BlockState state2, Direction direction) {
        BlockHalf half1 = state1.get(StairsBlock.HALF);
        BlockHalf half2 = state2.get(StairsBlock.HALF);
        Direction facing1 = state1.get(StairsBlock.FACING);
        Direction facing2 = state2.get(StairsBlock.FACING);
        StairShape shape1 = state1.get(StairsBlock.SHAPE);
        StairShape shape2 = state2.get(StairsBlock.SHAPE);

        if (direction == Direction.UP) {
            if (half2 == BlockHalf.BOTTOM)
                return true;

            if (half1 != half2) {
                if (facing1 == facing2 && shape1 == shape2)
                    return true;
                switch (shape1) {
                    case STRAIGHT -> {
                        if (shape2 == StairShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.rotateYClockwise()))
                            return true;
                        if (shape2 == StairShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.rotateYCounterclockwise()))
                            return true;
                    }
                    case INNER_LEFT -> {
                        if (shape2 == StairShape.INNER_RIGHT && facing2 == facing1.rotateYCounterclockwise())
                            return true;
                    }
                    case INNER_RIGHT -> {
                        if (shape2 == StairShape.INNER_LEFT && facing2 == facing1.rotateYClockwise())
                            return true;
                    }
                    case OUTER_LEFT -> {
                        if (shape2 == StairShape.OUTER_RIGHT && facing2 == facing1.rotateYCounterclockwise())
                            return true;
                        if (shape2 == StairShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.rotateYCounterclockwise()))
                            return true;
                    }

                    case OUTER_RIGHT -> {
                        if (shape2 == StairShape.OUTER_LEFT && facing2 == facing1.rotateYClockwise())
                            return true;
                        if (shape2 == StairShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.rotateYClockwise()))
                            return true;
                    }
                }
            }
        }

        if (direction == Direction.DOWN) {
            if (half2 == BlockHalf.TOP)
                return true;
            switch (shape1) {
                case STRAIGHT -> {
                    if (shape2 == StairShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.rotateYClockwise()))
                        return true;
                    if (shape2 == StairShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.rotateYCounterclockwise()))
                        return true;
                }
                case INNER_LEFT -> {
                    if (shape2 == StairShape.INNER_RIGHT && facing2 == facing1.rotateYCounterclockwise())
                        return true;
                }
                case INNER_RIGHT -> {
                    if (shape2 == StairShape.INNER_LEFT && facing2 == facing1.rotateYClockwise())
                        return true;
                }
                case OUTER_LEFT -> {
                    if (shape2 == StairShape.OUTER_RIGHT && facing2 == facing1.rotateYCounterclockwise())
                        return true;
                    if (shape2 == StairShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.rotateYCounterclockwise()))
                        return true;
                }
                case OUTER_RIGHT -> {
                    if (shape2 == StairShape.OUTER_LEFT && facing2 == facing1.rotateYClockwise())
                        return true;
                    if (shape2 == StairShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.rotateYClockwise()))
                        return true;
                }
            }
        }

        if (facing2 == direction.getOpposite())
            return true;

        if (direction == facing1 && half1 == half2 && shape1 != StairShape.STRAIGHT) {
            if (facing2 == facing1.rotateYCounterclockwise() && shape2 != StairShape.OUTER_RIGHT) return true;
            if (facing2 == facing1.rotateYClockwise() && shape2 != StairShape.OUTER_LEFT) return true;
        }

        if (direction == facing1.getOpposite() && half1 == half2) {
            if (facing2 == facing1.rotateYCounterclockwise() && shape2 != StairShape.OUTER_LEFT) return true;
            if (facing2 == facing1.rotateYClockwise() && shape2 != StairShape.OUTER_RIGHT) return true;
            if (facing2 == facing1.getOpposite()) return true;
        }

        if (direction == facing1.rotateYCounterclockwise() && half1 == half2) {
            if (facing2 == direction && shape1 != StairShape.INNER_LEFT && shape2 == StairShape.INNER_RIGHT) return true;
            if (facing2 == facing1 && shape2 != StairShape.OUTER_LEFT) return true;
            if (facing2 == facing1.getOpposite() && shape1 == StairShape.OUTER_RIGHT) return true;
        }


        if (direction == facing1.rotateYClockwise() && half1 == half2) {
            if (facing2 == direction && shape1 != StairShape.INNER_RIGHT && shape2 == StairShape.INNER_LEFT) return true;
            if (facing2 == facing1 && shape2 != StairShape.OUTER_RIGHT) return true;
            return facing2 == facing1.getOpposite() && shape1 == StairShape.OUTER_LEFT;
        }

        return false;
    }
}
