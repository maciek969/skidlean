/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import su.catlean.module.attack.AuraSortMode;

public final class TargetManager$WhenMappings {
    public static final int[] i;

    static {
        int[] nArray = new int[AuraSortMode.values().length];
        try {
            nArray[AuraSortMode.Distance.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraSortMode.FOV.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraSortMode.Health.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        i = nArray;
    }
}

