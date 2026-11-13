/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import su.catlean.module.esp.VoidEspMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class VoidEsp$WhenMappings {
    public static final int[] u;
    private static boolean n;

    static {
        long l = ZkmKeys.a(9011180962554359920L, 6156531790283830354L, MethodHandles.lookup().lookupClass()).a(88503043084977L) ^ 0x2AAAD2BDDF51L;
        int[] nArray = new int[VoidEspMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (boolean)true, (long)6911923896606059493L, (long)l);
        try {
            nArray[VoidEspMode.FLAT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[VoidEspMode.BOX.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[VoidEspMode.WIRE_FRAME.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[VoidEspMode.CROSS.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        u = nArray;
    }

    public static void zkmSetFlag(boolean bl) {
        n = bl;
    }

    public static boolean zkmGetFlag() {
        return n;
    }

    public static boolean x() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

