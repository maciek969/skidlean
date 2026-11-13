/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import su.catlean.module.player.SpeedMineTimingMode;

public final class MiningData$WhenMappings {
    public static final int[] h;

    static {
        int[] nArray = new int[SpeedMineTimingMode.values().length];
        try {
            nArray[SpeedMineTimingMode.VANILLA.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpeedMineTimingMode.SEQUENTIAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpeedMineTimingMode.GRIM.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        h = nArray;
    }
}

