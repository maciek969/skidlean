/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class BoxContractEvent
extends Event {
    @NotNull
    public static final BoxContractEvent INSTANCE = new BoxContractEvent();
    public static Box value;

    private BoxContractEvent() {
    }

    @NotNull
    public final Box getValue() {
        Box box = value;
        if (box != null) {
            return box;
        }
        Intrinsics.throwUninitializedPropertyAccessException("value");
        return null;
    }

    public final void setValue(@NotNull Box box) {
        Intrinsics.checkNotNullParameter(box, "<set-?>");
        value = box;
    }
}

