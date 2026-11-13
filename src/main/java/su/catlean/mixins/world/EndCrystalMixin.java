/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.world;

import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.world.CrystalCreateEvent;

@Mixin(value={EndCrystalEntity.class})
public class EndCrystalMixin {
    @Shadow
    public int endCrystalAge;
    @Unique
    float startAge = -1.0f;

    @Inject(method={"<init>(Lnet/minecraft/class_1299;Lnet/minecraft/class_1937;)V"}, at={@At(value="TAIL")})
    private void tickHook(CallbackInfo ci) {
        if (CrystalCreateEvent.INSTANCE.call() && this.startAge == -1.0f) {
            this.startAge = this.endCrystalAge;
            this.endCrystalAge = 0;
        }
    }
}

