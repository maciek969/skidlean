/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FixVelocityEvent
extends Event {
    @NotNull
    public static final FixVelocityEvent INSTANCE = new FixVelocityEvent();
    @NotNull
    private static Vec3d movementInput = new Vec3d(0.0, 0.0, 0.0);
    private static float speed;
    private static float yaw;
    @NotNull
    private static Vec3d velocity;

    private FixVelocityEvent() {
    }

    @NotNull
    public final Vec3d getMovementInput() {
        return movementInput;
    }

    public final void setMovementInput(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        movementInput = vec3d;
    }

    public final float getSpeed() {
        return speed;
    }

    public final void setSpeed(float f) {
        speed = f;
    }

    public final float getYaw() {
        return yaw;
    }

    public final void setYaw(float f) {
        yaw = f;
    }

    @NotNull
    public final Vec3d getVelocity() {
        return velocity;
    }

    public final void setVelocity(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        velocity = vec3d;
    }

    public final boolean call(@NotNull Vec3d movementInput, float speed, float yaw) {
        Intrinsics.checkNotNullParameter(movementInput, "movementInput");
        this.setCancelled(false);
        FixVelocityEvent.movementInput = movementInput;
        FixVelocityEvent.speed = speed;
        FixVelocityEvent.yaw = yaw;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        velocity = new Vec3d(0.0, 0.0, 0.0);
    }
}

