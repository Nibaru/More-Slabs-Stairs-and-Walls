package games.twinhead.moreslabsstairsandwalls.block.spreadable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtStairs;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

@SuppressWarnings("deprecation")
public class SpreadableStairs extends DirtStairs implements SimpleWaterloggedBlock, BonemealableBlock {

    public static final BooleanProperty SNOWY;

    public SpreadableStairs(ModBlocks block,BlockState defaultState, Properties settings) {
        super(block,defaultState, settings);
    }


    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!SpreadableSlab.canSurviveBlock(state, world, pos)) {
            ModBlocks deadBase = ModBlocks.DIRT;
            if (state.is(ModBlocks.WARPED_NYLIUM.getBlock(ModBlocks.BlockType.STAIRS))
                    || state.is(ModBlocks.CRIMSON_NYLIUM.getBlock(ModBlocks.BlockType.STAIRS))) {
                deadBase = ModBlocks.NETHERRACK;
            }
            world.setBlock(pos, deadBase.getBlock(ModBlocks.BlockType.STAIRS).withPropertiesOf(world.getBlockState(pos)), Block.UPDATE_CLIENTS);
        } else {
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    SpreadableSlab.trySpread(world, getModBlock().parentBlock, blockPos);
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        if(state.getValue(StairBlock.HALF) == Half.BOTTOM) return false;
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.is(ModTags.GRASS_BLOCKS)) {
            SpreadableSlab.growBoneMeal(world,random,pos);
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE, WATERLOGGED, SNOWY);
    }

    static {
        SNOWY = BlockStateProperties.SNOWY;
    }
}
