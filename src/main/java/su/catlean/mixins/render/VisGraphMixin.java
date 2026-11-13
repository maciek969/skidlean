/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.render;

import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.GofraState;

@Mixin(value={ChunkOcclusionDataBuilder.class})
public class VisGraphMixin {
    @Inject(method={"method_3682"}, at={@At(value="HEAD")}, cancellable=true)
    private void markClosedHook(BlockPos pos, CallbackInfo ci) {
        if (GofraState.INSTANCE.getXray()) {
            ci.cancel();
        }
    }
}

