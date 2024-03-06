package games.twinhead.moreslabsstairsandwalls.block;


import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import org.jetbrains.annotations.Nullable;

public class MoreBlockItem extends BlockItem {

    private final ModBlocks block;
    private final ModBlocks.BlockType type;

    public MoreBlockItem(ModBlocks blocks, ModBlocks.BlockType type,  Item.Settings settings) {
        super(blocks.getBlock(type), settings);
        this.block = blocks;
        this.type = type;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return getParentBurnTime(block, type, itemStack, recipeType);
    }

    @ExpectPlatform
    public static int getParentBurnTime(ModBlocks block, ModBlocks.BlockType type, ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        throw new AssertionError();
    }
}
