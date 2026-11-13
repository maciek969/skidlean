/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.api.event.events.player.EventPostPlayerTravel;
import su.catlean.api.event.events.player.PostAttackEvent;
import su.catlean.api.event.events.player.ReachStateEvent;
import su.catlean.api.event.events.player.ShouldDismountEvent;

@Mixin(value={PlayerEntity.class})
public class PlayerMixin {
    @Inject(method={"method_55754"}, at={@At(value="HEAD")}, cancellable=true)
    public void getBlockInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
        if (ReachStateEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)ReachStateEvent.INSTANCE.getBlockRange());
        }
    }

    @Inject(method={"method_55755"}, at={@At(value="HEAD")}, cancellable=true)
    public void getEntityInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
        if (ReachStateEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)ReachStateEvent.INSTANCE.getEntityRange());
        }
    }

    @Inject(method={"method_7324"}, at={@At(value="RETURN")})
    public void attackAHook(CallbackInfo callbackInfo) {
        PostAttackEvent.INSTANCE.call();
    }

    @Inject(method={"method_6091"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTravelhookPre(Vec3d movementInput, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null && EventPlayerTravel.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_6091"}, at={@At(value="RETURN")})
    private void onTravelhookPost(Vec3d movementInput, CallbackInfo ci) {
        EventPostPlayerTravel.INSTANCE.call();
    }

    @Inject(method={"method_21824"}, at={@At(value="HEAD")}, cancellable=true)
    protected void shouldDismountHook(CallbackInfoReturnable<Boolean> cir) {
        if (ShouldDismountEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)false);
        }
    }
}

