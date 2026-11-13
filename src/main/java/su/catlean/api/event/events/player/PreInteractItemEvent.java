/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class PreInteractItemEvent
extends Event {
    @NotNull
    public static final PreInteractItemEvent INSTANCE = new PreInteractItemEvent();
    @NotNull
    private static Hand hand = Hand.MAIN_HAND;

    private PreInteractItemEvent() {
    }

    @NotNull
    public final Hand getHand() {
        return hand;
    }

    public final void setHand(@NotNull Hand hand) {
        Intrinsics.checkNotNullParameter((Object)hand, "<set-?>");
        PreInteractItemEvent.hand = hand;
    }

    public final boolean call(@NotNull Hand hand) {
        Intrinsics.checkNotNullParameter((Object)hand, "hand");
        PreInteractItemEvent.hand = hand;
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

