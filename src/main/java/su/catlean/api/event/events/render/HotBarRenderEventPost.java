/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class HotBarRenderEventPost
extends Event {
    @NotNull
    public static final HotBarRenderEventPost INSTANCE = new HotBarRenderEventPost();

    private HotBarRenderEventPost() {
    }
}

