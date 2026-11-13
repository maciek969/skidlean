/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import net.minecraft.world.GameMode;
import su.catlean.module.esp.NameSource;
import su.catlean.module.esp.NameTagsNameMode;

public final class NameTags$WhenMappings {
    public static final int[] s;
    public static final int[] R;
    public static final int[] m;

    static {
        int[] nArray = new int[NameTagsNameMode.values().length];
        try {
            nArray[NameTagsNameMode.FULL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NameTagsNameMode.SEMI.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NameTagsNameMode.DEFAULT.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        s = nArray;
        nArray = new int[GameMode.values().length];
        try {
            nArray[GameMode.SURVIVAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GameMode.CREATIVE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GameMode.SPECTATOR.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[GameMode.ADVENTURE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        R = nArray;
        nArray = new int[NameSource.values().length];
        try {
            nArray[NameSource.ScoreBoard.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NameSource.PlayerList.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        m = nArray;
    }
}

