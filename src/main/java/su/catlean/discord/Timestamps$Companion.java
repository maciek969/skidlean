/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.TimestampsSerializer;

public final class Timestamps$Companion {
    private Timestamps$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return TimestampsSerializer.INSTANCE;
    }

    public Timestamps$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

