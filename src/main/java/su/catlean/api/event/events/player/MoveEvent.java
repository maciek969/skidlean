/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class MoveEvent
extends Event {
    @NotNull
    public static final MoveEvent INSTANCE = new MoveEvent();
    private static double x;
    private static double y;
    private static double z;

    private MoveEvent() {
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

    public final boolean call(double x, double y, double z) {
        this.setCancelled(false);
        MoveEvent.x = x;
        MoveEvent.y = y;
        MoveEvent.z = z;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

