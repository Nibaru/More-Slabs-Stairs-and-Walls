package games.twinhead.moreslabsstairsandwalls.registry;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.*;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.coral.CoralWallBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStainedSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStainedStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStairsBlock;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneSlabBlock;
import games.twinhead.moreslabsstairsandwalls.block.redstone.RedstoneStairsBlock;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Objects;

public class BlockRegistry {
    public static HashMap<ModBlocks, Block> SLABS = new HashMap<>();
    public static HashMap<ModBlocks, Block> STAIRS = new HashMap<>();
    public static HashMap<ModBlocks, Block> WALLS = new HashMap<>();

    public static void register(){
        for (ModBlocks block: ModBlocks.values()) {
            registerBlock(block, block.getCopyBlock(), block.hasSlab, block.hasStairs, block.hasWall);
        }
        registerOxidizable();
    }

    public static void registerOxidizable(){
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.CUT_COPPER.getWallBlock(), ModBlocks.EXPOSED_CUT_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_CUT_COPPER.getWallBlock(), ModBlocks.WEATHERED_CUT_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_CUT_COPPER.getWallBlock(), ModBlocks.OXIDIZED_CUT_COPPER.getWallBlock());

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.CUT_COPPER.getWallBlock(), ModBlocks.WAXED_CUT_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_CUT_COPPER.getWallBlock(), ModBlocks.WAXED_EXPOSED_CUT_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_CUT_COPPER.getWallBlock(), ModBlocks.WAXED_WEATHERED_CUT_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_CUT_COPPER.getWallBlock(), ModBlocks.WAXED_OXIDIZED_CUT_COPPER.getWallBlock());
    }


    public static void registerBlock(ModBlocks block, Block copyBlock, boolean slab, boolean stairs, boolean wall){
        if(slab)
            registerSlab(block, copyBlock);
        if(stairs)
            registerStairs(block, copyBlock);
        if(wall)
            registerWall(block, copyBlock);
    }

    public static void registerItem(ModBlocks block, Block blockItem, ModBlocks.BlockType type){
        Registry.register(Registry.ITEM, block.getIdentifier(type), new BlockItem(blockItem, new Item.Settings().group(MoreSlabsStairsAndWalls.modGroup)));
    }

    public static void registerSlab(ModBlocks block, Block copyBlock){
        Block slab = switch (block){
            case GLASS -> new CulledSlabBlock(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case WHITE_STAINED_GLASS,
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
                    BLUE_STAINED_GLASS -> new CulledStainedSlabBlock(block.getDyeColor(), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralSlabBlock(block.deadCoralBlock.getSlabBlock(), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case SLIME_BLOCK -> new SlimeBlockSlab(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case HONEY_BLOCK -> new HoneyBlockSlab(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));

            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableSlabBlock(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case SPRUCE_LOG,
                    ACACIA_LOG,
                    BIRCH_LOG,
                    DARK_OAK_LOG,
                    JUNGLE_LOG,
                    MANGROVE_LOG,
                    OAK_LOG,
                    WARPED_STEM,
                    CRIMSON_STEM ->  new SlabBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).luminance((i) -> block.getLuminance()));
            case BONE_BLOCK -> new SlabBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).sounds(BlockSoundGroup.BONE).luminance((i) -> block.getLuminance()));
            case REDSTONE_BLOCK -> new RedstoneSlabBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.STONE).luminance((i) -> block.getLuminance()));
            default -> new SlabBlock(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
        };

        registerItem(block, slab, ModBlocks.BlockType.SLAB);
        SLABS.put(block, slab);
        Registry.register(Registry.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_slab"), slab);
    }

    public static void registerStairs(ModBlocks block, Block copyBlock){
        StairsBlock stairs = switch (block){
            case GLASS -> new CulledStairsBlock(copyBlock.getDefaultState(), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case    WHITE_STAINED_GLASS,
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
                    BLUE_STAINED_GLASS -> new CulledStainedStairsBlock(copyBlock.getDefaultState(), block.getDyeColor(), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case OAK_LEAVES,
                    ACACIA_LEAVES,
                    BIRCH_LEAVES,
                    DARK_OAK_LEAVES,
                    JUNGLE_LEAVES,
                    MANGROVE_LEAVES,
                    SPRUCE_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES -> new StairsBlock(Blocks.GRASS_BLOCK.getDefaultState(),AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralStairsBlock(Objects.requireNonNull(block.deadCoralBlock.getStairsBlock()), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case SLIME_BLOCK -> new SlimeBlockStairs(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableStairsBlock(copyBlock.getDefaultState(),AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()).ticksRandomly());
            case SPRUCE_LOG,
                    ACACIA_LOG,
                    BIRCH_LOG,
                    DARK_OAK_LOG,
                    JUNGLE_LOG,
                    MANGROVE_LOG,
                    OAK_LOG,
                    WARPED_STEM,
                    CRIMSON_STEM ->  new StairsBlock(copyBlock.getDefaultState(), AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).luminance((i) -> block.getLuminance()));

            case BONE_BLOCK -> new StairsBlock(copyBlock.getDefaultState(), AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).sounds(BlockSoundGroup.BONE).luminance((i) -> block.getLuminance()));
            case REDSTONE_BLOCK -> new RedstoneStairsBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.STONE).luminance((i) -> block.getLuminance()));
            default -> new StairsBlock(copyBlock.getDefaultState(),AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
        };

        registerItem(block, stairs, ModBlocks.BlockType.STAIRS);
        STAIRS.put(block, stairs);
        Registry.register(Registry.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_stairs"), stairs);
    }

    public static void registerWall(ModBlocks block, Block copyBlock){
        WallBlock wall = switch (block){
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralWallBlock(block.deadCoralBlock.getWallBlock(), AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableWallBlock(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()).ticksRandomly());
            case SPRUCE_LOG,
                    ACACIA_LOG,
                    BIRCH_LOG,
                    DARK_OAK_LOG,
                    JUNGLE_LOG,
                    MANGROVE_LOG,
                    OAK_LOG,
                    WARPED_STEM,
                    CRIMSON_STEM ->  new WallBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).luminance((i) -> block.getLuminance()));
            case CUT_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(copyBlock));
            case EXPOSED_CUT_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(copyBlock));
            case WEATHERED_CUT_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(copyBlock));
            case OXIDIZED_CUT_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(copyBlock));
            case BONE_BLOCK -> new WallBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).sounds(BlockSoundGroup.BONE).luminance((i) -> block.getLuminance()));
            //case REDSTONE -> new RedstoneWallBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.STONE).luminance((i) -> block.getLuminance()));
            default -> new WallBlock(AbstractBlock.Settings.copy(copyBlock).luminance((i) -> block.getLuminance()));
        };


        registerItem(block, wall, ModBlocks.BlockType.WALL);
        WALLS.put(block, wall);
        Registry.register(Registry.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_wall"), wall);
    }
}
