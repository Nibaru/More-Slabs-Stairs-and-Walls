package games.twinhead.moreslabsstairsandwalls.registry.fabric;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import games.twinhead.moreslabsstairsandwalls.block.oxidizable.WaxedSlab;
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
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ModRegistry {

    public static final EntityType<FallingSlabBlockEntity> FALLING_SLAB_BLOCK_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MoreSlabsStairsAndWalls.MOD_ID, "falling_slab"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, FallingSlabBlockEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).trackRangeBlocks(10).trackedUpdateRate(20).build());

    public static final HashMap<Identifier, Block> MOD_BLOCKS = new HashMap<>();

    public static Block getBlock(Identifier id) {
        return MOD_BLOCKS.get(id);
    }

//    public static ItemGroup modGroup;

    public static void registerBlocks(){
        for (ModBlocks modBlock : ModBlocks.values())
        {
            for (ModBlocks.BlockType type : ModBlocks.BlockType.values())
            {
                if (modBlock.hasBlock(type))
                {
                    Block block = games.twinhead.moreslabsstairsandwalls.registry.ModRegistry.getBlock(modBlock, type);
                    Registry.register(Registries.BLOCK, modBlock.getId(type), block);
                    registerItem(modBlock.getId(type), block);
                    MOD_BLOCKS.put(modBlock.getId(type), block);
                }
            }
        }
    }

    public static void registerItem(Identifier id, Block block){
        net.minecraft.registry.Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
