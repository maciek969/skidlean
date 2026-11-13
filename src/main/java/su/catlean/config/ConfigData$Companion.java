/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigDataSerializer;

public final class ConfigData$Companion {
    private ConfigData$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ConfigDataSerializer.INSTANCE;
    }

    public ConfigData$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

