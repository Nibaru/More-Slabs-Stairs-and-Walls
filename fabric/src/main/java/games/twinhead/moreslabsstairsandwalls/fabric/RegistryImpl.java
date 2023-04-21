package games.twinhead.moreslabsstairsandwalls.fabric;


import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class RegistryImpl {

    public static ItemGroup modGroup  = FabricItemGroupBuilder.build(
            new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "creative_tab"),
            () -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)));

    public static final HashMap<Identifier, Block> MOD_BLOCKS = new HashMap<>();

    public static Block getBlock(Identifier id) {
        return MOD_BLOCKS.get(id);
    }

    public static void registerBlocks(){
           for (ModBlocks modBlock : ModBlocks.values()) {
               for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                   if(modBlock.hasBlock(type)) addBlock(modBlock, type);
               }
        }
    }

    public static void addBlock(ModBlocks block, ModBlocks.BlockType type) {
        Class<?> c = block.getBlockClass(type);
        Constructor<?> cons;
        Block b;

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
                    }else if (block.oxidationLevel != null) {
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
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Error while registering block: " + block.getId(type));
            throw new RuntimeException(e);
        }

        MOD_BLOCKS.put(block.getId(type), Registry.register(Registry.BLOCK, block.getId(type), b));
        registerItem(block.getId(type), b);
    }

    public static void registerItem(Identifier id, Block block){
        Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(modGroup)));
    }
}
