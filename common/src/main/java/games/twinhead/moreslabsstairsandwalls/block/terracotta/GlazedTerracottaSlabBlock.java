package games.twinhead.moreslabsstairsandwalls.block.terracotta;

import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;

public class GlazedTerracottaSlabBlock extends BaseSlab {

    public static final DirectionProperty FACING;

    public GlazedTerracottaSlabBlock(Settings settings) {
        super(settings);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(ctx.getWorld().getBlockState(ctx.getBlockPos()).getBlock() instanceof GlazedTerracottaSlabBlock){
          return super.getPlacementState(ctx).with(FACING, ctx.getWorld().getBlockState(ctx.getBlockPos()).get(FACING));
        }

        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }



}
