/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import su.catlean.module.esp.NameTags;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NameTags$onRender2D$1
implements Comparator {
    private static final long a = ZkmKeys.a(1471641405891890113L, -8921413038051735226L, MethodHandles.lookup().lookupClass()).a(238339136643562L);

    public final int compare(Object object, Object object2) {
        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)object2;
        Entity entity = Globals.getPlayer();
        Vec3d vec3d = NameTags.INSTANCE.getPos(entity);
        Intrinsics.checkNotNull(abstractClientPlayerEntity);
        Comparable comparable = Double.valueOf(vec3d.squaredDistanceTo(NameTags.INSTANCE.getPos(abstractClientPlayerEntity)));
        abstractClientPlayerEntity = (AbstractClientPlayerEntity)object;
        Comparable comparable2 = comparable;
        Entity entity2 = Globals.getPlayer();
        Vec3d vec3d2 = NameTags.INSTANCE.getPos(entity2);
        Intrinsics.checkNotNull(abstractClientPlayerEntity);
        return ComparisonsKt.compareValues(comparable2, vec3d2.squaredDistanceTo(NameTags.INSTANCE.getPos(abstractClientPlayerEntity)));
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

