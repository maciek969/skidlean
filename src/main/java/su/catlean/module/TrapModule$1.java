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

public final class TrapModule$1
implements Comparator {
    final double[] s;
    private static final long a = ZkmKeys.a(2162100038635540272L, -2305859575058774886L, MethodHandles.lookup().lookupClass()).a(235743095427625L);

    public TrapModule$1(double[] dArray) {
        this.s = dArray;
    }

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object;
        Comparable comparable = Double.valueOf(blockPos.toCenterPos().squaredDistanceTo(Globals.getPlayer().getX() + this.s[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + this.s[1]));
        blockPos = (BlockPos)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, blockPos.toCenterPos().squaredDistanceTo(Globals.getPlayer().getX() + this.s[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + this.s[1]));
    }
}

