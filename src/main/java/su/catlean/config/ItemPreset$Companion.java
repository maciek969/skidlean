/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ItemPreset$$serializer;

public final class ItemPreset$Companion {
    private ItemPreset$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ItemPreset$$serializer.INSTANCE;
    }

    public ItemPreset$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

