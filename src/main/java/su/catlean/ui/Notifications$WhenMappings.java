/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui;

import su.catlean.ui.NotificationType;

public final class Notifications$WhenMappings {
    public static final int[] M;

    static {
        int[] nArray = new int[NotificationType.values().length];
        try {
            nArray[NotificationType.DISABLED.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NotificationType.ENABLED.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        M = nArray;
    }
}

