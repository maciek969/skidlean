/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class HurtTiltEvent
extends Event {
    @NotNull
    public static final HurtTiltEvent INSTANCE = new HurtTiltEvent();

    private HurtTiltEvent() {
    }
}

