/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import su.catlean.module.rage.SpiderMode;

public final class Spider$WhenMappings {
    public static final int[] U;

    static {
        int[] nArray = new int[SpiderMode.values().length];
        try {
            nArray[SpiderMode.MATRIX.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpiderMode.DEFAULT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpiderMode.WATER_BUCKET.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpiderMode.MATRIX_2.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[SpiderMode.FT_FENCE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        U = nArray;
    }
}

