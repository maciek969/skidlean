/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package su.catlean.mixins.world;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import su.catlean.api.event.events.player.BoxContractEvent;
import su.catlean.api.event.events.player.EntityPushEvent;
import su.catlean.api.event.events.player.FixVelocityEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.interfaces.IEntity;

@Mixin(value={Entity.class})
public abstract class EntityMixin
implements IEntity {
    @Shadow
    private Vec3d pos;

    @Shadow
    public abstract Box getBoundingBox();

    @Override
    public Vec3d catLean$getPosition() {
        return this.pos;
    }

    @Inject(method={"method_18376"}, at={@At(value="HEAD")}, cancellable=true)
    public void setPoseHook(CallbackInfoReturnable<EntityPose> cir) {
        if (this == MinecraftClient.getInstance().player && SetPoseEvent.INSTANCE.call()) {
            cir.cancel();
            cir.setReturnValue((Object)SetPoseEvent.INSTANCE.getPose());
        }
    }

    @Inject(method={"method_5724"}, at={@At(value="HEAD")}, cancellable=true)
    public void updateVelocityHook(float speed, Vec3d movementInput, CallbackInfo ci) {
        if (this == MinecraftClient.getInstance().player && FixVelocityEvent.INSTANCE.call(movementInput, speed, MinecraftClient.getInstance().player.getYaw())) {
            ci.cancel();
            MinecraftClient.getInstance().player.setVelocity(MinecraftClient.getInstance().player.getVelocity().add(FixVelocityEvent.INSTANCE.getVelocity()));
        }
    }

    @ModifyArgs(method={"method_5697"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_5762(DDD)V"))
    public void pushAwayFromHook(Args args) {
        if ((Entity)((Object)this) == MinecraftClient.getInstance().player && EntityPushEvent.INSTANCE.call()) {
            args.set(0, (Object)0.0);
            args.set(1, (Object)0.0);
            args.set(2, (Object)0.0);
        }
    }

    @Inject(method={"method_5829"}, at={@At(value="HEAD")}, cancellable=true)
    public void getBoundingBoxHook(CallbackInfoReturnable<Box> cir) {
        if ((Entity)((Object)this) == MinecraftClient.getInstance().player && BoxContractEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)BoxContractEvent.INSTANCE.getValue());
            cir.cancel();
        }
    }
}

