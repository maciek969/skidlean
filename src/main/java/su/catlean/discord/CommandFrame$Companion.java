/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.CommandFrame$$serializer;

public final class CommandFrame$Companion {
    private CommandFrame$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return CommandFrame$$serializer.INSTANCE;
    }

    public CommandFrame$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

