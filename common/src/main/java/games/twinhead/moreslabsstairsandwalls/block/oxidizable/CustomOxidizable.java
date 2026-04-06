package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableBiMap;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.util.ParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Optional;
import java.util.function.Supplier;

public interface CustomOxidizable extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

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


            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))

            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))


            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))

            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))



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

            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.SLAB))

            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_COPPER_GRATE.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.SLAB))

            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER.getBlock(ModBlocks.BlockType.WALL))


            .build());

    Supplier<ImmutableBiMap<Object, Object>> OXIDATION_LEVEL_DECREASES = Suppliers.memoize(() -> OXIDATION_LEVEL_INCREASES.get().inverse());

    WeatheringCopper.WeatherState getAge();

    default ItemInteractionResult useItem(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand){
        if((player.getMainHandItem().getItem() instanceof AxeItem)){
            if(getAge() == WeatheringCopper.WeatherState.UNAFFECTED) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            if (!world.isClientSide) {
                Optional.ofNullable((Block) OXIDATION_LEVEL_DECREASES.get().get(state.getBlock())).ifPresent((stateX) -> {
                    world.setBlockAndUpdate(pos, stateX.withPropertiesOf(state));
                    if (!player.isCreative())
                        player.getMainHandItem().hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                });
            } else {
                world.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
                ParticleUtils.spawnParticlesOnBlockFaces(world, pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));
            }
        } else if ((player.getMainHandItem().getItem() instanceof HoneycombItem)) {
            if (!world.isClientSide) {
                Optional.ofNullable((Block) WAX_ON.get().get(state.getBlock())).ifPresent((stateX) -> {
                    world.setBlockAndUpdate(pos, stateX.withPropertiesOf(state));
                    if (!player.isCreative())
                        player.getMainHandItem().setCount(player.getMainHandItem().getCount()-1);
                });
            } else {
                ParticleUtils.spawnParticlesOnBlockFaces(world, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
                world.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return ItemInteractionResult.sidedSuccess(world.isClientSide);
    }

    static Optional<Block> getIncreasedOxidationBlock(Block block) {
        return Optional.ofNullable((Block) OXIDATION_LEVEL_INCREASES.get().get(block));
    }

    default float getChanceModifier() {
        return this.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }
    default Optional<BlockState> getNext(BlockState state) {
        return getIncreasedOxidationBlock(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

}
