/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import su.catlean.module.attack.AuraEspMode;
import su.catlean.module.attack.AutoCrystalAutoSwitchMode;
import su.catlean.module.attack.AutoCrystalPredictMode;

public final class AutoCrystal$WhenMappings {
    public static final int[] m;
    public static final int[] J;
    public static final int[] I;

    static {
        int[] nArray = new int[AuraEspMode.values().length];
        try {
            nArray[AuraEspMode.COMETS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AuraEspMode.SELECTION.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        m = nArray;
        nArray = new int[AutoCrystalAutoSwitchMode.values().length];
        try {
            nArray[AutoCrystalAutoSwitchMode.INVENTORY.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalAutoSwitchMode.SILENT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalAutoSwitchMode.NORMAL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        J = nArray;
        nArray = new int[AutoCrystalPredictMode.values().length];
        try {
            nArray[AutoCrystalPredictMode.AUTO.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalPredictMode.CUSTOM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoCrystalPredictMode.OFF.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        I = nArray;
    }
}

