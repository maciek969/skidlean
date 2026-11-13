/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import su.catlean.render.ShaderMode;

public final class HandChams$WhenMappings {
    public static final int[] K;

    static {
        int[] nArray = new int[ShaderMode.values().length];
        try {
            nArray[ShaderMode.MIRROR.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ShaderMode.DEFAULT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ShaderMode.BLOOM.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ShaderMode.DOUBLE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ShaderMode.CAMOUFLAGE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        K = nArray;
    }
}

