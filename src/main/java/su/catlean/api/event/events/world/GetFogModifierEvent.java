/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class GetFogModifierEvent
extends Event {
    @NotNull
    public static final GetFogModifierEvent INSTANCE = new GetFogModifierEvent();

    private GetFogModifierEvent() {
    }
}

