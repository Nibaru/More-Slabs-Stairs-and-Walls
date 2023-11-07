package games.twinhead.moreslabsstairsandwalls.client;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;


public class MoreSlabsStairsAndWallsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        initBlockColorsLayers();
        initRenderLayers();
    }

    private void initRenderLayers(){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                switch (block.modelType){
                    case LEAVES -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderLayer.getCutoutMipped());
                    case GRASS -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderLayer.getCutout());
                    case GLASS, SLIME -> BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderLayer.getTranslucent());
                }
                if(block == ModBlocks.HONEY_BLOCK
                        || block == ModBlocks.ICE) BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(type), RenderLayer.getTranslucent());
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

    public BlockColorProvider getBlockColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> {
                yield switch (block) {
                    case    OAK_LEAVES,
                            JUNGLE_LEAVES,
                            ACACIA_LEAVES,
                            DARK_OAK_LEAVES,
                            MANGROVE_LEAVES -> (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
                    case SPRUCE_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getSpruceColor();
                    case BIRCH_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getBirchColor();
                    default -> null;
                };
            }
            default -> null;
        };
    }

    public ItemColorProvider getItemColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (stack, tintIndex) -> GrassColors.getColor(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> {
                yield switch (block) {
                    case    OAK_LEAVES,
                            JUNGLE_LEAVES,
                            ACACIA_LEAVES,
                            DARK_OAK_LEAVES,
                            MANGROVE_LEAVES -> (stack, tintIndex) -> FoliageColors.getDefaultColor();
                    case SPRUCE_LEAVES -> (stack, tintIndex) -> FoliageColors.getSpruceColor();
                    case BIRCH_LEAVES -> (stack, tintIndex) -> FoliageColors.getBirchColor();
                    default -> null;
                };
            }
            default -> null;
        };
    }
}
