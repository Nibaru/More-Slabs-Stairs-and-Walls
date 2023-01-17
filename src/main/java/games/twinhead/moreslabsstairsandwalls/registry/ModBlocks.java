package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.HoneyBlockSlab;
import games.twinhead.moreslabsstairsandwalls.block.HoneyBlockStairs;
import games.twinhead.moreslabsstairsandwalls.block.HoneyBlockWall;
import games.twinhead.moreslabsstairsandwalls.block.OxidizableWallBlock;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralWallBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.dirt.*;
import games.twinhead.moreslabsstairsandwalls.block.log.StrippableSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.log.StrippableStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.log.StrippableWallBlock;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaSlab;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaStairs;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaWall;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeBlockSlab;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeBlockStairs;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeBlockWall;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandSlab;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandStairs;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandWall;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableWallBlock;
import net.minecraft.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreSlabsStairsAndWalls.MOD_ID);

    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB = createSlabBlock(Blocks.WHITE_CONCRETE);
    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB = createSlabBlock(Blocks.YELLOW_CONCRETE);
    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB = createSlabBlock(Blocks.BLACK_CONCRETE);
    public static final RegistryObject<Block> RED_CONCRETE_SLAB = createSlabBlock(Blocks.RED_CONCRETE);
    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB = createSlabBlock(Blocks.PURPLE_CONCRETE);
    public static final RegistryObject<Block> PINK_CONCRETE_SLAB = createSlabBlock(Blocks.PINK_CONCRETE);
    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB = createSlabBlock(Blocks.ORANGE_CONCRETE);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB = createSlabBlock(Blocks.MAGENTA_CONCRETE);
    public static final RegistryObject<Block> LIME_CONCRETE_SLAB = createSlabBlock(Blocks.LIME_CONCRETE);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB = createSlabBlock(Blocks.LIGHT_GRAY_CONCRETE);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB = createSlabBlock(Blocks.LIGHT_BLUE_CONCRETE);
    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB = createSlabBlock(Blocks.GREEN_CONCRETE);
    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB = createSlabBlock(Blocks.GRAY_CONCRETE);
    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB = createSlabBlock(Blocks.CYAN_CONCRETE);
    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB = createSlabBlock(Blocks.BROWN_CONCRETE);
    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB = createSlabBlock(Blocks.BLUE_CONCRETE);

    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS = createStairsBlock(Blocks.WHITE_CONCRETE);
    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS = createStairsBlock(Blocks.YELLOW_CONCRETE);
    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS = createStairsBlock(Blocks.BLACK_CONCRETE);
    public static final RegistryObject<Block> RED_CONCRETE_STAIRS = createStairsBlock(Blocks.RED_CONCRETE);
    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS = createStairsBlock(Blocks.PURPLE_CONCRETE);
    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS = createStairsBlock(Blocks.PINK_CONCRETE);
    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS = createStairsBlock(Blocks.ORANGE_CONCRETE);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS = createStairsBlock(Blocks.MAGENTA_CONCRETE);
    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS = createStairsBlock(Blocks.LIME_CONCRETE);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS = createStairsBlock(Blocks.LIGHT_GRAY_CONCRETE);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS = createStairsBlock(Blocks.LIGHT_BLUE_CONCRETE);
    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS = createStairsBlock(Blocks.GREEN_CONCRETE);
    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS = createStairsBlock(Blocks.GRAY_CONCRETE);
    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS = createStairsBlock(Blocks.CYAN_CONCRETE);
    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS = createStairsBlock(Blocks.BROWN_CONCRETE);
    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS = createStairsBlock(Blocks.BLUE_CONCRETE);

    public static final RegistryObject<Block> WHITE_CONCRETE_WALL = createWallBlock(Blocks.WHITE_CONCRETE);
    public static final RegistryObject<Block> YELLOW_CONCRETE_WALL = createWallBlock(Blocks.YELLOW_CONCRETE);
    public static final RegistryObject<Block> BLACK_CONCRETE_WALL = createWallBlock(Blocks.BLACK_CONCRETE);
    public static final RegistryObject<Block> RED_CONCRETE_WALL = createWallBlock(Blocks.RED_CONCRETE);
    public static final RegistryObject<Block> PURPLE_CONCRETE_WALL = createWallBlock(Blocks.PURPLE_CONCRETE);
    public static final RegistryObject<Block> PINK_CONCRETE_WALL = createWallBlock(Blocks.PINK_CONCRETE);
    public static final RegistryObject<Block> ORANGE_CONCRETE_WALL = createWallBlock(Blocks.ORANGE_CONCRETE);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_WALL = createWallBlock(Blocks.MAGENTA_CONCRETE);
    public static final RegistryObject<Block> LIME_CONCRETE_WALL = createWallBlock(Blocks.LIME_CONCRETE);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_WALL = createWallBlock(Blocks.LIGHT_GRAY_CONCRETE);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_WALL = createWallBlock(Blocks.LIGHT_BLUE_CONCRETE);
    public static final RegistryObject<Block> GREEN_CONCRETE_WALL = createWallBlock(Blocks.GREEN_CONCRETE);
    public static final RegistryObject<Block> GRAY_CONCRETE_WALL = createWallBlock(Blocks.GRAY_CONCRETE);
    public static final RegistryObject<Block> CYAN_CONCRETE_WALL = createWallBlock(Blocks.CYAN_CONCRETE);
    public static final RegistryObject<Block> BROWN_CONCRETE_WALL = createWallBlock(Blocks.BROWN_CONCRETE);
    public static final RegistryObject<Block> BLUE_CONCRETE_WALL = createWallBlock(Blocks.BLUE_CONCRETE);

    public static final RegistryObject<Block> TERRACOTTA_SLAB = createSlabBlock(Blocks.TERRACOTTA);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_SLAB = createSlabBlock(Blocks.WHITE_TERRACOTTA);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_SLAB = createSlabBlock(Blocks.YELLOW_TERRACOTTA);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_SLAB = createSlabBlock(Blocks.BLACK_TERRACOTTA);
    public static final RegistryObject<Block> RED_TERRACOTTA_SLAB = createSlabBlock(Blocks.RED_TERRACOTTA);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_SLAB = createSlabBlock(Blocks.PURPLE_TERRACOTTA);
    public static final RegistryObject<Block> PINK_TERRACOTTA_SLAB = createSlabBlock(Blocks.PINK_TERRACOTTA);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_SLAB = createSlabBlock(Blocks.ORANGE_TERRACOTTA);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_SLAB = createSlabBlock(Blocks.MAGENTA_TERRACOTTA);
    public static final RegistryObject<Block> LIME_TERRACOTTA_SLAB = createSlabBlock(Blocks.LIME_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_SLAB = createSlabBlock(Blocks.LIGHT_GRAY_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_SLAB = createSlabBlock(Blocks.LIGHT_BLUE_TERRACOTTA);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_SLAB = createSlabBlock(Blocks.GREEN_TERRACOTTA);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_SLAB = createSlabBlock(Blocks.GRAY_TERRACOTTA);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_SLAB = createSlabBlock(Blocks.CYAN_TERRACOTTA);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_SLAB = createSlabBlock(Blocks.BROWN_TERRACOTTA);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_SLAB = createSlabBlock(Blocks.BLUE_TERRACOTTA);

    public static final RegistryObject<Block> TERRACOTTA_STAIRS = createStairsBlock(Blocks.TERRACOTTA);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_STAIRS = createStairsBlock(Blocks.WHITE_TERRACOTTA);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_STAIRS = createStairsBlock(Blocks.YELLOW_TERRACOTTA);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_STAIRS = createStairsBlock(Blocks.BLACK_TERRACOTTA);
    public static final RegistryObject<Block> RED_TERRACOTTA_STAIRS = createStairsBlock(Blocks.RED_TERRACOTTA);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_STAIRS = createStairsBlock(Blocks.PURPLE_TERRACOTTA);
    public static final RegistryObject<Block> PINK_TERRACOTTA_STAIRS = createStairsBlock(Blocks.PINK_TERRACOTTA);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_STAIRS = createStairsBlock(Blocks.ORANGE_TERRACOTTA);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_STAIRS = createStairsBlock(Blocks.MAGENTA_TERRACOTTA);
    public static final RegistryObject<Block> LIME_TERRACOTTA_STAIRS = createStairsBlock(Blocks.LIME_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_STAIRS = createStairsBlock(Blocks.LIGHT_GRAY_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_STAIRS = createStairsBlock(Blocks.LIGHT_BLUE_TERRACOTTA);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_STAIRS = createStairsBlock(Blocks.GREEN_TERRACOTTA);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_STAIRS = createStairsBlock(Blocks.GRAY_TERRACOTTA);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_STAIRS = createStairsBlock(Blocks.CYAN_TERRACOTTA);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_STAIRS = createStairsBlock(Blocks.BROWN_TERRACOTTA);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_STAIRS = createStairsBlock(Blocks.BLUE_TERRACOTTA);

    public static final RegistryObject<Block> TERRACOTTA_WALL = createWallBlock(Blocks.TERRACOTTA);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_WALL = createWallBlock(Blocks.WHITE_TERRACOTTA);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_WALL = createWallBlock(Blocks.YELLOW_TERRACOTTA);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_WALL = createWallBlock(Blocks.BLACK_TERRACOTTA);
    public static final RegistryObject<Block> RED_TERRACOTTA_WALL = createWallBlock(Blocks.RED_TERRACOTTA);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_WALL = createWallBlock(Blocks.PURPLE_TERRACOTTA);
    public static final RegistryObject<Block> PINK_TERRACOTTA_WALL = createWallBlock(Blocks.PINK_TERRACOTTA);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_WALL = createWallBlock(Blocks.ORANGE_TERRACOTTA);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_WALL = createWallBlock(Blocks.MAGENTA_TERRACOTTA);
    public static final RegistryObject<Block> LIME_TERRACOTTA_WALL = createWallBlock(Blocks.LIME_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_WALL = createWallBlock(Blocks.LIGHT_GRAY_TERRACOTTA);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_WALL = createWallBlock(Blocks.LIGHT_BLUE_TERRACOTTA);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_WALL = createWallBlock(Blocks.GREEN_TERRACOTTA);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_WALL = createWallBlock(Blocks.GRAY_TERRACOTTA);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_WALL = createWallBlock(Blocks.CYAN_TERRACOTTA);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_WALL = createWallBlock(Blocks.BROWN_TERRACOTTA);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_WALL = createWallBlock(Blocks.BLUE_TERRACOTTA);

    public static final RegistryObject<Block> WHITE_WOOL_SLAB = createSlabBlock(Blocks.WHITE_WOOL);
    public static final RegistryObject<Block> YELLOW_WOOL_SLAB = createSlabBlock(Blocks.YELLOW_WOOL);
    public static final RegistryObject<Block> BLACK_WOOL_SLAB = createSlabBlock(Blocks.BLACK_WOOL);
    public static final RegistryObject<Block> RED_WOOL_SLAB = createSlabBlock(Blocks.RED_WOOL);
    public static final RegistryObject<Block> PURPLE_WOOL_SLAB = createSlabBlock(Blocks.PURPLE_WOOL);
    public static final RegistryObject<Block> PINK_WOOL_SLAB = createSlabBlock(Blocks.PINK_WOOL);
    public static final RegistryObject<Block> ORANGE_WOOL_SLAB = createSlabBlock(Blocks.ORANGE_WOOL);
    public static final RegistryObject<Block> MAGENTA_WOOL_SLAB = createSlabBlock(Blocks.MAGENTA_WOOL);
    public static final RegistryObject<Block> LIME_WOOL_SLAB = createSlabBlock(Blocks.LIME_WOOL);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_SLAB = createSlabBlock(Blocks.LIGHT_GRAY_WOOL);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_SLAB = createSlabBlock(Blocks.LIGHT_BLUE_WOOL);
    public static final RegistryObject<Block> GREEN_WOOL_SLAB = createSlabBlock(Blocks.GREEN_WOOL);
    public static final RegistryObject<Block> GRAY_WOOL_SLAB = createSlabBlock(Blocks.GRAY_WOOL);
    public static final RegistryObject<Block> CYAN_WOOL_SLAB = createSlabBlock(Blocks.CYAN_WOOL);
    public static final RegistryObject<Block> BROWN_WOOL_SLAB = createSlabBlock(Blocks.BROWN_WOOL);
    public static final RegistryObject<Block> BLUE_WOOL_SLAB = createSlabBlock(Blocks.BLUE_WOOL);

    public static final RegistryObject<Block> WHITE_WOOL_STAIRS = createStairsBlock(Blocks.WHITE_WOOL);
    public static final RegistryObject<Block> YELLOW_WOOL_STAIRS = createStairsBlock(Blocks.YELLOW_WOOL);
    public static final RegistryObject<Block> BLACK_WOOL_STAIRS = createStairsBlock(Blocks.BLACK_WOOL);
    public static final RegistryObject<Block> RED_WOOL_STAIRS = createStairsBlock(Blocks.RED_WOOL);
    public static final RegistryObject<Block> PURPLE_WOOL_STAIRS = createStairsBlock(Blocks.PURPLE_WOOL);
    public static final RegistryObject<Block> PINK_WOOL_STAIRS = createStairsBlock(Blocks.PINK_WOOL);
    public static final RegistryObject<Block> ORANGE_WOOL_STAIRS = createStairsBlock(Blocks.ORANGE_WOOL);
    public static final RegistryObject<Block> MAGENTA_WOOL_STAIRS = createStairsBlock(Blocks.MAGENTA_WOOL);
    public static final RegistryObject<Block> LIME_WOOL_STAIRS = createStairsBlock(Blocks.LIME_WOOL);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_STAIRS = createStairsBlock(Blocks.LIGHT_GRAY_WOOL);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_STAIRS = createStairsBlock(Blocks.LIGHT_BLUE_WOOL);
    public static final RegistryObject<Block> GREEN_WOOL_STAIRS = createStairsBlock(Blocks.GREEN_WOOL);
    public static final RegistryObject<Block> GRAY_WOOL_STAIRS = createStairsBlock(Blocks.GRAY_WOOL);
    public static final RegistryObject<Block> CYAN_WOOL_STAIRS = createStairsBlock(Blocks.CYAN_WOOL);
    public static final RegistryObject<Block> BROWN_WOOL_STAIRS = createStairsBlock(Blocks.BROWN_WOOL);
    public static final RegistryObject<Block> BLUE_WOOL_STAIRS = createStairsBlock(Blocks.BLUE_WOOL);

    public static final RegistryObject<Block> WHITE_WOOL_WALL = createWallBlock(Blocks.WHITE_WOOL);
    public static final RegistryObject<Block> YELLOW_WOOL_WALL = createWallBlock(Blocks.YELLOW_WOOL);
    public static final RegistryObject<Block> BLACK_WOOL_WALL = createWallBlock(Blocks.BLACK_WOOL);
    public static final RegistryObject<Block> RED_WOOL_WALL = createWallBlock(Blocks.RED_WOOL);
    public static final RegistryObject<Block> PURPLE_WOOL_WALL = createWallBlock(Blocks.PURPLE_WOOL);
    public static final RegistryObject<Block> PINK_WOOL_WALL = createWallBlock(Blocks.PINK_WOOL);
    public static final RegistryObject<Block> ORANGE_WOOL_WALL = createWallBlock(Blocks.ORANGE_WOOL);
    public static final RegistryObject<Block> MAGENTA_WOOL_WALL = createWallBlock(Blocks.MAGENTA_WOOL);
    public static final RegistryObject<Block> LIME_WOOL_WALL = createWallBlock(Blocks.LIME_WOOL);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_WALL = createWallBlock(Blocks.LIGHT_GRAY_WOOL);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_WALL = createWallBlock(Blocks.LIGHT_BLUE_WOOL);
    public static final RegistryObject<Block> GREEN_WOOL_WALL = createWallBlock(Blocks.GREEN_WOOL);
    public static final RegistryObject<Block> GRAY_WOOL_WALL = createWallBlock(Blocks.GRAY_WOOL);
    public static final RegistryObject<Block> CYAN_WOOL_WALL = createWallBlock(Blocks.CYAN_WOOL);
    public static final RegistryObject<Block> BROWN_WOOL_WALL = createWallBlock(Blocks.BROWN_WOOL);
    public static final RegistryObject<Block> BLUE_WOOL_WALL = createWallBlock(Blocks.BLUE_WOOL);

    public static final RegistryObject<Block> GLASS_SLAB = createCulledSlabBlock(Blocks.GLASS);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.WHITE_STAINED_GLASS);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.YELLOW_STAINED_GLASS);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.BLACK_STAINED_GLASS);
    public static final RegistryObject<Block> RED_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.RED_STAINED_GLASS);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.PURPLE_STAINED_GLASS);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.PINK_STAINED_GLASS);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.ORANGE_STAINED_GLASS);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.MAGENTA_STAINED_GLASS);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.LIME_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.GREEN_STAINED_GLASS);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.CYAN_STAINED_GLASS);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.BROWN_STAINED_GLASS);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_SLAB = createCulledSlabBlock(Blocks.BLUE_STAINED_GLASS);

    public static final RegistryObject<Block> GLASS_STAIRS = createCulledStairsBlock(Blocks.GLASS);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.WHITE_STAINED_GLASS);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.YELLOW_STAINED_GLASS);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.BLACK_STAINED_GLASS);
    public static final RegistryObject<Block> RED_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.RED_STAINED_GLASS);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.PURPLE_STAINED_GLASS);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.PINK_STAINED_GLASS);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.ORANGE_STAINED_GLASS);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.MAGENTA_STAINED_GLASS);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.LIME_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.GREEN_STAINED_GLASS);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.CYAN_STAINED_GLASS);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.BROWN_STAINED_GLASS);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_STAIRS = createCulledStairsBlock(Blocks.BLUE_STAINED_GLASS);

    public static final RegistryObject<Block> GLASS_WALL = createWallBlock(Blocks.GLASS);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_WALL = createWallBlock(Blocks.WHITE_STAINED_GLASS);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_WALL = createWallBlock(Blocks.YELLOW_STAINED_GLASS);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_WALL = createWallBlock(Blocks.BLACK_STAINED_GLASS);
    public static final RegistryObject<Block> RED_STAINED_GLASS_WALL = createWallBlock(Blocks.RED_STAINED_GLASS);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_WALL = createWallBlock(Blocks.PURPLE_STAINED_GLASS);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_WALL = createWallBlock(Blocks.PINK_STAINED_GLASS);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_WALL = createWallBlock(Blocks.ORANGE_STAINED_GLASS);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_WALL = createWallBlock(Blocks.MAGENTA_STAINED_GLASS);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_WALL = createWallBlock(Blocks.LIME_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_WALL = createWallBlock(Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_WALL = createWallBlock(Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_WALL = createWallBlock(Blocks.GREEN_STAINED_GLASS);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_WALL = createWallBlock(Blocks.GRAY_STAINED_GLASS);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_WALL = createWallBlock(Blocks.CYAN_STAINED_GLASS);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_WALL = createWallBlock(Blocks.BROWN_STAINED_GLASS);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_WALL = createWallBlock(Blocks.BLUE_STAINED_GLASS);

    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB = createSlabBlock(Blocks.QUARTZ_BRICKS);
    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS = createStairsBlock(Blocks.QUARTZ_BRICKS);
    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL = createWallBlock(Blocks.QUARTZ_BRICKS);

    public static final RegistryObject<Block> NETHERRACK_SLAB = createSlabBlock(Blocks.NETHERRACK);
    public static final RegistryObject<Block> NETHERRACK_STAIRS = createStairsBlock(Blocks.NETHERRACK);
    public static final RegistryObject<Block> NETHERRACK_WALL = createWallBlock(Blocks.NETHERRACK);

    public static final RegistryObject<Block> DIRT_SLAB = createDirtSlabBlock(Blocks.DIRT);
    public static final RegistryObject<Block> DIRT_STAIRS = createDirtStairsBlock(Blocks.DIRT);
    public static final RegistryObject<Block> DIRT_WALL = createDirtWallBlock(Blocks.DIRT);

    public static final RegistryObject<Block> COARSE_DIRT_SLAB = createDirtSlabBlock(Blocks.COARSE_DIRT);
    public static final RegistryObject<Block> COARSE_DIRT_STAIRS = createDirtStairsBlock(Blocks.COARSE_DIRT);
    public static final RegistryObject<Block> COARSE_DIRT_WALL = createDirtWallBlock(Blocks.COARSE_DIRT);

    public static final RegistryObject<Block> HONEYCOMB_BLOCK_SLAB = createSlabBlock(Blocks.HONEYCOMB_BLOCK);
    public static final RegistryObject<Block> HONEYCOMB_BLOCK_STAIRS = createStairsBlock(Blocks.HONEYCOMB_BLOCK);
    public static final RegistryObject<Block> HONEYCOMB_BLOCK_WALL = createWallBlock(Blocks.HONEYCOMB_BLOCK);

    public static final RegistryObject<Block> ROOTED_DIRT_SLAB = createDirtSlabBlock(Blocks.ROOTED_DIRT);
    public static final RegistryObject<Block> ROOTED_DIRT_STAIRS = createDirtStairsBlock(Blocks.ROOTED_DIRT);
    public static final RegistryObject<Block> ROOTED_DIRT_WALL = createDirtWallBlock(Blocks.ROOTED_DIRT);

    public static final RegistryObject<Block> SNOW_BLOCK_SLAB = createSlabBlock(Blocks.SNOW_BLOCK);
    public static final RegistryObject<Block> SNOW_BLOCK_STAIRS = createStairsBlock(Blocks.SNOW_BLOCK);
    public static final RegistryObject<Block> SNOW_BLOCK_WALL = createWallBlock(Blocks.SNOW_BLOCK);

    public static final RegistryObject<Block> MUSHROOM_STEM_SLAB = createSlabBlock(Blocks.MUSHROOM_STEM);
    public static final RegistryObject<Block> MUSHROOM_STEM_STAIRS = createStairsBlock(Blocks.MUSHROOM_STEM);
    public static final RegistryObject<Block> MUSHROOM_STEM_WALL = createWallBlock(Blocks.MUSHROOM_STEM);

    public static final RegistryObject<Block> BROWN_MUSHROOM_BLOCK_SLAB = createSlabBlock(Blocks.BROWN_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> BROWN_MUSHROOM_BLOCK_STAIRS = createStairsBlock(Blocks.BROWN_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> BROWN_MUSHROOM_BLOCK_WALL = createWallBlock(Blocks.BROWN_MUSHROOM_BLOCK);

    public static final RegistryObject<Block> RED_MUSHROOM_BLOCK_SLAB = createSlabBlock(Blocks.RED_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> RED_MUSHROOM_BLOCK_STAIRS = createStairsBlock(Blocks.RED_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> RED_MUSHROOM_BLOCK_WALL = createWallBlock(Blocks.RED_MUSHROOM_BLOCK);

    public static final RegistryObject<Block> PURPUR_WALL = createWallBlock("purpur", Blocks.PURPUR_BLOCK);

    public static final RegistryObject<Block> MOSS_BLOCK_SLAB = createSlabBlock(Blocks.MOSS_BLOCK);
    public static final RegistryObject<Block> MOSS_BLOCK_STAIRS = createStairsBlock(Blocks.MOSS_BLOCK);
    public static final RegistryObject<Block> MOSS_BLOCK_WALL = createWallBlock(Blocks.MOSS_BLOCK);

    public static final RegistryObject<Block> CALCITE_SLAB = createSlabBlock(Blocks.CALCITE);
    public static final RegistryObject<Block> CALCITE_STAIRS = createStairsBlock(Blocks.CALCITE);
    public static final RegistryObject<Block> CALCITE_WALL = createWallBlock(Blocks.CALCITE);

    public static final RegistryObject<Block> GLOWSTONE_SLAB = createSlabBlock(Blocks.GLOWSTONE);
    public static final RegistryObject<Block> GLOWSTONE_STAIRS = createStairsBlock(Blocks.GLOWSTONE);
    public static final RegistryObject<Block> GLOWSTONE_WALL = createWallBlock(Blocks.GLOWSTONE);

    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_SLAB = createSlabBlock(Blocks.CRACKED_STONE_BRICKS);
    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_STAIRS = createStairsBlock(Blocks.CRACKED_STONE_BRICKS);
    public static final RegistryObject<Block> CRACKED_STONE_BRICKS_WALL = createWallBlock(Blocks.CRACKED_STONE_BRICKS);

    public static final RegistryObject<Block> TUFF_SLAB = createSlabBlock(Blocks.TUFF);
    public static final RegistryObject<Block> TUFF_STAIRS = createStairsBlock(Blocks.TUFF);
    public static final RegistryObject<Block> TUFF_WALL = createWallBlock(Blocks.TUFF);

    public static final RegistryObject<Block> DRIPSTONE_BLOCK_SLAB = createSlabBlock(Blocks.DRIPSTONE_BLOCK);
    public static final RegistryObject<Block> DRIPSTONE_BLOCK_STAIRS = createStairsBlock(Blocks.DRIPSTONE_BLOCK);
    public static final RegistryObject<Block> DRIPSTONE_BLOCK_WALL = createWallBlock(Blocks.DRIPSTONE_BLOCK);

    public static final RegistryObject<Block> SEA_LANTERN_SLAB = createSlabBlock(Blocks.SEA_LANTERN);
    public static final RegistryObject<Block> SEA_LANTERN_STAIRS = createStairsBlock(Blocks.SEA_LANTERN);
    public static final RegistryObject<Block> SEA_LANTERN_WALL = createWallBlock(Blocks.SEA_LANTERN);

    public static final RegistryObject<Block> SHROOMLIGHT_SLAB = createSlabBlock(Blocks.SHROOMLIGHT);
    public static final RegistryObject<Block> SHROOMLIGHT_STAIRS = createStairsBlock(Blocks.SHROOMLIGHT);
    public static final RegistryObject<Block> SHROOMLIGHT_WALL = createWallBlock(Blocks.SHROOMLIGHT);

    public static final RegistryObject<Block> END_STONE_SLAB = createSlabBlock(Blocks.END_STONE);
    public static final RegistryObject<Block> END_STONE_STAIRS = createStairsBlock(Blocks.END_STONE);
    public static final RegistryObject<Block> END_STONE_WALL = createWallBlock(Blocks.END_STONE);

    public static final RegistryObject<Block> OAK_WOOD_SLAB = createSlabBlock(Blocks.OAK_WOOD);
    public static final RegistryObject<Block> OAK_WOOD_STAIRS = createStairsBlock(Blocks.OAK_WOOD);
    public static final RegistryObject<Block> OAK_WOOD_WALL = createWallBlock(Blocks.OAK_WOOD);

    public static final RegistryObject<Block> BIRCH_WOOD_SLAB = createSlabBlock(Blocks.BIRCH_WOOD);
    public static final RegistryObject<Block> BIRCH_WOOD_STAIRS = createStairsBlock(Blocks.BIRCH_WOOD);
    public static final RegistryObject<Block> BIRCH_WOOD_WALL = createWallBlock(Blocks.BIRCH_WOOD);

    public static final RegistryObject<Block> SPRUCE_WOOD_SLAB = createSlabBlock(Blocks.SPRUCE_WOOD);
    public static final RegistryObject<Block> SPRUCE_WOOD_STAIRS = createStairsBlock(Blocks.SPRUCE_WOOD);
    public static final RegistryObject<Block> SPRUCE_WOOD_WALL = createWallBlock(Blocks.SPRUCE_WOOD);

    public static final RegistryObject<Block> JUNGLE_WOOD_SLAB = createSlabBlock(Blocks.JUNGLE_WOOD);
    public static final RegistryObject<Block> JUNGLE_WOOD_STAIRS = createStairsBlock(Blocks.JUNGLE_WOOD);
    public static final RegistryObject<Block> JUNGLE_WOOD_WALL = createWallBlock(Blocks.JUNGLE_WOOD);

    public static final RegistryObject<Block> DARK_OAK_WOOD_SLAB = createSlabBlock(Blocks.DARK_OAK_WOOD);
    public static final RegistryObject<Block> DARK_OAK_WOOD_STAIRS = createStairsBlock(Blocks.DARK_OAK_WOOD);
    public static final RegistryObject<Block> DARK_OAK_WOOD_WALL = createWallBlock(Blocks.DARK_OAK_WOOD);

    public static final RegistryObject<Block> ACACIA_WOOD_SLAB = createSlabBlock(Blocks.ACACIA_WOOD);
    public static final RegistryObject<Block> ACACIA_WOOD_STAIRS = createStairsBlock(Blocks.ACACIA_WOOD);
    public static final RegistryObject<Block> ACACIA_WOOD_WALL = createWallBlock(Blocks.ACACIA_WOOD);

    public static final RegistryObject<Block> CRIMSON_HYPHAE_SLAB = createSlabBlock(Blocks.CRIMSON_HYPHAE);
    public static final RegistryObject<Block> CRIMSON_HYPHAE_STAIRS = createStairsBlock(Blocks.CRIMSON_HYPHAE);
    public static final RegistryObject<Block> CRIMSON_HYPHAE_WALL = createWallBlock(Blocks.CRIMSON_HYPHAE);

    public static final RegistryObject<Block> WARPED_HYPHAE_SLAB = createSlabBlock(Blocks.WARPED_HYPHAE);
    public static final RegistryObject<Block> WARPED_HYPHAE_STAIRS = createStairsBlock(Blocks.WARPED_HYPHAE);
    public static final RegistryObject<Block> WARPED_HYPHAE_WALL = createWallBlock(Blocks.WARPED_HYPHAE);

    public static final RegistryObject<Block> STRIPPED_OAK_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_OAK_WOOD);
    public static final RegistryObject<Block> STRIPPED_OAK_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_OAK_WOOD);
    public static final RegistryObject<Block> STRIPPED_OAK_WOOD_WALL = createWallBlock(Blocks.STRIPPED_OAK_WOOD);

    public static final RegistryObject<Block> STRIPPED_BIRCH_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_BIRCH_WOOD);
    public static final RegistryObject<Block> STRIPPED_BIRCH_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_BIRCH_WOOD);
    public static final RegistryObject<Block> STRIPPED_BIRCH_WOOD_WALL = createWallBlock(Blocks.STRIPPED_BIRCH_WOOD);

    public static final RegistryObject<Block> STRIPPED_SPRUCE_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_SPRUCE_WOOD);
    public static final RegistryObject<Block> STRIPPED_SPRUCE_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_SPRUCE_WOOD);
    public static final RegistryObject<Block> STRIPPED_SPRUCE_WOOD_WALL = createWallBlock(Blocks.STRIPPED_SPRUCE_WOOD);

    public static final RegistryObject<Block> STRIPPED_JUNGLE_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_JUNGLE_WOOD);
    public static final RegistryObject<Block> STRIPPED_JUNGLE_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_JUNGLE_WOOD);
    public static final RegistryObject<Block> STRIPPED_JUNGLE_WOOD_WALL = createWallBlock(Blocks.STRIPPED_JUNGLE_WOOD);

    public static final RegistryObject<Block> STRIPPED_DARK_OAK_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_DARK_OAK_WOOD);
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_DARK_OAK_WOOD);
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_WOOD_WALL = createWallBlock(Blocks.STRIPPED_DARK_OAK_WOOD);

    public static final RegistryObject<Block> STRIPPED_ACACIA_WOOD_SLAB = createSlabBlock(Blocks.STRIPPED_ACACIA_WOOD);
    public static final RegistryObject<Block> STRIPPED_ACACIA_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_ACACIA_WOOD);
    public static final RegistryObject<Block> STRIPPED_ACACIA_WOOD_WALL = createWallBlock(Blocks.STRIPPED_ACACIA_WOOD);

    public static final RegistryObject<Block> STRIPPED_CRIMSON_HYPHAE_SLAB = createSlabBlock(Blocks.STRIPPED_CRIMSON_HYPHAE);
    public static final RegistryObject<Block> STRIPPED_CRIMSON_HYPHAE_STAIRS = createStairsBlock(Blocks.STRIPPED_CRIMSON_HYPHAE);
    public static final RegistryObject<Block> STRIPPED_CRIMSON_HYPHAE_WALL = createWallBlock(Blocks.STRIPPED_CRIMSON_HYPHAE);

    public static final RegistryObject<Block> STRIPPED_WARPED_HYPHAE_SLAB = createSlabBlock(Blocks.STRIPPED_WARPED_HYPHAE);
    public static final RegistryObject<Block> STRIPPED_WARPED_HYPHAE_STAIRS = createStairsBlock(Blocks.STRIPPED_WARPED_HYPHAE);
    public static final RegistryObject<Block> STRIPPED_WARPED_HYPHAE_WALL = createWallBlock(Blocks.STRIPPED_WARPED_HYPHAE);



    public static final RegistryObject<Block> BASALT_SLAB = createSlabBlock(Blocks.BASALT);
    public static final RegistryObject<Block> BASALT_WALL = createWallBlock(Blocks.BASALT);

    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB = createSlabBlock(Blocks.SMOOTH_BASALT);
    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = createStairsBlock(Blocks.SMOOTH_BASALT);
    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = createWallBlock(Blocks.SMOOTH_BASALT);
    
    public static final RegistryObject<Block> CUT_COPPER_WALL = createWallBlock(Blocks.CUT_COPPER);
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_WALL = createWallBlock(Blocks.EXPOSED_CUT_COPPER);
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_WALL = createWallBlock(Blocks.WEATHERED_CUT_COPPER);
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_WALL = createWallBlock(Blocks.OXIDIZED_CUT_COPPER);

    public static final RegistryObject<Block> WAXED_CUT_COPPER_WALL = createWallBlock(Blocks.WAXED_CUT_COPPER);
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_WALL = createWallBlock(Blocks.WAXED_EXPOSED_CUT_COPPER);
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_WALL = createWallBlock(Blocks.WAXED_WEATHERED_CUT_COPPER);
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_WALL = createWallBlock(Blocks.WAXED_OXIDIZED_CUT_COPPER);

    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = createWallBlock(Blocks.SMOOTH_QUARTZ);
    public static final RegistryObject<Block> QUARTZ_WALL = createWallBlock("quartz", Blocks.QUARTZ_BLOCK);
    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL = createWallBlock(Blocks.POLISHED_ANDESITE);
    public static final RegistryObject<Block> POLISHED_DIORITE_WALL = createWallBlock(Blocks.POLISHED_DIORITE);
    public static final RegistryObject<Block> POLISHED_GRANITE_WALL = createWallBlock(Blocks.POLISHED_GRANITE);

    public static final RegistryObject<Block> SMOOTH_SANDSTONE_WALL = createWallBlock(Blocks.SMOOTH_SANDSTONE);
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_WALL = createWallBlock(Blocks.SMOOTH_RED_SANDSTONE);
    public static final RegistryObject<Block> DARK_PRISMARINE_WALL = createWallBlock(Blocks.DARK_PRISMARINE);
    public static final RegistryObject<Block> PRISMARINE_BRICKS_WALL = createWallBlock(Blocks.PRISMARINE_BRICKS);
    
    public static final RegistryObject<Block> STONE_WALL = createWallBlock(Blocks.STONE);

    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS = createStairsBlock(Blocks.SMOOTH_STONE);
    public static final RegistryObject<Block> SMOOTH_STONE_WALL = createWallBlock(Blocks.SMOOTH_STONE);


    public static final RegistryObject<Block> OBSIDIAN_SLAB = createSlabBlock(Blocks.OBSIDIAN);
    public static final RegistryObject<Block> OBSIDIAN_STAIRS = createStairsBlock(Blocks.OBSIDIAN);
    public static final RegistryObject<Block> OBSIDIAN_WALL = createWallBlock(Blocks.OBSIDIAN);

    public static final RegistryObject<Block> OAK_LEAVES_SLAB = createSlabBlock("oak_leaves", Blocks.OAK_LEAVES);
    public static final RegistryObject<Block> OAK_LEAVES_STAIRS = createStairsBlock("oak_leaves", Blocks.OAK_LEAVES);
    public static final RegistryObject<Block> OAK_LEAVES_WALL = createWallBlock("oak_leaves", Blocks.OAK_LEAVES);

    public static final RegistryObject<Block> SPRUCE_LEAVES_SLAB = createSlabBlock("spruce_leaves", Blocks.SPRUCE_LEAVES);
    public static final RegistryObject<Block> SPRUCE_LEAVES_STAIRS = createStairsBlock("spruce_leaves", Blocks.SPRUCE_LEAVES);
    public static final RegistryObject<Block> SPRUCE_LEAVES_WALL = createWallBlock("spruce_leaves", Blocks.SPRUCE_LEAVES);

    public static final RegistryObject<Block> BIRCH_LEAVES_SLAB = createSlabBlock("birch_leaves", Blocks.BIRCH_LEAVES);
    public static final RegistryObject<Block> BIRCH_LEAVES_STAIRS = createStairsBlock("birch_leaves", Blocks.BIRCH_LEAVES);
    public static final RegistryObject<Block> BIRCH_LEAVES_WALL = createWallBlock("birch_leaves", Blocks.BIRCH_LEAVES);

    public static final RegistryObject<Block> JUNGLE_LEAVES_SLAB = createSlabBlock("jungle_leaves", Blocks.JUNGLE_LEAVES);
    public static final RegistryObject<Block> JUNGLE_LEAVES_STAIRS = createStairsBlock("jungle_leaves", Blocks.JUNGLE_LEAVES);
    public static final RegistryObject<Block> JUNGLE_LEAVES_WALL = createWallBlock("jungle_leaves", Blocks.JUNGLE_LEAVES);

    public static final RegistryObject<Block> DARK_OAK_LEAVES_SLAB = createSlabBlock("dark_oak_leaves", Blocks.DARK_OAK_LEAVES);
    public static final RegistryObject<Block> DARK_OAK_LEAVES_STAIRS = createStairsBlock("dark_oak_leaves", Blocks.DARK_OAK_LEAVES);
    public static final RegistryObject<Block> DARK_OAK_LEAVES_WALL = createWallBlock("dark_oak_leaves", Blocks.DARK_OAK_LEAVES);

    public static final RegistryObject<Block> ACACIA_LEAVES_SLAB = createSlabBlock("acacia_leaves", Blocks.ACACIA_LEAVES);
    public static final RegistryObject<Block> ACACIA_LEAVES_STAIRS = createStairsBlock("acacia_leaves", Blocks.ACACIA_LEAVES);
    public static final RegistryObject<Block> ACACIA_LEAVES_WALL = createWallBlock("acacia_leaves", Blocks.ACACIA_LEAVES);

    public static final RegistryObject<Block> AZALEA_LEAVES_SLAB = createSlabBlock("azalea_leaves", Blocks.AZALEA_LEAVES);
    public static final RegistryObject<Block> AZALEA_LEAVES_STAIRS = createStairsBlock("azalea_leaves", Blocks.AZALEA_LEAVES);
    public static final RegistryObject<Block> AZALEA_LEAVES_WALL = createWallBlock("azalea_leaves", Blocks.AZALEA_LEAVES);

    public static final RegistryObject<Block> FLOWERING_AZALEA_LEAVES_SLAB = createSlabBlock("flowering_azalea_leaves", Blocks.FLOWERING_AZALEA_LEAVES);
    public static final RegistryObject<Block> FLOWERING_AZALEA_LEAVES_STAIRS = createStairsBlock("flowering_azalea_leaves", Blocks.FLOWERING_AZALEA_LEAVES);
    public static final RegistryObject<Block> FLOWERING_AZALEA_LEAVES_WALL = createWallBlock("flowering_azalea_leaves", Blocks.FLOWERING_AZALEA_LEAVES);

    public static final RegistryObject<Block> WARPED_WART_BLOCK_SLAB = createSlabBlock(Blocks.WARPED_WART_BLOCK);
    public static final RegistryObject<Block> WARPED_WART_BLOCK_STAIRS = createStairsBlock(Blocks.WARPED_WART_BLOCK);
    public static final RegistryObject<Block> WARPED_WART_BLOCK_WALL = createWallBlock(Blocks.WARPED_WART_BLOCK);

    public static final RegistryObject<Block> NETHER_WART_BLOCK_SLAB = createSlabBlock(Blocks.NETHER_WART_BLOCK);
    public static final RegistryObject<Block> NETHER_WART_BLOCK_STAIRS = createStairsBlock(Blocks.NETHER_WART_BLOCK);
    public static final RegistryObject<Block> NETHER_WART_BLOCK_WALL = createWallBlock(Blocks.NETHER_WART_BLOCK);

    public static final RegistryObject<Block> AMETHYST_BLOCK_SLAB = createSlabBlock(Blocks.AMETHYST_BLOCK);
    public static final RegistryObject<Block> AMETHYST_BLOCK_STAIRS = createStairsBlock(Blocks.AMETHYST_BLOCK);
    public static final RegistryObject<Block> AMETHYST_BLOCK_WALL = createWallBlock(Blocks.AMETHYST_BLOCK);

    public static final RegistryObject<Block> RAW_COPPER_BLOCK_SLAB = createSlabBlock(Blocks.RAW_COPPER_BLOCK);
    public static final RegistryObject<Block> RAW_COPPER_BLOCK_STAIRS = createStairsBlock(Blocks.RAW_COPPER_BLOCK);
    public static final RegistryObject<Block> RAW_COPPER_BLOCK_WALL = createWallBlock(Blocks.RAW_COPPER_BLOCK);

    public static final RegistryObject<Block> RAW_IRON_BLOCK_SLAB = createSlabBlock(Blocks.RAW_IRON_BLOCK);
    public static final RegistryObject<Block> RAW_IRON_BLOCK_STAIRS = createStairsBlock(Blocks.RAW_IRON_BLOCK);
    public static final RegistryObject<Block> RAW_IRON_BLOCK_WALL = createWallBlock(Blocks.RAW_IRON_BLOCK);

    public static final RegistryObject<Block> RAW_GOLD_BLOCK_SLAB = createSlabBlock(Blocks.RAW_GOLD_BLOCK);
    public static final RegistryObject<Block> RAW_GOLD_BLOCK_STAIRS = createStairsBlock(Blocks.RAW_GOLD_BLOCK);
    public static final RegistryObject<Block> RAW_GOLD_BLOCK_WALL = createWallBlock(Blocks.RAW_GOLD_BLOCK);

    public static final RegistryObject<Block> MAGMA_BLOCK_SLAB = BLOCKS.register("magma_block" + "_slab", () -> new MagmaSlab(getSettingsFromBlock(Blocks.MAGMA_BLOCK)));
    public static final RegistryObject<Block> MAGMA_BLOCK_STAIRS = BLOCKS.register("magma_block" + "_stairs", () -> new MagmaStairs(getSettingsFromBlock(Blocks.MAGMA_BLOCK)));
    public static final RegistryObject<Block> MAGMA_BLOCK_WALL = BLOCKS.register("magma_block" + "_wall", () -> new MagmaWall(getSettingsFromBlock(Blocks.MAGMA_BLOCK)));

    public static final RegistryObject<Block> SOUL_SAND_SLAB = BLOCKS.register("soul_sand" + "_slab", () -> new SoulSandSlab(getSettingsFromBlock(Blocks.SOUL_SAND)));
    public static final RegistryObject<Block> SOUL_SAND_STAIRS = BLOCKS.register("soul_sand" + "_stairs", () -> new SoulSandStairs(getSettingsFromBlock(Blocks.SOUL_SAND)));
    public static final RegistryObject<Block> SOUL_SAND_WALL = BLOCKS.register("soul_sand" + "_wall", () -> new SoulSandWall(getSettingsFromBlock(Blocks.SOUL_SAND)));

    public static final RegistryObject<Block> SOUL_SOIL_SLAB = createSlabBlock(Blocks.SOUL_SOIL);
    public static final RegistryObject<Block> SOUL_SOIL_STAIRS = createStairsBlock(Blocks.SOUL_SOIL);
    public static final RegistryObject<Block> SOUL_SOIL_WALL = createWallBlock(Blocks.SOUL_SOIL);

    public static final RegistryObject<Block> CLAY_SLAB = createSlabBlock(Blocks.CLAY);
    public static final RegistryObject<Block> CLAY_STAIRS = createStairsBlock(Blocks.CLAY);
    public static final RegistryObject<Block> CLAY_WALL = createWallBlock(Blocks.CLAY);

    public static final RegistryObject<Block> OCHRE_FROGLIGHT_SLAB = createSlabBlock(Blocks.OCHRE_FROGLIGHT);
    public static final RegistryObject<Block> OCHRE_FROGLIGHT_STAIRS = createStairsBlock(Blocks.OCHRE_FROGLIGHT);
    public static final RegistryObject<Block> OCHRE_FROGLIGHT_WALL = createWallBlock(Blocks.OCHRE_FROGLIGHT);

    public static final RegistryObject<Block> VERDANT_FROGLIGHT_SLAB = createSlabBlock(Blocks.VERDANT_FROGLIGHT);
    public static final RegistryObject<Block> VERDANT_FROGLIGHT_STAIRS = createStairsBlock(Blocks.VERDANT_FROGLIGHT);
    public static final RegistryObject<Block> VERDANT_FROGLIGHT_WALL = createWallBlock(Blocks.VERDANT_FROGLIGHT);

    public static final RegistryObject<Block> PEARLESCENT_FROGLIGHT_SLAB = createSlabBlock(Blocks.PEARLESCENT_FROGLIGHT);
    public static final RegistryObject<Block> PEARLESCENT_FROGLIGHT_STAIRS = createStairsBlock(Blocks.PEARLESCENT_FROGLIGHT);
    public static final RegistryObject<Block> PEARLESCENT_FROGLIGHT_WALL = createWallBlock(Blocks.PEARLESCENT_FROGLIGHT);

    public static final RegistryObject<Block> CRYING_OBSIDIAN_SLAB = createSlabBlock(Blocks.CRYING_OBSIDIAN);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_STAIRS = createStairsBlock(Blocks.CRYING_OBSIDIAN);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_WALL = createWallBlock(Blocks.CRYING_OBSIDIAN);

    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_SLAB = createSlabBlock(Blocks.CRACKED_NETHER_BRICKS);
    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_STAIRS = createStairsBlock(Blocks.CRACKED_NETHER_BRICKS);
    public static final RegistryObject<Block> CRACKED_NETHER_BRICKS_WALL = createWallBlock(Blocks.CRACKED_NETHER_BRICKS);

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_SLAB = createSlabBlock(Blocks.CRACKED_DEEPSLATE_BRICKS);
    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_STAIRS = createStairsBlock(Blocks.CRACKED_DEEPSLATE_BRICKS);
    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICKS_WALL = createWallBlock(Blocks.CRACKED_DEEPSLATE_BRICKS);

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB = createSlabBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS = createStairsBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL = createWallBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_SLAB = createSlabBlock(Blocks.CRACKED_DEEPSLATE_TILES);
    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_STAIRS = createStairsBlock(Blocks.CRACKED_DEEPSLATE_TILES);
    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILES_WALL = createWallBlock(Blocks.CRACKED_DEEPSLATE_TILES);

    public static final RegistryObject<Block> DEAD_TUBE_CORAL_BLOCK_SLAB = createSlabBlock(Blocks.DEAD_TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_TUBE_CORAL_BLOCK_STAIRS = createStairsBlock(Blocks.DEAD_TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_TUBE_CORAL_BLOCK_WALL = createWallBlock(Blocks.DEAD_TUBE_CORAL_BLOCK);

    public static final RegistryObject<Block> TUBE_CORAL_BLOCK_SLAB = createCoralSlabBlock(DEAD_TUBE_CORAL_BLOCK_SLAB, Blocks.TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> TUBE_CORAL_BLOCK_STAIRS = createCoralStairsBlock(DEAD_TUBE_CORAL_BLOCK_STAIRS, Blocks.TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> TUBE_CORAL_BLOCK_WALL = createCoralWallBlock(DEAD_TUBE_CORAL_BLOCK_WALL, Blocks.TUBE_CORAL_BLOCK);

    public static final RegistryObject<Block> DEAD_BUBBLE_CORAL_BLOCK_SLAB = createSlabBlock(Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_BUBBLE_CORAL_BLOCK_STAIRS = createStairsBlock(Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_BUBBLE_CORAL_BLOCK_WALL = createWallBlock(Blocks.DEAD_BUBBLE_CORAL_BLOCK);

    public static final RegistryObject<Block> BUBBLE_CORAL_BLOCK_SLAB = createCoralSlabBlock(DEAD_BUBBLE_CORAL_BLOCK_SLAB, Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> BUBBLE_CORAL_BLOCK_STAIRS = createCoralStairsBlock(DEAD_BUBBLE_CORAL_BLOCK_STAIRS, Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> BUBBLE_CORAL_BLOCK_WALL = createCoralWallBlock(DEAD_BUBBLE_CORAL_BLOCK_WALL, Blocks.BUBBLE_CORAL_BLOCK);

    public static final RegistryObject<Block> DEAD_BRAIN_CORAL_BLOCK_SLAB = createSlabBlock(Blocks.DEAD_BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_BRAIN_CORAL_BLOCK_STAIRS = createStairsBlock(Blocks.DEAD_BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_BRAIN_CORAL_BLOCK_WALL = createWallBlock(Blocks.DEAD_BRAIN_CORAL_BLOCK);

    public static final RegistryObject<Block> BRAIN_CORAL_BLOCK_SLAB = createCoralSlabBlock(DEAD_BRAIN_CORAL_BLOCK_SLAB, Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> BRAIN_CORAL_BLOCK_STAIRS = createCoralStairsBlock(DEAD_BRAIN_CORAL_BLOCK_STAIRS, Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> BRAIN_CORAL_BLOCK_WALL = createCoralWallBlock(DEAD_BRAIN_CORAL_BLOCK_WALL, Blocks.BRAIN_CORAL_BLOCK);

    public static final RegistryObject<Block> DEAD_FIRE_CORAL_BLOCK_SLAB = createSlabBlock(Blocks.DEAD_FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_FIRE_CORAL_BLOCK_STAIRS = createStairsBlock(Blocks.DEAD_FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_FIRE_CORAL_BLOCK_WALL = createWallBlock(Blocks.DEAD_FIRE_CORAL_BLOCK);

    public static final RegistryObject<Block> FIRE_CORAL_BLOCK_SLAB = createCoralSlabBlock(DEAD_FIRE_CORAL_BLOCK_SLAB, Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> FIRE_CORAL_BLOCK_STAIRS = createCoralStairsBlock(DEAD_FIRE_CORAL_BLOCK_STAIRS, Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> FIRE_CORAL_BLOCK_WALL = createCoralWallBlock(DEAD_FIRE_CORAL_BLOCK_WALL, Blocks.FIRE_CORAL_BLOCK);

    public static final RegistryObject<Block> DEAD_HORN_CORAL_BLOCK_SLAB = createSlabBlock(Blocks.DEAD_HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_HORN_CORAL_BLOCK_STAIRS = createStairsBlock(Blocks.DEAD_HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> DEAD_HORN_CORAL_BLOCK_WALL = createWallBlock(Blocks.DEAD_HORN_CORAL_BLOCK);

    public static final RegistryObject<Block> HORN_CORAL_BLOCK_SLAB = createCoralSlabBlock(DEAD_HORN_CORAL_BLOCK_SLAB, Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> HORN_CORAL_BLOCK_STAIRS = createCoralStairsBlock(DEAD_HORN_CORAL_BLOCK_STAIRS, Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> HORN_CORAL_BLOCK_WALL = createCoralWallBlock(DEAD_HORN_CORAL_BLOCK_WALL, Blocks.HORN_CORAL_BLOCK);

    public static final RegistryObject<Block> ICE_SLAB = createSlabBlock(Blocks.ICE);
    public static final RegistryObject<Block> ICE_STAIRS = createStairsBlock(Blocks.ICE);
    public static final RegistryObject<Block> ICE_WALL = createWallBlock(Blocks.ICE);

    public static final RegistryObject<Block> PACKED_ICE_SLAB = createSlabBlock(Blocks.PACKED_ICE);
    public static final RegistryObject<Block> PACKED_ICE_STAIRS = createStairsBlock(Blocks.PACKED_ICE);
    public static final RegistryObject<Block> PACKED_ICE_WALL = createWallBlock(Blocks.PACKED_ICE);

    public static final RegistryObject<Block> BLUE_ICE_SLAB = createSlabBlock(Blocks.BLUE_ICE);
    public static final RegistryObject<Block> BLUE_ICE_STAIRS = createStairsBlock(Blocks.BLUE_ICE);
    public static final RegistryObject<Block> BLUE_ICE_WALL = createWallBlock(Blocks.BLUE_ICE);

    public static final RegistryObject<Block> SLIME_BLOCK_SLAB = BLOCKS.register("slime_block" + "_slab", () -> new SlimeBlockSlab(getSettingsFromBlock(Blocks.SLIME_BLOCK)));
    public static final RegistryObject<Block> SLIME_BLOCK_STAIRS = BLOCKS.register("slime_block" + "_stairs", () -> new SlimeBlockStairs(getSettingsFromBlock(Blocks.SLIME_BLOCK)));
    public static final RegistryObject<Block> SLIME_BLOCK_WALL = BLOCKS.register("slime_block" + "_wall", () -> new SlimeBlockWall(getSettingsFromBlock(Blocks.SLIME_BLOCK)));

    public static final RegistryObject<Block> HONEY_BLOCK_SLAB = BLOCKS.register("honey_block" + "_slab", () -> new HoneyBlockSlab(getSettingsFromBlock(Blocks.HONEY_BLOCK)));
    public static final RegistryObject<Block> HONEY_BLOCK_STAIRS = BLOCKS.register("honey_block" + "_stairs", () -> new HoneyBlockStairs(getSettingsFromBlock(Blocks.HONEY_BLOCK)));
    public static final RegistryObject<Block> HONEY_BLOCK_WALL = BLOCKS.register("honey_block" + "_wall", () -> new HoneyBlockWall(getSettingsFromBlock(Blocks.HONEY_BLOCK)));

    public static final RegistryObject<Block> CRIMSON_NYLIUM_SLAB = createSpreadableSlabBlock(Blocks.CRIMSON_NYLIUM);
    public static final RegistryObject<Block> CRIMSON_NYLIUM_STAIRS = createSpreadableStairsBlock(Blocks.CRIMSON_NYLIUM);
    public static final RegistryObject<Block> CRIMSON_NYLIUM_WALL = createSpreadableWallBlock(Blocks.CRIMSON_NYLIUM);

    public static final RegistryObject<Block> WARPED_NYLIUM_SLAB = createSpreadableSlabBlock(Blocks.WARPED_NYLIUM);
    public static final RegistryObject<Block> WARPED_NYLIUM_STAIRS = createSpreadableStairsBlock(Blocks.WARPED_NYLIUM);
    public static final RegistryObject<Block> WARPED_NYLIUM_WALL = createSpreadableWallBlock(Blocks.WARPED_NYLIUM);

    public static final RegistryObject<Block> DRIED_KELP_BLOCK_SLAB = createSlabBlock(Blocks.DRIED_KELP_BLOCK);
    public static final RegistryObject<Block> DRIED_KELP_BLOCK_STAIRS = createStairsBlock(Blocks.DRIED_KELP_BLOCK);
    public static final RegistryObject<Block> DRIED_KELP_BLOCK_WALL = createWallBlock(Blocks.DRIED_KELP_BLOCK);

    public static final RegistryObject<Block> GRASS_BLOCK_SLAB = createSpreadableSlabBlock((Blocks.GRASS_BLOCK));
    public static final RegistryObject<Block> GRASS_BLOCK_STAIRS = createSpreadableStairsBlock(Blocks.GRASS_BLOCK);
    public static final RegistryObject<Block> GRASS_BLOCK_WALL = createSpreadableWallBlock(Blocks.GRASS_BLOCK);

    public static final RegistryObject<Block> MYCELIUM_SLAB = createSpreadableSlabBlock((Blocks.MYCELIUM));
    public static final RegistryObject<Block> MYCELIUM_STAIRS = createSpreadableStairsBlock(Blocks.MYCELIUM);
    public static final RegistryObject<Block> MYCELIUM_WALL = createSpreadableWallBlock(Blocks.MYCELIUM);

    public static final RegistryObject<Block> PODZOL_SLAB = createSlabBlock((Blocks.PODZOL));
    public static final RegistryObject<Block> PODZOL_STAIRS = createStairsBlock(Blocks.PODZOL);
    public static final RegistryObject<Block> PODZOL_WALL = createWallBlock(Blocks.PODZOL);

    public static final RegistryObject<Block> COAL_BLOCK_SLAB = createSlabBlock((Blocks.COAL_BLOCK));
    public static final RegistryObject<Block> COAL_BLOCK_STAIRS = createStairsBlock(Blocks.COAL_BLOCK);
    public static final RegistryObject<Block> COAL_BLOCK_WALL = createWallBlock(Blocks.COAL_BLOCK);

    public static final RegistryObject<Block> IRON_BLOCK_SLAB = createSlabBlock((Blocks.IRON_BLOCK));
    public static final RegistryObject<Block> IRON_BLOCK_STAIRS = createStairsBlock(Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> IRON_BLOCK_WALL = createWallBlock(Blocks.IRON_BLOCK);

    public static final RegistryObject<Block> GOLD_BLOCK_SLAB = createSlabBlock((Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> GOLD_BLOCK_STAIRS = createStairsBlock(Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> GOLD_BLOCK_WALL = createWallBlock(Blocks.GOLD_BLOCK);

    public static final RegistryObject<Block> DIAMOND_BLOCK_SLAB = createSlabBlock((Blocks.DIAMOND_BLOCK));
    public static final RegistryObject<Block> DIAMOND_BLOCK_STAIRS = createStairsBlock(Blocks.DIAMOND_BLOCK);
    public static final RegistryObject<Block> DIAMOND_BLOCK_WALL = createWallBlock(Blocks.DIAMOND_BLOCK);

    public static final RegistryObject<Block> NETHERITE_BLOCK_SLAB = createSlabBlock((Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> NETHERITE_BLOCK_STAIRS = createStairsBlock(Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> NETHERITE_BLOCK_WALL = createWallBlock(Blocks.NETHERITE_BLOCK);

    public static final RegistryObject<Block> LAPIS_BLOCK_SLAB = createSlabBlock((Blocks.LAPIS_BLOCK));
    public static final RegistryObject<Block> LAPIS_BLOCK_STAIRS = createStairsBlock(Blocks.LAPIS_BLOCK);
    public static final RegistryObject<Block> LAPIS_BLOCK_WALL = createWallBlock(Blocks.LAPIS_BLOCK);

    public static final RegistryObject<Block> EMERALD_BLOCK_SLAB = createSlabBlock((Blocks.EMERALD_BLOCK));
    public static final RegistryObject<Block> EMERALD_BLOCK_STAIRS = createStairsBlock(Blocks.EMERALD_BLOCK);
    public static final RegistryObject<Block> EMERALD_BLOCK_WALL = createWallBlock(Blocks.EMERALD_BLOCK);

    public static final RegistryObject<Block> GILDED_BLACKSTONE_SLAB = createSlabBlock((Blocks.GILDED_BLACKSTONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_STAIRS = createStairsBlock(Blocks.GILDED_BLACKSTONE);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_WALL = createWallBlock(Blocks.GILDED_BLACKSTONE);

    public static final RegistryObject<Block> PUMPKIN_SLAB = createSlabBlock((Blocks.PUMPKIN));
    public static final RegistryObject<Block> PUMPKIN_STAIRS = createStairsBlock(Blocks.PUMPKIN);
    public static final RegistryObject<Block> PUMPKIN_WALL = createWallBlock(Blocks.PUMPKIN);

    public static final RegistryObject<Block> MELON_SLAB = createSlabBlock((Blocks.MELON));
    public static final RegistryObject<Block> MELON_STAIRS = createStairsBlock(Blocks.MELON);
    public static final RegistryObject<Block> MELON_WALL = createWallBlock(Blocks.MELON);

    public static final RegistryObject<Block> BONE_BLOCK_SLAB = createSlabBlock((Blocks.BONE_BLOCK));
    public static final RegistryObject<Block> BONE_BLOCK_STAIRS = createStairsBlock(Blocks.BONE_BLOCK);
    public static final RegistryObject<Block> BONE_BLOCK_WALL = createWallBlock(Blocks.BONE_BLOCK);


    public static final RegistryObject<Block> HAY_BLOCK_SLAB = createSlabBlock((Blocks.HAY_BLOCK));
    public static final RegistryObject<Block> HAY_BLOCK_STAIRS = createStairsBlock(Blocks.HAY_BLOCK);
    public static final RegistryObject<Block> HAY_BLOCK_WALL = createWallBlock(Blocks.HAY_BLOCK);

    public static final RegistryObject<Block> BOOKSHELF_SLAB = createSlabBlock((Blocks.BOOKSHELF));
    public static final RegistryObject<Block> BOOKSHELF_STAIRS = createStairsBlock(Blocks.BOOKSHELF);
    //public static final RegistryObject<Block> BOOKSHELF_WALL = createWallBlock(Blocks.BOOKSHELF);
    
    public static final RegistryObject<Block> OAK_PLANKS_WALL = createWallBlock(Blocks.OAK_PLANKS);
    public static final RegistryObject<Block> BIRCH_PLANKS_WALL = createWallBlock(Blocks.BIRCH_PLANKS);
    public static final RegistryObject<Block> SPRUCE_PLANKS_WALL = createWallBlock(Blocks.SPRUCE_PLANKS);
    public static final RegistryObject<Block> JUNGLE_PLANKS_WALL = createWallBlock(Blocks.JUNGLE_PLANKS);
    public static final RegistryObject<Block> ACACIA_PLANKS_WALL = createWallBlock(Blocks.ACACIA_PLANKS);
    public static final RegistryObject<Block> DARK_OAK_PLANKS_WALL = createWallBlock(Blocks.DARK_OAK_PLANKS);
    public static final RegistryObject<Block> WARPED_PLANKS_WALL = createWallBlock(Blocks.WARPED_PLANKS);
    public static final RegistryObject<Block> CRIMSON_PLANKS_WALL = createWallBlock(Blocks.CRIMSON_PLANKS);


    public static final RegistryObject<Block> SCULK_SLAB = createSlabBlock((Blocks.SCULK));
    public static final RegistryObject<Block> SCULK_STAIRS = createStairsBlock(Blocks.SCULK);
    public static final RegistryObject<Block> SCULK_WALL = createWallBlock(Blocks.SCULK);

    public static final RegistryObject<Block> PACKED_MUD_SLAB = createSlabBlock((Blocks.PACKED_MUD));
    public static final RegistryObject<Block> PACKED_MUD_STAIRS = createStairsBlock(Blocks.PACKED_MUD);
    public static final RegistryObject<Block> PACKED_MUD_WALL = createWallBlock(Blocks.PACKED_MUD);

    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG_SLAB = createSlabBlock((Blocks.STRIPPED_MANGROVE_LOG));
    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_MANGROVE_LOG);
    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG_WALL = createWallBlock(Blocks.STRIPPED_MANGROVE_LOG);

    public static final RegistryObject<Block> MANGROVE_LOG_SLAB = createLogSlabBlock(STRIPPED_MANGROVE_LOG_SLAB, Blocks.MANGROVE_LOG);
    public static final RegistryObject<Block> MANGROVE_LOG_STAIRS = createLogStairsBlock(STRIPPED_MANGROVE_LOG_STAIRS, Blocks.MANGROVE_LOG);
    public static final RegistryObject<Block> MANGROVE_LOG_WALL = createLogWallBlock(STRIPPED_MANGROVE_LOG_WALL, Blocks.MANGROVE_LOG);

    public static final RegistryObject<Block> MANGROVE_WOOD_SLAB = createSlabBlock((Blocks.MANGROVE_WOOD));
    public static final RegistryObject<Block> MANGROVE_WOOD_STAIRS = createStairsBlock(Blocks.MANGROVE_WOOD);
    public static final RegistryObject<Block> MANGROVE_WOOD_WALL = createWallBlock(Blocks.MANGROVE_WOOD);

    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD_SLAB = createSlabBlock((Blocks.STRIPPED_MANGROVE_WOOD));
    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD_STAIRS = createStairsBlock(Blocks.STRIPPED_MANGROVE_WOOD);
    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD_WALL = createWallBlock(Blocks.STRIPPED_MANGROVE_WOOD);

    public static final RegistryObject<Block> MANGROVE_LEAVES_SLAB = createSlabBlock("mangrove_leaves", Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> MANGROVE_LEAVES_STAIRS = createStairsBlock("mangrove_leaves", Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> MANGROVE_LEAVES_WALL = createWallBlock("mangrove_leaves", Blocks.MANGROVE_LEAVES);

    public static final RegistryObject<Block> MANGROVE_PLANKS_WALL = createWallBlock(Blocks.MANGROVE_PLANKS);

    public static final RegistryObject<Block> MUD_SLAB = createSlabBlock((Blocks.MUD));
    public static final RegistryObject<Block> MUD_STAIRS = createStairsBlock(Blocks.MUD);
    public static final RegistryObject<Block> MUD_WALL = createWallBlock(Blocks.MUD);

    public static final RegistryObject<Block> REDSTONE_BLOCK_SLAB = BLOCKS.register("redstone_block" + "_slab", () -> new RedstoneSlabBlock(getSettingsFromBlock(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<Block> REDSTONE_BLOCK_STAIRS = BLOCKS.register("redstone_block" + "_stairs", () -> new RedstoneStairsBlock(getSettingsFromBlock(Blocks.REDSTONE_BLOCK)));
    //public static final RegistryObject<Block> REDSTONE_BLOCK_WALL = createWallBlock(Blocks.REDSTONE_BLOCK);

    public static final RegistryObject<Block> COPPER_BLOCK_SLAB = createOxidizedSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, Blocks.COPPER_BLOCK);
    public static final RegistryObject<Block> COPPER_BLOCK_STAIRS = createOxidizedStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, Blocks.COPPER_BLOCK);
    public static final RegistryObject<Block> COPPER_BLOCK_WALL = createOxidizedWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, Blocks.COPPER_BLOCK);

    public static final RegistryObject<Block> EXPOSED_COPPER_SLAB = createOxidizedSlabBlock(Oxidizable.OxidationLevel.EXPOSED, Blocks.EXPOSED_COPPER);
    public static final RegistryObject<Block> EXPOSED_COPPER_STAIRS = createOxidizedStairsBlock(Oxidizable.OxidationLevel.EXPOSED, Blocks.EXPOSED_COPPER);
    public static final RegistryObject<Block> EXPOSED_COPPER_WALL = createOxidizedWallBlock(Oxidizable.OxidationLevel.EXPOSED, Blocks.EXPOSED_COPPER);

    public static final RegistryObject<Block> WEATHERED_COPPER_SLAB = createOxidizedSlabBlock(Oxidizable.OxidationLevel.WEATHERED, Blocks.WEATHERED_COPPER);
    public static final RegistryObject<Block> WEATHERED_COPPER_STAIRS = createOxidizedStairsBlock(Oxidizable.OxidationLevel.WEATHERED, Blocks.WEATHERED_COPPER);
    public static final RegistryObject<Block> WEATHERED_COPPER_WALL = createOxidizedWallBlock(Oxidizable.OxidationLevel.WEATHERED, Blocks.WEATHERED_COPPER);

    public static final RegistryObject<Block> OXIDIZED_COPPER_SLAB = createOxidizedSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, Blocks.OXIDIZED_COPPER);
    public static final RegistryObject<Block> OXIDIZED_COPPER_STAIRS = createOxidizedStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, Blocks.OXIDIZED_COPPER);
    public static final RegistryObject<Block> OXIDIZED_COPPER_WALL = createOxidizedWallBlock(Oxidizable.OxidationLevel.OXIDIZED, Blocks.OXIDIZED_COPPER);

    public static final RegistryObject<Block> WAXED_COPPER_BLOCK_SLAB = createSlabBlock("waxed_copper", Blocks.WAXED_COPPER_BLOCK);
    public static final RegistryObject<Block> WAXED_COPPER_BLOCK_STAIRS = createStairsBlock("waxed_copper", Blocks.WAXED_COPPER_BLOCK);
    public static final RegistryObject<Block> WAXED_COPPER_BLOCK_WALL = createWallBlock("waxed_copper", Blocks.WAXED_COPPER_BLOCK);

    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SLAB = createSlabBlock((Blocks.WAXED_EXPOSED_COPPER));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_STAIRS = createStairsBlock(Blocks.WAXED_EXPOSED_COPPER);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_WALL = createWallBlock(Blocks.WAXED_EXPOSED_COPPER);

    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SLAB = createSlabBlock((Blocks.WAXED_WEATHERED_COPPER));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_STAIRS = createStairsBlock(Blocks.WAXED_WEATHERED_COPPER);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_WALL = createWallBlock(Blocks.WAXED_WEATHERED_COPPER);

    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SLAB = createSlabBlock((Blocks.WAXED_OXIDIZED_COPPER));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_STAIRS = createStairsBlock(Blocks.WAXED_OXIDIZED_COPPER);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_WALL = createWallBlock(Blocks.WAXED_OXIDIZED_COPPER);

    public static final RegistryObject<Block> DIRT_PATH_SLAB = BLOCKS.register("dirt_path" + "_slab", () -> new PathBlockSlab(getSettingsFromBlock(Blocks.DIRT_PATH)));
    public static final RegistryObject<Block> DIRT_PATH_STAIRS = BLOCKS.register("dirt_path" + "_stairs", () -> new PathBlockStairs(getSettingsFromBlock(Blocks.DIRT_PATH)));
    public static final RegistryObject<Block> DIRT_PATH_WALL = BLOCKS.register("dirt_path" + "_wall", () -> new PathBlockWall(getSettingsFromBlock(Blocks.DIRT_PATH)));

    public static final RegistryObject<Block> STRIPPED_OAK_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_OAK_LOG);
    public static final RegistryObject<Block> STRIPPED_OAK_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_OAK_LOG);
    public static final RegistryObject<Block> STRIPPED_OAK_LOG_WALL = createWallBlock(Blocks.STRIPPED_OAK_LOG);

    public static final RegistryObject<Block> OAK_LOG_SLAB = createLogSlabBlock(STRIPPED_OAK_LOG_SLAB, Blocks.OAK_LOG);
    public static final RegistryObject<Block> OAK_LOG_STAIRS = createLogStairsBlock(STRIPPED_OAK_LOG_STAIRS, Blocks.OAK_LOG);
    public static final RegistryObject<Block> OAK_LOG_WALL = createLogWallBlock(STRIPPED_OAK_LOG_WALL, Blocks.OAK_LOG);

    public static final RegistryObject<Block> STRIPPED_BIRCH_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_BIRCH_LOG);
    public static final RegistryObject<Block> STRIPPED_BIRCH_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_BIRCH_LOG);
    public static final RegistryObject<Block> STRIPPED_BIRCH_LOG_WALL = createWallBlock(Blocks.STRIPPED_BIRCH_LOG);

    public static final RegistryObject<Block> BIRCH_LOG_SLAB = createLogSlabBlock(STRIPPED_BIRCH_LOG_SLAB, Blocks.BIRCH_LOG);
    public static final RegistryObject<Block> BIRCH_LOG_STAIRS = createLogStairsBlock(STRIPPED_BIRCH_LOG_STAIRS, Blocks.BIRCH_LOG);
    public static final RegistryObject<Block> BIRCH_LOG_WALL = createLogWallBlock(STRIPPED_BIRCH_LOG_WALL, Blocks.BIRCH_LOG);

    public static final RegistryObject<Block> STRIPPED_SPRUCE_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_SPRUCE_LOG);
    public static final RegistryObject<Block> STRIPPED_SPRUCE_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_SPRUCE_LOG);
    public static final RegistryObject<Block> STRIPPED_SPRUCE_LOG_WALL = createWallBlock(Blocks.STRIPPED_SPRUCE_LOG);

    public static final RegistryObject<Block> SPRUCE_LOG_SLAB = createLogSlabBlock(STRIPPED_SPRUCE_LOG_SLAB, Blocks.SPRUCE_LOG);
    public static final RegistryObject<Block> SPRUCE_LOG_STAIRS = createLogStairsBlock(STRIPPED_SPRUCE_LOG_STAIRS, Blocks.SPRUCE_LOG);
    public static final RegistryObject<Block> SPRUCE_LOG_WALL = createLogWallBlock(STRIPPED_SPRUCE_LOG_WALL, Blocks.SPRUCE_LOG);

    public static final RegistryObject<Block> STRIPPED_JUNGLE_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_JUNGLE_LOG);
    public static final RegistryObject<Block> STRIPPED_JUNGLE_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_JUNGLE_LOG);
    public static final RegistryObject<Block> STRIPPED_JUNGLE_LOG_WALL = createWallBlock(Blocks.STRIPPED_JUNGLE_LOG);

    public static final RegistryObject<Block> JUNGLE_LOG_SLAB = createLogSlabBlock(STRIPPED_JUNGLE_LOG_SLAB, Blocks.JUNGLE_LOG);
    public static final RegistryObject<Block> JUNGLE_LOG_STAIRS = createLogStairsBlock(STRIPPED_JUNGLE_LOG_STAIRS, Blocks.JUNGLE_LOG);
    public static final RegistryObject<Block> JUNGLE_LOG_WALL = createLogWallBlock(STRIPPED_JUNGLE_LOG_WALL, Blocks.JUNGLE_LOG);

    public static final RegistryObject<Block> STRIPPED_DARK_OAK_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_DARK_OAK_LOG);
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_DARK_OAK_LOG);
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_LOG_WALL = createWallBlock(Blocks.STRIPPED_DARK_OAK_LOG);

    public static final RegistryObject<Block> DARK_OAK_LOG_SLAB = createLogSlabBlock(STRIPPED_DARK_OAK_LOG_SLAB, Blocks.DARK_OAK_LOG);
    public static final RegistryObject<Block> DARK_OAK_LOG_STAIRS = createLogStairsBlock(STRIPPED_DARK_OAK_LOG_STAIRS, Blocks.DARK_OAK_LOG);
    public static final RegistryObject<Block> DARK_OAK_LOG_WALL = createLogWallBlock(STRIPPED_DARK_OAK_LOG_WALL, Blocks.DARK_OAK_LOG);

    public static final RegistryObject<Block> STRIPPED_ACACIA_LOG_SLAB = createSlabBlock(Blocks.STRIPPED_ACACIA_LOG);
    public static final RegistryObject<Block> STRIPPED_ACACIA_LOG_STAIRS = createStairsBlock(Blocks.STRIPPED_ACACIA_LOG);
    public static final RegistryObject<Block> STRIPPED_ACACIA_LOG_WALL = createWallBlock(Blocks.STRIPPED_ACACIA_LOG);

    public static final RegistryObject<Block> ACACIA_LOG_SLAB = createLogSlabBlock(STRIPPED_ACACIA_LOG_SLAB, Blocks.ACACIA_LOG);
    public static final RegistryObject<Block> ACACIA_LOG_STAIRS = createLogStairsBlock(STRIPPED_ACACIA_LOG_STAIRS, Blocks.ACACIA_LOG);
    public static final RegistryObject<Block> ACACIA_LOG_WALL = createLogWallBlock(STRIPPED_ACACIA_LOG_WALL, Blocks.ACACIA_LOG);

    public static final RegistryObject<Block> STRIPPED_CRIMSON_STEM_SLAB = createSlabBlock(Blocks.STRIPPED_CRIMSON_STEM);
    public static final RegistryObject<Block> STRIPPED_CRIMSON_STEM_STAIRS = createStairsBlock(Blocks.STRIPPED_CRIMSON_STEM);
    public static final RegistryObject<Block> STRIPPED_CRIMSON_STEM_WALL = createWallBlock(Blocks.STRIPPED_CRIMSON_STEM);

    public static final RegistryObject<Block> CRIMSON_STEM_SLAB = createLogSlabBlock(STRIPPED_CRIMSON_STEM_SLAB, Blocks.CRIMSON_STEM);
    public static final RegistryObject<Block> CRIMSON_STEM_STAIRS = createLogStairsBlock(STRIPPED_CRIMSON_STEM_STAIRS, Blocks.CRIMSON_STEM);
    public static final RegistryObject<Block> CRIMSON_STEM_WALL = createLogWallBlock(STRIPPED_CRIMSON_STEM_WALL, Blocks.CRIMSON_STEM);

    public static final RegistryObject<Block> STRIPPED_WARPED_STEM_SLAB = createSlabBlock(Blocks.STRIPPED_WARPED_STEM);
    public static final RegistryObject<Block> STRIPPED_WARPED_STEM_STAIRS = createStairsBlock(Blocks.STRIPPED_WARPED_STEM);
    public static final RegistryObject<Block> STRIPPED_WARPED_STEM_WALL = createWallBlock(Blocks.STRIPPED_WARPED_STEM);

    public static final RegistryObject<Block> WARPED_STEM_SLAB = createLogSlabBlock(STRIPPED_WARPED_STEM_SLAB, Blocks.WARPED_STEM);
    public static final RegistryObject<Block> WARPED_STEM_STAIRS = createLogStairsBlock(STRIPPED_WARPED_STEM_STAIRS, Blocks.WARPED_STEM);
    public static final RegistryObject<Block> WARPED_STEM_WALL = createLogWallBlock(STRIPPED_WARPED_STEM_WALL, Blocks.WARPED_STEM);




    public static String getNameFromBlock(Block block){
        String[] nameParts = block.getTranslationKey().toLowerCase().split("\\.");
        return nameParts[nameParts.length-1];
    }



    public static AbstractBlock.Settings getSettingsFromBlock(Block block){

        AbstractBlock.Settings settings = AbstractBlock.Settings.of(block.getDefaultState().getMaterial())
                .sounds(block.getDefaultState().getSoundGroup())
                .luminance((view) -> block.getDefaultState().getLuminance())
                .mapColor(block.getDefaultMapColor())
                .hardness(block.getHardness())
                .resistance(block.getBlastResistance())
                .velocityMultiplier(block.getVelocityMultiplier())
                .slipperiness(block.getSlipperiness())

                ;


        if (!block.getDefaultState().isOpaque()){
            settings = settings.nonOpaque();
        }
        if (block.getDefaultState().isToolRequired()){
            settings = settings.requiresTool();
        }

        if (block.getDefaultState().hasRandomTicks()){
            settings = settings.ticksRandomly();
        }

        return settings;


    }

    public static RegistryObject<Block> createSlabBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new SlabBlock(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createStairsBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new StairsBlock(copyBlock::getDefaultState, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createWallBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new WallBlock(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createSlabBlock(String name, Block copyBlock){
        return BLOCKS.register(name + "_slab", () -> new SlabBlock(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createStairsBlock(String name,Block copyBlock){
        return BLOCKS.register(name + "_stairs", () -> new StairsBlock(name.contains("leaves") ? Blocks.STONE::getDefaultState : copyBlock::getDefaultState, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createWallBlock(String name,Block copyBlock){
        return BLOCKS.register(name + "_wall", () -> new WallBlock(getSettingsFromBlock(copyBlock)));
    }


    public static RegistryObject<Block> createCulledSlabBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new CulledSlabBlock(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createCulledStairsBlock(Block copyBlock) {
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new CulledStairsBlock(copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createCoralSlabBlock(RegistryObject<Block> deadCoralBlock, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new CoralSlabBlock(deadCoralBlock, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createCoralStairsBlock(RegistryObject<Block> deadCoralBlock,Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new CoralStairsBlock(deadCoralBlock, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createCoralWallBlock(RegistryObject<Block> deadCoralBlock, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new CoralWallBlock(deadCoralBlock, getSettingsFromBlock(copyBlock)));
    }
    
    public static RegistryObject<Block> createSpreadableSlabBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new SpreadableSlabBlock(getSettingsFromBlock(copyBlock).ticksRandomly()));
    }

    public static RegistryObject<Block> createSpreadableStairsBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new SpreadableStairsBlock(copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock).ticksRandomly()));
    }

    public static RegistryObject<Block> createSpreadableWallBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new SpreadableWallBlock(getSettingsFromBlock(copyBlock).ticksRandomly()));
    }

    public static RegistryObject<Block> createOxidizedSlabBlock(Oxidizable.OxidationLevel level, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new OxidizableSlabBlock(level, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createOxidizedStairsBlock(Oxidizable.OxidationLevel level, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new OxidizableStairsBlock(level, copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createOxidizedWallBlock(Oxidizable.OxidationLevel level, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new OxidizableWallBlock(level, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createDirtSlabBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new DirtSlab(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createDirtStairsBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new DirtStairs(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createDirtWallBlock(Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new DirtWall(getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createLogSlabBlock(RegistryObject<Block> strippedBlock, Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_slab", () -> new StrippableSlabBlock(strippedBlock, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createLogStairsBlock(RegistryObject<Block> strippedBlock,Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_stairs", () -> new StrippableStairsBlock(strippedBlock, getSettingsFromBlock(copyBlock)));
    }

    public static RegistryObject<Block> createLogWallBlock(RegistryObject<Block> strippedBlock,Block copyBlock){
        return BLOCKS.register(getNameFromBlock(copyBlock) + "_wall", () -> new StrippableWallBlock(strippedBlock, getSettingsFromBlock(copyBlock)));
    }

    
}
