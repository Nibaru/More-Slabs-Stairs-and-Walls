package games.twinhead.moreslabsstairsandwalls.block.redstone;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
@SuppressWarnings("deprecation")
public class RedstoneSlab extends BaseSlab {

    public RedstoneSlab(ModBlocks block, Properties settings) {
        super(block,settings);
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return direction.equals(Direction.UP) && state.getValue(TYPE) == SlabType.TOP ? 0 : 15;
    }
}
