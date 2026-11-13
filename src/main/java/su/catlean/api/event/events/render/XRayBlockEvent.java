/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class XRayBlockEvent
extends Event {
    @NotNull
    private Block block;

    public XRayBlockEvent() {
        Block block = Blocks.DIRT;
        Intrinsics.checkNotNullExpressionValue(block, "DIRT");
        this.block = block;
    }

    @NotNull
    public final Block getBlock() {
        return this.block;
    }

    public final void setBlock(@NotNull Block block) {
        Intrinsics.checkNotNullParameter(block, "<set-?>");
        this.block = block;
    }

    public final boolean call(@NotNull Block block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.setCancelled(false);
        this.block = block;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

