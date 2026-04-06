package games.twinhead.moreslabsstairsandwalls.block.dirt;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("deprecation")
public class DirtStairs extends BaseStairs {

    public DirtStairs(ModBlocks modBlocks, BlockState defaultState, Properties settings) {
        super(modBlocks, defaultState, settings);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!(stack.getItem() instanceof ShovelItem)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockState blockState = world.getBlockState(pos.above());
        if(blockState.isRedstoneConductor(world, pos) && !(blockState.getBlock() instanceof FenceGateBlock)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (!world.isClientSide) {
            world.setBlockAndUpdate(pos, ModBlocks.DIRT_PATH.getBlock(ModBlocks.BlockType.STAIRS).withPropertiesOf(state));
            stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        } else {
            world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return ItemInteractionResult.sidedSuccess(world.isClientSide);
    }
}
