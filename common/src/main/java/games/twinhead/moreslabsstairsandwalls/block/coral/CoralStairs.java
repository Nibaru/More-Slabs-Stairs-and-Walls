package games.twinhead.moreslabsstairsandwalls.block.coral;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class CoralStairs extends BaseStairs {

    private final ModBlocks deadCoralBlock;

    public CoralStairs(ModBlocks modBlocks, BlockState state, ModBlocks deadCoralBlock, Settings settings) {
        super(modBlocks,state, settings);
        this.deadCoralBlock = deadCoralBlock;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!CoralSlab.isInWater(world, pos) && !state.get(WATERLOGGED)) {
            world.setBlockState(pos, this.deadCoralBlock.getBlock(ModBlocks.BlockType.STAIRS).getStateWithProperties(state), Block.NOTIFY_LISTENERS);
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!CoralSlab.isInWater(world, pos)) {
            world.scheduleBlockTick(pos, this, 60 + world.getRandom().nextInt(40));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!CoralSlab.isInWater(ctx.getWorld(), ctx.getBlockPos())) {
            ctx.getWorld().scheduleBlockTick(ctx.getBlockPos(), this, 60 + ctx.getWorld().getRandom().nextInt(40));
        }

        return super.getPlacementState(ctx);
    }


}
