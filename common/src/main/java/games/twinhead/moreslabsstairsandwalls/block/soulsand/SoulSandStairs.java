package games.twinhead.moreslabsstairsandwalls.block.soulsand;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class SoulSandStairs extends BaseStairs {

    protected static final VoxelShape COLLISION_BOTTOM_NORTH_WEST_CORNER_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape COLLISION_BOTTOM_SOUTH_WEST_CORNER_SHAPE = Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0);
    protected static final VoxelShape COLLISION_TOP_NORTH_WEST_CORNER_SHAPE = Block.box(0.0, 8.0, 0.0, 8.0, 14.0, 8.0);
    protected static final VoxelShape COLLISION_TOP_SOUTH_WEST_CORNER_SHAPE = Block.box(0.0, 8.0, 8.0, 8.0, 14.0, 16.0);
    protected static final VoxelShape COLLISION_BOTTOM_NORTH_EAST_CORNER_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 6.0, 8.0);
    protected static final VoxelShape COLLISION_BOTTOM_SOUTH_EAST_CORNER_SHAPE = Block.box(8.0, 0.0, 8.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape COLLISION_TOP_NORTH_EAST_CORNER_SHAPE = Block.box(8.0, 8.0, 0.0, 16.0, 14.0, 8.0);
    protected static final VoxelShape COLLISION_TOP_SOUTH_EAST_CORNER_SHAPE = Block.box(8.0, 8.0, 8.0, 16.0, 14.0, 16.0);

    protected static final VoxelShape TOP_SHAPE = SoulSandSlab.COLLISION_SHAPE_TOP;
    protected static final VoxelShape BOTTOM_SHAPE = SoulSandSlab.COLLISION_SHAPE_BOTTOM;

    protected static final VoxelShape[]TOP_SHAPES = makeShapes(TOP_SHAPE, COLLISION_BOTTOM_NORTH_WEST_CORNER_SHAPE, COLLISION_BOTTOM_NORTH_EAST_CORNER_SHAPE, COLLISION_BOTTOM_SOUTH_WEST_CORNER_SHAPE, COLLISION_BOTTOM_SOUTH_EAST_CORNER_SHAPE);
    protected static final VoxelShape[]BOTTOM_SHAPES = makeShapes(BOTTOM_SHAPE, COLLISION_TOP_NORTH_WEST_CORNER_SHAPE, COLLISION_TOP_NORTH_EAST_CORNER_SHAPE, COLLISION_TOP_SOUTH_WEST_CORNER_SHAPE, COLLISION_TOP_SOUTH_EAST_CORNER_SHAPE);

    private static final int[] SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};

    public SoulSandStairs(ModBlocks block, BlockState state, Properties settings) {
        super(block,state, settings);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return (state.getValue(HALF) == Half.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_INDICES[getShapeIndex(state)]];
    }

    private int getShapeIndex(BlockState state) {
        return state.getValue(SHAPE).ordinal() * 4 + state.getValue(FACING).get2DDataValue();
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BubbleColumnBlock.updateColumn(world, pos.above(), state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && neighborState.is(Blocks.WATER) || this.getFluidState(state).is(Fluids.WATER)) {
            world.scheduleTick(pos, this, 20);
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleTick(pos, this, 20);
    }
}
