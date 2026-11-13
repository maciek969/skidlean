/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.ProxySerializer;

public final class Proxy$Companion {
    private Proxy$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return ProxySerializer.INSTANCE;
    }

    public Proxy$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

