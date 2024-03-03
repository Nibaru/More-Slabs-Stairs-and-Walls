package games.twinhead.moreslabsstairsandwalls.block.spreadable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class SpreadableStairs extends DirtStairs implements Waterloggable {

    public static final BooleanProperty SNOWY;

    public SpreadableStairs(ModBlocks block,BlockState defaultState, Settings settings) {
        super(block,defaultState, settings);
    }


    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!SpreadableSlab.canSurvive(state, world, pos)) {
            ModBlocks deadBase = ModBlocks.DIRT;
            if (state.isOf(ModBlocks.WARPED_NYLIUM.getBlock(ModBlocks.BlockType.STAIRS))
                    || state.isOf(ModBlocks.CRIMSON_NYLIUM.getBlock(ModBlocks.BlockType.STAIRS))) {
                deadBase = ModBlocks.NETHERRACK;
            }
            world.setBlockState(pos, deadBase.getBlock(ModBlocks.BlockType.STAIRS).getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    SpreadableSlab.trySpread(world, getModBlock().parentBlock, blockPos);
                }
            }
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE, WATERLOGGED, SNOWY);
    }

    static {
        SNOWY = Properties.SNOWY;
    }
}
