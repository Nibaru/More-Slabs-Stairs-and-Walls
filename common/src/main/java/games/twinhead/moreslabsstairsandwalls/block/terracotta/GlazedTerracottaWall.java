package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class GlazedTerracottaWall extends BaseWall {

    public GlazedTerracottaWall(ModBlocks block, Properties settings) {
        super(block,settings);
    }

    public PushReaction getPistonBehavior(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
}
