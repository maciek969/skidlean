/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class ShowFloatingItemEvent
extends Event {
    @NotNull
    public static final ShowFloatingItemEvent INSTANCE = new ShowFloatingItemEvent();
    public static ItemStack item;

    private ShowFloatingItemEvent() {
    }

    @NotNull
    public final ItemStack getItem() {
        ItemStack itemStack = item;
        if (itemStack != null) {
            return itemStack;
        }
        Intrinsics.throwUninitializedPropertyAccessException("item");
        return null;
    }

    public final void setItem(@NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "<set-?>");
        item = itemStack;
    }

    public final boolean call(@NotNull ItemStack item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.setCancelled(false);
        this.setItem(item);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

