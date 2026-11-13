/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.network.SendMessageEvent;
import su.catlean.api.event.events.player.FlagEvent;
import su.catlean.api.event.events.world.WorldUpdateEvent;

@Mixin(value={ClientPlayNetworkHandler.class})
public class ClientPacketListenerMixin {
    @Inject(method={"method_11157"}, cancellable=true, at={@At(value="RETURN")})
    private void onPlayerPositionLookHook(PlayerPositionLookS2CPacket packet, CallbackInfo ci) {
        if (new FlagEvent().call(packet)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_45729"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendChatMessageHook(String message, CallbackInfo ci) {
        if (SendMessageEvent.INSTANCE.call(message)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_11136"}, at={@At(value="TAIL")})
    private void onBlockUpdateHook(CallbackInfo ci) {
        new WorldUpdateEvent().call();
    }

    @Inject(method={"method_11100"}, at={@At(value="TAIL")})
    private void onChunkDeltaUpdateHook(CallbackInfo ci) {
        new WorldUpdateEvent().call();
    }
}

