package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class LootTableGenerator extends FabricBlockLootTableProvider {


    public LootTableGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generate() {

    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(block.hasBlock(type))
                    addBlock(block, type, identifierBuilderBiConsumer);
            }
//            if(block.hasSlab)
//                identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_slab"), this.slabDrops(block.getBlock(ModBlocks.BlockType.SLAB)));
//
//            if(block.hasStairs)
//                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_stairs", block.getBlock(ModBlocks.BlockType.STAIRS));
//            if(block.hasWall)
//                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_wall", block.getBlock(ModBlocks.BlockType.WALL));
        }
    }



    private void addBlock(ModBlocks block, ModBlocks.BlockType type, BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        Block drop = block.getBlock(type);
        if (block.equals(ModBlocks.GRASS_BLOCK) || block.equals(ModBlocks.MYCELIUM) || block.equals(ModBlocks.PODZOL) || block.equals(ModBlocks.DIRT_PATH))
            drop = ModBlocks.DIRT.getBlock(type);

        if(block.hasBlock(type)){
            if (type.equals(ModBlocks.BlockType.SLAB)){
                identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_" + type.toString().toLowerCase()), this.drops(block.getBlock(type), drop, ConstantLootNumberProvider.create(1.0F)).apply((LootFunction.Builder)((Object) SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)).conditionally(BlockStatePropertyLootCondition.builder(block.getBlock(type)).properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))))));

            } else {
                identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_" + type.toString().toLowerCase()), this.drops(block.getBlock(type), drop, ConstantLootNumberProvider.create(1.0F)));

            }
        }
    }
}
