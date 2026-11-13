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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.optionals.OptionalsKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.rage.Blink$WhenMappings;
import su.catlean.module.rage.BlinkAttackActionMode;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.render.RenderState;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Blink
extends Module {
    @NotNull
    public static final Blink INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting limit$delegate;
    @NotNull
    private static final ModeSetting attackAction$delegate;
    @NotNull
    private static final ModeSetting limitAction$delegate;
    @NotNull
    private static final BindSetting cancel$delegate;
    @NotNull
    private static final BooleanSetting showPosition$delegate;
    @NotNull
    private static final BooleanSetting delayOnlyMove$delegate;
    @NotNull
    private static final BooleanSetting ignoreInventoryActions$delegate;
    @NotNull
    private static final BooleanSetting onlyInRange$delegate;
    @NotNull
    private static final FloatSetting range$delegate;
    @NotNull
    private static final SettingGroup resetOn$delegate;
    @NotNull
    private static final BooleanSetting velocityReset$delegate;
    @NotNull
    private static final BooleanSetting lagBackReset$delegate;
    @NotNull
    private static final BooleanSetting openScreenReset$delegate;
    @NotNull
    private static Vec3d savedVelocity;
    private static float savedYaw;
    private static boolean L;
    @NotNull
    private static final Queue receivedPackets;
    @NotNull
    private static final Queue sentPackets;
    @NotNull
    private static final AtomicBoolean isFlushing;
    @Nullable
    private static Input savedInput;
    @Nullable
    private static Vec3d startPos;
    private static boolean Y;
    private static String f;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map l;

    private Blink() {
        super("blink", Categories.getRage(), null, null);
    }

    private final int getLimit() {
        return ((Number)limit$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final void setLimit(int n) {
        limit$delegate.setValue(this, delegatedProperties[0], n);
    }

    @NotNull
    public final BlinkAttackActionMode getAttackAction() {
        return (BlinkAttackActionMode)((Object)attackAction$delegate.getValue(this, delegatedProperties[1]));
    }

    public final void setAttackAction(@NotNull BlinkAttackActionMode blinkAttackActionMode) {
        block0: {
            long l = 0L;
            l = a ^ l;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8263241049007031219L, (long)l);
            Intrinsics.checkNotNullParameter((Object)blinkAttackActionMode, (String)((Object)Blink.zkmBootstrap("m", (int)25409, (long)(0x468A8C462C846640L ^ l))));
            CallSite callSite2 = callSite;
            attackAction$delegate.setValue(this, delegatedProperties[1], (Object)blinkAttackActionMode);
            if (callSite2 == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[3], (long)8255900584660727983L, (long)l);
        }
    }

    private final BlinkAttackActionMode getLimitAction() {
        return (BlinkAttackActionMode)((Object)limitAction$delegate.getValue(this, delegatedProperties[2]));
    }

    private final void setLimitAction(BlinkAttackActionMode blinkAttackActionMode) {
        limitAction$delegate.setValue(this, delegatedProperties[2], (Object)blinkAttackActionMode);
    }

    private final Bind getCancel() {
        return (Bind)cancel$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getShowPosition() {
        return (Boolean)showPosition$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getDelayOnlyMove() {
        return (Boolean)delayOnlyMove$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getIgnoreInventoryActions() {
        return (Boolean)ignoreInventoryActions$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getOnlyInRange() {
        return (Boolean)onlyInRange$delegate.getValue(this, delegatedProperties[7]);
    }

    private final float getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final SettingParent getResetOn() {
        return (SettingParent)resetOn$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getVelocityReset() {
        return (Boolean)velocityReset$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getLagBackReset() {
        return (Boolean)lagBackReset$delegate.getValue(this, delegatedProperties[11]);
    }

    private final boolean getOpenScreenReset() {
        return (Boolean)openScreenReset$delegate.getValue(this, delegatedProperties[12]);
    }

    @Nullable
    public final Vec3d getStartPos() {
        return startPos;
    }

    public final void setStartPos(@Nullable Vec3d vec3d) {
        startPos = vec3d;
    }

    @Override
    public void onEnable(long l) {
        long l2 = l ^ 0x1313C19FC590L;
        if (Globals.getMc().player == null || Globals.getMc().world == null || Globals.getMc().isIntegratedServerRunning()) {
            this.disable(l2);
            return;
        }
        sentPackets.clear();
        if (!this.getOnlyInRange()) {
            Entity entity = Globals.getPlayer();
            startPos = this.getPos(entity);
        }
        Vec3d vec3d = Globals.getPlayer().getVelocity();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getDeltaMovement(...)");
        savedVelocity = vec3d;
        savedYaw = Globals.getPlayer().getYaw();
        L = Globals.getPlayer().isSprinting();
        isFlushing.set(false);
        receivedPackets.clear();
        savedInput = Globals.getPlayer().input;
    }

    @Override
    public void onDisable(long l) {
        if (this.isInGame()) {
            while (!receivedPackets.isEmpty()) {
                Object e = receivedPackets.poll();
                Intrinsics.checkNotNullExpressionValue(e, "poll(...)");
                PacketUtil.sendPacket((Packet)e);
            }
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        long l = a ^ 0x594A39DFFD20L;
        long l2 = l ^ 0x4B2E175DB469L;
        if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket && this.getLagBackReset()) {
            this.disable(l2);
        }
        if (receivePacket.getPacket() instanceof EntityVelocityUpdateS2CPacket && ((EntityVelocityUpdateS2CPacket)receivePacket.getPacket()).getEntityId() == Globals.getPlayer().getId() && this.getVelocityReset()) {
            this.disable(l2);
        }
        if (receivePacket.getPacket() instanceof ExplosionS2CPacket && this.getVelocityReset()) {
            Optional<Vec3d> optional = ((ExplosionS2CPacket)receivePacket.getPacket()).comp_2884();
            Intrinsics.checkNotNullExpressionValue(optional, "playerKnockback(...)");
            Vec3d vec3d = OptionalsKt.getOrNull(optional);
            if (vec3d != null) {
                Vec3d vec3d2 = vec3d;
                if (!(vec3d2.x == 0.0 && vec3d2.y == 0.0 && vec3d2.z == 0.0)) {
                    INSTANCE.disable(l2);
                }
            }
        }
        if (receivePacket.getPacket() instanceof OpenScreenS2CPacket && this.getOpenScreenReset()) {
            this.disable(l2);
        }
    }

    public final void applyDefaults() {
        this.setLimit(60);
        this.setLimitAction(BlinkAttackActionMode.OFF);
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        long l;
        long l2 = l = a ^ 0x713561F09D3EL;
        long l3 = l2 ^ 0x3DFE78B8BFB4L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x63514F72D477L;
        if (Globals.getMc().player == null || isFlushing.get()) {
            return;
        }
        if (this.getOnlyInRange()) {
            PlayerEntity playerEntity = TargetUtil.Y((char)n, TargetUtil.INSTANCE, this.getRange(), AutoCrystalTargetSortMode.DISTANCE, false, true, 4, n2, (char)n3, null);
            if (playerEntity == null || !Aura.INSTANCE.getEnabled()) {
                this.flushPackets();
                startPos = null;
                return;
            }
            if (startPos == null) {
                Entity entity = Globals.getPlayer();
                startPos = this.getPos(entity);
            }
            Vec3d vec3d = this.getPos(playerEntity);
            Vec3d vec3d2 = startPos;
            Intrinsics.checkNotNull(vec3d2);
            double d = vec3d.distanceTo(vec3d2);
            Entity entity = Globals.getPlayer();
            double d2 = this.getPos(playerEntity).distanceTo(this.getPos(entity));
            if (d <= d2 || d < 1.0) {
                this.flushPackets();
                Entity entity2 = Globals.getPlayer();
                startPos = this.getPos(entity2);
                return;
            }
        }
        if (sendPacket.getPacket() instanceof PlayerMoveC2SPacket) {
            if (Y) {
                Y = false;
                this.flushPackets();
                return;
            }
        } else if (this.getDelayOnlyMove()) {
            return;
        }
        if (this.getIgnoreInventoryActions() && (sendPacket.getPacket() instanceof ClickSlotC2SPacket || sendPacket.getPacket() instanceof PlayerInteractItemC2SPacket || sendPacket.getPacket() instanceof PlayerActionC2SPacket && ((PlayerActionC2SPacket)sendPacket.getPacket()).getAction() == PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND || sendPacket.getPacket() instanceof UpdateSelectedSlotC2SPacket)) {
            return;
        }
        if ((sendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket || sendPacket.getPacket() instanceof HandSwingC2SPacket) && !ElytraTarget.INSTANCE.getEnabled()) {
            switch (Blink$WhenMappings.b[this.getAttackAction().ordinal()]) {
                case 1: {
                    this.disable(l4);
                    break;
                }
                case 2: {
                    Y = true;
                    break;
                }
            }
        }
        if (sendPacket.getPacket() instanceof CommonPongC2SPacket) {
            sentPackets.add(sendPacket.getPacket());
        }
        sendPacket.cancel();
        receivedPackets.add(sendPacket.getPacket());
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        long l = a ^ 0x2A8CFFAC8078L;
        long l2 = l ^ 0x38E8D12EC931L;
        if (!this.isInGame() || Globals.getPlayer().isDead()) {
            this.disable(l2);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l;
        long l2 = l = a ^ 0x5428FF382DA1L;
        int n = (int)((l2 ^ 0x5AF9921B8715L) << 48 >>> 48);
        long l3 = l2 ^ 0x464CD1BA64E8L;
        char c = (char)n;
        int n2 = this.getCancel().getKey();
        if (KeyboardUtil.isKeyPressed(c, n2) && startPos != null) {
            this.resetPosition();
            this.disable(l3);
            return;
        }
        if (receivedPackets.size() > this.getLimit()) {
            switch (Blink$WhenMappings.b[this.getLimitAction().ordinal()]) {
                case 1: {
                    this.disable(l3);
                    break;
                }
                case 2: {
                    this.flushPackets();
                    break;
                }
            }
        }
    }

    private final void flushPackets() {
        if (!this.isInGame()) {
            return;
        }
        isFlushing.set(true);
        while (!receivedPackets.isEmpty()) {
            Packet packet = (Packet)receivedPackets.poll();
            Intrinsics.checkNotNull(packet);
            PacketUtil.sendPacket(packet);
            if (!(packet instanceof PlayerMoveC2SPacket)) continue;
            startPos = new Vec3d(((PlayerMoveC2SPacket)packet).getX(Globals.getPlayer().getX()), ((PlayerMoveC2SPacket)packet).getY(Globals.getPlayer().getY()), ((PlayerMoveC2SPacket)packet).getZ(Globals.getPlayer().getZ()));
        }
        isFlushing.set(false);
        receivedPackets.clear();
    }

    private final void resetPosition() {
        receivedPackets.clear();
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Vec3d vec3d = startPos;
        Intrinsics.checkNotNull(vec3d);
        double d = vec3d.getX();
        Vec3d vec3d2 = startPos;
        Intrinsics.checkNotNull(vec3d2);
        double d2 = vec3d2.getY();
        Vec3d vec3d3 = startPos;
        Intrinsics.checkNotNull(vec3d3);
        clientPlayerEntity.setPos(d, d2, vec3d3.getZ());
        Globals.getPlayer().setVelocity(savedVelocity);
        Globals.getPlayer().setYaw(savedYaw);
        Globals.getPlayer().setSprinting(L);
        Globals.getPlayer().setSneaking(false);
        Globals.getMc().options.sneakKey.setPressed(false);
        if (savedInput != null) {
            ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
            Input input = savedInput;
            Intrinsics.checkNotNull(input);
            clientPlayerEntity2.input = input;
        }
        isFlushing.set(true);
        while (!sentPackets.isEmpty()) {
            Object e = sentPackets.poll();
            Intrinsics.checkNotNullExpressionValue(e, "poll(...)");
            PacketUtil.sendPacket((Packet)e);
        }
        isFlushing.set(false);
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block2: {
            long l = a ^ 0x23C3EA00C020L;
            long l2 = l ^ 0x1FDEF126AA7EL;
            if (!this.isInGame() || !this.getShowPosition()) break block2;
            Vec3d vec3d = startPos;
            if (vec3d != null) {
                Vec3d vec3d2 = vec3d;
                Entity entity = Globals.getPlayer();
                if (!Intrinsics.areEqual(startPos, INSTANCE.getPos(entity))) {
                    RenderState.Q(RenderState.INSTANCE, render3DEvent.getStack(), vec3d2, Globals.getPlayer(), 0.45f, false, null, l2);
                }
            }
        }
    }

    private static final boolean settingVisible() {
        return !INSTANCE.getDelayOnlyMove();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getOnlyInRange();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Blink.a = ZkmKeys.a(-5759693261412699404L, 2153976218910887908L, MethodHandles.lookup().lookupClass()).a(234038455752378L);
                        var20 = Blink.a ^ 3121195217834L;
                        Blink.g = new HashMap<K, V>(13);
                        Blink.zkmSetFlag("oV6YGb");
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
                        var18_3 = new String[46];
                        var16_4 = 0;
                        var15_5 = "\u0086\u0002 \u00bd\u0098;\u00d8\u0084WD\u0001\u00a1Z\u00e0t\u0013\u0080\f\u00cb\u0096\"\u001aP\u00ef\u00d8Yl\"P!\u0091\nK1!\u00c4\u00db\u00c4\u00c0\u0098 \u00e6\u00e8\u00eb\u00b5\u00f5\u0014\u00cd\u00e3\u00be\u00ca'v\u00b8c\u00e0J\u00a2\u00f9\u0095Z\u00bb2\u0082 \u00fee\u00927?_\u008a\u0012\u0010\r~\u00a6\u00bd\u0093\u008a!\u0089,AKY\u00f5=y\u00e2 \u00e2\\0\u00df\u00ee\u0091\u00b5\u00dcj\u0085\u0005\u00dc\u00b4\u008d;\u00d6\u0087\u00c0\u00072\u0097\u0087\\\u00d7\u00dd\u00ab\u00df\u0006JH\u00cc\u00a7\u0018O96\u0004\u000b\u00ae\u0002H(\u001c%\u0014\u008db\u0016\u00ea0/\u008ay\u00b8\u00b0t\u008f 0\u00e0\u0010\u00a1\u00e6{#'\u00ae\u0018\u00b2\u0016\u00c11W'\u0002\u0093p\u00a4\u001c@\u00d9\u00d1\u0005\u0002\u0006\u00f8\u00d8\u00a2\u0095\u00f70\u00a5\u00ac\u009bN\u0087\u00f7F_\u000b4-\u00b9\u009d\u00a7\u00e5\u00ea\u0093\\\u0012\u0087\u008b\u0083\u00ad:\u00bd5\u00e59A%\u0007\u00b5\u00bc\u00b9\u00d9\u00f6\u00d0!\u00e6G\u000f<c\u00f0\u0080\u00e0\u0017\u0016\u0010|{\u00db\u00bdM\u00ba\u00a3\u00cd\u0014\u0003U\u00ad\u00ed\u00b6\u00f8\u0018 m\u00d3R\u001d\u00fe\u00be \u009a?}\u00aeUW\u00b8\u0001\u00ef\u00fe\u0001I\u00d7\u001b\u00a5\u00c5\u0012\u0011\u00fc\u0097U\u00e0\u00a8\u00c0T\u0018\u0003\u001b\u00a1\u00b8{O\u00dc\u001f\u0081\u00ad\u00c2c\u00fa\u0089t\u000bw#\u0096\u0095\u00c7jd\u00fb @N\u001c{d}*V\u00b2\u009f\u00fd\u00f8$\u00af\u00ceEZ<64A\u00d6\u00a0\u0093\u00d8\u0083\u00cc\u008d\u0016\u00ef\u00c9C(Bhb\u00d4\u00b8m\u0081\u001b(i0F|\u008e\u00b84\u0000T\u00e3\u00a7\u00cc\u00ff\u001eN\u008c\u00f4\u00f4V\u00a2I\u00f8\u001f\u0016\u0086!\u0005\u0093\u00b5xP\u0010\u00e7 |\u000bC\u00a6j\u00b7\u00d24\u00e5\u00dd-\u00d4U\u009a\u0010F}\u00d4>\u00ba\u0000z\u00e88=\u008c\b\u00f5U\u00a4b \u0085\t\u001d\u00e9O\u00c8\u0081v\u00c2\u00b5\u0000\u0011w3J\u00b3\u0082\u00d3\u00f7\u0095\u00e2\u00a0'Q\u00d6\u00e7\u00fa\u00a7\u00a2\u0095\u00da\u001f8\u00e6\u008dx^\u00f4\u0003\u00a7\u00fe\u00fc}l\u00e0O\fP\u00cb\u00a2\u0011\u00c2\u00ee\u0080e\u00b6\u00aaC\u00f3\u001d\u0003\u00c7\u0081\u00d3\u001dr`;\u007fa9=\u00b4\u00b3+\r\u00d0y\u0007\u009bC9i\u00cb\u00e7\u00bf\u00f3\u00bd{ \u00cc\u00eb\u00b6\u00bd4\u008c6\u001eJ`\u00d6@w\u008c\u00c5F0\u00de\u00af\u00cb]\u0097eUP\u00ae\u00b6\u00fa\u008f\u00a8wu(\u00aa\u00fc\u00f2\u00dd1\n\u00d0\u00f7\u00e1\u00e5\u00af%\u009b\u0081\u0085\u00e3{\u001bX\u00c6\u00df\u00d1=\u00b5\u00bf\u00ebT\u00b2\u009b\u00ec\u00f0\u00da\u00e4\u008e\u00c9\u00fc\u009c\u008d\u00fd\u00cb\u0018\u0094\u0003\u0006\u009a\u00d4\u0015\u0081\u00c7\u00f66P\n\u009e\u00d1\u00bdKBw\u0088&\u00ba\u0084\u00cc\u00af \u00c6_+e6\u00e0\u00a5Z\u009d\u00c1\u00c3\u0013\u0090\u00a4\u00bf\u00f8\u00be\u001bY\u00da\u0081\u00ee~\u0005\u000f\u0007$]\u0091\u00ec\u00ef\u0089 \u00b5\u008f\u00f4\u00a8\u00a4\u0016h6\u0016k\u00df\u00c9\u00f7\u009b\u00f3\u00c9\u00ad\u00ef'\u00ec\u0096\u007f|\u00ed\u0006x_\u00bc\u0011`HC\u0018\u00fc\u00a2\u00f5\u0016:;?R\u0098\u00b9\u0002\u00c9#P\u00fa\u00af\u00b0\u00a1\"-\u009dI\u008bu\u0010q\u00b3t\u00b1a\u00eb\u0002\u00c3\u00b9\u00a0\u0095\u00c1\u00a8]\u00cbj \u00a5\u00c9\u0088\u00f8\u00a9\u00ce\u00eb5\u0003u\u00ff/\u00a2#\u00e7p\u001f\u0082\u00f3\u00e1\u00d1\u0094)\u00a5\u00c06\n\u00fd\u001b\u00c9\u00a1\u00b2\u0018Z\nc\u0016\u00ce\u00ad\u00df'\u001b\u00951\u00ef0^\u00d3>U\u00a7\u00b4\u0093b\u008eX,\u0010&\u00c6h\u0010k\u00be\u00d13\u00cf\u001a\u00cc}\u00d5\u0011\u00e8\u00f1\u0018\u0016\u001e\t\u00f5\u001e\u00a6%<k9&\u00b8\u00cf\u00ba\u0090\u00844\u00be\u00e6\u00fb1\u00baM\u00bd\u0010y\u0015?5~e\u00d7\u0005\u00ae\u00ae\u00eb\u00f9\u0092\u00ebC\u001d\u0018\u00af\u00e5\u000eI\u00fe\u00b1\u00cb\u00e5&\u00e6\u00a6\u00f1\u00d6\u00b9\u00f3\u00da\u001e`\u0093\u00c4\u00f4\u0096\u00a6\u0080(\u00be\u00fa\u00dd\u00dc8s\u00b2N\u001b=\u0081k*\u00c5\u008fc\u00ccD\u00ef\u0001\u0094\u00b4\u00bc=\u00d7\u0017\u00ae\u00fd'q\u009c!\u009eS\u00b9\u007f\u00e9\u001b\u00b5\u008b\u00a0+\u0003:\u0092\u00d2\u00b0>\u00de\u00a5\u0005Yb\u00eb\u0097_I\u00fc)>\u00d3\u0087\u00b39Pp:O\u00ae\u00e5\tZ\u00c6\u00ae\u000b~H\u0014\u00a3:4\u0092\u0092\u00a9\u001e\u0005FL\u0081L{\u0012\u00122\u00b9\u0089$\u00cfX\u00a4a\u00d2Ld\u00c9bNA\u00ad*\u00fc?}\u00daH\u00d7;{\u009b\u0098r\u00e2\u0018\u00fb<Z\u00ab\u008b\u0096!\u00b3P\u00ae\u00d7o\u00d7\u00ca\u00fa\u00bd\u0090\u00deN\u00ba\u00b8\n\u000fJ9\u0019\u0094\u00ea\u00c9X\u00d4\u0016\u00c2\u009b\u0099\u00f1}\u0092\u0093@tX\u001f\u001e\u0089\u00f1\u008cs\u0007\u00cf\u0001\u007f\u00a6d]\u0012\u00b8K\u00a2(\u0096\u0091\u00fb\u00ec\u0003l\u00cb&j\u0016v\u000f\u00d9\u00af\u0095\u00c4\u000b\u00a0(S\u0013\u0089n\u00a7\u00deu{\u00fd\u0085\u0096\u00e9\u00b4\u0013\\\u00a8\u00f4*|\u00f6{\u00d8\u001e\u00f2\u00e7\u00db\u00a1\u00a8O\f\u00fb\u00e6\u00bd\u0099_\u00ac\r\u00fd\u00f0\u00f4 \u008a\u0098\u00d7\u00d2\u00dc\u00c4yV\u0000\u00ed\u00db{\u0096\u00f4\u0081\u00ca\u00beL\u00ceX\u00fb)Z\u0004\u00b6\u00bb\u001c\u0080\u00b0\u00a5)\u00dc\u0010\u00fb>\u00e5z2q\\\u0088\u0012\\\u0082w~~\u00ed\u00d4\u0010{\u0013\u00a3\to<B\u00e4\u0019\b\u00f1\u00bc\u009dC\u0005\u00fd\u0018\u000f\u00bd\u00e0\u0086J=\u009b\"\u008e\u00b5\u00ff\u0002\u00f7\u00d7v8\u00b5\u00e3\u00fbP\u008b:$\u00bb0JQ\u0092q\u00de\u001f\u008e\u00db\u00e0_\u00b8<\u0095\u001b\u001f\u009f\u00abwfA\u0080\u00b4r\u00b5\u0013\u00d6\u00de#\u00d0\u00af\u0014E\u00c4\u00a5\u00b2\u00e4Q\u00fekh\u00aasR\u001a\u00ef\u00f8\u00a4<\u0018X\u00a9Ro\u0092\u009d\u009a\n\u00d9<\u00bb\u00a9B\u001f\u00fd\u009e\u009f)\u001d\u00b9\u00cfF\u00f7\u00d0 r\u00b8\u0004(y!\u00d1\u00a1\u0013{n!;\u00df<\u0084\u0094\u00ac\u00eaK>\u008cZ\u00b4\u00d9\u008d\u00c4\u00d8\u00ca\u00b0\u00b9\u0000(k\u001fV\u00c8\u00ca\u0014k\u0006\u00e9\u008f\u0096\u008d\u00a0\u00c9\u00ceyq\u00f0\u00a4\u0013\u0086\u00c6\u00bd\u00e1\u009b\u0016\u00fb\u00a5\r\u00c2\u00b8l%Y\u0096\u0019\u00d3\u00d8\u00be\u00b9 u\u00b6\u00a2\u00fa\u00d5\u00a8\u001aH\u00c1U\u00f4\u00b1s\u009b\f\u00f6\u0085\u008aC+Wa6:l\u00baG3\u00c4\u001a\u009f\u0007(*\u00c7\u00c8\u0089I\u00a2\u00f2|\u00f7\u00c4L\u00b9\u00cd\u0001\u00fb\u0097u\u00b2\u00f7o\u00c8\u00f9\u00cca\u00b1\u0014SJ\u00f7P\u00fc*\u00a2\u00d2\u00065\\\u00ad#/@\u000b~\u0081\u0096<Z.R7;\u0096\u00a2\u0001\u00df\u00fbz\u00adxT#\u00f4>O\u00c1\u0011E\u00cbv\u00cfi\u00a1P\u001fL&Hqr\u001e\u00ee\u00b5 ~\u009b\u00ab\rP`\u000e\u00d8s\u009d\u008e\u00a2\u00ac{\u00c9\u0003\u00e1\u00eb\u0003HJ\u009d0k\u00a0\u0010\u00f1\u00d7wtb\u00f6/o\")i\u0092I/J\u0091\u00bc\u009e\u00aah\u0005\u00e9J\u00a4\u00cc@\u00fd\u0086\u008aY#\u0090\u00d3\u00bd\u00d0\u00abck\u00a9/\u00f9~\u008d\u0085\u0091";
                        var17_6 = 1523;
                        var14_7 = 40;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Blink.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "&0\u00b2\u00fcw\u0081\u001b\u00ffv\u00b6]\u00e6\u00de%\u00d0\u00b2\u00cb\u00bfu\u00f1\u0015\u008e\u001b\u00979P$W\u00f3\u00cd\u00a8b \u0097\u0098\u00f2\u0093\n\u00eax\u0086\u00e6\u00a2o\u0003A\u00fdV?\u0004\u00cb\u0018\u0092@e\u009bl\u00e6\u0013Q{\u00ce\u00e2\u00cd=";
                            var17_6 = 65;
                            var14_7 = 32;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Blink.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                Blink.c = var18_3;
                Blink.d = new String[46];
                Blink.l = new HashMap<K, V>(13);
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
                var6_12 = new long[21];
                var3_13 = 0;
                var4_14 = "\u001d\u00ff\u00da\u00f6\u00e8\u00e9-\u00e4\u00c9\u0091\u00b4\u0090W\u0013\u00b4J\u00a3~Omg\u00e6k\u00af\u0007\u0092\u00eaX\u00b0#\u00f8|%\u00fd0\u00fa\"+c\u009f\u0083\f\u001f\u00e5\u00bcjV\u00bcSfr\u00d3\u00fd\u00dc\u0012\f\n\u00a2\u00f9#\u00c3lY\u00f3\u00d2\u00eb\u00ce\u0000\u001bK*J`{\u00f0\u008b+L#taY\u0012P\u00e1\u00b7\u008d\u00c7H/\u00d2G\u00ff\u0001\u00df@\u00b5\u0089\u00d3\u0098d\u00f2r\u00f0\u00caC\u00bd3.\u00cb\u00d2^\u00b4\u00b9v\u00cc\u0012&\u00c9\u00fc\u00cc\u00fb\u00e6f\u0010\u008dB\u00bf\u00a3\"\u00d8-7\u0002F\u00f6\u0082\u0007\u00f9\u008a\u0010-jK\u00eb\u00df\u00a6\u00bd@?\u00c7\u00d3";
                var5_15 = 152;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00d8\u00ea\u009dv\u00a4\u00a9\u00fa/\u00f4p\u00c5g\u00f0\u00b4\u00f3\u0089";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        Blink.h = var6_12;
        Blink.i = new Integer[21];
        var42_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blink.class, "limit", "getLimit()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blink.class, "attackAction", "getAttackAction()Lsu/catlean/st;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blink.class, "limitAction", "getLimitAction()Lsu/catlean/client/features/modules/movement/rage/blink/Blink$LimitAction;", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "cancel", "getCancel()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "showPosition", "getShowPosition()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "delayOnlyMove", "getDelayOnlyMove()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "ignoreInventoryActions", "getIgnoreInventoryActions()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "onlyInRange", "getOnlyInRange()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "range", "getRange()F", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "resetOn", "getResetOn()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "velocityReset", "getVelocityReset()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "lagBackReset", "getLagBackReset()Z", 0)), Reflection.property1(new PropertyReference1Impl(Blink.class, "openScreenReset", "getOpenScreenReset()Z", 0))};
        Blink.delegatedProperties = var42_20;
        Blink.INSTANCE = new Blink();
        Blink.limit$delegate = SettingFactory.intSetting$default(Blink.INSTANCE, "limit", 15, new IntRange(1, 600), null, null, 24, null);
        Blink.attackAction$delegate = SettingFactory.modeSetting$default((SettingContainer)Blink.INSTANCE, "attack-action", (Enum)BlinkAttackActionMode.NONE, null, null, 12, null);
        Blink.limitAction$delegate = SettingFactory.modeSetting$default((SettingContainer)Blink.INSTANCE, "limit-action", (Enum)BlinkAttackActionMode.NONE, null, null, 12, null);
        Blink.cancel$delegate = SettingFactory.bindSetting$default(Blink.INSTANCE, "cancel-button", new Bind(340, false, false, 6, null), null, null, 12, null);
        Blink.showPosition$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "show-position", true, null, null, 12, null);
        Blink.delayOnlyMove$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "delay-only-move", false, null, null, 12, null);
        Blink.ignoreInventoryActions$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "ignore-inventory-actions", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Blink.onlyInRange$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "only-in-range", false, null, null, 12, null);
        Blink.range$delegate = SettingFactory.floatSetting$default(Blink.INSTANCE, "range", 5.0f, RangesKt.rangeTo(2.0f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        Blink.resetOn$delegate = SettingFactory.settingGroup$default(Blink.INSTANCE, "reset-on", AuraTargetsMode.BOOLS, null, 4, null);
        Blink.velocityReset$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "velocity", true, Blink.INSTANCE.getResetOn(), null, 8, null);
        Blink.lagBackReset$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "lag-back", true, Blink.INSTANCE.getResetOn(), null, 8, null);
        Blink.openScreenReset$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blink.INSTANCE, "open-screen", true, Blink.INSTANCE.getResetOn(), null, 8, null);
        v15 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v15, "ZERO");
        Blink.savedVelocity = v15;
        Blink.receivedPackets = new LinkedList<E>();
        Blink.sentPackets = new LinkedList<E>();
        Blink.isFlushing = new AtomicBoolean();
    }

    public static void zkmSetFlag(String string) {
        f = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return f;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x76B9;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/us", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            Blink.d[n2] = Blink.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Blink.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/us" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7CD1;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])Blink.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    Blink.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/us", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Blink.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Blink.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/us" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Blink.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Blink.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

