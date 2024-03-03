package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;

public class GlazedTerracottaWall extends BaseWall {

    public GlazedTerracottaWall(ModBlocks block, Settings settings) {
        super(block,settings);
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }
}
