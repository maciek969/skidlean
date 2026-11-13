/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import su.catlean.module.player.AutoRespawnDeathCommandMode;

public final class AutoRespawn$WhenMappings {
    public static final int[] A;

    static {
        int[] nArray = new int[AutoRespawnDeathCommandMode.values().length];
        try {
            nArray[AutoRespawnDeathCommandMode.KIT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AutoRespawnDeathCommandMode.BACK.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AutoRespawnDeathCommandMode.HOME.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AutoRespawnDeathCommandMode.CUSTOM.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        A = nArray;
    }
}

