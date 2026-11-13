/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class SlotRenderEvent
extends Event {
    @NotNull
    public static final SlotRenderEvent INSTANCE = new SlotRenderEvent();
    public static DrawContext context;
    public static Slot slot;

    private SlotRenderEvent() {
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

    public final void setContext(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "<set-?>");
        context = drawContext;
    }

    @NotNull
    public final Slot getSlot() {
        Slot slot = SlotRenderEvent.slot;
        if (slot != null) {
            return slot;
        }
        Intrinsics.throwUninitializedPropertyAccessException("slot");
        return null;
    }

    public final void setSlot(@NotNull Slot slot) {
        Intrinsics.checkNotNullParameter(slot, "<set-?>");
        SlotRenderEvent.slot = slot;
    }
}

