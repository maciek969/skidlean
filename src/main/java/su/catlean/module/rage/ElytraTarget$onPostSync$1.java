/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.ui.Notifications;
import su.catlean.zkm.ZkmKeys;

public final class ElytraTarget$onPostSync$1
implements Runnable {
    final int p;
    private static String W;
    private static final long a;

    public ElytraTarget$onPostSync$1(int n) {
        this.p = n;
    }

    @Override
    public final void run() {
        long l;
        long l2 = l = a ^ 0x297DB4B52225L;
        long l3 = l2 ^ 0x598FA1ECFE44L;
        int n = (int)(l3 >>> 56);
        long l4 = l3 << 8 >>> 8;
        long l5 = l2 ^ 0x5F5807AEECDDL;
        Thread.sleep(this.p);
        ElytraTarget.l(false);
        Notifications.INSTANCE.t(true);
        Blink.INSTANCE.disable(l5);
        ElytraTarget.m(false);
        if (ElytraTarget.access$getFakeLag(ElytraTarget.INSTANCE)) {
            Blink.INSTANCE.enable((byte)n, l4);
            ElytraTarget.m(true);
        }
        Notifications.INSTANCE.t(false);
    }

    public static void zkmSetFlag(String string) {
        W = string;
    }

    public static String zkmGetFlag() {
        return W;
    }

    static {
        a = ZkmKeys.a(-1144173234703483777L, 2499100012809533188L, MethodHandles.lookup().lookupClass()).a(103773081018879L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

