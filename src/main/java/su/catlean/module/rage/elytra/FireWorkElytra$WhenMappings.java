/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage.elytra;

import su.catlean.module.rage.ElytraPlusAntiKickMode;

public final class FireWorkElytra$WhenMappings {
    public static final int[] D;

    static {
        int[] nArray = new int[ElytraPlusAntiKickMode.values().length];
        try {
            nArray[ElytraPlusAntiKickMode.Jitter.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ElytraPlusAntiKickMode.Glide.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ElytraPlusAntiKickMode.Off.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        D = nArray;
    }
}

