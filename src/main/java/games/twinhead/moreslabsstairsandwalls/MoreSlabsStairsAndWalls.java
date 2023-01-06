package games.twinhead.moreslabsstairsandwalls;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.client.Client;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod(MoreSlabsStairsAndWalls.MOD_ID)
@Mod.EventBusSubscriber
public class MoreSlabsStairsAndWalls {

    public static final String MOD_ID ="more_slabs_stairs_and_walls";


//    public static ItemGroup modGroup  = FabricItemGroup.builder(
//            new Identifier(MOD_ID, "creative_tab"))
//            .icon(()-> new ItemStack(ModBlocks.GRASS_BLOCK.getStairsBlock()))
//            .build();

    public static final ItemGroup ITEM_GROUP = new ItemGroup("creative_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.GRASS_BLOCK_STAIRS.get());
        }
    };

    public MoreSlabsStairsAndWalls() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(MOD_BUS);

        MOD_BUS.addListener(this::register);
        MOD_BUS.addListener(Client::onInitializeClient);
        MOD_BUS.addListener(Client::registerBlockColors);
        MOD_BUS.addListener(Client::registerItemColors);
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {

        event.register(ForgeRegistries.Keys.ITEMS,
                helper -> {
                    ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                            .forEach(block -> {
                                Item.Settings properties = new Item.Settings().group(ITEM_GROUP);
                                BlockItem blockItem = new BlockItem(block, properties);
                                String[] nameParts = block.getTranslationKey().toLowerCase().split("\\.");
                                helper.register(new Identifier(MOD_ID, nameParts[nameParts.length-1]),blockItem);
                            });
                }
        );
    }



}
