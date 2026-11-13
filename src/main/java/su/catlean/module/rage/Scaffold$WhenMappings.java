/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import su.catlean.module.rage.ScaffoldAutoSwitchMode;
import su.catlean.module.rage.ScaffoldTimingMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Scaffold$WhenMappings {
    public static final int[] N;
    public static final int[] V;
    private static int[] W;

    static {
        long l = ZkmKeys.a(-5754272858510067567L, 7660310438428181429L, MethodHandles.lookup().lookupClass()).a(62896063864868L) ^ 0xBE9A417396CL;
        int[] nArray = new int[ScaffoldTimingMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[2], (long)-8750728167761357216L, (long)l);
        try {
            nArray[ScaffoldTimingMode.SEQUENTIAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[ScaffoldTimingMode.VANILLA.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[ScaffoldTimingMode.GRIM.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[ScaffoldTimingMode.GRIM_BYPASS.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        N = nArray;
        nArray = new int[ScaffoldAutoSwitchMode.values().length];
        try {
            nArray[ScaffoldAutoSwitchMode.Inventory.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[ScaffoldAutoSwitchMode.Normal.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[ScaffoldAutoSwitchMode.Silent.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        V = nArray;
    }

    public static void zkmSetFlag(int[] nArray) {
        W = nArray;
    }

    public static int[] zkmGetFlag() {
        return W;
    }
}

