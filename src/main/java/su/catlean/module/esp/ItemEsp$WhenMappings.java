/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import su.catlean.module.esp.ItemEspFilterMode;
import su.catlean.render.EspRenderMode;

public final class ItemEsp$WhenMappings {
    public static final int[] w;
    public static final int[] l;

    static {
        int[] nArray = new int[EspRenderMode.values().length];
        try {
            nArray[EspRenderMode.BOX.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EspRenderMode.OUTLINE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        w = nArray;
        nArray = new int[ItemEspFilterMode.values().length];
        try {
            nArray[ItemEspFilterMode.NONE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ItemEspFilterMode.WHITE_LIST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        l = nArray;
    }
}

