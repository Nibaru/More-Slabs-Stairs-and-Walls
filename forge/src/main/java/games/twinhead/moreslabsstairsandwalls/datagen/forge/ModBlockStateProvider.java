package games.twinhead.moreslabsstairsandwalls.datagen.forge;

import games.twinhead.moreslabsstairsandwalls.MoreSlabsStairsAndWalls;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator data, ExistingFileHelper exFileHelper) {
        super(data, MoreSlabsStairsAndWalls.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (ModBlocks modBlock : ModBlocks.values()) {
            for (ModBlocks.BlockType type: ModBlocks.BlockType.values()) {
                if(modBlock.hasBlock(type)) blockWithItem(modBlock.getBlock(type));
            }
        }
    }

    private void blockWithItem(Block block) {
        super.simpleBlockItem(block, cubeAll(block));
    }
}
