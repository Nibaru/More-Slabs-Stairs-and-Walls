package games.twinhead.moreslabsstairsandwalls.client;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Client {

    @SubscribeEvent
    public static void onInitializeClient(final FMLClientSetupEvent event) {
        copyGlassCompatibilityPackIfMissing();
        initRenderLayers();
    }


    public static void initRenderLayers() {
        copyGlassCompatibilityPackIfMissing();

        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .forEach(block -> {
                    if(block.getTranslationKey().contains("grass")){
                        RenderLayers.setRenderLayer(block, RenderLayer.getCutout());
                    } else if (block.getTranslationKey().contains("leaves")) {
                        RenderLayers.setRenderLayer(block, RenderLayer.getCutoutMipped());
                    } else if (block.getTranslationKey().contains("glass")
                            || block.getTranslationKey().contains("honey")
                            || block.getTranslationKey().contains("slime")) {
                        RenderLayers.setRenderLayer(block, RenderLayer.getTranslucent());
                    }
                });
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event){
        event.getBlockColors().registerColorProvider((state, world, pos, tint) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0), ModBlocks.GRASS_BLOCK_SLAB.get(), ModBlocks.GRASS_BLOCK_STAIRS.get(), ModBlocks.GRASS_BLOCK_WALL.get());
        event.getBlockColors().registerColorProvider(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()),
                ModBlocks.OAK_LEAVES_SLAB.get(),
                ModBlocks.OAK_LEAVES_STAIRS.get(),
                ModBlocks.OAK_LEAVES_WALL.get(),
                ModBlocks.JUNGLE_LEAVES_SLAB.get(),
                ModBlocks.JUNGLE_LEAVES_STAIRS.get(),
                ModBlocks.JUNGLE_LEAVES_WALL.get(),
                ModBlocks.ACACIA_LEAVES_SLAB.get(),
                ModBlocks.ACACIA_LEAVES_STAIRS.get(),
                ModBlocks.ACACIA_LEAVES_WALL.get(),
                ModBlocks.DARK_OAK_LEAVES_SLAB.get(),
                ModBlocks.DARK_OAK_LEAVES_STAIRS.get(),
                ModBlocks.DARK_OAK_LEAVES_WALL.get()
                );
        event.getBlockColors().registerColorProvider((state, world, pos, tint) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAVES_SLAB.get(), ModBlocks.SPRUCE_LEAVES_STAIRS.get(), ModBlocks.SPRUCE_LEAVES_WALL.get());
        event.getBlockColors().registerColorProvider((state, world, pos, tint) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAVES_SLAB.get(), ModBlocks.BIRCH_LEAVES_STAIRS.get(), ModBlocks.BIRCH_LEAVES_WALL.get());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event){
        event.getItemColors().register((stack, tint) -> GrassColors.getColor(0.5, 1.0), ModBlocks.GRASS_BLOCK_SLAB.get(), ModBlocks.GRASS_BLOCK_STAIRS.get(), ModBlocks.GRASS_BLOCK_WALL.get());

        event.getItemColors().register(((stack, tint) -> FoliageColors.getDefaultColor()),
                ModBlocks.OAK_LEAVES_SLAB.get(),
                ModBlocks.OAK_LEAVES_STAIRS.get(),
                ModBlocks.OAK_LEAVES_WALL.get(),
                ModBlocks.JUNGLE_LEAVES_SLAB.get(),
                ModBlocks.JUNGLE_LEAVES_STAIRS.get(),
                ModBlocks.JUNGLE_LEAVES_WALL.get(),
                ModBlocks.ACACIA_LEAVES_SLAB.get(),
                ModBlocks.ACACIA_LEAVES_STAIRS.get(),
                ModBlocks.ACACIA_LEAVES_WALL.get(),
                ModBlocks.DARK_OAK_LEAVES_SLAB.get(),
                ModBlocks.DARK_OAK_LEAVES_STAIRS.get(),
                ModBlocks.DARK_OAK_LEAVES_WALL.get()
        );

        event.getItemColors().register((stack, tint) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAVES_SLAB.get(), ModBlocks.SPRUCE_LEAVES_STAIRS.get(), ModBlocks.SPRUCE_LEAVES_WALL.get());
        event.getItemColors().register((stack, tint) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAVES_SLAB.get(), ModBlocks.BIRCH_LEAVES_STAIRS.get(), ModBlocks.BIRCH_LEAVES_WALL.get());
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
