/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package su.catlean.mixins.world;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import su.catlean.api.event.events.world.FireWorkVectorEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;

@Mixin(value={FireworkRocketEntity.class})
public class FireWorkEntityMixin {
    @Shadow
    private LivingEntity shooter;

    @Redirect(method={"method_5773"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1309;method_5720()Lnet/minecraft/class_243;"))
    private Vec3d tickHook(LivingEntity instance) {
        if (this.shooter == MinecraftClient.getInstance().player && FireWorkVectorEvent.INSTANCE.call(this.shooter.getRotationVector())) {
            return FireWorkVectorEvent.INSTANCE.getVector();
        }
        return this.shooter.getRotationVector();
    }

    @ModifyArgs(method={"method_5773"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1309;method_18799(Lnet/minecraft/class_243;)V"))
    public void tickHook2(Args args) {
        if (this.shooter == MinecraftClient.getInstance().player && FireWorkVelocityEvent.INSTANCE.call()) {
            args.set(0, (Object)FireWorkVelocityEvent.INSTANCE.getVelocity());
        }
    }
}

