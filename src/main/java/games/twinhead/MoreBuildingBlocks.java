package games.twinhead;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.Map;

public class MoreBuildingBlocks implements ModInitializer {
    public static final String mod_id="more_slabs_stairs_and_walls";

    @Override
    public void onInitialize() {
        BlockRegistry.RegisterAllBlocks();

        for (Map.Entry<String, Block> entry : BlockRegistry.blocks.entrySet()) {
            if (entry.getKey().contains("glass") || !entry.getKey().contains("stained")) {
                BlockRenderLayerMap.INSTANCE.putBlock(entry.getValue(), RenderLayer.getCutoutMipped());
            }
            if (entry.getKey().contains("stained_glass")) {
                BlockRenderLayerMap.INSTANCE.putBlock(entry.getValue(), RenderLayer.getTranslucent());
            }
        }
    }
}
