/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class RenderNameTagEvent
extends Event {
    @NotNull
    public static final RenderNameTagEvent INSTANCE = new RenderNameTagEvent();
    public static EntityRenderState state;

    private RenderNameTagEvent() {
    }

    @NotNull
    public final EntityRenderState getState() {
        EntityRenderState entityRenderState = state;
        if (entityRenderState != null) {
            return entityRenderState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("state");
        return null;
    }

    public final void setState(@NotNull EntityRenderState entityRenderState) {
        Intrinsics.checkNotNullParameter(entityRenderState, "<set-?>");
        state = entityRenderState;
    }

    public final boolean call(@NotNull EntityRenderState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.setState(state);
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

