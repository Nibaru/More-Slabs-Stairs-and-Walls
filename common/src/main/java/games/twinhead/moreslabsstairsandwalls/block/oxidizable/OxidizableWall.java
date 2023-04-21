package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.WallBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public class OxidizableWall extends WallBlock implements CustomOxidizable {

    private final Oxidizable.OxidationLevel oxidationLevel;
    private final ModBlocks nextBlock;

    public OxidizableWall(Oxidizable.OxidationLevel oxidationLevel, ModBlocks nextBlock, Settings arg) {
        super(arg);
        this.oxidationLevel = oxidationLevel;
        this.nextBlock = nextBlock;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if((player.getMainHandStack().getItem() instanceof AxeItem)){
            if(oxidationLevel == Oxidizable.OxidationLevel.UNAFFECTED) return ActionResult.PASS;
            if (!world.isClient) {
                BlockState newState = switch (oxidationLevel){
                    case UNAFFECTED -> null;
                    case EXPOSED -> ModBlocks.CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                    case WEATHERED -> ModBlocks.EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                    case OXIDIZED -> ModBlocks.WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                };
                world.setBlockState(pos, newState);
                player.getMainHandStack().damage(1, player, p -> p .sendToolBreakStatus(hand));
            } else {
                world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.SCRAPE, UniformIntProvider.create(3, 5));
            }
        } else if ((player.getMainHandStack().getItem() instanceof HoneycombItem)) {
            if (!world.isClient) {
                BlockState newState = switch (oxidationLevel){
                    case UNAFFECTED -> ModBlocks.WAXED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                    case EXPOSED -> ModBlocks.WAXED_EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                    case WEATHERED -> ModBlocks.WAXED_WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                    case OXIDIZED -> ModBlocks.WAXED_OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state);
                };
                world.setBlockState(pos, newState);
                player.getMainHandStack().setCount(player.getMainHandStack().getCount()-1);
            } else {
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.WAX_ON, UniformIntProvider.create(3, 5));
                world.playSound(player, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }else {
            return ActionResult.PASS;
        }

        return ActionResult.SUCCESS;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(this.oxidationLevel != Oxidizable.OxidationLevel.OXIDIZED){
            tickDegradation(state, world, pos, random);
        }
    }

    public boolean hasRandomTicks(BlockState state) {return true;}

    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

    public Optional<BlockState> getDegradationResult(BlockState state) {
        return Optional.ofNullable((this.nextBlock == null ? state : nextBlock.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state)));
    }

}
