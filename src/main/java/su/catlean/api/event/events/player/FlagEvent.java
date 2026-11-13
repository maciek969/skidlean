/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FlagEvent
extends Event {
    @Nullable
    private PlayerPositionLookS2CPacket pos;

    @Nullable
    public final PlayerPositionLookS2CPacket getPos() {
        return this.pos;
    }

    public final void setPos(@Nullable PlayerPositionLookS2CPacket playerPositionLookS2CPacket) {
        this.pos = playerPositionLookS2CPacket;
    }

    public final boolean call(@NotNull PlayerPositionLookS2CPacket pos) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        this.pos = pos;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

