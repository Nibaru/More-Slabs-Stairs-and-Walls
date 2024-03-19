package games.twinhead.moreslabsstairsandwalls.registry.forge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;

import games.twinhead.moreslabsstairsandwalls.block.MoreBlockItem;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(RegistryKeys.ENTITY_TYPE, MoreSlabsStairsAndWalls.MOD_ID);

    public static final RegistryObject<EntityType<FallingSlabBlockEntity>> FALLING_SLAB_BLOCK_ENTITY = ENTITIES.register("falling_slab", () -> EntityType.Builder.create(FallingSlabBlockEntity::new, SpawnGroup.MISC).setDimensions(0.98f, 0.98f).setTrackingRange(10).trackingTickInterval(20).build("falling_slab"));

    public ModRegistry() {}

    @SubscribeEvent
    public void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(FALLING_SLAB_BLOCK_ENTITY.get(), FallingBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void register(RegisterEvent event)
    {
        event.register(RegistryKeys.BLOCK,
                helper ->
                {
                    for (ModBlocks modBlock : ModBlocks.values())
                    {
                        for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                        {
                            if (modBlock.hasBlock(type))
                            {
                                helper.register(MoreSlabsStairsAndWalls.id(modBlock.name().toLowerCase() + "_" + type.name().toLowerCase()), games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlock(modBlock, type));
                            }
                        }
                    }
                }
        );
    }

    @SubscribeEvent
    public void registerItem(RegisterEvent event) {
        event.register(RegistryKeys.ITEM,
            helper ->
            {
                for (ModBlocks modBlock : ModBlocks.values())
                {
                    for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                    {
                        if (modBlock.hasBlock(type))
                        {
                            BlockItem blockItem = new MoreBlockItem(modBlock, type, new Item.Settings());
                            helper.register(modBlock.getId(type), blockItem);
                        }
                    }
                }
            });
    }

    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(RegistryKeys.ITEM_GROUP, MoreSlabsStairsAndWalls.MOD_ID);

    public static final RegistryObject<ItemGroup> CREATIVE_TAB = ITEM_GROUPS.register("creative_tab", () -> ItemGroup.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .displayName(Text.translatable("itemGroup." + MoreSlabsStairsAndWalls.MOD_ID + ".creative_tab"))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)))
            //Add your items to the tab.
            .entries((params, output) -> {
                for (ModBlocks block: ModBlocks.values())
                    for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                        if (block.hasBlock(type)) output.add(block.getBlock(type));
            })
            .build()
    );

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
