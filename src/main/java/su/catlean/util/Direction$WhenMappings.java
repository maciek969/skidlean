/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import net.minecraft.util.math.Direction;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Direction$WhenMappings {
    public static final int[] F;
    private static boolean L;

    static {
        long l = ZkmKeys.a(3184539057178708915L, 7545630833672240559L, MethodHandles.lookup().lookupClass()).a(78132414378253L) ^ 0x534DF4B118D2L;
        int[] nArray = new int[Direction.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (boolean)true, (long)1428327288083525750L, (long)l);
        try {
            nArray[Direction.EAST.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[Direction.NORTH.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[Direction.SOUTH.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[Direction.WEST.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        F = nArray;
    }

    public static void zkmSetFlag(boolean bl) {
        L = bl;
    }

    public static boolean zkmGetFlag() {
        return L;
    }

    public static boolean m() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

