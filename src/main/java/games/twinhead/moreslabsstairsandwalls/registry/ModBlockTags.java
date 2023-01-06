package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public class ModBlockTags {
    public static final TagKey<Block> WALLS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "walls"));
    public static final TagKey<Block> SLABS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "slabs"));
    public static final TagKey<Block> STAIRS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "stairs"));
    public static final TagKey<Block> DIRT = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "dirt"));
}
