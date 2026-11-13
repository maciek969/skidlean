/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;

public final class ItemCommandEvent
extends Event {
    @NotNull
    public static final ItemCommandEvent INSTANCE = new ItemCommandEvent();
    @Nullable
    private static VertexConsumerProvider provider;

    private ItemCommandEvent() {
    }

    @Nullable
    public final VertexConsumerProvider getProvider() {
        return provider;
    }

    public final void setProvider(@Nullable VertexConsumerProvider vertexConsumerProvider) {
        provider = vertexConsumerProvider;
    }
}

