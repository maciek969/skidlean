/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import su.catlean.module.esp.SearchResult;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Search$onRender3D$1
implements Comparator {
    private static final long a = ZkmKeys.a(4812327757467540131L, -4779279334101556560L, MethodHandles.lookup().lookupClass()).a(141173933098680L);

    public final int compare(Object object, Object object2) {
        SearchResult searchResult = (SearchResult)object;
        Comparable comparable = Double.valueOf(Globals.getPlayer().squaredDistanceTo(searchResult.getPos().toCenterPos()));
        searchResult = (SearchResult)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, Globals.getPlayer().squaredDistanceTo(searchResult.getPos().toCenterPos()));
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

