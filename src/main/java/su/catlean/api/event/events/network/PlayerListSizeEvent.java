/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.network;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class PlayerListSizeEvent
extends Event {
    @NotNull
    public static final PlayerListSizeEvent INSTANCE = new PlayerListSizeEvent();

    private PlayerListSizeEvent() {
    }
}

