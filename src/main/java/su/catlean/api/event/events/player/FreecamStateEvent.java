/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class FreecamStateEvent
extends Event {
    @NotNull
    public static final FreecamStateEvent INSTANCE = new FreecamStateEvent();
    private static double x;
    private static double y;
    private static double z;
    private static float yaw;
    private static float pitch;

    private FreecamStateEvent() {
    }

    public final double getX() {
        return x;
    }

    public final void setX(double d) {
        x = d;
    }

    public final double getY() {
        return y;
    }

    public final void setY(double d) {
        y = d;
    }

    public final double getZ() {
        return z;
    }

    public final void setZ(double d) {
        z = d;
    }

    public final float getYaw() {
        return yaw;
    }

    public final void setYaw(float f) {
        yaw = f;
    }

    public final float getPitch() {
        return pitch;
    }

    public final void setPitch(float f) {
        pitch = f;
    }
}

