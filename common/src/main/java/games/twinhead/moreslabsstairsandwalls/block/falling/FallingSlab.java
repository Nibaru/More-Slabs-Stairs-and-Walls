package games.twinhead.moreslabsstairsandwalls.block.falling;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import games.twinhead.moreslabsstairsandwalls.block.entity.LandingSlabBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class FallingSlab extends BaseSlab implements LandingSlabBlock, SimpleWaterloggedBlock {

    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_AABB;
    protected static final VoxelShape TOP_AABB;


    public FallingSlab(ModBlocks modBlocks, Properties settings) {
        super(modBlocks,settings);
    }


    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleTick(pos, this, this.getFallDelay());
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleTick(pos, this, this.getFallDelay());
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockPos = ctx.getClickedPos();
        BlockState blockState = ctx.getLevel().getBlockState(blockPos);
        if (blockState.is(this)) {
            return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
            return this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (canFallThrough(world.getBlockState(pos.below())) && pos.getY() >= world.getMinBuildHeight()) {
            FallingSlabBlockEntity.fall(world, pos, state.setValue(TYPE, state.getValue(TYPE).equals(SlabType.TOP) ? SlabType.BOTTOM : state.getValue(TYPE)));
        } else if (state.getValue(TYPE) == SlabType.TOP) {
            world.setBlockAndUpdate(pos, state.setValue(TYPE, SlabType.BOTTOM));
        }

        if(world.getBlockState(pos.below()).is(this) && this == world.getBlockState(pos.below()).getBlock()){
            if(world.getBlockState(pos.below()).getValue(TYPE) == SlabType.BOTTOM){
                if (world.getBlockState(pos.below()).getValue(TYPE) == SlabType.DOUBLE){
                    world.setBlockAndUpdate(pos, world.getBlockState(pos.below()).setValue(TYPE, SlabType.BOTTOM));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                }
                world.setBlockAndUpdate(pos.below(), world.getBlockState(pos.below()).setValue(TYPE, SlabType.DOUBLE));
            }
        }
    }

    @Override
    public void onLanding(Level world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingSlabBlockEntity fallingBlockEntity) {
        if(currentStateInPos.getBlock() instanceof FallingSlab slab && slab == this)
            if(currentStateInPos.getValue(TYPE) == SlabType.BOTTOM && fallingBlockState.getValue(TYPE) == SlabType.DOUBLE){
                world.setBlockAndUpdate(pos, fallingBlockState.setValue(TYPE, SlabType.DOUBLE));
                world.setBlockAndUpdate(pos.above(), fallingBlockState.setValue(TYPE, SlabType.BOTTOM));
            } else if (fallingBlockState.getValue(TYPE).equals(SlabType.TOP)) {
                world.setBlockAndUpdate(pos, fallingBlockState.setValue(TYPE, SlabType.BOTTOM));
            } else{
                world.setBlockAndUpdate(pos, fallingBlockState);
            }
    }

    @Override
    public void onDestroyedOnLanding(Level world, BlockPos pos, FallingSlabBlockEntity fallingBlockEntity) {
        fallingBlockEntity.dropItem = true;

        // check if the block in postition is a slab and if it is the same type as the one that is falling
        if(world.getBlockState(pos).getBlock() instanceof FallingSlab slab && slab == fallingBlockEntity.getBlockState().getBlock()){
            if(world.getBlockState(pos).getValue(TYPE) == SlabType.BOTTOM){
                if(fallingBlockEntity.getBlockState().getValue(TYPE) == SlabType.DOUBLE){
                    world.setBlockAndUpdate(pos.above(), fallingBlockEntity.getBlockState().setValue(TYPE, SlabType.BOTTOM));
                }
                world.setBlockAndUpdate(pos, fallingBlockEntity.getBlockState().setValue(TYPE, SlabType.DOUBLE));
            }
        } else {
            if(fallingBlockEntity.getBlockState().getValue(TYPE) == SlabType.DOUBLE) fallingBlockEntity.spawnAtLocation(this);
            fallingBlockEntity.spawnAtLocation(this);
        }

    }

    protected int getFallDelay() {
        return 2;
    }


    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (random.nextInt(16) == 0) {
            BlockPos blockPos = pos.below();
            if (canFallThrough(world.getBlockState(blockPos))) {
                double d = (double)pos.getX() + random.nextDouble();
                double e = (double)pos.getY() - 0.05;
                double f = (double)pos.getZ() + random.nextDouble();
                world.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    static {
        TYPE = BlockStateProperties.SLAB_TYPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_AABB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    }

}
