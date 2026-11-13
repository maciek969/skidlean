/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.IpcFrame$$serializer;

public final class IpcFrame$Companion {
    private IpcFrame$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return IpcFrame$$serializer.INSTANCE;
    }

    public IpcFrame$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

