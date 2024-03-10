package games.twinhead.moreslabsstairsandwalls.block.spreadable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.dirt.DirtSlab;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class SpreadableSlab extends DirtSlab implements Waterloggable, Fertilizable {

    public static final BooleanProperty SNOWY;
    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;

    public SpreadableSlab(ModBlocks block, Settings settings) {
        super(block,settings);
    }

    public static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);

        if (state.getBlock() instanceof DirtSlab && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) {
            return !state.get(SlabBlock.WATERLOGGED);
        }

        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        }

        if (blockState.getFluidState().getLevel() == 8) {
            return false;
        }

        if (state.isIn(BlockTags.WALLS) && blockState.isIn(BlockTags.WALLS) && blockState.isOpaque()) {
            return false;
        }

        if (blockState.getBlock() instanceof SlabBlock && blockState.get(SlabBlock.TYPE) == SlabType.TOP){
            return true;
        }

        if (blockState.getBlock() instanceof StairsBlock && blockState.get(StairsBlock.HALF) == BlockHalf.TOP){
            return true;
        }

        int i = ChunkLightProvider.getRealisticOpacity(world, ModBlocks.GRASS_BLOCK.parentBlock.getDefaultState(), pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        if(state.get(SlabBlock.TYPE) == SlabType.BOTTOM) return false;
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isIn(ModTags.GRASS_BLOCKS)) {
            growBoneMeal(world,random,pos);
        }
    }


    public static void growBoneMeal(ServerWorld world, Random random, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = Blocks.SHORT_GRASS.getDefaultState();
        Optional<RegistryEntry.Reference<PlacedFeature>> optional = world.getRegistryManager().get(RegistryKeys.PLACED_FEATURE).getEntry(VegetationPlacedFeatures.GRASS_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;

            for(int j = 0; j < i / 16; ++j) {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.down()).isIn(ModTags.GRASS_BLOCKS) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
                    continue label49;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
                ((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
            }

            if (blockState2.isAir()) {
                RegistryEntry<PlacedFeature> registryEntry;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }
                    registryEntry = ((RandomPatchFeatureConfig) list.get(0).config()).feature();
                } else {
                    if (optional.isEmpty()) {
                        continue;
                    }
                    registryEntry = optional.get();
                }
                registryEntry.value().generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
            }
        }

    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            ModBlocks deadBase = ModBlocks.DIRT;
            if (state.isOf(ModBlocks.WARPED_NYLIUM.getBlock(ModBlocks.BlockType.SLAB))
                    || state.isOf(ModBlocks.CRIMSON_NYLIUM.getBlock(ModBlocks.BlockType.SLAB))) {
                deadBase = ModBlocks.NETHERRACK;
            }
            world.setBlockState(pos, deadBase.getBlock(ModBlocks.BlockType.SLAB).getStateWithProperties(world.getBlockState(pos)), Block.NOTIFY_LISTENERS);
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    trySpread(world, getModBlock().parentBlock, blockPos);
                }
            }
        }
    }


    public static void trySpread(ServerWorld world, Block parentBlock, BlockPos spreadPos) {
        BlockState newState = null;
        BlockState oldState = world.getBlockState(spreadPos);
        ModBlocks[] fromDirt = {ModBlocks.GRASS_BLOCK, ModBlocks.MYCELIUM};




        if (oldState.isOf(Blocks.DIRT)) {
            // target is a full dirt block
            for (ModBlocks modBlock : fromDirt) {
                if (parentBlock.equals(modBlock.parentBlock)) {
                    newState = modBlock.parentBlock.getDefaultState().with(SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW));
                }
            }
        } else {
            // luckily all properties except "SNOWY" can be copied using getStateWithProperties(...)
            for (ModBlocks.BlockType blockType :ModBlocks.BlockType.values()) {
                if (oldState.isOf(ModBlocks.DIRT.getBlock(blockType))) {
                    // target is dirt slab/stairs/wall
                    for (ModBlocks modBlock : fromDirt) {
                        if (parentBlock.equals(modBlock.parentBlock)) {
                            newState = modBlock.getBlock(blockType)
                                    .getStateWithProperties(world.getBlockState(spreadPos));
                            if (newState.contains(Properties.SNOWY))
                                newState = newState.with(Properties.SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW));
                        }
                    }
                }
            }
        }
        if (newState != null && canSurvive(newState, world, spreadPos) && !world.getFluidState(spreadPos.up()).isIn(FluidTags.WATER))
            world.setBlockState(spreadPos, newState);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, SNOWY);
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

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        state = state.with(SNOWY, world.getBlockState(pos.up()).isIn(BlockTags.SNOW));


        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    static {
        TYPE = Properties.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
        SNOWY = Properties.SNOWY;
    }
}
