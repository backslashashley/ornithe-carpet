package carpet.log.loggers.portalCaching;

import carpet.log.framework.LoggerRegistry;
import carpet.utils.Messenger;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PortalCaching {

    public static void portalCachingCleared(World world, int cachedCount, ArrayList<Vec3d> uncacheCount) {
        if (cachedCount == 0 && uncacheCount.isEmpty()) {
            return;
        }
        final int count = uncacheCount.size();
        List<Text> comp = new ArrayList<>();
        LoggerRegistry.getLogger("portalCaching").log((option) -> {
            comp.add(Messenger.s(String.format("%s Portals cached %d, Portal caches removed %d", world.dimension.getType(), cachedCount, count)));
            switch (option) {
                case "brief":
                    return comp.toArray(new Text[0]);
                case "full":
                    return finalReport(world, comp, uncacheCount).toArray(new Text[0]);
                default:
                    return null;
            }
        });
    }

    private static List<Text> finalReport(World world, List<Text> comp, ArrayList<Vec3d> uncacheCount) {
        List<String> line = new ArrayList<>();
        for (int i = 0; i < uncacheCount.size(); i++) {
            Vec3d p = uncacheCount.get(i);
            Vec3d pos, tp, mot;
            if (world.dimension.getType().getId() == -1) {
                pos = new Vec3d(p.x * 8, p.y, p.z * 8);
                tp = pos.add(4, 0, 4);
                mot = pos.add(8, 0, 8);
            } else {
                pos = new Vec3d(p.x * 0.125, p.y, p.z * 0.125);
                tp = pos.add(0.5, 0, 0.5);
                mot = pos.add(1, 0, 1);
            }
            line.add("w  x");
            line.add(String.format("^w Cache: %d\nx: %f\ny: %f\nz: %f\n------------\nmx: %f\nmy: %f\nmz: %f",
                    i, pos.x, pos.y, pos.z, mot.x, mot.y, mot.z));
            line.add("?/tp " + tp.x + " " + tp.y + " " + tp.z);

            if ((((i + 1) % 50) == 0) || i == uncacheCount.size() - 1) {
                comp.add(Messenger.c(line.toArray(new Object[0])));
                line.clear();
            }
        }
        return comp;
    }
}
