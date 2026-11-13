/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.RenderFireOverlayEvent;
import su.catlean.api.event.events.render.RenderFloatingItemEvent;
import su.catlean.api.event.events.render.RenderInWallOverlayEvent;
import su.catlean.api.event.events.render.RenderUnderwaterOverlayEvent;

@Mixin(value={InGameOverlayRenderer.class})
public class GameOverlayRendererMixin {
    @Inject(method={"method_23070"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderFireOverlayHook(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Sprite sprite, CallbackInfo ci) {
        if (RenderFireOverlayEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_70939"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderFloatingItemHook(MatrixStack matrices, float tickProgress, OrderedRenderCommandQueue queue, CallbackInfo ci) {
        if (RenderFloatingItemEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_23069"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderUnderwaterOverlayHook(MinecraftClient client, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        if (RenderUnderwaterOverlayEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_23068"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderInWallOverlayHook(Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        if (RenderInWallOverlayEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

