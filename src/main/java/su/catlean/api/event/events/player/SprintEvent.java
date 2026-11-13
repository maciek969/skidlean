/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class SprintEvent
extends Event {
    @NotNull
    public static final SprintEvent INSTANCE = new SprintEvent();
    private static boolean sprinting;

    private SprintEvent() {
    }

    public final boolean getSprinting() {
        return sprinting;
    }

    public final void setSprinting(boolean bl) {
        sprinting = bl;
    }
}

