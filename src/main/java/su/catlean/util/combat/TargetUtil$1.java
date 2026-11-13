/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TargetUtil$1
implements Comparator {
    private static final long a = ZkmKeys.a(-8398062320906324946L, 4923199049222960135L, MethodHandles.lookup().lookupClass()).a(52478358323778L);

    public final int compare(Object object, Object object2) {
        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)object;
        Comparable comparable = Float.valueOf(Globals.getPlayer().distanceTo(abstractClientPlayerEntity));
        abstractClientPlayerEntity = (AbstractClientPlayerEntity)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, (Comparable)Float.valueOf(Globals.getPlayer().distanceTo(abstractClientPlayerEntity)));
    }
}

