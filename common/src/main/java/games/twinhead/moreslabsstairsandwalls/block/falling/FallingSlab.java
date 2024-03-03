package games.twinhead.moreslabsstairsandwalls.block.falling;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import games.twinhead.moreslabsstairsandwalls.block.entity.LandingSlabBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class FallingSlab extends BaseSlab implements LandingSlabBlock, Waterloggable {

    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;


    public FallingSlab(ModBlocks modBlocks, Settings settings) {
        super(modBlocks,settings);
    }


    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (BlockState)((BlockState)blockState.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            return (BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingSlabBlockEntity.spawnFromBlock(world, pos, state.with(TYPE, state.get(TYPE).equals(SlabType.TOP) ? SlabType.BOTTOM : state.get(TYPE)));
            //this.configureFallingBlockEntity(fallingBlockEntity);
        } else if (state.get(TYPE) == SlabType.TOP) {
            world.setBlockState(pos, state.with(TYPE, SlabType.BOTTOM));
        }

        if(world.getBlockState(pos.down()).isOf(this) && this == ((FallingSlab) world.getBlockState(pos.down()).getBlock())){
            if(world.getBlockState(pos.down()).get(TYPE) == SlabType.BOTTOM){
                if (world.getBlockState(pos.down()).get(TYPE) == SlabType.DOUBLE){
                    world.setBlockState(pos, world.getBlockState(pos.down()).with(TYPE, SlabType.BOTTOM));
                } else {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
                world.setBlockState(pos.down(), world.getBlockState(pos.down()).with(TYPE, SlabType.DOUBLE));
            }
        }
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingSlabBlockEntity fallingBlockEntity) {
        if(currentStateInPos.getBlock() instanceof FallingSlab slab && slab == this)
            if(currentStateInPos.get(TYPE) == SlabType.BOTTOM && fallingBlockState.get(TYPE) == SlabType.DOUBLE){
                world.setBlockState(pos, fallingBlockState.with(TYPE, SlabType.DOUBLE));
                world.setBlockState(pos.up(), fallingBlockState.with(TYPE, SlabType.BOTTOM));
            } else if (fallingBlockState.get(TYPE).equals(SlabType.TOP)) {
                world.setBlockState(pos, fallingBlockState.with(TYPE, SlabType.BOTTOM));
            } else{
                world.setBlockState(pos, fallingBlockState);
            }
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingSlabBlockEntity fallingBlockEntity) {
        fallingBlockEntity.dropItem = true;

        // check if the block in postition is a slab and if it is the same type as the one that is falling
        if(world.getBlockState(pos).getBlock() instanceof FallingSlab slab && slab == ((FallingSlab) fallingBlockEntity.getBlockState().getBlock())){
            if(world.getBlockState(pos).get(TYPE) == SlabType.BOTTOM){
                if(fallingBlockEntity.getBlockState().get(TYPE) == SlabType.DOUBLE){
                    world.setBlockState(pos.up(), fallingBlockEntity.getBlockState().with(TYPE, SlabType.BOTTOM));
                }
                world.setBlockState(pos, fallingBlockEntity.getBlockState().with(TYPE, SlabType.DOUBLE));
            }
        } else {
            if(fallingBlockEntity.getBlockState().get(TYPE) == SlabType.DOUBLE) fallingBlockEntity.dropItem(this);
            fallingBlockEntity.dropItem(this);
        }

    }

    protected int getFallDelay() {
        return 2;
    }

    @SuppressWarnings("deprecation")
    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state.isReplaceable();
    }



    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(16) == 0) {
            BlockPos blockPos = pos.down();
            if (canFallThrough(world.getBlockState(blockPos))) {
                double d = (double)pos.getX() + random.nextDouble();
                double e = (double)pos.getY() - 0.05;
                double f = (double)pos.getZ() + random.nextDouble();
                world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state), d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }

    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return -16777216;
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }


    static {
        TYPE = Properties.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    }

}
