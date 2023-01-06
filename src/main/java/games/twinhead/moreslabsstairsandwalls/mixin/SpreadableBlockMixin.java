package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.SpreadableSlabBlock;
import games.twinhead.moreslabsstairsandwalls.registry.ModBlockTags;
import net.minecraft.block.*;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin extends SnowyBlock {
    public SpreadableBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci, BlockState defaultState, int j, BlockPos spreadPos) {

        if (canSpread(defaultState, world, spreadPos)) {
            if(world.getBlockState(spreadPos).isOf(Blocks.DIRT))
                if(state.isOf(Blocks.GRASS_BLOCK)){
                    world.setBlockState(spreadPos, Blocks.GRASS_BLOCK.getDefaultState().with(SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW)));
                } else if(state.isOf(Blocks.MYCELIUM)){
                    world.setBlockState(spreadPos, Blocks.MYCELIUM.getDefaultState().with(SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW)));
                }

            if(world.getBlockState(spreadPos).isOf(ModBlocks.DIRT_SLAB.get()))
                if(state.isOf(Blocks.GRASS_BLOCK)){
                    world.setBlockState(spreadPos, ModBlocks.GRASS_BLOCK_SLAB.get().getDefaultState().with(SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(spreadPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(spreadPos).get(SlabBlock.TYPE)));
                } else if(state.isOf(Blocks.MYCELIUM)){
                    world.setBlockState(spreadPos, ModBlocks.MYCELIUM_SLAB.get().getDefaultState().with(SNOWY, world.getBlockState(spreadPos.up()).isOf(Blocks.SNOW)).with(SlabBlock.WATERLOGGED, world.getBlockState(spreadPos).get(SlabBlock.WATERLOGGED)).with(SlabBlock.TYPE, world.getBlockState(spreadPos).get(SlabBlock.TYPE)));
                }

            if(world.getBlockState(spreadPos).isOf(ModBlocks.DIRT_STAIRS.get()))
                if(state.isOf(Blocks.GRASS_BLOCK)){
                    world.setBlockState(spreadPos, ModBlocks.GRASS_BLOCK_STAIRS.get().getStateWithProperties(world.getBlockState(spreadPos)));
                } else if(state.isOf(Blocks.MYCELIUM)) {
                    world.setBlockState(spreadPos, ModBlocks.MYCELIUM_STAIRS.get().getStateWithProperties(world.getBlockState(spreadPos)));
                }

            if(world.getBlockState(spreadPos).isOf(ModBlocks.DIRT_WALL.get()) && !world.getBlockState(spreadPos.up()).isIn(ModBlockTags.WALLS))
                if(state.isOf(Blocks.GRASS_BLOCK)){
                    world.setBlockState(spreadPos, ModBlocks.GRASS_BLOCK_WALL.get().getStateWithProperties(world.getBlockState(spreadPos)));
                } else if(state.isOf(Blocks.MYCELIUM)) {
                    world.setBlockState(spreadPos, ModBlocks.MYCELIUM_WALL.get().getStateWithProperties(world.getBlockState(spreadPos)));
                }
        }
    }


    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return SpreadableSlabBlock.canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }
}
