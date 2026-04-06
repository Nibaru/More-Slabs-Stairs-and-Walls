package games.twinhead.moreslabsstairsandwalls.registry.fabric;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class ModRegistry {

    public static final EntityType<FallingSlabBlockEntity> FALLING_SLAB_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MoreSlabsStairsAndWalls.MOD_ID, "falling_slab"),
            FabricEntityTypeBuilder.create(MobCategory.MISC, FallingSlabBlockEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).trackRangeBlocks(10).trackedUpdateRate(20).build());

    public static Block getBlock(ResourceLocation id) {
        return BuiltInRegistries.BLOCK.get(id);
    }

    public static CreativeModeTab modGroup = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MoreSlabsStairsAndWalls.MOD_ID, "creative_tab"),
                   FabricItemGroup.builder()
                           .icon(() -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)))
            .title(Component.translatable("itemGroup.more_slabs_stairs_and_walls.creative_tab"))
            .displayItems((displayContext, entries) -> {
        for (ModBlocks block: ModBlocks.values())
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                if (block.hasBlock(type)) entries.accept(block.getBlock(type));})
                                   .build());


    public static void registerBlocks(){
        for (ModBlocks modBlock : ModBlocks.values())
        {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
            {
                if (modBlock.hasBlock(type))
                {
                    Block block = games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlock(modBlock, type);
                    Registry.register(BuiltInRegistries.BLOCK, modBlock.getId(type), block);
                    registerItem(modBlock.getId(type), block);

                    if (modBlock.parentBlock.defaultBlockState().ignitedByLava()){
                        FlammableBlockRegistry.getDefaultInstance().add(block, games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBurnChance(modBlock), games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getSpreadChance(modBlock));
                    }

                    if (FuelRegistry.INSTANCE.get(modBlock.parentBlock) != null && FuelRegistry.INSTANCE.get(modBlock.parentBlock) > 0){
                        FuelRegistry.INSTANCE.add(block, type == ModBlocks.BlockType.SLAB ? FuelRegistry.INSTANCE.get(modBlock.parentBlock) / 2 : FuelRegistry.INSTANCE.get(modBlock.parentBlock));
                    } else {
                        if (fuelLogBlocks.contains(modBlock) || planks.contains(modBlock)){
                            if (modBlock != ModBlocks.CRIMSON_PLANKS && modBlock != ModBlocks.WARPED_PLANKS)
                                FuelRegistry.INSTANCE.add(block, type == ModBlocks.BlockType.SLAB ? 300 / 2 : 300);
                        }
                        if (wool.contains(modBlock)){
                            FuelRegistry.INSTANCE.add(block, type == ModBlocks.BlockType.SLAB ? 100 / 2 : 100);
                        }
                    }
                }
            }
        }
    }

    public static void registerItem(ResourceLocation id, Block block){
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
    }

    public static final List<ModBlocks> fuelLogBlocks = List.of(
            ModBlocks.ACACIA_LOG,
            ModBlocks.BIRCH_LOG,
            ModBlocks.DARK_OAK_LOG,
            ModBlocks.JUNGLE_LOG,
            ModBlocks.OAK_LOG,
            ModBlocks.SPRUCE_LOG,
            ModBlocks.MANGROVE_LOG,
            ModBlocks.CHERRY_LOG,
            ModBlocks.BAMBOO_BLOCK,
            ModBlocks.STRIPPED_ACACIA_LOG,
            ModBlocks.STRIPPED_BIRCH_LOG,
            ModBlocks.STRIPPED_DARK_OAK_LOG,
            ModBlocks.STRIPPED_JUNGLE_LOG,
            ModBlocks.STRIPPED_OAK_LOG,
            ModBlocks.STRIPPED_SPRUCE_LOG,
            ModBlocks.STRIPPED_MANGROVE_LOG,
            ModBlocks.STRIPPED_CHERRY_LOG,
            ModBlocks.STRIPPED_BAMBOO_BLOCK,
            ModBlocks.ACACIA_WOOD,
            ModBlocks.BIRCH_WOOD,
            ModBlocks.DARK_OAK_WOOD,
            ModBlocks.JUNGLE_WOOD,
            ModBlocks.OAK_WOOD,
            ModBlocks.SPRUCE_WOOD,
            ModBlocks.MANGROVE_WOOD,
            ModBlocks.CHERRY_WOOD,
            ModBlocks.STRIPPED_ACACIA_WOOD,
            ModBlocks.STRIPPED_BIRCH_WOOD,
            ModBlocks.STRIPPED_DARK_OAK_WOOD,
            ModBlocks.STRIPPED_JUNGLE_WOOD,
            ModBlocks.STRIPPED_OAK_WOOD,
            ModBlocks.STRIPPED_SPRUCE_WOOD,
            ModBlocks.STRIPPED_MANGROVE_WOOD,
            ModBlocks.STRIPPED_CHERRY_WOOD
    );

    public static final List<ModBlocks> wool = List.of(
            ModBlocks.WHITE_WOOL,
            ModBlocks.ORANGE_WOOL,
            ModBlocks.MAGENTA_WOOL,
            ModBlocks.LIGHT_BLUE_WOOL,
            ModBlocks.YELLOW_WOOL,
            ModBlocks.LIME_WOOL,
            ModBlocks.PINK_WOOL,
            ModBlocks.GRAY_WOOL,
            ModBlocks.LIGHT_GRAY_WOOL,
            ModBlocks.CYAN_WOOL,
            ModBlocks.PURPLE_WOOL,
            ModBlocks.BLUE_WOOL,
            ModBlocks.BROWN_WOOL,
            ModBlocks.GREEN_WOOL,
            ModBlocks.RED_WOOL,
            ModBlocks.BLACK_WOOL
    );

    public static final List<ModBlocks> planks = List.of(ModBlocks.ACACIA_PLANKS,
            ModBlocks.BIRCH_PLANKS,
            ModBlocks.CRIMSON_PLANKS,
            ModBlocks.DARK_OAK_PLANKS,
            ModBlocks.JUNGLE_PLANKS,
            ModBlocks.OAK_PLANKS,
            ModBlocks.SPRUCE_PLANKS,
            ModBlocks.WARPED_PLANKS,
            ModBlocks.MANGROVE_PLANKS,
            ModBlocks.CHERRY_PLANKS,
            ModBlocks.BAMBOO_PLANKS);
}
