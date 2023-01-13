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
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

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


        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_BLOCK.getSlabBlock(), ModBlocks.EXPOSED_COPPER.getSlabBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER.getSlabBlock(), ModBlocks.WEATHERED_COPPER.getSlabBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER.getSlabBlock(), ModBlocks.OXIDIZED_COPPER.getSlabBlock());

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_BLOCK.getSlabBlock(), ModBlocks.WAXED_COPPER.getSlabBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER.getSlabBlock(), ModBlocks.WAXED_EXPOSED_COPPER.getSlabBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER.getSlabBlock(), ModBlocks.WAXED_WEATHERED_COPPER.getSlabBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER.getSlabBlock(), ModBlocks.WAXED_OXIDIZED_COPPER.getSlabBlock());

        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_BLOCK.getStairsBlock(), ModBlocks.EXPOSED_COPPER.getStairsBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER.getStairsBlock(), ModBlocks.WEATHERED_COPPER.getStairsBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER.getStairsBlock(), ModBlocks.OXIDIZED_COPPER.getStairsBlock());

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_BLOCK.getStairsBlock(), ModBlocks.WAXED_COPPER.getStairsBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER.getStairsBlock(), ModBlocks.WAXED_EXPOSED_COPPER.getStairsBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER.getStairsBlock(), ModBlocks.WAXED_WEATHERED_COPPER.getStairsBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER.getStairsBlock(), ModBlocks.WAXED_OXIDIZED_COPPER.getStairsBlock());

        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_BLOCK.getWallBlock(), ModBlocks.EXPOSED_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER.getWallBlock(), ModBlocks.WEATHERED_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER.getWallBlock(), ModBlocks.OXIDIZED_COPPER.getWallBlock());

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_BLOCK.getWallBlock(), ModBlocks.WAXED_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER.getWallBlock(), ModBlocks.WAXED_EXPOSED_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER.getWallBlock(), ModBlocks.WAXED_WEATHERED_COPPER.getWallBlock());
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER.getWallBlock(), ModBlocks.WAXED_OXIDIZED_COPPER.getWallBlock());
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
        Item item = Registry.register(Registries.ITEM, block.getIdentifier(type), new BlockItem(blockItem, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(MoreSlabsStairsAndWalls.modGroup).register(entries -> entries.add(item));
    }

    public static AbstractBlock.Settings getSettingsFromBlock(Block block){
        AbstractBlock.Settings settings = AbstractBlock.Settings.of(block.getDefaultState().getMaterial())
                .sounds(block.getDefaultState().getSoundGroup())
                .luminance((view) -> block.getDefaultState().getLuminance())
                .mapColor(block.getDefaultMapColor())
                .hardness(block.getHardness())
                .resistance(block.getBlastResistance())
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

    public static void registerSlab(ModBlocks block, Block copyBlock){
        Block slab = switch (block){
            case GLASS -> new CulledSlabBlock(getSettingsFromBlock(copyBlock));
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
                    BLUE_STAINED_GLASS -> new CulledStainedSlabBlock(block.getDyeColor(), getSettingsFromBlock(copyBlock));
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralSlabBlock(block.deadCoralBlock.getSlabBlock(), getSettingsFromBlock(copyBlock));
            case SLIME_BLOCK -> new SlimeBlockSlab(getSettingsFromBlock(copyBlock));
            case HONEY_BLOCK -> new HoneyBlockSlab(getSettingsFromBlock(copyBlock));

            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableSlabBlock(getSettingsFromBlock(copyBlock));
            case REDSTONE_BLOCK -> new RedstoneSlabBlock(getSettingsFromBlock(copyBlock));

            case COPPER_BLOCK -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, getSettingsFromBlock(copyBlock));
            case EXPOSED_COPPER -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, getSettingsFromBlock(copyBlock));
            case WEATHERED_COPPER -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, getSettingsFromBlock(copyBlock));
            case OXIDIZED_COPPER -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, getSettingsFromBlock(copyBlock));


            default -> new SlabBlock(getSettingsFromBlock(copyBlock));
        };

        registerItem(block, slab, ModBlocks.BlockType.SLAB);
        SLABS.put(block, slab);
        Registry.register(Registries.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_slab"), slab);
    }

    public static void registerStairs(ModBlocks block, Block copyBlock){
        StairsBlock stairs = switch (block){
            case GLASS -> new CulledStairsBlock(copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock));
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
                    BLUE_STAINED_GLASS -> new CulledStainedStairsBlock(copyBlock.getDefaultState(), block.getDyeColor(), getSettingsFromBlock(copyBlock));
            case OAK_LEAVES,
                    ACACIA_LEAVES,
                    BIRCH_LEAVES,
                    DARK_OAK_LEAVES,
                    JUNGLE_LEAVES,
                    MANGROVE_LEAVES,
                    SPRUCE_LEAVES,
                    AZALEA_LEAVES,
                    FLOWERING_AZALEA_LEAVES -> new StairsBlock(Blocks.GRASS_BLOCK.getDefaultState(),getSettingsFromBlock(copyBlock));
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralStairsBlock(Objects.requireNonNull(block.deadCoralBlock.getStairsBlock()), getSettingsFromBlock(copyBlock));
            case SLIME_BLOCK -> new SlimeBlockStairs(getSettingsFromBlock(copyBlock));
            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableStairsBlock(copyBlock.getDefaultState(),getSettingsFromBlock(copyBlock));
            case COPPER_BLOCK -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock));
            case EXPOSED_COPPER -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock));
            case WEATHERED_COPPER -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock));
            case OXIDIZED_COPPER -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, copyBlock.getDefaultState(), getSettingsFromBlock(copyBlock));
            case REDSTONE_BLOCK -> new RedstoneStairsBlock(getSettingsFromBlock(copyBlock));
            default -> new StairsBlock(copyBlock.getDefaultState(),getSettingsFromBlock(copyBlock));
        };

        registerItem(block, stairs, ModBlocks.BlockType.STAIRS);
        STAIRS.put(block, stairs);
        Registry.register(Registries.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_stairs"), stairs);
    }

    public static void registerWall(ModBlocks block, Block copyBlock){
        WallBlock wall = switch (block){
            case BUBBLE_CORAL_BLOCK,
                    HORN_CORAL_BLOCK,
                    BRAIN_CORAL_BLOCK,
                    FIRE_CORAL_BLOCK,
                    TUBE_CORAL_BLOCK -> new CoralWallBlock(block.deadCoralBlock.getWallBlock(), getSettingsFromBlock(copyBlock));
            case GRASS_BLOCK,
                    MYCELIUM,
                    CRIMSON_NYLIUM,
                    WARPED_NYLIUM -> new SpreadableWallBlock(getSettingsFromBlock(copyBlock));
            case SPRUCE_LOG,
                    ACACIA_LOG,
                    BIRCH_LOG,
                    DARK_OAK_LOG,
                    JUNGLE_LOG,
                    MANGROVE_LOG,
                    OAK_LOG,
                    WARPED_STEM,
                    CRIMSON_STEM, BONE_BLOCK ->  new WallBlock(getSettingsFromBlock(copyBlock));
            case SLIME_BLOCK -> new SlimeBlockWall(getSettingsFromBlock(copyBlock));
            case CUT_COPPER,
                    COPPER_BLOCK -> new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, getSettingsFromBlock(copyBlock));
            case EXPOSED_CUT_COPPER,
                    EXPOSED_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, getSettingsFromBlock(copyBlock));
            case WEATHERED_CUT_COPPER,
                    WEATHERED_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, getSettingsFromBlock(copyBlock));
            case OXIDIZED_CUT_COPPER,
                    OXIDIZED_COPPER -> new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, getSettingsFromBlock(copyBlock));
            //case REDSTONE -> new RedstoneWallBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.STONE).luminance((i) -> block.getLuminance()));
            default -> new WallBlock(getSettingsFromBlock(copyBlock));
        };


        registerItem(block, wall, ModBlocks.BlockType.WALL);
        WALLS.put(block, wall);
        Registry.register(Registries.BLOCK, new Identifier(MoreSlabsStairsAndWalls.mod_id, block.toString().toLowerCase() + "_wall"), wall);
    }
}
