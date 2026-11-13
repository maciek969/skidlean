/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class FlipFrameEvent
extends Event {
    @NotNull
    public static final FlipFrameEvent INSTANCE = new FlipFrameEvent();

    private FlipFrameEvent() {
    }
}

