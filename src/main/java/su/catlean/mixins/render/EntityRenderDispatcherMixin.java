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
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.render.RenderEntityEvent;
import su.catlean.api.event.events.render.RenderFireOnEntityEvent;

@Mixin(value={EntityRenderManager.class})
public class EntityRenderDispatcherMixin {
    @Inject(method={"method_72976"}, at={@At(value="HEAD")})
    private <S extends EntityRenderState> void onRenderHead(S renderState, CameraRenderState cameraRenderState, double d, double e, double f, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CallbackInfo ci) {
        if (RenderFireOnEntityEvent.INSTANCE.call()) {
            renderState.onFire = false;
        }
    }

    @Inject(method={"method_3950"}, at={@At(value="HEAD")}, cancellable=true)
    public <E extends Entity, S extends EntityRenderState> void onRenderEntity(E entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (RenderEntityEvent.INSTANCE.call(entity)) {
            cir.setReturnValue((Object)false);
        }
    }
}

