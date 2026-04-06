package games.twinhead.moreslabsstairsandwalls.block.leaves;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class LeavesStairs extends BaseStairs {

    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public LeavesStairs(ModBlocks block,  BlockState baseBlockState, Properties settings) {
        super(block, baseBlockState, settings);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return super.getShape(state, world, pos, context);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(DISTANCE) == 7 && !(Boolean)state.getValue(PERSISTENT);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (this.shouldDecay(state)) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    public boolean shouldDecay(BlockState state) {
        return !(Boolean)state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7;
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        world.setBlock(pos, LeavesSlab.updateDistanceFromLogs(state, world, pos), 3);
    }

    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 1;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        int i = LeavesSlab.getDistanceFromLog(neighborState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            world.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }


    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (world.isRainingAt(pos.above())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.below();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.canOcclude() || !blockState.isFaceSturdy(world, blockPos, Direction.UP)) {
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() - 0.05;
                    double f = (double)pos.getZ() + random.nextDouble();
                    world.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT);
        super.createBlockStateDefinition(builder);
    }
}
