package games.twinhead.moreslabsstairsandwalls.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseStairs;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseWall;
import games.twinhead.moreslabsstairsandwalls.block.concretepowder.ConcretePowderSlab;
import games.twinhead.moreslabsstairsandwalls.block.concretepowder.ConcretePowderStairs;
import games.twinhead.moreslabsstairsandwalls.block.concretepowder.ConcretePowderWall;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralSlab;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralStairs;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralWall;
import games.twinhead.moreslabsstairsandwalls.block.dirt.*;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingSlab;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingStairs;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingWall;
import games.twinhead.moreslabsstairsandwalls.block.honey.HoneySlab;
import games.twinhead.moreslabsstairsandwalls.block.honey.HoneyStairs;
import games.twinhead.moreslabsstairsandwalls.block.honey.HoneyWall;
import games.twinhead.moreslabsstairsandwalls.block.ice.IceSlab;
import games.twinhead.moreslabsstairsandwalls.block.ice.IceStairs;
import games.twinhead.moreslabsstairsandwalls.block.leaves.LeavesSlab;
import games.twinhead.moreslabsstairsandwalls.block.leaves.LeavesStairs;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaSlab;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaStairs;
import games.twinhead.moreslabsstairsandwalls.block.magma.MagmaWall;
import games.twinhead.moreslabsstairsandwalls.block.oxidizable.*;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneSlab;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneStairs;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneWall;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeSlab;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeStairs;
import games.twinhead.moreslabsstairsandwalls.block.slime.SlimeWall;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandSlab;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandStairs;
import games.twinhead.moreslabsstairsandwalls.block.soulsand.SoulSandWall;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableSlab;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableStairs;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.SpreadableWall;
import games.twinhead.moreslabsstairsandwalls.block.strippable.StrippableSlab;
import games.twinhead.moreslabsstairsandwalls.block.strippable.StrippableStairs;
import games.twinhead.moreslabsstairsandwalls.block.strippable.StrippableWall;
import games.twinhead.moreslabsstairsandwalls.block.terracotta.GlazedTerracottaSlab;
import games.twinhead.moreslabsstairsandwalls.block.terracotta.GlazedTerracottaStairs;
import games.twinhead.moreslabsstairsandwalls.block.terracotta.GlazedTerracottaWall;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentStairs;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.entity.EntityType;

public class ModRegistry {

    @ExpectPlatform
    public static EntityType<FallingSlabBlockEntity> getFallingSlabEntityType(){
        throw new AssertionError();
    }

    public static Block getBlock(ModBlocks block, ModBlocks.BlockType type)
    {
        return switch (block) {
            case GRASS_BLOCK, MYCELIUM -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new SpreadableSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new SpreadableStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new SpreadableWall(block, block.getSettings()) : null);
            };

