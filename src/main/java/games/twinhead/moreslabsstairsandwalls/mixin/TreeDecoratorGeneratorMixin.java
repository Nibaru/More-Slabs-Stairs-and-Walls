package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Mixin(TreeDecorator.Generator.class)
public abstract class TreeDecoratorGeneratorMixin {

    @Shadow @Final private TestableWorld world;

    @Shadow public abstract TestableWorld getWorld();

    @Shadow @Final private BiConsumer<BlockPos, BlockState> replacer;
    private static final Set<BlockState> podzolReplaceableStairs = new HashSet<>();
    private static final Set<BlockState> podzolReplaceableSlabs = new HashSet<>();

    @Inject(method = "replace", at = @At("HEAD"), cancellable = true)
    private void replace(BlockPos pos, BlockState state, CallbackInfo ci){
        if (state.isOf(Blocks.PODZOL)){
            for (BlockState modBlockState: podzolReplaceableStairs) {
                if(world.testBlockState(pos, Predicate.isEqual(modBlockState))){
                    this.replacer.accept(pos, ModBlocks.PODZOL_STAIRS.get().getDefaultState().with(StairsBlock.FACING, modBlockState.get(StairsBlock.FACING)).with(StairsBlock.SHAPE, modBlockState.get(StairsBlock.SHAPE)).with(StairsBlock.HALF, modBlockState.get(StairsBlock.HALF)));
                    ci.cancel();
                }
            }
            for (BlockState modBlockState: podzolReplaceableSlabs) {
                if(world.testBlockState(pos, Predicate.isEqual(modBlockState))){
                    this.replacer.accept(pos, ModBlocks.PODZOL_SLAB.get().getDefaultState().with(SlabBlock.TYPE, modBlockState.get(SlabBlock.TYPE)));
                    ci.cancel();
                }
            }
        }
    }


    static {
        podzolReplaceableStairs.addAll(ModBlocks.GRASS_BLOCK_STAIRS.get().getStateManager().getStates());
        podzolReplaceableStairs.addAll(ModBlocks.PODZOL_STAIRS.get().getStateManager().getStates());
        podzolReplaceableStairs.addAll(ModBlocks.DIRT_STAIRS.get().getStateManager().getStates());
        podzolReplaceableStairs.addAll(ModBlocks.COARSE_DIRT_STAIRS.get().getStateManager().getStates());
        podzolReplaceableStairs.addAll(ModBlocks.MYCELIUM_STAIRS.get().getStateManager().getStates());
        podzolReplaceableStairs.addAll(ModBlocks.ROOTED_DIRT_STAIRS.get().getStateManager().getStates());

        podzolReplaceableSlabs.addAll(ModBlocks.GRASS_BLOCK_SLAB.get().getStateManager().getStates());
        podzolReplaceableSlabs.addAll(ModBlocks.PODZOL_SLAB.get().getStateManager().getStates());
        podzolReplaceableSlabs.addAll(ModBlocks.DIRT_SLAB.get().getStateManager().getStates());
        podzolReplaceableSlabs.addAll(ModBlocks.COARSE_DIRT_SLAB.get().getStateManager().getStates());
        podzolReplaceableSlabs.addAll(ModBlocks.MYCELIUM_SLAB.get().getStateManager().getStates());
        podzolReplaceableSlabs.addAll(ModBlocks.ROOTED_DIRT_SLAB.get().getStateManager().getStates());

    }
}
