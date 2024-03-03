package games.twinhead.moreslabsstairsandwalls.block.soulsand;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class SoulSandSlab extends BaseSlab {

    protected static final VoxelShape COLLISION_SHAPE_BOTTOM = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape COLLISION_SHAPE_TOP = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 14.0, 16.0);
    protected static final VoxelShape COLLISION_SHAPE_FULL = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

    public SoulSandSlab(ModBlocks modblock, Settings settings) {
        super(modblock,settings);
    }


    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(SlabBlock.TYPE)){
            case TOP -> COLLISION_SHAPE_TOP;
            case BOTTOM -> COLLISION_SHAPE_BOTTOM;
            case DOUBLE -> COLLISION_SHAPE_FULL;
        };
    }

    @SuppressWarnings("deprecation")
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

    @SuppressWarnings("deprecation")
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20);
    }
}
