/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.math.BlockPos;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class SourceFiller$onPlayerUpdate$1
implements Comparator {
    private static String[] O;
    private static final long a;

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().squaredDistanceTo(blockPos.toCenterPos()));
        blockPos = (BlockPos)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().squaredDistanceTo(blockPos.toCenterPos()));
    }

    public static void zkmSetFlag(String[] stringArray) {
        O = stringArray;
    }

    public static String[] zkmGetFlag() {
        return O;
    }

    static {
        a = ZkmKeys.a(-311697594900908806L, 3782620389181955327L, MethodHandles.lookup().lookupClass()).a(112880146224265L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

