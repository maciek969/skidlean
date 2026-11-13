/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;

public final class RenderToolTipEvent
extends Event {
    @NotNull
    public static final RenderToolTipEvent INSTANCE = new RenderToolTipEvent();
    @Nullable
    private static ItemStack stack;
    @Nullable
    private static DrawContext context;
    private static int mouseX;
    private static int mouseY;

    private RenderToolTipEvent() {
    }

    @Nullable
    public final ItemStack getStack() {
        return stack;
    }

    public final void setStack(@Nullable ItemStack itemStack) {
        stack = itemStack;
    }

    @Nullable
    public final DrawContext getContext() {
        return context;
    }

    public final void setContext(@Nullable DrawContext drawContext) {
        context = drawContext;
    }

    public final int getMouseX() {
        return mouseX;
    }

    public final void setMouseX(int n) {
        mouseX = n;
    }

    public final int getMouseY() {
        return mouseY;
    }

    public final void setMouseY(int n) {
        mouseY = n;
    }

    public final void call(@NotNull ItemStack stack, @NotNull DrawContext context, int mouseX, int mouseY) {
        Intrinsics.checkNotNullParameter(stack, "stack");
        Intrinsics.checkNotNullParameter(context, "context");
        RenderToolTipEvent.stack = stack;
        RenderToolTipEvent.context = context;
        RenderToolTipEvent.mouseX = mouseX;
        RenderToolTipEvent.mouseY = mouseY;
        this.call();
    }
}

