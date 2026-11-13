/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class UpdateCrosshairTarget
extends Event {
    @NotNull
    public static final UpdateCrosshairTarget INSTANCE = new UpdateCrosshairTarget();

    private UpdateCrosshairTarget() {
    }
}

