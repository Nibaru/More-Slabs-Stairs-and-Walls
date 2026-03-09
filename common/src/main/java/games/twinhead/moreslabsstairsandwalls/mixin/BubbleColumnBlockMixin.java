package games.twinhead.moreslabsstairsandwalls.mixin;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

import static net.minecraft.world.level.block.BubbleColumnBlock.DRAG_DOWN;

@Mixin(BubbleColumnBlock.class)
public class BubbleColumnBlockMixin extends Block implements BucketPickup {
    public BubbleColumnBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "getColumnState", at = @At(value = "HEAD"), cancellable = true)
    private static void getState(BlockState state, CallbackInfoReturnable<BlockState> cir){
        for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
            if (state.is(ModBlocks.SOUL_SAND.getBlock(type))) cir.setReturnValue(Blocks.BUBBLE_COLUMN.defaultBlockState().setValue(DRAG_DOWN, false));
            if (state.is(ModBlocks.MAGMA_BLOCK.getBlock(type))) cir.setReturnValue(Blocks.BUBBLE_COLUMN.defaultBlockState().setValue(DRAG_DOWN, true));
        }
    }

    @Shadow
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor world, BlockPos pos, BlockState state) {
        return null;
    }

    @Shadow
    public Optional<SoundEvent> getPickupSound() {
        return Optional.empty();
    }
}
