package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;

public class GlazedTerracottaStairsBlock extends BaseStairs {

    public GlazedTerracottaStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    @SuppressWarnings("deprecation")
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }
}
