/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import su.catlean.config.ConfigType;

public final class ConfigIO$WhenMappings {
    public static final int[] N;

    static {
        int[] nArray = new int[ConfigType.values().length];
        try {
            nArray[ConfigType.MODULES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ConfigType.WIDGETS.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        N = nArray;
    }
}

