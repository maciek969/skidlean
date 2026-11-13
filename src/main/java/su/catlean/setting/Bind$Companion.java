/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.BindSerializer;

public final class Bind$Companion {
    private Bind$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return BindSerializer.INSTANCE;
    }

    public Bind$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

