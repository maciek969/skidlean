/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class CollisionEvent
extends Event {
    @NotNull
    private BlockState state;
    @NotNull
    private BlockPos pos;

    public CollisionEvent() {
        BlockState blockState = Blocks.AIR.getDefaultState();
        Intrinsics.checkNotNullExpressionValue(blockState, "defaultBlockState(...)");
        this.state = blockState;
        BlockPos blockPos = BlockPos.ORIGIN;
        Intrinsics.checkNotNullExpressionValue(blockPos, "ZERO");
        this.pos = blockPos;
    }

    @NotNull
    public final BlockState getState() {
        return this.state;
    }

    public final void setState(@NotNull BlockState blockState) {
        Intrinsics.checkNotNullParameter(blockState, "<set-?>");
        this.state = blockState;
    }

    @NotNull
    public final BlockPos getPos() {
        return this.pos;
    }

    public final void setPos(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "<set-?>");
        this.pos = blockPos;
    }

    public final boolean call(@NotNull BlockState state, @NotNull BlockPos pos) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        this.pos = pos;
        this.state = state;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

