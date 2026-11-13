/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.IpcEventType;

public final class IpcEventType$Companion {
    private IpcEventType$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return this.serializer2();
    }

    private final KSerializer serializer2() {
        return (KSerializer)IpcEventType.o().getValue();
    }

    public IpcEventType$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

