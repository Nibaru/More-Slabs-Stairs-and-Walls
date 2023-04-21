package games.twinhead.moreslabsstairsandwalls.block.ice;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;


public class IceSlab extends TranslucentSlab {

    Random random = new Random();

    public IceSlab(ModBlocks block, AbstractBlock.Settings settings) {
        super(block, settings);
    }


    public void onEntityLand(BlockView world, Entity entity) {
        entity.setVelocity(entity.getVelocity().multiply(this.getSlipperiness(), 0.0, this.getSlipperiness()));
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1 && !entity.bypassesSteppingEffects()) {
            double e = this.getSlipperiness();
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0, e));
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    protected void pushOutOfBlocks(double x, double y, double z, Entity entity) {
        BlockPos blockPos = new BlockPos(x, y, z);
        Vec3d vec3d = new Vec3d(x - (double)blockPos.getX(), y - (double)blockPos.getY(), z - (double)blockPos.getZ());
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction direction = Direction.UP;
        double d = Double.MAX_VALUE;
        Direction[] var13 = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP};
        int var14 = var13.length;

        for(int var15 = 0; var15 < var14; ++var15) {
            Direction direction2 = var13[var15];
            mutable.set(blockPos, direction2);
//            if (!entity.world.getBlockState(mutable).isFullCube(entity.world, mutable)) {
//                double e = vec3d.getComponentAlongAxis(direction2.getAxis());
//                double f = direction2.getDirection() == Direction.AxisDirection.POSITIVE ? 1.0 - e : e;
//                if (f < d) {
//                    d = f;
//                    direction = direction2;
//                }
//            }
        }

        float g = random.nextFloat() * 0.2F + 0.1F;
        float h = (float)direction.getDirection().offset();
        Vec3d vec3d2 = entity.getVelocity().multiply(0.75);
        if (direction.getAxis() == Direction.Axis.X) {
            entity.setVelocity((double)(h * g), vec3d2.y, vec3d2.z);
        } else if (direction.getAxis() == Direction.Axis.Y) {
            entity.setVelocity(vec3d2.x, (double)(h * g), vec3d2.z);
        } else if (direction.getAxis() == Direction.Axis.Z) {
            entity.setVelocity(vec3d2.x, vec3d2.y, (double)(h * g));
        }

    }


}
