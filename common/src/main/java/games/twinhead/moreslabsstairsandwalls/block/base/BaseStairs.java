package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import java.util.stream.IntStream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseStairs extends StairBlock {

    ModBlocks modBlock;

    public BaseStairs(ModBlocks block, BlockState state, Properties settings) {
        super(state, settings);
        this.registerDefaultState(((((this.defaultBlockState()).setValue(FACING, Direction.NORTH)).setValue(HALF, Half.BOTTOM)).setValue(SHAPE, StairsShape.STRAIGHT)).setValue(WATERLOGGED, false));
        this.modBlock = block;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.STAIRS;
    }

    public static VoxelShape[] makeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        return IntStream.range(0, 16).mapToObj((i) -> makeStairShape(i, base, northWest, northEast, southWest, southEast)).toArray(VoxelShape[]::new);
    }

    public static VoxelShape makeStairShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        VoxelShape voxelShape = base;
        if ((i & 1) != 0) {
            voxelShape = Shapes.or(base, northWest);
        }

        if ((i & 2) != 0) {
            voxelShape = Shapes.or(voxelShape, northEast);
        }

        if ((i & 4) != 0) {
            voxelShape = Shapes.or(voxelShape, southWest);
        }

        if ((i & 8) != 0) {
            voxelShape = Shapes.or(voxelShape, southEast);
        }

        return voxelShape;
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
