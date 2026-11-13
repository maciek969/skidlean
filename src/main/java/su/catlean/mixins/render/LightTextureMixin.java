/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  org.spongepowered.asm.mixin.Final
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
import com.mojang.blaze3d.textures.GpuTexture;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.render.DarknessEffectEvent;
import su.catlean.api.event.events.render.LightMapUpdateEvent;

@Mixin(value={LightmapTextureManager.class})
public class LightTextureMixin {
    @Final
    @Shadow
    private GpuTexture glTexture;

    @Inject(method={"method_42596"}, at={@At(value="HEAD")}, cancellable=true)
    private void getDarknessHook(LivingEntity entity, float factor, float tickProgress, CallbackInfoReturnable<Float> cir) {
        if (DarknessEffectEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)Float.valueOf(0.0f));
        }
    }

    @Inject(method={"method_3313"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3695;method_15396(Ljava/lang/String;)V", shift=At.Shift.AFTER)}, cancellable=true)
    private void updateHook(float tickProgress, CallbackInfo ci, @Local Profiler profiler) {
        if (LightMapUpdateEvent.INSTANCE.call()) {
            RenderSystem.getDevice().createCommandEncoder().clearColorTexture(this.glTexture, -1);
            profiler.pop();
            ci.cancel();
        }
    }
}

