/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class EventPostPlayerTravel
extends Event {
    @NotNull
    public static final EventPostPlayerTravel INSTANCE = new EventPostPlayerTravel();

    private EventPostPlayerTravel() {
    }
}

