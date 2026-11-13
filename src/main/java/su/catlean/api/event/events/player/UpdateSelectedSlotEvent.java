/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class UpdateSelectedSlotEvent
extends Event {
    @NotNull
    public static final UpdateSelectedSlotEvent INSTANCE = new UpdateSelectedSlotEvent();
    private static int slot;

    private UpdateSelectedSlotEvent() {
    }

    public final int getSlot() {
        return slot;
    }

    public final void setSlot(int n) {
        slot = n;
    }
}

