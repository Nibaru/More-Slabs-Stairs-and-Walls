package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;


public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataGenerator, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataGenerator, registryLookup);
    }

    @Override
    public void generate() {
        for (ModBlocks block : ModBlocks.values()) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (block.hasBlock(type))
                    addBlock(block, type);
            }
        }
    }

    private void addBlock(ModBlocks block, ModBlocks.BlockType type) {
        Block drop = block.getBlock(type);
        if (block.equals(ModBlocks.GRASS_BLOCK) || block.equals(ModBlocks.MYCELIUM) || block.equals(ModBlocks.PODZOL) || block.equals(ModBlocks.DIRT_PATH))
            drop = ModBlocks.DIRT.getBlock(type);

        if (block.hasBlock(type)) {
            if (type.equals(ModBlocks.BlockType.SLAB)) {
                add(block.getBlock(type), createSingleItemTableWithSilkTouch(block.getBlock(type), drop, ConstantValue.exactly(1.0F))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.getBlock(type))
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))));
            } else {
                add(block.getBlock(type), createSingleItemTableWithSilkTouch(block.getBlock(type), drop, ConstantValue.exactly(1.0F)));
            }
        }
    }
}
