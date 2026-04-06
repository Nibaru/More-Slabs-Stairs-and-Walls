package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class GlazedTerracottaStairs extends BaseStairs {

    public GlazedTerracottaStairs(ModBlocks block, BlockState baseBlockState, Properties settings) {
        super(block,baseBlockState, settings);
    }

    public PushReaction getPistonBehavior(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
}
