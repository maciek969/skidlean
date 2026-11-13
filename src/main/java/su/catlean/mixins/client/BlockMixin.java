/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.XRayBlockEvent;
import su.catlean.api.event.events.world.SlipperinessEvent;
import su.catlean.api.event.events.world.VelocityMultiplierEvent;

@Mixin(value={Block.class})
public class BlockMixin {
    @Inject(method={"method_23349"}, at={@At(value="HEAD")}, cancellable=true)
    public void getVelocityMultiplierHook(CallbackInfoReturnable<Float> cir) {
        if (VelocityMultiplierEvent.INSTANCE.call((Block)((Object)this))) {
            cir.setReturnValue((Object)Float.valueOf(Blocks.DIRT.getVelocityMultiplier()));
        }
    }

    @Inject(method={"method_9499"}, at={@At(value="HEAD")}, cancellable=true)
    public void getSlipperinessHook(CallbackInfoReturnable<Float> cir) {
        if (SlipperinessEvent.INSTANCE.call((Block)((Object)this))) {
            cir.setReturnValue((Object)Float.valueOf(Blocks.DIRT.getSlipperiness()));
        }
    }

    @ModifyReturnValue(method={"method_9607"}, at={@At(value="RETURN")})
    private static boolean shouldDrawSideHook(boolean original, BlockState state, BlockState otherState, Direction side) {
        if (GofraState.INSTANCE.getXray()) {
            XRayBlockEvent event = new XRayBlockEvent();
            event.call(state.getBlock());
            return event.getCancelled();
        }
        return original;
    }
}

