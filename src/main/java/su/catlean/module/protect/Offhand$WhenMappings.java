/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

import java.lang.invoke.MethodHandles;
import su.catlean.module.Module;
import su.catlean.module.protect.OffhandOffhandMode;
import su.catlean.module.protect.OffhandSwapMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Offhand$WhenMappings {
    public static final int[] v;
    public static final int[] a;
    private static Module[] S;

    static {
        long l = ZkmKeys.a(7985256281696422549L, -2604575219532910221L, MethodHandles.lookup().lookupClass()).a(35608222560684L) ^ 0x3412D06D51DL;
        int[] nArray = new int[OffhandOffhandMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)6687404575770027715L, (long)l);
        try {
            nArray[OffhandOffhandMode.OFF.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandOffhandMode.CRYSTAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandOffhandMode.GAPPLE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        v = nArray;
        nArray = new int[OffhandSwapMode.values().length];
        try {
            nArray[OffhandSwapMode.BALL_SHIELD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandSwapMode.GAPPLE_BALL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandSwapMode.GAPPLE_SHIELD.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandSwapMode.BALL_TOTEM.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[OffhandSwapMode.TOTEM_TOTEM.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        a = nArray;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        S = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return S;
    }
}

