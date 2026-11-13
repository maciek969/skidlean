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

import net.minecraft.client.render.block.entity.MobSpawnerBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.RenderSpawnerEntityEvent;

@Mixin(value={MobSpawnerBlockEntityRenderer.class})
public class SpawnerRendererMixin {
    @Inject(method={"method_3589(Lnet/minecraft/class_11973;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderHook(CallbackInfo ci) {
        if (RenderSpawnerEntityEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

