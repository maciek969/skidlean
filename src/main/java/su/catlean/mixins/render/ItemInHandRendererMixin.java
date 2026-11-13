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

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.EquipArmEvent;
import su.catlean.api.event.events.render.HandModifyEvent;
import su.catlean.api.event.events.render.SwingArmEvent;

@Mixin(value={HeldItemRenderer.class})
public class ItemInHandRendererMixin {
    @Inject(method={"method_3228"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V")})
    private void onRenderItem(AbstractClientPlayerEntity player, float tickProgress, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, int light, CallbackInfo ci) {
        HandModifyEvent.INSTANCE.call(hand, matrices, item, false);
    }

    @Inject(method={"method_3218"}, at={@At(value="HEAD")}, cancellable=true)
    private void applyEatOrDrinkTransformationHook(MatrixStack matrices, float tickDelta, Arm arm, ItemStack stack, PlayerEntity player, CallbackInfo ci) {
        Hand hand = MinecraftClient.getInstance().options.getMainArm().getValue() == Arm.RIGHT ? (arm == Arm.RIGHT ? Hand.MAIN_HAND : Hand.OFF_HAND) : (arm == Arm.RIGHT ? Hand.OFF_HAND : Hand.MAIN_HAND);
        if (HandModifyEvent.INSTANCE.call(hand, matrices, stack, true)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_65816(FLnet/minecraft/class_4587;ILnet/minecraft/class_1306;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void swingArmHook(float f, MatrixStack matrixStack, int i, Arm arm, CallbackInfo ci) {
        if (SwingArmEvent.INSTANCE.call(f, matrixStack, i, arm)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_3224"}, at={@At(value="HEAD")}, cancellable=true)
    private void swingArmHook(MatrixStack matrices, Arm arm, float equipProgress, CallbackInfo ci) {
        if (EquipArmEvent.INSTANCE.call(equipProgress, arm)) {
            ci.cancel();
        }
    }
}

