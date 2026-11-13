/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class EntitySpawn
extends Event {
    @NotNull
    public static final EntitySpawn INSTANCE = new EntitySpawn();
    private static Entity entity;

    private EntitySpawn() {
    }

    @NotNull
    public final Entity getEntity() {
        Entity entity = EntitySpawn.entity;
        if (entity != null) {
            return entity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entity");
        return null;
    }

    public final boolean call(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        this.setCancelled(false);
        EntitySpawn.entity = entity;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

