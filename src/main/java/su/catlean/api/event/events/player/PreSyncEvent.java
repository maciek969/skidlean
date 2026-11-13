/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.SyncEvent;

public final class PreSyncEvent
extends SyncEvent {
    @NotNull
    public static final PreSyncEvent INSTANCE = new PreSyncEvent();

    private PreSyncEvent() {
    }
}

