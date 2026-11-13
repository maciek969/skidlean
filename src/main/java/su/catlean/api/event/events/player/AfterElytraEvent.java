/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.ElytraEvent;
import su.catlean.gofra.Gofra;

public final class AfterElytraEvent
extends ElytraEvent {
    @NotNull
    public static final AfterElytraEvent INSTANCE = new AfterElytraEvent();

    private AfterElytraEvent() {
    }

    public final boolean call(@NotNull Vec3d vec) {
        Intrinsics.checkNotNullParameter(vec, "vec");
        this.setCancelled(false);
        this.setVec(vec);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

