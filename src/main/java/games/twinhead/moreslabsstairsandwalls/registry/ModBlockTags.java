package games.twinhead.moreslabsstairsandwalls.registry;

import dev.architectury.registry.registries.Registries;
import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("deprecation")
public class ModBlockTags {
    public static final TagKey<Block> WALLS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "walls"));
    public static final TagKey<Block> SLABS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "slabs"));
    public static final TagKey<Block> STAIRS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "stairs"));
    public static final TagKey<Block> DIRT = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "dirt"));
}
