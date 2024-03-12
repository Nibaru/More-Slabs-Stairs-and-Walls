package games.twinhead.moreslabsstairsandwalls.block.translucent;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.WallShape;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

@SuppressWarnings("deprecation")
public class TranslucentWall extends BaseWall {

    public TranslucentWall(ModBlocks modBlock, Settings settings) {
        super(modBlock, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState state2, Direction dir) {
        if (state2.getBlock() == this.getModBlock().parentBlock) return true;

        if (dir.equals(Direction.DOWN)){

            if (state2.getBlock() instanceof TranslucentSlab slab){
                if (slab.getModBlock() == getModBlock())
                    if (state2.get(Properties.SLAB_TYPE).equals(SlabType.TOP)) return true;
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
                    if (state2.get(Properties.SLAB_TYPE).equals(SlabType.BOTTOM)) return true;
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

        return super.isSideInvisible(state, state2, dir);
    }


    private boolean isMatchingBelow(BlockState state, BlockState state2){
        if (state.get(WallBlock.EAST_SHAPE).equals(WallShape.LOW) && state2.get(WallBlock.EAST_SHAPE).equals(WallShape.TALL)) state = state.with(WallBlock.EAST_SHAPE, WallShape.TALL);
        if (state.get(WallBlock.WEST_SHAPE).equals(WallShape.LOW) && state2.get(WallBlock.WEST_SHAPE).equals(WallShape.TALL)) state = state.with(WallBlock.WEST_SHAPE, WallShape.TALL);
        if (state.get(WallBlock.NORTH_SHAPE).equals(WallShape.LOW) && state2.get(WallBlock.NORTH_SHAPE).equals(WallShape.TALL)) state = state.with(WallBlock.NORTH_SHAPE, WallShape.TALL);
        if (state.get(WallBlock.SOUTH_SHAPE).equals(WallShape.LOW) && state2.get(WallBlock.SOUTH_SHAPE).equals(WallShape.TALL)) state = state.with(WallBlock.SOUTH_SHAPE, WallShape.TALL);

        return state.getBlock().getOutlineShape(state, null, null, null).equals(state2.getBlock().getOutlineShape(state2, null, null, null));
    }
}
