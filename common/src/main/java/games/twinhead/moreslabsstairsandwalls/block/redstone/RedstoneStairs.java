package games.twinhead.moreslabsstairsandwalls.block.redstone;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
@SuppressWarnings("deprecation")
public class RedstoneStairs extends BaseStairs {

    public RedstoneStairs(ModBlocks block, BlockState state, Settings settings) {
        super(block,state, settings);
    }

    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 15;
    }
}
