/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.macro.ChatMacro$$serializer;

public final class ChatMacro$Companion {
    private ChatMacro$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ChatMacro$$serializer.INSTANCE;
    }

    public ChatMacro$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

