package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

import static net.minecraft.block.Oxidizable.getIncreasedOxidationBlock;

public class OxidizableSlab extends SlabBlock implements CustomOxidizable {

    private final Oxidizable.OxidationLevel oxidationLevel;
    private final ModBlocks nextBlock;

    public OxidizableSlab(Oxidizable.OxidationLevel oxidationLevel, ModBlocks nextBlock, Settings arg) {
        super(arg);
        this.oxidationLevel = oxidationLevel;
        this.nextBlock = nextBlock;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(this.oxidationLevel != Oxidizable.OxidationLevel.OXIDIZED){
            tickDegradation(state, world, pos, random);
        }
    }

    public boolean hasRandomTicks(BlockState state) {
        return getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state) {
        return Optional.ofNullable((this.nextBlock == null ? state : nextBlock.getBlock(ModBlocks.BlockType.WALL).getStateWithProperties(state)));
    }

}
