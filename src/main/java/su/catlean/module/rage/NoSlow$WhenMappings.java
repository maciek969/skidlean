/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import su.catlean.util.AntiCheatMode;

public final class NoSlow$WhenMappings {
    public static final int[] V;

    static {
        int[] nArray = new int[AntiCheatMode.values().length];
        try {
            nArray[AntiCheatMode.NCP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AntiCheatMode.GRIM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        V = nArray;
    }
}

