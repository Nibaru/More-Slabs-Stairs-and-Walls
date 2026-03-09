package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseSlab extends SlabBlock {

    ModBlocks modBlock;

    public BaseSlab(ModBlocks block, Properties settings) {
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
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return defaultBlockState().ignitedByLava() ? ModRegistry.getBurnChance(this.modBlock) : 0;
    }

    //Used to get the fire spread speed of the block on NeoForge
    public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction face) {
        return defaultBlockState().ignitedByLava() ?  ModRegistry.getSpreadChance(this.modBlock) : 0;
    }
}
