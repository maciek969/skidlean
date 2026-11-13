/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.render;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.OutputTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.FrameBufferEvent;

@Mixin(value={OutputTarget.class})
public class MixinRenderPhase {
    @Inject(method={"method_75921()Lnet/minecraft/class_276;"}, at={@At(value="HEAD")}, cancellable=true)
    public void getHook(CallbackInfoReturnable<Framebuffer> cir) {
        if (GofraState.INSTANCE.getModifyBuffer() && FrameBufferEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)FrameBufferEvent.INSTANCE.getFrameBuffer());
            cir.cancel();
        }
    }
}

