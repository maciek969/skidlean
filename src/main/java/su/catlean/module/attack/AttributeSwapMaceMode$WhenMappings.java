/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.MethodHandles;
import su.catlean.module.attack.AttributeSwapMaceMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AttributeSwapMaceMode$WhenMappings {
    public static final int[] O;
    private static String[] W;

    static {
        long l = ZkmKeys.a(2051992107460895125L, -5479819645875781613L, MethodHandles.lookup().lookupClass()).a(28055980419419L) ^ 0x3175961902F8L;
        int[] nArray = new int[AttributeSwapMaceMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (Object)new String[4], (long)5930243009857504690L, (long)l);
        try {
            nArray[AttributeSwapMaceMode.BREACH.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            nArray[AttributeSwapMaceMode.DENSITY.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        O = nArray;
    }

    public static void zkmSetFlag(String[] stringArray) {
        W = stringArray;
    }

    public static String[] zkmGetFlag() {
        return W;
    }
}

