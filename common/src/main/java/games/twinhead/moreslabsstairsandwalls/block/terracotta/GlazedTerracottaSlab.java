package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
public class GlazedTerracottaSlab extends BaseSlab {

    public static final DirectionProperty FACING;

    public GlazedTerracottaSlab(ModBlocks block, Properties settings) {
        super(block,settings);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if(ctx.getLevel().getBlockState(ctx.getClickedPos()).getBlock() instanceof GlazedTerracottaSlab){
          return super.getStateForPlacement(ctx).setValue(FACING, ctx.getLevel().getBlockState(ctx.getClickedPos()).getValue(FACING));
        }

        return super.getStateForPlacement(ctx).setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    public PushReaction getPistonBehavior(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }



}
