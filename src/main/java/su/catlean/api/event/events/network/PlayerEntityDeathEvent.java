/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.network;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;

public final class PlayerEntityDeathEvent
extends Event {
    @NotNull
    public static final PlayerEntityDeathEvent INSTANCE = new PlayerEntityDeathEvent();
    @Nullable
    private static PlayerEntity player;
    private static int totemsPopped;

    private PlayerEntityDeathEvent() {
    }

    @Nullable
    public final PlayerEntity getPlayer() {
        return player;
    }

    public final void setPlayer(@Nullable PlayerEntity playerEntity) {
        player = playerEntity;
    }

    public final int getTotemsPopped() {
        return totemsPopped;
    }

    public final void setTotemsPopped(int n) {
        totemsPopped = n;
    }
}

