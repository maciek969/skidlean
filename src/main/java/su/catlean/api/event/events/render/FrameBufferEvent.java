/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import net.minecraft.client.gl.Framebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;

public final class FrameBufferEvent
extends Event {
    @NotNull
    public static final FrameBufferEvent INSTANCE = new FrameBufferEvent();
    @Nullable
    private static Framebuffer frameBuffer;

    private FrameBufferEvent() {
    }

    @Nullable
    public final Framebuffer getFrameBuffer() {
        return frameBuffer;
    }

    public final void setFrameBuffer(@Nullable Framebuffer framebuffer) {
        frameBuffer = framebuffer;
    }
}

