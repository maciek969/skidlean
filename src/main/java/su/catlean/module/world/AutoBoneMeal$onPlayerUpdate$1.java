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

public final class AutoBoneMeal$onPlayerUpdate$1
implements Comparator {
    private static int[] h;
    private static final long a;

    public final int compare(Object object, Object object2) {
        BlockHitResult blockHitResult = (BlockHitResult)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
        blockHitResult = (BlockHitResult)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
    }

    public static void zkmSetFlag(int[] nArray) {
        h = nArray;
    }

    public static int[] zkmGetFlag() {
        return h;
    }

    static {
        a = ZkmKeys.a(5923240106666421228L, -7850087669393763421L, MethodHandles.lookup().lookupClass()).a(22166380070477L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

