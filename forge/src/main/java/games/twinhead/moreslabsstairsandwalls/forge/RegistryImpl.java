package games.twinhead.moreslabsstairsandwalls.forge;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class RegistryImpl {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreSlabsStairsAndWalls.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreSlabsStairsAndWalls.MOD_ID);

    public static final HashMap<Identifier, RegistryObject<Block>> MOD_BLOCKS = new HashMap<>();
    public static final HashMap<Identifier, RegistryObject<Item>> MOD_ITEMS = new HashMap<>();

    public static Block getBlock(Identifier id) {
        return MOD_BLOCKS.get(id).get();
    }

    public static final ItemGroup modGroup = new ItemGroup(MoreSlabsStairsAndWalls.MOD_ID + ".creative_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS));
        }
    };


    public static void registerBlocks(){
        for (ModBlocks modBlock : ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(modBlock.hasBlock(type)) addBlock(modBlock, type);
            }
        }
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void addBlock(ModBlocks block, ModBlocks.BlockType type) {
        RegistryObject<Block> registryBlock = BLOCKS.register(block.getId(type).getPath(), () -> {
            Class<?> c = block.getBlockClass(type);
            Constructor<?> cons = null;

            Block b = null;
                try {
                    b = switch (type) {
                        case SLAB -> {
                            if (games.twinhead.moreslabsstairsandwalls.Registry.GLASS.contains(block)) {
                                cons = c.getConstructor(ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block, block.getSettings());
                            }else if (block.oxidationLevel != null) {
                                cons = c.getConstructor(Oxidizable.OxidationLevel.class, ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.oxidationLevel, block.accociatedBlock, block.getSettings().ticksRandomly());
                            } else if (block.accociatedBlock != null) {
                                cons = c.getConstructor(ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.accociatedBlock, block.getSettings());
                            } else {
                                cons = c.getConstructor(AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.getSettings());
                            }
                        }
                        case STAIRS -> {
                            if (games.twinhead.moreslabsstairsandwalls.Registry.GLASS.contains(block)) {
                                cons = c.getConstructor(BlockState.class, ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.parentBlock.getDefaultState(), block, block.getSettings());
                            }else if (block.oxidationLevel != null) {
                                cons = c.getConstructor(BlockState.class, Oxidizable.OxidationLevel.class, ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.parentBlock.getDefaultState(), block.oxidationLevel, block.accociatedBlock, block.getSettings().ticksRandomly());
                            } else if (block.accociatedBlock != null) {
                                cons = c.getConstructor(BlockState.class, ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.parentBlock.getDefaultState(), block.accociatedBlock, block.getSettings());
                            } else {
                                cons = c.getConstructor(BlockState.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.parentBlock.getDefaultState(), block.getSettings());
                            }
                        }
                        case WALL -> {
                            if (games.twinhead.moreslabsstairsandwalls.Registry.GLASS.contains(block) && block.getBlockClass(type) != BaseWall.class) {
                                cons = c.getConstructor(ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block, block.getSettings());
                            }else if (block.oxidationLevel != null && block.getBlockClass(type) != BaseWall.class) {
                                cons = c.getConstructor(Oxidizable.OxidationLevel.class, ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.oxidationLevel, block.accociatedBlock, block.getSettings().ticksRandomly());
                            } else if (block.accociatedBlock != null && block.getBlockClass(type) != BaseWall.class) {
                                cons = c.getConstructor(ModBlocks.class, AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.accociatedBlock, block.getSettings());
                            } else {
                                cons = c.getConstructor(AbstractBlock.Settings.class);
                                yield (Block) cons.newInstance(block.getSettings());
                            }
                        }

                    };
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    System.out.println("Error while registering block: " + block.getId(type));
                    throw new RuntimeException(e);
                }
            return b;
        });


        MOD_BLOCKS.put(block.getId(type), registryBlock);
        registerItem(block.getId(type), registryBlock);
    }

    public static void registerItem(Identifier id, RegistryObject<Block> block){
        MOD_ITEMS.put(id, ITEMS.register(id.getPath(), () -> new BlockItem(block.get(), new Item.Settings().group(modGroup))));
        //Item item = net.minecraft.util.registry.Registry.register(Registry.ITEM, id, new BlockItem(block.get(), new Item.Settings().group(modGroup)));
        //ItemGroupEvents.modifyEntriesEvent(MoreSlabsStairsAndWalls.modGroup).register(entries -> entries.add(item));
    }
}
