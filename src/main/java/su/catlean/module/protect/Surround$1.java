/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.math.BlockPos;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Surround$1
implements Comparator {
    final double[] F;
    private static final long a = ZkmKeys.a(5160970768762988838L, 1526931674224405926L, MethodHandles.lookup().lookupClass()).a(251757663716269L);

    public Surround$1(double[] dArray) {
        this.F = dArray;
    }

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object;
        Comparable comparable = Double.valueOf(blockPos.toCenterPos().squaredDistanceTo(Globals.getPlayer().getX() + this.F[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + this.F[1]));
        blockPos = (BlockPos)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, blockPos.toCenterPos().squaredDistanceTo(Globals.getPlayer().getX() + this.F[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + this.F[1]));
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

