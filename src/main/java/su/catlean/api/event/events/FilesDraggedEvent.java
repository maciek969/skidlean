/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events;

import java.nio.file.Path;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class FilesDraggedEvent
extends Event {
    @NotNull
    public static final FilesDraggedEvent INSTANCE = new FilesDraggedEvent();
    public static List<? extends Path> paths;

    private FilesDraggedEvent() {
    }

    @NotNull
    public final List<Path> getPaths() {
        List<Path> list = paths;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("paths");
        return null;
    }

    public final void setPaths(@NotNull List<? extends Path> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        paths = list;
    }

    public final boolean call(@NotNull List<? extends Path> paths) {
        Intrinsics.checkNotNullParameter(paths, "paths");
        this.setPaths(paths);
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

