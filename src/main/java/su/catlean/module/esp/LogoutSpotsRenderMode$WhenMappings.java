/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import su.catlean.module.esp.LogoutSpotsRenderMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class LogoutSpotsRenderMode$WhenMappings {
    public static final int[] o;
    private static String[] N;

    static {
        long l = ZkmKeys.a(8225791897810224488L, 3182479557769148131L, MethodHandles.lookup().lookupClass()).a(155962638002754L) ^ 0x1E015022255BL;
        int[] nArray = new int[LogoutSpotsRenderMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)-5047445844435204881L, (long)l);
        try {
            nArray[LogoutSpotsRenderMode.GHOST.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[LogoutSpotsRenderMode.DEFAULT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        o = nArray;
    }

    public static void zkmSetFlag(String[] stringArray) {
        N = stringArray;
    }

    public static String[] zkmGetFlag() {
        return N;
    }
}

