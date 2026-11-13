/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import su.catlean.module.basic.GuiMoveClickBypassMode;

public final class GuiMove$WhenMappings {
    public static final int[] Z;

    static {
        int[] nArray = new int[GuiMoveClickBypassMode.values().length];
        try {
            nArray[GuiMoveClickBypassMode.STRICT_NCP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GuiMoveClickBypassMode.STRICT_NCP_2.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GuiMoveClickBypassMode.MATRIX_NCP.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GuiMoveClickBypassMode.LEGIT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GuiMoveClickBypassMode.DISABLE_CLICKS.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        Z = nArray;
    }
}

