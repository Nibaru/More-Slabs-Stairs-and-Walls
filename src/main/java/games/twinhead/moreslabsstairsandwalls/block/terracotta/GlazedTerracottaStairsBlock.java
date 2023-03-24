package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.piston.PistonBehavior;

public class GlazedTerracottaStairsBlock extends StairsBlock {

    public GlazedTerracottaStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    @SuppressWarnings("deprecation")
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }
}
