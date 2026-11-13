/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.client;

import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.client.TickFactorEvent;

@Mixin(value={RenderTickCounter.Dynamic.class})
public class MixinDynamic {
    @Shadow
    private float dynamicDeltaTicks;
    @Shadow
    private float tickProgress;
    @Shadow
    private long lastTimeMillis;
    @Final
    @Shadow
    private float tickTime;

    @Inject(method={"method_60639(J)I"}, at={@At(value="HEAD")}, cancellable=true)
    private void beginRenderTickHook(long timeMillis, CallbackInfoReturnable<Integer> cir) {
        if (TickFactorEvent.INSTANCE.call()) {
            this.dynamicDeltaTicks = (float)(timeMillis - this.lastTimeMillis) / this.tickTime * TickFactorEvent.INSTANCE.getFactor();
            this.lastTimeMillis = timeMillis;
            this.tickProgress += this.dynamicDeltaTicks;
            int roundedTickDelta = (int)this.tickProgress;
            this.tickProgress -= (float)roundedTickDelta;
            cir.setReturnValue((Object)roundedTickDelta);
        }
    }
}

