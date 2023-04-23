package games.twinhead.moreslabsstairsandwalls.forge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

@Mod(MoreSlabsStairsAndWalls.MOD_ID)
public class MoreSlabsStairsAndWallsForge {
    public MoreSlabsStairsAndWallsForge() {
        MoreSlabsStairsAndWalls.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerTab);
    }


    @SubscribeEvent
    public void registerTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "creative_tab"), builder ->
                // Set name of tab to display
                builder.displayName(Text.translatable("itemGroup." + MoreSlabsStairsAndWalls.MOD_ID + ".creative_tab"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)))
                        // Add default items to tab
                        .entries((enabledFlags, populator) -> {
                            RegistryImpl.BLOCKS.getEntries().stream().map(RegistryObject::get)
                                    .forEach(block -> {
                                        populator.add(block.asItem());
                                    });
                        }));
    }
}
