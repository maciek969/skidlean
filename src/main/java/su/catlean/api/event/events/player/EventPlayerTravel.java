/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class EventPlayerTravel
extends Event {
    @NotNull
    public static final EventPlayerTravel INSTANCE = new EventPlayerTravel();

    private EventPlayerTravel() {
    }
}

