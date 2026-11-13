/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.render;

import java.nio.file.Path;
import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.FilesDraggedEvent;
import su.catlean.api.event.events.render.RenderGuiBackgroundEvent;
import su.catlean.api.event.events.render.ScreenMouseCoordsEvent;

@Mixin(value={Screen.class})
public class ScreenMixin {
    @Inject(method={"method_52752"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderInGameBackground(DrawContext context, CallbackInfo ci) {
        if (RenderGuiBackgroundEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_25394"}, at={@At(value="HEAD")})
    public void renderHook(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ScreenMouseCoordsEvent.INSTANCE.call(mouseX, mouseY);
    }

    @Inject(method={"method_29638"}, at={@At(value="HEAD")})
    public void filesDragged(List<Path> paths, CallbackInfo ci) {
        FilesDraggedEvent.INSTANCE.call(paths);
    }
}

