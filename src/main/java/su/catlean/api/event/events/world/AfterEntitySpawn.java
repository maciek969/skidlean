/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class AfterEntitySpawn
extends Event {
    @NotNull
    public static final AfterEntitySpawn INSTANCE = new AfterEntitySpawn();
    private static Entity entity;

    private AfterEntitySpawn() {
    }

    @NotNull
    public final Entity getEntity() {
        Entity entity = AfterEntitySpawn.entity;
        if (entity != null) {
            return entity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entity");
        return null;
    }

    public final boolean call(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        this.setCancelled(false);
        AfterEntitySpawn.entity = entity;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

