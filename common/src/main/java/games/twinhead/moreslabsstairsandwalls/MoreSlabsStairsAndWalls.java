package games.twinhead.moreslabsstairsandwalls;

import net.minecraft.resources.ResourceLocation;

public class MoreSlabsStairsAndWalls
{
	public static final String MOD_ID = "more_slabs_stairs_and_walls";

	public static void init() {}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
