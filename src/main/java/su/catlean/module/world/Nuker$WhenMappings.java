/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import su.catlean.module.world.NukerFilterMode;

public final class Nuker$WhenMappings {
    public static final int[] P;

    static {
        int[] nArray = new int[NukerFilterMode.values().length];
        try {
            nArray[NukerFilterMode.All.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NukerFilterMode.Select.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NukerFilterMode.WhiteList.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        P = nArray;
    }
}

