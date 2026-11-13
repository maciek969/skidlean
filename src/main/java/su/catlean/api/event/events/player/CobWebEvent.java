/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class CobWebEvent
extends Event {
    @NotNull
    public static final CobWebEvent INSTANCE = new CobWebEvent();
    @NotNull
    private static BlockPos pos;

    private CobWebEvent() {
    }

    @NotNull
    public final BlockPos getPos() {
        return pos;
    }

    public final void setPos(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "<set-?>");
        pos = blockPos;
    }

    public final boolean call(@NotNull BlockPos pos) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        CobWebEvent.pos = pos;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        BlockPos blockPos = BlockPos.ORIGIN;
        Intrinsics.checkNotNullExpressionValue(blockPos, "ZERO");
        pos = blockPos;
    }
}

