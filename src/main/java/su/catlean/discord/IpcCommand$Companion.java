/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.IpcCommand;

public final class IpcCommand$Companion {
    private IpcCommand$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return this.serializer2();
    }

    private final KSerializer serializer2() {
        return (KSerializer)IpcCommand.O().getValue();
    }

    public IpcCommand$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

