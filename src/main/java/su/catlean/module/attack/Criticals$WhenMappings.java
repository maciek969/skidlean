/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import su.catlean.module.attack.CriticalsMode;
import su.catlean.module.attack.CriticalsNcpMode;

public final class Criticals$WhenMappings {
    public static final int[] K;
    public static final int[] h;

    static {
        int[] nArray = new int[CriticalsMode.values().length];
        try {
            nArray[CriticalsMode.NCP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[CriticalsMode.CUSTOM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[CriticalsMode.GRIM_BYPASS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        K = nArray;
        nArray = new int[CriticalsNcpMode.values().length];
        try {
            nArray[CriticalsNcpMode.OLD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[CriticalsNcpMode.NORMAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[CriticalsNcpMode.NEW.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[CriticalsNcpMode.STRICT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        h = nArray;
    }
}

