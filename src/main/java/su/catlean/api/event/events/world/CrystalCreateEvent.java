/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class CrystalCreateEvent
extends Event {
    @NotNull
    public static final CrystalCreateEvent INSTANCE = new CrystalCreateEvent();

    private CrystalCreateEvent() {
    }
}

