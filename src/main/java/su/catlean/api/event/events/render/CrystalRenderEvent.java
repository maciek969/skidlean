/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class CrystalRenderEvent
extends Event {
    @NotNull
    public static final CrystalRenderEvent INSTANCE = new CrystalRenderEvent();

    private CrystalRenderEvent() {
    }
}

