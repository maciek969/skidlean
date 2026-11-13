/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class UpdateBlockBreakingProgressEvent
extends Event {
    @NotNull
    public static final UpdateBlockBreakingProgressEvent INSTANCE = new UpdateBlockBreakingProgressEvent();

    private UpdateBlockBreakingProgressEvent() {
    }
}

