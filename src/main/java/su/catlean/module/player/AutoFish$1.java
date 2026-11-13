/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.MethodHandles;
import su.catlean.module.player.AutoFish$Recast;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AutoFish$1
implements Runnable {
    final int h;
    private static String[] l;
    private static final long a;

    public AutoFish$1(int n) {
        this.h = n;
    }

    @Override
    public final void run() {
        Thread.sleep(this.h);
        Globals.getMc().executeSync(AutoFish$Recast.T);
    }

    public static void zkmSetFlag(String[] stringArray) {
        l = stringArray;
    }

    public static String[] zkmGetFlag() {
        return l;
    }

    static {
        a = ZkmKeys.a(5471893296269150731L, -8535250809707412076L, MethodHandles.lookup().lookupClass()).a(246538749756351L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

