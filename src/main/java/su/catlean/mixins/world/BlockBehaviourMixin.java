/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.world;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.CalcBlockBreakingDeltaEvent;

@Mixin(value={AbstractBlock.class})
public class BlockBehaviourMixin {
    @Inject(method={"method_9594"}, at={@At(value="HEAD")}, cancellable=true)
    public void calcBlockBreakingDeltaHook(BlockState state, PlayerEntity player, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> ci) {
        if (CalcBlockBreakingDeltaEvent.INSTANCE.call(pos, state)) {
            ci.setReturnValue((Object)Float.valueOf(CalcBlockBreakingDeltaEvent.INSTANCE.getDelta()));
        }
    }
}

