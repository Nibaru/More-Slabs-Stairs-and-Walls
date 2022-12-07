package games.twinhead.moreslabsstairsandwalls.client;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        initBlockColors();
        initRenderLayers();
        copyGlassCompatibilityPackIfMissing();
    }

    private void initRenderLayers(){
        for (ModBlocks block: ModBlocks.values()) {

            RenderLayer layer = switch (block.getRenderLayer()){
                case CUTOUT -> RenderLayer.getCutout();
                case TRANSLUCENT -> RenderLayer.getTranslucent();
                case CUTOUT_MIPPED -> RenderLayer.getCutoutMipped();
                case SOLID, NONE -> RenderLayer.getSolid();
            };

            if(block.hasSlab())
                BlockRenderLayerMap.INSTANCE.putBlock(block.getSlabBlock(), layer);
            if(block.hasStairs())
                BlockRenderLayerMap.INSTANCE.putBlock(block.getStairsBlock(), layer);
            if(block.hasWall())
                BlockRenderLayerMap.INSTANCE.putBlock(block.getWallBlock(), layer);
        }
    }

    private void initBlockColors(){
        for (ModBlocks block: ModBlocks.values()) {
            BlockColorProvider blockColor = switch (block){
                case
                        OAK_LEAVES,
                                JUNGLE_LEAVES,
                                ACACIA_LEAVES,
                                DARK_OAK_LEAVES,
                                MANGROVE_LEAVES
                        -> ((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor());

                case SPRUCE_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getSpruceColor();
                case BIRCH_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getBirchColor();
                case GRASS_BLOCK -> (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
                default -> null;
            };

            ItemColorProvider itemColor = switch (block){
                case
                        OAK_LEAVES,
                                JUNGLE_LEAVES,
                                ACACIA_LEAVES,
                                DARK_OAK_LEAVES
                        -> ((stack, tintIndex) -> FoliageColors.getDefaultColor());

                case SPRUCE_LEAVES -> (stack, tintIndex) -> FoliageColors.getSpruceColor();
                case BIRCH_LEAVES -> (stack, tintIndex) -> FoliageColors.getBirchColor();
                case MANGROVE_LEAVES -> (stack, tintIndex) -> FoliageColors.getMangroveColor();
                case GRASS_BLOCK -> (stack, tintIndex) -> GrassColors.getColor(0.5, 1.0);
                default -> null;
            };

            if(blockColor != null){
                if(block.hasSlab()) registerBlockColor(blockColor, itemColor, block.getSlabBlock());
                if(block.hasStairs()) registerBlockColor(blockColor, itemColor, block.getStairsBlock());
                if(block.hasWall()) registerBlockColor(blockColor, itemColor, block.getWallBlock());
            }
        }
    }

    public void registerBlockColor(BlockColorProvider blockColor, ItemColorProvider itemColor, Block block){
        ColorProviderRegistry.BLOCK.register(blockColor, block);
        ColorProviderRegistry.ITEM.register(itemColor, block.asItem());
    }

    private static void copyGlassCompatibilityPackIfMissing() {
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "Glass Resource Pack Fix.zip");

        if(!target.exists())
            try {
                dir.mkdirs();
                InputStream in = MoreSlabsStairsAndWalls.class.getResourceAsStream("/assets/more_slabs_stairs_and_walls/Glass Resource Pack Fix.zip");
                FileOutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[16384];
                int len;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
