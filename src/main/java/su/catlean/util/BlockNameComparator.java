/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.block.Block;

public final class BlockNameComparator
implements Comparator {
    public final int compare(Object object, Object object2) {
        Block block = (Block)object;
        Comparable comparable = (Comparable)((Object)block.getName().getString());
        block = (Block)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, (Comparable)((Object)block.getName().getString()));
    }
}

