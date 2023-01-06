package games.twinhead.moreslabsstairsandwalls.block.coral;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class CoralStairsBlock extends StairsBlock {

    private final RegistryObject<Block> deadCoralBlock;

    public CoralStairsBlock(RegistryObject<Block> deadCoralBlock, Settings settings) {
        super(deadCoralBlock.get().getDefaultState(), settings);
        this.deadCoralBlock = deadCoralBlock;
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isInWater(world, pos) && !state.get(WATERLOGGED)) {
            world.setBlockState(pos, this.deadCoralBlock.get().getDefaultState().with(FACING, state.get(FACING)).with(SHAPE, state.get(SHAPE)).with(HALF, state.get(HALF)), Block.NOTIFY_LISTENERS);
        }
    }




    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!this.isInWater(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 60 + world.getRandom().nextInt(40));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean isInWater(BlockView world, BlockPos pos) {
        Direction[] var3 = Direction.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            FluidState fluidState = world.getFluidState(pos.offset(direction));
            if (fluidState.isIn(FluidTags.WATER)) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!this.isInWater(ctx.getWorld(), ctx.getBlockPos())) {
            ctx.getWorld().createAndScheduleBlockTick(ctx.getBlockPos(), this, 60 + ctx.getWorld().getRandom().nextInt(40));
        }

        return super.getPlacementState(ctx);
    }


}
