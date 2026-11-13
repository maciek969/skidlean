/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.client.CloseEvent;
import su.catlean.api.event.events.client.InitEvent;
import su.catlean.api.event.events.client.PostTickEvent;
import su.catlean.api.event.events.client.ScreenEvent;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.DisconnectEvent;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.player.AttackEvent;
import su.catlean.api.event.events.player.ItemUseEvent;

@Mixin(value={MinecraftClient.class})
public class MinecraftMixin {
    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    void postWindowInit(RunArgs args, CallbackInfo ci) {
        InitEvent.INSTANCE.call();
    }

    @Inject(method={"method_1507"}, at={@At(value="HEAD")}, cancellable=true)
    public void setScreenHook(Screen screen, CallbackInfo ci) {
        if (ScreenEvent.INSTANCE.call(screen)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1574"}, at={@At(value="HEAD")})
    void preTickHook(CallbackInfo ci) {
        TickEvent.INSTANCE.call();
    }

    @Inject(method={"method_1574"}, at={@At(value="RETURN")})
    void postTickHook(CallbackInfo ci) {
        PostTickEvent.INSTANCE.call();
    }

    @Inject(method={"method_1536"}, at={@At(value="HEAD")}, cancellable=true)
    private void doAttackHook(CallbackInfoReturnable<Boolean> cir) {
        if (AttackEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"close"}, at={@At(value="HEAD")}, cancellable=true)
    private void closeHook(CallbackInfo ci) {
        if (CloseEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1588"}, at={@At(value="HEAD")}, cancellable=true)
    private static void isAmbientOcclusionEnabledHook(CallbackInfoReturnable<Boolean> cir) {
        if (GofraState.INSTANCE.getXray()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_18096(Lnet/minecraft/class_437;ZZ)V"}, at={@At(value="HEAD")})
    public void disconnect(Screen screen, boolean bl, boolean bl2, CallbackInfo ci) {
        DisconnectEvent.INSTANCE.call();
    }

    @Inject(method={"method_1523"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_310;method_5383()V", shift=At.Shift.AFTER)})
    private void render(boolean tick, CallbackInfo ci, @Local int n) {
        PostTasksProcessEvent.INSTANCE.call();
    }

    @Inject(method={"method_1583"}, at={@At(value="HEAD")})
    private void doItemUse(CallbackInfo callbackInfo) {
        ItemUseEvent.INSTANCE.call();
    }
}

