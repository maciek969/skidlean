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

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.state.EndCrystalEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.render.CrystalRenderEvent;
import su.catlean.api.event.events.render.CrystalYOffsetEvent;
import su.catlean.api.event.events.render.SetCrystalScaleEvent;

@Mixin(value={EndCrystalEntityRenderer.class})
public class EndCrystalRendererMixin {
    @Inject(method={"method_3908(Lnet/minecraft/class_10014;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderHook(EndCrystalEntityRenderState endCrystalEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (CrystalRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_3908(Lnet/minecraft/class_10014;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4587;method_22905(FFF)V")})
    private void scaleHook(EndCrystalEntityRenderState endCrystalEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (SetCrystalScaleEvent.INSTANCE.call(endCrystalEntityRenderState.age)) {
            float scale = SetCrystalScaleEvent.INSTANCE.getScale();
            matrixStack.scale(scale, scale, scale);
        }
    }

    @Inject(method={"method_23155"}, at={@At(value="HEAD")}, cancellable=true)
    private static void getYOffsetHook(float age, CallbackInfoReturnable<Float> cir) {
        if (CrystalYOffsetEvent.INSTANCE.call(age)) {
            cir.setReturnValue((Object)Float.valueOf(CrystalYOffsetEvent.INSTANCE.getOffset()));
        }
    }
}

