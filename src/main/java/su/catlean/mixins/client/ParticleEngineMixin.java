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

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.RenderParticleEvent;

@Mixin(value={ParticleManager.class})
public class ParticleEngineMixin {
    @Inject(at={@At(value="HEAD")}, method={"method_3058(Lnet/minecraft/class_703;)V"}, cancellable=true)
    public void addParticleHook(Particle particle, CallbackInfo ci) {
        if (RenderParticleEvent.INSTANCE.call(particle)) {
            ci.cancel();
        }
    }
}

