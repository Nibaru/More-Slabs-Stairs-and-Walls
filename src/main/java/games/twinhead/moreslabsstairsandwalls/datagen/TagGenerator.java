package games.twinhead.moreslabsstairsandwalls.datagen;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider<Block> {



    public TagGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(dataGenerator, RegistryKeys.BLOCK, registriesFuture);
    }


    public void addTag(ModBlocks block, TagKey<Block> tagKey){
        if(block.hasSlab()) getOrCreateTagBuilder(tagKey).add(block.getSlabBlock());
        if(block.hasStairs())getOrCreateTagBuilder(tagKey).add(block.getStairsBlock());
        if(block.hasWall()) getOrCreateTagBuilder(tagKey).add(block.getWallBlock());
    }

    public void removeTag(ModBlocks block, TagKey<Block> tagKey){
        if(block.hasSlab()) getOrCreateTagBuilder(tagKey).add(block.getSlabBlock());
        if(block.hasStairs())getOrCreateTagBuilder(tagKey).add(block.getStairsBlock());
        if(block.hasWall()) getOrCreateTagBuilder(tagKey).add(block.getWallBlock());
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        TagKey<Block> mineablePickaxe = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "mineable/pickaxe"));
        TagKey<Block> mineableShovel = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "mineable/shovel"));
        TagKey<Block> mineableHoe = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "mineable/hoe"));
        TagKey<Block> mineableAxe = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "mineable/axe"));

        TagKey<Block> beacon = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "beacon_base_blocks"));

        TagKey<Block> dirt = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "dirt"));

        TagKey<Block> slabs = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "slabs"));
        TagKey<Block> walls = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "walls"));
        TagKey<Block> stairs = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "stairs"));

        TagKey<Block> mushroom = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "mushroom_grow_block"));
        TagKey<Block> nylium = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "nylium"));

        TagKey<Block> wool = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "wool"));

        TagKey<Block> witherBaseBlocks = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "wither_summon_base_blocks"));
        TagKey<Block> soulFire = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "soul_fire_based_blocks"));
        TagKey<Block> soulSpeed = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "soul_speed_blocks"));

        TagKey<Block> dragonImmune = TagKey.of(Registries.BLOCK.getKey(), new Identifier("minecraft", "dragon_immune"));

        for (ModBlocks block: ModBlocks.values()) {
            if(block.toString().contains("wool")){
                if(block.hasSlab()) getOrCreateTagBuilder(wool).add(block.getSlabBlock());
                if(block.hasStairs())getOrCreateTagBuilder(wool).add(block.getStairsBlock());
                if(block.hasWall()) getOrCreateTagBuilder(wool).add(block.getWallBlock());
            }

            if(block.hasSlab()) {
                getOrCreateTagBuilder(slabs).add(block.getSlabBlock());
                getOrCreateTagBuilder(ModBlockTags.SLABS).add(block.getSlabBlock());
            }
            if(block.hasStairs()){
                getOrCreateTagBuilder(stairs).add(block.getStairsBlock());
                getOrCreateTagBuilder(ModBlockTags.STAIRS).add(block.getStairsBlock());
            }
            if(block.hasWall()){
                getOrCreateTagBuilder(walls).add(block.getWallBlock());
                getOrCreateTagBuilder(ModBlockTags.WALLS).add(block.getWallBlock());
            }

            switch (block){
                case GRASS_BLOCK,
                        DIRT,
                        COARSE_DIRT,
                        MYCELIUM,
                        PODZOL, ROOTED_DIRT -> {
                    addTag(block, ModBlockTags.DIRT);
                    addTag(block, dirt);
                }
                case CRIMSON_NYLIUM, WARPED_NYLIUM -> {
                    addTag(block, nylium);
                }
                case IRON_BLOCK, EMERALD_BLOCK, DIAMOND_BLOCK, GOLD_BLOCK, NETHERITE_BLOCK -> {
                    if(block.hasStairs())getOrCreateTagBuilder(beacon).add(block.getStairsBlock());
                }
                case SOUL_SAND, SOUL_SOIL -> {
                    addTag(block, witherBaseBlocks);
                    addTag(block, soulSpeed);
                    addTag(block, soulFire);
                }
                case OBSIDIAN, CRYING_OBSIDIAN -> addTag(block, dragonImmune);
            }

            switch (block){
                case MYCELIUM, PODZOL, CRIMSON_NYLIUM, WARPED_NYLIUM -> {
                    addTag(block, mushroom);
                }
            }

            switch (block.toolType) {
                case  PICKAXE -> addTag(block, mineablePickaxe);
                case  AXE -> addTag(block, mineableAxe);
                case  SHOVEL -> addTag(block, mineableShovel);
                case  HOE -> addTag(block, mineableHoe);
            }
        }
    }
}
