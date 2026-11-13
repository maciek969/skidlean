/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.resource.ResourceReload;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class SplashOverlayEvent
extends Event {
    @NotNull
    public static final SplashOverlayEvent INSTANCE = new SplashOverlayEvent();
    private static boolean reloading;
    private static float progress;
    private static long reloadCompleteTime;
    private static long reloadStartTime;
    @Nullable
    private static ResourceReload reload;
    @Nullable
    private static Consumer<Optional<Throwable>> exceptionHandler;
    @Nullable
    private static DrawContext context;

    private SplashOverlayEvent() {
    }

    public final boolean getReloading() {
        return reloading;
    }

    public final void setReloading(boolean bl) {
        reloading = bl;
    }

    public final float getProgress() {
        return progress;
    }

    public final void setProgress(float f) {
        progress = f;
    }

    public final long getReloadCompleteTime() {
        return reloadCompleteTime;
    }

    public final void setReloadCompleteTime(long l) {
        reloadCompleteTime = l;
    }

    public final long getReloadStartTime() {
        return reloadStartTime;
    }

    public final void setReloadStartTime(long l) {
        reloadStartTime = l;
    }

    @Nullable
    public final ResourceReload getReload() {
        return reload;
    }

    public final void setReload(@Nullable ResourceReload resourceReload) {
        reload = resourceReload;
    }

    @Nullable
    public final Consumer<Optional<Throwable>> getExceptionHandler() {
        return exceptionHandler;
    }

    public final void setExceptionHandler(@Nullable Consumer<Optional<Throwable>> consumer) {
        exceptionHandler = consumer;
    }

    @Nullable
    public final DrawContext getContext() {
        return context;
    }

    public final void setContext(@Nullable DrawContext drawContext) {
        context = drawContext;
    }

    public final boolean call(@NotNull DrawContext context, boolean reloading, float progress, long reloadCompleteTime, long reloadStartTime, @Nullable ResourceReload reload, @Nullable Consumer<Optional<Throwable>> exceptionHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        SplashOverlayEvent.context = context;
        SplashOverlayEvent.reloading = reloading;
        SplashOverlayEvent.progress = progress;
        SplashOverlayEvent.reloadCompleteTime = reloadCompleteTime;
        SplashOverlayEvent.reloadStartTime = reloadStartTime;
        SplashOverlayEvent.reload = reload;
        SplashOverlayEvent.exceptionHandler = exceptionHandler;
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        reloadCompleteTime = -1L;
        reloadStartTime = -1L;
    }
}

