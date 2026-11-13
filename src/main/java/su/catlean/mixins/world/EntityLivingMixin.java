/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.world;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.FallFlyingEvent;
import su.catlean.api.event.events.player.HealthEvent;
import su.catlean.api.event.events.player.JumpEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.SprintEvent;
import su.catlean.api.event.events.render.SwingDurationEvent;

@Mixin(value={LivingEntity.class})
public abstract class EntityLivingMixin {
    @Shadow
    protected abstract float getJumpVelocity();

    @Inject(method={"method_6091"}, at={@At(value="HEAD")}, cancellable=true)
    public void travelHook(Vec3d movementInput, CallbackInfo ci) {
        if ((LivingEntity)((Object)this) != MinecraftClient.getInstance().player) {
            return;
        }
        if (PreElytraEvent.INSTANCE.call(MinecraftClient.getInstance().player.getVelocity())) {
            MinecraftClient.getInstance().player.move(MovementType.SELF, PreElytraEvent.INSTANCE.getVec());
            ci.cancel();
        }
    }

    @Inject(method={"method_6091"}, at={@At(value="RETURN")}, cancellable=true)
    public void travelPostHook(Vec3d movementInput, CallbackInfo ci) {
        if ((LivingEntity)((Object)this) != MinecraftClient.getInstance().player) {
            return;
        }
        if (AfterElytraEvent.INSTANCE.call(MinecraftClient.getInstance().player.getVelocity())) {
            MinecraftClient.getInstance().player.move(MovementType.SELF, AfterElytraEvent.INSTANCE.getVec());
            ci.cancel();
        }
    }

    @Inject(method={"method_6128"}, at={@At(value="TAIL")}, cancellable=true)
    public void isGlidingHook(CallbackInfoReturnable<Boolean> cir) {
        if ((LivingEntity)((Object)this) != MinecraftClient.getInstance().player) {
            return;
        }
        if (FallFlyingEvent.INSTANCE.call((Boolean)cir.getReturnValue())) {
            cir.setReturnValue((Object)FallFlyingEvent.INSTANCE.getValue());
        }
    }

    @Inject(method={"method_6043"}, at={@At(value="HEAD")}, cancellable=true)
    private void jumpHook(CallbackInfo info) {
        if ((LivingEntity)((Object)this) != MinecraftClient.getInstance().player) {
            return;
        }
        LivingEntity pl = (LivingEntity)((Object)this);
        if (JumpEvent.INSTANCE.call(pl.getYaw(), this.getJumpVelocity(), 0.2f)) {
            info.cancel();
            if (JumpEvent.INSTANCE.getY() > 1.0E-5f) {
                Vec3d vec3d = pl.getVelocity();
                pl.setVelocity(vec3d.x, Math.max((double)JumpEvent.INSTANCE.getY(), vec3d.y), vec3d.z);
                if (pl.isSprinting()) {
                    float g = JumpEvent.INSTANCE.getYaw() * ((float)Math.PI / 180);
                    pl.addVelocityInternal(new Vec3d(-Math.sin(g) * (double)JumpEvent.INSTANCE.getXz(), 0.0, Math.cos(g) * (double)JumpEvent.INSTANCE.getXz()));
                }
                pl.velocityDirty = true;
            }
        }
    }

    @ModifyVariable(method={"method_5728"}, at=@At(value="HEAD"), ordinal=0, argsOnly=true)
    private boolean setSprintingHook(boolean sprinting) {
        SprintEvent.INSTANCE.setSprinting(sprinting);
        SprintEvent.INSTANCE.call();
        return SprintEvent.INSTANCE.getSprinting();
    }

    @Inject(method={"method_6033"}, at={@At(value="HEAD")})
    private void setHealth(float health, CallbackInfo ci) {
        if (this == MinecraftClient.getInstance().player) {
            HealthEvent.INSTANCE.call(health);
        }
    }

    @Inject(method={"method_6028"}, at={@At(value="HEAD")}, cancellable=true)
    private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> info) {
        if (SwingDurationEvent.INSTANCE.call()) {
            info.setReturnValue((Object)SwingDurationEvent.INSTANCE.getValue());
        }
    }
}

