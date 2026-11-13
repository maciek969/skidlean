/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import su.catlean.module.basic.AntiKnockBackMode;

public final class AntiKnockBack$WhenMappings {
    public static final int[] D;

    static {
        int[] nArray = new int[AntiKnockBackMode.values().length];
        try {
            nArray[AntiKnockBackMode.Custom.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AntiKnockBackMode.Cancel.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AntiKnockBackMode.OldGrim.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AntiKnockBackMode.GrimNew.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AntiKnockBackMode.GrimBypass.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        D = nArray;
    }
}

