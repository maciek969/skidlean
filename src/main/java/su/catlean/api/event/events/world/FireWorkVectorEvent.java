/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FireWorkVectorEvent
extends Event {
    @NotNull
    public static final FireWorkVectorEvent INSTANCE = new FireWorkVectorEvent();
    public static Vec3d vector;

    private FireWorkVectorEvent() {
    }

    @NotNull
    public final Vec3d getVector() {
        Vec3d vec3d = vector;
        if (vec3d != null) {
            return vec3d;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vector");
        return null;
    }

    public final void setVector(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        vector = vec3d;
    }

    public final boolean call(@NotNull Vec3d vec) {
        Intrinsics.checkNotNullParameter(vec, "vec");
        this.setVector(vec);
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

