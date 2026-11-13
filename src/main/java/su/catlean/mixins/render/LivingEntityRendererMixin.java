/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package su.catlean.mixins.render;

import java.awt.Color;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import su.catlean.api.event.events.render.EntityAlphaEvent;
import su.catlean.api.event.events.render.LookRenderEvent;

@Mixin(value={LivingEntityRenderer.class})
public class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Unique
    private S prevState;

    @Inject(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="HEAD")})
    private void renderHook(S state, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        this.prevState = state;
        if (state instanceof PlayerEntityRenderState) {
            PlayerEntityRenderState prs = (PlayerEntityRenderState)state;
            if (MinecraftClient.getInstance().player != null && Objects.equals(prs.id, MinecraftClient.getInstance().player.getId())) {
                LookRenderEvent.INSTANCE.call(((LivingEntityRenderState)state).relativeHeadYaw, ((LivingEntityRenderState)state).pitch, ((LivingEntityRenderState)state).bodyYaw);
                ((LivingEntityRenderState)state).relativeHeadYaw = LookRenderEvent.INSTANCE.getYaw();
                ((LivingEntityRenderState)state).pitch = LookRenderEvent.INSTANCE.getPitch();
                ((LivingEntityRenderState)state).bodyYaw = LookRenderEvent.INSTANCE.getBodyYaw();
            }
        }
    }

    @ModifyArgs(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_11659;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V"))
    private void renderHook(Args args) {
        S s;
        if (this.prevState != null && (s = this.prevState) instanceof PlayerEntityRenderState) {
            PlayerEntityRenderState prs = (PlayerEntityRenderState)s;
            if (MinecraftClient.getInstance().player != null && !Objects.equals(prs.id, MinecraftClient.getInstance().player.getId()) && EntityAlphaEvent.INSTANCE.call(new Vec3d(prs.x, prs.y, prs.z))) {
                args.set(6, (Object)this.withAlpha(new Color(0x26FFFFFF), EntityAlphaEvent.INSTANCE.getAlpha()).getRGB());
            }
        }
    }

    @Unique
    private Color withAlpha(Color c, float alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(255.0f * alpha));
    }
}

