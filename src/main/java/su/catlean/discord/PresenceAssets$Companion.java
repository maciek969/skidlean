/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.PresenceAssetsSerializer;

public final class PresenceAssets$Companion {
    private PresenceAssets$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return PresenceAssetsSerializer.INSTANCE;
    }

    public PresenceAssets$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

