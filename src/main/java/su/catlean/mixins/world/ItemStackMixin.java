/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package su.catlean.mixins.world;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.FinishUsingItemEvent;
import su.catlean.api.event.events.player.StackCollectEvent;

@Mixin(value={ItemStack.class})
public class ItemStackMixin {
    @Inject(method={"method_7910"}, at={@At(value="HEAD")})
    private void onFinishUsing(World world, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        if (entity == MinecraftClient.getInstance().player) {
            FinishUsingItemEvent.INSTANCE.call((ItemStack)((Object)this));
        }
    }

    @Inject(method={"method_7912"}, at={@At(value="HEAD")})
    private void onRenewItem(int bobbingAnimationTime, CallbackInfo ci) {
        if (bobbingAnimationTime == 5) {
            StackCollectEvent.INSTANCE.setItemStack((ItemStack)((Object)this));
            StackCollectEvent.INSTANCE.call();
        }
    }
}

