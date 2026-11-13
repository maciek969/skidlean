/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.macro.MacrosSerializer;

public final class Macros$Companion {
    private Macros$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return MacrosSerializer.INSTANCE;
    }

    public Macros$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

