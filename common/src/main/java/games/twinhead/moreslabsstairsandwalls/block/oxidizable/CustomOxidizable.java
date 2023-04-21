package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Iterator;
import java.util.Optional;

public interface CustomOxidizable {

    default void tickDegradation(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.05688889F) {
            tryDegrade(state, world, pos, random);
        }
    }

    Oxidizable.OxidationLevel getDegradationLevel();

    default void tryDegrade(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = this.getDegradationLevel().ordinal();
        int j = 0;
        int k = 0;
        Iterator var8 = BlockPos.iterateOutwards(pos, 4, 4, 4).iterator();

        while(var8.hasNext()) {
            BlockPos blockPos = (BlockPos)var8.next();
            int l = blockPos.getManhattanDistance(pos);
            if (l > 4) {
                break;
            }

            if (!blockPos.equals(pos)) {
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();

                if (block instanceof CustomOxidizable) {
                    Enum<?> enum_ = ((CustomOxidizable)block).getDegradationLevel();
                    if (this.getDegradationLevel().getClass() == enum_.getClass()) {
                        int m = enum_.ordinal();
                        if (m < i) {
                            return;
                        }

                        if (m > i) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float g = f * f * this.getDegradationChanceMultiplier();
        if (random.nextFloat() < g) {
            this.getDegradationResult(state).ifPresent((statex) -> {
                world.setBlockState(pos, statex);
            });
        }

    }

    default float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == Oxidizable.OxidationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    Optional<BlockState> getDegradationResult(BlockState state);

}
