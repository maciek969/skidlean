/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import su.catlean.module.world.AutoShear;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AutoShear$onPlayerUpdate$1
implements Comparator {
    private static int[] A;
    private static final long a;

    public final int compare(Object object, Object object2) {
        Entity entity = (Entity)object;
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNull(entity);
        Comparable comparable = Double.valueOf(vec3d.squaredDistanceTo(AutoShear.INSTANCE.getPos(entity)));
        entity = (Entity)object2;
        Comparable comparable2 = comparable;
        Object var10_5 = null;
        Vec3d vec3d2 = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNull(entity);
        return ComparisonsKt.compareValues(comparable2, vec3d2.squaredDistanceTo(AutoShear.INSTANCE.getPos(entity)));
    }

    public static void zkmSetFlag(int[] nArray) {
        A = nArray;
    }

    public static int[] zkmGetFlag() {
        return A;
    }

    static {
        a = ZkmKeys.a(509664487931814498L, 1453492134935506192L, MethodHandles.lookup().lookupClass()).a(258674519614279L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

