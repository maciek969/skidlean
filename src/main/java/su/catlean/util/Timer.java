/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Timer {
    private long C;
    private static boolean B;
    private static final long a;

    public Timer() {
        this.reset();
    }

    public final boolean hasPassed(int n) {
        return Globals.now() - this.C >= (long)n;
    }

    public final boolean hasPassed2(int n) {
        boolean bl;
        block0: {
            boolean bl2 = bl = Globals.now() - this.C >= (long)n;
            if (!bl) break block0;
            this.reset();
        }
        return bl;
    }

    public final void setTime(int n) {
        this.C = System.currentTimeMillis() - (long)n;
    }

    public final long getElapsed() {
        return Globals.now() - this.C;
    }

    public final void reset() {
        this.C = Globals.now();
    }

    public static void zkmSetFlag(boolean bl) {
        B = bl;
    }

    public static boolean zkmGetFlag() {
        return B;
    }

    public static boolean n() {
        return true;
    }

    static {
        a = ZkmKeys.a(1087004212696968314L, -4002072988966639041L, MethodHandles.lookup().lookupClass()).a(104254268490845L);
        if (!Timer.n()) {
            Timer.zkmSetFlag(true);
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

