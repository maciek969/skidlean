/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class ScoreBoardRenderEvent
extends Event {
    @NotNull
    public static final ScoreBoardRenderEvent INSTANCE = new ScoreBoardRenderEvent();

    private ScoreBoardRenderEvent() {
    }
}

