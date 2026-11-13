/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.render;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.UpdateCrosshairTarget;
import su.catlean.api.event.events.render.BasicProjectionMatrixEvent;
import su.catlean.api.event.events.render.HurtTiltEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.RenderHandEvent;
import su.catlean.api.event.events.render.RenderScreenEvent;
import su.catlean.api.event.events.render.ShowFloatingItemEvent;

@Mixin(value={GameRenderer.class})
public abstract class GameRendererMixin {
    @Shadow
    public abstract float getFarPlaneDistance();

    @Inject(method={"method_3188"}, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/CommandEncoder;clearDepthTexture(Lcom/mojang/blaze3d/textures/GpuTexture;D)V", shift=At.Shift.BEFORE)})
    void render3dHook(RenderTickCounter renderTickCounter, CallbackInfo ci, @Local(ordinal=0) Matrix4f projection) {
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        MatrixStack matrixStack = new MatrixStack();
        RenderSystem.getModelViewStack().pushMatrix().mul((Matrix4fc)matrixStack.peek().getPositionMatrix());
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0f));
        Render3DEvent.INSTANCE.call(matrixStack, projection);
        RenderSystem.getModelViewStack().popMatrix();
    }

    @Inject(method={"method_3190"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_746;method_76762(FLnet/minecraft/class_1297;)Lnet/minecraft/class_239;")}, cancellable=true)
    private void onUpdateTargetedEntity(float tickDelta, CallbackInfo info) {
        if (UpdateCrosshairTarget.INSTANCE.call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_3192"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_74036(Lnet/minecraft/class_1041;)V")})
    private void render2dHook(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local(ordinal=0) DrawContext dc) {
        if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
            RenderScreenEvent.INSTANCE.call(dc);
        }
    }

    @Inject(method={"method_3172"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderHand(float tickProgress, boolean sleeping, Matrix4f positionMatrix, CallbackInfo ci) {
        if (RenderHandEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_3189"}, at={@At(value="HEAD")}, cancellable=true)
    private void showFloatingItemHook(ItemStack floatingItem, CallbackInfo info) {
        if (ShowFloatingItemEvent.INSTANCE.call(floatingItem)) {
            info.cancel();
        }
    }

    @Inject(method={"method_3198"}, at={@At(value="HEAD")}, cancellable=true)
    private void tiltViewWhenHurtHook(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (HurtTiltEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_22973"}, at={@At(value="HEAD")}, cancellable=true)
    private void getBasicProjectionMatrixHook(float fovDegrees, CallbackInfoReturnable<Matrix4f> cir) {
        BasicProjectionMatrixEvent.INSTANCE.setFov(fovDegrees);
        if (BasicProjectionMatrixEvent.INSTANCE.call()) {
            Matrix4f matrix4f = new Matrix4f();
            float zoom = BasicProjectionMatrixEvent.INSTANCE.getZoom();
            float zoomX = BasicProjectionMatrixEvent.INSTANCE.getZoomX();
            float zoomY = BasicProjectionMatrixEvent.INSTANCE.getZoomY();
            float fov = BasicProjectionMatrixEvent.INSTANCE.getFov();
            if (zoom != 1.0f) {
                matrix4f.translate(zoomX, -zoomY, 0.0f);
                matrix4f.scale(zoom, zoom, 1.0f);
            }
            cir.setReturnValue((Object)matrix4f.perspective(fov * ((float)Math.PI / 180), BasicProjectionMatrixEvent.INSTANCE.getRatio(), 0.05f, this.getFarPlaneDistance()));
            cir.cancel();
        }
    }
}

