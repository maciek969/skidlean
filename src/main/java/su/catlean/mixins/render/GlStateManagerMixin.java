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

import com.mojang.blaze3d.opengl.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.GofraState;

@Mixin(value={GlStateManager.class})
public class GlStateManagerMixin {
    @Inject(method={"_glBindFramebuffer"}, at={@At(value="HEAD")}, cancellable=true)
    private static void _glBindFramebuffer(int target, int framebuffer, CallbackInfo ci) {
        if (GofraState.INSTANCE.getStopSwapBuffers()) {
            ci.cancel();
        }
    }
}

