package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.WallSide;

@SuppressWarnings("deprecation")
public class TranslucentWall extends BaseWall {

    public TranslucentWall(ModBlocks modBlock, Properties settings) {
        super(modBlock, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean skipRendering(BlockState state, BlockState state2, Direction dir) {
        if (state2.getBlock() == this.getModBlock().parentBlock) return true;

        if (dir.equals(Direction.DOWN)){

            if (state2.getBlock() instanceof TranslucentSlab slab){
                if (slab.getModBlock() == getModBlock())
                    if (state2.getValue(BlockStateProperties.SLAB_TYPE).equals(SlabType.TOP)) return true;
            }

            if (state2.getBlock() instanceof TranslucentWall wall){
                {
                    if (wall.getModBlock() == getModBlock())
                        return isMatchingBelow(state, state2);
                }
            }
        } else if (dir.equals(Direction.UP)){
            if (state2.getBlock() instanceof TranslucentSlab slab){
                if (slab.getModBlock() == getModBlock())
                    if (state2.getValue(BlockStateProperties.SLAB_TYPE).equals(SlabType.BOTTOM)) return true;
            }

            if (state2.getBlock() instanceof TranslucentWall wall){
                {
                    if (wall.getModBlock() == getModBlock())
                        return isMatchingBelow(state2, state);
                }
            }

        } else if (state2.getBlock() instanceof TranslucentWall wall){
            if (wall.getModBlock() == getModBlock())
                return true;
        }

        return super.skipRendering(state, state2, dir);
    }


    private boolean isMatchingBelow(BlockState state, BlockState state2){
        if (state.getValue(WallBlock.EAST_WALL).equals(WallSide.LOW) && state2.getValue(WallBlock.EAST_WALL).equals(WallSide.TALL)) state = state.setValue(WallBlock.EAST_WALL, WallSide.TALL);
        if (state.getValue(WallBlock.WEST_WALL).equals(WallSide.LOW) && state2.getValue(WallBlock.WEST_WALL).equals(WallSide.TALL)) state = state.setValue(WallBlock.WEST_WALL, WallSide.TALL);
        if (state.getValue(WallBlock.NORTH_WALL).equals(WallSide.LOW) && state2.getValue(WallBlock.NORTH_WALL).equals(WallSide.TALL)) state = state.setValue(WallBlock.NORTH_WALL, WallSide.TALL);
        if (state.getValue(WallBlock.SOUTH_WALL).equals(WallSide.LOW) && state2.getValue(WallBlock.SOUTH_WALL).equals(WallSide.TALL)) state = state.setValue(WallBlock.SOUTH_WALL, WallSide.TALL);

        return state.getShape(null, null, null).
            equals(state2.getShape(null, null, null));
    }
}
