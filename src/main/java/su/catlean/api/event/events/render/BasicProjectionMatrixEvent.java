/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class BasicProjectionMatrixEvent
extends Event {
    @NotNull
    public static final BasicProjectionMatrixEvent INSTANCE = new BasicProjectionMatrixEvent();
    private static float ratio = 1.0f;
    private static float zoom = 1.0f;
    private static float zoomX = 1.0f;
    private static float zoomY = 1.0f;
    private static float fov = 1.0f;

    private BasicProjectionMatrixEvent() {
    }

    public final float getRatio() {
        return ratio;
    }

    public final void setRatio(float f) {
        ratio = f;
    }

    public final float getZoom() {
        return zoom;
    }

    public final void setZoom(float f) {
        zoom = f;
    }

    public final float getZoomX() {
        return zoomX;
    }

    public final void setZoomX(float f) {
        zoomX = f;
    }

    public final float getZoomY() {
        return zoomY;
    }

    public final void setZoomY(float f) {
        zoomY = f;
    }

    public final float getFov() {
        return fov;
    }

    public final void setFov(float f) {
        fov = f;
    }
}

