package games.twinhead.moreslabsstairsandwalls.block.strippable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
@SuppressWarnings("deprecation")
public class StrippableSlab extends BaseSlab {

    private final ModBlocks strippedBlock;

    public StrippableSlab(ModBlocks block,ModBlocks strippedBlock, Properties settings) {
        super(block,settings);
        this.strippedBlock = strippedBlock;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!(player.getMainHandItem().getItem() instanceof AxeItem)) return InteractionResult.PASS;

        if (!world.isClientSide) {
            world.setBlockAndUpdate(pos, strippedBlock.getBlock(ModBlocks.BlockType.SLAB).withPropertiesOf(state));
            player.getMainHandItem().hurtAndBreak(1, player, p -> p .broadcastBreakEvent(hand));
        } else {
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return InteractionResult.SUCCESS;
    }
}
