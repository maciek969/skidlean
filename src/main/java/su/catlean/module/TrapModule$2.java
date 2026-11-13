/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.math.BlockPos;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TrapModule$2
implements Comparator {
    private static final long a = ZkmKeys.a(-4165720267587765446L, 5979288400800260635L, MethodHandles.lookup().lookupClass()).a(203553888226240L);

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object2;
        Comparable comparable = Double.valueOf(Globals.getPlayer().getEyePos().distanceTo(blockPos.toCenterPos()));
        blockPos = (BlockPos)object;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().getEyePos().distanceTo(blockPos.toCenterPos()));
    }
}

