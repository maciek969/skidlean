/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ProxyListSerializer;

public final class ProxyList$Companion {
    private ProxyList$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ProxyListSerializer.INSTANCE;
    }

    public ProxyList$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

