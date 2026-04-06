package games.twinhead.moreslabsstairsandwalls.block.coral;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class CoralSlab extends BaseSlab {

    private final ModBlocks deadCoralBlock;

    public CoralSlab(ModBlocks modBlocks, ModBlocks deadCoralBlock, Properties settings) {
        super(modBlocks, settings);
        this.deadCoralBlock = deadCoralBlock;
    }


    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!isInWater(world, pos) && !state.getValue(WATERLOGGED)) {
            world.setBlock(pos, this.deadCoralBlock.getBlock(getBlockType()).withPropertiesOf(state), Block.UPDATE_CLIENTS);
        }
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (!isInWater(world, pos)) {
            world.scheduleTick(pos, this, 60 + world.getRandom().nextInt(40));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }



    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if (!isInWater(ctx.getLevel(), ctx.getClickedPos())) {
            ctx.getLevel().scheduleTick(ctx.getClickedPos(), this, 60 + ctx.getLevel().getRandom().nextInt(40));
        }

        return super.getStateForPlacement(ctx);
    }


    public static boolean isInWater(BlockGetter world, BlockPos pos) {
        Direction[] var3 = Direction.values();

        for (Direction direction : var3) {
            FluidState fluidState = world.getFluidState(pos.relative(direction));
            if (fluidState.is(FluidTags.WATER)) {
                return true;
            }
        }

        return false;
    }


}
