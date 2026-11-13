/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Waypoint$$serializer;

public final class Waypoint$Companion {
    private Waypoint$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return Waypoint$$serializer.INSTANCE;
    }

    public Waypoint$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

