package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class BaseSlab extends SlabBlock {

    ModBlocks modBlock;

    public BaseSlab(ModBlocks block, Settings settings) {
        super(settings);
        this.modBlock = block;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.SLAB;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }


    //Used to get the flammability of the block on NeoForge
    public int getFlammability(BlockState state, BlockView level, BlockPos pos, Direction face) {
        return getDefaultState().isBurnable() ? ModRegistry.getBurnChance(this.modBlock) : 0;
    }

    //Used to get the fire spread speed of the block on NeoForge
    public int getFireSpreadSpeed(BlockState state,BlockView level, BlockPos pos, Direction face) {
        return getDefaultState().isBurnable() ?  ModRegistry.getSpreadChance(this.modBlock) : 0;
    }
}
