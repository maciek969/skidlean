/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ProfileConfigSerializer;

public final class ProfileConfig$Companion {
    private ProfileConfig$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ProfileConfigSerializer.INSTANCE;
    }

    public ProfileConfig$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

