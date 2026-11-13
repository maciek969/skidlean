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

public final class SoilRipper$onPlayerUpdate$1
implements Comparator {
    private static String[] m;
    private static final long a;

    public final int compare(Object object, Object object2) {
        BlockHitResult blockHitResult = (BlockHitResult)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
        blockHitResult = (BlockHitResult)object2;
        Comparable comparable2 = comparable;
        Object var7_5 = null;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
    }

    public static void zkmSetFlag(String[] stringArray) {
        m = stringArray;
    }

    public static String[] zkmGetFlag() {
        return m;
    }

    static {
        a = ZkmKeys.a(-9081064515331713736L, 4874658618522533266L, MethodHandles.lookup().lookupClass()).a(88765670507335L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

