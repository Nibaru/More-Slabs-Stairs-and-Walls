package games.twinhead.moreslabsstairsandwalls.registry.forge;

import games.twinhead.moreslabsstairsandwalls.block.entity.FallingSlabBlockEntity;
import net.minecraft.entity.EntityType;

public class ModRegistryImpl {

    public static EntityType<FallingSlabBlockEntity> getFallingSlabEntityType() {
        return ModRegistry.FALLING_SLAB_BLOCK_ENTITY.get();
    }
}
