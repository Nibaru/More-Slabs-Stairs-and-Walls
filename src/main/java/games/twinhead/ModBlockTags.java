package games.twinhead;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {
    public static final TagKey<Block> WALLS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreBuildingBlocks.mod_id, "walls"));
    public static final TagKey<Block> SLABS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreBuildingBlocks.mod_id, "slabs"));
    public static final TagKey<Block> STAIRS = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreBuildingBlocks.mod_id, "stairs"));
    public static final TagKey<Block> DIRT = TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreBuildingBlocks.mod_id, "dirt"));
}
