package games.twinhead.moreslabsstairsandwalls.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.WallBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class OxidizableWallBlock extends WallBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public OxidizableWallBlock(OxidationLevel oxidationLevel,Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }


    @SuppressWarnings("deprecation")
    public void randomTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        this.tickDegradation(blockState, serverWorld, blockPos, random);
    }

    public boolean hasRandomTicks(BlockState blockState) {
        return Oxidizable.getIncreasedOxidationBlock(blockState.getBlock()).isPresent();
    }

    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }
}
