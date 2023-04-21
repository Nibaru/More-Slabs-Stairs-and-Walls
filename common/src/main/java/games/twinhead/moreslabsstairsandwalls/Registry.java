package games.twinhead.moreslabsstairsandwalls;

import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.List;

public class Registry {

    public static final List<ModBlocks> GLASS = List.of(ModBlocks.GLASS, ModBlocks.BLACK_STAINED_GLASS, ModBlocks.BLUE_STAINED_GLASS, ModBlocks.BROWN_STAINED_GLASS, ModBlocks.CYAN_STAINED_GLASS, ModBlocks.GRAY_STAINED_GLASS, ModBlocks.GREEN_STAINED_GLASS, ModBlocks.LIGHT_BLUE_STAINED_GLASS, ModBlocks.LIGHT_GRAY_STAINED_GLASS, ModBlocks.LIME_STAINED_GLASS, ModBlocks.MAGENTA_STAINED_GLASS, ModBlocks.ORANGE_STAINED_GLASS, ModBlocks.PINK_STAINED_GLASS, ModBlocks.PURPLE_STAINED_GLASS, ModBlocks.RED_STAINED_GLASS, ModBlocks.WHITE_STAINED_GLASS, ModBlocks.YELLOW_STAINED_GLASS, ModBlocks.ICE);

    @ExpectPlatform
    public static Block getBlock(Identifier id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBlocks(){
        throw new AssertionError();
    }

}
