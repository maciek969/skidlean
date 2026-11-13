/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class BossBarRenderEvent
extends Event {
    @NotNull
    public static final BossBarRenderEvent INSTANCE = new BossBarRenderEvent();

    private BossBarRenderEvent() {
    }
}

