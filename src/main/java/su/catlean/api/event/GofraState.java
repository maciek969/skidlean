/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event;

import org.jetbrains.annotations.NotNull;

public final class GofraState {
    @NotNull
    public static final GofraState INSTANCE = new GofraState();
    private static boolean modifyBuffer;
    private static boolean modifyCollisions;
    private static boolean shouldRender;
    private static boolean stopSwapBuffers;
    private static boolean xray;

    private GofraState() {
    }

    public final boolean getModifyBuffer() {
        return modifyBuffer;
    }

    public final void setModifyBuffer(boolean bl) {
        modifyBuffer = bl;
    }

    public final boolean getModifyCollisions() {
        return modifyCollisions;
    }

    public final void setModifyCollisions(boolean bl) {
        modifyCollisions = bl;
    }

    public final boolean getShouldRender() {
        return shouldRender;
    }

    public final void setShouldRender(boolean bl) {
        shouldRender = bl;
    }

    public final boolean getStopSwapBuffers() {
        return stopSwapBuffers;
    }

    public final void setStopSwapBuffers(boolean bl) {
        stopSwapBuffers = bl;
    }

    public final boolean getXray() {
        return xray;
    }

    public final void setXray(boolean bl) {
        xray = bl;
    }
}

