/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import su.catlean.util.HoleType;

public final class Hole$WhenMappings {
    public static final int[] M;

    static {
        int[] nArray = new int[HoleType.values().length];
        try {
            nArray[HoleType.DOUBLE_X.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleType.DOUBLE_Z.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleType.QUAD.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        M = nArray;
    }
}

