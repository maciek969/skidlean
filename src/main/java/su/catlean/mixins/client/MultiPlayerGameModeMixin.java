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
package su.catlean.mixins.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.catlean.api.event.events.player.AttackBlockEvent;
import su.catlean.api.event.events.player.BreakBlockEvent;
import su.catlean.api.event.events.player.ClickSlotEvent;
import su.catlean.api.event.events.player.InteractBlockEvent;
import su.catlean.api.event.events.player.PickUpBlockEvent;
import su.catlean.api.event.events.player.PostInteractItemEvent;
import su.catlean.api.event.events.player.PreInteractItemEvent;
import su.catlean.api.event.events.player.StopUsingItemEvent;
import su.catlean.api.event.events.world.UpdateBlockBreakingProgressEvent;

@Mixin(value={ClientPlayerInteractionManager.class})
public class MultiPlayerGameModeMixin {
    @Inject(method={"method_2902"}, at={@At(value="HEAD")}, cancellable=true)
    public void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (UpdateBlockBreakingProgressEvent.INSTANCE.call()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_2910"}, at={@At(value="HEAD")}, cancellable=true)
    private void attackBlockHook(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (AttackBlockEvent.INSTANCE.call(direction, pos)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_2899"}, at={@At(value="RETURN")}, cancellable=true)
    public void breakBlockHook(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BreakBlockEvent.INSTANCE.call(pos)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_2906"}, at={@At(value="HEAD")}, cancellable=true)
    public void clickSlotHook(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (ClickSlotEvent.INSTANCE.call(actionType, slotId, button, syncId)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_2897"}, at={@At(value="HEAD")}, cancellable=true)
    public void stopUsingItemHook(PlayerEntity player, CallbackInfo ci) {
        if (StopUsingItemEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_2896"}, at={@At(value="HEAD")}, cancellable=true)
    private void interactBlockHook(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (InteractBlockEvent.INSTANCE.call(hand, hitResult)) {
            cir.setReturnValue((Object)ActionResult.PASS);
        }
    }

    @Inject(method={"method_2919"}, at={@At(value="HEAD")}, cancellable=true)
    private void preInteractItemHook(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (PreInteractItemEvent.INSTANCE.call(hand)) {
            cir.setReturnValue((Object)ActionResult.PASS);
            cir.cancel();
        }
    }

    @Inject(method={"method_2919"}, at={@At(value="RETURN")})
    private void postInteractItemHook(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        PostInteractItemEvent.INSTANCE.call();
    }

    @Inject(method={"method_65193"}, at={@At(value="HEAD")}, cancellable=true)
    public void pickItemFromBlockHook(BlockPos pos, boolean includeData, CallbackInfo ci) {
        if (PickUpBlockEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

