/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class StringDecomposerEvent
extends Event {
    @NotNull
    public static final StringDecomposerEvent INSTANCE = new StringDecomposerEvent();
    @NotNull
    private static String string = "";

    private StringDecomposerEvent() {
    }

    @NotNull
    public final String getString() {
        return string;
    }

    public final void setString(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        StringDecomposerEvent.string = string;
    }

    @NotNull
    public final String call(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        StringDecomposerEvent.string = string;
        Gofra.INSTANCE.drain(this);
        return StringDecomposerEvent.string;
    }
}

