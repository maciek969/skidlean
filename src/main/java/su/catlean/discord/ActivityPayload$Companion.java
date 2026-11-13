/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.ActivityPayloadSerializer;

public final class ActivityPayload$Companion {
    private ActivityPayload$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ActivityPayloadSerializer.INSTANCE;
    }

    public ActivityPayload$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

