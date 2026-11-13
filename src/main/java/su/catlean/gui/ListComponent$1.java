/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

public final class ListComponent$1
implements Comparator {
    public final int compare(Object object, Object object2) {
        String string = (String)object;
        Comparable comparable = (Comparable)((Object)string);
        string = (String)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, (Comparable)((Object)string));
    }
}

