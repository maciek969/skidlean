/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.render;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.RenderNameTagEvent;
import su.catlean.api.event.events.render.ShouldRenderEntityEvent;

@Mixin(value={EntityRenderer.class})
public abstract class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    @Inject(method={"method_3926"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderLabelIfPresent(S state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (RenderNameTagEvent.INSTANCE.call((EntityRenderState)state)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_3933"}, at={@At(value="HEAD")}, cancellable=true)
    public void shouldRender(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (GofraState.INSTANCE.getShouldRender() && ShouldRenderEntityEvent.INSTANCE.call((Entity)entity)) {
            cir.setReturnValue((Object)false);
        }
    }
}

