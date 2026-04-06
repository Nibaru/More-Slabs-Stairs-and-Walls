package games.twinhead.moreslabsstairsandwalls.fabric;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.fabric.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;

public class MoreSlabsStairsAndWallsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        initBlockColorsLayers();
        initRenderLayers();

        EntityRendererRegistry.register(ModRegistry.FALLING_SLAB_BLOCK_ENTITY, FallingBlockRenderer::new);
    }

    private void initRenderLayers() {
        for (ModBlocks block : ModBlocks.values()) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (block.hasBlock(type)) {
                    BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), block.modelType == ModBlocks.ModelType.LEAVES ? RenderType.cutoutMipped() : ItemBlockRenderTypes.getChunkRenderType(block.parentBlock.defaultBlockState()));
                }
            }
        }
    }

    private void initBlockColorsLayers() {
        for (ModBlocks block : ModBlocks.values()) {
            if (!needsParentTint(block)) continue;
            Block parent = block.parentBlock;
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (!block.hasBlock(type)) continue;
                Block self = block.getBlock(type);
                ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
                    var mc = Minecraft.getInstance();
                    var parentState = parent.defaultBlockState();
                    return mc.getBlockColors().getColor(parentState, world, pos, tintIndex);
                }, self);
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Minecraft.getInstance()
                        .getBlockColors()
                        .getColor(parent.defaultBlockState(), null, null, tintIndex), self.asItem());
            }
        }
    }
    private static boolean needsParentTint(ModBlocks block) {
        return switch (block.modelType) {
            case LEAVES, GRASS, ROOTS -> true;
            default -> false;
        };
    }
}
