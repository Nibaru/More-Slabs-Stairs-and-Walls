package games.twinhead.moreslabsstairsandwalls.block.base;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.stream.IntStream;

public class BaseStairs extends StairsBlock {

    ModBlocks modBlock;

    public BaseStairs(ModBlocks block, BlockState state, Settings settings) {
        super(state, settings);
        this.setDefaultState(((((this.getDefaultState()).with(FACING, Direction.NORTH)).with(HALF, BlockHalf.BOTTOM)).with(SHAPE, StairShape.STRAIGHT)).with(WATERLOGGED, false));
        this.modBlock = block;
    }

    public ModBlocks getModBlock() {
        return this.modBlock;
    }

    public ModBlocks.BlockType getBlockType() {
        return ModBlocks.BlockType.STAIRS;
    }

    public static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        return (VoxelShape[]) IntStream.range(0, 16).mapToObj((i) -> {
            return composeShape(i, base, northWest, northEast, southWest, southEast);
        }).toArray((i) -> {
            return new VoxelShape[i];
        });
    }

    public static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        VoxelShape voxelShape = base;
        if ((i & 1) != 0) {
            voxelShape = VoxelShapes.union(base, northWest);
        }

        if ((i & 2) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, northEast);
        }

        if ((i & 4) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southWest);
        }

        if ((i & 8) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southEast);
        }

        return voxelShape;
    }
}
