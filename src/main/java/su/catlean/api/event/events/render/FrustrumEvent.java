/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.Frustum;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class FrustrumEvent
extends Event {
    @NotNull
    public static final FrustrumEvent INSTANCE = new FrustrumEvent();
    public static Frustum frustrum;

    private FrustrumEvent() {
    }

    @NotNull
    public final Frustum getFrustrum() {
        Frustum frustum = frustrum;
        if (frustum != null) {
            return frustum;
        }
        Intrinsics.throwUninitializedPropertyAccessException("frustrum");
        return null;
    }

    public final void setFrustrum(@NotNull Frustum frustum) {
        Intrinsics.checkNotNullParameter(frustum, "<set-?>");
        frustrum = frustum;
    }
}

