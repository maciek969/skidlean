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

import net.minecraft.block.CobwebBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.player.CobWebEvent;

@Mixin(value={CobwebBlock.class})
public class WebBlockMixin {
    @Inject(method={"method_9548"}, at={@At(value="HEAD")}, cancellable=true)
    public void onEntityCollisionHook(CallbackInfo ci) {
        if (CobWebEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

