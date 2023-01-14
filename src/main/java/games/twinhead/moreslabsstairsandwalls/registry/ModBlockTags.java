package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> WALLS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "walls"));
    public static final TagKey<Block> SLABS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "slabs"));
    public static final TagKey<Block> STAIRS = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "stairs"));
    public static final TagKey<Block> DIRT = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "dirt"));

    public static final TagKey<Block> canBecomePathSlab = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "becomes_path_slab"));
    public static final TagKey<Block> canBecomePathStairs = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "becomes_path_stairs"));
    public static final TagKey<Block> canBecomePathWall = TagKey.of(Registries.BLOCK.getKey(), new Identifier(MoreSlabsStairsAndWalls.mod_id, "becomes_path_wall"));

}
