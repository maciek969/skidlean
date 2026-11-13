/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class LookRenderEvent
extends Event {
    @NotNull
    public static final LookRenderEvent INSTANCE = new LookRenderEvent();
    private static float yaw;
    private static float pitch;
    private static float bodyYaw;

    private LookRenderEvent() {
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

    public final float getBodyYaw() {
        return bodyYaw;
    }

    public final void setBodyYaw(float f) {
        bodyYaw = f;
    }

    public final boolean call(float yaw, float pitch, float bodyYaw) {
        this.setCancelled(false);
        LookRenderEvent.yaw = yaw;
        LookRenderEvent.pitch = pitch;
        LookRenderEvent.bodyYaw = bodyYaw;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

