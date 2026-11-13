/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.network;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class AfterReceivePacket
extends Event {
    @NotNull
    private final Packet<?> packet;

    public AfterReceivePacket(@NotNull Packet<?> packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        this.packet = packet;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }
}

