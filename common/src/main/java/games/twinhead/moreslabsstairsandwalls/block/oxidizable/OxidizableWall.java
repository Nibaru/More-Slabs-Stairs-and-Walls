package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public class OxidizableWall extends BaseWall implements CustomOxidizable {

    private final Oxidizable.OxidationLevel oxidationLevel;
    private final ModBlocks nextBlock;

    public OxidizableWall(ModBlocks modblock,Oxidizable.OxidationLevel oxidationLevel, ModBlocks nextBlock, Settings arg) {
        super(modblock,arg);
        this.oxidationLevel = oxidationLevel;
        this.nextBlock = nextBlock;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
       return useItem(state, world, pos, player, hand);
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return this.oxidationLevel != Oxidizable.OxidationLevel.OXIDIZED;
    }

    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

    public Optional<BlockState> getDegradationResult(BlockState state) {
        return Optional.ofNullable((this.nextBlock == null ? state : nextBlock.getBlock(getBlockType()).getStateWithProperties(state)));
    }

}
