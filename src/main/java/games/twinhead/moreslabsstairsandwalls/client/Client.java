package games.twinhead.moreslabsstairsandwalls.client;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client {

    public static void onInitializeClient(final FMLClientSetupEvent event) {
        copyGlassCompatibilityPackIfMissing();
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        event.register((state, world, pos, tint) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0), ModBlocks.GRASS_BLOCK_SLAB.get(), ModBlocks.GRASS_BLOCK_STAIRS.get(), ModBlocks.GRASS_BLOCK_WALL.get());
        event.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()),
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
                ModBlocks.DARK_OAK_LEAVES_WALL.get(),
                ModBlocks.MANGROVE_LEAVES_SLAB.get(),
                ModBlocks.MANGROVE_LEAVES_STAIRS.get(),
                ModBlocks.MANGROVE_LEAVES_WALL.get()
                );
        event.register((state, world, pos, tint) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAVES_SLAB.get(), ModBlocks.SPRUCE_LEAVES_STAIRS.get(), ModBlocks.SPRUCE_LEAVES_WALL.get());
        event.register((state, world, pos, tint) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAVES_SLAB.get(), ModBlocks.BIRCH_LEAVES_STAIRS.get(), ModBlocks.BIRCH_LEAVES_WALL.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        event.register((stack, tint) -> GrassColors.getColor(0.5, 1.0), ModBlocks.GRASS_BLOCK_SLAB.get(), ModBlocks.GRASS_BLOCK_STAIRS.get(), ModBlocks.GRASS_BLOCK_WALL.get());

        event.register(((stack, tint) -> FoliageColors.getDefaultColor()),
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
                ModBlocks.DARK_OAK_LEAVES_WALL.get(),
                ModBlocks.MANGROVE_LEAVES_SLAB.get(),
                ModBlocks.MANGROVE_LEAVES_STAIRS.get(),
                ModBlocks.MANGROVE_LEAVES_WALL.get()
        );

        event.register((stack, tint) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAVES_SLAB.get(), ModBlocks.SPRUCE_LEAVES_STAIRS.get(), ModBlocks.SPRUCE_LEAVES_WALL.get());
        event.register((stack, tint) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAVES_SLAB.get(), ModBlocks.BIRCH_LEAVES_STAIRS.get(), ModBlocks.BIRCH_LEAVES_WALL.get());
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
