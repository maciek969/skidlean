/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class WaterPushEvent
extends Event {
    @NotNull
    public static final WaterPushEvent INSTANCE = new WaterPushEvent();
    private static BlockPos pos;
    private static FluidState state;
    private static Fluid fluid;
    public static Vec3d vec;

    private WaterPushEvent() {
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

    @NotNull
    public final FluidState getState() {
        FluidState fluidState = state;
        if (fluidState != null) {
            return fluidState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("state");
        return null;
    }

    @NotNull
    public final Fluid getFluid() {
        Fluid fluid = WaterPushEvent.fluid;
        if (fluid != null) {
            return fluid;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fluid");
        return null;
    }

    @NotNull
    public final Vec3d getVec() {
        Vec3d vec3d = vec;
        if (vec3d != null) {
            return vec3d;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vec");
        return null;
    }

    public final void setVec(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "<set-?>");
        vec = vec3d;
    }

    public final boolean call(@NotNull BlockPos pos, @NotNull Fluid fluid, @NotNull FluidState state, @NotNull Vec3d vec) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        Intrinsics.checkNotNullParameter(fluid, "fluid");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(vec, "vec");
        this.setCancelled(false);
        WaterPushEvent.pos = pos;
        this.setVec(vec);
        WaterPushEvent.fluid = fluid;
        WaterPushEvent.state = state;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

