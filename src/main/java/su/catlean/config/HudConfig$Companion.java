/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.HudConfigSerializer;

public final class HudConfig$Companion {
    private HudConfig$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return HudConfigSerializer.INSTANCE;
    }

    public HudConfig$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

