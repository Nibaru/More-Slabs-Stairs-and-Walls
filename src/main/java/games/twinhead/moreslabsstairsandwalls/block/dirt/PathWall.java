package games.twinhead.moreslabsstairsandwalls.block.dirt;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class PathWall extends BaseWall {

    public PathWall(Settings settings) {
        super(settings);
    }



    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(!this.canPlaceAt(ctx.getWorld().getBlockState(ctx.getBlockPos()), ctx.getWorld(), ctx.getBlockPos())){
            return Block.pushEntitiesUpBeforeBlockChange(this.getDefaultState(), ModBlocks.DIRT.getBlock(ModBlocks.BlockType.WALL).getPlacementState(ctx), ctx.getWorld(), ctx.getBlockPos());
        }
        return super.getPlacementState(ctx);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !canPlaceAt(world.getBlockState(pos), world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, ModBlocks.DIRT.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state), world, pos));
    }


    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return !blockState.isSolidBlock(world, pos) || blockState.getBlock() instanceof FenceGateBlock;
    }


}
