/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import su.catlean.module.attack.AuraEspMode;

public final class Aura$WhenMappings {
    public static final int[] i;

    static {
        int[] nArray = new int[AuraEspMode.values().length];
        try {
            nArray[AuraEspMode.COMETS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraEspMode.BLOOM_CIRCLE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraEspMode.SELECTION.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        i = nArray;
    }
}

