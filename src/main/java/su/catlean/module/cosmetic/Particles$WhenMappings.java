/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import su.catlean.module.cosmetic.ParticlesMode;

public final class Particles$WhenMappings {
    public static final int[] G;

    static {
        int[] nArray = new int[ParticlesMode.values().length];
        try {
            nArray[ParticlesMode.DROP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ParticlesMode.FLY.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        G = nArray;
    }
}

