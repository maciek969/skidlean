/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import su.catlean.module.player.SpeedMineRenderMode;

public final class SpeedMine$WhenMappings {
    public static final int[] i;

    static {
        int[] nArray = new int[SpeedMineRenderMode.values().length];
        try {
            nArray[SpeedMineRenderMode.Block.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[SpeedMineRenderMode.Grow.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[SpeedMineRenderMode.Shrink.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        i = nArray;
    }
}

