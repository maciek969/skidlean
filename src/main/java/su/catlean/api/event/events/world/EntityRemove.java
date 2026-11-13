/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class EntityRemove
extends Event {
    @NotNull
    public static final EntityRemove INSTANCE = new EntityRemove();
    @Nullable
    private static Entity entity;

    private EntityRemove() {
    }

    @Nullable
    public final Entity getEntity() {
        return entity;
    }

    public final boolean call(@Nullable Entity entity) {
        this.setCancelled(false);
        EntityRemove.entity = entity;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

