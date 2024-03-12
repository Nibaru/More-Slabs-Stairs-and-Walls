package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (ModBlocks modBlock : ModBlocks.values()) {
            if(modBlock.hasBlock(ModBlocks.BlockType.SLAB)) createSlabBlockState(modBlock, blockStateModelGenerator);
            if(modBlock.hasBlock(ModBlocks.BlockType.STAIRS)) createStairsBlockState(modBlock, blockStateModelGenerator);
            if(modBlock.hasBlock(ModBlocks.BlockType.WALL)) createWallBlockState(modBlock, blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private void createSlabBlockState(ModBlocks block, BlockStateModelGenerator blockStateModelGenerator){
        TextureMap textureMap = getTextureMap(block);
        Identifier slab = null;
        Identifier slabTop = null;
        Identifier slabDouble = getResourceId(Registries.BLOCK.getId(block.parentBlock));

        switch (block.modelType){
            case LOG -> textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + Registries.BLOCK.getId(block.parentBlock).toString().split(":")[1]));

            case GRASS -> {
                textureMap.put(TextureKey.LAYER0, getIdWithSuffix(getResourceId(Registries.BLOCK.getId(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureKey.BOTTOM, getResourceId(Registries.BLOCK.getId((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureKey.TOP, getResourceId(Registries.BLOCK.getId(block.parentBlock)));

                slab = getTemplateModel("template_grass_slab", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.LAYER0).upload(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelCollector);
                slabTop = getTemplateModel("template_grass_slab_top", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.LAYER0).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case LEAVES -> {
                slab = getTemplateModel("template_leaves_slab", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelCollector);
                slabTop = getTemplateModel("template_leaves_slab_top", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelCollector);
                slabDouble = getTemplateModel("template_leaves_slab_double", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"),  textureMap, blockStateModelGenerator.modelCollector);

            }
            case GLASS -> {
                textureMap.put(TextureKey.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  "_side"));
                textureMap.put(TextureKey.END, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                slab = getTemplateModel("template_glass_slab",TextureKey.END, TextureKey.SIDE).upload(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelCollector);
                slabTop = getTemplateModel("template_glass_slab_top",TextureKey.END, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelCollector);
                slabDouble = getTemplateModel("template_glass_slab_double", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"),  textureMap, blockStateModelGenerator.modelCollector);

            }
            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }

            case GLAZED_TERRACOTTA -> {
                textureMap.put(TextureKey.ALL, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                slab = getTemplateModel("glazed_terracotta_slab", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelCollector);
                slabTop = getTemplateModel("glazed_terracotta_slab_top", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelCollector);
                blockStateModelGenerator.blockStateCollector.accept(createRotatableSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, getResourceId(Registries.BLOCK.getId(block.parentBlock)), false));
                return;
            }

            case CUSTOM -> {
                if(block.toString().contains("waxed")){
                    slabDouble = new Identifier("minecraft", "block/" + block.textureId);
                }
            }

            case TRANSLUCENT -> {
                textureMap.put(TextureKey.ALL, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                slab = getTemplateModel("translucent_slab", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, blockStateModelGenerator.modelCollector);
                slabTop = getTemplateModel("translucent_slab_top", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, blockStateModelGenerator.modelCollector);
            }
        }

        if(slab == null) slab = Models.SLAB.upload(block.getBlock(ModBlocks.BlockType.SLAB),  textureMap, blockStateModelGenerator.modelCollector);
        if(slabTop == null) slabTop = Models.SLAB_TOP.upload(block.getBlock(ModBlocks.BlockType.SLAB),  textureMap, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, slabDouble));
    }

    private void createStairsBlockState(ModBlocks block, BlockStateModelGenerator blockStateModelGenerator){
        TextureMap textureMap = getTextureMap(block);
        Identifier stair = null;
        Identifier inner = null;
        Identifier outer = null;

        switch (block.modelType){
            case LOG ->textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + Registries.BLOCK.getId(block.parentBlock).toString().split(":")[1]));
            case GRASS -> {
                TextureKey[] textureKeys = new TextureKey[]{TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.LAYER0};

                textureMap.put(TextureKey.LAYER0, getIdWithSuffix(getResourceId(Registries.BLOCK.getId(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureKey.BOTTOM, getResourceId(Registries.BLOCK.getId((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureKey.TOP, getResourceId(Registries.BLOCK.getId(block.parentBlock)));


                stair = getTemplateModel("template_grass_stairs", textureKeys).upload(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelCollector);
                inner = getTemplateModel("template_grass_stairs_inner", textureKeys).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
                outer = getTemplateModel("template_grass_stairs_outer", textureKeys).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);

                Identifier stairUp = getTemplateModel("template_grass_stairs_up", textureKeys).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_up"),  textureMap, blockStateModelGenerator.modelCollector);
                Identifier innerUp = getTemplateModel("template_grass_stairs_inner_up", textureKeys).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner_up"),  textureMap, blockStateModelGenerator.modelCollector);
                Identifier outerUp = getTemplateModel("template_grass_stairs_outer_up", textureKeys).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer_up"),  textureMap, blockStateModelGenerator.modelCollector);

                blockStateModelGenerator.blockStateCollector.accept(createGrassStairsBlockState(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer, stairUp, innerUp, outerUp, block.modelType == ModBlocks.ModelType.GRASS));
                return;

            }
            case LEAVES -> {
                stair = getTemplateModel("template_leaves_stairs", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelCollector);
                inner = getTemplateModel("template_leaves_stairs_inner", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
                outer = getTemplateModel("template_leaves_stairs_outer", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case GLAZED_TERRACOTTA -> {
                stair = getTemplateModel("glazed_terracotta_stairs", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelCollector);
                inner = getTemplateModel("glazed_terracotta_stairs_inner", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
                outer = getTemplateModel("glazed_terracotta_stairs_outer", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case GLASS -> {
                textureMap.put(TextureKey.BOTTOM, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                textureMap.put(TextureKey.FRONT, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_side"));
                textureMap.put(TextureKey.TOP, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_small"));
                textureMap.put(TextureKey.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_side"));

                stair = getTemplateModel("template_glass_stairs", TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.TOP, TextureKey.SIDE).upload(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelCollector);
                inner = getTemplateModel("template_glass_stairs_inner", TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
                outer = getTemplateModel("template_glass_stairs_outer", TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case TRANSLUCENT -> {
                textureMap.put(TextureKey.ALL, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                stair = getTemplateModel("translucent_stairs", TextureKey.ALL).upload(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelCollector);
                inner = getTemplateModel("translucent_stairs_inner", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
                outer = getTemplateModel("translucent_stairs_outer", TextureKey.ALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);
            }

            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }
        }

        if(stair == null) stair = Models.STAIRS.upload(block.getBlock(ModBlocks.BlockType.STAIRS),  textureMap, blockStateModelGenerator.modelCollector);
        if(inner == null) inner = Models.INNER_STAIRS.upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelCollector);
        if(outer == null) outer = Models.OUTER_STAIRS.upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(createStairsBlockState(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer, block.modelType != ModBlocks.ModelType.GLASS && block.modelType != ModBlocks.ModelType.GLAZED_TERRACOTTA));
    }

    private void createWallBlockState(ModBlocks block, BlockStateModelGenerator blockStateModelGenerator) {
        if(block.modelType == ModBlocks.ModelType.PATH || block.modelType == ModBlocks.ModelType.GLASS || block.equals(ModBlocks.ICE))  return;
        Identifier inventory;
        TextureMap textureMap = getTextureMap(block);
        Identifier post;
        Identifier low;
        Identifier tall;


        switch (block.modelType){
            case GRASS -> {
                textureMap.put(TextureKey.LAYER0, getIdWithSuffix(getResourceId(Registries.BLOCK.getId(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureKey.BOTTOM, getResourceId(Registries.BLOCK.getId((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureKey.TOP, getResourceId(Registries.BLOCK.getId(block.parentBlock)));
                textureMap.put(TextureKey.SIDE, getIdWithSuffix(getResourceId(Registries.BLOCK.getId(block.parentBlock)), "_side"));

                post = getTemplateModel("template_grass_wall_post", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.LAYER0).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("template_grass_wall_side", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.LAYER0).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("template_grass_wall_side_tall", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.LAYER0).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("template_grass_wall_inventory", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, TextureKey.LAYER0).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }

            case LOG -> {
                textureMap.put(TextureKey.SIDE, new Identifier("minecraft", "block/" + Registries.BLOCK.getId(block.parentBlock).toString().split(":")[1]));
                textureMap.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + Registries.BLOCK.getId(block.parentBlock).toString().split(":")[1] + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("template_column_wall_side", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("template_column_wall_side_tall", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("template_column_wall_inventory", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }

            case LEAVES -> {
                post = getTemplateModel("template_leaves_wall_post", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("template_leaves_wall_side", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("template_leaves_wall_side_tall", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("template_leaves_wall_inventory", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case CUBE_BOTTOM_TOP, ROOTS -> {
                textureMap.put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + Registries.BLOCK.getId(block.parentBlock).toString().split(":")[1] + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("template_column_wall_side", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("template_column_wall_side_tall", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("template_column_wall_inventory", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case CUSTOM_SIDE_BOTTOM_TOP -> {
                post = getTemplateModel("template_column_wall_post", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("template_column_wall_side", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("template_column_wall_side_tall", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("template_column_wall_inventory", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }
            case TRANSLUCENT ->{
                post = getTemplateModel("translucent_wall_post", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelCollector);
                low = getTemplateModel("translucent_wall_side", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelCollector);
                tall = getTemplateModel("translucent_wall_side_tall", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelCollector);
                inventory = getTemplateModel("translucent_wall_inventory", TextureKey.WALL).upload(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelCollector);
            }



            default -> {
                post = Models.TEMPLATE_WALL_POST.upload(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelCollector);
                low = Models.TEMPLATE_WALL_SIDE.upload(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelCollector);
                tall = Models.TEMPLATE_WALL_SIDE_TALL.upload(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelCollector);
                inventory = Models.WALL_INVENTORY.upload(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelCollector);
            }

        }
        blockStateModelGenerator.registerParentedItemModel(block.getBlock(ModBlocks.BlockType.WALL).asItem(), inventory);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(block.getBlock(ModBlocks.BlockType.WALL), post, low, tall));
    }

    private TextureMap getTextureMap(ModBlocks block){
        return switch (block.modelType) {
            case LOG, CUBE_BOTTOM_TOP, GRASS -> TexturedModel.CUBE_COLUMN.get(block.parentBlock).getTextures();
            case LEAVES -> TexturedModel.LEAVES.get(block.parentBlock).getTextures();
            case CUSTOM -> new TextureMap().put(TextureKey.ALL, new Identifier("minecraft", "block/" + block.textureId));
            case CUSTOM_SIDE_BOTTOM_TOP -> new TextureMap()
                    .put(TextureKey.SIDE, new Identifier("minecraft", "block/" + block.textureId))
                    .put(TextureKey.BOTTOM, new Identifier("minecraft", "block/" + block.bottomId))
                    .put(TextureKey.TOP, new Identifier("minecraft", "block/" + block.topId));
            case SLIME -> new TextureMap().put(TextureKey.ALL, new Identifier("minecraft", "block/slime_block"));
            case ROOTS -> new TextureMap().put(TextureKey.SIDE, new Identifier("minecraft", "block/mangrove_roots_side"))
                    .put(TextureKey.TOP, new Identifier("minecraft", "block/mangrove_roots_top"));
            case HONEY -> new TextureMap().put(TextureKey.ALL, new Identifier("minecraft", "block/honey_block_bottom"));
            default -> TexturedModel.CUBE_ALL.get(block.parentBlock).getTextures();
        };

    }

    private Model getTemplateModel(String templateName, TextureKey... requiredTextureKeys){
        return new Model(Optional.of(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "block/" + templateName)), Optional.empty(), requiredTextureKeys);
    }

    private Identifier getResourceId(Identifier id) {
        return new Identifier(id.getNamespace(), "block/" + id.getPath());
    }

    private Identifier getIdWithSuffix(Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), id.getPath() + suffix);
    }

    public static BlockStateSupplier createSlabBlockState(Block slabBlock, Identifier bottomModelId, Identifier topModelId, Identifier fullModelId) {
        return VariantsBlockStateSupplier.create(slabBlock).coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE).register(SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomModelId)).register(SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topModelId)).register(SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, fullModelId)));
    }

    public static BlockStateSupplier createStairsBlockState(Block stairsBlock, Identifier regularModelId, Identifier innerModelId, Identifier outerModelId, boolean uvLock) {
        return VariantsBlockStateSupplier.create(stairsBlock).coordinate(
                BlockStateVariantMap.create(
                        Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId)).register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, uvLock)).register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, uvLock)).register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModelId).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, uvLock)));
    }

    public static BlockStateSupplier createGrassStairsBlockState(Block stairsBlock, Identifier regular, Identifier inner, Identifier outer, Identifier regularUp, Identifier innerUp, Identifier outerUp, boolean uvLock) {
        return VariantsBlockStateSupplier.create(stairsBlock).coordinate(
                BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE)
                        .register(Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regular))
                        .register(Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regular)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regular)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regular)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer))
                        .register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer))
                        .register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outer)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner))
                        .register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner))
                        .register(Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, inner)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))


                        .register(Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regularUp)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regularUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regularUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, regularUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, outerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                .put(VariantSettings.UVLOCK, uvLock))
                        .register(Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, innerUp)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                .put(VariantSettings.UVLOCK, uvLock)));
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
}
