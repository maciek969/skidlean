/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import su.catlean.zkm.ZkmKeys;

public final class Surround$onAfterReceivePacket$1
implements Comparator {
    final Vec3d U;
    private static String R;
    private static final long a;

    public Surround$onAfterReceivePacket$1(Vec3d vec3d) {
        this.U = vec3d;
    }

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object;
        Comparable comparable = Double.valueOf(blockPos.toCenterPos().distanceTo(this.U));
        blockPos = (BlockPos)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, blockPos.toCenterPos().distanceTo(this.U));
    }

    public static void zkmSetFlag(String string) {
        R = string;
    }

    public static String zkmGetFlag() {
        return R;
    }

    static {
        a = ZkmKeys.a(-6313708130230503124L, 7962333429127072206L, MethodHandles.lookup().lookupClass()).a(26767716032862L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

