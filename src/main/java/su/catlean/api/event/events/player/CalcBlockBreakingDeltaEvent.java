/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class CalcBlockBreakingDeltaEvent
extends Event {
    @NotNull
    public static final CalcBlockBreakingDeltaEvent INSTANCE = new CalcBlockBreakingDeltaEvent();
    public static BlockPos blockPos;
    public static BlockState state;
    private static float delta;

    private CalcBlockBreakingDeltaEvent() {
    }

    @NotNull
    public final BlockPos getBlockPos() {
        BlockPos blockPos = CalcBlockBreakingDeltaEvent.blockPos;
        if (blockPos != null) {
            return blockPos;
        }
        Intrinsics.throwUninitializedPropertyAccessException("blockPos");
        return null;
    }

    public final void setBlockPos(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "<set-?>");
        CalcBlockBreakingDeltaEvent.blockPos = blockPos;
    }

    @NotNull
    public final BlockState getState() {
        BlockState blockState = state;
        if (blockState != null) {
            return blockState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("state");
        return null;
    }

    public final void setState(@NotNull BlockState blockState) {
        Intrinsics.checkNotNullParameter(blockState, "<set-?>");
        state = blockState;
    }

    public final float getDelta() {
        return delta;
    }

    public final void setDelta(float f) {
        delta = f;
    }

    public final boolean call(@NotNull BlockPos pos, @NotNull BlockState state) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        Intrinsics.checkNotNullParameter(state, "state");
        this.setCancelled(false);
        this.setBlockPos(pos);
        INSTANCE.setState(state);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

