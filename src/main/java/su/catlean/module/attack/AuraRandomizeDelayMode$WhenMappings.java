/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import su.catlean.module.attack.AuraRandomizeDelayMode;

public final class AuraRandomizeDelayMode$WhenMappings {
    public static final int[] Y;

    static {
        int[] nArray = new int[AuraRandomizeDelayMode.values().length];
        try {
            nArray[AuraRandomizeDelayMode.DEFAULT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraRandomizeDelayMode.VULCAN.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        Y = nArray;
    }
}

