package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import java.util.function.BiConsumer;

public class LootTableGenerator extends FabricBlockLootTableProvider {


    public LootTableGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generate() {

    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> identifierBuilderBiConsumer) {
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



    private void addBlock(ModBlocks block, ModBlocks.BlockType type, BiConsumer<ResourceLocation, LootTable.Builder> identifierBuilderBiConsumer) {
        Block drop = block.getBlock(type);
        if (block.equals(ModBlocks.GRASS_BLOCK) || block.equals(ModBlocks.MYCELIUM) || block.equals(ModBlocks.PODZOL) || block.equals(ModBlocks.DIRT_PATH))
            drop = ModBlocks.DIRT.getBlock(type);

        if(block.hasBlock(type)){
            if (type.equals(ModBlocks.BlockType.SLAB)){
                identifierBuilderBiConsumer.accept(new ResourceLocation(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_" + type.toString().toLowerCase()), this.createSingleItemTableWithSilkTouch(block.getBlock(type), drop, ConstantValue.exactly(1.0F)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.getBlock(type)).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))));

            } else {
                identifierBuilderBiConsumer.accept(new ResourceLocation(MoreSlabsStairsAndWalls.MOD_ID, "blocks/" + block.toString().toLowerCase() + "_" + type.toString().toLowerCase()), this.createSingleItemTableWithSilkTouch(block.getBlock(type), drop, ConstantValue.exactly(1.0F)));

            }
        }
    }
}
