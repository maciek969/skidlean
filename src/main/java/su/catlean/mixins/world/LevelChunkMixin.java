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
package su.catlean.mixins.world;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.world.BlockStateEvent;

@Mixin(value={WorldChunk.class})
public class LevelChunkMixin {
    @Shadow
    @Final
    World world;

    @Inject(method={"method_12010"}, at={@At(value="RETURN")})
    private void setBlockStateHook(BlockPos pos, BlockState state, int flags, CallbackInfoReturnable<BlockState> cir) {
        if (this.world.isClient()) {
            BlockStateEvent.INSTANCE.call(pos, state, (BlockState)cir.getReturnValue());
        }
    }
}

