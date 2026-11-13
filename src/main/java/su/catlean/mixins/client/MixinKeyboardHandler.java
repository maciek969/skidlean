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

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.client.InputEvent;

@Mixin(value={Keyboard.class})
public class MixinKeyboardHandler {
    @Inject(method={"method_1466"}, at={@At(value="HEAD")}, cancellable=true)
    private void onKey(long window, int action, KeyInput input, CallbackInfo ci) {
        if (window == MinecraftClient.getInstance().getWindow().getHandle() && MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null && InputEvent.INSTANCE.call(input.comp_4795(), InputEvent.Device.Keyboard, action == 0 ? InputEvent.Action.Release : (action == 1 ? InputEvent.Action.Press : InputEvent.Action.Hold))) {
            ci.cancel();
        }
    }
}

