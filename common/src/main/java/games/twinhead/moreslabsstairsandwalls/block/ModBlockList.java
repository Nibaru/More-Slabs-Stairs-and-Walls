package games.twinhead.moreslabsstairsandwalls.block;

import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;

import java.util.ArrayList;
import java.util.List;

public class ModBlockList {


    public static final List<ModBlock> BLOCKS = new ArrayList<>();

    public static final ModBlock GRASS_BLOCK = add(ModBlock.builder(Blocks.GRASS_BLOCK).modelType(ModBlock.ModelType.GRASS).addBlockTag(BlockTags.DIRT).build());
    public static final ModBlock PODZOL = add(ModBlock.builder(Blocks.PODZOL).modelType(ModBlock.ModelType.GRASS).addBlockTags(BlockTags.DIRT, BlockTags.MUSHROOM_GROW_BLOCK).build());
    public static final ModBlock MYCELIUM = add(ModBlock.builder(Blocks.MYCELIUM).modelType(ModBlock.ModelType.GRASS).addBlockTags(BlockTags.DIRT, BlockTags.MUSHROOM_GROW_BLOCK).build());
    public static final ModBlock COARSE_DIRT = add(ModBlock.builder(Blocks.COARSE_DIRT).addBlockTag(BlockTags.DIRT).build());
    public static final ModBlock DIRT = add(ModBlock.builder(Blocks.DIRT).modelType(ModBlock.ModelType.GRASS).addBlockTag(BlockTags.DIRT).build());
    public static final ModBlock DIRT_PATH = add(ModBlock.builder(Blocks.DIRT_PATH).addBlockTag(BlockTags.DIRT).build());
    public static final ModBlock ROOTED_DIRT = add(ModBlock.builder(Blocks.ROOTED_DIRT).addBlockTag(BlockTags.DIRT).build());

    public static final ModBlock STRIPPED_OAK_LOG = add(ModBlock.builder(Blocks.STRIPPED_OAK_LOG).modelType(ModBlock.ModelType.LOG).build());
    public static final ModBlock OAK_LOG = add(ModBlock.builder(Blocks.OAK_LOG).associatedBlock(STRIPPED_OAK_LOG).modelType(ModBlock.ModelType.LOG).build());
    public static final ModBlock STRIPPED_OAK_WOOD = add(ModBlock.builder(Blocks.STRIPPED_OAK_WOOD).modelType(ModBlock.ModelType.LOG_ALL).build());
    public static final ModBlock OAK_WOOD = add(ModBlock.builder(Blocks.OAK_WOOD).associatedBlock(STRIPPED_OAK_WOOD).modelType(ModBlock.ModelType.LOG_ALL).build());
    public static final ModBlock OAK_LEAVES = add(ModBlock.builder(Blocks.OAK_LEAVES).modelType(ModBlock.ModelType.LEAVES).addBlockTag(BlockTags.LEAVES).build());
    public static final ModBlock OAK_PLANKS = add(ModBlock.builder(Blocks.OAK_PLANKS).hasSlab(false).hasStairs(false).build());

    public static final ModBlock BEDROCK = add(ModBlock.builder(Blocks.BEDROCK).addBlockTags(BlockTags.DRAGON_IMMUNE, BlockTags.WITHER_IMMUNE).build());

    public static ModBlock add(ModBlock block){
        BLOCKS.add(block);
        return block;
    }
}
