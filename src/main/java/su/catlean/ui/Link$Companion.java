/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.ui.Link$$serializer;

public final class Link$Companion {
    private Link$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return Link$$serializer.INSTANCE;
    }

    public Link$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

