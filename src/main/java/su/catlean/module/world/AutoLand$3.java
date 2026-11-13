/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.hit.BlockHitResult;
import su.catlean.module.world.AutoLand$2;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AutoLand$3
implements Comparator {
    private static final long a = ZkmKeys.a(-2090984215748182622L, -5200576937610715304L, MethodHandles.lookup().lookupClass()).a(126835002431466L);

    public final int compare(Object object, Object object2) {
        AutoLand$2.c();
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

