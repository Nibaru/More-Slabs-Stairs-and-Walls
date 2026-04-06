package games.twinhead.moreslabsstairsandwalls.block.dirt;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("deprecation")
public class PathWall extends BaseWall {

    public PathWall(ModBlocks modBlocks, Properties settings) {
        super(modBlocks,settings);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if(!this.canSurvive(ctx.getLevel().getBlockState(ctx.getClickedPos()), ctx.getLevel(), ctx.getClickedPos())){
            return Block.pushEntitiesUp(this.defaultBlockState(), ModBlocks.DIRT.getBlock(ModBlocks.BlockType.WALL).getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos());
        }
        return super.getStateForPlacement(ctx);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !canSurvive(world.getBlockState(pos), world, pos)) {
            world.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        world.setBlockAndUpdate(pos, pushEntitiesUp(state, ModBlocks.DIRT.getBlock(ModBlocks.BlockType.WALL).withPropertiesOf(state), world, pos));
    }


    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.above());
        return !blockState.isRedstoneConductor(world, pos) || blockState.getBlock() instanceof FenceGateBlock;
    }


}
