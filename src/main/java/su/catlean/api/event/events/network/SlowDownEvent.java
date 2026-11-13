/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.network;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class SlowDownEvent
extends Event {
    @NotNull
    public static final SlowDownEvent INSTANCE = new SlowDownEvent();

    private SlowDownEvent() {
    }
}

