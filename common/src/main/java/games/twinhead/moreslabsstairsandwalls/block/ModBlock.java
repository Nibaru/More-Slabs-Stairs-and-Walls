package games.twinhead.moreslabsstairsandwalls.block;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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

        List<TagKey<Block>> mineableTags = List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.MINEABLE_WITH_HOE);


        public Builder(Block parentBlock) {
            this.parentBlock = parentBlock;
            this.blockTags = new ArrayList<>();
            this.itemTags = new ArrayList<>();

            for (TagKey<Block> tag : mineableTags) {
                if (parentBlock.defaultBlockState().is(tag))
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
