package games.twinhead.moreslabsstairsandwalls.block.dirt;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
@SuppressWarnings("deprecation")
public class DirtSlab extends BaseSlab {

    public DirtSlab(ModBlocks modBlocks, Properties settings) {
        super(modBlocks, settings);
    }


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!(player.getMainHandItem().getItem() instanceof ShovelItem)) return InteractionResult.PASS;

        BlockState blockState = world.getBlockState(pos.above());
        if(blockState.isRedstoneConductor(world, pos) && !(blockState.getBlock() instanceof FenceGateBlock)) return InteractionResult.PASS;

        if (!world.isClientSide) {
            world.setBlockAndUpdate(pos, ModBlocks.DIRT_PATH.getBlock(ModBlocks.BlockType.SLAB).defaultBlockState().setValue(SlabBlock.TYPE, state.getValue(SlabBlock.TYPE)));
            player.getMainHandItem().hurtAndBreak(1, player, p -> p .broadcastBreakEvent(hand));
        } else {
            world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return InteractionResult.SUCCESS;
    }
}
