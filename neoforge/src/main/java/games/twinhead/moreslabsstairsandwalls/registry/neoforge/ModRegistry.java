package games.twinhead.moreslabsstairsandwalls.registry.neoforge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;

import games.twinhead.moreslabsstairsandwalls.block.MoreBlockItem;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.*;

@SuppressWarnings("unused")
public class ModRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MoreSlabsStairsAndWalls.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<FallingSlabBlockEntity>> FALLING_SLAB_BLOCK_ENTITY = ENTITIES.register("falling_slab", () -> EntityType.Builder.of(FallingSlabBlockEntity::new, MobCategory.MISC).sized(0.98f, 0.98f).setTrackingRange(10).updateInterval(20).build("falling_slab"));

    public ModRegistry() {}

    @SubscribeEvent
    public void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(FALLING_SLAB_BLOCK_ENTITY.get(), FallingBlockRenderer::new);
    }

    @SubscribeEvent
    public void register(RegisterEvent event)
    {
        event.register(Registries.BLOCK,
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
        event.register(Registries.ITEM,
            helper ->
            {
                for (ModBlocks modBlock : ModBlocks.values())
                {
                    for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                    {
                        if (modBlock.hasBlock(type))
                        {
                            BlockItem blockItem = new MoreBlockItem(modBlock, type, new Item.Properties());
                            helper.register(modBlock.getId(type), blockItem);
                        }
                    }
                }
            });
    }

    public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MoreSlabsStairsAndWalls.MOD_ID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> CREATIVE_TAB = ITEM_GROUPS.register("creative_tab", () -> CreativeModeTab.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .title(Component.translatable("itemGroup." + MoreSlabsStairsAndWalls.MOD_ID + ".creative_tab"))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)))
            //Add your items to the tab.
            .displayItems((params, output) -> {
                for (ModBlocks block: ModBlocks.values())
                    for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                        if (block.hasBlock(type)) output.accept(block.getBlock(type));
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
