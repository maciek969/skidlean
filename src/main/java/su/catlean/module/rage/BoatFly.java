/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.VehicleMoveS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.api.event.events.player.ShouldDismountEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.BoatFlyMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class BoatFly
extends Module {
    @NotNull
    public static final BoatFly INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting phase$delegate;
    @NotNull
    private static final BooleanSetting gravity$delegate;
    @NotNull
    private static final BooleanSetting automount$delegate;
    @NotNull
    private static final BooleanSetting allowShift$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting yspeed$delegate;
    @NotNull
    private static final SettingGroup advanced$delegate;
    @NotNull
    private static final FloatSetting glidespeed$delegate;
    @NotNull
    private static final BooleanSetting slotClick$delegate;
    @NotNull
    private static final BooleanSetting limit$delegate;
    @NotNull
    private static final BooleanSetting ongroundpacket$delegate;
    @NotNull
    private static final BooleanSetting spoofpackets$delegate;
    @NotNull
    private static final FloatSetting jitter$delegate;
    @NotNull
    private static final BooleanSetting cancelrotations$delegate;
    @NotNull
    private static final BooleanSetting cancel$delegate;
    @NotNull
    private static final BooleanSetting pause$delegate;
    @NotNull
    private static final IntSetting enableticks$delegate;
    @NotNull
    private static final IntSetting waitTicks$delegate;
    @NotNull
    private static final FloatSetting timer$delegate;
    @NotNull
    private static final ArrayList A;
    private static int W;
    private static int f;
    private static boolean y;
    private static boolean Y;
    private static boolean S;
    private static final long b;
    private static final String[] i;
    private static final String[] t;
    private static final Map u;
    private static final long[] w;
    private static final Integer[] x;
    private static final Map C;

    private BoatFly() {
        super("boat-fly", Categories.getRage(), null, null);
    }

    private final BoatFlyMode getMode() {
        return (BoatFlyMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getPhase() {
        return (Boolean)phase$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getGravity() {
        return (Boolean)gravity$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getAutomount() {
        return (Boolean)automount$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getAllowShift() {
        return (Boolean)allowShift$delegate.getValue(this, delegatedProperties[4]);
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    private final float getYspeed() {
        return ((Number)yspeed$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    private final SettingParent getAdvanced() {
        return (SettingParent)advanced$delegate.getValue(this, delegatedProperties[7]);
    }

    private final float getGlidespeed() {
        return ((Number)glidespeed$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final boolean getSlotClick() {
        return (Boolean)slotClick$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getLimit() {
        return (Boolean)limit$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getOngroundpacket() {
        return (Boolean)ongroundpacket$delegate.getValue(this, delegatedProperties[11]);
    }

    private final boolean getSpoofpackets() {
        return (Boolean)spoofpackets$delegate.getValue(this, delegatedProperties[12]);
    }

    private final float getJitter() {
        return ((Number)jitter$delegate.getValue(this, delegatedProperties[13])).floatValue();
    }

    private final boolean getCancelrotations() {
        return (Boolean)cancelrotations$delegate.getValue(this, delegatedProperties[14]);
    }

    private final boolean getCancel() {
        return (Boolean)cancel$delegate.getValue(this, delegatedProperties[15]);
    }

    private final boolean getPause() {
        return (Boolean)pause$delegate.getValue(this, delegatedProperties[16]);
    }

    private final int getEnableticks() {
        return ((Number)enableticks$delegate.getValue(this, delegatedProperties[17])).intValue();
    }

    private final int getWaitTicks() {
        return ((Number)waitTicks$delegate.getValue(this, delegatedProperties[18])).intValue();
    }

    private final float getTimer() {
        return ((Number)timer$delegate.getValue(this, delegatedProperties[19])).floatValue();
    }

    @Override
    public void onEnable(long l) {
        block1: {
            long l2 = l ^ 0x1313C19FC590L;
            if (Globals.getMc().world == null || Globals.getMc().player == null) {
                this.disable(l2);
                return;
            }
            if (!this.getAutomount()) break block1;
            this.findBoat();
        }
    }

    @Override
    public void onDisable(long l) {
        A.clear();
        y = false;
        if (Globals.getMc().player == null) {
            return;
        }
        if (this.getPhase() && this.getMode() == BoatFlyMode.Motion) {
            Entity entity = Globals.getPlayer().getControllingVehicle();
            if (entity != null) {
                Entity entity2 = entity;
                entity2.noClip = false;
            }
            Globals.getPlayer().noClip = false;
        }
        Entity entity = Globals.getPlayer().getControllingVehicle();
        if (entity != null) {
            entity.setNoGravity(false);
        }
        Globals.getPlayer().setNoGravity(false);
    }

    private final float toggleState() {
        S = !S;
        return S ? this.getJitter() : -this.getJitter();
    }

    private final void sendVehiclePacket(VehicleMoveC2SPacket vehicleMoveC2SPacket) {
        A.add(vehicleMoveC2SPacket);
        PacketUtil.sendPacket(vehicleMoveC2SPacket);
    }

    private final void findGroundY(Entity entity) {
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n = 255;
        for (int i = 0; i < n; ++i) {
            if (!Globals.getWorld().getBlockState(blockPos2).isReplaceable() || Globals.getWorld().getBlockState(blockPos2).getBlock() == Blocks.WATER) {
                entity.setPosition(entity.getX(), blockPos2.getY() + 1, entity.getZ());
                VehicleMoveC2SPacket vehicleMoveC2SPacket = VehicleMoveC2SPacket.fromVehicle(entity);
                Intrinsics.checkNotNullExpressionValue(vehicleMoveC2SPacket, "fromEntity(...)");
                INSTANCE.sendVehiclePacket(vehicleMoveC2SPacket);
                entity.setPosition(entity.getX(), entity.getY(), entity.getZ());
                return;
            }
            Intrinsics.checkNotNullExpressionValue(blockPos2.down(), "below(...)");
        }
    }

    private final void findBoat() {
        Iterator<Entity> iterator2 = Globals.getWorld().getEntities().iterator();
        while (iterator2.hasNext()) {
            Entity entity;
            Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
            if (!(entity instanceof BoatEntity) || Globals.getPlayer().squaredDistanceTo(entity) > 25.0) continue;
            PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = PlayerInteractEntityC2SPacket.interact(entity, false, Hand.MAIN_HAND);
            Intrinsics.checkNotNullExpressionValue(playerInteractEntityC2SPacket, "createInteractionPacket(...)");
            PacketUtil.sendPacket(playerInteractEntityC2SPacket);
            break;
        }
    }

    @Flow
    private final void onShouldDismount(ShouldDismountEvent shouldDismountEvent) {
        block0: {
            if (!this.getAllowShift()) break block0;
            shouldDismountEvent.cancel();
        }
    }

    @Flow
    private final void onPlayerTravel(EventPlayerTravel eventPlayerTravel) {
        if (!this.isInGame()) {
            return;
        }
        if (Globals.getPlayer().getControllingVehicle() == null) {
            if (this.getAutomount()) {
                this.findBoat();
            }
            return;
        }
        if (this.getPhase() && this.getMode() == BoatFlyMode.Motion) {
            Entity entity = Globals.getPlayer().getControllingVehicle();
            if (entity != null) {
                Entity entity2 = entity;
                entity2.noClip = true;
                entity2.setNoGravity(true);
            }
            Globals.getPlayer().noClip = true;
        }
        if (!Y) {
            Entity entity = Globals.getPlayer().getControllingVehicle();
            if (entity != null) {
                entity.setNoGravity(!this.getGravity());
            }
            Globals.getPlayer().setNoGravity(!this.getGravity());
        }
        if (this.getPause()) {
            if (W > this.getEnableticks() && !y) {
                W = 0;
                y = true;
                f = this.getWaitTicks();
            }
            if (f > 0 && y) {
                f += -1;
                return;
            }
            if (f <= 0) {
                y = false;
            }
        }
        Entity entity = Globals.getPlayer().getControllingVehicle();
        if (entity == null) {
            return;
        }
        Entity entity3 = entity;
        if (!(this.getTimer() == 1.0f)) {
            TimerManager.INSTANCE.x(this.getTimer());
        }
        entity3.setYaw(Globals.getPlayer().getYaw());
        double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getSpeed(), false, 0.0f, 0.0f, 0.0f, 30, null);
        double d = entity3.getX() + dArray[0];
        double d2 = entity3.getZ() + dArray[1];
        double d3 = entity3.getY();
        Y = false;
        entity3.setVelocity(entity3.getVelocity().getX(), -this.getGlidespeed() / 100.0f, entity3.getVelocity().getZ());
        if (this.getMode() == BoatFlyMode.Motion) {
            entity3.setVelocity(dArray[0], entity3.getVelocity().getY(), dArray[1]);
        }
        if (Globals.getMc().options.jumpKey.isPressed()) {
            if (this.getMode() == BoatFlyMode.Motion) {
                entity3.setVelocity(entity3.getVelocity().getX(), entity3.getVelocity().getY() + (double)this.getYspeed(), entity3.getVelocity().getZ());
            } else {
                d3 += (double)this.getYspeed();
            }
        } else if (Globals.getMc().options.sneakKey.isPressed()) {
            if (this.getMode() == BoatFlyMode.Motion) {
                entity3.setVelocity(entity3.getVelocity().getX(), entity3.getVelocity().getY() - (double)this.getYspeed(), entity3.getVelocity().getZ());
            } else {
                d3 -= (double)this.getYspeed();
            }
        }
        if (!MovementUtil.INSTANCE.isMoving()) {
            entity3.setVelocity(0.0, entity3.getVelocity().getY(), 0.0);
        }
        if (this.getOngroundpacket()) {
            this.findGroundY(entity3);
        }
        if (this.getMode() == BoatFlyMode.Packet) {
            entity3.setPosition(d, d3, d2);
            VehicleMoveC2SPacket vehicleMoveC2SPacket = VehicleMoveC2SPacket.fromVehicle(entity3);
            Intrinsics.checkNotNullExpressionValue(vehicleMoveC2SPacket, "fromEntity(...)");
            this.sendVehiclePacket(vehicleMoveC2SPacket);
        }
        if (this.getSlotClick()) {
            Globals.getInteractionManager().clickSlot(Globals.getPlayer().currentScreenHandler.syncId, 0, 0, SlotActionType.CLONE, Globals.getPlayer());
        }
        if (this.getSpoofpackets()) {
            Vec3d vec3d = this.getPos(entity3).add(0.0, this.toggleState(), 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            Vec3d vec3d2 = vec3d;
            float f = entity3.getYaw();
            float f2 = entity3.getPitch();
            Vec3d vec3d3 = this.getPos(entity3);
            entity3.setYaw(entity3.getYaw());
            entity3.setPitch(entity3.getPitch());
            entity3.setPosition(vec3d2);
            VehicleMoveC2SPacket vehicleMoveC2SPacket = VehicleMoveC2SPacket.fromVehicle(entity3);
            Intrinsics.checkNotNullExpressionValue(vehicleMoveC2SPacket, "fromEntity(...)");
            this.sendVehiclePacket(vehicleMoveC2SPacket);
            entity3.setYaw(f);
            entity3.setPitch(f2);
            entity3.setPosition(vec3d3);
        }
        Globals.getPlayer().move(MovementType.SELF, Globals.getPlayer().getVelocity());
        eventPlayerTravel.cancel();
        ++W;
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        long l = b ^ 0x15D64BE74085L;
        long l2 = l ^ 0x61A57E643EB5L;
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            return;
        }
        if (receivePacket.getPacket() instanceof DisconnectS2CPacket) {
            this.disable(l2);
        }
        if (!Globals.getPlayer().isRiding() || Y || y) {
            return;
        }
        if (this.getCancel()) {
            if (receivePacket.getPacket() instanceof VehicleMoveS2CPacket) {
                receivePacket.cancel();
            }
            if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket) {
                receivePacket.cancel();
            }
            if (receivePacket.getPacket() instanceof EntityS2CPacket) {
                receivePacket.cancel();
            }
            if (receivePacket.getPacket() instanceof EntityAttachS2CPacket) {
                receivePacket.cancel();
            }
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block7: {
            if (Globals.getMc().world == null || Globals.getMc().player == null) {
                return;
            }
            if ((sendPacket.getPacket() instanceof PlayerMoveC2SPacket.LookAndOnGround && this.getCancelrotations() || sendPacket.getPacket() instanceof PlayerInputC2SPacket) && Globals.getPlayer().isRiding()) {
                sendPacket.cancel();
            }
            if (Y && sendPacket.getPacket() instanceof VehicleMoveC2SPacket) {
                sendPacket.cancel();
            }
            if (sendPacket.getPacket() instanceof PlayerInputC2SPacket && this.getAllowShift()) {
                sendPacket.cancel();
            }
            if (Globals.getPlayer().getControllingVehicle() == null || Y || y) {
                return;
            }
            if (!(sendPacket.getPacket() instanceof VehicleMoveC2SPacket) || !this.getLimit() || this.getMode() != BoatFlyMode.Packet) break block7;
            if (A.contains(sendPacket.getPacket())) {
                A.remove(sendPacket.getPacket());
            } else {
                sendPacket.cancel();
            }
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getSpoofpackets();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getPause();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getPause();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        BoatFly.b = ZkmKeys.a(5724145000750933656L, 124536481492501746L, MethodHandles.lookup().lookupClass()).a(259120174945635L);
                        var20 = BoatFly.b ^ 14818885469485L;
                        BoatFly.u = new HashMap<K, V>(13);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[68];
                        var16_4 = 0;
                        var15_5 = "\u0092\u00ff\u008cY\u0003M\u0091\u0019\u0094iR\u008b\u00a6\u00d3H@\u009e\u0097W\u00c4\u0091\u00ffgO\u0010Av\u00e7UAS7\u00c1\u0011\u00df\u0098s70z\f v`f\u00a7\u00be\u001b\u0083b\u00f9a\u00eaS\u00f0\u00f7\u00c5\u00c9\u00c5\u00d4e1\u00f2\u0095\u00bcc4\u000f:\u0015<\u00b1\u00aa@ \u00a0\u008a?Q\u00e8[m\u00af\u008d>=\u0010\u00e4\u00e8\u009e\u00fa]\u00b5\u001c\u00b7S\u00fbH_\u00eb\u00c8\u00db3=\u00ad4\b\u0010d\u0006\u00a97\u00c0\u001a\u00b6f\u0097\u00f8\u00e9\u00ab\u00f3\\\u0094?\u00105xG\u00a2\u00ee\u008a:}\u00bb\u0084D\u00c2\u00a1\u0016Va\u0018\u00c0\u0080\u00f0\u0095_?T'7OqM\u00bf`E|>\u001cmD\u00a4\u000b\u00b6x \u0001\u00d3\u0003\u00ffLX\u0000\u00ab\u008ad\u00c8\u00eb!^m\u0015\u00d1\u0091\u00d6\u0093O\u0014\u008f\u00de\u00ba\u0005\u009f-<(x\u0019\u0018\u00efE\u00b4nck\u009a\u00a8m\u00f9\u00d8a5\u0097a\u001e_<\u00d1\u00b9:\u00f1\u00ff\u000b WI\u001b\u0014\u000b\u009dI\u0097P\u00a9\u001c\u009d\u00c9\u0082\u00e3\u0005\u00e0\u00ff@\u0091>l1,\u0002o\u009a\\\u00d8\u00c1i\u00ff\u00109w\u00efv\u00e6\u00f8\u0011\u009e\u0081\u0080F\u0017-\u00dcCB(s\u00851\u008dME\u0006\u0084\u0017\u0014\u00e1\u009a\u000eo\u00d8\u0088;Y\u00cea>e%\u0088\u0015\u00e8\u008e\u009b\u00dd\u00b6\u0083\u0011\u000f\u0093\u00ba\u0014SIm\u0099 \u0091j\u009b`\u00f1\u00cd\u00deSY\u00e1b2a\u00a0&\u0098\u001a\u008c\rM\u00f4\u00feX\u0091r\u0081\u0095\u00da\u0014\u00a8e\u00ba \u00d3y\u00cbK\u00a5C\u00bbD\u001cdG5.\u008c\u008c\u00b7\u00bam\u0005\u00a1\u0081\u00d7\u00e2\u000f\u0091\u00c8\u00b1\u0090&\u0003\u0003 (\u00f1\u001b\r\u00bc\u0019\u00fd\u00ff\u0019\u00c32(\u0010\u00b8\u0007\u00d7vNS\u0015\u00c2\u00b9\u00e3\u00d0[\u0095\u000e\u0019\u0019\u000e\u00c9\u00b7,+\u0014\u00e7\u0095\u0099W\u0089\u0089\u0018:\r\u0087h\u0088\u0012\u00f0\u00d3\u00eb\u00d6\u009a\u00f7\u000b\u00f4\\8\u0005\u00a2D\u00cf@u\u0081P \u0088h\u009c\u0083L\u00cd\u00d5\u009a\u00f2\u00f4C0\u00d2D\u0092\u001bp-\u0090\u00b2\u00c9\u001c\u0089\u00ca\u00a3\u0096\t\u00c4x\u001c\u00a4<\u0018\u00dal-x\u00e9\u00a6i\u00a7\u00f6\u00d7\u00cd \u0000F\r\u00e0\u00b5\u00b3\u0013x=\u0097\u0018\u0091\u0018TQ\u00c2}\u009b\u00e5\u00e4\u0087-s\u00cf\u00ef[\u00ed~\u00cee\u00f5\\\u00b1\u00d9\u00af\u00abF \u00b1EJ[W=\u0017\u00c7\u00a1\u0011\u00c0?Z\u00adk^~\u00f1\u0094\u00c6\"j\u00e0\u00d7\u00b1\u0090\u00a1\\\u00cby\u0083h\u0018.4w.>CJ\u00a6\u00c7\u00e2\u00faJ>\u009a\u0015\u00b1\u00bd\u0011\u00b3g\u0015o5J(\u0099\"\u0088\u0091!\u00fc\u00a05\u00e7,\u0000D\u00e0\u00a20\u00b4nJu\u00b6\u0089\u00cc\u00ad\u00bf\u001f[\u00b6&VPp\f\u000e;Z\u00e6\u00a8\u00be\u001eM \u008f\u0013\u009bH\u000698\u00c2\u00ff\u00828`\u009a\u00b3\u0012\u0012\f\u00bf\u00fc\u008c\u00e7\u001d\u0096g6z~\u00f7%\u0086\\}(\u0086/\u00c6\u00c5\u008c\u00faT\u00b5I\u00b4\u009f\u00ef\u00c4\u00f3\u0096_\u00cc\u0083!ej=S\u00ee\u0083\u0094\u00e7E\u00113M[\fru\u0099\u00c8\u0085\u00a8w\u0018\u008dF\u00d3P\u00be\u00bb\u00f0N\u00ab~\u0001\u00c1f\u0001b?\b\u00bd\u009dO\u00c4\u00e0\u00df\u00f5 \u0000\u00b1\u00d2\u00d6\u0092n/{B\u00e3P\u00b5U\u0003E\u0010\u0017J\\\u0083c\u000b\u0093{\u00a3\u00db&g\u0010\u00d7s?\u0018d8o\u00dcY7\u0099\u00956N\u0084\u00ad\u0015\u00aef\u00fa\b\u00cao\u0095\u008a\u00f5+\u00b1\u0010\u00ef\u000b\t\u001c\u00fa\u001c\u00ddf\u00bav\u00bc\u00e1\u00f0\tN# \u00c2ya\u0014\u00c32\u00a4&\u00fd\u00f7w\u00db\u00de@\u00a0\fC\u000fA>u\u0082\u00fc\u00ff\u008a\u00e8D\u00bd\u00dc\u0018)!\u0010&\u0081\u000e\u00a4&\u00dfZ\u00e1I\u00f5\u009bV\u00a3\u00fa\u00a6\u00e2\u0010U\u00d5^\u0089X\u00bcE\u0088a\u0000o\u00e6\u00a8\u00dd\u00ec\u00ee\u0010E\u00c6B\u00bf\u00de\u0083Nl\u00f3\u00f3\u0005\u00e7\u001d\u0010\u0092\u00e4(%\u00d2QX\u0094\u00d8\u00ce\u0090J\r\u0082\u0018*!wU\u0018\u00eb\u00c1}\u0006NC\u00b3\u00fb\u00fci\u0096\u00a3#`\u00e1\u00c1\u0080\u0001a\u00b7\u00e2\u00d9U(\u007fa7\u00ec\u00de\u008c\u00caD\u0011\u00f8\u0016M>\u008f1w\u00b1\u00d7\u00f3M\u00dcZ\u00c5w\u00e18\u00f9~x\u0096\u00e2\u00b4\u00cd\u007f\u00f4$RV\u0005\u00ee\u0010\u00ca\u0014/\u00f0DB \u001fT\u0092\u0012\u00e9\u0011k\u00d6\u0005 \u00b1\u00c06g,\u0096\u00b8g\u001e\u00d8\u00a4\u00ed\u00c5\u00a63\u00d0\u008d?\u001c\u00b18\u000b\u0019Z\u0082dz\u0007\u00de\u0011<\u00c20\u0099Ue\u00db\u00ab\u00e1\u008e\u00e4\u0087VuI@}\u001c\u00c2\u0011d\u00bf3\u00a2r\u00e3\u00e7\u00a4(Z\u0095\u0084\u00b1\u0086\u0080(\u0001nG\u00f1o\u001aFW\u00bd^\u00d8PD\u00de\u00fd \u00d7\u0003\u00e1\bC\u009b{J\u001a\u00c0*\u0019\u00be\u008f\u008cm\u00c5x\u00e6w7!\u00b6\u00e0\u00fc\u0081\u00ec\u009d\u00df-\u00e7\u00e2 Y\u0095\u00e6U\\\u00ff\u00d7^\u0098\u00d3\u0081\u00e8[x\u0091V\b\u00d5\u00ed\u00f7B\u00cb\u00bfN>\u00bcZ\u0015\u00c9\u00d5\"\u00df\u0018\u0096\u00fc\u00c5\u0013\u00ea\u00a0\u0007\u00e2\u00f0>\u00ca8\u00a4%\u0094th~\u00cc_\u00ef\u0015x\u001f\u0018\u00fcd\u00f0\u00ecO\u00e0{\u00bck\u00e3\u00a0\u008b_\u00e4\u00a8\u0010\u0003\u0016\u00ec_\u000fB\u00cd\u0083\u0010\u00deo\u00d3\b\u008cj\u00fal\u0080@~\u0002\u009b\u0080]\u00e2\u0018\u00bd\ns\u000e\u00f4\u00e2\u00abSzp\n\u009c\u00de\u0090\u0091oz\u00c8N\u00e2v\u00d1\u00b8\u00f2\u0010\u00c4\u0092\u00aa]E\u0099[\u00a4<=`\u0085\u00d1=0\u00b5\u0010\u00a94\u009e@D\u00b9\u00b8z\u00eaQ+\u00a2\u00c4\u0002\u00fd\u00ff\u0018\u00b1\u00d1<i\u0080\u00e0\u0081\u00a5kr\u000b\u0082\u00f9\u00b2[\u00d7\u00ec\u00ea\u0087\u00cc\u00e1\u00a0\u00bd\u0081\u0018\u007f\u0094\u00f0AL\u00bca\u009cb\u0015\u001c\u00e8\u0000\u00e2cH9k3\u00df1\u00c0\u00a9y\u0018\u00be\u0092o\u0088I\u009aK\u00fbt<\u00e8B \u00da\u0089\u001b\u00aa%\u00ea\u00b9\u008b\u00ff\u0002\u008d(\u00fc\u00e8\u00c9\u0088j\u00be\u00f9\u00b9\u00c7we\u0081\r\n\u0001\u00d5\u0095Z\u00ef.\u00835\u00a8^\u00ea$\u008e%Z\u00be\u00d3\u00a0\u00a5(\u00f5\u0006\"\u0095\u008b7 \u00a4Z\u0014q+\u008f\u0018'P\u00f2\u00e4\u008fL\u0016\u0082p\n\u00c3\u00ce\u001c(\u008f\u00c7u5\u0085w\u00b3n\u00a0-\u00c8\u0018\u009e\u00ab\u009d\u0081\u0098c\u0092\u0082\u00fcSBIJ\u00e9\u00c5\n\u0097\u00ac\u0091\u0086\u00cd\u00e3\u0013\u00d6\u0010ja\u00c9\u0015#L\u00b4\b\u00f3?Th\u00af>Y\u00e3 \u0005+\u00cb\u00b0\rH\u00e2\u00ea\u001d.\u00cf\u00a4\u0099\u009c\u00c0\u009f\u00fc\u00c79\u0013\u0093\u001d\u0091\bu\u00e0\u00f3|\u001f\u00a3\u00a4F\u00106|Ld\u00f7*I\u0014^2\u00fd=O\u00ae\u007f\u00c8 \u0017\u0003\u0095\u00ae\u00dc\"\u00a5\u00bf\u00d4\n\u0015\u00edf7<\u00f7\u00eb\u008a\u008f\u00c2l&\u00dbA\u00d3\u00a9\u00d3\u00d7\u008d\u00e3\u0005`8*\u00a0E\u00edle\u00ae(C\u00bd.\u008d\u0088\u00af\u00a0\u0094%\u00c4JrV\u0090\u00f4-\u0099\u00deK\u0091\u00d7yA\u00f4e\"b\u00c3\u00b2;*\u00ed\u00b4\u00bc\u00ef\u00956\u00df\u00c9&H\u00e8\u00cf\u001fV\u00d6\u00f9\u00e1\u0018\u00d4\u009c\u00d11\u00f3E${\u00f6{\u0083\u00b5\u0000\u00e4\u00baq$(z\u00d8\u00d4mF\u00e0\u0010\u00ea\u00c00\u00e69\u00fc\u00c4\u00fa\u00b6\u00c6\u00c8\u00c6\u00ae\u00e4\u00bb\u0019\u0010\u00ec\u0094\u00c2\u00aa\u00c77\u00b1\u00e4\u00c5\u0005\u00af`\u008ad:|\u0018A\u0085\u008f\u00eb\u0005\u00d5;\u00d1\u008b\u009f\u008e\u00c7T\u0090<+\n\u009b\u00f7\u0005\u001fF\u00d84\u0010\u00c4Z\u0083\t<\u00ef\u00f6\u00b6I\u0092\u00ee\u00f9\u00ef\u0015\u00d5c\u0010\u0092\u001b\u00b7\u0007\u00e5l)dQ\u00de\u0090\u00e5h\u00fa\u00f3`\u0010\u0016@\u009c\u0086\u00ef\u0019,(\u00e2t\u00a4\u00ea?\u0097h\u00b0(1TR\u0092\u0014\u00aa1\u00cf\u0087\u00af\u00fa\u009d\u00e75\u00df\u008d\u00139j\u00c7\u0005\u001b\u009c\u001e\u00a4\u00d4\u00c4\u0091\u00d4N\u0003T\u00b62\u0085\u0019\u00e4\u0083c\u00d0\u0010,\u00ac\u00a7\u00e2N/\u0090Y\u00b4\u00d8\u0097\u00c3j\b\u008a\u00b1 \u00f3\u00c0 \u00c0\u001f\u00dbL\u00e1P\u00c1ch\u00cb!9l\u0088\u00c1\u00d2$*\u009a9\f\u0018(H\u0081M\u00d46\u0015";
                        var17_6 = 1817;
                        var14_7 = 24;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = BoatFly.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ac@\u0000\u0010\u00a8ey\t\u00ff\fH\u009a\u00bb\u008c'Lq\u001d[\u00be(\u0091\u00e4't\u0091\u0010N5%!\u00c28\u0080Xo\u00fc)\u00c6\u0019\u0082\u00fa\u0095N2\u008e\u00c9=\u0090\u00b3\u00d6\u008e\u00dc\u0095=<7\u0000\u00b0\u00fa\u0081\u00ces\u00c7\u00f3~\u0081&\u00d3\u00d2\u0090P&\u00d7\u00ec\u00b9\u008c\u00ee-\u0010\u00edF\u00d4\u00bbKv\u00c0\u00a2\u00e7";
                            var17_6 = 89;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = BoatFly.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                BoatFly.i = var18_3;
                BoatFly.t = new String[68];
                BoatFly.C = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[35];
                var3_13 = 0;
                var4_14 = "\u0080`5#9\u00da\u00a3\u00b9\u00e4\u0019X\u00c1\u00cc\u00e2Z\u00f3\u00b1\u00a0\u00b6\u00be\u0016\u00a6T\u00f1\u00bb\u008a`3\u00e8C\u0094q\u0082%o\u0017\u007f\u00fa\u00a0+S\u0007\u00b6\u00d2\u00f3\u0087i\u00d6\u009d\u0080\u00f4\u00cf\u00d1\u00c1N\u0016M\u0004\u00fb\u00a7e\u009d\u00f8\u001a\u0083\u008cC\u008f\u00ae\u0015\u00cc\u000e\u00d0D\u00e2{\t\u00a1\u00dc\u0005^`m\u00d3\u00f9\u00f2a\u0099\u00ba\u00f5\u008b :\u00c0\u00d7z\u0084yg\u00ea\u00c8\u00f9j\u0084\"\u00a3\u0002\u00a8Up{\u00a7\u0084\u00bc\u009f\u0019\u00ae\u0017\u0019\u009fQ\u0004--F'\u00f5V\u0018\u0089\u00ab\u00e5g\u00bbP\u00a5\u00a4~\u00e3\u001f\u00fdF\u00c5\u0013\u00b6\u0000\u0019\u00eb\u0099\u0098\u00f2\u0017\u009dU\u00d0\u00df\u00a2\u00d3\u007f\u00b4\u0083\u001f\u00fbz5:\u00d5n\u001c@\u001c\u00c9\u00cb\u00ea\u0082zE\u00a9\u00c9\u00c3_\u00da\u0082\u008a\u008e\u00b79\u009f\u00ccs\u009c\u0090\u00f3\u0016Z\u00a5\u001bUi\u00aaA\u0017\u0004\u00c4f\u00cb\u00df\u007f\u00a5\u00d5\u00b2\u001b\u001d,\u0087\u00b1\u00c9\u00b2\u0098\u009d\u001b\u00a9\u0004\u00c9\u0010\u00ee\n\u008czV._\u000f\u00c3\u0094\u0082Yy\u00ef\u009d\u000b\u001b\n&T\u00a5es\u00c8\u0088\u00a2\u00ed\u0005\u00d9\u0085\u00a4\u00b1\u00d2E\u008d\u009b`\u00e3x";
                var5_15 = 264;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "e\u001f\u00adXZ\u0002\u0085\u00d0P\u00bc\u0080\u00c5/\u00e3d\u00c8";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        BoatFly.w = var6_12;
        BoatFly.x = new Integer[35];
        var39_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(BoatFly.class, "mode", "getMode()Lsu/catlean/ik;", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "phase", "getPhase()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "gravity", "getGravity()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "automount", "getAutomount()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "allowShift", "getAllowShift()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "yspeed", "getYspeed()F", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "advanced", "getAdvanced()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "glidespeed", "getGlidespeed()F", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "slotClick", "getSlotClick()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "limit", "getLimit()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "ongroundpacket", "getOngroundpacket()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "spoofpackets", "getSpoofpackets()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "jitter", "getJitter()F", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "cancelrotations", "getCancelrotations()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "cancel", "getCancel()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "pause", "getPause()Z", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "enableticks", "getEnableticks()I", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "waitTicks", "getWaitTicks()I", 0)), Reflection.property1(new PropertyReference1Impl(BoatFly.class, "timer", "getTimer()F", 0))};
        BoatFly.delegatedProperties = var39_20;
        BoatFly.INSTANCE = new BoatFly();
        BoatFly.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)BoatFly.INSTANCE, "mode", (Enum)BoatFlyMode.Packet, null, null, 12, null);
        BoatFly.phase$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "phase", false, null, null, 12, null);
        BoatFly.gravity$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "gravity", false, null, null, 12, null);
        BoatFly.automount$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "auto-mount", true, null, null, 12, null);
        BoatFly.allowShift$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "allow-shift", true, null, null, 12, null);
        BoatFly.speed$delegate = SettingFactory.floatSetting$default(BoatFly.INSTANCE, "speed", 2.0f, RangesKt.rangeTo(0.0f, 25.0f), null, null, 24, null);
        BoatFly.yspeed$delegate = SettingFactory.floatSetting$default(BoatFly.INSTANCE, "y-speed", 1.0f, RangesKt.rangeTo(0.0f, 10.0f), null, null, 24, null);
        BoatFly.advanced$delegate = SettingFactory.settingGroup$default(BoatFly.INSTANCE, "advanced", null, null, 6, null);
        var40_21 = null;
        var41_22 = 16;
        var42_23 = null;
        var43_24 = BoatFly.INSTANCE.getAdvanced();
        var44_25 = RangesKt.rangeTo(0.0f, 10.0f);
        BoatFly.glidespeed$delegate = SettingFactory.floatSetting$default(BoatFly.INSTANCE, "glide-speed", 0.0f, var44_25, var43_24, var42_23, var41_22, var40_21);
        BoatFly.slotClick$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "click-slot", false, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.limit$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "limit", true, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.ongroundpacket$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "on-ground-packet", false, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.spoofpackets$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "spoof-packets", false, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.jitter$delegate = SettingFactory.floatSetting((SettingContainer)BoatFly.INSTANCE, "jitter", 0.1f, RangesKt.rangeTo(0.0f, 10.0f), BoatFly.INSTANCE.getAdvanced(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)());
        BoatFly.cancelrotations$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "cancel-rotations", true, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.cancel$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "cancel", true, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        BoatFly.pause$delegate = SettingFactory.booleanSetting$default((SettingContainer)BoatFly.INSTANCE, "pause", false, BoatFly.INSTANCE.getAdvanced(), null, 8, null);
        var47_26 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)();
        var48_27 = BoatFly.INSTANCE.getAdvanced();
        var49_28 = new IntRange(1, 100);
        var50_29 = 10;
        var51_30 = "enable-ticks";
        var52_31 = BoatFly.INSTANCE;
        BoatFly.enableticks$delegate = SettingFactory.intSetting(var52_31, var51_30, var50_29, var49_28, var48_27, var47_26);
        var55_32 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)();
        var56_33 = BoatFly.INSTANCE.getAdvanced();
        var57_34 = new IntRange(1, 100);
        var58_35 = 10;
        var59_36 = "wait-ticks";
        var60_37 = BoatFly.INSTANCE;
        BoatFly.waitTicks$delegate = SettingFactory.intSetting(var60_37, var59_36, var58_35, var57_34, var56_33, var55_32);
        var63_38 = null;
        var64_39 = 16;
        var65_40 = null;
        var66_41 = BoatFly.INSTANCE.getAdvanced();
        var67_42 = RangesKt.rangeTo(0.1f, 5.0f);
        BoatFly.timer$delegate = SettingFactory.floatSetting$default(BoatFly.INSTANCE, "timer", 1.0f, var67_42, var66_41, var65_40, var64_39, var63_38);
        BoatFly.A = new ArrayList<E>();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4A2E;
        if (t[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])u.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    u.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/uj", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            BoatFly.t[n2] = BoatFly.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return t[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BoatFly.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/uj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3ADB;
        if (x[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = w[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])C.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    C.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/uj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BoatFly.x[n2] = n3;
        }
        return x[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BoatFly.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/uj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BoatFly.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(BoatFly.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

