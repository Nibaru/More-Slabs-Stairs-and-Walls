package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableBiMap;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.Supplier;

public interface CustomOxidizable extends Degradable<Oxidizable.OxidationLevel> {

    Supplier<ImmutableBiMap<Object, Object>> OXIDATION_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.builder()
            .put(ModBlocks.CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.WALL), ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.SLAB))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))

            .build());

    Supplier<ImmutableBiMap<Object, Object>> WAX_ON = Suppliers.memoize(() -> ImmutableBiMap.builder()
            .put(ModBlocks.CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .build());

    Supplier<ImmutableBiMap<Object, Object>> OXIDATION_LEVEL_DECREASES = Suppliers.memoize(() -> OXIDATION_LEVEL_INCREASES.get().inverse());

    Oxidizable.OxidationLevel getDegradationLevel();

    default ActionResult useItem(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand){
        if((player.getMainHandStack().getItem() instanceof AxeItem)){
            if(getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED) return ActionResult.PASS;
            if (!world.isClient) {
                Optional.ofNullable((Block) OXIDATION_LEVEL_DECREASES.get().get(state.getBlock())).ifPresent((stateX) -> {
                    world.setBlockState(pos, stateX.getStateWithProperties(state));
                    if (!player.isCreative())
                        player.getMainHandStack().damage(1, player, p -> p .sendToolBreakStatus(hand));
                });
            } else {
                world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.SCRAPE, UniformIntProvider.create(3, 5));
            }
        } else if ((player.getMainHandStack().getItem() instanceof HoneycombItem)) {
            if (!world.isClient) {
                Optional.ofNullable((Block) WAX_ON.get().get(state.getBlock())).ifPresent((stateX) -> {
                    world.setBlockState(pos, stateX.getStateWithProperties(state));
                    if (!player.isCreative())
                        player.getMainHandStack().setCount(player.getMainHandStack().getCount()-1);
                });
            } else {
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.WAX_ON, UniformIntProvider.create(3, 5));
                world.playSound(player, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }else {
            return ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
    }

    static Optional<Block> getIncreasedOxidationBlock(Block block) {
        return Optional.ofNullable((Block) OXIDATION_LEVEL_INCREASES.get().get(block));
    }

    default float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }
    default Optional<BlockState> getDegradationResult(BlockState state) {
        return getIncreasedOxidationBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
    }

}
