/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class SignTextRenderEvent
extends Event {
    @NotNull
    public static final SignTextRenderEvent INSTANCE = new SignTextRenderEvent();
    private static BlockPos pos;

    private SignTextRenderEvent() {
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

    public final boolean call(@NotNull BlockPos pos) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        this.setCancelled(false);
        SignTextRenderEvent.pos = pos;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

