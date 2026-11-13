/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.network.SlowDownEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PostPlayerUpdateEvent;
import su.catlean.api.event.events.player.PostSyncEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.PushOutOfBlocksEvent;
import su.catlean.api.event.events.player.ShouldStopSprintingEvent;
import su.catlean.api.event.events.player.UsingItemEvent;

@Mixin(value={ClientPlayerEntity.class}, priority=800)
public abstract class LocalPlayerMixin
extends AbstractClientPlayerEntity {
    public LocalPlayerMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method={"method_5773"}, at={@At(value="HEAD")}, cancellable=true)
    public void tickHook(CallbackInfo info) {
        if (PlayerUpdateEvent.INSTANCE.call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_5773"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_742;method_5773()V", shift=At.Shift.AFTER)})
    public void postTickHook(CallbackInfo info) {
        PostPlayerUpdateEvent.INSTANCE.call();
    }

    @Inject(method={"method_3136"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendMovementPacketsHook(CallbackInfo info) {
        if (PreSyncEvent.INSTANCE.call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_3136"}, at={@At(value="RETURN")})
    private void sendMovementPacketsPostHook(CallbackInfo info) {
        PostSyncEvent.INSTANCE.call();
    }

    @Inject(method={"method_5784"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_742;method_5784(Lnet/minecraft/class_1313;Lnet/minecraft/class_243;)V")}, cancellable=true)
    public void onMoveHook(MovementType movementType, @NotNull Vec3d movement, CallbackInfo ci) {
        if (MoveEvent.INSTANCE.call(movement.x, movement.y, movement.z)) {
            double cacheX = this.getX();
            double cacheZ = this.getZ();
            super.move(movementType, new Vec3d(MoveEvent.INSTANCE.getX(), MoveEvent.INSTANCE.getY(), MoveEvent.INSTANCE.getZ()));
            this.distanceTraveled += MathHelper.hypot((float)(this.getX() - cacheX), (float)(this.getZ() - cacheZ)) * 0.6f;
            ci.cancel();
        }
    }

    @Inject(method={"method_20303"}, at={@At(value="HEAD")}, cancellable=true)
    public void shouldSlowDownHook(CallbackInfoReturnable<Boolean> cir) {
        if (SlowDownEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_6115"}, at={@At(value="HEAD")}, cancellable=true)
    public void isUsingItemHook(CallbackInfoReturnable<Boolean> cir) {
        if (UsingItemEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_30673"}, at={@At(value="HEAD")}, cancellable=true)
    private void onPushOutOfBlocksHook(double x, double d, CallbackInfo info) {
        if (PushOutOfBlocksEvent.INSTANCE.call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_65949"}, at={@At(value="HEAD")}, cancellable=true)
    public void shouldStopSprintingHook(CallbackInfoReturnable<Boolean> cir) {
        if (ShouldStopSprintingEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)false);
        }
    }
}

