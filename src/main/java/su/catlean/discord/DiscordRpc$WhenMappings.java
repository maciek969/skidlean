/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import su.catlean.discord.IpcOpcode;

public final class DiscordRpc$WhenMappings {
    public static final int[] y;

    static {
        int[] nArray = new int[IpcOpcode.values().length];
        try {
            nArray[IpcOpcode.FRAME.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[IpcOpcode.CLOSE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        y = nArray;
    }
}

