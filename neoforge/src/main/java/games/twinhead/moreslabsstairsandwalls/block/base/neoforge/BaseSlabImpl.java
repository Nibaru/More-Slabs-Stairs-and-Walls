package games.twinhead.moreslabsstairsandwalls.block.base.neoforge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class BaseSlabImpl {

    public static boolean getFlammable(ModBlocks block, BlockView level, BlockPos pos, Direction face) {
        return block.parentBlock.getDefaultState().isFlammable(level, pos, face);
    }
}
