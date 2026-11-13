/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class HandModifyEvent
extends Event {
    @NotNull
    public static final HandModifyEvent INSTANCE = new HandModifyEvent();
    @NotNull
    private static Hand hand = Hand.MAIN_HAND;
    @NotNull
    private static MatrixStack stack = new MatrixStack();
    @NotNull
    private static ItemStack item;
    private static boolean eating;

    private HandModifyEvent() {
    }

    @NotNull
    public final Hand getHand() {
        return hand;
    }

    public final void setHand(@NotNull Hand hand) {
        Intrinsics.checkNotNullParameter((Object)hand, "<set-?>");
        HandModifyEvent.hand = hand;
    }

    @NotNull
    public final MatrixStack getStack() {
        return stack;
    }

    public final void setStack(@NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "<set-?>");
        stack = matrixStack;
    }

    @NotNull
    public final ItemStack getItem() {
        return item;
    }

    public final void setItem(@NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "<set-?>");
        item = itemStack;
    }

    public final boolean getEating() {
        return eating;
    }

    public final void setEating(boolean bl) {
        eating = bl;
    }

    public final boolean call(@NotNull Hand hand, @NotNull MatrixStack stack, @NotNull ItemStack item, boolean eating) {
        Intrinsics.checkNotNullParameter((Object)hand, "hand");
        Intrinsics.checkNotNullParameter(stack, "stack");
        Intrinsics.checkNotNullParameter(item, "item");
        this.setCancelled(false);
        HandModifyEvent.stack = stack;
        HandModifyEvent.hand = hand;
        HandModifyEvent.item = item;
        HandModifyEvent.eating = eating;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        ItemStack itemStack = Items.AIR.getDefaultStack();
        Intrinsics.checkNotNullExpressionValue(itemStack, "getDefaultInstance(...)");
        item = itemStack;
    }
}

