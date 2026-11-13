/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.MethodHandles;
import su.catlean.module.Module;
import su.catlean.module.equip.ChestStealerSortMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ChestStealerSortMode$WhenMappings {
    public static final int[] L;
    private static Module[] c;

    static {
        long l = ZkmKeys.a(-5232878641821488569L, 4003668326182994273L, MethodHandles.lookup().lookupClass()).a(148165987628268L) ^ 0x1AB9CBD9D5E8L;
        int[] nArray = new int[ChestStealerSortMode.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[4], (long)702317868093189318L, (long)l);
        try {
            nArray[ChestStealerSortMode.NONE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[ChestStealerSortMode.WHITE_LIST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        L = nArray;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        c = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return c;
    }
}

