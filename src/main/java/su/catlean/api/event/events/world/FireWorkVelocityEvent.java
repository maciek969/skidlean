/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FireWorkVelocityEvent
extends Event {
    @NotNull
    public static final FireWorkVelocityEvent INSTANCE = new FireWorkVelocityEvent();
    @Nullable
    private static Vec3d velocity;

    private FireWorkVelocityEvent() {
    }

    @Nullable
    public final Vec3d getVelocity() {
        return velocity;
    }

    public final void setVelocity(@Nullable Vec3d vec3d) {
        velocity = vec3d;
    }

    public final boolean call(@NotNull Vec3d vec) {
        Intrinsics.checkNotNullParameter(vec, "vec");
        velocity = vec;
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

