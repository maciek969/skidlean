/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import su.catlean.module.attack.AutoCrystalTargetSortMode;

public final class TargetUtil$WhenMappings {
    public static final int[] a;

    static {
        int[] nArray = new int[AutoCrystalTargetSortMode.values().length];
        try {
            nArray[AutoCrystalTargetSortMode.FOV.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalTargetSortMode.HEALTH.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalTargetSortMode.DISTANCE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        a = nArray;
    }
}

