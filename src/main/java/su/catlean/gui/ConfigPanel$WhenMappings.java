/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import su.catlean.config.ConfigType;

public final class ConfigPanel$WhenMappings {
    public static final int[] i;

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
        i = nArray;
    }
}

