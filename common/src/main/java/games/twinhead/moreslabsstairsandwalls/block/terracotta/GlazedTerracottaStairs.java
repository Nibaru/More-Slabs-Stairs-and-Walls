package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;

public class GlazedTerracottaStairs extends BaseStairs {

    public GlazedTerracottaStairs(ModBlocks block, BlockState baseBlockState, Settings settings) {
        super(block,baseBlockState, settings);
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }
}
