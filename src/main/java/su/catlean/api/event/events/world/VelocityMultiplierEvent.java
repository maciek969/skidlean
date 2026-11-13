/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class VelocityMultiplierEvent
extends Event {
    @NotNull
    public static final VelocityMultiplierEvent INSTANCE = new VelocityMultiplierEvent();
    private static Block block;

    private VelocityMultiplierEvent() {
    }

    @NotNull
    public final Block getBlock() {
        Block block = VelocityMultiplierEvent.block;
        if (block != null) {
            return block;
        }
        Intrinsics.throwUninitializedPropertyAccessException("block");
        return null;
    }

    public final boolean call(@NotNull Block block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.setCancelled(false);
        VelocityMultiplierEvent.block = block;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

