/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.SettingEntry$$serializer;

public final class SettingEntry$Companion {
    private SettingEntry$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return SettingEntry$$serializer.INSTANCE;
    }

    public SettingEntry$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

