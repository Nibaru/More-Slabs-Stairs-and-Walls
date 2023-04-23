package games.twinhead.moreslabsstairsandwalls.block.ice;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.translucent.TranslucentSlab;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;


public class IceSlab extends TranslucentSlab {

    public IceSlab(ModBlocks block, AbstractBlock.Settings settings) {
        super(block, settings);
    }


    public void onEntityLand(BlockView world, Entity entity) {
        entity.setVelocity(entity.getVelocity().multiply(this.getSlipperiness(), 0.0, this.getSlipperiness()));
    }

}
