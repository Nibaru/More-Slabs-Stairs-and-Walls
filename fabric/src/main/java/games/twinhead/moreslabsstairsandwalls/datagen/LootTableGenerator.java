package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
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

            if(block.hasSlab)
                identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_slab"), this.slabDrops(block.getBlock(ModBlocks.BlockType.SLAB)));

            if(block.hasStairs)
                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_stairs", block.getBlock(ModBlocks.BlockType.STAIRS));
            if(block.hasWall)
                addBlock(identifierBuilderBiConsumer, block.toString().toLowerCase() + "_wall", block.getBlock(ModBlocks.BlockType.WALL));
        }
    }

    public void addBlock(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer, String name, Block block){
        identifierBuilderBiConsumer.accept(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + name), this.drops(block, block.asItem(), ConstantLootNumberProvider.create(1.0F)));
    }
}
