/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import su.catlean.render.ColorMode;

public final class AmbientParticle$WhenMappings {
    public static final int[] K;

    static {
        int[] nArray = new int[ColorMode.values().length];
        try {
            nArray[ColorMode.CUSTOM.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ColorMode.SYNC.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ColorMode.SKY.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ColorMode.LIGHT_RGB.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ColorMode.RGB.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        K = nArray;
    }
}

