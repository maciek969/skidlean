/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.ui.hud.HudPositionSerializer;

public final class HudPosition$Companion {
    private HudPosition$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return HudPositionSerializer.INSTANCE;
    }

    public HudPosition$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

