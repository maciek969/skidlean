/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.SyncEvent;

public final class PostSyncEvent
extends SyncEvent {
    @NotNull
    public static final PostSyncEvent INSTANCE = new PostSyncEvent();

    private PostSyncEvent() {
    }
}

