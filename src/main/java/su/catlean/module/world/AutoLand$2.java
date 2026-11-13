/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.hit.BlockHitResult;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AutoLand$2
implements Comparator {
    private static int t;
    private static final long a;

    public final int compare(Object object, Object object2) {
        BlockHitResult blockHitResult = (BlockHitResult)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
        blockHitResult = (BlockHitResult)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
    }

    public static void zkmSetFlag(int n) {
        t = n;
    }

    public static int zkmGetFlag() {
        return t;
    }

    public static int c() {
        return 0;
    }

    static {
        a = ZkmKeys.a(-8395517365974704207L, 6048797387394052316L, MethodHandles.lookup().lookupClass()).a(269779843598329L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

