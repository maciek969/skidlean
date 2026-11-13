/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.client;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class InitEvent
extends Event {
    @NotNull
    public static final InitEvent INSTANCE = new InitEvent();

    private InitEvent() {
    }
}

