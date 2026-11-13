/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.client;

import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class ScreenEvent
extends Event {
    @NotNull
    public static final ScreenEvent INSTANCE = new ScreenEvent();
    @Nullable
    private static Screen screen;

    private ScreenEvent() {
    }

    @Nullable
    public final Screen getScreen() {
        return screen;
    }

    public final void setScreen(@Nullable Screen screen) {
        ScreenEvent.screen = screen;
    }

    public final boolean call(@Nullable Screen screen) {
        this.setCancelled(false);
        ScreenEvent.screen = screen;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

