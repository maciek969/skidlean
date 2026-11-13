/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.MethodHandles;
import su.catlean.mc;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AutoFish$2
implements Runnable {
    final int n;
    private static final long a = ZkmKeys.a(3670996963746500995L, 5886809280182922498L, MethodHandles.lookup().lookupClass()).a(18885287829215L);

    public AutoFish$2(int n) {
        this.n = n;
    }

    @Override
    public final void run() {
        Thread.sleep(this.n);
        Globals.getMc().executeSync(mc.J);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

