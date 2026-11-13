/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

public final class SettingGroupElement$1
implements Comparator {
    public final int compare(Object object, Object object2) {
        Enum enum_ = (Enum)object;
        Comparable comparable = Integer.valueOf(enum_.name().length());
        enum_ = (Enum)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, enum_.name().length());
    }
}

