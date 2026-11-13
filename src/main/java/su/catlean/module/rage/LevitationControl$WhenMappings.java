/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import su.catlean.module.rage.LevitationControlMode;

public final class LevitationControl$WhenMappings {
    public static final int[] x;

    static {
        int[] nArray = new int[LevitationControlMode.values().length];
        try {
            nArray[LevitationControlMode.NCP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[LevitationControlMode.DEFAULT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[LevitationControlMode.ANTI.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        x = nArray;
    }
}

