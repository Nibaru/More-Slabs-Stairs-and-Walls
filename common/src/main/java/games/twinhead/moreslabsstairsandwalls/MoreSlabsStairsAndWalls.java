package games.twinhead.moreslabsstairsandwalls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MoreSlabsStairsAndWalls {
    public static final String MOD_ID = "more_slabs_stairs_and_walls";

    public static void init() {
        Registry.registerBlocks();
    }

    public static void copyGlassCompatibilityPackIfMissing() {
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "MSSW_Glass_Resource_Pack_Fix.zip");

        if(!target.exists())
            try {
                dir.mkdirs();
                InputStream in = MoreSlabsStairsAndWalls.class.getResourceAsStream("/assets/more_slabs_stairs_and_walls/MSSW_Glass_Resource_Pack_Fix.zip");
                FileOutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[16384];
                int len;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
