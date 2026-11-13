/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.RichPresenceSerializer;

public final class RichPresence$Companion {
    private RichPresence$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return RichPresenceSerializer.INSTANCE;
    }

    public RichPresence$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

