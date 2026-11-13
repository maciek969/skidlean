/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class GetFogBufferEvent
extends Event {
    @NotNull
    public static final GetFogBufferEvent INSTANCE = new GetFogBufferEvent();

    private GetFogBufferEvent() {
    }
}

