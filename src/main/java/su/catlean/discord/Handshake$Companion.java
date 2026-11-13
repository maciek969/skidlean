/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.Handshake$$serializer;

public final class Handshake$Companion {
    private Handshake$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return Handshake$$serializer.INSTANCE;
    }

    public Handshake$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

