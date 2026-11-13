/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import su.catlean.module.rage.BlinkAttackActionMode;

public final class Blink$WhenMappings {
    public static final int[] b;

    static {
        int[] nArray = new int[BlinkAttackActionMode.values().length];
        try {
            nArray[BlinkAttackActionMode.OFF.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[BlinkAttackActionMode.REPEAT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        b = nArray;
    }
}

