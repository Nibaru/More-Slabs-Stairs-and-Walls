package games.twinhead.moreslabsstairsandwalls.block.strippable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
@SuppressWarnings("deprecation")
public class StrippableWall extends BaseWall {

    private final ModBlocks strippedBlock;

    public StrippableWall(ModBlocks block,ModBlocks strippedBlock, Properties settings) {
        super(block,settings);
        this.strippedBlock = strippedBlock;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!(itemStack.getItem() instanceof AxeItem)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (!world.isClientSide) {
            world.setBlockAndUpdate(pos, strippedBlock.getBlock(ModBlocks.BlockType.WALL).withPropertiesOf(state));
            itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        } else {
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return ItemInteractionResult.SUCCESS;
    }
}
