/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class PostInteractItemEvent
extends Event {
    @NotNull
    public static final PostInteractItemEvent INSTANCE = new PostInteractItemEvent();

    private PostInteractItemEvent() {
    }
}

