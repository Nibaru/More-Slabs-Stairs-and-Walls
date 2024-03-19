package games.twinhead.moreslabsstairsandwalls.block.forge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

public class MoreBlockItemImpl {

    public static int getParentBurnTime(ModBlocks block, ModBlocks.BlockType type, ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        if (ForgeHooks.getBurnTime(block.parentBlock.asItem().getDefaultStack(), recipeType) > 0){
            int parentBurnTime = ForgeHooks.getBurnTime(block.parentBlock.asItem().getDefaultStack(), recipeType);
            return type == ModBlocks.BlockType.SLAB ? parentBurnTime / 2 : parentBurnTime;
        }
        return -1;
    }
}
