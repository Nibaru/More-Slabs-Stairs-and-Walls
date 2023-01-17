package games.twinhead.moreslabsstairsandwalls.block.dirt;

import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PathBlockSlab extends SlabBlock {


    public static final VoxelShape BOTTOM_SHAPE;
    public static final VoxelShape TOP_SHAPE;
    public static final VoxelShape FULL_SHAPE;


    public PathBlockSlab(Settings settings) {
        super(settings);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(!this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())){
            return Block.pushEntitiesUpBeforeBlockChange(this.getDefaultState(), ModBlocks.DIRT_SLAB.get().getPlacementState(ctx), ctx.getWorld(), ctx.getBlockPos());
        }

        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (BlockState)((BlockState)blockState.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? blockState2 : (BlockState)blockState2.with(TYPE, SlabType.TOP);
        }
    }


    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = (SlabType)state.get(TYPE);
        return switch (slabType) {
            case DOUBLE -> FULL_SHAPE;
            case TOP -> TOP_SHAPE;
            default -> BOTTOM_SHAPE;
        };
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, ModBlocks.DIRT_SLAB.get().getDefaultState().with(TYPE, world.getBlockState(pos).get(TYPE)), world, pos));
    }



    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock;
    }

    static {
        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
        TOP_SHAPE = Block.createCuboidShape(0.0, 7.0, 0.0, 16.0, 15.0, 16.0);
        FULL_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    }


}
