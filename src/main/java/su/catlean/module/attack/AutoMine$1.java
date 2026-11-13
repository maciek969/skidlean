/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class AutoMine$1
implements Comparator {
    final PlayerEntity H;
    private static Module[] L;
    private static final long a;

    public AutoMine$1(PlayerEntity playerEntity) {
        this.H = playerEntity;
    }

    public final int compare(Object object, Object object2) {
        BlockPos blockPos = (BlockPos)object;
        Comparable comparable = Double.valueOf(this.H.getEyePos().distanceTo(blockPos.toCenterPos()));
        blockPos = (BlockPos)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, this.H.getEyePos().distanceTo(blockPos.toCenterPos()));
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        L = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return L;
    }

    static {
        a = ZkmKeys.a(-3711468906153983158L, -4387807468706797874L, MethodHandles.lookup().lookupClass()).a(130708210504400L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

