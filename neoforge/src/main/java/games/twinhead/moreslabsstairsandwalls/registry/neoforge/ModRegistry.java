package games.twinhead.moreslabsstairsandwalls.registry.neoforge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;

import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.*;


public class ModRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MoreSlabsStairsAndWalls.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<FallingSlabBlockEntity>> FALLING_SLAB_BLOCK_ENTITY = ENTITIES.register("falling_slab", () -> EntityType.Builder.create(FallingSlabBlockEntity::new, SpawnGroup.MISC).setDimensions(0.98f, 0.98f).setTrackingRange(10).trackingTickInterval(20).build("falling_slab"));

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
                            BlockItem blockItem = new BlockItem(modBlock.getBlock(type), new Item.Settings());
                            helper.register(modBlock.getId(type), blockItem);
                        }
                    }
                }
            });
    }
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
