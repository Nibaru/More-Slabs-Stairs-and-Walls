package games.twinhead.moreslabsstairsandwalls.block.neoforge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class MoreBlockItemImpl {
    public static int getParentBurnTime(ModBlocks block, ModBlocks.BlockType type, ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        ItemStack parentFuel = new ItemStack(block.parentBlock.asItem());
        int burn = parentFuel.getBurnTime(recipeType);
        if (burn > 0) {
            int parentBurnTime = burn;
            return type == ModBlocks.BlockType.SLAB ? parentBurnTime / 2 : parentBurnTime;
        }
        return 0;
    }
}
