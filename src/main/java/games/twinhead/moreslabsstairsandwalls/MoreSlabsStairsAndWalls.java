package games.twinhead.moreslabsstairsandwalls;

import games.twinhead.moreslabsstairsandwalls.registry.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

@Mod(MoreSlabsStairsAndWalls.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreSlabsStairsAndWalls {

    public static final String MOD_ID ="more_slabs_stairs_and_walls";

    public static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID +".creative_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.GRASS_BLOCK_STAIRS.get());
        }
    };

    public MoreSlabsStairsAndWalls() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(MOD_BUS);

        //MOD_BUS.addGenericListener(RegistryEvents::register);
        MinecraftForge.EVENT_BUS.register(RegistryEvents.class);

    }


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void register(RegistryEvent.Register<Item> event) {
            ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                    .forEach(block -> {
                        Item.Settings properties = new Item.Settings().group(ITEM_GROUP);
                        BlockItem blockItem = new BlockItem(block, properties);
                        String[] nameParts = block.getTranslationKey().toLowerCase().split("\\.");
                        event.getRegistry().register(blockItem.setRegistryName(new Identifier(MOD_ID, nameParts[nameParts.length - 1])));
                    });
        }
    }
}
