/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.ThemePresetSerializer;

public final class ThemePreset$Companion {
    private ThemePreset$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ThemePresetSerializer.INSTANCE;
    }

    public ThemePreset$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

