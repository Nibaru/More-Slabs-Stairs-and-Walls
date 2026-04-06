package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class BaseSlab extends SlabBlock {

    ModBlocks modBlock;

    public BaseSlab(ModBlocks block, Properties settings) {
        super(settings);
        this.modBlock = block;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.SLAB;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }

    /**
     * When {@code true}, a {@link SlabType#DOUBLE} slab may stay or accept {@code waterlogged} (placement, bucket,
     * fluids). Derived from {@link ModBlocks#parentBlock}: vanilla full cubes (stone, planks) are usually false;
     * stairs/fences/scaffolding-style parents can be true. Override when the parent is not a reliable signal (e.g.
     * {@link games.twinhead.moreslabsstairsandwalls.block.leaves.LeavesSlab}).
     */
    protected boolean allowsWaterloggedWhenDoubleSlab() {
        var parent = modBlock.parentBlock;
        BlockState parentState = parent.defaultBlockState();
        return parentState.hasProperty(BlockStateProperties.WATERLOGGED) || parent instanceof SimpleWaterloggedBlock;
    }

    private boolean doubleSlabAllowsFluids(BlockState blockState) {
        return blockState.getValue(TYPE) != SlabType.DOUBLE || allowsWaterloggedWhenDoubleSlab();
    }

    /**
     * Water buckets consult this (not only {@link #canPlaceLiquid}) before emptying into a block.
     */
    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        if (!doubleSlabAllowsFluids(state)) {
            return false;
        }
        return super.canBeReplaced(state, fluid);
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        if (blockState.getValue(TYPE) == SlabType.DOUBLE && allowsWaterloggedWhenDoubleSlab()) {
            if (blockState.getValue(WATERLOGGED)) {
                return false;
            }
            if (fluidState.getType() == Fluids.WATER && fluidState.isSource()) {
                if (!levelAccessor.isClientSide()) {
                    levelAccessor.setBlock(blockPos, blockState.setValue(WATERLOGGED, true), 3);
                    levelAccessor.scheduleTick(blockPos, fluidState.getType(), fluidState.getType().getTickDelay(levelAccessor));
                }
                return true;
            }
            return false;
        }
        if (!doubleSlabAllowsFluids(blockState)) {
            return false;
        }
        return super.placeLiquid(levelAccessor, blockPos, blockState, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        if (blockState.getValue(TYPE) == SlabType.DOUBLE && allowsWaterloggedWhenDoubleSlab()) {
            if (blockState.getValue(WATERLOGGED)) {
                return false;
            }
            if (fluid == Fluids.WATER) {
                return true;
            }
            return super.canPlaceLiquid(player, blockGetter, blockPos, blockState, fluid);
        }
        if (!doubleSlabAllowsFluids(blockState)) {
            return false;
        }
        return super.canPlaceLiquid(player, blockGetter, blockPos, blockState, fluid);
    }

    /**
     * Merging half-slabs underwater uses placement, not {@link #placeLiquid}; clear {@code waterlogged} on double unless allowed.
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        if (state != null
                && !allowsWaterloggedWhenDoubleSlab()
                && state.getValue(TYPE) == SlabType.DOUBLE
                && state.getValue(WATERLOGGED)) {
            return state.setValue(WATERLOGGED, false);
        }
        return state;
    }


    //Used to get the flammability of the block on NeoForge
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return defaultBlockState().ignitedByLava() ? ModRegistry.getBurnChance(this.modBlock) : 0;
    }

    //Used to get the fire spread speed of the block on NeoForge
    public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction face) {
        return defaultBlockState().ignitedByLava() ?  ModRegistry.getSpreadChance(this.modBlock) : 0;
    }
}
