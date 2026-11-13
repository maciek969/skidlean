/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.client;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class PostTitleScreenInitEvent
extends Event {
    @NotNull
    public static final PostTitleScreenInitEvent INSTANCE = new PostTitleScreenInitEvent();

    private PostTitleScreenInitEvent() {
    }
}

