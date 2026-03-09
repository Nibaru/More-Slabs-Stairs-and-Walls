package games.twinhead.moreslabsstairsandwalls.block.neoforge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.Nullable;

public class MoreBlockItemImpl {

    public static int getParentBurnTime(ModBlocks block, ModBlocks.BlockType type, ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        if (CommonHooks.getBurnTime(block.parentBlock.asItem().getDefaultInstance(), recipeType) > 0){
            int parentBurnTime = CommonHooks.getBurnTime(block.parentBlock.asItem().getDefaultInstance(), recipeType);
            return type == ModBlocks.BlockType.SLAB ? parentBurnTime / 2 : parentBurnTime;
        }
        return -1;
    }
}
