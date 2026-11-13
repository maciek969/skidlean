/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class AttackBlockEvent
extends Event {
    @NotNull
    public static final AttackBlockEvent INSTANCE = new AttackBlockEvent();
    @NotNull
    private static Direction direction = Direction.UP;
    @NotNull
    private static BlockPos pos;

    private AttackBlockEvent() {
    }

    @NotNull
    public final Direction getDirection() {
        return direction;
    }

    public final void setDirection(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter(direction, "<set-?>");
        AttackBlockEvent.direction = direction;
    }

    @NotNull
    public final BlockPos getPos() {
        return pos;
    }

    public final void setPos(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "<set-?>");
        pos = blockPos;
    }

    public final boolean call(@NotNull Direction direction, @NotNull BlockPos pos) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        AttackBlockEvent.pos = pos;
        AttackBlockEvent.direction = direction;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        BlockPos blockPos = BlockPos.ORIGIN;
        Intrinsics.checkNotNullExpressionValue(blockPos, "ZERO");
        pos = blockPos;
    }
}

