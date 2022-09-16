package games.twinhead;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.Map;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
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
