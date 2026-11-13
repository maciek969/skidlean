/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.player.KeyboardInputEvent;

@Mixin(value={KeyboardInput.class})
public class KeyboardInputMixin {
    @Inject(method={"method_3129"}, at={@At(value="FIELD", target="Lnet/minecraft/class_743;field_55868:Lnet/minecraft/class_241;", ordinal=0, shift=At.Shift.AFTER)}, cancellable=true)
    private void tickHook(CallbackInfo ci) {
        if (KeyboardInputEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

