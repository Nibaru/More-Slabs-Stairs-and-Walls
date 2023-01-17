package games.twinhead.moreslabsstairsandwalls.block.dirt;


import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DirtSlab extends SlabBlock {

    public DirtSlab(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!(player.getMainHandStack().getItem() instanceof ShovelItem)) return ActionResult.PASS;

        if (!world.isClient) {
            world.setBlockState(pos, ModBlocks.DIRT_PATH_SLAB.get().getDefaultState().with(SlabBlock.TYPE, state.get(SlabBlock.TYPE)));
            player.getMainHandStack().damage(1, player, p -> p .sendToolBreakStatus(hand));
        } else {
            world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }

        return ActionResult.SUCCESS;
    }
}
