/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.WaypointListSerializer;

public final class WaypointList$Companion {
    private WaypointList$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return WaypointListSerializer.INSTANCE;
    }

    public WaypointList$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

