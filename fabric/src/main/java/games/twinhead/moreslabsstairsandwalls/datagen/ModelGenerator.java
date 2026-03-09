package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.client.*;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (ModBlocks modBlock : ModBlocks.values()) {
            if(modBlock.hasBlock(ModBlocks.BlockType.SLAB)) createSlabBlockState(modBlock, blockStateModelGenerator);
            if(modBlock.hasBlock(ModBlocks.BlockType.STAIRS)) createStairsBlockState(modBlock, blockStateModelGenerator);
            if(modBlock.hasBlock(ModBlocks.BlockType.WALL)) createWallBlockState(modBlock, blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    private void createSlabBlockState(ModBlocks block, BlockModelGenerators blockStateModelGenerator){
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation slab = null;
        ResourceLocation slabTop = null;
        ResourceLocation slabDouble = getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock));

        switch (block.modelType){
            case LOG -> textureMap.put(TextureSlot.SIDE, new ResourceLocation("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.parentBlock).toString().split(":")[1]));

            case GRASS -> {
                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(BuiltInRegistries.BLOCK.getKey((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureSlot.TOP, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));

                slab = getTemplateModel("template_grass_slab", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelOutput);
                slabTop = getTemplateModel("template_grass_slab_top", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case LEAVES -> {
                slab = getTemplateModel("template_leaves_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelOutput);
                slabTop = getTemplateModel("template_leaves_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelOutput);
                slabDouble = getTemplateModel("template_leaves_slab_double", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"),  textureMap, blockStateModelGenerator.modelOutput);

            }
            case GLASS -> {
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  "_side"));
                textureMap.put(TextureSlot.END, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                slab = getTemplateModel("template_glass_slab",TextureSlot.END, TextureSlot.SIDE).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelOutput);
                slabTop = getTemplateModel("template_glass_slab_top",TextureSlot.END, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelOutput);
                slabDouble = getTemplateModel("template_glass_slab_double", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"),  textureMap, blockStateModelGenerator.modelOutput);

            }
            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }

            case GLAZED_TERRACOTTA -> {
                textureMap.put(TextureSlot.ALL, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                slab = getTemplateModel("glazed_terracotta_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)),  textureMap, blockStateModelGenerator.modelOutput);
                slabTop = getTemplateModel("glazed_terracotta_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"),  textureMap, blockStateModelGenerator.modelOutput);
                blockStateModelGenerator.blockStateOutput.accept(createRotatableSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)), false));
                return;
            }

            case CUSTOM -> {
                if(block.toString().contains("waxed")){
                    slabDouble = new ResourceLocation("minecraft", "block/" + block.textureId);
                }
            }

            case TRANSLUCENT -> {
                textureMap.put(TextureSlot.ALL, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                slab = getTemplateModel("translucent_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, blockStateModelGenerator.modelOutput);
                slabTop = getTemplateModel("translucent_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, blockStateModelGenerator.modelOutput);
            }
        }

        if(slab == null) slab = ModelTemplates.SLAB_BOTTOM.create(block.getBlock(ModBlocks.BlockType.SLAB),  textureMap, blockStateModelGenerator.modelOutput);
        if(slabTop == null) slabTop = ModelTemplates.SLAB_TOP.create(block.getBlock(ModBlocks.BlockType.SLAB),  textureMap, blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(createSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, slabDouble));
    }

    private void createStairsBlockState(ModBlocks block, BlockModelGenerators blockStateModelGenerator){
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation stair = null;
        ResourceLocation inner = null;
        ResourceLocation outer = null;

        switch (block.modelType){
            case LOG ->textureMap.put(TextureSlot.SIDE, new ResourceLocation("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.parentBlock).toString().split(":")[1]));
            case GRASS -> {
                TextureSlot[] textureKeys = new TextureSlot[]{TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0};

                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(BuiltInRegistries.BLOCK.getKey((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureSlot.TOP, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));


                stair = getTemplateModel("template_grass_stairs", textureKeys).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelOutput);
                inner = getTemplateModel("template_grass_stairs_inner", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
                outer = getTemplateModel("template_grass_stairs_outer", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);

                ResourceLocation stairUp = getTemplateModel("template_grass_stairs_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_up"),  textureMap, blockStateModelGenerator.modelOutput);
                ResourceLocation innerUp = getTemplateModel("template_grass_stairs_inner_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner_up"),  textureMap, blockStateModelGenerator.modelOutput);
                ResourceLocation outerUp = getTemplateModel("template_grass_stairs_outer_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer_up"),  textureMap, blockStateModelGenerator.modelOutput);

                blockStateModelGenerator.blockStateOutput.accept(createGrassStairsBlockState(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer, stairUp, innerUp, outerUp, block.modelType == ModBlocks.ModelType.GRASS));
                return;

            }
            case LEAVES -> {
                stair = getTemplateModel("template_leaves_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelOutput);
                inner = getTemplateModel("template_leaves_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
                outer = getTemplateModel("template_leaves_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case GLAZED_TERRACOTTA -> {
                stair = getTemplateModel("glazed_terracotta_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelOutput);
                inner = getTemplateModel("glazed_terracotta_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
                outer = getTemplateModel("glazed_terracotta_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case GLASS -> {
                textureMap.put(TextureSlot.BOTTOM, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                textureMap.put(TextureSlot.FRONT, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_side"));
                textureMap.put(TextureSlot.TOP, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_small"));
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_side"));

                stair = getTemplateModel("template_glass_stairs", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelOutput);
                inner = getTemplateModel("template_glass_stairs_inner", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
                outer = getTemplateModel("template_glass_stairs_outer", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case TRANSLUCENT -> {
                textureMap.put(TextureSlot.ALL, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                stair = getTemplateModel("translucent_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)),  textureMap, blockStateModelGenerator.modelOutput);
                inner = getTemplateModel("translucent_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
                outer = getTemplateModel("translucent_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);
            }

            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }
        }

        if(stair == null) stair = ModelTemplates.STAIRS_STRAIGHT.create(block.getBlock(ModBlocks.BlockType.STAIRS),  textureMap, blockStateModelGenerator.modelOutput);
        if(inner == null) inner = ModelTemplates.STAIRS_INNER.create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"),  textureMap, blockStateModelGenerator.modelOutput);
        if(outer == null) outer = ModelTemplates.STAIRS_OUTER.create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"),  textureMap, blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(createStairsBlockState(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer, block.modelType != ModBlocks.ModelType.GLASS && block.modelType != ModBlocks.ModelType.GLAZED_TERRACOTTA));
    }

    private void createWallBlockState(ModBlocks block, BlockModelGenerators blockStateModelGenerator) {
        if(block.modelType == ModBlocks.ModelType.PATH || block.modelType == ModBlocks.ModelType.GLASS || block.equals(ModBlocks.ICE) || block.equals(ModBlocks.SLIME_BLOCK) || block.equals(ModBlocks.HONEY_BLOCK))  return;
        ResourceLocation inventory;
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation post;
        ResourceLocation low;
        ResourceLocation tall;


        switch (block.modelType){
            case GRASS -> {
                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(BuiltInRegistries.BLOCK.getKey((block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT))));
                if(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) textureMap.put(TextureSlot.TOP, getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)));
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(BuiltInRegistries.BLOCK.getKey(block.parentBlock)), "_side"));

                post = getTemplateModel("template_grass_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("template_grass_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("template_grass_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("template_grass_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }

            case LOG -> {
                textureMap.put(TextureSlot.SIDE, new ResourceLocation("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.parentBlock).toString().split(":")[1]));
                textureMap.put(TextureSlot.BOTTOM, new ResourceLocation("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.parentBlock).toString().split(":")[1] + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }

            case LEAVES -> {
                post = getTemplateModel("template_leaves_wall_post", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("template_leaves_wall_side", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("template_leaves_wall_side_tall", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("template_leaves_wall_inventory", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case CUBE_BOTTOM_TOP, ROOTS -> {
                textureMap.put(TextureSlot.BOTTOM, new ResourceLocation("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.parentBlock).toString().split(":")[1] + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case CUSTOM_SIDE_BOTTOM_TOP -> {
                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }
            case TRANSLUCENT ->{
                post = getTemplateModel("translucent_wall_post", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"),  textureMap, blockStateModelGenerator.modelOutput);
                low = getTemplateModel("translucent_wall_side", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"),  textureMap, blockStateModelGenerator.modelOutput);
                tall = getTemplateModel("translucent_wall_side_tall", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"),  textureMap, blockStateModelGenerator.modelOutput);
                inventory = getTemplateModel("translucent_wall_inventory", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"),  textureMap, blockStateModelGenerator.modelOutput);
            }



            default -> {
                post = ModelTemplates.WALL_POST.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelOutput);
                low = ModelTemplates.WALL_LOW_SIDE.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelOutput);
                tall = ModelTemplates.WALL_TALL_SIDE.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelOutput);
                inventory = ModelTemplates.WALL_INVENTORY.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, blockStateModelGenerator.modelOutput);
            }

        }
        blockStateModelGenerator.delegateItemModel(block.getBlock(ModBlocks.BlockType.WALL).asItem(), inventory);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createWall(block.getBlock(ModBlocks.BlockType.WALL), post, low, tall));
    }

    private TextureMapping getTextureMap(ModBlocks block){
        return switch (block.modelType) {
            case LOG, CUBE_BOTTOM_TOP, GRASS -> TexturedModel.COLUMN.get(block.parentBlock).getMapping();
            case LEAVES -> TexturedModel.LEAVES.get(block.parentBlock).getMapping();
            case CUSTOM -> new TextureMapping().put(TextureSlot.ALL, new ResourceLocation("minecraft", "block/" + block.textureId));
            case CUSTOM_SIDE_BOTTOM_TOP -> new TextureMapping()
                    .put(TextureSlot.SIDE, new ResourceLocation("minecraft", "block/" + block.textureId))
                    .put(TextureSlot.BOTTOM, new ResourceLocation("minecraft", "block/" + block.bottomId))
                    .put(TextureSlot.TOP, new ResourceLocation("minecraft", "block/" + block.topId));
            case SLIME -> new TextureMapping().put(TextureSlot.ALL, new ResourceLocation("minecraft", "block/slime_block"));
            case ROOTS -> new TextureMapping().put(TextureSlot.SIDE, new ResourceLocation("minecraft", "block/mangrove_roots_side"))
                    .put(TextureSlot.TOP, new ResourceLocation("minecraft", "block/mangrove_roots_top"));
            case HONEY -> new TextureMapping().put(TextureSlot.ALL, new ResourceLocation("minecraft", "block/honey_block_bottom"));
            default -> TexturedModel.CUBE.get(block.parentBlock).getMapping();
        };

    }

    private ModelTemplate getTemplateModel(String templateName, TextureSlot... requiredTextureKeys){
        return new ModelTemplate(Optional.of(new ResourceLocation(MoreSlabsStairsAndWalls.MOD_ID, "block/" + templateName)), Optional.empty(), requiredTextureKeys);
    }

    private ResourceLocation getResourceId(ResourceLocation id) {
        return new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
    }

    private ResourceLocation getIdWithSuffix(ResourceLocation id, String suffix) {
        return new ResourceLocation(id.getNamespace(), id.getPath() + suffix);
    }

    public static BlockStateGenerator createSlabBlockState(Block slabBlock, ResourceLocation bottomModelId, ResourceLocation topModelId, ResourceLocation fullModelId) {
        return MultiVariantGenerator.multiVariant(slabBlock).with(PropertyDispatch.property(BlockStateProperties.SLAB_TYPE).select(SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bottomModelId)).select(SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, topModelId)).select(SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, fullModelId)));
    }

    public static BlockStateGenerator createStairsBlockState(Block stairsBlock, ResourceLocation regularModelId, ResourceLocation innerModelId, ResourceLocation outerModelId, boolean uvLock) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(
                PropertyDispatch.properties(
                        BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE).select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)).select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)).select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock)).select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)));
    }

    public static BlockStateGenerator createGrassStairsBlockState(Block stairsBlock, ResourceLocation regular, ResourceLocation inner, ResourceLocation outer, ResourceLocation regularUp, ResourceLocation innerUp, ResourceLocation outerUp, boolean uvLock) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(
                PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regular))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regular)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regular)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regular)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outer))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outer))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, inner))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, inner))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))


                        .select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regularUp)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regularUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regularUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, regularUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, outerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                .with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant()
                                .with(VariantProperties.MODEL, innerUp)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                .with(VariantProperties.UV_LOCK, uvLock)));
    }
    public static BlockStateGenerator createRotatableSlabBlockState(Block slabBlock, ResourceLocation bottomId, ResourceLocation topId, ResourceLocation doubleId, boolean uvlock) {
        return MultiVariantGenerator.multiVariant(slabBlock).with(PropertyDispatch.properties(BlockStateProperties.SLAB_TYPE, BlockStateProperties.HORIZONTAL_FACING)
                .select(SlabType.BOTTOM, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, bottomId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.BOTTOM, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, bottomId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.BOTTOM, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, bottomId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.BOTTOM, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, bottomId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.DOUBLE, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, doubleId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.DOUBLE, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, doubleId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.DOUBLE, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, doubleId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.DOUBLE, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, doubleId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.TOP, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, topId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.TOP, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, topId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.TOP, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, topId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvlock))
                .select(SlabType.TOP, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, topId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvlock)));
    }
}
