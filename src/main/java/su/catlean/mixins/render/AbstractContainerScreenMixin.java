/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.RenderToolTipEvent;
import su.catlean.api.event.events.render.SlotRenderEvent;

@Mixin(value={HandledScreen.class})
public abstract class AbstractContainerScreenMixin<T extends ScreenHandler>
extends Screen
implements ScreenHandlerProvider<T> {
    @Shadow
    @Nullable
    protected Slot focusedSlot;
    @Shadow
    protected int x;
    @Shadow
    protected int y;

    protected AbstractContainerScreenMixin(Text title) {
        super(title);
    }

    @Inject(method={"method_25394"}, at={@At(value="TAIL")})
    private void renderHook(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.focusedSlot != null && !this.focusedSlot.getStack().isEmpty() && MinecraftClient.getInstance().player.playerScreenHandler.getCursorStack().isEmpty()) {
            RenderToolTipEvent.INSTANCE.call(this.focusedSlot.getStack(), context, mouseX, mouseY);
        }
    }

    @Inject(method={"method_2385(Lnet/minecraft/class_332;Lnet/minecraft/class_1735;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_51428(Lnet/minecraft/class_1799;III)V", shift=At.Shift.BEFORE)})
    protected void drawSlotHook(DrawContext drawContext, Slot slot, int i, int j, CallbackInfo ci) {
        SlotRenderEvent.INSTANCE.setSlot(slot);
        SlotRenderEvent.INSTANCE.setContext(drawContext);
        SlotRenderEvent.INSTANCE.call();
    }
}

