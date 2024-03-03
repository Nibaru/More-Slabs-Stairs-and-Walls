package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
public class GlazedTerracottaSlab extends BaseSlab {

    public static final DirectionProperty FACING;

    public GlazedTerracottaSlab(ModBlocks block, Settings settings) {
        super(block,settings);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(ctx.getWorld().getBlockState(ctx.getBlockPos()).getBlock() instanceof GlazedTerracottaSlab){
          return super.getPlacementState(ctx).with(FACING, ctx.getWorld().getBlockState(ctx.getBlockPos()).get(FACING));
        }

        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }



}
