package games.twinhead.moreslabsstairsandwalls.block;

import games.twinhead.moreslabsstairsandwalls.registry.ModBlockTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NetherConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class SpreadableSlabBlock extends GrassBlock implements Waterloggable {

    public static final BooleanProperty SNOWY;
    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;

    public SpreadableSlabBlock(Settings settings) {
        super(settings);
    }

    public static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);

        if(blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1){
            return true;
        }else if (!blockState.isOpaqueFullCube(world, pos) &&
                (blockState.getBlock() instanceof SpreadableSlabBlock && blockState.get(SlabBlock.TYPE) == SlabType.TOP)
                || (blockState.getBlock() instanceof SpreadableStairsBlock && blockState.get(StairsBlock.HALF) == BlockHalf.TOP))
        {
            return true;

        } else if (state.getBlock() instanceof SpreadableSlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM){
            return true;
        } else if (state.getBlock() instanceof SpreadableSlabBlock && state.get(SlabBlock.TYPE) == SlabType.TOP && !blockState.isSideSolid(world, pos, Direction.DOWN, SideShapeType.FULL)){
            return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if(state.get(SlabBlock.TYPE) == SlabType.BOTTOM) return false;

        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        if (blockState.isOf(ModBlocks.CRIMSON_NYLIUM.getSlabBlock())) {
            NetherConfiguredFeatures.CRIMSON_FOREST_VEGETATION_BONEMEAL.value().generate(world, chunkGenerator, random, blockPos);
        } else if (blockState.isOf(ModBlocks.WARPED_NYLIUM.getSlabBlock())) {
            NetherConfiguredFeatures.WARPED_FOREST_VEGETATION_BONEMEAL.value().generate(world, chunkGenerator, random, blockPos);
            NetherConfiguredFeatures.NETHER_SPROUTS_BONEMEAL.value().generate(world, chunkGenerator, random, blockPos);
            if (random.nextInt(8) == 0) {
                NetherConfiguredFeatures.TWISTING_VINES_BONEMEAL.value().generate(world, chunkGenerator, random, blockPos);
            }
        } else if (blockState.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())) {
            super.grow(world,random,pos,state);
        }

    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            if(state.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())) {
                world.setBlockState(pos, ModBlocks.DIRT.getSlabBlock().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.MYCELIUM.getSlabBlock())) {
                world.setBlockState(pos, ModBlocks.DIRT.getSlabBlock().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.WARPED_NYLIUM.getSlabBlock())) {
                world.setBlockState(pos, ModBlocks.NETHERRACK.getSlabBlock().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.CRIMSON_NYLIUM.getSlabBlock())) {
                world.setBlockState(pos, ModBlocks.NETHERRACK.getSlabBlock().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }  else {
                world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState blockState = this.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (canSpread(blockState, world, blockPos)) {
                        if(world.getBlockState(blockPos).isOf(Blocks.DIRT))
                            if(state.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())){
                                world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                            } else if(state.isOf(ModBlocks.MYCELIUM.getSlabBlock())){
                                world.setBlockState(blockPos, Blocks.MYCELIUM.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT.getSlabBlock()))
                            if(state.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK.getSlabBlock().getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(blockPos).get(SlabBlock.TYPE)));
                            } else if(state.isOf(ModBlocks.MYCELIUM.getSlabBlock())){
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM.getSlabBlock().getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(blockPos).get(SlabBlock.TYPE)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT.getStairsBlock()))
                            if(state.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK.getStairsBlock().getStateWithProperties(world.getBlockState(blockPos)));
                            } else if(state.isOf(ModBlocks.MYCELIUM.getSlabBlock())) {
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM.getStairsBlock().getStateWithProperties(world.getBlockState(blockPos)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT.getWallBlock()) && !world.getBlockState(blockPos.up()).isIn(ModBlockTags.WALLS))
                            if(state.isOf(ModBlocks.GRASS_BLOCK.getSlabBlock())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK.getWallBlock().getStateWithProperties(world.getBlockState(blockPos)));
                            } else if(state.isOf(ModBlocks.MYCELIUM.getSlabBlock())) {
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM.getWallBlock().getStateWithProperties(world.getBlockState(blockPos)));
                            }
                    }
                }
            }

        }
    }

    @SuppressWarnings("deprecation")
    public boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != SlabType.DOUBLE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, SNOWY);
    }


    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        switch (slabType) {
            case DOUBLE:
                return VoxelShapes.fullCube();
            case TOP:
                return TOP_SHAPE;
            default:
                return BOTTOM_SHAPE;
        }
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false).with(SNOWY, ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isIn(BlockTags.SNOW));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? blockState2 : blockState2.with(TYPE, SlabType.TOP);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        SlabType slabType = state.get(TYPE);
        if (slabType != SlabType.DOUBLE && itemStack.isOf(this.asItem())) {
            if (context.canReplaceExisting()) {
                boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5;
                Direction direction = context.getSide();
                if (slabType == SlabType.BOTTOM) {
                    return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
                } else {
                    return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != SlabType.DOUBLE && Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != SlabType.DOUBLE && Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @SuppressWarnings("deprecation")
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return switch (type) {
            case WATER -> world.getFluidState(pos).isIn(FluidTags.WATER);
            default -> false;
        };
    }

    static {
        TYPE = Properties.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
        SNOWY = Properties.SNOWY;
    }
}
