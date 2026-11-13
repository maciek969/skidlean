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

import net.minecraft.client.gl.ShaderLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.ShaderApplyEvent;

@Mixin(value={ShaderLoader.class})
public abstract class ShaderManagerMixin {
    @Inject(method={"method_62945(Lnet/minecraft/class_10151$class_10153;Lnet/minecraft/class_3300;Lnet/minecraft/class_3695;)V"}, at={@At(value="TAIL")})
    private void applyResourcesHook(CallbackInfo info) {
        ShaderApplyEvent.INSTANCE.call();
    }
}

