package games.twinhead.moreslabsstairsandwalls.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.List;

public class ModBlock {

    private final Block parentBlock;
    private boolean hasSlab = true;
    private boolean hasStairs = true;
    private boolean hasWall = true;
    private ModelType modelType;

    private ModBlock associatedBlock = null;

    private final List<TagKey<Block>> blockTags;
    private final List<TagKey<Item>> itemTags;

    public ModBlock(Builder builder) {
        this.parentBlock = builder.parentBlock;
        this.hasSlab = builder.hasSlab;
        this.hasStairs = builder.hasStairs;
        this.hasWall = builder.hasWall;
        this.blockTags = builder.blockTags;
        this.itemTags = builder.itemTags;
        this.associatedBlock = builder.associatedBlock;
        this.modelType = builder.build().modelType;

    }

    public static Builder builder(Block parentBlock) {
        return new Builder(parentBlock);
    }


    public static class Builder {
        private final Block parentBlock;
        private boolean hasSlab = true;
        private boolean hasStairs = true;
        private boolean hasWall = true;
        private ModelType modelType = ModelType.CUBE_ALL;
        private ModBlock associatedBlock = null;

        private final List<TagKey<Block>> blockTags;
        private final List<TagKey<Item>> itemTags;

        List<TagKey<Block>> mineableTags = List.of(BlockTags.PICKAXE_MINEABLE, BlockTags.AXE_MINEABLE, BlockTags.SHOVEL_MINEABLE, BlockTags.HOE_MINEABLE);


        public Builder(Block parentBlock) {
            this.parentBlock = parentBlock;
            this.blockTags = new ArrayList<>();
            this.itemTags = new ArrayList<>();

            for (TagKey<Block> tag : mineableTags) {
                if (parentBlock.getDefaultState().isIn(tag))
                    this.blockTags.add(tag);
            }
        }

        public Builder modelType(ModelType modelType) {
            this.modelType = modelType;
            return this;
        }

        @SafeVarargs
        public final Builder addBlockTags(TagKey<Block>... blockTags) {
            this.blockTags.addAll(List.of(blockTags));
            return this;
        }

        public Builder addBlockTag(TagKey<Block> blockTag) {
            this.blockTags.add(blockTag);
            return this;
        }

        @SafeVarargs
        public final Builder addItemTags(TagKey<Item>... itemTags) {
            this.itemTags.addAll(List.of(itemTags));
            return this;
        }

        public Builder addItemTag(TagKey<Item> itemTag) {
            this.itemTags.add(itemTag);
            return this;
        }

        public Builder hasSlab(boolean hasSlab) {
            this.hasSlab = hasSlab;
            return this;
        }

        public Builder hasStairs(boolean hasStairs) {
            this.hasStairs = hasStairs;
            return this;
        }

        public Builder hasWall(boolean hasWall) {
            this.hasWall = hasWall;
            return this;
        }

        public Builder associatedBlock(ModBlock associatedBlock) {
            this.associatedBlock = associatedBlock;
            return this;
        }

        public ModBlock build() {
            return new ModBlock(this);
        }
    }

    public enum ModelType {
        CUBE_ALL,
        CUBE_COLUMN,
        CUBE_BOTTOM_TOP,
        LOG,
        GRASS,
        LEAVES,
        GLASS,
        PATH,
        LOG_ALL,
        CUSTOM,
        GLAZED_TERRACOTTA,
        ROTATABLE,
        SLIME, HONEY, TRANSLUCENT, ROOTS, CUSTOM_SIDE_BOTTOM_TOP
    }

}
