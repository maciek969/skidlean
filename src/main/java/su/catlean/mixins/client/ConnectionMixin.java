/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPipeline
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.handler.PacketSizeLogger;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.network.AddHandlersEvent;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;

@Mixin(value={ClientConnection.class})
public class ConnectionMixin {
    @Inject(method={"method_10770(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/class_2596;)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        if (MinecraftClient.getInstance().player == null || packet == null) {
            return;
        }
        if (packet instanceof BundleS2CPacket) {
            BundleS2CPacket packs = (BundleS2CPacket)packet;
            packs.getPackets().forEach(p -> {
                if (new ReceivePacket((Packet<?>)p).call()) {
                    info.cancel();
                }
            });
        } else if (new ReceivePacket(packet).call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_10743(Lnet/minecraft/class_2596;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSendPacketPre(Packet<?> packet, CallbackInfo info) {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
        if (new SendPacket(packet).call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_10743(Lnet/minecraft/class_2596;)V"}, at={@At(value="RETURN")}, cancellable=true)
    private void onSendPacketPost(Packet<?> packet, CallbackInfo info) {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
        if (new AfterSendPacket(packet).call()) {
            info.cancel();
        }
    }

    @Inject(method={"method_48311"}, at={@At(value="RETURN")})
    private static void addHandlersHook(ChannelPipeline pipeline, NetworkSide side, boolean local, PacketSizeLogger packetSizeLogger, CallbackInfo ci) {
        AddHandlersEvent.INSTANCE.call(new AtomicReference<ChannelPipeline>(pipeline), side, local);
    }
}

