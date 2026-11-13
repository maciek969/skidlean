/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ModuleConfigSerializer;

public final class ModuleConfig$Companion {
    private ModuleConfig$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ModuleConfigSerializer.INSTANCE;
    }

    public ModuleConfig$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

