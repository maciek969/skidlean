/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package su.catlean.mixins.world;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockCollisionSpliterator;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.player.CollisionEvent;

@Mixin(value={BlockCollisionSpliterator.class}, priority=800)
public abstract class BlockCollisionsMixin {
    @Redirect(method={"computeNext"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1922;method_8320(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;"))
    private BlockState computeNextHook(BlockView instance, BlockPos blockPos) {
        if (MinecraftClient.getInstance().world == null || MinecraftClient.getInstance().player == null || !GofraState.INSTANCE.getModifyCollisions()) {
            return instance.getBlockState(blockPos);
        }
        CollisionEvent event = new CollisionEvent();
        event.call(instance.getBlockState(blockPos), blockPos);
        return event.getState();
    }
}

