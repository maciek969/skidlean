/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class BlockStateEvent
extends Event {
    @NotNull
    public static final BlockStateEvent INSTANCE = new BlockStateEvent();
    private static BlockPos pos;
    @Nullable
    private static BlockState prevState;
    @Nullable
    private static BlockState state;

    private BlockStateEvent() {
    }

    @NotNull
    public final BlockPos getPos() {
        BlockPos blockPos = pos;
        if (blockPos != null) {
            return blockPos;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pos");
        return null;
    }

    @Nullable
    public final BlockState getPrevState() {
        return prevState;
    }

    public final void setPrevState(@Nullable BlockState blockState) {
        prevState = blockState;
    }

    @Nullable
    public final BlockState getState() {
        return state;
    }

    public final boolean call(@NotNull BlockPos pos, @Nullable BlockState prevState, @Nullable BlockState state) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        BlockStateEvent.pos = pos;
        BlockStateEvent.state = state;
        BlockStateEvent.prevState = prevState;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

