package games.twinhead.moreslabsstairsandwalls.block.redstone;

import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class RedstoneWallBlock extends WallBlock {

    private static final IntProperty POWER;

    public RedstoneWallBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWER, 15));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        super.appendProperties(builder);
    }

    static {
        POWER = Properties.POWER;
    }
}
