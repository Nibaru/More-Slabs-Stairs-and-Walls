package games.twinhead.moreslabsstairsandwalls.block.redstone;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
@SuppressWarnings("deprecation")
public class RedstoneStairs extends BaseStairs {

    public RedstoneStairs(ModBlocks block, BlockState state, Properties settings) {
        super(block,state, settings);
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return 15;
    }
}
