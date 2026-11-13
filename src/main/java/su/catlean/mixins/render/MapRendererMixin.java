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

import net.minecraft.client.render.MapRenderState;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.MapRenderEvent;

@Mixin(value={MapRenderer.class})
public class MapRendererMixin {
    @Inject(method={"method_1773"}, at={@At(value="HEAD")}, cancellable=true)
    public void drawHook(MapRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, boolean bl, int light, CallbackInfo ci) {
        if (bl && MapRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

