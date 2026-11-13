/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.MethodHandles;
import su.catlean.module.player.AutoRespawn;
import su.catlean.zkm.ZkmKeys;

public final class AutoRespawn$onPlayerUpdate$1
implements Runnable {
    final int g;
    private static String r;
    private static final long a;

    public AutoRespawn$onPlayerUpdate$1(int n) {
        this.g = n;
    }

    @Override
    public final void run() {
        Thread.sleep(this.g);
        AutoRespawn.INSTANCE.respawn();
    }

    public static void zkmSetFlag(String string) {
        r = string;
    }

    public static String zkmGetFlag() {
        return r;
    }

    static {
        a = ZkmKeys.a(1552214561807891309L, 4600427558378185114L, MethodHandles.lookup().lookupClass()).a(174544275208148L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

