package games.twinhead.moreslabsstairsandwalls.block.soulsand;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class SoulSandWall extends WallBlock {

    public SoulSandWall(Settings settings) {
        super(settings);
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
