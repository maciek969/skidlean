/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class SwingDurationEvent
extends Event {
    @NotNull
    public static final SwingDurationEvent INSTANCE = new SwingDurationEvent();
    private static int value = 12;

    private SwingDurationEvent() {
    }

    public final int getValue() {
        return value;
    }

    public final void setValue(int n) {
        value = n;
    }
}

