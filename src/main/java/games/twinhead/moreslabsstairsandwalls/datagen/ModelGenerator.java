package games.twinhead.moreslabsstairsandwalls.datagen;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Objects;
import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (ModBlocks block: ModBlocks.values()) {
            if(block.hasSlab())
                generateSlab(blockStateModelGenerator, block);
            if(block.hasStairs())
                generateStairs(blockStateModelGenerator, block);
            if(block.hasWall())
                generateWall(blockStateModelGenerator, block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

    public static BlockStateSupplier createSlabBlockState(Block slabBlock, Identifier bottomModelId, Identifier topModelId, Identifier fullModelId) {
        return VariantsBlockStateSupplier.create(slabBlock).coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE).register(SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomModelId)).register(SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topModelId)).register(SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, fullModelId)));
    }

    public static BlockStateSupplier createRotatableSlabBlockState(Block slabBlock, Identifier bottomId, Identifier topId, Identifier doubleId, boolean uvlock) {
        return VariantsBlockStateSupplier.create(slabBlock).coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE, Properties.HORIZONTAL_FACING)
                .register(SlabType.BOTTOM, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, bottomId).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.BOTTOM, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, bottomId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.BOTTOM, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, bottomId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.BOTTOM, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, bottomId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.DOUBLE, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, doubleId).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.DOUBLE, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, doubleId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.DOUBLE, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, doubleId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.DOUBLE, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, doubleId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.TOP, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, topId).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.TOP, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, topId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.TOP, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, topId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvlock))
                .register(SlabType.TOP, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, topId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvlock)));
    }

    public void generateSlab(BlockStateModelGenerator blockStateModelGenerator, ModBlocks block){
        TextureMap textureMap = TextureMap.all(block.getCopyBlock());

        if(block.hasCustomTexture()){
            if(Objects.equals(block.getSideTexture(), block.getTopTexture()) && Objects.equals(block.getSideTexture(), block.getBottomTexture())){
                textureMap.put(TextureKey.ALL, new Identifier("minecraft", "block/" + block.getSideTexture()));
            } else {
                textureMap = TextureMap.sideTopBottom(block.getCopyBlock());
                textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + block.getSideTexture()));
                textureMap.put(TextureKey.TOP, new Identifier("minecraft", "block/" + block.getTopTexture()));
                textureMap.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + block.getBottomTexture()));
            }
        }

        Identifier slab;
        Identifier slabTop;

        if (block == ModBlocks.GRASS_BLOCK) {
            slab = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/grass_slab")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab"), textureMap, blockStateModelGenerator.modelCollector);
            slabTop = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/grass_slab_top")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab_top"), textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(block.getSlabBlock(), slab, slabTop, new Identifier("minecraft", "block/" + block.toString().toLowerCase())));
            return;
        }

        if(block.toString().toLowerCase().contains("glazed")){
            slab =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glazed_terracotta_slab")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab"), textureMap, blockStateModelGenerator.modelCollector);
            slabTop =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glazed_terracotta_slab_top")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab_top"), textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(createRotatableSlabBlockState(block.getSlabBlock(), slab, slabTop, new Identifier("minecraft", "block/" + block.toString().toLowerCase()), false));
            return;
        }



        if(block.toString().toLowerCase().contains("leaves")){
            slab =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_slab")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab"), textureMap, blockStateModelGenerator.modelCollector);
            slabTop =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_slab_top")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab_top"), textureMap, blockStateModelGenerator.modelCollector);
        }else {
            slab = Models.SLAB.upload(block.getSlabBlock(),
                    (block.toString().contains("glass") ? textureMap.put(TextureKey.SIDE, new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab_side")) : textureMap ), blockStateModelGenerator.modelCollector);
            slabTop = Models.SLAB_TOP.upload(block.getSlabBlock(), textureMap, blockStateModelGenerator.modelCollector);
        }



        Identifier fullId = new Identifier("minecraft" , "block/" + (block.toString().toLowerCase().contains("waxed") ? block.toString().toLowerCase().replace("waxed_", "") : block.toString().toLowerCase()));

        blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(block.getSlabBlock(), slab, slabTop, fullId));
    }

    public static BlockStateSupplier createStairsBlockState(Block stairsBlock, Identifier innerModelId, Identifier regularModelId, Identifier outerModelId, boolean uvLock) {
        return VariantsBlockStateSupplier.create(stairsBlock).coordinate(
                BlockStateVariantMap.create(
                        Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)));
    }

    public void generateStairs(BlockStateModelGenerator blockStateModelGenerator, ModBlocks block){
        boolean uvLock = true;

        TextureMap textureMap = TextureMap.all(block.getCopyBlock());

        if(block.hasCustomTexture()){
            if(Objects.equals(block.getSideTexture(), block.getTopTexture()) && Objects.equals(block.getSideTexture(), block.getBottomTexture())){
                textureMap.put(TextureKey.ALL, new Identifier("minecraft", "block/" + block.getSideTexture()));
            } else {
                textureMap = TextureMap.sideTopBottom(block.getCopyBlock());
                textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + block.getSideTexture()));
                textureMap.put(TextureKey.TOP, new Identifier("minecraft", "block/" + block.getTopTexture()));
                textureMap.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + block.getBottomTexture()));
            }
        }



        TextureMap glass_map = TextureMap.all(block.getCopyBlock());
        glass_map.put(TextureKey.SIDE, new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stair_side"));
        glass_map.put(TextureKey.FRONT, new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_slab_side"));
        glass_map.put(TextureKey.END, new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stair_small"));
        glass_map.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + block.toString().toLowerCase()));

        Identifier stairs;
        Identifier inner;
        Identifier outer;

        if (block == ModBlocks.GRASS_BLOCK) {
            inner = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/grass_stairs_inner")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_inner"), textureMap, blockStateModelGenerator.modelCollector);
            stairs = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/grass_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs"), textureMap, blockStateModelGenerator.modelCollector);
            outer = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/grass_stairs_outer")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_outer"), textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(createStairsBlockState(block.getStairsBlock(), inner, stairs, outer, false));
            return;
        }

        if(block.toString().contains("glass")){
            inner =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glass/glass_stairs_inner")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.FRONT)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_inner"), glass_map, blockStateModelGenerator.modelCollector);
            stairs = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glass/glass_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.FRONT)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs"), glass_map, blockStateModelGenerator.modelCollector);
            outer = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glass/glass_stairs_outer")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.FRONT)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_outer"), glass_map, blockStateModelGenerator.modelCollector);
            uvLock = false;
        } else if(block.toString().contains("leaves")){
            inner =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_inner_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_inner"), textureMap, blockStateModelGenerator.modelCollector);
            stairs = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs"), textureMap, blockStateModelGenerator.modelCollector);
            outer = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_outer_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_outer"), textureMap, blockStateModelGenerator.modelCollector);
        } else if(block.toString().contains("glazed")){
            inner =  new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glazed_terracotta_stairs_inner")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_inner"), textureMap, blockStateModelGenerator.modelCollector);
            stairs = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glazed_terracotta_stairs")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs"), textureMap, blockStateModelGenerator.modelCollector);
            outer = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/glazed_terracotta_stairs_outer")), Optional.empty(), TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_stairs_outer"), textureMap, blockStateModelGenerator.modelCollector);
            uvLock = false;
        } else {
            stairs = Models.STAIRS.upload(block.getStairsBlock(), textureMap, blockStateModelGenerator.modelCollector);
            inner = Models.INNER_STAIRS.upload(block.getStairsBlock(), textureMap, blockStateModelGenerator.modelCollector);
            outer = Models.OUTER_STAIRS.upload(block.getStairsBlock(), textureMap, blockStateModelGenerator.modelCollector);
        }
        blockStateModelGenerator.blockStateCollector.accept(createStairsBlockState(block.getStairsBlock(), inner, stairs, outer, uvLock));
    }

    public void generateWall(BlockStateModelGenerator blockStateModelGenerator, ModBlocks block){
        TextureMap textureMap = TextureMap.all(block.getCopyBlock());

        if(block.hasCustomTexture()){
            textureMap.put(TextureKey.WALL, new Identifier("minecraft", "block/" + block.getSideTexture()));
        }

        Identifier post;
        Identifier low;
        Identifier tall;


        if(block.toString().contains("leaves")) {
            post = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_template_wall_post")), Optional.empty(), TextureKey.WALL)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_post"), textureMap, blockStateModelGenerator.modelCollector);
            low = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_template_wall_side_tall")), Optional.empty(), TextureKey.WALL)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_side_tall"), textureMap, blockStateModelGenerator.modelCollector);
            tall = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_template_wall_side")), Optional.empty(), TextureKey.WALL)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_side"), textureMap, blockStateModelGenerator.modelCollector);

            Identifier inventory = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/leaves_wall_inventory")), Optional.empty(), TextureKey.WALL)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_inventory"), textureMap, blockStateModelGenerator.modelCollector);


            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(block.getWallBlock(), post, low, tall));
            blockStateModelGenerator.registerParentedItemModel(block.getWallBlock().asItem(), inventory);
        } else if(block.toString().contains("log") || block.equals(ModBlocks.BASALT) || block.equals(ModBlocks.POLISHED_BASALT) || block.equals(ModBlocks.HAY_BLOCK) || block.equals(ModBlocks.PUMPKIN) || block.equals(ModBlocks.MELON) || (block.toString().contains("stem") && !block.toString().contains("mushroom"))){

            textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + block.getSideTexture()));
            textureMap.put(TextureKey.LAYER0, new Identifier("minecraft", "block/" + block.getSideTexture()));
            textureMap.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + block.getBottomTexture()));
            textureMap.put(TextureKey.TOP, new Identifier("minecraft", "block/" + block.getTopTexture()));

            post = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/log_wall_post")), Optional.empty(), TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.TOP)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_post"), textureMap, blockStateModelGenerator.modelCollector);

            tall = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/log_wall_side_tall")), Optional.empty(), TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.TOP)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_side_tall"), textureMap, blockStateModelGenerator.modelCollector);

            low = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/log_wall_side")), Optional.empty(), TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.TOP)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_side"), textureMap, blockStateModelGenerator.modelCollector);

            Identifier inventory = new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/log_wall_inventory")), Optional.empty(), TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.TOP)
                    .upload(new Identifier(MoreSlabsStairsAndWalls.mod_id, "block/" + block.toString().toLowerCase() + "_wall_inventory"), textureMap, blockStateModelGenerator.modelCollector);


            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(block.getWallBlock(), post, low, tall));
            blockStateModelGenerator.registerParentedItemModel(block.getWallBlock().asItem(), inventory);

        }  else if(!block.toString().contains("glass")){
            post = Models.TEMPLATE_WALL_POST.upload(block.getWallBlock(), textureMap, blockStateModelGenerator.modelCollector);
            low = Models.TEMPLATE_WALL_SIDE.upload(block.getWallBlock(), textureMap, blockStateModelGenerator.modelCollector);
            tall = Models.TEMPLATE_WALL_SIDE_TALL.upload(block.getWallBlock(), textureMap, blockStateModelGenerator.modelCollector);

            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(block.getWallBlock(), post, low, tall));
            blockStateModelGenerator.registerParentedItemModel(block.getWallBlock().asItem(), Models.WALL_INVENTORY.upload(block.getWallBlock(), textureMap, blockStateModelGenerator.modelCollector ));
        }
    }
}
