/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import su.catlean.module.esp.BreakEspMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BreakEsp$WhenMappings {
    public static final int[] a;
    private static int[] W;

    static {
        long l = ZkmKeys.a(4177637233814730235L, -8839912655941682496L, MethodHandles.lookup().lookupClass()).a(16335986938190L) ^ 0x6971E04B31BL;
        int[] nArray = new int[BreakEspMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)-5013277013829771549L, (long)l);
        try {
            nArray[BreakEspMode.DEFAULT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[BreakEspMode.GROW.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[BreakEspMode.SHRINK.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[BreakEspMode.FILL.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[BreakEspMode.EMPTY.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        a = nArray;
    }

    public static void zkmSetFlag(int[] nArray) {
        W = nArray;
    }

    public static int[] zkmGetFlag() {
        return W;
    }
}

