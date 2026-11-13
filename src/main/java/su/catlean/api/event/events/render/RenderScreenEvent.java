/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class RenderScreenEvent
extends Event {
    @NotNull
    public static final RenderScreenEvent INSTANCE = new RenderScreenEvent();
    private static DrawContext context;

    private RenderScreenEvent() {
    }

    @NotNull
    public final DrawContext getContext() {
        DrawContext drawContext = context;
        if (drawContext != null) {
            return drawContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final boolean call(@NotNull DrawContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.setCancelled(false);
        RenderScreenEvent.context = context;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

