/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class Render3DEventDepth
extends Event {
    @NotNull
    public static final Render3DEventDepth INSTANCE = new Render3DEventDepth();
    private static MatrixStack stack;
    private static Matrix4f projection;

    private Render3DEventDepth() {
    }

    @NotNull
    public final MatrixStack getStack() {
        MatrixStack matrixStack = stack;
        if (matrixStack != null) {
            return matrixStack;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stack");
        return null;
    }

    @NotNull
    public final Matrix4f getProjection() {
        Matrix4f matrix4f = projection;
        if (matrix4f != null) {
            return matrix4f;
        }
        Intrinsics.throwUninitializedPropertyAccessException("projection");
        return null;
    }

    public final boolean call(@NotNull MatrixStack stack, @NotNull Matrix4f projection) {
        Intrinsics.checkNotNullParameter(stack, "stack");
        Intrinsics.checkNotNullParameter(projection, "projection");
        this.setCancelled(false);
        Render3DEventDepth.stack = stack;
        Render3DEventDepth.projection = projection;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

