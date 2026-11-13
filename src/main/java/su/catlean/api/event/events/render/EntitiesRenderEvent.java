/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class EntitiesRenderEvent
extends Event {
    @NotNull
    public static final EntitiesRenderEvent INSTANCE = new EntitiesRenderEvent();
    private static MatrixStack stack;

    private EntitiesRenderEvent() {
    }

    @NotNull
    public final MatrixStack getStack() {
        MatrixStack matrixStack = stack;
        if (matrixStack != null) {
            return matrixStack;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stack");
        return null;
    }

    public final boolean call(@NotNull MatrixStack stack) {
        Intrinsics.checkNotNullParameter(stack, "stack");
        this.setCancelled(false);
        EntitiesRenderEvent.stack = stack;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

