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

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.TridentUseEvent;
import su.catlean.api.event.events.player.TridentWeatherEvent;

@Mixin(value={TridentItem.class})
public abstract class TridentItemMixin {
    @Inject(method={"method_7840"}, at={@At(value="HEAD")}, cancellable=true)
    public void onStoppedUsingHook(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfoReturnable<Boolean> ci) {
        if (user == MinecraftClient.getInstance().player && EnchantmentHelper.getTridentSpinAttackStrength(stack, MinecraftClient.getInstance().player) > 0.0f && TridentUseEvent.INSTANCE.call()) {
            ci.cancel();
            ci.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_7836"}, at={@At(value="HEAD")}, cancellable=true)
    public void useHook(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (EnchantmentHelper.getTridentSpinAttackStrength(itemStack, user) > 0.0f && !user.isTouchingWaterOrRain() && TridentWeatherEvent.INSTANCE.call()) {
            user.setCurrentHand(hand);
            cir.setReturnValue((Object)ActionResult.CONSUME);
        }
    }
}

