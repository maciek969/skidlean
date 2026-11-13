/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class WeatherRenderEvent
extends Event {
    @NotNull
    public static final WeatherRenderEvent INSTANCE = new WeatherRenderEvent();

    private WeatherRenderEvent() {
    }
}

