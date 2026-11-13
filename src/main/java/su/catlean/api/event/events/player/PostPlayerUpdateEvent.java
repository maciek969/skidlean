/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class PostPlayerUpdateEvent
extends Event {
    @NotNull
    public static final PostPlayerUpdateEvent INSTANCE = new PostPlayerUpdateEvent();

    private PostPlayerUpdateEvent() {
    }
}

