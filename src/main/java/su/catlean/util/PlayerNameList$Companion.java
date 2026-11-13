/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.PlayerNameList$$serializer;

public final class PlayerNameList$Companion {
    private PlayerNameList$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return PlayerNameList$$serializer.INSTANCE;
    }

    public PlayerNameList$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

