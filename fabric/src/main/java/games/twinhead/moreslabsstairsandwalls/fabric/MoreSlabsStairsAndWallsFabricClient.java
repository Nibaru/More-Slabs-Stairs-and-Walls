package games.twinhead.moreslabsstairsandwalls.fabric;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.fabric.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;


public class MoreSlabsStairsAndWallsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        initBlockColorsLayers();
        initRenderLayers();

        EntityRendererRegistry.register(ModRegistry.FALLING_SLAB_BLOCK_ENTITY, FallingBlockRenderer::new);
    }

    private void initRenderLayers(){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                switch (block.modelType){
                    case LEAVES -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderType.cutoutMipped());
                    case GRASS, ROOTS -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderType.cutout());
                    case GLASS, SLIME -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), block.equals(ModBlocks.GLASS) ? RenderType.cutout() : RenderType.translucent());
                }
                if(block == ModBlocks.HONEY_BLOCK
                        || block == ModBlocks.ICE) BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderType.translucent());
            }
        }
    }

    private void initBlockColorsLayers(){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if (getBlockColor(block) != null && getItemColor(block) != null){
                    ColorProviderRegistry.BLOCK.register(getBlockColor(block), block.getBlock(type));
                    ColorProviderRegistry.ITEM.register(getItemColor(block), block.getBlock(type).asItem());
                }
            }
        }
    }

    public BlockColor getBlockColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> switch (block) {
                case    OAK_LEAVES,
                        JUNGLE_LEAVES,
                        ACACIA_LEAVES,
                        DARK_OAK_LEAVES,
                        MANGROVE_LEAVES -> (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
                case SPRUCE_LEAVES -> (state, world, pos, tintIndex) -> FoliageColor.getEvergreenColor();
                case BIRCH_LEAVES -> (state, world, pos, tintIndex) -> FoliageColor.getBirchColor();
                default -> null;
            };
            default -> null;
        };
    }

    public ItemColor getItemColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (stack, tintIndex) -> GrassColor.get(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> switch (block) {
                case    OAK_LEAVES,
                        JUNGLE_LEAVES,
                        ACACIA_LEAVES,
                        DARK_OAK_LEAVES,
                        MANGROVE_LEAVES -> (stack, tintIndex) -> FoliageColor.getDefaultColor();
                case SPRUCE_LEAVES -> (stack, tintIndex) -> FoliageColor.getEvergreenColor();
                case BIRCH_LEAVES -> (stack, tintIndex) -> FoliageColor.getBirchColor();
                default -> null;
            };
            default -> null;
        };
    }
}
