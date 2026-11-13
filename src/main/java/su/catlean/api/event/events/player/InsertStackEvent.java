/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class InsertStackEvent
extends Event {
    @NotNull
    public static final InsertStackEvent INSTANCE = new InsertStackEvent();
    @NotNull
    private static ItemStack stack;

    private InsertStackEvent() {
    }

    @NotNull
    public final ItemStack getStack() {
        return stack;
    }

    public final void setStack(@NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "<set-?>");
        stack = itemStack;
    }

    static {
        ItemStack itemStack = ItemStack.EMPTY;
        Intrinsics.checkNotNullExpressionValue(itemStack, "EMPTY");
        stack = itemStack;
    }
}

