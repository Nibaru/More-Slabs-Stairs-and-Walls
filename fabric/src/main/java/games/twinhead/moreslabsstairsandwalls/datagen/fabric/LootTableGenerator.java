package games.twinhead.moreslabsstairsandwalls.datagen.fabric;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class LootTableGenerator extends SimpleFabricLootTableProvider {


    public LootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        for (ModBlocks block: ModBlocks.values()) {

            if(block.hasSlab)
                identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_slab"), BlockLootTableGenerator.slabDrops(block.getBlock(ModBlocks.BlockType.SLAB)));

            if(block.hasStairs)
                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_stairs", block.getBlock(ModBlocks.BlockType.STAIRS));
            if(block.hasWall)
                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_wall", block.getBlock(ModBlocks.BlockType.WALL));
        }
    }

    public void addBlock(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer, String name, Block block){
        identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + name), BlockLootTableGenerator.drops(block, block.asItem(), ConstantLootNumberProvider.create(1.0F)));
    }
}
