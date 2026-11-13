/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class StackCollectEvent
extends Event {
    @NotNull
    public static final StackCollectEvent INSTANCE = new StackCollectEvent();
    @NotNull
    private static ItemStack itemStack;

    private StackCollectEvent() {
    }

    @NotNull
    public final ItemStack getItemStack() {
        return itemStack;
    }

    public final void setItemStack(@NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "<set-?>");
        StackCollectEvent.itemStack = itemStack;
    }

    static {
        ItemStack itemStack = Items.DIRT.getDefaultStack();
        Intrinsics.checkNotNullExpressionValue(itemStack, "getDefaultInstance(...)");
        StackCollectEvent.itemStack = itemStack;
    }
}

