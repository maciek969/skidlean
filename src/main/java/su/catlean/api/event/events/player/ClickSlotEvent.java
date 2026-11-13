/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class ClickSlotEvent
extends Event {
    @NotNull
    public static final ClickSlotEvent INSTANCE = new ClickSlotEvent();
    @NotNull
    private static SlotActionType action = SlotActionType.PICKUP;
    private static int id;
    private static int button;
    private static int syncId;

    private ClickSlotEvent() {
    }

    @NotNull
    public final SlotActionType getAction() {
        return action;
    }

    public final void setAction(@NotNull SlotActionType slotActionType) {
        Intrinsics.checkNotNullParameter((Object)slotActionType, "<set-?>");
        action = slotActionType;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int n) {
        id = n;
    }

    public final int getButton() {
        return button;
    }

    public final void setButton(int n) {
        button = n;
    }

    public final int getSyncId() {
        return syncId;
    }

    public final void setSyncId(int n) {
        syncId = n;
    }

    public final boolean call(@NotNull SlotActionType action, int id, int button, int syncId) {
        Intrinsics.checkNotNullParameter((Object)action, "action");
        this.setCancelled(false);
        ClickSlotEvent.action = action;
        ClickSlotEvent.id = id;
        ClickSlotEvent.button = button;
        ClickSlotEvent.syncId = syncId;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

