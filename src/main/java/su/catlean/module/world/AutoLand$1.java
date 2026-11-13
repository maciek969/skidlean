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

public final class AutoLand$1
implements Comparator {
    private static final long a = ZkmKeys.a(7040518653584201961L, -5907578590386348756L, MethodHandles.lookup().lookupClass()).a(191752529576189L);

    public final int compare(Object object, Object object2) {
        BlockHitResult blockHitResult = (BlockHitResult)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
        blockHitResult = (BlockHitResult)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().getEyePos().squaredDistanceTo(blockHitResult.getPos()));
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

