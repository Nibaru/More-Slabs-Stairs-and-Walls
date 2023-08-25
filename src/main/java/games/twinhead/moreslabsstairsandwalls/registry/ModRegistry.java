package games.twinhead.moreslabsstairsandwalls.registry;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableBiMap;
import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
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
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingSlab;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingStairs;
import games.twinhead.moreslabsstairsandwalls.block.falling.FallingWall;
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
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Supplier;

public class ModRegistry {


    public static final HashMap<Identifier, Block> MOD_BLOCKS = new HashMap<>();

    public static Block getBlock(Identifier id) {
        return MOD_BLOCKS.get(id);
    }

    public static ItemGroup modGroup;

    public static final Supplier<ImmutableBiMap<Object, Object>> WAX_ON = Suppliers.memoize(() -> ImmutableBiMap.builder()
            .put(ModBlocks.CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_CUT_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.WALL))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.WALL), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.WALL))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.STAIRS), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.STAIRS))

            .put(ModBlocks.COPPER_BLOCK.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_COPPER_BLOCK.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_EXPOSED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_WEATHERED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .put(ModBlocks.OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.SLAB), ModBlocks.WAXED_OXIDIZED_COPPER.getBlock(ModBlocks.BlockType.SLAB))
            .build());

    public static void registerBlocks(){
           for (ModBlocks modBlock : ModBlocks.values()) {
                addBlock(modBlock);
           }

           modGroup = Registry.register(Registries.ITEM_GROUP, new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "creative_tab"),
                   FabricItemGroup.builder()
                           .icon(() -> new ItemStack(ModBlocks.GRASS_BLOCK.getBlock(ModBlocks.BlockType.STAIRS)))
                           .displayName(Text.translatable("itemGroup.more_slabs_stairs_and_walls.creative_tab"))
                           .entries((displayContext, entries) -> {
                               for (ModBlocks block: ModBlocks.values())
                                       for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
                                           if (block.hasBlock(type)) entries.add(block.getBlock(type));


                           }
                           )
                           .build());

    }

    public static void addBlock(ModBlocks block) {
        switch (block) {
                case GRASS_BLOCK, MYCELIUM -> registerBlock(block,
                        (block.hasSlab ? new SpreadableSlab(block.getSettings()) : null),
                        (block.hasStairs ? new SpreadableStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasSlab ? new SpreadableWall(block.getSettings()) : null));
                case DIRT,
                    PODZOL-> registerBlock(block,
                        (block.hasSlab ? new DirtSlab(block.getSettings()) : null),
                        (block.hasStairs ? new DirtStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new DirtWall(block.getSettings()) : null));
                case DIRT_PATH -> registerBlock(block,
                        (block.hasSlab ? new PathSlab(block.getSettings()) : null),
                        (block.hasStairs ?  new PathStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new PathWall(block.getSettings()) : null));

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
                CRIMSON_HYPHAE-> registerBlock(block,
                        (block.hasSlab ? new StrippableSlab(block.accociatedBlock, block.getSettings()) : null),
                        (block.hasStairs ? new StrippableStairs(block.parentBlock.getDefaultState(),block.accociatedBlock, block.getSettings()) : null),
                        (block.hasWall ? new StrippableWall(block.accociatedBlock, block.getSettings()) : null));

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
                    WARPED_WART-> registerBlock(block,
                        (block.hasSlab ? new LeavesSlab(block.getSettings()) : null),
                        (block.hasStairs ? new LeavesStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new BaseWall(block.getSettings()) : null));
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
                        -> registerBlock(block,
                        (block.hasSlab ? new TranslucentSlab(block, block.getSettings()) : null),
                        (block.hasStairs ? new TranslucentStairs(block.parentBlock.getDefaultState(), block, block.getSettings()) : null),
                        (block.hasWall ? new BaseWall(block.getSettings()) : null));

                case SAND,
                    GRAVEL,
                    RED_SAND -> registerBlock(block,
                        (block.hasSlab ? new FallingSlab(block.getSettings()) : null),
                        (block.hasStairs ? new FallingStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new FallingWall(block.getSettings()) : null));
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
                        YELLOW_CONCRETE_POWDER -> registerBlock(block,
                        (block.hasSlab ? new ConcretePowderSlab(block.accociatedBlock, block.getSettings()) : null),
                        (block.hasStairs ? new ConcretePowderStairs(block.parentBlock.getDefaultState(), block.accociatedBlock, block.getSettings()) : null),
                        (block.hasWall ? new ConcretePowderWall(block.accociatedBlock, block.getSettings()) : null));

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
                BLUE_GLAZED_TERRACOTTA -> registerBlock(block,
                        (block.hasSlab ? new GlazedTerracottaSlab(block.getSettings()) : null),
                        (block.hasStairs ? new GlazedTerracottaStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new GlazedTerracottaWall(block.getSettings()) : null));

                case OXIDIZED_COPPER,
                 WEATHERED_COPPER,
                 EXPOSED_COPPER,
                 COPPER_BLOCK,
                 OXIDIZED_CUT_COPPER,
                 WEATHERED_CUT_COPPER,
                    EXPOSED_CUT_COPPER,
                    CUT_COPPER -> registerBlock(block,
                        (block.hasSlab ? new OxidizableSlab(block.oxidationLevel, block.accociatedBlock, block.getSettings()) : null),
                        (block.hasStairs ? new OxidizableStairs(block.parentBlock.getDefaultState(),block.oxidationLevel, block.accociatedBlock, block.getSettings()) : null),
                        (block.hasWall ? new OxidizableWall(block.oxidationLevel, block.accociatedBlock, block.getSettings()) : null));
                case WAXED_COPPER_BLOCK,
                WAXED_EXPOSED_COPPER,
                WAXED_WEATHERED_COPPER,
                WAXED_OXIDIZED_COPPER,
                WAXED_CUT_COPPER,
                WAXED_EXPOSED_CUT_COPPER,
                WAXED_WEATHERED_CUT_COPPER,
                WAXED_OXIDIZED_CUT_COPPER -> registerBlock(block,
                        (block.hasSlab ? new WaxedSlab(block.accociatedBlock, block.getSettings()) : null),
                        (block.hasStairs ? new WaxedStairs(block.parentBlock.getDefaultState(),block.accociatedBlock, block.getSettings()) : null),
                        (block.hasWall ? new WaxedWall(block.accociatedBlock, block.getSettings()) : null));

                case MAGMA_BLOCK -> registerBlock(block,
                        (block.hasSlab ? new MagmaSlab(block.getSettings()) : null),
                        (block.hasStairs ? new MagmaStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new MagmaWall(block.getSettings()) : null));
                case SOUL_SAND -> registerBlock(block,
                        (block.hasSlab ? new SoulSandSlab(block.getSettings()) : null),
                        (block.hasStairs ? new SoulSandStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new SoulSandWall(block.getSettings()) : null));
                case SLIME_BLOCK -> registerBlock(block,
                        (block.hasSlab ? new SlimeSlab(block.getSettings()) : null),
                        (block.hasStairs ? new SlimeStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new SlimeWall(block.getSettings()) : null));
                case REDSTONE_BLOCK -> registerBlock(block,
                        (block.hasSlab ? new RedstoneSlab(block.getSettings()) : null),
                        (block.hasStairs ? new RedstoneStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new RedstoneWall(block.getSettings()) : null));
                case TUBE_CORAL_BLOCK,
                BUBBLE_CORAL_BLOCK,
                BRAIN_CORAL_BLOCK,
                FIRE_CORAL_BLOCK,
                HORN_CORAL_BLOCK -> registerBlock(block,
                        (block.hasSlab ? new CoralSlab(block.accociatedBlock, block.getSettings()) : null),
                        (block.hasStairs ? new CoralStairs(block.parentBlock.getDefaultState(),block.accociatedBlock,  block.getSettings()) : null),
                        (block.hasWall ? new CoralWall(block.accociatedBlock, block.getSettings()) : null));

                case ICE -> registerBlock(block,
                        (block.hasSlab ? new IceSlab(block, block.getSettings()) : null),
                        (block.hasStairs ? new IceStairs(block.parentBlock.getDefaultState(),block,  block.getSettings()) : null),
                        (block.hasWall ? new BaseWall(block.getSettings()) : null));
                default -> registerBlock(block,
                        (block.hasSlab ? new BaseSlab(block.getSettings()) : null),
                        (block.hasStairs ?  new BaseStairs(block.parentBlock.getDefaultState(), block.getSettings()) : null),
                        (block.hasWall ? new BaseWall(block.getSettings()) : null));
        }
    }

    public static void registerBlock(ModBlocks block, @Nullable Block slab, @Nullable Block stair, @Nullable Block wall) {
        if (block.hasSlab) {
            System.out.println(block.toString());
            MOD_BLOCKS.put(block.getId(ModBlocks.BlockType.SLAB), net.minecraft.registry.Registry.register(Registries.BLOCK, block.getId(ModBlocks.BlockType.SLAB), slab));
            registerItem(block.getId(ModBlocks.BlockType.SLAB), slab);
        }

        if (block.hasStairs) {
            MOD_BLOCKS.put(block.getId(ModBlocks.BlockType.STAIRS), net.minecraft.registry.Registry.register(Registries.BLOCK, block.getId(ModBlocks.BlockType.STAIRS), stair));
            registerItem(block.getId(ModBlocks.BlockType.STAIRS), stair);
        }

        if (block.hasWall) {
            MOD_BLOCKS.put(block.getId(ModBlocks.BlockType.WALL), net.minecraft.registry.Registry.register(Registries.BLOCK, block.getId(ModBlocks.BlockType.WALL), wall));
            registerItem(block.getId(ModBlocks.BlockType.WALL), wall);
        }

    }



    public static void registerItem(Identifier id, Block block){
        Item item = net.minecraft.registry.Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
