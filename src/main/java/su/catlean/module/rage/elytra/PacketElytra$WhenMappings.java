/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage.elytra;

import su.catlean.module.rage.ElytraPlusNcpStrictMode;

public final class PacketElytra$WhenMappings {
    public static final int[] j;

    static {
        int[] nArray = new int[ElytraPlusNcpStrictMode.values().length];
        try {
            nArray[ElytraPlusNcpStrictMode.New.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ElytraPlusNcpStrictMode.Motion.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ElytraPlusNcpStrictMode.Old.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        j = nArray;
    }
}

