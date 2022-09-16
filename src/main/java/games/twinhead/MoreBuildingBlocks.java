package games.twinhead;


import net.fabricmc.api.ModInitializer;

public class MoreBuildingBlocks implements ModInitializer {
    public static final String mod_id="more_slabs_stairs_and_walls";

    @Override
    public void onInitialize() {
        BlockRegistry.RegisterAllBlocks();
    }
}
