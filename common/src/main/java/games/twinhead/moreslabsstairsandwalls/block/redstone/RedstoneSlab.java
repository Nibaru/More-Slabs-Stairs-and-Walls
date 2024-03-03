package games.twinhead.moreslabsstairsandwalls.block.redstone;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
@SuppressWarnings("deprecation")
public class RedstoneSlab extends BaseSlab {

    public RedstoneSlab(ModBlocks block, Settings settings) {
        super(block,settings);
    }

    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction.equals(Direction.UP) && state.get(TYPE) == SlabType.TOP ? 0 : 15;
    }
}
