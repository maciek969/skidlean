/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import su.catlean.module.rage.ElytraMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ElytraMode$WhenMappings {
    public static final int[] p;
    private static boolean B;

    static {
        long l = ZkmKeys.a(-8748202399386563053L, 3396528333513052542L, MethodHandles.lookup().lookupClass()).a(15596453794262L) ^ 0x31036C03DFEFL;
        int[] nArray = new int[ElytraMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (boolean)true, (long)2847341247724774947L, (long)l);
        try {
            nArray[ElytraMode.Vanilla.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ElytraMode.GRIM_GLIDE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        p = nArray;
    }

    public static void zkmSetFlag(boolean bl) {
        B = bl;
    }

    public static boolean zkmGetFlag() {
        return B;
    }

    public static boolean t() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

