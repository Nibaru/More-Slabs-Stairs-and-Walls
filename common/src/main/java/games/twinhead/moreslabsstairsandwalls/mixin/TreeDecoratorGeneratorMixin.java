package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

@Mixin(TreeDecorator.Context.class)
public abstract class TreeDecoratorGeneratorMixin {

    @Shadow @Final private LevelSimulatedReader level;

    @Shadow public abstract LevelSimulatedReader level();

    @Shadow @Final private BiConsumer<BlockPos, BlockState> decorationSetter;

    @Unique
    private static final Set<BlockState> podzolReplaceableStairs = new HashSet<>();
    @Unique
    private static final Set<BlockState> podzolReplaceableSlabs = new HashSet<>();

    @Inject(method = "setBlock", at = @At("HEAD"), cancellable = true)
    private void replace(BlockPos pos, BlockState state, CallbackInfo ci){
        if (state.is(Blocks.PODZOL)){
            for (BlockState modBlockState: podzolReplaceableStairs) {
                if(level.isStateAtPosition(pos, Predicate.isEqual(modBlockState))){
                    this.decorationSetter.accept(pos, ModBlocks.PODZOL.getBlock(ModBlocks.BlockType.STAIRS).defaultBlockState().setValue(StairBlock.FACING, modBlockState.getValue(StairBlock.FACING)).setValue(StairBlock.SHAPE, modBlockState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF, modBlockState.getValue(StairBlock.HALF)));
                    ci.cancel();
                }
            }
            for (BlockState modBlockState: podzolReplaceableSlabs) {
                if(level.isStateAtPosition(pos, Predicate.isEqual(modBlockState))){
                    this.decorationSetter.accept(pos, ModBlocks.PODZOL.getBlock(ModBlocks.BlockType.SLAB).defaultBlockState().setValue(SlabBlock.TYPE, modBlockState.getValue(SlabBlock.TYPE)));
                    ci.cancel();
                }
            }
        }
    }


    static {
        podzolReplaceableStairs.addAll(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());
        podzolReplaceableStairs.addAll(ModBlocks.PODZOL.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());
        podzolReplaceableStairs.addAll(ModBlocks.DIRT.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());
        podzolReplaceableStairs.addAll(ModBlocks.COARSE_DIRT.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());
        podzolReplaceableStairs.addAll(ModBlocks.MYCELIUM.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());
        podzolReplaceableStairs.addAll(ModBlocks.ROOTED_DIRT.getBlock(ModBlocks.BlockType.STAIRS).getStateDefinition().getPossibleStates());

        podzolReplaceableSlabs.addAll(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());
        podzolReplaceableSlabs.addAll(ModBlocks.PODZOL.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());
        podzolReplaceableSlabs.addAll(ModBlocks.DIRT.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());
        podzolReplaceableSlabs.addAll(ModBlocks.COARSE_DIRT.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());
        podzolReplaceableSlabs.addAll(ModBlocks.MYCELIUM.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());
        podzolReplaceableSlabs.addAll(ModBlocks.ROOTED_DIRT.getBlock(ModBlocks.BlockType.SLAB).getStateDefinition().getPossibleStates());

    }
}
