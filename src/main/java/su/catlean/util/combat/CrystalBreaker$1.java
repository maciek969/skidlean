/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import net.minecraft.util.math.Vec3d;

public final class CrystalBreaker$1
implements Comparator {
    final Vec3d n;

    public CrystalBreaker$1(Vec3d vec3d) {
        this.n = vec3d;
    }

    public final int compare(Object object, Object object2) {
        Vec3d vec3d = (Vec3d)object;
        Comparable comparable = Double.valueOf(this.n.distanceTo(vec3d));
        vec3d = (Vec3d)object2;
        Comparable comparable2 = comparable;
        return ComparisonsKt.compareValues(comparable2, this.n.distanceTo(vec3d));
    }
}

