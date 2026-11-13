/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui.setting;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import su.catlean.setting.Setting;

public final class GroupElement$1
implements Comparator {
    public final int compare(Object object, Object object2) {
        Setting setting2 = (Setting)object;
        Comparable comparable = Integer.valueOf(setting2.Q().length());
        setting2 = (Setting)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, setting2.Q().length());
    }
}

