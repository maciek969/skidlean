/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class InteractBlockEvent
extends Event {
    @NotNull
    public static final InteractBlockEvent INSTANCE = new InteractBlockEvent();
    public static Hand hand;
    public static BlockHitResult hitResult;

    private InteractBlockEvent() {
    }

    @NotNull
    public final Hand getHand() {
        Hand hand = InteractBlockEvent.hand;
        if (hand != null) {
            return hand;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hand");
        return null;
    }

    public final void setHand(@NotNull Hand hand) {
        Intrinsics.checkNotNullParameter((Object)hand, "<set-?>");
        InteractBlockEvent.hand = hand;
    }

    @NotNull
    public final BlockHitResult getHitResult() {
        BlockHitResult blockHitResult = hitResult;
        if (blockHitResult != null) {
            return blockHitResult;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hitResult");
        return null;
    }

    public final void setHitResult(@NotNull BlockHitResult blockHitResult) {
        Intrinsics.checkNotNullParameter(blockHitResult, "<set-?>");
        hitResult = blockHitResult;
    }

    public final boolean call(@NotNull Hand hand, @NotNull BlockHitResult hitResult) {
        Intrinsics.checkNotNullParameter((Object)hand, "hand");
        Intrinsics.checkNotNullParameter(hitResult, "hitResult");
        this.setHand(hand);
        this.setHitResult(hitResult);
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

