/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import java.lang.invoke.MethodHandles;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import net.minecraft.util.math.Direction;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class WindHop$EntriesMappings {
    public static final EnumEntries enumEntries;
    private static String x;

    static {
        long l = ZkmKeys.a(-555114785115173781L, 5221349638151657294L, MethodHandles.lookup().lookupClass()).a(82272977165517L) ^ 0x5AE09AD4D786L;
        enumEntries = EnumEntriesKt.enumEntries((Enum[])Direction.values());
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)2386593419600910249L, (long)l);
    }

    public static void zkmSetFlag(String string) {
        x = string;
    }

    public static String zkmGetFlag() {
        return x;
    }
}

