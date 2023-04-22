package games.twinhead.moreslabsstairsandwalls;


import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableBiMap;
import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Supplier;

public class Registry {

    public static final Supplier<ImmutableBiMap<Object, Object>> WAX_ON = Suppliers.memoize(() -> ImmutableBiMap.builder()
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


    public static final List<ModBlocks> GLASS = List.of(ModBlocks.GLASS, ModBlocks.BLACK_STAINED_GLASS, ModBlocks.BLUE_STAINED_GLASS, ModBlocks.BROWN_STAINED_GLASS, ModBlocks.CYAN_STAINED_GLASS, ModBlocks.GRAY_STAINED_GLASS, ModBlocks.GREEN_STAINED_GLASS, ModBlocks.LIGHT_BLUE_STAINED_GLASS, ModBlocks.LIGHT_GRAY_STAINED_GLASS, ModBlocks.LIME_STAINED_GLASS, ModBlocks.MAGENTA_STAINED_GLASS, ModBlocks.ORANGE_STAINED_GLASS, ModBlocks.PINK_STAINED_GLASS, ModBlocks.PURPLE_STAINED_GLASS, ModBlocks.RED_STAINED_GLASS, ModBlocks.WHITE_STAINED_GLASS, ModBlocks.YELLOW_STAINED_GLASS, ModBlocks.ICE);

    @ExpectPlatform
    public static Block getBlock(Identifier id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBlocks(){
        throw new AssertionError();
    }

}
