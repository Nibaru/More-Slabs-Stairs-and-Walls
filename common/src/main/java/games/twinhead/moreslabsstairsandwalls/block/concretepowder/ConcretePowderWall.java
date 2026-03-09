package games.twinhead.moreslabsstairsandwalls.block.concretepowder;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingWall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class ConcretePowderWall extends FallingWall {

    private final ModBlocks hardenedBlock;

    public ConcretePowderWall(ModBlocks modBlocks, ModBlocks hardenedBlock, Properties settings) {
        super(modBlocks, settings);
        this.hardenedBlock = hardenedBlock;
    }

    public void onLand(Level world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (shouldHarden(world, pos, currentStateInPos) || currentStateInPos.getFluidState().is(FluidTags.WATER)) {
            world.setBlock(pos, this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).withPropertiesOf(fallingBlockEntity.getBlockState()), 3);
        }
        super.onLand(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return hardensOnAnySide(world, pos) || state.getValue(WATERLOGGED) ? this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).withPropertiesOf(state) : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (shouldHarden(world, pos, state)) {
            world.setBlock(pos, this.hardenedBlock.getBlock(ModBlocks.BlockType.WALL).withPropertiesOf(state), 3);
        }
        super.tick(state, world, pos, random);
    }

    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }

    private static boolean hardensOnAnySide(BlockGetter world, BlockPos pos) {
        boolean bl = false;
        BlockPos.MutableBlockPos mutable = pos.mutable();
        Direction[] var4 = Direction.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            BlockState blockState = world.getBlockState(mutable);
            if (direction != Direction.DOWN || hardensIn(blockState)) {
                mutable.setWithOffset(pos, direction);
                blockState = world.getBlockState(mutable);
                if (hardensIn(blockState) && !blockState.isFaceSturdy(world, pos, direction.getOpposite())) {
                    bl = true;
                    break;
                }
            }
        }

        return bl;
    }


    private static boolean shouldHarden(BlockGetter world, BlockPos pos, BlockState state) {
        return hardensIn(state) || hardensOnAnySide(world, pos);
    }
}
