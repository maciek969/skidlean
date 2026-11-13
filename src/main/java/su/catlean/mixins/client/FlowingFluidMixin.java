/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.client;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.world.WaterPushEvent;

@Mixin(value={FlowableFluid.class})
public abstract class FlowingFluidMixin {
    @Inject(method={"method_15782"}, at={@At(value="HEAD")}, cancellable=true)
    private void getVelocityHook(BlockView world, BlockPos pos, FluidState state, CallbackInfoReturnable<Vec3d> cir) {
        if (WaterPushEvent.INSTANCE.call(pos, (Fluid)((Object)this), state, Vec3d.ZERO)) {
            cir.setReturnValue((Object)WaterPushEvent.INSTANCE.getVec());
        }
    }
}

