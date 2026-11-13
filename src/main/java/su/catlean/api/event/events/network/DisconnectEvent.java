/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.network;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class DisconnectEvent
extends Event {
    @NotNull
    public static final DisconnectEvent INSTANCE = new DisconnectEvent();

    private DisconnectEvent() {
    }
}

