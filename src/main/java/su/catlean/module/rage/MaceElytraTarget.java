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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVectorEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.Criticals;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.DefaultRotationHandler;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MaceElytraTarget
extends Module {
    @NotNull
    public static final MaceElytraTarget INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting height$delegate;
    @NotNull
    private static final IntSetting swapDelay$delegate;
    @NotNull
    private static final IntSetting fireworkDelay$delegate;
    @NotNull
    private static final BooleanSetting offHandFirework$delegate;
    @NotNull
    private static final BooleanSetting alternativeFireworkSwap$delegate;
    @NotNull
    private static final BooleanSetting pauseWhileEating$delegate;
    @NotNull
    private static final BooleanSetting minimizeClicks$delegate;
    @NotNull
    private static final BooleanSetting peekAssist$delegate;
    @NotNull
    private static final BooleanSetting windBurstSync;
    @NotNull
    private static final BooleanSetting boost$delegate;
    @NotNull
    private static final BooleanSetting customSpeed$delegate;
    @NotNull
    private static final FloatSetting degrees0$delegate;
    @NotNull
    private static final FloatSetting degrees5$delegate;
    @NotNull
    private static final FloatSetting degrees10$delegate;
    @NotNull
    private static final FloatSetting degrees15$delegate;
    @NotNull
    private static final FloatSetting degrees20$delegate;
    @NotNull
    private static final FloatSetting degrees25$delegate;
    @NotNull
    private static final FloatSetting degrees30$delegate;
    @NotNull
    private static final FloatSetting degrees35$delegate;
    @NotNull
    private static final FloatSetting degrees40$delegate;
    @NotNull
    private static final FloatSetting degrees45$delegate;
    @NotNull
    private static final FloatSetting degrees50$delegate;
    @NotNull
    private static final FloatSetting degrees55$delegate;
    @NotNull
    private static final FloatSetting degrees60$delegate;
    @NotNull
    private static final FloatSetting degrees65$delegate;
    @NotNull
    private static final FloatSetting degrees70$delegate;
    @NotNull
    private static final FloatSetting degrees75$delegate;
    @NotNull
    private static final FloatSetting degrees80$delegate;
    @NotNull
    private static final FloatSetting degrees85$delegate;
    @NotNull
    private static final FloatSetting degrees90$delegate;
    @NotNull
    private static final TickTimer fireworkTimer;
    @NotNull
    private static final TickTimer f;
    @NotNull
    private static final TickTimer K;
    @NotNull
    private static HashMap pitchMap;
    private static boolean P;
    private static int w;
    private static final long B;
    private static final String[] E;
    private static final String[] O;
    private static final Map U;
    private static final long[] ab;
    private static final Integer[] fb;
    private static final Map gb;

    private MaceElytraTarget() {
        super("mace-elytra-target", Categories.getRage(), null, null);
    }

    private final int getHeight() {
        return ((Number)height$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final int getSwapDelay() {
        return ((Number)swapDelay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final int getFireworkDelay() {
        return ((Number)fireworkDelay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getOffHandFirework() {
        return (Boolean)offHandFirework$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getAlternativeFireworkSwap() {
        return (Boolean)alternativeFireworkSwap$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getPauseWhileEating() {
        return (Boolean)pauseWhileEating$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getMinimizeClicks() {
        return (Boolean)minimizeClicks$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getPeekAssist() {
        return (Boolean)peekAssist$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean p() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ B;
        return (Boolean)windBurstSync.getValue(this, delegatedProperties[MaceElytraTarget.zkmBootstrap2("x", (int)9603, (long)(0x4971128811EBE3F5L ^ l2))]);
    }

    private final boolean getBoost() {
        return (Boolean)boost$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getCustomSpeed() {
        return (Boolean)customSpeed$delegate.getValue(this, delegatedProperties[10]);
    }

    private final float getDegrees0() {
        return ((Number)degrees0$delegate.getValue(this, delegatedProperties[11])).floatValue();
    }

    private final float getDegrees5() {
        return ((Number)degrees5$delegate.getValue(this, delegatedProperties[12])).floatValue();
    }

    private final float getDegrees10() {
        return ((Number)degrees10$delegate.getValue(this, delegatedProperties[13])).floatValue();
    }

    private final float getDegrees15() {
        return ((Number)degrees15$delegate.getValue(this, delegatedProperties[14])).floatValue();
    }

    private final float getDegrees20() {
        return ((Number)degrees20$delegate.getValue(this, delegatedProperties[15])).floatValue();
    }

    private final float getDegrees25() {
        return ((Number)degrees25$delegate.getValue(this, delegatedProperties[16])).floatValue();
    }

    private final float getDegrees30() {
        return ((Number)degrees30$delegate.getValue(this, delegatedProperties[17])).floatValue();
    }

    private final float getDegrees35() {
        return ((Number)degrees35$delegate.getValue(this, delegatedProperties[18])).floatValue();
    }

    private final float getDegrees40() {
        return ((Number)degrees40$delegate.getValue(this, delegatedProperties[19])).floatValue();
    }

    private final float getDegrees45() {
        return ((Number)degrees45$delegate.getValue(this, delegatedProperties[20])).floatValue();
    }

    private final float getDegrees50() {
        return ((Number)degrees50$delegate.getValue(this, delegatedProperties[21])).floatValue();
    }

    private final float getDegrees55() {
        return ((Number)degrees55$delegate.getValue(this, delegatedProperties[22])).floatValue();
    }

    private final float getDegrees60() {
        return ((Number)degrees60$delegate.getValue(this, delegatedProperties[23])).floatValue();
    }

    private final float getDegrees65() {
        return ((Number)degrees65$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    private final float getDegrees70() {
        return ((Number)degrees70$delegate.getValue(this, delegatedProperties[25])).floatValue();
    }

    private final float getDegrees75() {
        return ((Number)degrees75$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    private final float getDegrees80() {
        return ((Number)degrees80$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    private final float getDegrees85() {
        return ((Number)degrees85$delegate.getValue(this, delegatedProperties[28])).floatValue();
    }

    private final float getDegrees90() {
        return ((Number)degrees90$delegate.getValue(this, delegatedProperties[29])).floatValue();
    }

    /*
     * Unable to fully structure code
     */
    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        v0 = var2_2 = MaceElytraTarget.B ^ 74189792899556L;
        var15_3 = v0 ^ 1013559520011L;
        var21_4 = v0 ^ 19558919922299L;
        v1 = v0 ^ 89422230681561L;
        var23_5 = (int)(v1 >>> 48);
        var24_6 = v1 << 16 >>> 16;
        if (MaceElytraTarget.w > 0) {
            var34_7 = MaceElytraTarget.w;
            MaceElytraTarget.w = var34_7 + -1;
            return;
        }
        var34_8 = InventoryUtil.INSTANCE.findElytra();
        var36_9 = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        var35_11 = InventoryUtil.INSTANCE.findInInventory(var36_9, (char)var23_5, var24_6);
        v2 = var36_10 = Aura.INSTANCE.getEnabled() != false && Aura.INSTANCE.getTarget() != null;
        if (!var36_10) ** GOTO lbl-1000
        v3 = Globals.getPlayer().getEyePos();
        v4 = Aura.INSTANCE.getTarget();
        Intrinsics.checkNotNull(v4);
        var32_12 = v4;
        if (v3.distanceTo(this.getPos(var32_12)) < 4.0 && Globals.getPlayer().getVelocity().y < -1.0 && DefaultRotationHandler.INSTANCE.y() <= 0) {
            v5 = true;
        } else lbl-1000:
        // 2 sources

        {
            v5 = var37_13 = false;
        }
        if (var34_8 < 0 || !var35_11.isFound() || Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isInLava()) {
            return;
        }
        if (this.getPauseWhileEating() && Globals.getPlayer().isUsingItem() && (Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.FOOD) || Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.POTION_CONTENTS))) {
            return;
        }
        if (Globals.getPlayer().isOnGround()) {
            if (!Globals.getMc().options.jumpKey.isPressed()) {
                Globals.getMc().options.jumpKey.setPressed(true);
                Scheduler.schedule$default(Scheduler.INSTANCE, 3, (byte)0, false, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, onPlayerUpdate$lambda$0(), ()V)(), 6, null);
            }
            return;
        }
        if (!var37_13 && !Globals.getPlayer().isGliding() && MaceElytraTarget.f.hasPassedAndReset(this.getSwapDelay())) {
            this.swapWithMacro(var15_3, var34_8);
        }
        if (this.getPeekAssist()) {
            this.attackTarget(var21_4);
        }
    }

    private final void attackTarget(long l) {
        block4: {
            boolean bl;
            long l2 = l = B ^ l;
            int n = (int)((l2 ^ 0x2F7B0CA08D23L) >>> 48);
            long l3 = l2 ^ 0x3BFA76DA40C4L;
            if (Aura.INSTANCE.getTarget() == null) {
                return;
            }
            Entity entity = Aura.INSTANCE.getTarget();
            Intrinsics.checkNotNull(entity);
            Vec3d vec3d = entity.getEyePos().add(0.0, this.getHeight(), 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            Rotation rotation = Rotation.Companion.to(vec3d);
            double d = Globals.getPlayer().getY();
            Entity entity2 = Aura.INSTANCE.getTarget();
            Intrinsics.checkNotNull(entity2);
            boolean bl2 = d - entity2.getY() > (double)this.getHeight();
            boolean bl3 = bl = DefaultRotationHandler.INSTANCE.y() > 0 || !K.hasPassed((short)n, 10);
            if (bl) {
                if (!P) {
                    ElytraTarget.INSTANCE.findFirework(this.getOffHandFirework(), l3, this.getAlternativeFireworkSwap());
                }
                P = true;
            }
            if (bl2) {
                P = false;
            }
            if (!P) break block4;
            RotationManager.INSTANCE.rotate(rotation, 200);
        }
    }

    private final void swapWithMacro(long l, int n) {
        long l2 = l = B ^ l;
        long l3 = l2 ^ 0x22D502480396L;
        long l4 = l2 ^ 0x47584A06A59FL;
        GuiMove.INSTANCE.setMacroActive(true);
        if (n >= 36) {
            this.startGlidingWith(l4, n - 36);
        } else {
            this.swapChestSlot(n, l3);
        }
        GuiMove.INSTANCE.setMacroActive(false);
    }

    private final void swapChestSlot(int n, long l) {
        long l2 = (l = B ^ l) ^ 0x7E6B641CF51AL;
        InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
        InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
        if (!this.getMinimizeClicks()) {
            InteractionUtil.clickSlot$default(n, 0, null, true, 6, null);
        }
        ElytraSwap.INSTANCE.K(true);
        Globals.getPlayer().startGliding();
        PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        if (fireworkTimer.hasPassedAndReset(this.getFireworkDelay())) {
            ElytraTarget.INSTANCE.findFirework(this.getOffHandFirework(), l2, this.getAlternativeFireworkSwap());
        }
        if (!this.getMinimizeClicks()) {
            InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
        }
        InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
        InteractionUtil.clickSlot$default(n, 0, null, true, 6, null);
    }

    private final void startGlidingWith(long l, int n) {
        long l2 = (l = B ^ l) ^ 0x1BE62C525313L;
        InteractionUtil.clickSlot(6, n, SlotActionType.SWAP, true);
        ElytraSwap.INSTANCE.K(true);
        Globals.getPlayer().startGliding();
        PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        if (fireworkTimer.hasPassedAndReset(this.getFireworkDelay())) {
            ElytraTarget.INSTANCE.findFirework(this.getOffHandFirework(), l2, this.getAlternativeFireworkSwap());
        }
        InteractionUtil.clickSlot(6, n, SlotActionType.SWAP, true);
    }

    @Flow
    private final void onFireWorkVelocity(FireWorkVelocityEvent fireWorkVelocityEvent) {
        float f;
        Vec3d vec3d;
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
        if (this.getCustomSpeed()) {
            this.buildPitchMap();
        }
        float f2 = Math.abs((float)Math.toDegrees(-Math.atan2(vec3d4.y, Math.hypot(vec3d4.x, vec3d4.z))));
        if (Blink.INSTANCE.getEnabled() || !this.getBoost()) {
            f = 0.1f;
        } else {
            Object v4;
            Iterable iterable = ((Map)(this.getCustomSpeed() ? pitchMap : ElytraTarget.INSTANCE.Pd())).entrySet();
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

    @Flow
    private final void onSetPose(SetPoseEvent setPoseEvent) {
        setPoseEvent.cancel();
        setPoseEvent.setPose(EntityPose.STANDING);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block1: {
            if (!(receivePacket.getPacket() instanceof PlaySoundS2CPacket)) break block1;
            RegistryEntry<SoundEvent> registryEntry = ((PlaySoundS2CPacket)receivePacket.getPacket()).getSound();
            Intrinsics.checkNotNullExpressionValue(registryEntry, "getSound(...)");
            RegistryEntry<SoundEvent> registryEntry2 = registryEntry;
            if (Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GOLD) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_IRON) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)) {
                receivePacket.cancel();
            }
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        long l = B ^ 0x2F2409DAA060L;
        int n = (int)((l ^ 0x420D5CFFA86EL) >>> 56);
        if (sendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.isAttackPacket((PlayerInteractEntityC2SPacket)sendPacket.getPacket()) && this.hasWindBurst((byte)n)) {
            if (!Intrinsics.areEqual(Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)sendPacket.getPacket()), Aura.INSTANCE.getTarget()) || MovementUtil.INSTANCE.getFallDistance() < 3.0) {
                return;
            }
            w = 7;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean hasWindBurst(byte by) {
        int n = 0;
        int n2 = 0;
        long l = ((long)by << 56 | (long)n << 32 >>> 8 | (long)n2 << 40 >>> 40) ^ B;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)674987534782507404L, (long)l);
        boolean bl = this.getEnabled();
        if (callSite != null) {
            if (!bl) return false;
            bl = this.p();
        }
        CallSite callSite2 = callSite;
        if (by >= 0) {
            if (callSite2 != null) {
                if (!bl) return false;
                ItemStack itemStack = Globals.getPlayer().getMainHandStack();
                Intrinsics.checkNotNullExpressionValue(itemStack, (String)((Object)MaceElytraTarget.zkmBootstrap("p", (int)32503, (long)(0x5ED7FBEA60B5A10EL ^ l))));
                RegistryKey<Enchantment> registryKey = Enchantments.WIND_BURST;
                Intrinsics.checkNotNullExpressionValue(registryKey, (String)((Object)MaceElytraTarget.zkmBootstrap("p", (int)2902, (long)(0x4157A83AB2C3D4F8L ^ l))));
                bl = EnchantUtil.hasEnchantment(itemStack, registryKey);
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) return bl;
        if (!bl) return false;
        return true;
    }

    private final void buildPitchMap() {
        Pair[] pairArray = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(this.getDegrees0())), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(this.getDegrees5())), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(this.getDegrees10())), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(this.getDegrees15())), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(this.getDegrees20())), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(this.getDegrees25())), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(this.getDegrees30())), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(this.getDegrees35())), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(this.getDegrees40())), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(this.getDegrees45())), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(this.getDegrees50())), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(this.getDegrees55())), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(this.getDegrees60())), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(this.getDegrees65())), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(this.getDegrees70())), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(this.getDegrees75())), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(this.getDegrees80())), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(this.getDegrees85())), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(this.getDegrees90()))};
        pitchMap = MapsKt.hashMapOf(pairArray);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getOffHandFirework();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getPeekAssist();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getBoost();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final void onPlayerUpdate$lambda$0() {
        block0: {
            long l = B ^ 0x3EE5B457E026L;
            int n = (int)((l ^ 0x470AD9F9DC7DL) << 48 >>> 48);
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
                        MaceElytraTarget.B = ZkmKeys.a(-819645340179714705L, -3328665022242807348L, MethodHandles.lookup().lookupClass()).a(50319478314770L);
                        var20 = MaceElytraTarget.B ^ 139260379567848L;
                        MaceElytraTarget.U = new HashMap<K, V>(13);
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
                        var18_3 = new String[98];
                        var16_4 = 0;
                        var15_5 = "?t\u0004iV\u0089\u00c6\u0084Y\u0007a*\u007f4\u00d38^\u0013i\u00f6\u00beS\u008f\u00d4 \u0090\u00f9\u0018\u00da\u00b2K\u00b3Xt\u00b97snW'b\u0085\u0081\u0087SL\u0013\b\u0011K\u00de\u00a4\u00ee\u00bfj\u00df\u00ec(\u00e8{\u0085HZ\u0083[|\u00dc:\u00cb\u0095G$\u00b2\u00afH?\u0098&\u00e0\u0089\u007f\u0081\u0082\u0082\u00e6&\u00c2\u00d0:\u0095]f\u007f\u001f\u0096v]\u0003 C-O\u00f7J\u00d3\u00f4\u0083\u00ee\u0094\u00e3{\u0084\u00d6b\u0095}i\u0094J\u001bDy\u0098\u00c0ytF$\u009d\u00a9 \u0018i?\u00b8\u00ae0\u0000\u0092\u0001o\t\u0081\u00f0\u00bc\u008f/G2\u00e3\u00a69X\u0095\u00af} \u00cf\u0019\u00064\u00f5\u00e9\u00f8\u00ea\u00d1\u00ce\u00c2D\u0012\u00cf=\u0096\u00ae\u00a4\u00fc\u0096\u00a3!\u000e\u009fo\u0086\u00db c\u0001\u0088\u009b \u00d2+\u00e4NX\u0095D+\u00b5=\u0094\u00bb\u00b4\u00da\u001f\u00f7OYaE\u00a8\u00a4\u00f9 \u00dd\u008a\u00e5g\u00f0~\u008b> \u00c3\u00ed\u009a\u0080\u00b0z\u00e7X\u0092eR\u00f9-\u0014g\u00bf&/?\u00ae;-\u00eb\u00f6\u00c3\u0004\u00f2\u00c2 \u0095\u00c5-\u0018\u009d\u00b0\u0098;Tf\u00dca\u0019xH\u00dd\u0001\u00f8W\\\u00f7z0\u009ea\u00a2\u00ce6 \u00e0\u00c4\u008a\u00d5\u0082*^Ck\u0089\u00f1\u0084+\u00e4\u00fd\u009a\u0003\u0013\u00cf!8\u0098Jp\u00f8\u00b9w\\\"\u00c0\u0080\u00d4(\u0018\u00f7[#\u00d3LL\u00a4XB\u00f5Gh\u0013U\u000b\u00d6\u001f\u00ca\u0014\u00ffm+\u0002-\u00ec\u0097'%\u00eb<\r\u00b1'\u00c5T\u00dfZ\u0004\u001c \u00f5\u00f24\u00e8\u0001\u00d2\u00b9-KG\u00e2<\u00b1\u00d3\u0007\\\u00bb\u0094n\u00be\u00ee\u00cb\u00bc\u00fb\u00cf\u00f0\u00cd\u00cc\u001fv#1\u0018Bm\u00fe\u00a8J\u00c6\u00c9\u00cb+E\u00fc}\u0094\u00d2L\u00a4\u000f\u0089JAkv\u0081c \u0002\u00ed#\u00c1^-6\u001fy\u00a1G\u00aa\u00d7\u0019\u00fa\u00b6,-\u0010\u0091\u00bf\u00f8\u009b\\hl}\u00ee\u00ce\u00f9A\u00d0(\u0095-\u00ee0l\u0099a\u00f2\u00c4\u001c\u008d\u00daw\u0082\u00b5\u00cb\u00da\u00ab\u0003\u00af\u00d2\n\u00d0-v\u00af\u00fb\u0088\u00b7x\u00f4\u0004:\u00c8\u00be\u00ba3\u00ed\u00f5} |\u0015\u00a2\u00e2\u00d0\u00c0\u00d3\u0003\u0014\u00e1U\u00df\u001d\u00e5\u0011\u00196\u0019\u001f\u00c5\u0004\u00bb\u0093\u00e0:\u00ec\u00ee\u0016\n\u00fb\u00d4w\u0018u\u00bfl\u00a9\u00ca\u008b\u00adk\u00ee\u00aa5emn \u00e5\u00ca\u00c6\u00d0\u00d7\u00c4\u00fa\u00a9|(\u00a1T\u0080\u00a6\u0001\u00cb\u00abtO\u00cd\u00d3\"\b\u00e8\u00a2\u00c3\u0012\u0010\u0088\u0087\u00ac\u00af\u00bcTdyJf\"\u0080\u0091g\u00b0\u00d5\u00157\u00cc\u00b7:r \u00cd\u00a1\u00ab\u00e9\u00eb\u00ee\u00d1\u00f8\u0000\u00da\u008d\u009c\u00c8\u00a8\u00d1@\u0011\"\u00ad\u00d3\u008e \u00e9\u00ce\u00e7\u0081\u00b4\u00fd 0\u00c5\u0090 \u00ec\u00f8\u009fD\u00cbY\u001c\f8\"\u00ea\u001d-\u001a\u00f9r\u009d\u00ff\u0014l+\u00fb\u00bcu\u0015\u00c3F\u001d\u00c1\u00e0\u0002\u00a8 J\u001b\\\u00a3$\u00a6f\u00e6\ftW!\u00e2\u00fa:6\u009e\u00f4\u00d36\n[y\u0093\u00cbWY|[\f\fM ><U\u0002=\u00c3\u00f4\u00cd\u00d6\u00bd\u00d3\u00c3\r\u0003\u00c9\u00c36\nlr\u008eyB\u00aeMnqR\u0006}9$(\u0099\u001b#\u0011\u000fr\u00be=\u0081\u00f8\u00cbRU\u00b4v*\u00eb[03\u00c4\u00ca72\\\u00c3V\u0080\u00a3B\u00e7\u00db3>\u00fcN\u0091n\u00f9< \u000b+\u009a\u00ae\u009a\u0084\u00c0r\u0099w\u00b1\u008bx \u0000\u0014>\u00c5\u00e3tPwwe\u00b7Y`9\u00bc\u00ff\u000f\u00e4\u0010\u00fbs\u00fc\u0017\u009eQS\u00ac\u00f9\u00f17\u00a7\u00f8A\u00c3\u00b9\u0010f\u0094\u00f1\u0086\u001fzX/\u008f\u00c1<\f\u00df\u00a4\u00cdt ,4\u0090\u00d5\u001a\u00b1\u00b7i7\r\u00942\u0018\u0015[\u0018\u001f\u0010\t\u0084\u00bd\u009b\t\u00edc\u00f9C\u00e8\u00f0\u001d/\u00d3 \u0092\u00b5\u00a8\u00eaq\u0013\u0097\u00bf\u00e0\u00e3C\u00a8B\u001bSH\u00db\u00a5\u0000\u0085\u0091n\u00e4\u009b\u0011\u00d4\u00ffS\u00b0\u00a8rK(\u000f\u00141\u00fbyN\\}\u0081\u00ce\u00c9\u0012\"\u009b\u00ea\u00b1\u001f\u00d1I\u00f3B\u00d9{\u00c5\u00b3\u0093,\u00e9\u00f0\u00b5\u000b\u00ddA`!{\u008f<\u00c4b\u0018\u0093H\u0091fA?Zu\u00af8\u001fp'\u00ad\u00e4\u0015\u00d9\u001c\u00aa\u00f62%@\u00bf\u0018\u00ec\u00eb\u00d9\u00deM\u00d0,\u00ed\u00fc\u00a3\u00fbWIS\"\u008d=\u00eb\u00e3gv37\u00a6 \u00d3\u00cb\u00cd\u001c\u0000\u009a2\u001f\u00d2$y\u00bc%\u007fX\u008cV\u00df\u00fc\u008c+\u0091\u00fc\u00c1\u00d7\u00e0\u00a0\\d\u00d8\u00c8\u00f5 \u008cmA\u001e+\u00b6\u008a\u0098b\u0084\u00cc~\u00ca!\u00d2\u00160\u00bc\f\u00db\u00db{\u0004\u0083\u009e\n\u0087\u00de\u0012\u0098b\u009d !\u0002\u00ef\u001az\u00b7\u009bL\u008f1\u00e9H\u00ac\u00c2Q\u0013y\u00f0\u00d8\u001e\u00a2\u0094|\u008b_\u00dd\u00ac|\b\u0092}9(B\u0090\rq\u00b5\u00bc\u008a\u0094\u0088>vt\u0017s\u0080CG\u00e0*\u00f6\u00ffs\u00c6[_\u00bc\u00ab\u00bd-\u009c\u00b1\u00eb\u00ab\u00c7c\u00c5\u00132d} \u0007\u008c\u0014R1\u0085\u00fd\u00d5\u009b\u00d4>d\u0005\u0004s\u00c9x\u00d2\u00bc\u00b0=`+f*\u0002\u0084Ds\u00b9\u000bF\u0018%\u000bC\u00c1q(\u0088\u00e0(\u00a3d\r)ie\u00c5\u000e\u00e1o\u0080E\u00e6\u000bm\u0018Q\u00acX\u0089\u001c\u001d\u00ae\u00e5\r\u00cf\u00f6\u00d0\u00c5c[i\u00e8\u0016Q\u00ba\u00aa[\bO \u00a54 \u00ffv\u0004\u00f7h\u00aa\u0084\u00d8\u00d8\u00ec\u00bev\u00ac\u008f\u000f}\u00a9\u00a94\u00bb\u00bf\u00cf\u0099\u008f\u00c9\u00826\u0012\u00f4(\u0017`D>\u00d3\u00d4\u00f42\u00fa>\u00a1\u0093$\u00b0mh\u00f2pIv4\u001f\u00bc\u00eaT\u0006z\u00d5\u00b4\u00c6\u00e8\u00896\u00d3\u00f7dj\u00ab\u00ff\r \u0090\u00e0\u00d0`\u00e0\ntnO\u00b6E\u00ca\f\u00d2u5 ry\u00c0\u0003\u001a\u00ea\u00fc\u00dd6\u009e\u00d2\r}\u00db* \u008c\u00a7:\u00db\u00cd\u00f6j\u00f3+\u00aet\b\u00ac\u00f8\u008fRN\u00fa\\0y\u0097T\u0003\u001a\u00c1\u00d4\u00e8\u0097\u00b4\u00f0\u00e8(\u00f3\u00d5{\u00daU\u0093\u00b2\u00e3p\u00af\u008cmu\u00bb73\u0095{\u0089{\u001f\u00db\u00da\u001d\u00d6\u00f9E}NF\u00ed\u00ca\u009b\u0089\u0092\u00f6\u00c8f\u00f8\u00a0 Q\u0094\u00aa\u00d8A&\rm\u0015\u0007R\u0018\u0082L`J\u0083\u001d\u00cdt%s{,\u00c7\u00cc\u008c\u00bfZ\u0017\u00c3\u00cf Xh\u00e2F2\u0088\u00d2\u0085\u0097'\u0006\u0080\u0013\u00f3\u00c7kQ\u001b\u00ab\u0096\u0011Y\u0091\u00c4ok\u0087T\u00ba\u00db\u0085\u00ea \u000e\u00ea^\u00fd(\u00c9\u000b\u00e16M\u00e6TUD\u001f\u00f4H\u00f6\u0002\u00c3\u00e3\u008d\u00bei\u0013\u00d8\u00e1?\u0098:^\u009e nF\u00acp\u00db\u000b\u001d\u009cO\u00aa\u0000}j\u0093\u00b6Mup\u008d\u007f\u0004$\u00e9\u0010\u00ef\u00a6\t\r\u00fc9\u009d\u00be 0\u00e5w\u00bb1mv\n/\u001c\u001a\u001b\u00cd\r\u0012\u00a3Q>\u009bq\u00d1\u00df\u00a5\u0085j\u00a8\f\u00b4F\u00ca&% \r\u00bc]D\u00ba~\u000eU:\u00a2\u00c4\n\u0097\u00d7\u0095\u00fe\u00aa\u00df\u00db\u0019\u008e\u00ac\u001b\u0092\u00f1\u0012\tx\u001d\u0085\u0099>\u0010\u00d1\u00f5\fc\u00e6T\u00a3\u00cf\u0087\u00d1\u00d6\u00d2\u00ed\u001b\u008f \u0018F\u0017\u0000#\u00f6\u00a0\u00c6\u00c3\u00e0\u001d\u00c1\u0000r\b\u009fD\u00b5\u0083\u009b\u00ff)\u0018E` G\u0080G\u00d9\u00a4F\u00ec\u00e2\u00c5\u009b\t\u0093\t3\u0099\u00eb msy\u000b\u00f0\u00dbN\u00b9\u00d9\u00ba*\u00d0\u00c0\fZ 2@-@\f\u001b;};B\u0017%\u00d5\u00ae9\r\u00ea\u008d\u00c4\u000e\u0010G\u00ef\u000e\u008f\u0097\u00cd\u00f8 B\u0013\u00b5\u0018Wk\u0004\u0088\u00a0\u00d0\u0080\u00e9\u00ac<\u000e\u00cbs\u00d3\u00a1\u00d1\u00fc\u00a1\u001b\u0081\u00a7F\u0096\u00c2 \u00ee\u00f7i\u00b4\u001cN\u00a0\u0089\u00cb\u00a7\u00d6\u0019\u00f7\u0085I\u00f4\u00b8 G\u00b4\u00bd\u00d6\u00023\u000e\"\u00c9\u00a0\u0001eq\u00fa\u0018\f\u00d9\u00d0\u0016T\u00e4\u00d3}\u00b2\u00f3\u00c1\u00a2\u00f8\u00d1\u008b\u00f9\u00d9\u0016\u008b\u0010\u00fc\u0099\u0016c\u0018\u0088\u00f7\u009c3\u00e7WT\u008f\u00c3\u00be0\u00e0\u0090L\u0099\u008e\u00d2\u00a85:\u00f8\u00f0\u0081\u009f(b}\u00c8\u00b6\u008d\u00ba?5\u009b:Ms\u001f\u00ddS\u0094\u001c\u00b8\u00d3h\u009d_\u00988\u0091\u00c6\u001f\u00bbd\u009ek\u00d6l\u0097\u0099\u00cd2\u00d4|\u0098 \u00cd\u0088\u00fcC7si\u00e6\u00c3\u00ff\u00abAInK`\u00ca\u001c\u00f78\u00aa\u00ac\u00ac\u00ff\u001a\u00c1\u0092\u0010Z\bQ\u00b4 LB\u00ed%\u00f17\u00eb\u00d6\u0012\u00c2Vxa\u00e6\u00d2\u00c9\u00d1\u00c2\u00fc\u00e6\u00c5\u00a5 \u00e1\fAk1\u00c3\u00efp\u00d2 \u001b\u00acI\u0005\u0082\"\u0099~'z\u0086\u008d}7w\u00a2\u0013\u0003\u0016\u00ef#U\u00da_Q!\u0099\u009fTf\u00e7\u009d(\u001d,\u00ec\"'-\u000e\u00aak\u0082\u00a4\u00ec\u00a9\u008f\u00c5Q{\u001f\u0089K\u00a1\u0082X\u00ca\u00e3\u00db\u00c1I\u00eb\u008f\u00de\u008d\u00d7>h\u00ff\u00b5\f\u0096\u000f\u0018\u00f3D\u00e2\u00df\u00b0\u009f\u008d\u00eeNU\u00e5C\u0083::P\u009a\u00b5\u0011\u00a1\u00c8\u009a\u00ec\u001b(D\u00d3\u008e\u00a9jxn\u0010\u00199)]\u00cf\u0083\u0098\u00d9\u00f3\u00e8/\u00fd\u00c7\u00b1\u00e8\u00e5Uo\u00e9\u00af\u00bd\u00d2\u00d11D\u00f8R\u00dcc\u00fc\u000f\u00ba\u0018\u00b0,X\u00b7H\u0006\u008a\u00f4\u001dA\f\"d\u00c2f\u00ab\u00f1\u001c\u00f4\u00ff\u0085\u00e4\"\u00b0 \u00928\u0001\r\u00dc\u00e7\b\u00a0\u00da3\u00ea\u0086\u00a4\u0087\u00b2\u0098\u00b91\u00db\u001d\u0000\u00dee\u00e5\u0096\t\u0094\u0015F\u00f7-\u0002 @\u0001\u001f\u00f0\u0005\u0087,$\u00ac\u00faB6wf\u0098\u0094\u00b7|\u00d7,<e\u000e\u0091z0\u000e\u0098&^\u00e6[(\u00fcS?\u00dds\u0093\u00f6\u009b/\u00b8\u0010\u00b7\u0006\u0094\fU\u00bc\u00bf\u0003n~\u00a75\u00a3\u000e\u0092\u00b3\u00174\u00c2\u00cc\u00b6\u00bc\u000b\u00ab\u00d5\u001f1[\u00ef\u0018\u00d8\u00e7\u0094\u00f1\u00ba\u000f\u000eW\u00f9\u009e\u00b1\u001a\u00af\u00b3\u00a55\u00b4\u00a2\u0019@\u00b3F.\u00c1 3\u0082\u00ea\fD\u00b3%\u0010eG\u00f1,n\u008b}\u0093\u00f3\u00e2-B\u009a\u00b3\u0091\u00d5n\u00bf\u0007/\t\u00f3M\u0098\u0018q$\u0080\u00ad\u0099V\u0096\u00cb\u00d5\fs\u00f6O2\u00bfg\u001c]6\u0007\u009e\u00a6C\u00ec 3V1\u00d2)\u0004'\u00c0c\u0010\u0003\u00d1\u0003\u00bbO\u001e8\u0084\u001c\u000e\u00e0\u00d2\u00af\u00bfZp\u00bf\u0084r\u00bd\u0006/(\u0087J\u0093\u0014\u0099w\u001b\r\u00e3\u00ff\u00eem\r\u00c4[\u00e0\u008e\u0094\u00f1yaN\u00d4\u00d2d2z\n\u0018\u00a9\u00aeK\u00f46\u00b0Ed\u0088\u00b3\u0016 \u001f\u00d9\u00a7\u00a0@\u00e4\u00ab\u00f2\u0095]\u00c0r\u0017\u00e7X\u0095\u0007i\r\u0093I\u000b\u00fe\u00e8\u008a\u00cf\u00ef\u009d\u00f1c\u00b5T(\u009b\u00e56/\u00f3K\u00e9 \u009f\u00b6I\u00b2Z!\u0001'\u00bf\u00fb\u00f7\u00e1-QF\u00e1Z\n\u00b6\u00f0\u00f4 V,!n\u0004\u00c8\u0085%\u00018 \u009d0\tbox\u0006\u0087eD\u00e6m\u00bcsV\u00fdV\u00fcVE\u0086\u00a0\u00feH\u00ea\u00ba\u00c81p8\u00e9\u00da8\u0098\u008f~\u0089T\u0011\u00d2e\u009b\u00bf3s\u008a\u00d9M\u00b8\u00c1\u0085\u00c5l\u00c9V\u00ae\u00de\u00ba\u00f1_\rPp\u00cd\u001d\u00e4\u0015\u008e\u0007\u001b\u00d8~t\u00f0~DN\u0006\u00bbH_\u0084\u00b3\u00c9\u00c4!\u00a7\u00a1\u00e8 @|\u001b\u00052\u00a9\u00163\u000e\u00a5\u00a5\u00f1k\u00d6X6\u00dc\u0090\u0010^\u00ef\u00859\u00c9p\u009e<\u0000\u0019\u00f4\u0097\u000e \u00aex\u0010_\u0084\u00db\u00e8Z\u00f9\u00cdDI\u0085\u00da\u001d(h\u00c6\u00a1&\u0096\u00b6\u00fa\"9 \u000b\u001f\u00cf\u00bb\u00d7\u00f1 \u0001\u0016\\K\u00c9\r\u00f3\u001c\u000e\bk\u0003\u0091\u0005\u008a\u001f\u001f\u008d\u00ee<\u0001\u009b\u00a1\u009bq\u0000\u00b6f\u00bd!nD\u0018\u0091\u00f0F\u00c15\u00f6{\u0007\u00a1X\u001a\u0005\u00f4\u009b)l\u00b3\u00e5\u008a\u00cbO\u00db\u000bg(\u00db\u0012D\u00d3C\u00b1\u001eV\u00dc\u00a7{\u0088\u00df\u00ff\u00c6 \u0085\u00f2\u001a\u00ac\u0087\u00931y\u00db\u00f0\u00c7\u00ba\u00809\"7(\nh\u0098\u008aZ\u00e7\u00de\u0010c\u00cer1\u0096\u001c\u0082\u00e6/1q\u009d\u00b5\u00ed\u009e\u00f3 \u00f2h\u0019\u00aai\u00b5\u00bb\u0013\u009d\u0095'\u0097o\u00d0\u00d2\u0096\u0007e.\u0082~KfpE\u00d7P\u00fc\u00ff\u00fd\u00d3? \u001aN\u00a3\u00fa\u00b5ng+\u00a9\u0082\u00f0\u00de\u00dcm\u0081\u008e\u00c2p\u00dey\u00d8aS\u00e9\u008e\u0089\u00ab\u00f5ekV8 ,\u0097\u00ba\u00c4\u00a6\u00f0!m\u00de\u00d7\u00e1\u008bj\u00b8\u00b2!\u00f2$\u00fd\u00be\u00ff\u00e5\u0082\u0090\u0010\u0015/3\u00c4\u00ec\u00abE \u00d7W\u00fb\u00c9\u00c7^\u00ae$\u00e6\"*,\u00063\u0083J\u00e7\u00cbc\u00be\u00f2\u00c8r\u00daf\u00b4\u0010n\u00861\u001d\u00cc \"\u00f6\u00a4\u00d6q\u0004\u00c1S\u00f6\t\u0017\u00d7\u00fe\u00a9\u00f2\u001f\u00d8$W\u00f6]\u00b9\u0084\u00c02\u0004\u00a5\u00d0\u00ea\u00e66D\u0018c\u00c6\na\u0094HS\u008e\u0082\rI\u009e\"\u000eI\u0001z\u00d1\u00bf\u00ee\u00bfy\r\u00a4\u00183w\nsL\u00b3\u00aaY\u0092\u00c2n\u001d\u00cbP'\u00d6\u008fF\u0005bS\u0080\nB\u0018&\\[r\u00b2\u00d0\u0094\u0006\u0097\u00c9\u00da\u0017\u00f2\u00cah\u00ea\u00f4{\u00ca\u00ebYp\u009b\u0081 \u00b17\u0089\u00c9\u0092\u0005\u00ff\u00e0C\u0096(\u00d1\u00f1\u00a8*?q.\u0085d\u00edn\u00e0\u0085\u0007\u00bfd\u009d+\u007f\u00b0  %\u00bdn\u0013\u0095\u00d9\u0018\u00d8\u0093z\u009f#HK\u00c5:5U\u00a8\u0085]\u001d\u008b\u00fc\u00b2C\u00f3\u00a1\u00056\u00dd\u001c\u0018\u00ae\u00b5\u00f8\u00d1\u00b11\u0002\u00e8\u0083<Z<\u008b2l\u00d4\u000b$\u001c\u00fb\u0017\u0085\u009d\u009b l\u00cd/\u00d3\u008fdmf\u00f8SV-\u00ce/\u00c9f\u00e5YS\u001f\u0092\u00bc\u0091\u0003|\u00c6\u0094\u00cf\u00b5\u0080/]\u0018\u001d\u00a9Q\u000b\u008f\u00f0\u00fex\u00d5\u0084T)\u00ec\u0092df$\u00edd\u00c2\u00eaa\u009aO";
                        var17_6 = 3071;
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
                            var18_3[var16_4++] = MaceElytraTarget.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b9\u00d1fv\u00e01C\u00c4J#\u0096\u00a5\u007f\u00d9\u00f7\u00a5\u00b3\u009fy\u00fe\u00176\u0016\u00e3l\u00f8\r+K\u0092\u00ff| B\u00bb\u0002\u00af\u00db\u00af\u00bf1\u00f9YQ\u0087\u008a\u00f6\u0086;\u0003\u0014\u00deE\u0092|t\u00f0\u00bf\u0081\u001a\u00ef'\n\u00adw";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = MaceElytraTarget.zkmDecodeUtf8(var19_9).intern();
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
                MaceElytraTarget.E = var18_3;
                MaceElytraTarget.O = new String[98];
                MaceElytraTarget.gb = new HashMap<K, V>(13);
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
                var6_12 = new long[56];
                var3_13 = 0;
                var4_14 = "D\u008b\u00cei\u00b6\u0012-\u00b1:\u00e4\u00cc\u00d8\u0013^@<\u0091\u00dbn\u00e5\u00c2\u0093Ib\u00fe#\u00b1\u00c2\u00d7\u00f2\u00e6q\u00e1[r\u0007Q\u00e8</\u007f\u0004;\u00c21\u00dc\u00ce_\u001c\u00a0\u001c\u00ea\u009e#8KE\u00f4\u00f8\u00bd\"\u0088\u00ed\u00b5\u0099 \u008dJ\u00ea\u00f3`J\u00b7\u0096t\u00ba,\u00a5B\u00ffc\u007fB\u00ae\u00c0\u00c3\u00e2\u0088\u00d4\u0099lr)\u00ee/\u00c5E\u00bb\u0002*\u00a7ym\t2\n\u0000O\u00d8L N\u0082\u0013d cE\n\u00e6)\u00f3\u0086a\u0000\\\u00e3\u00fc\u00c5U\u00e5\u001b\u00e1&x\b;\u0092\u0092@w\u0017\u00b4\u00fe\u00f5\u0084uEDl\u00d1\u00b4H\u00ac\u0099P\u000e\u0092\u008dr\u00e8\u00f51\u00f5YO\rd\u000f\u00b4!\u0000rfX\u0088k\u00a9\u0083EE[\u00f0\u009bys\u00e4N\u00ff\u0083\u007f\u0016\u00cc\u009av\u0093\u00bd\u0092\u00812o\u0099\u0006\u009b\u0019\u00fa\u001a\u00c3\u00dev^\u00d4[o\u00a8'H\u0099\u00b2\u001f+a\u00e8\u0085\u00f1KS|b:#\u00e3\u00d5\u00bd\u00d7T\u0002l\u0003\u008e\u0089r\u00baf5/\u001b+\u00b8\u00b4\u00a1@3\u0085B\u00ac\u00c3&\u00b5B]k\u00f0\u00c7(\u0010]\u00eb\u0007\u0091\u00ecP\u00b2\u001d^\u0012\u00bacp\u00171\u0092x\u0001\u008d\u00d5\u00c5\u00c0\u0013\u00d9\u00a5\u0092\u00edZ\u001e\u00ac\u0084\u00a4T\t\u0019L\u0007\u00d6}\u00a3\u00ea\u00c8I\u00fbR1\u0007\u00aad\u00b4M\u00da\u00b9\u0084\u00df\u001ctZ\u00dc\u00dbt`\u00f6d/\u009e\u0013\u001b\u0096\u00b4h\u00f9~\u00c1Z\u00a2K*&\u00ee\u00e9U\u00ca\u00f3V\u000b%\u00ac\u00b4\u0089a\u0098'vhl\u00b9\u0007u\u00dew\u00d4\u0088\u00e2\u00f5\u00fb\u00c9\u00c2iI\u00ad\u0005%\u00e35\u00cd\u009d\u00fcI\u00d4\u00fa\u00dcH\u00db9\u00d4\u00b1?\u00a54\u009a\u0013\u0083~\u0089\u00f2\u00d3\u00ed\u00fe\u00daH\u00bc-\u00e7\u009d(4zX\u00fe\u00dd#\u00b9H\u00d9\u000b\u00f3V\u0014`J\u001f0\u00a7\u0003\u00da";
                var5_15 = 432;
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
                    var4_14 = "\u00c7\u00a1m\u007f\u00d1\u00aa\u00ac{\u00b7\"\u00de\u00a4:\u0001\u0092\u00fa";
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
        MaceElytraTarget.ab = var6_12;
        MaceElytraTarget.fb = new Integer[56];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "height", "getHeight()I", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "swapDelay", "getSwapDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "fireworkDelay", "getFireworkDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "offHandFirework", "getOffHandFirework()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "alternativeFireworkSwap", "getAlternativeFireworkSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "pauseWhileEating", "getPauseWhileEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "minimizeClicks", "getMinimizeClicks()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "peekAssist", "getPeekAssist()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "windBurstSync", "getWindBurstSync()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "boost", "getBoost()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "customSpeed", "getCustomSpeed()Z", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees0", "getDegrees0()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees5", "getDegrees5()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees10", "getDegrees10()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees15", "getDegrees15()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees20", "getDegrees20()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees25", "getDegrees25()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees30", "getDegrees30()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees35", "getDegrees35()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees40", "getDegrees40()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees45", "getDegrees45()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees50", "getDegrees50()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees55", "getDegrees55()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees60", "getDegrees60()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees65", "getDegrees65()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees70", "getDegrees70()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees75", "getDegrees75()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees80", "getDegrees80()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees85", "getDegrees85()F", 0)), Reflection.property1(new PropertyReference1Impl(MaceElytraTarget.class, "degrees90", "getDegrees90()F", 0))};
        MaceElytraTarget.delegatedProperties = var32_20;
        MaceElytraTarget.INSTANCE = new MaceElytraTarget();
        MaceElytraTarget.height$delegate = SettingFactory.intSetting$default(MaceElytraTarget.INSTANCE, "height", 25, new IntRange(25, 60), null, null, 24, null);
        MaceElytraTarget.swapDelay$delegate = SettingFactory.intSetting$default(MaceElytraTarget.INSTANCE, "elytra-delay", 2, new IntRange(0, 40), null, null, 24, null);
        MaceElytraTarget.fireworkDelay$delegate = SettingFactory.intSetting$default(MaceElytraTarget.INSTANCE, "firework-delay", 8, new IntRange(0, 40), null, null, 24, null);
        MaceElytraTarget.offHandFirework$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "offhand-firework", true, null, null, 12, null);
        MaceElytraTarget.alternativeFireworkSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "alt-firework-swap", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        MaceElytraTarget.pauseWhileEating$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "pause-while-eating", true, null, null, 12, null);
        MaceElytraTarget.minimizeClicks$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "minimize-clicks", false, null, null, 12, null);
        MaceElytraTarget.peekAssist$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "peek-assist", true, null, null, 12, null);
        MaceElytraTarget.windBurstSync = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "wind-burst-sync", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        MaceElytraTarget.boost$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "boost", false, null, null, 12, null);
        MaceElytraTarget.customSpeed$delegate = SettingFactory.booleanSetting$default((SettingContainer)MaceElytraTarget.INSTANCE, "custom-speed", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        MaceElytraTarget.degrees0$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "0-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees5$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "5-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees10$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "10-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees15$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "15-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees20$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "20-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees25$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "25-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees30$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "30-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees35$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "35-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees40$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "40-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees45$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "45-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees50$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "50-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees55$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "55-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees60$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "60-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees65$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "65-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees70$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "70-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees75$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "75-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees80$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "80-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees85$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "85-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.degrees90$delegate = SettingFactory.floatSetting$default(MaceElytraTarget.INSTANCE, "90-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)(), 8, null);
        MaceElytraTarget.fireworkTimer = new TickTimer();
        7106617725459475156L ^ var20;
        MaceElytraTarget.f = new TickTimer();
        MaceElytraTarget.K = new TickTimer();
        var32_20 = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(0.155f)), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(0.19f)), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(0.27f)), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(0.28f)), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(0.2f)), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(0.18f)), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(0.13f))};
        MaceElytraTarget.pitchMap = MapsKt.hashMapOf(var32_20);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x57B4;
        if (O[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])U.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    U.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ul", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = E[n2].getBytes("ISO-8859-1");
            MaceElytraTarget.O[n2] = MaceElytraTarget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return O[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MaceElytraTarget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ul" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE68;
        if (fb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = ab[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])gb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    gb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ul", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MaceElytraTarget.fb[n2] = n3;
        }
        return fb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MaceElytraTarget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ul" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MaceElytraTarget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MaceElytraTarget.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

