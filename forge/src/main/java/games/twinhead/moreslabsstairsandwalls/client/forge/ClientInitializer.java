package games.twinhead.moreslabsstairsandwalls.client.forge;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInitializer {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if (getBlockColor(block) != null){
                    event.register(getBlockColor(block), block.getBlock(type));
                }
            }
        }
    }

    public static BlockColorProvider getBlockColor(ModBlocks block){
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

    public static ItemColorProvider getItemColor(ModBlocks block){
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

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        for (ModBlocks block: ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if (getItemColor(block) != null){
                    event.register(getItemColor(block), block.getBlock(type));
                }
            }
        }
    }
}
