/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import su.catlean.util.rotation.AimPoint;
import su.catlean.util.rotation.JitterMode;

public final class AimSettings$WhenMappings {
    public static final int[] u;
    public static final int[] x;

    static {
        int[] nArray = new int[JitterMode.values().length];
        try {
            nArray[JitterMode.SINE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[JitterMode.ROAMING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[JitterMode.DRUNK_SINE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[JitterMode.RANDOM.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[JitterMode.NONE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        u = nArray;
        nArray = new int[AimPoint.values().length];
        try {
            nArray[AimPoint.CENTER.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AimPoint.EYES.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AimPoint.ROAMING.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AimPoint.RUST.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AimPoint.CLOSEST.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        x = nArray;
    }
}

