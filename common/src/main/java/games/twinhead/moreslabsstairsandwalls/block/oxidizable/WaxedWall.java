package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
@SuppressWarnings("deprecation")
public class WaxedWall extends BaseWall {

    private final ModBlocks block;

    public WaxedWall(ModBlocks modBlock,ModBlocks block, Settings settings) {
        super(modBlock,settings);
        this.block = block;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!(player.getMainHandStack().getItem() instanceof AxeItem)) return ActionResult.PASS;

        if (!world.isClient) {
            world.setBlockState(pos, block.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state));
            player.getMainHandStack().damage(1, player, p -> p .sendToolBreakStatus(hand));
        } else {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            ParticleUtil.spawnParticle(world, pos, ParticleTypes.WAX_OFF, UniformIntProvider.create(3, 5));
        }

        return ActionResult.SUCCESS;
    }
}
