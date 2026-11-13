/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FinishUsingItemEvent
extends Event {
    @NotNull
    public static final FinishUsingItemEvent INSTANCE = new FinishUsingItemEvent();
    @Nullable
    private static ItemStack item;

    private FinishUsingItemEvent() {
    }

    @Nullable
    public final ItemStack getItem() {
        return item;
    }

    public final void setItem(@Nullable ItemStack itemStack) {
        item = itemStack;
    }

    public final boolean call(@NotNull ItemStack item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.setCancelled(false);
        FinishUsingItemEvent.item = item;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

