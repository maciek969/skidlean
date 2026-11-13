/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class Render2DEvent
extends Event {
    @NotNull
    public static final Render2DEvent INSTANCE = new Render2DEvent();
    private static DrawContext context;

    private Render2DEvent() {
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
        Render2DEvent.context = context;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

