package games.twinhead.moreslabsstairsandwalls.block.spreadable;

import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtSlab;
import games.twinhead.moreslabsstairsandwalls.registry.ModBlockTags;
import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
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
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SpreadableSlabBlock extends DirtSlab implements Waterloggable, Fertilizable {

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

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false).with(SNOWY, ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isIn(BlockTags.SNOW));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(SNOWY, ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isIn(BlockTags.SNOW));
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? blockState2 : blockState2.with(TYPE, SlabType.TOP);
        }
    }



    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if(state.get(SlabBlock.TYPE) == SlabType.BOTTOM) return false;
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if(isClient) return false;
        if(state.get(SlabBlock.TYPE) == SlabType.BOTTOM) return false;
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (canGrow(world, random, pos, state)) {
            growBonemeal(world,random,pos,state);
        }

    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            if(state.isOf(ModBlocks.GRASS_BLOCK_SLAB.get())) {
                world.setBlockState(pos, ModBlocks.DIRT_SLAB.get().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.MYCELIUM_SLAB.get())) {
                world.setBlockState(pos, ModBlocks.DIRT_SLAB.get().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.WARPED_NYLIUM_SLAB.get())) {
                world.setBlockState(pos, ModBlocks.NETHERRACK_SLAB.get().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
            }else if(state.isOf(ModBlocks.CRIMSON_NYLIUM_SLAB.get())) {
                world.setBlockState(pos, ModBlocks.NETHERRACK_SLAB.get().getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
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
                            if(state.isOf(ModBlocks.GRASS_BLOCK_SLAB.get())){
                                world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                            } else if(state.isOf(ModBlocks.MYCELIUM_SLAB.get())){
                                world.setBlockState(blockPos, Blocks.MYCELIUM.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT_SLAB.get()))
                            if(state.isOf(ModBlocks.GRASS_BLOCK_SLAB.get())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK_SLAB.get().getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(blockPos).get(SlabBlock.TYPE)));
                            } else if(state.isOf(ModBlocks.MYCELIUM_SLAB.get())){
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM_SLAB.get().getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(blockPos).get(SlabBlock.TYPE)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT_STAIRS.get()))
                            if(state.isOf(ModBlocks.GRASS_BLOCK_SLAB.get())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK_STAIRS.get().getStateWithProperties(world.getBlockState(blockPos)));
                            } else if(state.isOf(ModBlocks.MYCELIUM_SLAB.get())) {
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM_STAIRS.get().getStateWithProperties(world.getBlockState(blockPos)));
                            }

                        if(world.getBlockState(blockPos).isOf(ModBlocks.DIRT_WALL.get()) && !world.getBlockState(blockPos.up()).isIn(ModBlockTags.WALLS))
                            if(state.isOf(ModBlocks.GRASS_BLOCK_SLAB.get())){
                                world.setBlockState(blockPos, ModBlocks.GRASS_BLOCK_WALL.get().getStateWithProperties(world.getBlockState(blockPos)));
                            } else if(state.isOf(ModBlocks.MYCELIUM_SLAB.get())) {
                                world.setBlockState(blockPos, ModBlocks.MYCELIUM_WALL.get().getStateWithProperties(world.getBlockState(blockPos)));
                            }
                    }
                }
            }

        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        state = state.with(SNOWY, world.getBlockState(pos.up()).isIn(BlockTags.SNOW));


        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void growBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = Blocks.GRASS.getDefaultState();

        label46:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;

            for(int j = 0; j < i / 16; ++j) {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
                    continue label46;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
                ((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
            }

            if (blockState2.isAir()) {
                RegistryEntry registryEntry;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = ((Biome)world.getBiome(blockPos2).value()).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    registryEntry = ((RandomPatchFeatureConfig)((ConfiguredFeature)list.get(0)).config()).feature();
                } else {
                    registryEntry = VegetationPlacedFeatures.GRASS_BONEMEAL;
                }

                ((PlacedFeature)registryEntry.value()).generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, SNOWY);
    }

    static {
        TYPE = Properties.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
        SNOWY = Properties.SNOWY;
    }
}
