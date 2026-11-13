/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import su.catlean.module.esp.ItemGroup;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ItemEsp$onRender2D$1
implements Comparator {
    private static final long a = ZkmKeys.a(-4108389660852814468L, -3529977151439157233L, MethodHandles.lookup().lookupClass()).a(178748269017660L);

    public final int compare(Object object, Object object2) {
        ItemGroup.zkmIntConstant();
        ItemGroup itemGroup = (ItemGroup)object2;
        Comparable comparable = Double.valueOf(Globals.getPlayer().squaredDistanceTo(itemGroup.f()));
        itemGroup = (ItemGroup)object;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().squaredDistanceTo(itemGroup.f()));
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

