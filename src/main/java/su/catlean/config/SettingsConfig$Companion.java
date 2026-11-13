/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.SettingsConfigSerializer;

public final class SettingsConfig$Companion {
    private SettingsConfig$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return SettingsConfigSerializer.INSTANCE;
    }

    public SettingsConfig$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

