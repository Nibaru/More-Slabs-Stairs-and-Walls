package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (ModBlocks block : ModBlocks.values()) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (!block.hasBlock(type)) {
                    continue;
                }
                Block target = block.getBlock(type);
                boolean dirtDrop = block.equals(ModBlocks.GRASS_BLOCK)
                        || block.equals(ModBlocks.MYCELIUM)
                        || block.equals(ModBlocks.PODZOL)
                        || block.equals(ModBlocks.DIRT_PATH);
                Block drop = dirtDrop ? ModBlocks.DIRT.getBlock(type) : target;

                if (type == ModBlocks.BlockType.SLAB) {
                    add(target, createSlabItemTable(drop));
                } else if (dirtDrop) {
                    dropOther(target, drop.asItem());
                } else {
                    dropSelf(target);
                }
            }
        }
    }
}
