package games.twinhead.moreslabsstairsandwalls.forge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MoreSlabsStairsAndWalls.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MoreSlabsStairsAndWallsForgeClient {

    @SubscribeEvent
    public void registerBlockColors(RegisterColorHandlersEvent.Block event){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlockColor(block) != null){
                    event.register(games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlockColor(block), block.getBlock(type));
                }
            }
        }
    }

    @SubscribeEvent
    public void registerItemColors(RegisterColorHandlersEvent.Item event){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values()) {
                if (games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlockColor(block) != null){
                    event.register(games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getItemColor(block), block.getBlock(type));
                }
            }
        }
    }
}
