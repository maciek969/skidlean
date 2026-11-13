/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public class ElytraEvent
extends Event {
    @NotNull
    private Vec3d vec;

    public ElytraEvent() {
        Vec3d vec3d = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
        this.vec = vec3d;
    }

    @NotNull
    public final Vec3d getVec() {
        return this.vec;
    }

    public final void setVec(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        this.vec = vec3d;
    }
}

