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
package su.catlean.mixins.render;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import su.catlean.api.event.events.player.FreecamStateEvent;
import su.catlean.api.event.events.render.CameraOffsetEvent;
import su.catlean.api.event.events.render.SetupTerrainEvent;

@Mixin(value={Camera.class})
public abstract class CameraMixin {
    @Shadow
    private boolean thirdPerson;

    @Shadow
    protected abstract float clipToSpace(float var1);

    @ModifyArgs(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_4184;method_19324(FFF)V", ordinal=0))
    private void modifyCameraDistance(Args args) {
        if (CameraOffsetEvent.INSTANCE.call()) {
            args.set(0, (Object)Float.valueOf(-this.clipToSpace(CameraOffsetEvent.INSTANCE.getOffset())));
        }
    }

    @Inject(method={"method_19318"}, at={@At(value="HEAD")}, cancellable=true)
    private void onClipToSpace(float f, CallbackInfoReturnable<Float> cir) {
        if (CameraOffsetEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)Float.valueOf(CameraOffsetEvent.INSTANCE.getOffset()));
        }
    }

    @Inject(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at={@At(value="RETURN")})
    private void updateHook(World area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (SetupTerrainEvent.INSTANCE.call()) {
            this.thirdPerson = true;
        }
    }

    @ModifyArgs(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_4184;method_19325(FF)V"))
    private void setRotationHook(Args args) {
        if (FreecamStateEvent.INSTANCE.call()) {
            args.setAll(new Object[]{Float.valueOf(FreecamStateEvent.INSTANCE.getYaw()), Float.valueOf(FreecamStateEvent.INSTANCE.getPitch())});
        }
    }

    @ModifyArgs(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_4184;method_19327(DDD)V"))
    private void setPosHook(Args args) {
        if (FreecamStateEvent.INSTANCE.call()) {
            args.setAll(new Object[]{FreecamStateEvent.INSTANCE.getX(), FreecamStateEvent.INSTANCE.getY(), FreecamStateEvent.INSTANCE.getZ()});
        }
    }
}

