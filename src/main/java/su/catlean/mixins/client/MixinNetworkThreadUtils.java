/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.network.AfterReceivePacket;

@Mixin(targets={"net/minecraft/class_11980$class_11981"})
public class MixinNetworkThreadUtils<T extends PacketListener> {
    @Final
    @Shadow
    private Packet<T> packet;

    @Inject(method={"method_74450"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_2596;method_65081(Lnet/minecraft/class_2547;)V", shift=At.Shift.AFTER)})
    private void afterPacketApply(CallbackInfo ci) {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
        Packet<T> packet = this.packet;
        if (packet instanceof BundleS2CPacket) {
            BundleS2CPacket packs = (BundleS2CPacket)packet;
            packs.getPackets().forEach(p -> new AfterReceivePacket((Packet<?>)p).call());
        } else {
            new AfterReceivePacket(this.packet).call();
        }
    }
}