            case DIRT, COARSE_DIRT, ROOTED_DIRT -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new DirtSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new DirtStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new DirtWall(block, block.getSettings()) : null);
            };
            case DIRT_PATH -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new PathSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new PathStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new PathWall(block, block.getSettings()) : null);
            };

            case BRAIN_CORAL_BLOCK, BUBBLE_CORAL_BLOCK, FIRE_CORAL_BLOCK, HORN_CORAL_BLOCK, TUBE_CORAL_BLOCK -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new CoralSlab(block, block.associatedBlock, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new CoralStairs(block, block.parentBlock.getDefaultState(), block.associatedBlock,block.getSettings()) : null);
                case WALL ->(block.hasWall ? new CoralWall(block, block.associatedBlock, block.getSettings()) : null);
            };
            case OAK_LOG,
                    OAK_WOOD,
                    SPRUCE_LOG,
                    SPRUCE_WOOD,
                    BIRCH_LOG,
                    BIRCH_WOOD,
                    JUNGLE_LOG,
                    JUNGLE_WOOD,
                    ACACIA_LOG,
                    ACACIA_WOOD,
                    DARK_OAK_LOG,
                    DARK_OAK_WOOD,
                    MANGROVE_LOG,
                    MANGROVE_WOOD,
                    WARPED_STEM,
                    WARPED_HYPHAE,
                    CRIMSON_STEM,
                    CRIMSON_HYPHAE,
                    CHERRY_LOG,
                    CHERRY_WOOD,
                    BAMBOO_BLOCK-> switch (type)
            {
                case SLAB ->(block.hasSlab ? new StrippableSlab(block, block.associatedBlock, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new StrippableStairs(block,  block.parentBlock.getDefaultState(), block.associatedBlock, block.getSettings()) : null);
                case WALL ->(block.hasWall ? new StrippableWall(block, block.associatedBlock, block.getSettings()) : null);
            };

            case OAK_LEAVES,
                    SPRUCE_LEAVES,
                    BIRCH_LEAVES,
                    JUNGLE_LEAVES,
                    ACACIA_LEAVES,
                    DARK_OAK_LEAVES,
                    MANGROVE_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES,
                    CRIMSON_WART,
                    WARPED_WART,
                    CHERRY_LEAVES-> switch (type)
            {
                case SLAB ->(block.hasSlab ? new LeavesSlab(block,  block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new LeavesStairs(block,  block.parentBlock.getDefaultState(),  block.getSettings()) : null);
                case WALL ->(block.hasWall ? new BaseWall(block,  block.getSettings()) : null);
            };
            case GLASS,
                    WHITE_STAINED_GLASS,
                    YELLOW_STAINED_GLASS,
                    BLACK_STAINED_GLASS,
                    RED_STAINED_GLASS,
                    PURPLE_STAINED_GLASS,
                    PINK_STAINED_GLASS,
                    ORANGE_STAINED_GLASS,
                    MAGENTA_STAINED_GLASS,
                    LIME_STAINED_GLASS,
                    LIGHT_GRAY_STAINED_GLASS,
                    LIGHT_BLUE_STAINED_GLASS,
                    GREEN_STAINED_GLASS,
                    GRAY_STAINED_GLASS,
                    CYAN_STAINED_GLASS,
                    BROWN_STAINED_GLASS,
                    BLUE_STAINED_GLASS
                    -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new TranslucentSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new TranslucentStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new BaseWall(block, block.getSettings()) : null);
            };

            case SAND,
                    GRAVEL,
                    RED_SAND -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new FallingSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new FallingStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new FallingWall(block, block.getSettings()) : null);
            };
            case BLACK_CONCRETE_POWDER,
                    BLUE_CONCRETE_POWDER,
                    BROWN_CONCRETE_POWDER,
                    CYAN_CONCRETE_POWDER,
                    GRAY_CONCRETE_POWDER,
                    GREEN_CONCRETE_POWDER,
                    LIGHT_BLUE_CONCRETE_POWDER,
                    LIGHT_GRAY_CONCRETE_POWDER,
                    LIME_CONCRETE_POWDER,
                    MAGENTA_CONCRETE_POWDER,
                    ORANGE_CONCRETE_POWDER,
                    PINK_CONCRETE_POWDER,
                    PURPLE_CONCRETE_POWDER,
                    RED_CONCRETE_POWDER,
                    WHITE_CONCRETE_POWDER,
                    YELLOW_CONCRETE_POWDER -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new ConcretePowderSlab(block, block.associatedBlock, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new ConcretePowderStairs(block, block.parentBlock.getDefaultState(), block.associatedBlock, block.getSettings()) : null);
                case WALL ->(block.hasWall ? new ConcretePowderWall(block, block.associatedBlock, block.getSettings()) : null);
            };

            case WHITE_GLAZED_TERRACOTTA,
                    YELLOW_GLAZED_TERRACOTTA,
                    BLACK_GLAZED_TERRACOTTA,
                    RED_GLAZED_TERRACOTTA,
                    PURPLE_GLAZED_TERRACOTTA,
                    PINK_GLAZED_TERRACOTTA,
                    ORANGE_GLAZED_TERRACOTTA,
                    MAGENTA_GLAZED_TERRACOTTA,
                    LIME_GLAZED_TERRACOTTA,
                    LIGHT_GRAY_GLAZED_TERRACOTTA,
                    LIGHT_BLUE_GLAZED_TERRACOTTA,
                    GREEN_GLAZED_TERRACOTTA,
                    GRAY_GLAZED_TERRACOTTA,
                    CYAN_GLAZED_TERRACOTTA,
                    BROWN_GLAZED_TERRACOTTA,
                    BLUE_GLAZED_TERRACOTTA -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new GlazedTerracottaSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new GlazedTerracottaStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new GlazedTerracottaWall(block, block.getSettings()) : null);
            };

            case OXIDIZED_COPPER,
                    WEATHERED_COPPER,
                    EXPOSED_COPPER,
                    COPPER_BLOCK,
                    OXIDIZED_CUT_COPPER,
                    WEATHERED_CUT_COPPER,
                    EXPOSED_CUT_COPPER,
                    CUT_COPPER -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new OxidizableSlab(block, block.oxidationLevel, block.associatedBlock, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new OxidizableStairs(block, block.parentBlock.getDefaultState(), block.oxidationLevel, block.associatedBlock, block.getSettings()) : null);
                case WALL ->(block.hasWall ? new OxidizableWall(block, block.oxidationLevel, block.associatedBlock, block.getSettings()) : null);
            };
            case WAXED_COPPER_BLOCK,
                    WAXED_EXPOSED_COPPER,
                    WAXED_WEATHERED_COPPER,
                    WAXED_OXIDIZED_COPPER,
                    WAXED_CUT_COPPER,
                    WAXED_EXPOSED_CUT_COPPER,
                    WAXED_WEATHERED_CUT_COPPER,
                    WAXED_OXIDIZED_CUT_COPPER -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new WaxedSlab(block, block.associatedBlock, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new WaxedStairs(block, block.parentBlock.getDefaultState(), block.associatedBlock, block.getSettings()) : null);
                case WALL ->(block.hasWall ? new WaxedWall(block, block.associatedBlock, block.getSettings()) : null);
            };

            case MAGMA_BLOCK -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new MagmaSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new MagmaStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new MagmaWall(block, block.getSettings()) : null);
            };
            case SOUL_SAND -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new SoulSandSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new SoulSandStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new SoulSandWall(block, block.getSettings()) : null);
            };
            case SLIME_BLOCK -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new SlimeSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new SlimeStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new SlimeWall(block, block.getSettings()) : null);
            };
            case HONEY_BLOCK -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new HoneySlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new HoneyStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new HoneyWall(block, block.getSettings()) : null);
            };
            case REDSTONE_BLOCK -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new RedstoneSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new RedstoneStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new RedstoneWall(block, block.getSettings()) : null);
            };

            case ICE -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new IceSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new IceStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new BaseWall(block, block.getSettings()) : null);
            };
            case OBSIDIAN, CRYING_OBSIDIAN -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new BaseSlab(block, block.getSettings().pistonBehavior(PistonBehavior.BLOCK)) : null);
                case STAIRS ->(block.hasStairs ?  new BaseStairs(block, block.parentBlock.getDefaultState(), block.getSettings().pistonBehavior(PistonBehavior.BLOCK)) : null);
                case WALL ->(block.hasWall ? new BaseWall(block, block.getSettings().pistonBehavior(PistonBehavior.BLOCK)) : null);
            };
                
            default -> switch (type)
            {
                case SLAB ->(block.hasSlab ? new BaseSlab(block, block.getSettings()) : null);
                case STAIRS ->(block.hasStairs ?  new BaseStairs(block, block.parentBlock.getDefaultState(), block.getSettings()) : null);
                case WALL ->(block.hasWall ? new BaseWall(block, block.getSettings()) : null);
            };
        };
    }

    public static BlockColorProvider getBlockColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> {
                yield switch (block) {
                    case    OAK_LEAVES,
                            JUNGLE_LEAVES,
                            ACACIA_LEAVES,
                            DARK_OAK_LEAVES,
                            MANGROVE_LEAVES -> (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
                    case SPRUCE_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getSpruceColor();
                    case BIRCH_LEAVES -> (state, world, pos, tintIndex) -> FoliageColors.getBirchColor();
                    default -> null;
                };
            }
            default -> null;
        };
    }

    public static ItemColorProvider getItemColor(ModBlocks block){
        if(block.equals(ModBlocks.GRASS_BLOCK)) return (stack, tintIndex) -> GrassColors.getColor(0.5, 1.0);
        return switch (block.modelType){
            case LEAVES -> {
                yield switch (block) {
                    case    OAK_LEAVES,
                            JUNGLE_LEAVES,
                            ACACIA_LEAVES,
                            DARK_OAK_LEAVES,
                            MANGROVE_LEAVES -> (stack, tintIndex) -> FoliageColors.getDefaultColor();
                    case SPRUCE_LEAVES -> (stack, tintIndex) -> FoliageColors.getSpruceColor();
                    case BIRCH_LEAVES -> (stack, tintIndex) -> FoliageColors.getBirchColor();
                    default -> null;
                };
            }
            default -> null;
        };
    }

    public static int getBurnChance(ModBlocks block){
        return switch (block){
            case ACACIA_LEAVES,
                    OAK_LEAVES,
                    SPRUCE_LEAVES,
                    BIRCH_LEAVES,
                    JUNGLE_LEAVES,
                    CHERRY_LEAVES,
                    DARK_OAK_LEAVES,
                    MANGROVE_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES,
                    DRIED_KELP_BLOCK,
                    BOOKSHELF -> 30;
            case HAY_BLOCK -> 60;
            default -> 5;
        };
    }

    public static int getSpreadChance(ModBlocks block){
        return switch (block){
            case ACACIA_LEAVES,
                    OAK_LEAVES,
                    SPRUCE_LEAVES,
                    BIRCH_LEAVES,
                    JUNGLE_LEAVES,
                    CHERRY_LEAVES,
                    DARK_OAK_LEAVES,
                    MANGROVE_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES,
                    DRIED_KELP_BLOCK, WHITE_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIGHT_BLUE_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL, GRAY_WOOL, LIGHT_GRAY_WOOL, CYAN_WOOL, PURPLE_WOOL, BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL -> 60;
            case OAK_LOG,
                    SPRUCE_LOG,
                    BIRCH_LOG,
                    JUNGLE_LOG,
                    ACACIA_LOG,
                    CHERRY_LOG,
                    DARK_OAK_LOG,
                    MANGROVE_LOG,
                    BAMBOO_BLOCK,
                    STRIPPED_OAK_LOG,
                    STRIPPED_SPRUCE_LOG,
                    STRIPPED_BIRCH_LOG,
                    STRIPPED_JUNGLE_LOG,
                    STRIPPED_ACACIA_LOG,
                    STRIPPED_CHERRY_LOG,
                    STRIPPED_DARK_OAK_LOG,
                    STRIPPED_MANGROVE_LOG,
                    STRIPPED_BAMBOO_BLOCK,
                    STRIPPED_OAK_WOOD,
                    STRIPPED_SPRUCE_WOOD,
                    STRIPPED_BIRCH_WOOD,
                    STRIPPED_JUNGLE_WOOD,
                    STRIPPED_ACACIA_WOOD,
                    STRIPPED_CHERRY_WOOD,
                    STRIPPED_DARK_OAK_WOOD,
                    STRIPPED_MANGROVE_WOOD,
                    OAK_WOOD,
                    SPRUCE_WOOD,
                    BIRCH_WOOD,
                    JUNGLE_WOOD,
                    ACACIA_WOOD,
                    CHERRY_WOOD,
                    DARK_OAK_WOOD,
                    MANGROVE_WOOD,
                    COAL_BLOCK-> 5;
            default -> 20;
        };
    }
}
