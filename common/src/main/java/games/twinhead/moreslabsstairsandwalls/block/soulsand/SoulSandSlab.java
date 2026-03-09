package games.twinhead.moreslabsstairsandwalls.block.soulsand;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
@SuppressWarnings("deprecation")
public class SoulSandSlab extends BaseSlab {

    protected static final VoxelShape COLLISION_SHAPE_BOTTOM = Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);
    protected static final VoxelShape COLLISION_SHAPE_TOP = Block.box(0.0, 8.0, 0.0, 16.0, 14.0, 16.0);
    protected static final VoxelShape COLLISION_SHAPE_FULL = Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

    public SoulSandSlab(ModBlocks block, Properties settings) {
        super(block,settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(SlabBlock.TYPE)){
            case TOP -> COLLISION_SHAPE_TOP;
            case BOTTOM -> COLLISION_SHAPE_BOTTOM;
            case DOUBLE -> COLLISION_SHAPE_FULL;
        };
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BubbleColumnBlock.updateColumn(world, pos.above(), state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && neighborState.is(Blocks.WATER) || this.getFluidState(state).is(Fluids.WATER)) {
            world.scheduleTick(pos, this, 20);
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleTick(pos, this, 20);
    }
}
