/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import su.catlean.module.misc.AutoLeavePlayersMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AutoLeavePlayersMode$WhenMappings {
    public static final int[] y;
    private static String m;

    static {
        long l = ZkmKeys.a(8489111107365677733L, -3521827521182079485L, MethodHandles.lookup().lookupClass()).a(274547483648350L) ^ 0x3637A5A56799L;
        int[] nArray = new int[AutoLeavePlayersMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)7000012258261979677L, (long)l);
        try {
            nArray[AutoLeavePlayersMode.Command.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoLeavePlayersMode.Leave.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoLeavePlayersMode.None.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        y = nArray;
    }

    public static void zkmSetFlag(String string) {
        m = string;
    }

    public static String zkmGetFlag() {
        return m;
    }
}

