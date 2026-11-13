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
package su.catlean.mixins.player;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.InsertStackEvent;
import su.catlean.api.event.events.player.UpdateSelectedSlotEvent;

@Mixin(value={PlayerInventory.class})
public class InventoryMixin {
    @Inject(method={"method_7394(Lnet/minecraft/class_1799;)Z"}, at={@At(value="HEAD")})
    private void insertStackHook(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        InsertStackEvent.INSTANCE.setStack(stack);
        InsertStackEvent.INSTANCE.call();
    }

    @Inject(method={"method_61496"}, at={@At(value="HEAD")}, cancellable=true)
    private void setSelectedSlotHook(int slot, CallbackInfo ci) {
        UpdateSelectedSlotEvent.INSTANCE.setSlot(slot);
        if (UpdateSelectedSlotEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

