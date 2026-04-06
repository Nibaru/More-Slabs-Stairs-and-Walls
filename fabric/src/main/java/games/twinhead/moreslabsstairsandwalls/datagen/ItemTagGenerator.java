package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    /** Log-like blocks that get per-variant item tags (mirrors former {@code RecipeGenerator.logBlocks}). */
    private static final List<ModBlocks> LOG_BLOCKS_FOR_TAGS = List.of(
            ModBlocks.ACACIA_LOG,
            ModBlocks.BIRCH_LOG,
            ModBlocks.CRIMSON_STEM,
            ModBlocks.DARK_OAK_LOG,
            ModBlocks.JUNGLE_LOG,
            ModBlocks.OAK_LOG,
            ModBlocks.SPRUCE_LOG,
            ModBlocks.WARPED_STEM,
            ModBlocks.MANGROVE_LOG,
            ModBlocks.CHERRY_LOG,
            ModBlocks.BAMBOO_BLOCK,
            ModBlocks.STRIPPED_ACACIA_LOG,
            ModBlocks.STRIPPED_BIRCH_LOG,
            ModBlocks.STRIPPED_CRIMSON_STEM,
            ModBlocks.STRIPPED_DARK_OAK_LOG,
            ModBlocks.STRIPPED_JUNGLE_LOG,
            ModBlocks.STRIPPED_OAK_LOG,
            ModBlocks.STRIPPED_SPRUCE_LOG,
            ModBlocks.STRIPPED_WARPED_STEM,
            ModBlocks.STRIPPED_MANGROVE_LOG,
            ModBlocks.STRIPPED_CHERRY_LOG,
            ModBlocks.STRIPPED_BAMBOO_BLOCK,
            ModBlocks.ACACIA_WOOD,
            ModBlocks.BIRCH_WOOD,
            ModBlocks.CRIMSON_HYPHAE,
            ModBlocks.DARK_OAK_WOOD,
            ModBlocks.JUNGLE_WOOD,
            ModBlocks.OAK_WOOD,
            ModBlocks.SPRUCE_WOOD,
            ModBlocks.WARPED_HYPHAE,
            ModBlocks.MANGROVE_WOOD,
            ModBlocks.CHERRY_WOOD,
            ModBlocks.STRIPPED_ACACIA_WOOD,
            ModBlocks.STRIPPED_BIRCH_WOOD,
            ModBlocks.STRIPPED_CRIMSON_HYPHAE,
            ModBlocks.STRIPPED_DARK_OAK_WOOD,
            ModBlocks.STRIPPED_JUNGLE_WOOD,
            ModBlocks.STRIPPED_OAK_WOOD,
            ModBlocks.STRIPPED_SPRUCE_WOOD,
            ModBlocks.STRIPPED_WARPED_HYPHAE,
            ModBlocks.STRIPPED_MANGROVE_WOOD,
            ModBlocks.STRIPPED_CHERRY_WOOD
    );

    public ItemTagGenerator(FabricDataOutput dataGenerator, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataGenerator, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        for (ModBlocks block: ModBlocks.values()){
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(!block.hasBlock(type)) continue;
                if (LOG_BLOCKS_FOR_TAGS.contains(block)) {
                    getOrCreateTagBuilder(ModTags.getLogTagKey(block, type)).add(block.getBlock(type).asItem());
                }
                if (ModTags.isVanillaWoolParent(block.parentBlock)) {
                    var woolItemTag = switch (type) {
                        case SLAB -> ModTags.WOOL_SLABS_ITEMS;
                        case STAIRS -> ModTags.WOOL_STAIRS_ITEMS;
                        case WALL -> ModTags.WOOL_WALLS_ITEMS;
                    };
                    getOrCreateTagBuilder(woolItemTag).add(block.getBlock(type).asItem());
                }
            }
        }
    }
}
