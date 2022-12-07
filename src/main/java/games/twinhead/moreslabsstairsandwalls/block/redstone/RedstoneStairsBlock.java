package games.twinhead.moreslabsstairsandwalls.block.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class RedstoneStairsBlock extends StairsBlock {

    private static final IntProperty POWER;

    public RedstoneStairsBlock(Settings settings) {
        super(Blocks.STONE.getDefaultState(), settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWER, 15));
    }

    @SuppressWarnings("deprecation")
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 15;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        super.appendProperties(builder);
    }

    static {
        POWER = Properties.POWER;
    }
}
