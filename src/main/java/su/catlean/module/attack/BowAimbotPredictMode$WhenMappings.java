/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.MethodHandles;
import su.catlean.module.attack.BowAimbotPredictMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BowAimbotPredictMode$WhenMappings {
    public static final int[] U;
    private static String[] z;

    static {
        long l = ZkmKeys.a(7094525223541479720L, 2468025852360839364L, MethodHandles.lookup().lookupClass()).a(194185700975916L) ^ 0x7FD8A2F93E8DL;
        int[] nArray = new int[BowAimbotPredictMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)-7992705173765562923L, (long)l);
        try {
            nArray[BowAimbotPredictMode.SMART.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[BowAimbotPredictMode.CUSTOM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[BowAimbotPredictMode.OFF.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        U = nArray;
    }

    public static void zkmSetFlag(String[] stringArray) {
        z = stringArray;
    }

    public static String[] zkmGetFlag() {
        return z;
    }
}

