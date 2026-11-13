/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage.elytra;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVectorEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.module.rage.ElytraPlusStopMoveMode;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.util.ElytraListener;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.util.TickTimer;
import su.catlean.util.TimerManager;
import su.catlean.util.rotation.MoveFix;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class SilentFireWorkElytra
implements ElytraListener {
    @NotNull
    public static final SilentFireWorkElytra INSTANCE;
    private static float savedYaw;
    private static float savedPitch;
    @NotNull
    private static final TickTimer fireworkTimer;
    @NotNull
    private static final TickTimer swapTimer;
    @NotNull
    private static HashMap q;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private SilentFireWorkElytra() {
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        Intrinsics.checkNotNullParameter(preSyncEvent, "e");
    }

    @Override
    public void onPreElytra(@NotNull PreElytraEvent preElytraEvent, long l) {
        long l2 = l ^ 0x3302801E4F39L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(preElytraEvent, "e");
        savedYaw = Globals.getPlayer().getYaw();
        savedPitch = Globals.getPlayer().getPitch();
        Globals.getPlayer().setYaw(this.getMoveYaw((char)n, n2, (char)n3));
        if (!ElytraPlus.INSTANCE.getPitchControl()) {
            Globals.getPlayer().setPitch(0.0f);
        }
        Globals.getPlayer().setPitch(this.getTargetPitch());
    }

    @Override
    public void onAfterElytra(@NotNull AfterElytraEvent afterElytraEvent) {
        Intrinsics.checkNotNullParameter(afterElytraEvent, "e");
        Globals.getPlayer().setYaw(savedYaw);
        Globals.getPlayer().setPitch(savedPitch);
    }

    @Override
    public void onMove(@NotNull MoveEvent moveEvent, long l) {
        Intrinsics.checkNotNullParameter(moveEvent, "e");
    }

    @Override
    public void onAfterSendPacket(long l, @NotNull AfterSendPacket afterSendPacket) {
        Intrinsics.checkNotNullParameter(afterSendPacket, "event");
    }

    @Override
    public void onReceivePacket(@NotNull ReceivePacket receivePacket, long l, char c) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
    }

    @Override
    public void onPlayerUpdate(int n, @NotNull PlayerUpdateEvent playerUpdateEvent, char c, char c2) {
        long l;
        long l2 = l = (long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48;
        long l3 = l2 ^ 0x10E9293ACD1EL;
        long l4 = l2 ^ 0x3F408CCB534CL;
        int n2 = (int)(l4 >>> 48);
        int n3 = (int)(l4 << 16 >>> 32);
        int n4 = (int)(l4 << 48 >>> 48);
        long l5 = l2 ^ 0x1DE88FFFA740L;
        int n5 = (int)(l5 >>> 48);
        long l6 = l5 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        if (!this.isMoving()) {
            if (ElytraPlus.INSTANCE.getStopMove() == ElytraPlusStopMoveMode.BLOCK) {
                playerUpdateEvent.cancel();
                return;
            }
            if (ElytraPlus.INSTANCE.getStopMove() == ElytraPlusStopMoveMode.TIMER) {
                TimerManager.INSTANCE.x(0.05f);
            }
        }
        int n6 = InventoryUtil.INSTANCE.findElytra();
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n5, l6);
        if (n6 < 0 || !slotResult.isFound() || Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isInLava()) {
            return;
        }
        if (ElytraPlus.INSTANCE.Fu() && Globals.getPlayer().isUsingItem() && (Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.FOOD) || Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.POTION_CONTENTS))) {
            return;
        }
        if (Globals.getPlayer().isOnGround()) {
            if (!Globals.getMc().options.jumpKey.isPressed()) {
                Globals.getMc().options.jumpKey.setPressed(true);
                Scheduler.schedule$default(Scheduler.INSTANCE, 3, (byte)0, false, SilentFireWorkElytra::releaseJump, 6, null);
            }
            return;
        }
        if (!Globals.getPlayer().isGliding() && swapTimer.hasPassedAndReset(ElytraPlus.INSTANCE.getSwapDelay())) {
            if (n6 >= 36) {
                GuiMove.INSTANCE.setMacroActive(true);
                int n7 = n6 - 36;
                InteractionUtil.clickSlot(6, n7, SlotActionType.SWAP, true);
                ElytraSwap.INSTANCE.K(true);
                Globals.getPlayer().startGliding();
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                if (fireworkTimer.hasPassedAndReset(ElytraPlus.INSTANCE.getFireworkDelay())) {
                    ElytraTarget.INSTANCE.findFirework(ElytraPlus.INSTANCE.getOffHandFirework(), l3, ElytraPlus.INSTANCE.getAlternativeFireworkSwap());
                }
                InteractionUtil.clickSlot(6, n7, SlotActionType.SWAP, true);
                GuiMove.INSTANCE.setMacroActive(false);
            } else {
                GuiMove.INSTANCE.setMacroActive(true);
                InteractionUtil.clickSlot$default(n6, 0, null, false, 14, null);
                Object var54_16 = null;
                int n8 = 14;
                boolean bl = false;
                SlotActionType slotActionType = null;
                int n9 = 0;
                InteractionUtil.clickSlot$default(6, n9, slotActionType, bl, n8, var54_16);
                if (!ElytraPlus.INSTANCE.getMinimizeClicks()) {
                    InteractionUtil.clickSlot$default(n6, 0, null, true, 6, null);
                }
                ElytraSwap.INSTANCE.K(true);
                Globals.getPlayer().startGliding();
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                if (fireworkTimer.hasPassedAndReset(ElytraPlus.INSTANCE.getFireworkDelay())) {
                    ElytraTarget.INSTANCE.findFirework(ElytraPlus.INSTANCE.getOffHandFirework(), l3, ElytraPlus.INSTANCE.getAlternativeFireworkSwap());
                }
                if (!ElytraPlus.INSTANCE.getMinimizeClicks()) {
                    InteractionUtil.clickSlot$default(n6, 0, null, false, 14, null);
                }
                InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                InteractionUtil.clickSlot$default(n6, 0, null, true, 6, null);
                GuiMove.INSTANCE.setMacroActive(false);
            }
        }
        RotationManager.INSTANCE.rotate(new Rotation(this.getMoveYaw((char)n2, n3, (char)n4), this.getTargetPitch(), false, MoveFix.FORCE, 4, null), 0);
    }

    @Override
    public void onEnable(char c, int n, short s) {
    }

    @Override
    public void onDisable() {
    }

    private final boolean isMoving() {
        return Globals.getMc().options.forwardKey.isPressed() || Globals.getMc().options.leftKey.isPressed() || Globals.getMc().options.rightKey.isPressed() || Globals.getMc().options.backKey.isPressed() || Globals.getMc().options.jumpKey.isPressed() || Globals.getMc().options.sneakKey.isPressed();
    }

    private final float getMoveYaw(char c, int n, char c2) {
        long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)c2 << 48 >>> 48) ^ a;
        int n2 = (int)((l ^ 0x3FD8F5A7E2AFL) >>> 32);
        return MovementUtil.INSTANCE.b(n2);
    }

    private final float getTargetPitch() {
        return Globals.getMc().options.jumpKey.isPressed() ? -90.0f : (Globals.getMc().options.sneakKey.isPressed() ? 90.0f : (!ElytraPlus.INSTANCE.getPitchControl() ? 0.0f : Globals.getPlayer().getPitch()));
    }

    @Override
    public void onFireworkVelocity(@NotNull FireWorkVelocityEvent fireWorkVelocityEvent) {
        float f;
        Vec3d vec3d;
        Intrinsics.checkNotNullParameter(fireWorkVelocityEvent, "e");
        fireWorkVelocityEvent.cancel();
        Vec3d vec3d2 = Globals.getPlayer().getRotationVector();
        Intrinsics.checkNotNullExpressionValue(vec3d2, "getLookAngle(...)");
        if (FireWorkVectorEvent.INSTANCE.call(vec3d2)) {
            vec3d = FireWorkVectorEvent.INSTANCE.getVector();
        } else {
            Vec3d vec3d3 = Globals.getPlayer().getRotationVector();
            vec3d = vec3d3;
            Intrinsics.checkNotNullExpressionValue(vec3d3, "getLookAngle(...)");
        }
        Vec3d vec3d4 = vec3d;
        if (ElytraPlus.INSTANCE.getCustomSpeed()) {
            this.buildPitchMap();
        }
        float f2 = Math.abs((float)Math.toDegrees(-Math.atan2(vec3d4.y, Math.hypot(vec3d4.x, vec3d4.z))));
        if (Blink.INSTANCE.getEnabled() || !ElytraPlus.INSTANCE.getBoost()) {
            f = 0.1f;
        } else {
            Object v4;
            Iterable iterable = ((Map)(ElytraPlus.INSTANCE.getCustomSpeed() ? q : ElytraTarget.INSTANCE.Pd())).entrySet();
            Iterator iterator2 = iterable.iterator();
            if (!iterator2.hasNext()) {
                v4 = null;
            } else {
                Object t = iterator2.next();
                if (!iterator2.hasNext()) {
                    v4 = t;
                } else {
                    Map.Entry entry = (Map.Entry)t;
                    float f3 = Math.abs(f2 - ((Number)entry.getKey()).floatValue());
                    do {
                        Object t2;
                        Map.Entry entry2;
                        float f4;
                        if (Float.compare(f3, f4 = Math.abs(f2 - ((Number)(entry2 = (Map.Entry)(t2 = iterator2.next())).getKey()).floatValue())) <= 0) continue;
                        t = t2;
                        f3 = f4;
                    } while (iterator2.hasNext());
                    v4 = t;
                }
            }
            Map.Entry entry = v4;
            f = entry != null ? ((Number)entry.getValue()).floatValue() : 0.1f;
        }
        float f5 = f;
        fireWorkVelocityEvent.setVelocity(Globals.getPlayer().getVelocity().add(vec3d4.x * (double)f5 + (vec3d4.x * 1.5 - Globals.getPlayer().getVelocity().x) * 0.5, vec3d4.y * (double)f5 + (vec3d4.y * 1.5 - Globals.getPlayer().getVelocity().y) * 0.5, vec3d4.z * (double)f5 + (vec3d4.z * 1.5 - Globals.getPlayer().getVelocity().z) * 0.5));
    }

    @Override
    public void onSetPose(@NotNull SetPoseEvent setPoseEvent) {
        Intrinsics.checkNotNullParameter(setPoseEvent, "e");
        setPoseEvent.cancel();
        setPoseEvent.setPose(EntityPose.STANDING);
    }

    @Override
    public void handlePacket(long l, @NotNull ReceivePacket receivePacket) {
        block1: {
            Intrinsics.checkNotNullParameter(receivePacket, "e");
            if (!(receivePacket.getPacket() instanceof PlaySoundS2CPacket)) break block1;
            RegistryEntry<SoundEvent> registryEntry = ((PlaySoundS2CPacket)receivePacket.getPacket()).getSound();
            Intrinsics.checkNotNullExpressionValue(registryEntry, "getSound(...)");
            RegistryEntry<SoundEvent> registryEntry2 = registryEntry;
            if (Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GOLD) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_IRON) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)) {
                receivePacket.cancel();
            }
        }
    }

    private final void buildPitchMap() {
        Pair[] pairArray = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(ElytraPlus.INSTANCE.L())), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(ElytraPlus.INSTANCE.q())), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees10())), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(ElytraPlus.INSTANCE.j())), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees20())), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(ElytraPlus.INSTANCE.kU())), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(ElytraPlus.INSTANCE.kf())), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees35())), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(ElytraPlus.INSTANCE.kz())), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees45())), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees50())), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees55())), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees60())), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees65())), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees70())), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees75())), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees80())), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees85())), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(ElytraPlus.INSTANCE.getDegrees90()))};
        q = MapsKt.hashMapOf(pairArray);
    }

    private static final void releaseJump() {
        block0: {
            long l = a ^ 0x6A0A9803FE42L;
            int n = (int)((l ^ 0x533CBB548F46L) << 48 >>> 48);
            if (KeyboardUtil.isKeyPressed((char)n, 32)) break block0;
            Globals.getMc().options.jumpKey.setPressed(false);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SilentFireWorkElytra.a = ZkmKeys.a(-6844058597710634435L, 5516195895963292931L, MethodHandles.lookup().lookupClass()).a(88685240984708L);
                        var20 = SilentFireWorkElytra.a ^ 93866500815563L;
                        SilentFireWorkElytra.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "@\u0095\u00d0\u00d0\b\u0016\u00f1\u0000\u001c\u00ab\u008e\u0017\u00e5\u00a3\u00d1\u00d3\u00ad\u00f4\u0010m\u00d1\u0013H\u00e3\u00e9\u00d8\u008cz\u00dd#b\u00a0\u00cc\u00ae\u00ae@I#\u00fc\u00b8 \u00ee,\u00cfF?\u00f3z\u001f\u0019Dfy@ \u00f0eEe\u0091\u001f\u00f6]F\u0089Q\u00a1\u00a8\u00bc2\u009d\u00d13\u0010\u00dd\u00e4\u00ca\u00a8O>\u00cc\u00c5,O?R\u00e2\u0016,u";
                        var17_6 = 90;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = SilentFireWorkElytra.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0014\u0099\u00d4\u00f6I\u00991r\u0080\u00ffB\u00832\u00a1t\u00ba\u0014Ar(\u0087\u0001<\u00ff\u0089xj\u00f0\u008ar\u00826H\u00f7\u00f0\u00c18V3\u00c6 \u00f2\u0084\u00df\u00e5N;\u00b4T\u00d7k\u00e8\u0002\u00fd\u009f3\u009b\u00fa{[j\u00d3'\u00bd\u008dm@Y\u00bd\u00bb&\u0003P";
                            var17_6 = 73;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = SilentFireWorkElytra.zkmDecodeUtf8(var19_9).intern();
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
                SilentFireWorkElytra.b = var18_3;
                SilentFireWorkElytra.c = new String[5];
                SilentFireWorkElytra.h = new HashMap<K, V>(13);
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
                var6_12 = new long[31];
                var3_13 = 0;
                var4_14 = "\t4,\u00ec\u00c8\u009e\u001b,\u00dek(\u008dw\u00eff\u009d\u00d0u\u00a1\u0010\u0018\u00df.\u00db0\u0000\u0017N\u00f8xiZ]i)z\u00e7\u0090\u0003\u00a0A\u00bc\u00d2\u00b4\u0085\u00c3\u00bdDr:@\u00acp\u00cd\u0018\u00ad\t\u00d5\u00ae\u00d0\u00ce\u0005\u00db\u0012\u00a8\u00118\u008a\u00d3)CgS\u008bd?\u0094j\u00f6\u00c2\u00b4\u00a2{|\u00c4\u009f`\u00c8\u00e3\u00ac\u00e9g\u00d8\u0000\u007f2\u0088\u00c4\u001c\u00c1Z'{Z\u0005\u0006]\u001ew\u0017\u00b2\u009e\u00a2\u00fe\u00db\u0099\u00f3i\u00d2\u00ccn\u00c8w(\u00b9\u00c0U\u001f|\u00ca\r\u00dd\u00c5\u0011[q\u000b\u00db\u008br\u0086E\u00f5\u00a7\u00f2\u00d9\u00b5\u00b6\u00d4\u009c&\u00d1?\u00bfw\u0002\n\u000fGNHp\u0001\u008b\u00e3\u00e8\u00e6mP\u00dd\u0088\u0007}\u00b6':\u00ef\u00a4<\u00b4yO\u00ee>\u0082\u0090\u000e\u00e0\u000ef\u00f7d\u008d\u000b\u001ep\u009fK\u00c6\u00ebrYtsZs\u00aaPr\u00a8\u00d8\u00e7\u0003L\u000e\u00c0R8&\u0003e\u00f5\u0088[]c\u0082f\u00b9\u0096OG";
                var5_15 = 232;
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
                    var4_14 = "\u00a6Y?\u008d\u0004\u0016\u00133\u00cf\u00ae\u001e\u00c2.\u008aO ";
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
        SilentFireWorkElytra.e = var6_12;
        SilentFireWorkElytra.g = new Integer[31];
        SilentFireWorkElytra.INSTANCE = new SilentFireWorkElytra();
        SilentFireWorkElytra.fireworkTimer = new TickTimer();
        864378471891771724L ^ var20;
        SilentFireWorkElytra.swapTimer = new TickTimer();
        var24_20 = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(0.155f)), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(0.19f)), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(0.27f)), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(0.28f)), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(0.2f)), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(0.18f)), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(0.13f))};
        SilentFireWorkElytra.q = MapsKt.hashMapOf(var24_20);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x13A7;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/f1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            SilentFireWorkElytra.c[n2] = SilentFireWorkElytra.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SilentFireWorkElytra.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/f1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x51A3;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/f1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SilentFireWorkElytra.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SilentFireWorkElytra.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/f1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SilentFireWorkElytra.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SilentFireWorkElytra.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

