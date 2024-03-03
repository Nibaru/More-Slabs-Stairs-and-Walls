package games.twinhead.moreslabsstairsandwalls.block.concretepowder;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingWall;
import net.minecraft.block.BlockState;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ConcretePowderWall extends FallingWall {

    private final ModBlocks hardenedBlock;

    public ConcretePowderWall(ModBlocks modBlocks, ModBlocks hardenedBlock, Settings settings) {
        super(modBlocks, settings);
        this.hardenedBlock = hardenedBlock;
    }

    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (shouldHarden(world, pos, currentStateInPos) || currentStateInPos.getFluidState().isIn(FluidTags.WATER)) {
            world.setBlockState(pos, this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(fallingBlockEntity.getBlockState()), 3);
        }
        super.onLanding(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return hardensOnAnySide(world, pos) || state.get(WATERLOGGED) ? this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (shouldHarden(world, pos, state)) {
            world.setBlockState(pos, this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state), 3);
        }
        super.scheduledTick(state, world, pos, random);
    }

    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }

    private static boolean hardensOnAnySide(BlockView world, BlockPos pos) {
        boolean bl = false;
        BlockPos.Mutable mutable = pos.mutableCopy();
        Direction[] var4 = Direction.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            BlockState blockState = world.getBlockState(mutable);
            if (direction != Direction.DOWN || hardensIn(blockState)) {
                mutable.set(pos, direction);
                blockState = world.getBlockState(mutable);
                if (hardensIn(blockState) && !blockState.isSideSolidFullSquare(world, pos, direction.getOpposite())) {
                    bl = true;
                    break;
                }
            }
        }

        return bl;
    }


    private static boolean shouldHarden(BlockView world, BlockPos pos, BlockState state) {
        return hardensIn(state) || hardensOnAnySide(world, pos);
    }
}
