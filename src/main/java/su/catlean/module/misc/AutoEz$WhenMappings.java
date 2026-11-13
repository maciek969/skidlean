/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import su.catlean.module.misc.AutoEzMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AutoEz$WhenMappings {
    public static final int[] A;
    private static String[] m;

    static {
        long l = ZkmKeys.a(-8294004098769026650L, -6821158249576863314L, MethodHandles.lookup().lookupClass()).a(226051231569457L) ^ 0x62515A536EEAL;
        int[] nArray = new int[AutoEzMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)5477317425195937561L, (long)l);
        try {
            nArray[AutoEzMode.GLOBAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoEzMode.LOCAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoEzMode.WHISPERS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[AutoEzMode.SILENT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        A = nArray;
    }

    public static void zkmSetFlag(String[] stringArray) {
        m = stringArray;
    }

    public static String[] zkmGetFlag() {
        return m;
    }
}

