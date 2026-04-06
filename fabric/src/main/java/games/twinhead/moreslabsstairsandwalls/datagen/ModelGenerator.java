package games.twinhead.moreslabsstairsandwalls.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
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
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.core.Direction;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators g) {
        for (ModBlocks modBlock : ModBlocks.values()) {
            if (modBlock.hasBlock(ModBlocks.BlockType.SLAB)) {
                createSlabBlockState(modBlock, g);
            }
            if (modBlock.hasBlock(ModBlocks.BlockType.STAIRS)) {
                createStairsBlockState(modBlock, g);
            }
            if (modBlock.hasBlock(ModBlocks.BlockType.WALL)) {
                createWallBlockState(modBlock, g);
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    }

    private void createSlabBlockState(ModBlocks block, BlockModelGenerators g) {
        BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOut = modelOutputFor(g, block.modelType);
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation slab = null;
        ResourceLocation slabTop = null;
        ResourceLocation slabDouble = getResourceId(blockKey(block.parentBlock));

        switch (block.modelType) {
            case LOG -> textureMap.put(TextureSlot.SIDE, ResourceLocation.parse("minecraft:block/" + blockKey(block.parentBlock).getPath()));

            case GRASS -> {
                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(blockKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(blockKey(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT)));
                if (block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) {
                    textureMap.put(TextureSlot.TOP, getResourceId(blockKey(block.parentBlock)));
                }

                slab = getTemplateModel("template_grass_slab", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, modelOut);
                slabTop = getTemplateModel("template_grass_slab_top", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, modelOut);
            }
            case LEAVES, CUTOUT -> {
                slab = getTemplateModel("template_leaves_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, modelOut);
                slabTop = getTemplateModel("template_leaves_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, modelOut);
                slabDouble = getTemplateModel("template_leaves_slab_double", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"), textureMap, modelOut);
            }
            case GLASS -> {
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_side"));
                textureMap.put(TextureSlot.END, getResourceId(blockKey(block.parentBlock)));
                slab = getTemplateModel("template_glass_slab", TextureSlot.END, TextureSlot.SIDE).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, modelOut);
                slabTop = getTemplateModel("template_glass_slab_top", TextureSlot.END, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, modelOut);
                slabDouble = getTemplateModel("template_glass_slab_double", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_double"), textureMap, modelOut);
            }
            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }

            case GLAZED_TERRACOTTA -> {
                textureMap.put(TextureSlot.ALL, getResourceId(blockKey(block.parentBlock)));
                slab = getTemplateModel("glazed_terracotta_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, modelOut);
                slabTop = getTemplateModel("glazed_terracotta_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, modelOut);
                g.blockStateOutput.accept(createRotatableSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, getResourceId(blockKey(block.parentBlock)), false));
                return;
            }

            case CUSTOM -> {
                if (block.toString().contains("waxed")) {
                    slabDouble = ResourceLocation.parse("minecraft:block/" + block.textureId);
                }
            }

            case TRANSLUCENT -> {
                textureMap.put(TextureSlot.ALL, getResourceId(blockKey(block.parentBlock)));
                slab = getTemplateModel("translucent_slab", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), textureMap, modelOut);
                slabTop = getTemplateModel("translucent_slab_top", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_top"), textureMap, modelOut);
            }
            default -> {
            }
        }

        if (slab == null) {
            slab = ModelTemplates.SLAB_BOTTOM.create(block.getBlock(ModBlocks.BlockType.SLAB), textureMap, modelOut);
        }
        if (slabTop == null) {
            slabTop = ModelTemplates.SLAB_TOP.create(block.getBlock(ModBlocks.BlockType.SLAB), textureMap, modelOut);
        }

        g.blockStateOutput.accept(createSlabBlockState(block.getBlock(ModBlocks.BlockType.SLAB), slab, slabTop, slabDouble));
    }

    private void createStairsBlockState(ModBlocks block, BlockModelGenerators g) {
        BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOut = modelOutputFor(g, block.modelType);
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation stair = null;
        ResourceLocation inner = null;
        ResourceLocation outer = null;

        switch (block.modelType) {
            case LOG -> textureMap.put(TextureSlot.SIDE, ResourceLocation.parse("minecraft:block/" + blockKey(block.parentBlock).getPath()));
            case GRASS -> {
                TextureSlot[] textureKeys = new TextureSlot[]{TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.LAYER0};

                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(blockKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(blockKey(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT)));
                if (block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) {
                    textureMap.put(TextureSlot.TOP, getResourceId(blockKey(block.parentBlock)));
                }

                stair = getTemplateModel("template_grass_stairs", textureKeys).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), textureMap, modelOut);
                inner = getTemplateModel("template_grass_stairs_inner", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
                outer = getTemplateModel("template_grass_stairs_outer", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);

                ResourceLocation stairUp = getTemplateModel("template_grass_stairs_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_up"), textureMap, modelOut);
                ResourceLocation innerUp = getTemplateModel("template_grass_stairs_inner_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner_up"), textureMap, modelOut);
                ResourceLocation outerUp = getTemplateModel("template_grass_stairs_outer_up", textureKeys).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer_up"), textureMap, modelOut);

                g.blockStateOutput.accept(createGrassStairsBlockState(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer, stairUp, innerUp, outerUp, block.modelType == ModBlocks.ModelType.GRASS));
                return;
            }
            case LEAVES, CUTOUT -> {
                stair = getTemplateModel("template_leaves_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), textureMap, modelOut);
                inner = getTemplateModel("template_leaves_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
                outer = getTemplateModel("template_leaves_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);
            }
            case GLAZED_TERRACOTTA -> {
                stair = getTemplateModel("glazed_terracotta_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), textureMap, modelOut);
                inner = getTemplateModel("glazed_terracotta_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
                outer = getTemplateModel("glazed_terracotta_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);
            }
            case GLASS -> {
                textureMap.put(TextureSlot.BOTTOM, getResourceId(blockKey(block.parentBlock)));
                textureMap.put(TextureSlot.FRONT, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.SLAB)), "_side"));
                textureMap.put(TextureSlot.TOP, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_small"));
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_side"));

                stair = getTemplateModel("template_glass_stairs", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), textureMap, modelOut);
                inner = getTemplateModel("template_glass_stairs_inner", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
                outer = getTemplateModel("template_glass_stairs_outer", TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);
            }
            case TRANSLUCENT -> {
                textureMap.put(TextureSlot.ALL, getResourceId(blockKey(block.parentBlock)));
                stair = getTemplateModel("translucent_stairs", TextureSlot.ALL).create(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), textureMap, modelOut);
                inner = getTemplateModel("translucent_stairs_inner", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
                outer = getTemplateModel("translucent_stairs_outer", TextureSlot.ALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);
            }

            case PATH, SLIME, HONEY, ROOTS -> {
                return;
            }
            default -> {
            }
        }

        if (stair == null) {
            stair = ModelTemplates.STAIRS_STRAIGHT.create(block.getBlock(ModBlocks.BlockType.STAIRS), textureMap, modelOut);
        }
        if (inner == null) {
            inner = ModelTemplates.STAIRS_INNER.create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_inner"), textureMap, modelOut);
        }
        if (outer == null) {
            outer = ModelTemplates.STAIRS_OUTER.create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.STAIRS)), "_outer"), textureMap, modelOut);
        }

        g.blockStateOutput.accept(createStairsBlockStateNoUvLock(block.getBlock(ModBlocks.BlockType.STAIRS), stair, inner, outer));
    }

    private void createWallBlockState(ModBlocks block, BlockModelGenerators g) {
        if (block.modelType == ModBlocks.ModelType.PATH || block.modelType == ModBlocks.ModelType.GLASS || block.equals(ModBlocks.ICE) || block.equals(ModBlocks.SLIME_BLOCK) || block.equals(ModBlocks.HONEY_BLOCK)) {
            return;
        }
        BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOut = modelOutputFor(g, block.modelType);
        ResourceLocation inventory;
        TextureMapping textureMap = getTextureMap(block);
        ResourceLocation post;
        ResourceLocation low;
        ResourceLocation tall;

        switch (block.modelType) {
            case GRASS -> {
                textureMap.put(TextureSlot.LAYER0, getIdWithSuffix(getResourceId(blockKey(block.parentBlock)), block.equals(ModBlocks.GRASS_BLOCK) ? "_side_overlay" : "_side"));
                textureMap.put(TextureSlot.BOTTOM, getResourceId(blockKey(block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM) ? Blocks.NETHERRACK : Blocks.DIRT)));
                if (block.equals(ModBlocks.CRIMSON_NYLIUM) || block.equals(ModBlocks.WARPED_NYLIUM)) {
                    textureMap.put(TextureSlot.TOP, getResourceId(blockKey(block.parentBlock)));
                }
                textureMap.put(TextureSlot.SIDE, getIdWithSuffix(getResourceId(blockKey(block.parentBlock)), "_side"));

                post = getTemplateModel("template_grass_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("template_grass_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("template_grass_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("template_grass_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.LAYER0).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }

            case LOG -> {
                textureMap.put(TextureSlot.SIDE, ResourceLocation.parse("minecraft:block/" + blockKey(block.parentBlock).getPath()));
                textureMap.put(TextureSlot.BOTTOM, ResourceLocation.parse("minecraft:block/" + blockKey(block.parentBlock).getPath() + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }

            case LEAVES, CUTOUT -> {
                post = getTemplateModel("template_leaves_wall_post", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("template_leaves_wall_side", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("template_leaves_wall_side_tall", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("template_leaves_wall_inventory", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }
            case CUBE_BOTTOM_TOP, ROOTS -> {
                textureMap.put(TextureSlot.BOTTOM, ResourceLocation.parse("minecraft:block/" + blockKey(block.parentBlock).getPath() + "_top"));

                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }
            case CUSTOM_SIDE_BOTTOM_TOP -> {
                post = getTemplateModel("template_column_wall_post", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("template_column_wall_side", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("template_column_wall_side_tall", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("template_column_wall_inventory", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }
            case TRANSLUCENT -> {
                post = getTemplateModel("translucent_wall_post", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_post"), textureMap, modelOut);
                low = getTemplateModel("translucent_wall_side", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side"), textureMap, modelOut);
                tall = getTemplateModel("translucent_wall_side_tall", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_side_tall"), textureMap, modelOut);
                inventory = getTemplateModel("translucent_wall_inventory", TextureSlot.WALL).create(getIdWithSuffix(getResourceId(block.getId(ModBlocks.BlockType.WALL)), "_inventory"), textureMap, modelOut);
            }

            default -> {
                post = ModelTemplates.WALL_POST.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, modelOut);
                low = ModelTemplates.WALL_LOW_SIDE.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, modelOut);
                tall = ModelTemplates.WALL_TALL_SIDE.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, modelOut);
                inventory = ModelTemplates.WALL_INVENTORY.create(block.getBlock(ModBlocks.BlockType.WALL), textureMap, modelOut);
            }
        }
        g.delegateItemModel(block.getBlock(ModBlocks.BlockType.WALL), inventory);
        g.blockStateOutput.accept(createWallBlockStateMultipart(block.getBlock(ModBlocks.BlockType.WALL), post, low, tall));
    }

    /**
     * {@code render_type} on generated block model JSON (vanilla {@link ModelTemplates} omit it).
     * Aligns with {@code MoreSlabsStairsAndWallsFabricClient} render layer registration.
     */
    private static Optional<String> blockModelRenderType(ModBlocks.ModelType modelType) {
        return switch (modelType) {
            case LEAVES -> Optional.of("cutout_mipped");
            case GRASS, ROOTS, CUTOUT -> Optional.of("cutout");
            case GLASS, TRANSLUCENT, SLIME, HONEY -> Optional.of("translucent");
            default -> Optional.empty();
        };
    }

    private static BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutputFor(BlockModelGenerators g, ModBlocks.ModelType modelType) {
        return blockModelRenderType(modelType)
                .map(renderType -> modelOutputInjectingRenderType(g, renderType))
                .orElse(g.modelOutput);
    }

    private static BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutputInjectingRenderType(
            BlockModelGenerators g,
            String renderType) {
        BiConsumer<ResourceLocation, Supplier<JsonElement>> base = g.modelOutput;
        return (id, supplier) -> base.accept(id, () -> {
            JsonElement element = supplier.get();
            if (element instanceof JsonObject object && !object.has("render_type")) {
                object.addProperty("render_type", renderType);
            }
            return element;
        });
    }

    private TextureMapping getTextureMap(ModBlocks block) {
        return switch (block.modelType) {
            case LOG, CUBE_BOTTOM_TOP, GRASS -> TexturedModel.COLUMN.get(block.parentBlock).getMapping();
            case LEAVES -> TexturedModel.LEAVES.get(block.parentBlock).getMapping();
            case CUTOUT -> textureMapForCutout(block);
            case CUSTOM -> textureMapForUniformTextureId(block);
            case CUSTOM_SIDE_BOTTOM_TOP -> new TextureMapping()
                    .put(TextureSlot.SIDE, ResourceLocation.parse("minecraft:block/" + block.textureId))
                    .put(TextureSlot.BOTTOM, ResourceLocation.parse("minecraft:block/" + block.bottomId))
                    .put(TextureSlot.TOP, ResourceLocation.parse("minecraft:block/" + block.topId));
            case SLIME -> new TextureMapping().put(TextureSlot.ALL, ResourceLocation.parse("minecraft:block/slime_block"));
            case ROOTS -> new TextureMapping().put(TextureSlot.SIDE, ResourceLocation.parse("minecraft:block/mangrove_roots_side"))
                    .put(TextureSlot.TOP, ResourceLocation.parse("minecraft:block/mangrove_roots_top"));
            case HONEY -> new TextureMapping().put(TextureSlot.ALL, ResourceLocation.parse("minecraft:block/honey_block_bottom"));
            default -> TexturedModel.CUBE.get(block.parentBlock).getMapping();
        };
    }

    /** {@link ModBlocks.Builder#setAllTexture(String)} / {@code setAllTexture(ModelType, String)} — one texture for every face. */
    private static TextureMapping textureMapForUniformTextureId(ModBlocks block) {
        if (block.textureId == null || block.textureId.isEmpty()) {
            return TexturedModel.CUBE.get(block.parentBlock).getMapping();
        }
        return uniformMinecraftBlockTexture(block.textureId);
    }

    /**
     * Cutout blocks: explicit {@link ModBlocks#textureId}, else unwaxed twin via {@link ModBlocks#associatedBlock}
     * (waxed copper grates share {@code minecraft:block/copper_grate} etc., not {@code waxed_*} paths).
     */
    private static TextureMapping textureMapForCutout(ModBlocks block) {
        if (block.textureId != null && !block.textureId.isEmpty()) {
            return uniformMinecraftBlockTexture(block.textureId);
        }
        if (block.associatedBlock != null) {
            String path = BuiltInRegistries.BLOCK.getKey(block.associatedBlock.parentBlock).getPath();
            return uniformMinecraftBlockTexture(path);
        }
        return TexturedModel.CUBE.get(block.parentBlock).getMapping();
    }

    /**
     * Fills every slot vanilla slab/stair/wall {@link ModelTemplates} commonly read; {@code #all} alone is not always enough.
     */
    private static TextureMapping uniformMinecraftBlockTexture(String textureId) {
        ResourceLocation tex = ResourceLocation.parse("minecraft:block/" + textureId);
        return new TextureMapping()
                .put(TextureSlot.ALL, tex)
                .put(TextureSlot.SIDE, tex)
                .put(TextureSlot.TOP, tex)
                .put(TextureSlot.BOTTOM, tex)
                .put(TextureSlot.END, tex)
                .put(TextureSlot.WALL, tex);
    }

    private ModelTemplate getTemplateModel(String templateName, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(MoreSlabsStairsAndWalls.MOD_ID, "block/" + templateName)), Optional.empty(), requiredTextureKeys);
    }

    private static ResourceLocation blockKey(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private ResourceLocation getResourceId(ResourceLocation id) {
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath());
    }

    private ResourceLocation getIdWithSuffix(ResourceLocation id, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + suffix);
    }

    public static net.minecraft.data.models.blockstates.BlockStateGenerator createSlabBlockState(Block slabBlock, ResourceLocation bottomModelId, ResourceLocation topModelId, ResourceLocation fullModelId) {
        return MultiVariantGenerator.multiVariant(slabBlock).with(
                PropertyDispatch.property(SlabBlock.TYPE)
                        .select(SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bottomModelId))
                        .select(SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, topModelId))
                        .select(SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, fullModelId)));
    }

    /** Same orientations as pre-1.21 generator, with {@code uvlock} false (glass / glazed). */
    private static net.minecraft.data.models.blockstates.BlockStateGenerator createStairsBlockStateNoUvLock(Block stairsBlock, ResourceLocation regularModelId, ResourceLocation innerModelId, ResourceLocation outerModelId) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(
                PropertyDispatch.properties(StairBlock.FACING, StairBlock.HALF, StairBlock.SHAPE)
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
    }

    public static net.minecraft.data.models.blockstates.BlockStateGenerator createGrassStairsBlockState(Block stairsBlock, ResourceLocation regular, ResourceLocation inner, ResourceLocation outer, ResourceLocation regularUp, ResourceLocation innerUp, ResourceLocation outerUp, boolean uvLock) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(
                PropertyDispatch.properties(StairBlock.FACING, StairBlock.HALF, StairBlock.SHAPE)
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner))
                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularUp).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerUp).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock)));
    }

    public static net.minecraft.data.models.blockstates.BlockStateGenerator createRotatableSlabBlockState(Block slabBlock, ResourceLocation bottomId, ResourceLocation topId, ResourceLocation doubleId, boolean uvlock) {
        return MultiVariantGenerator.multiVariant(slabBlock).with(
                PropertyDispatch.properties(SlabBlock.TYPE, BlockStateProperties.HORIZONTAL_FACING)
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

    /** Matches vanilla wall multipart layout (post + four sides × low/tall). */
    private static net.minecraft.data.models.blockstates.BlockStateGenerator createWallBlockStateMultipart(Block wallBlock, ResourceLocation post, ResourceLocation sideLow, ResourceLocation sideTall) {
        return MultiPartGenerator.multiPart(wallBlock)
                .with(Condition.condition().term(BlockStateProperties.UP, true), Variant.variant().with(VariantProperties.MODEL, post).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(WallBlock.NORTH_WALL, WallSide.LOW), Variant.variant().with(VariantProperties.MODEL, sideLow).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(WallBlock.NORTH_WALL, WallSide.TALL), Variant.variant().with(VariantProperties.MODEL, sideTall).with(VariantProperties.UV_LOCK, true))
                .with(Condition.condition().term(WallBlock.EAST_WALL, WallSide.LOW), Variant.variant().with(VariantProperties.MODEL, sideLow).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(WallBlock.EAST_WALL, WallSide.TALL), Variant.variant().with(VariantProperties.MODEL, sideTall).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(WallBlock.SOUTH_WALL, WallSide.LOW), Variant.variant().with(VariantProperties.MODEL, sideLow).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(WallBlock.SOUTH_WALL, WallSide.TALL), Variant.variant().with(VariantProperties.MODEL, sideTall).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .with(Condition.condition().term(WallBlock.WEST_WALL, WallSide.LOW), Variant.variant().with(VariantProperties.MODEL, sideLow).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .with(Condition.condition().term(WallBlock.WEST_WALL, WallSide.TALL), Variant.variant().with(VariantProperties.MODEL, sideTall).with(VariantProperties.UV_LOCK, true).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
    }
}
