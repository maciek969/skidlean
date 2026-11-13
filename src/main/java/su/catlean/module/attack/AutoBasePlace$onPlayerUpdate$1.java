/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.MethodHandles;
import su.catlean.module.attack.AutoBasePlace;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.util.combat.CrystalPlacer;
import su.catlean.zkm.ZkmKeys;

public final class AutoBasePlace$onPlayerUpdate$1
implements Runnable {
    final int F;
    final boolean M;
    private static int[] k;
    private static final long a;

    public AutoBasePlace$onPlayerUpdate$1(int n, boolean bl) {
        this.F = n;
        this.M = bl;
    }

    @Override
    public final void run() {
        long l = a ^ 0x111BA47FDFB7L;
        long l2 = l ^ 0x619EF6BCD0F8L;
        Thread.sleep(this.F);
        AutoBasePlace.setLastHit(CrystalPlacer.calcBasePosition(AutoCrystal.INSTANCE, AutoBasePlace.access$getOnlyNoPositions(AutoBasePlace.INSTANCE), l2, this.M));
        Object var7_3 = null;
    }

    public static void zkmSetFlag(int[] nArray) {
        k = nArray;
    }

    public static int[] zkmGetFlag() {
        return k;
    }

    static {
        a = ZkmKeys.a(7286528217325016279L, 1837187445394322979L, MethodHandles.lookup().lookupClass()).a(143681571296802L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

