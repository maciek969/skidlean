/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import su.catlean.module.rage.SpeedBoostAngleMode;

public final class Speed$WhenMappings {
    public static final int[] k;

    static {
        int[] nArray = new int[SpeedBoostAngleMode.values().length];
        try {
            nArray[SpeedBoostAngleMode.SERVER.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[SpeedBoostAngleMode.CLIENT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[SpeedBoostAngleMode.PREDICT.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        k = nArray;
    }
}

