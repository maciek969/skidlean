/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class StopUsingItemEvent
extends Event {
    @NotNull
    public static final StopUsingItemEvent INSTANCE = new StopUsingItemEvent();

    private StopUsingItemEvent() {
    }
}

