/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import su.catlean.module.misc.SpammerMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Spammer$WhenMappings {
    public static final int[] t;
    private static boolean T;

    static {
        long l = ZkmKeys.a(-3957160002670670851L, -3286444797927844794L, MethodHandles.lookup().lookupClass()).a(13055167878322L) ^ 0x7F85E59DEF8EL;
        int[] nArray = new int[SpammerMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (boolean)true, (long)3238675305508099914L, (long)l);
        try {
            nArray[SpammerMode.GLOBAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpammerMode.LOCAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpammerMode.WHISPERS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        t = nArray;
    }

    public static void zkmSetFlag(boolean bl) {
        T = bl;
    }

    public static boolean zkmGetFlag() {
        return T;
    }

    public static boolean n() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

