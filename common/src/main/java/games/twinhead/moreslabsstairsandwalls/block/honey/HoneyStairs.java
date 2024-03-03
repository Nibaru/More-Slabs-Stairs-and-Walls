package games.twinhead.moreslabsstairsandwalls.block.honey;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class HoneyStairs extends TranslucentStairs {

    protected static final VoxelShape TOP_SHAPE = HoneySlab.TOP_SHAPE;
    protected static final VoxelShape BOTTOM_SHAPE = HoneySlab.BOTTOM_SHAPE;
    protected static final VoxelShape BOTTOM_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape BOTTOM_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(1.0, 0.0, 8.0, 8.0, 8.0, 15.0);
    protected static final VoxelShape TOP_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(1.0, 7.0, 1.0, 8.0, 15.0, 8.0);
    protected static final VoxelShape TOP_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(1.0, 7.0, 8.0, 8.0, 15.0, 15.0);
    protected static final VoxelShape BOTTOM_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 0.0, 1.0, 15.0, 8.0, 8.0);
    protected static final VoxelShape BOTTOM_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 0.0, 8.0, 15.0, 8.0, 15.0);
    protected static final VoxelShape TOP_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 7.0, 1.0, 15.0, 15.0, 8.0);
    protected static final VoxelShape TOP_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0, 7.0, 8.0, 15.0, 15.0, 15.0);
    protected static final VoxelShape[] TOP_SHAPES = composeShapes(TOP_SHAPE, BOTTOM_NORTH_WEST_CORNER_SHAPE, BOTTOM_NORTH_EAST_CORNER_SHAPE, BOTTOM_SOUTH_WEST_CORNER_SHAPE, BOTTOM_SOUTH_EAST_CORNER_SHAPE);
    protected static final VoxelShape[] BOTTOM_SHAPES = composeShapes(BOTTOM_SHAPE, TOP_NORTH_WEST_CORNER_SHAPE, TOP_NORTH_EAST_CORNER_SHAPE, TOP_SOUTH_WEST_CORNER_SHAPE, TOP_SOUTH_EAST_CORNER_SHAPE);
    private static final int[] SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};



    public HoneyStairs(ModBlocks modBlocks,BlockState state, Settings settings) {
        super(modBlocks, state, settings);
    }

    @Override

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape[] shapes;
        if (state.get(HALF) == BlockHalf.TOP) {
            shapes = TOP_SHAPES;
        } else {
            shapes = BOTTOM_SHAPES;
        }
        return shapes[SHAPE_INDICES[this.getShapeIndexIndex(state)]];
    }

    private int getShapeIndexIndex(BlockState state) {
        return state.get(SHAPE).ordinal() * 4 + state.get(FACING).getHorizontal();
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 1.0f);
        if (!world.isClient) {
            world.sendEntityStatus(entity, EntityStatuses.DRIP_RICH_HONEY);
        }
        if (entity.handleFallDamage(fallDistance, 0.2f, world.getDamageSources().fall())) {
            entity.playSound(this.soundGroup.getFallSound(), this.soundGroup.getVolume() * 0.5f, this.soundGroup.getPitch() * 0.75f);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isSliding(pos, entity)) {
            HoneySlab.triggerAdvancement(entity, pos);
            HoneySlab.updateSlidingVelocity(entity);
            HoneySlab.addCollisionEffects(world, entity);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    private boolean isSliding(BlockPos pos, Entity entity) {
        if (entity.isOnGround()) {
            return false;
        }
        // would be more correct to use 0.4375 instead of 0.9375 depending on the side the entity is on
        if (entity.getY() > (double)pos.getY() + 0.9375 - 1.0E-7) {
            return false;
        }
        if (entity.getVelocity().y >= -0.08) {
            return false;
        }
        double d = Math.abs((double)pos.getX() + 0.5 - entity.getX());
        double e = Math.abs((double)pos.getZ() + 0.5 - entity.getZ());
        double f = 0.4375 + (double)(entity.getWidth() / 2.0f);
        return d + 1.0E-7 > f || e + 1.0E-7 > f;
    }

    /**
     * @return true if the block is sticky block which used for pull or push adjacent blocks (use by piston)
     */
    public boolean isStickyBlock(BlockState state) {
        return SlimeSlab.isStateHoney(state) || SlimeSlab.isStateSlime(state);
    }

    /**
     * Determines if this block can stick to another block when pushed by a piston.
     *
     * @param other Other block
     * @return True to link blocks
     */
    public boolean canStickTo(BlockState state, BlockState other) {
        if (SlimeSlab.isStateSlime(state) && SlimeSlab.isStateHoney(other)) return false;
        if (SlimeSlab.isStateSlime(other) && SlimeSlab.isStateHoney(state)) return false;
        return isStickyBlock(state) || isStickyBlock(other);
    }

}
