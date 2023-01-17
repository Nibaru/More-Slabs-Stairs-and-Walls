package games.twinhead.moreslabsstairsandwalls.block.leaves;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LeavesSlabBlock extends SlabBlock {
    public static final int MAX_DISTANCE = 7;
    public static final IntProperty DISTANCE = Properties.DISTANCE_1_7;
    public static final BooleanProperty PERSISTENT = Properties.PERSISTENT;

    public LeavesSlabBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getOutlineShape(state, world, pos, context);
    }

    public boolean hasRandomTicks(BlockState state) {
        return (Integer)state.get(DISTANCE) == 7 && !(Boolean)state.get(PERSISTENT);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldDecay(state)) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }
    }



    protected boolean shouldDecay(BlockState state) {
        return !(Boolean)state.get(PERSISTENT) && (Integer)state.get(DISTANCE) == 7;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), 3);
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || (Integer)state.get(DISTANCE) != i) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int i = 7;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            mutable.set(pos, direction);
            i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return (BlockState)state.with(DISTANCE, i);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeavesBlock ? (Integer)state.get(DISTANCE) : 7;
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() - 0.05;
                    double f = (double)pos.getZ() + random.nextDouble();
                    world.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT);
        super.appendProperties(builder);
    }
}
