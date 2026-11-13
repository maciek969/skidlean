/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class EntityAlphaEvent
extends Event {
    @NotNull
    public static final EntityAlphaEvent INSTANCE = new EntityAlphaEvent();
    @NotNull
    private static Vec3d pos;
    private static float alpha;

    private EntityAlphaEvent() {
    }

    @NotNull
    public final Vec3d getPos() {
        return pos;
    }

    public final void setPos(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        pos = vec3d;
    }

    public final float getAlpha() {
        return alpha;
    }

    public final void setAlpha(float f) {
        alpha = f;
    }

    public final boolean call(@NotNull Vec3d pos) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        EntityAlphaEvent.pos = pos;
        alpha = 1.0f;
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        Vec3d vec3d = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
        pos = vec3d;
        alpha = 1.0f;
    }
}

