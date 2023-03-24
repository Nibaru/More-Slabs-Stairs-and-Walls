package games.twinhead.moreslabsstairsandwalls.block.soulsand;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.stream.IntStream;

public class SoulSandStairs extends StairsBlock {

    protected static final VoxelShape COLLISION_BOTTOM_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape COLLISION_BOTTOM_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 8.0, 16.0);
    protected static final VoxelShape COLLISION_TOP_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 8.0, 14.0, 8.0);
    protected static final VoxelShape COLLISION_TOP_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0, 8.0, 8.0, 8.0, 14.0, 16.0);
    protected static final VoxelShape COLLISION_BOTTOM_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 6.0, 8.0);
    protected static final VoxelShape COLLISION_BOTTOM_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape COLLISION_TOP_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 8.0, 0.0, 16.0, 14.0, 8.0);
    protected static final VoxelShape COLLISION_TOP_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 8.0, 8.0, 16.0, 14.0, 16.0);

    protected static final VoxelShape TOP_SHAPE = SoulSandSlab.COLLISION_SHAPE_TOP;
    protected static final VoxelShape BOTTOM_SHAPE = SoulSandSlab.COLLISION_SHAPE_BOTTOM;

    protected static final VoxelShape[]TOP_SHAPES = composeShapes(TOP_SHAPE, COLLISION_BOTTOM_NORTH_WEST_CORNER_SHAPE, COLLISION_BOTTOM_NORTH_EAST_CORNER_SHAPE, COLLISION_BOTTOM_SOUTH_WEST_CORNER_SHAPE, COLLISION_BOTTOM_SOUTH_EAST_CORNER_SHAPE);
    protected static final VoxelShape[]BOTTOM_SHAPES = composeShapes(BOTTOM_SHAPE, COLLISION_TOP_NORTH_WEST_CORNER_SHAPE, COLLISION_TOP_NORTH_EAST_CORNER_SHAPE, COLLISION_TOP_SOUTH_WEST_CORNER_SHAPE, COLLISION_TOP_SOUTH_EAST_CORNER_SHAPE);

    private static final int[] SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};;

    private static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        return (VoxelShape[]) IntStream.range(0, 16).mapToObj((i) -> {
            return composeShape(i, base, northWest, northEast, southWest, southEast);
        }).toArray((i) -> {
            return new VoxelShape[i];
        });
    }



    private static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        VoxelShape voxelShape = base;
        if ((i & 1) != 0) {
            voxelShape = VoxelShapes.union(base, northWest);
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

    public SoulSandStairs(Settings settings) {
        super(Blocks.SOUL_SAND.getDefaultState(), settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (state.get(HALF) == BlockHalf.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_INDICES[getShapeIndexIndex(state)]];
    }

    private int getShapeIndexIndex(BlockState state) {
        return ((StairShape)state.get(SHAPE)).ordinal() * 4 + ((Direction)state.get(FACING)).getHorizontal();
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BubbleColumnBlock.update(world, pos.up(), state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && neighborState.isOf(Blocks.WATER) || this.getFluidState(state).isOf(Fluids.WATER)) {
            world.scheduleBlockTick(pos, this, 20);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20);
    }


}
