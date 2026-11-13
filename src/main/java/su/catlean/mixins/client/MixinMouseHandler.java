/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.interfaces.IMouse;

@Mixin(value={Mouse.class})
public class MixinMouseHandler
implements IMouse {
    @Shadow
    private double x;
    @Shadow
    private double y;

    @Inject(method={"method_1601"}, at={@At(value="HEAD")}, cancellable=true)
    public void onMouseButtonHook(long window, MouseInput input, int action, CallbackInfo ci) {
        if (window == MinecraftClient.getInstance().getWindow().getHandle() && InputEvent.INSTANCE.call(input.comp_4801(), InputEvent.Device.Mouse, action == 0 ? InputEvent.Action.Release : InputEvent.Action.Press)) {
            ci.cancel();
        }
    }

    @Override
    public void setPosition(int xp, int yp) {
        this.x = xp;
        this.y = yp;
    }
}

