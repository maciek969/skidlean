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
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.ElytraPlusAntiKickMode;
import su.catlean.module.rage.ElytraPlusMode;
import su.catlean.module.rage.ElytraPlusNcpStrictMode;
import su.catlean.module.rage.ElytraPlusStopMoveMode;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class ElytraPlus
extends Module {
    @NotNull
    public static final ElytraPlus INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting twoBee$delegate;
    @NotNull
    private static final BindSetting lift$delegate;
    @NotNull
    private static final BooleanSetting onlySpace$delegate;
    @NotNull
    private static final BooleanSetting stopOnGround$delegate;
    @NotNull
    private static final BooleanSetting infDurability$delegate;
    @NotNull
    private static final BooleanSetting vertical$delegate;
    @NotNull
    private static final ModeSetting ncpStrictMode$delegate;
    @NotNull
    private static final ModeSetting antiKick$delegate;
    @NotNull
    private static final FloatSetting xzSpeed$delegate;
    @NotNull
    private static final FloatSetting ySpeed$delegate;
    @NotNull
    private static final IntSetting fireSlot;
    @NotNull
    private static final BooleanSetting accelerate$delegate;
    @NotNull
    private static final FloatSetting accelerateFactor$delegate;
    @NotNull
    private static final FloatSetting fireDelay$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final BooleanSetting stayMad$delegate;
    @NotNull
    private static final BooleanSetting keepFlying$delegate;
    @NotNull
    private static final BooleanSetting disableOnFlag$delegate;
    @NotNull
    private static final BooleanSetting allowFireSwap;
    @NotNull
    private static final BooleanSetting instantFly;
    @NotNull
    private static final BooleanSetting cruiseControl$delegate;
    @NotNull
    private static final BooleanSetting pitchControl$delegate;
    @NotNull
    private static final FloatSetting factor;
    @NotNull
    private static final FloatSetting upSpeed$delegate;
    @NotNull
    private static final FloatSetting downFactor$delegate;
    @NotNull
    private static final BooleanSetting stopMotion$delegate;
    @NotNull
    private static final FloatSetting minUpSpeed$delegate;
    @NotNull
    private static final BooleanSetting forceHeight$delegate;
    @NotNull
    private static final IntSetting manualHeight$delegate;
    @NotNull
    private static final FloatSetting sneakDownSpeed$delegate;
    @NotNull
    private static final BooleanSetting speedLimit;
    @NotNull
    private static final FloatSetting maxSpeed$delegate;
    @NotNull
    private static final FloatSetting redeployInterval$delegate;
    @NotNull
    private static final FloatSetting redeployTimeOut$delegate;
    @NotNull
    private static final FloatSetting redeployDelay$delegate;
    @NotNull
    private static final IntSetting infiniteMaxHeight$delegate;
    @NotNull
    private static final ModeSetting stopMove$delegate;
    @NotNull
    private static final IntSetting swapDelay$delegate;
    @NotNull
    private static final IntSetting fireworkDelay$delegate;
    @NotNull
    private static final BooleanSetting offHandFirework$delegate;
    @NotNull
    private static final BooleanSetting alternativeFireworkSwap$delegate;
    @NotNull
    private static final BooleanSetting pauseWhileEating;
    @NotNull
    private static final BooleanSetting minimizeClicks$delegate;
    @NotNull
    private static final BooleanSetting boost$delegate;
    @NotNull
    private static final BooleanSetting customSpeed$delegate;
    @NotNull
    private static final FloatSetting _0Degrees;
    @NotNull
    private static final FloatSetting _5Degrees;
    @NotNull
    private static final FloatSetting degrees10$delegate;
    @NotNull
    private static final FloatSetting _15Degrees;
    @NotNull
    private static final FloatSetting degrees20$delegate;
    @NotNull
    private static final FloatSetting _25Degrees;
    @NotNull
    private static final FloatSetting _30Degrees;
    @NotNull
    private static final FloatSetting degrees35$delegate;
    @NotNull
    private static final FloatSetting _40Degrees;
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
    private static final Timer redeployTimer;
    @NotNull
    private static final Timer boostTimer;
    private static boolean isReady;
    private static boolean n;
    private static float currentSpeed;
    private static float boostSpeed;
    private static float savedPitch;
    private static Module[] p7;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;

    private ElytraPlus() {
        super("elytra-plus", Categories.getRage(), null, null);
    }

    @NotNull
    public final ElytraPlusMode getMode() {
        return (ElytraPlusMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    public final boolean getTwoBee() {
        return (Boolean)twoBee$delegate.getValue(this, delegatedProperties[1]);
    }

    @NotNull
    public final Bind getLift() {
        return (Bind)lift$delegate.getValue(this, delegatedProperties[2]);
    }

    public final boolean getOnlySpace() {
        return (Boolean)onlySpace$delegate.getValue(this, delegatedProperties[3]);
    }

    public final boolean getStopOnGround() {
        return (Boolean)stopOnGround$delegate.getValue(this, delegatedProperties[4]);
    }

    public final boolean getInfDurability() {
        return (Boolean)infDurability$delegate.getValue(this, delegatedProperties[5]);
    }

    public final boolean getVertical() {
        return (Boolean)vertical$delegate.getValue(this, delegatedProperties[6]);
    }

    @NotNull
    public final ElytraPlusNcpStrictMode getNcpStrictMode() {
        return (ElytraPlusNcpStrictMode)((Object)ncpStrictMode$delegate.getValue(this, delegatedProperties[7]));
    }

    @NotNull
    public final ElytraPlusAntiKickMode getAntiKick() {
        return (ElytraPlusAntiKickMode)((Object)antiKick$delegate.getValue(this, delegatedProperties[8]));
    }

    public final float getXzSpeed() {
        return ((Number)xzSpeed$delegate.getValue(this, delegatedProperties[9])).floatValue();
    }

    public final float getYSpeed() {
        return ((Number)ySpeed$delegate.getValue(this, delegatedProperties[10])).floatValue();
    }

    public final int kQ() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return ((Number)fireSlot.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)31301, (long)(0x1BEC77E8B41DFF82L ^ l))])).intValue();
    }

    public final boolean getAccelerate() {
        return (Boolean)accelerate$delegate.getValue(this, delegatedProperties[12]);
    }

    public final float getAccelerateFactor() {
        return ((Number)accelerateFactor$delegate.getValue(this, delegatedProperties[13])).floatValue();
    }

    public final float getFireDelay() {
        return ((Number)fireDelay$delegate.getValue(this, delegatedProperties[14])).floatValue();
    }

    public final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[15]);
    }

    public final boolean getStayMad() {
        return (Boolean)stayMad$delegate.getValue(this, delegatedProperties[16]);
    }

    public final boolean getKeepFlying() {
        return (Boolean)keepFlying$delegate.getValue(this, delegatedProperties[17]);
    }

    public final boolean getDisableOnFlag() {
        return (Boolean)disableOnFlag$delegate.getValue(this, delegatedProperties[18]);
    }

    public final boolean kA() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)allowFireSwap.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)27385, (long)(0x614D55AF0421E509L ^ l))]);
    }

    public final boolean kC() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ ab;
        return (Boolean)instantFly.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)4148, (long)(0x5C0953248C7EAE26L ^ l))]);
    }

    public final boolean getCruiseControl() {
        return (Boolean)cruiseControl$delegate.getValue(this, delegatedProperties[21]);
    }

    public final boolean getPitchControl() {
        return (Boolean)pitchControl$delegate.getValue(this, delegatedProperties[22]);
    }

    public final float Fl() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return ((Number)factor.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)16388, (long)(0x77B367B3866B7D9DL ^ l))])).floatValue();
    }

    public final float getUpSpeed() {
        return ((Number)upSpeed$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    public final float getDownFactor() {
        return ((Number)downFactor$delegate.getValue(this, delegatedProperties[25])).floatValue();
    }

    public final boolean getStopMotion() {
        return (Boolean)stopMotion$delegate.getValue(this, delegatedProperties[26]);
    }

    public final float getMinUpSpeed() {
        return ((Number)minUpSpeed$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    public final boolean getForceHeight() {
        return (Boolean)forceHeight$delegate.getValue(this, delegatedProperties[28]);
    }

    public final int getManualHeight() {
        return ((Number)manualHeight$delegate.getValue(this, delegatedProperties[29])).intValue();
    }

    public final float getSneakDownSpeed() {
        return ((Number)sneakDownSpeed$delegate.getValue(this, delegatedProperties[30])).floatValue();
    }

    public final boolean Fg() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)speedLimit.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)27828, (long)(0x1AAC9E75CC542399L ^ l))]);
    }

    public final float getMaxSpeed() {
        return ((Number)maxSpeed$delegate.getValue(this, delegatedProperties[32])).floatValue();
    }

    public final float getRedeployInterval() {
        return ((Number)redeployInterval$delegate.getValue(this, delegatedProperties[33])).floatValue();
    }

    public final float getRedeployTimeOut() {
        return ((Number)redeployTimeOut$delegate.getValue(this, delegatedProperties[34])).floatValue();
    }

    public final float getRedeployDelay() {
        return ((Number)redeployDelay$delegate.getValue(this, delegatedProperties[35])).floatValue();
    }

    public final int getInfiniteMaxHeight() {
        return ((Number)infiniteMaxHeight$delegate.getValue(this, delegatedProperties[36])).intValue();
    }

    @NotNull
    public final ElytraPlusStopMoveMode getStopMove() {
        return (ElytraPlusStopMoveMode)((Object)stopMove$delegate.getValue(this, delegatedProperties[37]));
    }

    public final int getSwapDelay() {
        return ((Number)swapDelay$delegate.getValue(this, delegatedProperties[38])).intValue();
    }

    public final int getFireworkDelay() {
        return ((Number)fireworkDelay$delegate.getValue(this, delegatedProperties[39])).intValue();
    }

    public final boolean getOffHandFirework() {
        return (Boolean)offHandFirework$delegate.getValue(this, delegatedProperties[40]);
    }

    public final boolean getAlternativeFireworkSwap() {
        return (Boolean)alternativeFireworkSwap$delegate.getValue(this, delegatedProperties[41]);
    }

    public final boolean Fu() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)pauseWhileEating.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)16249, (long)(0x717686C76F1DD9A5L ^ l))]);
    }

    public final boolean getMinimizeClicks() {
        return (Boolean)minimizeClicks$delegate.getValue(this, delegatedProperties[43]);
    }

    public final boolean getBoost() {
        return (Boolean)boost$delegate.getValue(this, delegatedProperties[44]);
    }

    public final boolean getCustomSpeed() {
        return (Boolean)customSpeed$delegate.getValue(this, delegatedProperties[45]);
    }

    public final float L() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return ((Number)_0Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)14020, (long)(0x5444727E67748BC2L ^ l))])).floatValue();
    }

    public final float q() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ ab;
        return ((Number)_5Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)29303, (long)(0x52A288756DA64AD8L ^ l))])).floatValue();
    }

    public final float getDegrees10() {
        return ((Number)degrees10$delegate.getValue(this, delegatedProperties[48])).floatValue();
    }

    public final float j() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ ab;
        return ((Number)_15Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)3866, (long)(0x5550CDF1CEED73A2L ^ l2))])).floatValue();
    }

    public final float getDegrees20() {
        return ((Number)degrees20$delegate.getValue(this, delegatedProperties[50])).floatValue();
    }

    public final float kU() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ ab;
        return ((Number)_25Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)25167, (long)(0x61C63EE2CDC3D6EL ^ l))])).floatValue();
    }

    public final float kf() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 32 | l << 32 >>> 32) ^ ab;
        return ((Number)_30Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)21414, (long)(0x12A8382A67BBA091L ^ l2))])).floatValue();
    }

    public final float getDegrees35() {
        return ((Number)degrees35$delegate.getValue(this, delegatedProperties[53])).floatValue();
    }

    public final float kz() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return ((Number)_40Degrees.getValue(this, delegatedProperties[ElytraPlus.zkmBootstrap2("e", (int)28207, (long)(0x34577EF4F704B8A9L ^ l))])).floatValue();
    }

    public final float getDegrees45() {
        return ((Number)degrees45$delegate.getValue(this, delegatedProperties[55])).floatValue();
    }

    public final float getDegrees50() {
        return ((Number)degrees50$delegate.getValue(this, delegatedProperties[56])).floatValue();
    }

    public final float getDegrees55() {
        return ((Number)degrees55$delegate.getValue(this, delegatedProperties[57])).floatValue();
    }

    public final float getDegrees60() {
        return ((Number)degrees60$delegate.getValue(this, delegatedProperties[58])).floatValue();
    }

    public final float getDegrees65() {
        return ((Number)degrees65$delegate.getValue(this, delegatedProperties[59])).floatValue();
    }

    public final float getDegrees70() {
        return ((Number)degrees70$delegate.getValue(this, delegatedProperties[60])).floatValue();
    }

    public final float getDegrees75() {
        return ((Number)degrees75$delegate.getValue(this, delegatedProperties[61])).floatValue();
    }

    public final float getDegrees80() {
        return ((Number)degrees80$delegate.getValue(this, delegatedProperties[62])).floatValue();
    }

    public final float getDegrees85() {
        return ((Number)degrees85$delegate.getValue(this, delegatedProperties[63])).floatValue();
    }

    public final float getDegrees90() {
        return ((Number)degrees90$delegate.getValue(this, delegatedProperties[64])).floatValue();
    }

    @NotNull
    public final Timer getRedeployTimer() {
        return redeployTimer;
    }

    @NotNull
    public final Timer getBoostTimer() {
        return boostTimer;
    }

    public final boolean isReady() {
        return isReady;
    }

    public final void setIsReady(boolean bl) {
        isReady = bl;
    }

    public final boolean F1() {
        return n;
    }

    public final void e(boolean bl) {
        n = bl;
    }

    public final float getCurrentSpeed() {
        return currentSpeed;
    }

    public final void setCurrentSpeed(float f) {
        currentSpeed = f;
    }

    public final float getBoostSpeed() {
        return boostSpeed;
    }

    public final void setBoostSpeed(float f) {
        boostSpeed = f;
    }

    public final float getSavedPitch() {
        return savedPitch;
    }

    public final void setSavedPitch(float f) {
        savedPitch = f;
    }

    @Override
    public void onEnable(long l) {
        long l2 = l ^ 0x6A3FE8999CE4L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        if (Globals.getMc().world == null) {
            return;
        }
        currentSpeed = 0.0f;
        boostSpeed = (float)Globals.getPlayer().getY();
        this.getMode().getElytraListener().onEnable((char)n, n2, (short)n3);
    }

    @Flow
    private final void onPreElytra(PreElytraEvent preElytraEvent) {
        long l = ab ^ 0x70D9DB6B8DE7L;
        long l2 = l ^ 0x7BC1C3DB4907L;
        this.getMode().getElytraListener().onPreElytra(preElytraEvent, l2);
    }

    @Flow
    private final void onAfterElytra(AfterElytraEvent afterElytraEvent) {
        this.getMode().getElytraListener().onAfterElytra(afterElytraEvent);
    }

    @Flow
    private final void onPreSync(PreSyncEvent preSyncEvent) {
        long l = ab ^ 0x2F5D2729480AL;
        long l2 = l ^ 0x3A0D9F9F59DFL;
        this.getMode().getElytraListener().onPreSync(l2, preSyncEvent);
    }

    @Flow(priority=-20)
    public final void onMove(@NotNull MoveEvent moveEvent) {
        long l = ab ^ 0x3EDAD130FDA9L;
        long l2 = l ^ 0x7257A62054D0L;
        Intrinsics.checkNotNullParameter(moveEvent, "e");
        this.getMode().getElytraListener().onMove(moveEvent, l2);
    }

    @Flow
    public final void onAfterSendPacket(@NotNull AfterSendPacket afterSendPacket) {
        long l = ab ^ 0x2E17FF3D98DEL;
        long l2 = l ^ 0x473F844A2822L;
        Intrinsics.checkNotNullParameter(afterSendPacket, "e");
        this.getMode().getElytraListener().onAfterSendPacket(l2, afterSendPacket);
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        long l = ab ^ 0x836B804102BL;
        long l2 = l ^ 0x5CA8F34E89A2L;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(receivePacket, "e");
        this.getMode().getElytraListener().onReceivePacket(receivePacket, l3, (char)n);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        long l = ab ^ 0x3F437A1E3FB5L;
        long l2 = l ^ 0x38196E7BE720L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        this.getMode().getElytraListener().onPlayerUpdate(n, playerUpdateEvent, (char)n2, (char)n3);
    }

    @Flow
    private final void onFireWorkVelocity(FireWorkVelocityEvent fireWorkVelocityEvent) {
        this.getMode().getElytraListener().onFireworkVelocity(fireWorkVelocityEvent);
    }

    @Flow
    private final void onSetPose(SetPoseEvent setPoseEvent) {
        this.getMode().getElytraListener().onSetPose(setPoseEvent);
    }

    @Flow
    private final void onReceivePacket2(ReceivePacket receivePacket) {
        long l = ab ^ 0x2FE0DC8A180DL;
        long l2 = l ^ 0x6887B999B4B2L;
        this.getMode().getElytraListener().handlePacket(l2, receivePacket);
    }

    @Override
    public void onDisable(long l) {
        this.getMode().getElytraListener().onDisable();
    }

    public final boolean isWearingElytra() {
        return Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA);
    }

    public final boolean isUsableElytra(@NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "stack");
        return itemStack.getDamage() < itemStack.getMaxDamage() - 1 && itemStack.contains(DataComponentTypes.GLIDER);
    }

    public final void disableWithReason(long l, @NotNull String string) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x4AEBE022A487L;
        long l4 = l2 ^ 0x20D1F8E49573L;
        Intrinsics.checkNotNullParameter(string, "m");
        if (((CharSequence)string).length() == 0) {
            this.disable(l4);
        } else {
            this.disableWithMessage(string, l3);
        }
    }

    public static void access$disableWithReason(ElytraPlus elytraPlus, short s, String string, char c, Object object, int n) {
        long l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n << 32 >>> 32) ^ ab;
        long l2 = l ^ 0x21063AB2B505L;
        string = "";
        elytraPlus.disableWithReason(l2, string);
    }

    @NotNull
    public final String translate(@NotNull String string, @NotNull Object[] objectArray) {
        Intrinsics.checkNotNullParameter(string, "key");
        Intrinsics.checkNotNullParameter(objectArray, "args");
        Object[] objectArray2 = new Object[]{objectArray};
        return ModuleMessages.i18n$default(this, string, objectArray2, false, 4, null);
    }

    public final void disableNeedHeight(long l) {
        long l2 = (l = ab ^ l) ^ 0x37587E127DFDL;
        Object[] objectArray = new Object[]{String.valueOf(this.getInfiniteMaxHeight())};
        Object var13_4 = null;
        int n = 4;
        boolean bl = false;
        Object[] objectArray2 = objectArray;
        String string = "need-more-height";
        Module module = this;
        this.disableWithMessage(ModuleMessages.i18n$default(module, string, objectArray2, bl, n, var13_4), l2);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && INSTANCE.getTwoBee();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && INSTANCE.getTwoBee();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == ElytraPlusMode.PACKET;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == ElytraPlusMode.PACKET;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getMode() == ElytraPlusMode.PACKET;
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getMode() == ElytraPlusMode.PACKET;
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getMode() != ElytraPlusMode.BOOST && INSTANCE.getMode() != ElytraPlusMode.INFINITE && INSTANCE.getMode() != ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK || INSTANCE.getMode() == ElytraPlusMode.PACKET && INSTANCE.getVertical();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getMode() == ElytraPlusMode.CONTROL || INSTANCE.getMode() == ElytraPlusMode.PACKET;
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getAccelerate() && (INSTANCE.getMode() == ElytraPlusMode.CONTROL || INSTANCE.getMode() == ElytraPlusMode.PACKET);
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK;
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && !INSTANCE.getTwoBee() || INSTANCE.getMode() == ElytraPlusMode.CONTROL;
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getMode() == ElytraPlusMode.CONTROL || INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible23() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible24() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && !INSTANCE.getTwoBee() || INSTANCE.getMode() == ElytraPlusMode.CONTROL;
    }

    private static final boolean settingVisible25() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && !INSTANCE.getTwoBee() || INSTANCE.getMode() == ElytraPlusMode.CONTROL;
    }

    private static final boolean settingVisible26() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && !INSTANCE.getTwoBee();
    }

    private static final boolean settingVisible27() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && INSTANCE.getCruiseControl();
    }

    private static final boolean settingVisible28() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && INSTANCE.getCruiseControl();
    }

    private static final boolean settingVisible29() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST && INSTANCE.getForceHeight();
    }

    private static final boolean settingVisible30() {
        return INSTANCE.getMode() == ElytraPlusMode.CONTROL;
    }

    private static final boolean settingVisible31() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible32() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible33() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible34() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible35() {
        return INSTANCE.getMode() == ElytraPlusMode.BOOST;
    }

    private static final boolean settingVisible36() {
        return INSTANCE.getMode() == ElytraPlusMode.INFINITE;
    }

    private static final boolean settingVisible37() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible38() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible39() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible40() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible41() {
        return INSTANCE.getOffHandFirework() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible42() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible43() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible44() {
        return INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible45() {
        return INSTANCE.getBoost() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible46() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible47() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible48() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible49() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible50() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible51() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible52() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible53() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible54() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible55() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible56() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible57() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible58() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible59() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible60() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible61() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible62() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible63() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    private static final boolean settingVisible64() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed() && INSTANCE.getMode() == ElytraPlusMode.SILENT_FIRE_WORK;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ElytraPlus.ab = ZkmKeys.a(2360879178904688583L, 1468243867425369021L, MethodHandles.lookup().lookupClass()).a(140349415067031L);
                        var20 = ElytraPlus.ab ^ 105824399821278L;
                        ElytraPlus.hb = new HashMap<K, V>(13);
                        ElytraPlus.zkmSetFlag(new Module[1]);
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
                        var18_3 = new String[200];
                        var16_4 = 0;
                        var15_5 = "\u00ff0\u00f8\u00ca\u00b2\u00a6\u00d6\u001f\u00e8-$hK\u00b1\u00b6u\u00ef ^=\u00bf\u00d4\u00e1\u00b7\u00d8S\u00c6Y\u00c7\u00ab\u0092\u009a\u0010\u00be\u00f6\u00dev\u00d8a\u00bb\u00d7\u00bdQ\u00cc\u0003\u001e3\u00a3\u0098(u\u000e\u0012\u00fe\u00fc\u00dd\u00b9;;\u00d9\u00cb\u00a1\u00ff1\u00d9\u008cN\u00b8\u00e7\u0016\u00e7\u00da9-\u0007\u00b6M\u0003\u0084\u0084\u00d36\u00fe\u00b5q\u0085n\u0081\u0080\u0000 \u00bd\u00ce\u00b3J\u00f3\u008e\rL\u0081\u0012\\83\u00e3\u0088\u0016\u0010U\u0092h\u00d3L]E\u00a7v\u00d2t\u00dd\u00b2\u0005`\u0010\u00c6]\u00d6\u00c265R\u00bb\u00dd\u00cb\u009f\u00c1\u000e%u\u00a5 \u00cd\u009b\u00e2P\u009an\u00a3u\u0088\u001ak\u00ac\u00ff\u009av\u0089P\u0082!\u009fq\u00de!u8\u00d4\u00aa\u001e\u00b3O\u00a1\u000f\u0018{(\u0080\u001d\u00f6\u00f0\u00ab\u00a5B\\\u00f8\u00e1I\u00d7\u00f6@\u00b7\u00fb)\u00b4\u0010P\u00cfC H\u0010\fO\u00e4\u009a\u00dc1\u00de\u0007\u0011\u00be\u00055d\u008d3\u0091\u00c8\u00d2\u00d5\u00a4\u00e1\"8\u009f\u0097\\\u00989\u00d4\u00c1\u0010\u00fb\u00a9\u00ea\u00fb\u000f\u00e8\u00ab0@\u00a54\u00d0\u00a9\b\u00c1\\ B\u009d6\u00c9\u00e6*\u0011d\u00c8\u00fe`(O|[7O_\u0005\u0095\u0011\u00a8l\u000b\u00e6\u00ef{\u009e\u00ce\u0012[3\u0010\u00c9\t)\u00cb8\u00be3\u00ee\u00e5\\\u00ab\u00d7na\u00d0j j\u00f8\u00c8\u00f0\u009evb\u00f0\u00e9\u008b\u00baU\u00e3E\u0002\u00ca\u00d6Z\u00c0\u00ad@\u0083\u00a9\u00a1\u00db \u0018\u00d3\u0012\u00a6\u00996 *\u009c>h\u00faS\bi\u00fb\u00ed\u001b\u0016-\u00aa]B}C-\u001e\u00c3`t\u00e9\u00b1\u0014\u00c9\u009b\u00be\u00f1hX\u0018\u00f1\u001en\u0083\u0012\u0011\u0014\u00a6\u008dt\u00ff\u00d3@\r\u00fe\u00f45r\u0098\r\u00f1!\u0091:\u0018\u0083A\u009f\u00e5\u00f7\u00c7Z#X\u00b8e\u0014z\u00a0\u00f4\u00b8\u00c1\u00fc\u00da\u00f3\u00de\u00b9\u00d0A m*\u00a8\u00ebu!\u00ae\u0095[\u00c1\u0005q\u00e0}\u0096\u00ee\u00f8\u00c7\u0015j\u007fz%\u00e7K\u001fq\u0095\u0095\u0092H! \u00ee;Wl\u0002\u00b2\u00e8O(\u00c6\u009a\u0006`}r\u009e\u00c2\u00bew\u00ed\u0011\u00cd\u00b4\u0000\u00a7\u00a74\u00e4|\u0087\u00c6; \\\u00b1}\u0087\u00fd\u00f0;\u00ea\u00e3\u00e4u\u00c2\u00b0\u00fc\u00a5\u00a7K\u0019\u00a3\\\u00af\u00f3\u00bc\u00b7E\u00aa\u008d\u0094\u00ff\u0092\u00ed\u00a6 \u00f9p*\u00db\u00fb\u00db\u000b_UJ\u00dd\u00a9}\u009cr\u00db\u008a\u00b4W{~\u008a\u00a8\u00fa\u00e5\u0098\u00da\u00f9\u00c4A\u0087\u00e8\u0018o\u00d1\u00cd(Y\u00f7^\u009d\u00f7\u0085(/\u00aao\u00f5\u00aa;)H\u00f5\u00bc\u00d8\u00e9L\u0018\u00bd]K\u0081\u00ce\u00dd\u0019\u00e1%\u00ae\u00d3G\u00f9PINg\u00fb\u00e1\u00dae\"]\u009e\u0018\u00ae\u00ca\u00c3\u0006\u0003\u00f7\u0081K&\u00b6\u0092],\u00872v\u0006\u0014\u00c4:\u00f1m\u00c74 W\u0016\u009b7\u009dQ?O@J\u009a\u00fb\u00ca\u00c7K\u0003.\u0086\f)\u0084\u00ff\bv\u00ce8;\u0083\u001aC\u00bd,(\u001b{\u00d4\u008a,\u001bfo\u001e\u00ae\u00b1\u00fa\u00ff\u0095\u0018A\u0080\u00b4\u00ed\u0091\u009a\u00b2C\u00ec/m\u00af\u0007\u0084\u00be\u00d7\u00e3\u0004\u0000'\u009b\u009c\u00d3\u00f880\u00d3]\u008fy7h\u001a\u00a6\u00a23\u0082|\u0090\u0091\u001e\u00bc\u0019 \u00f6\u00a76\u00b7c\u0082<\u0093>H\u00b1$\u0096C\u00f0\u00ad\u00e5XE}\u00c8X\u00d1\u00d1\u00e8\u0019@\u00bd\u008dU(K\u00e9_\u00f8j\u0006\u00d3\u0087L\u00dc\u008f$\u00c6*T\u0016\u00f9-\u00f5\u00ac\u00aa\u00e9\u00d1p\u009cw)\u00d9\u00b3\u00e4\u0095\u00af\u00bfm\u0099\u00ba\u00c6#R\u00f6\u0018n&$\u001f\u00d0\u00a9\u00e3yr\u000bbvD\u0016\u00d3#\u0097\u00a6~\u00dc\u00fa\u00dd\u00e7b \u00f2P1\u00fb\u008d\u00e3F\u0080I\u00f7r'\u009c*\u008a\u00c3\"\u0004\u008d\u00e9\u00edl\u00eb.R\u0099\u009f\u00bd <r\u00c1 \u0095\u00c5L\u009b\u0095LoF\u00c3E\u0088GTP\u0012A3\u00f11Rj`\u0091\u00da\u00df\u00ffC[\u0095\u00a4\u00cc\u007f\u0018v\f5\u00f7\r-a\u001c\u00c3W\u000e\u00ee\u00b1R\u008e\u00e3\u00e1\u00f4%\u00ad\u00b2\u0006>f\u0010\u00bcY\u00dba~\u00e4\u00d5&\u00b9;<\u00c4+\u00e2\u00c5\u0002\u0018s\u00c4\u009d\u0012\u00fc\u00e2\u0019}\u000e\u00a4\u001d\u0092,-\u00b2\u009c9\u00e5K\u00ad#^\u00fe\u00cb(\u00ea\u00e0\u00fb\u0019\u00aa\u00ed\u00ce\u0085\u00ab\u008b\u00d6\u000f\u00c1\u00a7\u00d5\u009fs~\u00f6g\u00d6&\u00f68\u008a\u00e6\u001c,`\u00b6\u00b4\u00cd\u0080=)\u0080\u00d8:#l(`\u00c8 j1\u00d3\u00c0\u00c1\u0005\u00fc&9\u0083\u00d6\u0087\u00fd\u00e7\\\u0018@Z\u0005B\u00f3\u00ec\u00b4(\u00e7\u009eU\f\u00d6\u00b4\u00fcN\u00e9M\u008b\u00ff\u00b2 \u0001\u00d9\u0013\u0083d{w\u00b7!\u0093RMN\u00fe\u008a%\u00b4\u00d1\u0098\u00f9\u0094x\u00f2U\u00ca\u000b\u00f6\u0080^a\u0085\f 5B\u00ee\u009a\u00ab\u00f7{\u008c\u00a6\u00dc\u00d7\u00f0\u00c3\u00b3\u00da5e\u0086\u00b9\u00c2\u00b1\u00d3\u0085g\u007f\u00c0\u00e8G\u0001-\u001b\u00d7 \u00d8\u00c9P\u0096\u001fa\u00b59\u00ce.Z[\u00bd\u00c6b`]K\u00d5\u00bd\u0001Y\u0092s\u00ed\u00cc\f4D\u001e4\u0081\u0018E\u00d3\u0010i\u0083\u0014\u000fq\u00c7\u00f1\u0082\u00a19\u00d8x\u0019$\u00fb\u00be\u00acl\u00d6:\u00c7\u0018\u00d42\u00cd}\u0084\u001b\u00a6\u0094fY\u00b5\u0085\u00c4\u00f8,\u001b\u0096)N\u00c33\u00db\u0092b0\u00f2\u009ex\u000f;\u00d5\u00b2\u00e7\u0084l\u009e8\u008d\u00df\u00a7b\u00a3~\u00fcy\u009d\u001d\u009af\u00b5\u00c6\u00ff\n\u0094\u008f\u00dd#J\u008e\u00d2|M\u00d1d`8A@\u00e5[\u00b3\u00f6\u00c5(LPK\u0093(;\\Q\u0083-P)X\u001e\u001b\u00e55\u00a5\n{\u00fb\u0091\u001f\u00day\u00f9#\u00ecd\u0082\u0004\u0084\u00a9AI\u00d9\u00ebW\u00ad\f\u0010\n\u0084\u00b2'\u00cf\u00d9\u00c5\u001f\u00d1\u00ac2][\u007f\u0099J \u00deJ\u00ff\u000fe\u0017\u00de\u0000\u00c73\u0091'\u00f3\u009c\u0088X\u008fO\u008d$Ca\u0098<\u00a1\u00e8\u00d4\u00ee\u0004\u00a4\u00fb\\ \u00f8+\u00cc\u00a3\u000f\u00f4\u00bdMDC\u00f6l\u0001\u0015\u00a7\u0018\u0085\u00e5&'\u00d4p\u00af\u00e4\u008bo!'\u00a5\u0091\u00e2\u0097 F\u00ca\u00a5\u007fx\u00b8\u0093\u001dQ\u0098T\u0081\u00a3:_\u0019\u008fnJ\u0099g\u00fb\u00b9\u00a5\u00ec\u0002J\u00ad\u00bc\u00c1W\u0094 t\u00f5Jk\u0000\u00f6UH\u0081#\u00d8oZ\u008fI\u0098\u00d0Z.\u00fb\u0011\u00cf\u0018\u0004\u0096\u00dd\u00d9~\u00be\u0094\u00ccc\u0018YY\u00a7\u0086\u00c6l\b\u00c8\u007f\f}\u00fayK\u00a7{\u00dc+\u008b\u00c9\u0089\u0090V\u0003\u0018\u00c0\u00d5\u00ae.\u00f6\u0085\u007f\u00a3\u00a8\u0094MD\u00f9T\"#[i\u0007T\u00f3\u0013{\u00faH~#\u00c9\u00a5\u0083g\f\u00a7\u00b5\u00d3\n\u001f\u0091\u00c7\u00ca\u00a9\u0083\u009f\u0086\u00b4\u00d2\u00f2\u0084\u008eU\u00ca\u0091\u000er\u00f8\u0014N('g\u00b1i\u0004\"\u00c5\u0006[\u00d1}\u0007y\u0080\u00f0\u008ePw\u00f83\u00fc\u00afz\u0017y\u00b9\u00b5\u00ac~\u001c^\u0082\u00d2#\u0001+\u0010T\u00ef\u00184\u0006\u00d0\u009f\u00e5\u00b1\u00bc\u00d3A\u0083{S\u00ff\u009d\u00c0c\u00e7\u00a5\u00e7B\u00bc{/\u00b3 \u0094\u008f&\u00db\u00d0!]3Eesi\u00cfS\u00b6v\u0019\u00ab\u00b8o.\u001c\u00d3\u00c3K\u00af\u0002hL\u000f\u00bfz(\u000b\u00cb\u0092 \u00e8?\u001at\u00e2\u00f4\u009b]\u008b@\u0017Y\u00acT\u00d3\u00e8\u0096\u00f9\tp\u00cd~\u00d4\u0086\u00a2\u0091+#\u00c1\u00c6\u00e4\u008a\u00e6a\u00bd\u0005\u0018\u0096V\u00e5\u00c7\u001a\u00a3\u00ecK\u00cc\u00ec\u00d3pC\u0093\u00bf\u00862(\u00a9\u0085u\u00c4\u009f\u00ec %dRg\u00bf\u00df\u001b\u00f2\u008e\u0006\u00d5\u00e4oM\u0091g\u0080*\u00b7\u00d9\u0089R#1\u00e8\u0092z\u008c^\u00bc\u00a0\u00f1\u0018\u00b0\u00ce\u00d9m\u00b0_]\u0098`~l\u00c2\u00e9[\u00fbx\u00fa=\u008b*9\u0011\b`0\u00d5.\u0084j,\u00d4\u00048\u00ba\t\u00ef7*k\u00baD\u001f\u00ad\u00d9\u008ch\u00944]\u009f\u008e\u00e9y\u0013\u00b6\u0089b\u0000\u00ff7\u00e8x\u0098\u00fa)\u00eb\u008a\u00b0\u00e5w\u00feu\u00fa8\u00edM\u00c5\u00ad\u00aa\u008c\u009f\u00b1E\u001b\u0094NR\u0088\u00cckQ\u00a2\u00a8\u00f3QG\u0097\u00fbR\u0087\u00dc\u0099i\u00e7 \u00b1\u0083\u0085\u00fe\u00ed\t\u00e4\u00df\u001b\u0085cH+0\\\u0010\u00e1\u00aaW#\u00b6\u00b9SD\u00b5 \u008d\u001e\u001f\u00b91i\u0017vh\u00c1\u00d9Q/\u008a\u000e~\u00f1K\u00d3\u00f1<\u0088_(\u00d0\u00f1\t\u00c8\u00b6\u00b5>\u00b7 U-\u00a4\u00d9D8r\u00d9\u00f6a\u00ea\u00ae\u00fajn_\u00e5\b\u00cc\u0003\u00a3\u00037#\u00deo\u00f4\u00ee\u009ageE(\u00a1\u00c7\u00ae\u00bc\u00f0b\u0010NZ\u00e6t\u009b^]\u00d0vQ\u00ac\u00detVUy{&\u001fD3G\u0087\u00d1H\u00fa\u0099g\u00f41\"\u0003y \u008dR\\']\u00e9}ALuR\u00b9l\"\u009c\u00b5\u0084;!\u00da\u00cb\u0001l\u00c5\u0005h\u00f1\u0085\u00b4#\u00a77 \u0088\u00d6\u008e\u00d7I}\u00cb\u0011\u0091\u00d6\u0088\u00c0\u00d3\u00df!!\u0095\u00d0\u0094CG(f\u00ad\b9o\u0012\u00e3N\u00cc\u0015 0)\u008a`\u00ed\u0018\u00ddw\u0096ap\u0091\u00d5\u0095\u0083V\u00ff\u00e3\u00fc\u0083\u00ce\u00dd\u00ad\u00a6\u00a4\u00ddi\u0082C[\u001b\u00ef(j\u00e8\u0017\u00b0\u00f5\u0006\u00c6\u00d4\u00b2\u00dc\u00cd\u009e\u0000\"\u009b\u00d7Lzz\u008b\u0019\u00f8\u007f\u0094oDx\u00e1\u00dd\u0082AxY\u00da\u00dbg\u00f6 \u008a\u00ae(9\u00ae/\u00ed\u0093\u00cd6\u009f\u00f4\u00b8\u00a5(\u0015L\u007f\u00fd']i\u00db\u009fOG\u00a6\u0089\u00bd\u00ab\u00c1\u00a5\u00cd\u00076\u001c\u00e6G\u00e9X\u0014sr\u00181\n\u0010\u00c8\bW\u000b\u0084{v\u00d0\u00c0U\u00fb\u00da\u001cW\u00d1Yz)\u0092d\u00e1 \u00f9\u009c\u0019\u008d\u0000\u00e6\u00d2\u00e7\u00c1u\u00c52Z\u00e6\u00b6J\b\u0000QfHD\u00d2\u000b\u00ca\u00d5\u0088\u001a\u00a2\u00d1\u00d4\u0017 {\u0083\u001e3\u00b0\u00cf\rJ2\u00a4b$c\\\b\u001c\u00c4\u00f4\u00deT\n9\u00a2C\u001b\u0012\u00c1[\u00b9L\u00d4! \u00f2!\u00f5\u00e3\u0000\u00dd2\u00de\u0093\u0018\u00db\u00a7O\u0012\u00f4Ul\u00c3\u00ec\u00af\u007f\u00bd<\u0006s\u00c5\u00f0\u00f5R}Y\u00ba(\u00e1\u00b6\u0090\u001f\u00b8\fT\u00f6\u00ce\u0005\u00c1\u009c\u00ea\u008a^\u00e9v\u0084v\u00e5\u0007&\u00a9\u00aa\u00b2}\u00b3\u00e3X\u00d6X~\r\u0003\u00cdx\u0017\u00faGB\u0010\u00dc@\u00a9\u00a5\u0017\u00c4I\u0017\u0019\u00d2\u00ba\u00e2\u00f1\u00cf\u00b0D n\u00b3\u00fa@\u0096\u00c8\u001ayD\u008f\u00a3\u00c1J\u0091C\u00ed\u001d\u0014,\u00d6\u00cf\u0007\u00a9\u0000(\u00d0\u001b\n]\u0087?= ^O\u008e_j\u00ae\u00b5\u00ec\u00db\u00b8S\u0081j\u00bc\u0007\u00a7t\u00dd\u008f\u0089u\u00f8s\u00c3+\u00dd\u00cao\u0088\u0085'\u0097\u0018n\u0080\u00c7\u00a9\u00cd\u00a2d\u008c\u0086kL\u00a8\u0094~\u000b\u00c4\u00d1\u008d30A$\u00d2\u00c9 }\u001e\u00b0\u00ce(\u001b\u00a4\u00e4\u00ae\u0000\r\u00d6\u00e0\u00d4\u0094\u009c}\u00c2\u00a1\u00e9^\u00d7\u009aV\u00b8\u00d5\u00893\u00e3+\u00a4]\u0010\u00f8\u00a2\u0095K\u00ac\u00a6@\u0095\u00fd\u001e%\u00e1\u00d41\u00a3\u0001\u0018L\u001b\u00a1\u0004y\u00a9\u00c1\u0002\u00de\u0094*\u00favX\u000f\u0017p1\u00f0\u00e3\u00c9L[\u001e\u0018gS\u0005\u00dd\u00ae\u00d2F\u007fY\u00bbw\u00f2\u00b1D\u00e5t\u00bbd\"\u00a9;\u00e9W\u009c\u0018\u00ac\u00bf\u00f21&Z\u00ea\u00c2 u>\u001c\u0003KlH\u00fc\u00d6F\u00cb\u00fe.\u00b4\u0080\u0018]\r5\u0082\u00da\u0096,\u00c4:,0\u00f6\u0010\u00ce\u001c\u00ecQ\u000frU\u0090\u00ec\u00a1[(\u0012qx&\u00e8\u00ecD\u00fe\u00cf\u00d6\u001d\u009f\u00b1\u009d\u0086\u007f\u00fa\u00b3S\u00a7>\u0088m\u0097\u008c\u00da\u00a1\u007f\u00b1&\u0089\u00f8\u00a0\u00bf\u008f\u0099\u00e23\u0082~ \u00f4\u00ea\u001f\u00f1\u0016\u00ff+/\f\u00b4\u009c<Ija\u0091w\u00c6s\u00fd\u001eb\u00ab?&\u001a\u00b0\u00ee\u00df\u0006\u0011\u000e \u00f4\u000b\u0019\u0086\u001e\u00ad\u00f0\u00e8p\u00ee'\u00a14\u00db\u009a\u00e0)\u00b92\u00d6\u00ba\u00ea\u00b9\u00d9\u0094\u00865\u00f0\u0011\u00c7\u00e0* \u0093\u0088,\f2\u009a\u008f;\u0098\u00b0\u00f0\u00de\u00d1;,\t\u00fb\u00beR\u00b5\u00a2\u00a8\u00f2G\u00ad?\u0007\u0005Rl\u00fdK\u0010\u000f\u00ef\u00c8\u00dc\u0091&\u0098\u001c\u00cam~\u00be\u00e6lF\u0001 \u008ae\u00ca\u00aaHA\u00f5\u00a0\u00b6\u00ebNCHv~\u00d1\u00ac\\\u00b2\u00fc\u001b\u000e\u00ae\u00b7V\u00d15I^T\u00f5\u00a1 \u00ca\u00b0asC8a\u00eb\u00ce\u00be<R\u0002\u00e6\u009239\u00a7\u00be\u00dc;\u0093\u00ae'\u00f9\u00c1\u00e8V\u00e6x'X \u00ffP\u00b7\u00da%\u00a8H\u000b\u00c1>\u00b5\u0007I_\u00ef\u00e3\u00a1T\u009c\u00e4\u00a4\u00c8d\u00d8\u00b9\u00a3&\u00a2w\u008d\u00cb\u00a0 \u0081\u0091\u0011\u00e6Z\u00a8r\u00d2\u00d83\u00b5\u00f2\bcL~B\u00f3\u00fc\u0081\u0017\u001b\u00bd\u0087\\\u00ad\u00f9\u0081l\u000f\t\u008d \u00a7?\u0080$\u00fc[\u0017\u00b6\u00e7ln\"\u001d\u00b4K\u00ee0\u00d0x*\u0093V\u00d6\u00ba\u00ab\u001eI\u00eb\u001d\u00b3\u0012\u00ae o\u0012\u0015\u00f9\u00de\u00daT\u009e\u00e9\u00b9\u00a29l\u00b3Om]\u00e908}\u00c0\u0015\u00c97\b-Bk>(\u00e2 \u001c\u0090_13\u00b0]/\u0007k^\f\u00c1\u00cc\u009dI8\u00ad\u00a4\u00fcVi]\u00cag\u00dev~\u00b5}\u000b\u00ba \u00ed,UG\u00d9\u00d16\u00c8\u00a5\u00e9T{u^\u007f\u00a8\u00db\u00c4\u0090\u0086\u00ba\u00c1\u001d\b\u00dc\u00aa\u00adA \u00a7\u00acm 5\u008c\u00b2h\u00a5\u00a5\u00bb\u00a8\u00b0\u00aes\u00ab\u00b0\u0095\u00fd\u008ed\"\u00e8{/\u00e9\u00ac\u00a5-@\u00a5\u00ef\u00c3\u00171\u0084(\f\u00de\u00a0\u0007\u001d\u0095\u0003\u0081D\u0016\u00c39;G2\u00cez\u00dd\u00b7=\u00cd\u0090?\u00a4\u0098\u0095\u0002?\u00b5\u00b8:\u00ab{bj\u00bf!\u00cc\u0099\u00000DZ\u00ebWlVL\u00c04\u00c5D\u009a\u0002V)\u00ab8m\u0010\u008a`m\u0003\u0017<\u0000)\u00c80\u00eb\u00df\u0089\u0012s\u00df\u00925\u00ed\u0016\u0011D\u00fe\u00c0\u00f8q7\u0092\u0094 0\u00ca\u008a\u00c5fI\u00c7\u00e6>\u0088#\u00af\u00b7\u00e1\u0019\u00d1r\u00fc\fQQ3\u00a5K\u00f3\t\u0000\\\u009c\u0097\u00c3\u000e\u0018k\u00c5\u00b8\u00f7\u00cc\u00c5+\u00bc\t{][\u0004\u00bbeZ\u00c2X\u00e6W\u00a3\u00ea|\u00e2\u0018\u00b7p\u000eI>\u00c6\u00e8'\u00ad]C\u00b47\u00a9\u00bd:>\u00a6R\u00d9f\u001e\u001a\u00c8 %\u00ed\u000b=\u001a\u00a8\u00ee-\u00bc\u0091&\u00c6\u00f9\u00819\"\u00b0P\u0093\u00bf\u0088\u0096@\u00c2Q\u009b.\u00d6\u008a\u00bf\u0017\u008f(\u0088\u00acO\u0080\u00d3\u00f1\u00f6\u00b3Yu\u008ep\u0084E\u00b6\u0004\u00a4\u0098\u00fc\u00a0a\u00ea\u008d\u009a\u008axG\u00d0\u008aN\u00d1_\u0098\u00b8\u00a9\nF\u00fa\u0093\u00d0\u0018\u009et\u00da\u0001\u00c5\u00abh\u00d2\u00b2,\u00d8\u0016\u00cd\u009fi\u009c&\u00d5\u00b8R\u00a4\u0016\u0087\u00a7(%\u00b5\u00f8\u008c\u00b3u\u00f3w.\u00ed7^\u0094\u00d9\u0080\u00db,l\u0019T2\u00db<7\r\u009f\u00c33n{t\u0096\r\u00cd-\u001e\u00e4\u00cc\u00cc\u009a(\u00d0\u00d7\u00b3b)\u00d2\tq|\u00f1D\u0016\u009b\u00f3\u00c3(\u0091\u00c7\u00bd\u001bq\u0014N\u00eevH\u0090\u00fa\u00adEq7\u00b0\u00cdn\u00f8\u008a\u00d0[\u001c\u0018\u001b\u00df\u00c3\u00c3\u0019#\u009c\u00e1\u0011{\u00fe>..\u00e2j\u00b6\u00cc\u0099\u00ca\u0007\u008d\u001d\u00ba \u00c9\u000e\u0001\u00194\u00d4\u00eeCv@T\u00cdz\u00f7A\u00df*u\u00ea\u00b1\u00e0\u00e6\u0081N\u00e5Q\u00c1\u0014\u00a2\u00ff\u0098- \u00a9c\u00b3\u00d6\u00b9\u00ab\u00de\u00cc\u00ab\u00e6\u00e9`o\u0019_\u0099\u0081\u00ed\u00b9\u00b0xz\u00f4\u00bbt\u00ac_Gq[w!\u0018\u00ac\u0094MW%M*p\u0006\u001c\u0001\u001f\\\u00f6=\u0082V\u001a\u001b \f\r\u0005&\u0018?+\u0016\u00fc-\u00bd\u00ec\u00d9DmZ\u00147\u0001\u00e3K5';\u00f0\u00b4d:\u00a6(\u0090\u0093\u008cC;\u00bf\u00ef\u00a6{\u0099\u00e6\u001bH\u0001\u001b\u00ee\u00d4\u00da\u00af\u0011\u001a\u0098\u008e\u0097\u00f9\u00eb'\u00c83\u00b0\u00d8\u0088;\u0096\u00d0p;Y\\\u0088 \u0080\u00de\u0010d,i\u00d4'E\u00149\u0011\u00ed\u00c2\u00d6t\u0088\u009fQ\u0007\u00d8\u00c0A\u00c9lo\u00db\u00c5\u00deZ\u00903 SG\u0002\u000en\u00d5`\u00c1\u00f3U\u00b8\u00c1\u00a1\u00da#\u00d6\u00ae\u0080\u00d7r1G\u00bb\u00c3\u001e\u00f1\u0086x\u00ff\n\u0098K\u0018\u00a4\u0097M\u0082\u0087<t\u00a5\u0089\u00d9\u00e8k7t\u00ceC\u0088\u00fe\u00b8\u001f\u00df\u0013?\u00d6\u0018\u00f3\u00ea\u0002\u00db\u000e7\u009df\u001d\u00b2\u00c0\u00c7\u00181\u00a4A\u00a0q\u00b9\u009f\u0018\u0089LN(+\u009c>3\u00e66\u00f32Q\u00c7\u00e3\u00a9E\f\u00a3G/N3\u00ee\u00db\u00c8.\u0001\u00e4\u00ce\u00bc\u00a3\\\u00f2\u007f\u00d6Q\u00e7h\u00df\u00b2\u001f\u0002\u00c8(\u0014\u00a2\u001eg,Tg=_\u00a9\u00dfe\u00aa\u00b3\u0011\u00c9\u009eZ:[(\u00d19\u007flK\u00a6\u000e\u00b2\u00ab\u00d7%\u00df\u0081\u00eao\u00cf\u00ef\u0088: O\n\u00a10\u001c!\u00b7C&\u0018\u00bf\u0092F\u00b9\u0093>M=\u0094\u00ca\u00c5\u009d\u00b2S\u00d3=\u00fc\u00d7\u0006@\u008d\u00d5 \u009a\u00c4\u00e2\u00f7\u0084\u00e37\u00b0\u00c8\u00f7]q\u00fdX1\u00f1\u00a7\u008c\u00a4\u00f4\u0001\u00e6J\u00c0[\u0012\u00bc\u009a\u00f4\u00d2\u0000\u008e\u00181\u00bf\u008du\u00a4\u00d8\u00c0\u00f8,\u000b\u00ad\u009d\u00b4\u00fe\u00a3[\u00a9\u0097\u0089\u0092\u00c8T\u0004-\u0010\u0083AR\u00e2\u00fb\u00f9\u001d\u00a4&\u0080E2=\u009aE\u0013(\u00ebsH\u00ed!FtW\u0013\u00a5\u00a31g~\u00cdm|\u001f{W^1\u00d4\u00adY9k\u00e2N\u0092$x-\u008d\u00e7\u001bB\u00fa\u00abY \u00c4\\+\u001e\u00b1\u00f6H3\u00d5\u001c[\u00d1\u008ec\u00f4g\u0095\u0011\u0086^Q\u00fdo&\u00cec\u00c8\u00b9[)K\f 1c\u00d2\u00f6d\u0004\u00ec\u00e8\u00b0\u00fb\u008fE\u00f58.\u00f6\u009c\u001d~\u00a4*\u00a6\u00c81\u0091a\u00e8\u0097\u008b*EV \u00944Q-o-\u00e5\u009am\u00fc\u0090\u009f\u00d5Ag\t\u00ef\u00f2\u00fe[\u00e4\u00fe\u00ae\u00f9\u00a5\u00cf\u001f5\u0002\u009fw\u00f5\u0010N,\u00c4\u00c8\u00da\u0092\u00b8\u00f7c\u00cd\u0014\u00d4#\u00a3xm )\u0085\u00bdS\u00b2\u00af\u00dd\u009e\u00db\u00ec\u00b3:\u00010\u00ba\u00aeb\u00a0\u0005\u00ea\u00f58\u009eyY,\u00a4n\u007f\u0090\u00aa\u0082(\u00b4\u00e7:\u00ec\u00af\u00e9^\u00cd\u0096.\u00fa@\u0003\u00a0\u00ed\u001b\u00d2\u00e0F\u0000\u00f3\u00b4\u00921!\u00f9\u00f8\u0095\u00c7\u00db\u0011~T\u00cb)\u00ad\f\u0019\u009c5\u0018B\u0080wBi\u0015\u0018+\u0019i\u0099'\"\u00c2\u00ce#\u00a6\u00a7e\u00afQ>\u0016\u0004 \u008e \u0015\u00bc\u00159%\u00f6\u00af\u00d73\u00c1\u00df3\u009d*Q4\u0005\u00a4y\u00e8\u0003\u0095V\u00d8]\u0007\u0005L\u00b0\u00a9 I\u001f\u00d4\u00f9Y\u00d0\u009c\u00df\u00e8\u00f7\u00a01\u00c0\u0002H\u00ae\u00bb:^S\u0083\u00d4V\u00f662\u00f8/\u00bc\"\u00bd\u00eb\u0018\u00b8\u00b4\u00de\u0019\u00bb\u00e3\n\u001fS\u0014FM\u00a5\u00aee\u0094IP\u0013?\u00c6\u0098\u0086\u00a9 \u009f\u00a5F\u00c2\u00c1\\\u0018\u001cS;\u001dk\u00cav\u0089\u00d6;IA\b\u008bo;\u0096w\u00a2\u00a4Qv\u00c5r{ C\u00aa\u00cf\u0005q\u00fc\u00d4\u0000\u00c7PT\u00cc\u00d0\u00a4p+[\u00ca\u00a3\u0015\u00b8\\\u00b8\u0014\u008c\u008c\u0016\u0013vVan\u0018\u00bb\u0087\u00f4\u00b58o\u0001v\u00e2_\u00f9\u009c\u0004\u00106]\u00d8*K\u00e7(;\u00cb\u00a5\u0018\u00d9>\u000e\u00dek5\u0081y3\u008c\b\u0088}\u00b3\u00bd\u00f9*\u00a6\u008c\u001bjw\u0018\u0005 \u0014T\u008ch\u0002\u00fe\u00a1\u0002.~\u001aJ\u00b3\u00ddS\u0002(\u00e1\u0089\u00fd\u0000\u001fU{~gL\u00b8\u00d2y\u00b1\u001d(t\u00c7\u0000\u00e6}>X\u00ceF\u00b0y\u00ce\u00dcgg7\u00d0_|\u00cejQ\u00db\u00f0j$\u00b8ZSpD\u001cS\u00f7D\u00c7\u00d5\u00d4\u0017N(\u00b7P\u00cdU\u00c1\u00c1}v\u00c4\u0084(\u00cb_A\u000e\u00e5h\u00dd\u0099j\u0018\u00e3l\u008d\u001fd}-\u00f3\u00c9U\u00a3(\u00fa\u00ce\u00afaY<g\u0010@<\u00ebw\u0091'\u00b8jw\u00d4\u00eb\u00bf\u00e5\u00d4\u000f\u00d4(\u00b9\u0090j\u0090%\u00a5v8\u00b1\u00d5\u00a1\u0016\u008d\u0091'\u00a5\u001e\u00a7!\u009c\u0087e\u0006\u00d7AUt\u00c9>\u00d1\u0086m\u0010l\u0017\u0087\u00b2[i\u00f4\u0010S.\u009fsM\u00b1\u00ba\u0010.\u001ef(K\u00a1s\u00b7\u0018\u0085C\u00f1I`n\u001e\u008e\"\u00ae\u00bb\u0006\u00cd\u00c9\u00c3\u0016\u00ef\u00bdn\u00e4\u001a\u00d7\u00c1\u009b\u0010\u00ad\u00f9\u00ac\u007f\u00cf\u0013@\u0095\t}\u00f6\u00fd\u00e9\u00d9\u00e3?\u0010\u00b66\u00c3\u00a1\u00b7!s\u008f\u008e\u00e9^\u0012M\u00de\u009a\u00e7(\u0012\u00b84\u00c0=}\u00f7R\u00fc,9B\u00f4\u00c5\u00a0=H\u0089}R1\u00f80\u0090\u00af\u00e4.\u0095\u000f\u001f\u001d\u0085\u00ff\u0003\u00fe\u00bb\u00fbW\u00e3\u00f8\u0018\u00f3\u00a3\u00b2{~\u008b/a\u00f3,\u0088D\u00ee\u00ac\u009d\u0004\u00c7Y\u00c6k\u00de\u009fZg\u0018K\u000e){\u00f2\u00a2\u00c8Vg\u00be\u00c8\u00b0F\u0082_\u000b\u00d6\b\u00061\u0003\u000b\u009a  4\u00c6b\u00c4\u00a8[\u00d8\u0014\u00bf%\u00e5d\u00fc\u00b7\u00801o\u00b7\u0097\u009cnIr\u0007:\u00d9\u008b\u0015_\u0081\u0016k \u008fJ\u0005g1\u00fc\u00ca\u0015\t\u00f0Y\u00fb\u0002\u00bd\u00ab\u009d\u0005\u00b8a-\u00e7\u0090\u00fd\u00cc\u00db\u00a3k\u0002`\u00de\u00a4v ,\u0005c\u00fd\u00a4\u00b6h\u0002x\u008c\u00b6\u00ee\u00ae\t(G\u00db$\u00d7&\u0082`\u00cf\u00f2\u00c3\u0006c\u00a7z\u00bd(\u00b7 8\u00d4p\u0096\u00d6\u00a3\u0090\u00af\u00e5\u009c\u000b\u009c\u00fav\u00cf\u009a<W\u00c5a_\u00ed\u00f8`\u00c5nCX\t\u0090?,(n@\u0097^\u0087\u00cb\u00edb\u0013\u00a7\u00c7\u00c1$\u001bpE\u0012\u00ea\u00a6\u007fA8\u00c3\u00b3\u0099\u00b1\u0085\u008d]\u0089\u00d78\u00e9\u0095\u00eaHI\u0018r\u00fe \u009f\u0089!\u00f0k\u0017\u00e8x\u00ca\u00e1w{\u0001^\u00ac\u0086\u0005\u0097\u00baMY{\u00c9\u00c6\u00a7\u00b4:\u00d6\u00cc\u007fl\u00bf(\u00dd\u00ca/\u00ff \u009a\u0084\u00a9%b\u0087l\u0018\u00a1l>DBp5.\u00e2\u0014K\u00a8{+\u009b\u001d\u00cbE\u00fd\u0083\b\u00d4\u00f5UyU\u0018(4\u00ea\u00f5s\u00ad\u009cpBU\u00163k\u00ecH\u00b3Id%\u00db=13uC\u0092\u00bc?\\Fq\u000e%\u00145\u00d0\u0006Q\u00bd\u00a2\u00f3 \u00c1\r\u00d1\u00ed!\u00b9\u0005\u007f\u00a6wC3\u001f\\\u00bfx\u00af\u00f8\u008e\u00e4\u00cd\u00d6\u0085\u00b4\u00ef\u00d6\u0093\u008bS\u00a0\u0087\u00bc\u0010{\u00db\u0080\u00e9{\th`1\u00c5\u0016\u00d6n\u00b1\u00c6! u\u00f3\u00f5A\u00cf\u00d8g^\u008d\u000b7/1\u0083g\u00c4\u00fa0\u00c8\u009f\u00cd\u00ee\u008d6\u0094\u009c\u00baD\u00c2\u008f?\u00ac(}\u00fa\u00ecRj\u00b9\u00cb,\u00c2\u00a2\u0015\u00a3\u00a8s-\\m7'\u00f7U~\u009dh\u00e6\u008c\u00a0a\u00d3{>/\u00d3]F\b'\u001f\u008b\u001e\u0018\u00a1\u00beYN\u009b\u0098+\u00fc%e}\b\u0082\u00e5\u00ec\u0002\u00d7\u008fq\u001c\u008a\u0016\u00be\u00d9\u0018^\u00ac\u0001\u0094\u0010*iaOD\u009a\u00e7\u00ed\u0085\u001a\t\u00c2\u00edV\u00e7\u00f9<\u00aa\u00ef\u0018\u00baC*\u0015\u00a5\u0011\u00fb\u0084\u00d3\u00ca\u001d\u00bc\bZ\u00db\u00a0\u00ad\u00dbe\u00a9\u001a\u00af\u00f89 \u0017\u009d\u00eb.\u009a\u00b5\u00f5\u0003\u0088\u00bd4\u00ce\u00d4@nQ[\u009fPb\u0005\u0091\u00b0r'\u00a3\u0000m\u00916\u009d\u00e3@h\u00c0\u009dH@\u0093\u00a0:\u00b8\u00f6\u00d3\u00deG\"\u0092\u0099\u00d7\u00cd@P\u00b0\u008c\u0091\u00d6\u00c6\u00ec\u009b]\u0006\u00ae$ND\b{\u00e8=\u00bc\u00f1\u00fc\u0017~\u0088\u00cfl\u00ce7\u00c9\u00b5\u00d8i\b\u0003S\u0082\u00a6\u00a4\u00e0\u00ac\u0088\"\u0097\u00ed\u0085\u0010\"\u0097g\u0085\u00f7\u00de6\u0098t\t\u009c\u001e\u00f4Ic_8dz\u00d5j\u009dh\u0010\u008c\u0089\u00a7L\u00d1\u00e4\u00bfyL\u001a\u00c5\u00cab_\u0098B\u00c8WFQyV\u00fd\u00a3\u00d2\u00aa\u00edwz\u00d8ry\u009f_D\u00b08\u0000\u00d5\u00e8(`<\u00b6\u0001\u00a7\u009f/\u00a2 \u0017\by\u007f\u0081\u00cf\u009d\u007f\u00ce\u00c7\u0018b\u0010\u0086\u009b\u00c8.+9\u00bc\u0091s\u0016\u00de\u00d5!\u00b8x\u00d2\u0083\u00e1\u00e6\u0010\u00b3$|\u00d1\u00b5\u00a5\u0082\u00e1\u00b0$4\u008c\u00b5\u00a6\u00a7e \u0006!8q\u0087H\u0015\u009bN-|\u0088\u00e47\u0010-\u00d6\u0091\u00ec.\u00a7_\u00ec7\u00e2\u00c20\u00b3\u009as\u00be\u009b(Gi\u0017\u0012\u00b5\u00c3\u0099\u0099V\u00ddg\u0091,\u00c6\u00e4\u000f\u00ba\u00f3k\u0083:_1\u009c\u0085\u0087?\u00e3\u00c8\u0003\u00e7\u00f1\u00b1\u00c3\u008a\u00e4\u00af\u0097\u0006S\u0018\u00ac\u00d6M\u00be\u00be\u00d5\u00bd\u00fe\u0089\u00ef\u00c6*$&\u00f49O\u00b65\u00b0\u009d\u0018\u00d9\u00c3 \u009b\u00d6\u00fb\u00a2\u008fhD\u00bfx\u00a0\u00d2@\u00e3\u00b9\u00c5\u00e8.Fn=bk\u00e0\u0019\u00fb\u0016\u00d77\u008cd\u00d1u\u0018\u00adN\u00d1\u008b)\u00c4\u00f7\u00e5\u00b5R^<\u00f6\u00c4\u00a7M\u00d4\u00a9;j\u00be\u000b\u0019\u0015\u0010\u00cf\u009d\u0011\u00d6\u0089\u00c1\u00f90z\u00ee\u001c\u0092g\u0003\u00d6` \u00c9(w\u009c\u0005\u00e3\u0017{\r6\u00e7\u00d7\u0094\u008c\u00c4\u00c0\u00bc@\u00a4\u001ft\u008d_$\u00a9\u00c3\u00de\u00ee\u00cf\u009e\u00b6\u00e9(\u009f\u00ab\u0091[%\u00d9\u00bd\u0093q\u00dd~\u001ag\u0005X\u00af\u00f3\u00c2\u0091\u001d\u000e\u00141\u00b9\u00c5Y(\u00e4\u00d89\u0014II\u00a0U2\r\u00d7d> \u0087\u00ae\u00d1\f\u0017jL\u00e7\u0004\u00dfG~\u0091\u00bdVolr\u00172\u008b-\u008e3\u00f3\u00ca\u00d1p\n\u00ec\u00f0\u0018\u0018\u0082\u00c66~\u0013\u00d4\u00be\u00e8p\u0085z\fQ%k\u00af\u00c3\u00fd\\\u00d7\u00ae\u0014\u00ca\u00c9\u0018\u0001&s5\u0000\u00a3d?\u0091<\u008a\u0019\u009c\u00f4\u00a2\u001c\u00fb\u0012\u00bb\u00c37\u00c9\u008b\u00d4 \t,\u00ef\u0089\u00b4hw\u00c9\u00bc_\u00c6\u00800\u00fe\u00a0:N\u00f2\u00ed\u00d9[\u00aa\u00a6\u008f~\u009bnm\u00cepC\u00d4 9\u00e3\u008c:\u00efZ\u0081n\u00a5\u008c\u008f\rW~\u00f7Yv\u00bf,\r \u008b\u009b\u0088&\u00dc\u00b5\u00a5\u0006M\u00ef$ /\u0082m\u00fb\u00ec\u00b6\u00b8<L\u0010\u00cd\u0005\u00c2\u00ad\u00be\u00d0;\u0004oQ\u0085rO[F\u00f5\u008f\u00feQ:\u00ebF T$9p{\u0005\u00dc\u00d6\u00cb]\u009c\u007fbl\u00e6\u0081!0\u00f1\u000eh\u000f\u00d7\t\u009a?X\u00dd\u00811u\u00c8 \u00d6\u0095a\u00adnY\u00ef!J\u0086\u0013\u00d5?\u00da\u00c0\u00dcy.\u0013De\u009d\u00a5\u00f8\u0002\u00b8%\u00b4\u00a8\u0010\u008a\u0099\u0010\t\u00d3%\u0085\u00be\u00f5-\r\u0007\u00e8,\u0000\u00ec\u008f\u00be\u00ff(\u0014\u00d9oq\u00a9C\u00c0QEwG\u00cf\u00a9]`\u00fa\tw\u00c6d\u00d4\u00c0\u000e\u00e4A\u00ab\\\f\u00d9\u00ac\u00d0\u009b\u00cf\u0080\u00fe~ 7z\u0018 \u00b1b\u00dau\u00bf\u00ec\u000f\u0014\u000e\u008d-\u0002Sqe\u0011\u0088\u00f2\u00f2\u00e6\u000bo\u0004{\u0000j4\u00c4|HL\u00b1\u0018\n\u000f\u00dc%\u00f3\u00993\u007f#\u00ad=\u009e\u0092\u0086_\u00e0E6\u00a1\u009c\u00d0\u00b6\u00841 \u00e6j\u001c\u0086\u00c2\u00a2\u00e8\u00ee~\u00f7\u00d6\u00c4\u001b\u00e2\u00fa\u00c5u\u0098\u001f\"i\u0010\u00f2.^1<3\b\u001f\u00d9\u00a4 \u00bd#\u00eb\u00a7\u00a5\u0010\u00f3G\u0099h\u0016\\\u00f1\u00f1\u00c0\u00c59&7\u009b\u00d2@}\u00f0}\u00f8_vjG:\t\u0018\u0018\u001a+u?\u0098\u00c58Ws\u001cI\u00d9\u009c\u00f0D\u00cfI\t\u00f6`\u001a\u00e20 g\u0004\u00c3\u00b4('\u00df\u00e6)&.\u00b3\u0080r\u00a9L1\u00a9\u00ba\u00c3\u00f1E\u00f5\u00f80\u0015/B\u00c6\u008ef\u00ac\u0010\u0007y\u00ca[A\u007fI-9\u00dc\u00ebg\u0007\b\u00b0g\u0018\u00b3I\u0093\u0094\u00ae\u007fh\u00c5\u0082\u00da\u00faM\u00920\u00aa\u00a3\u00b6\u00c0\u00f3\u00b0\u0080\t*\u00b4\u0018R\u000e\u008a\r\u00fdh\u001e\u00cd\u00fb\u0099\u00ef/\u0002\u00e5AOW$R\u00e8\u00f9\u00ef\u009a\u00b2\u0018\u00f8\u00c4\u00f0\u009dEg\"\u00a7\u00cc\u00dc\u00f4\u00de\u001f\u00f8\u008b\u0004\u00c9\u00ae\u007fY#=)\u0094\u00188\u0002F\u00c9\r-b\u00cd}\u00056Ek)h\u0084Q\u00d7\f@\u00e4\u00d2\u00c2n \u00ab\u0016\u00a5>\u00b30qj\u00d2z\u00b3Y\u00cc\rp\u0097w?YP\u00c7\u00c3\u0080\u00a7\u00c5\u00ed[\u001b\u001f\u00f6\u00e4\u0099";
                        var17_6 = 6229;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = ElytraPlus.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c7\u00d5\u00b7\u00a8>\u00920\u008d\u0016d\u009e\u00dd?\u0014\u00f7\u0018\u00181\u00a8\u00c1\u00cb\u00f4O\u00ecC\u00f0\u00e3\u0096\u00d2$\u00ac\u00f7\u00e4q;v\u0086\u00aa\u0099..";
                            var17_6 = 41;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = ElytraPlus.zkmDecodeUtf8(var19_9).intern();
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
                ElytraPlus.fb = var18_3;
                ElytraPlus.gb = new String[200];
                ElytraPlus.nb = new HashMap<K, V>(13);
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
                var6_12 = new long[123];
                var3_13 = 0;
                var4_14 = "a\u0090\u009c\\\u00bc{\u00d4G\u00f7i\u0094N\r\u00d4\u0088\u00fc\u00e7\u009032\f\u008d22\u00b2h\u00c7\u001e{\u0003\u0099\u00bbq\u008be\u008b`\u0098\u00f0r\u0099\u00f2\"\u00e3\u00f4\u0016\u000e\u0090\u00eaJ\u00d5l*g\u001a\u00cd\u00a2\u0006|sl\u008btS\u00aeB@\u00a5lxu\u00b8[\u00fc\u0084\u0016=\u00cb\u0096\u00a6]\u0082\u0015\u0087\bsi^R\u00feC\u00fd\u0090\u00c1\u00ec\u00f3k@s\u00f0\u00af\u00ea_\u0085@\u00ca`\u00f0\u001f\u00d7\u00d4\u0003\u00ab\u00bc2\u00b9Q\u00f4\u00ac\u00a8\u00af\u001e\u00f36\u00fbT\u0097\u00ee\u001e&\u00f2\u00fc\u0098\u001e\u008c\u0006\u00feM#\u00c6\u0092\"X\u00ba\u0099\u00cee&\u00bf\u00e5\u00d0\u00b3|<\u008b\u00bd\u0092\u0081\u00ac\u00ba\u00c3*\u000b(X\u00b2\u00052\u00f0Yg\u00f0Lb\u0013\u0003r0\u007f\u00de\u00fc\rk]\u00d1\u00c4\u00c3@\u00dd\u00ae\u0094m:\u00baFvp@&h\u00e5\nzwn\u001e^*\u00a8\u0092\u00d8\u00b3\u00f4\u0001\u001bY\u00f0u$V]\u00fb\u007f\u00ad{\u00dc\u00a3\u00d5\u00f2\u0087\u000bH$?\u0007\u0018zwb k\u0099\u0082\u0016<*\u00f7%;4j\u00f8\u00e7@A0 \u00ef5\u00f8A\u00a1\u00e1\b\u00dc\u0089\u00e2E\u0099\u00caM\"\u00aa\u00ec\u00cd\u00b1\u008d\u00e4\u00a2s5\u00cf\u00a0\u000ec\u00d7\u0083e\u00ee\u00eb0\u00ba\u001e\u00a8\u00fa}\u00b8\u0091\u0082\u0090\u00ed\u00de%\u00a2\u00a5b\u0087\u008b\u00a1\u0099\u009cB\u00e2\u0092\u00bf\u00a7\u008a7\u00df\u00daQ\u0012\u000ee\u001e\u00f1\u00f1\u0087\u0084\u00b2-\u00ec0\u00ef\u00c7\u00cd\u00f8@\u0019\u000b\u00f3\u00afd\u00c4s/9\u00bc\u0001\u0018\u00a8\u00f7\u008b\u00d9\u0088\u00df\u0098\u0082C\u001a\u00d74\u008c\u0099\u00a17\u00b1$\u009aB\u00f7\u000eL\u00e0\u00f7\u0095`/R\u001f\u00ca\u00e9\u00e0\u00bd\u00a1\u00b2\u00b4\f\u00c9?t\u00df\u00a33\u00bcU\u00cb\u0003\u00fe\r\u001c\u00012\u00dd\u00fd<\u0099\u009dIN\u000e\u00f3\u00ec'\u00a6r\u00eb\u0096\u0084\u00fcz\u00ba\u008b\u00b9\u00cc\u00aak&o\u00ce\u00e1-\u00c7\u0093|\u00ca\u00c0\u00b8/\u008a4\u0016)J\u00a7\u009f\u00d5\u00d7:\u009d\u00f9W\u00f0\u00b7J\u001a|L\u0086s43X\u00b5\u008dJ!\u0016X\u0004\u00c6\u000e\u00bb[\u00baF\u00d3PS\u0086\u00ff|\u00e1u\u00ca\u00b6N\u00fb\u009bf*d\u00ae\u00fb\u008f\u000b\u0080\u0000\u00aa#\u00b7\u00f3\u00db\u0091-\u001a\u00f6\u00ce\u0019\u00de\u00b4\u008b]b7jv\u00f4\u00e2\u00a0a\u00fdE^\u0089\u00a9\u00c7]\u00d8\u0082\u00f9\u009e*\u00ec\u0001EJ<W\u008b\u00ee\u0090'\u00c5\u0018\u00a0/\u00aat\u00a8Ea\u0000\u00e6e\u00c68\u00be\u00cd\u00ebW\u00e0-\u009d9\u00be\u001e\u00df\u0089\u000b?\u00c8\u00e4\u00d9\u00a3K\u00bd\u009e\u0013F\u001a\u00ba#\u0086\u0015\u008e\u0089\r\u009b\u0084\u00a1\u00f51\u00f9\u00b9N\u00d4\u0013\u0011y\u0013\u001e\u00ad\u00b6\u00abz\u0017\u0007\u00f58\u00d7E\u00cdVI\r\u00fc\u0098\u00fbT\u008d\u00f3m*tI\u0097\u00d6\u00e5e\u00d7\f.N\u00ae\u000e\u00b3\u00d3>;f\u009b\u00c5\u0092\u0087\u00b5\u00e8\u0094\u00cd\u00a5\u00bbhg\u00fe\u00868\u009b\u00ca\u00037\u0007RZ:AF\u00e8tL^\u00bd\u00acz\u00cd\u0019\u00d4v\u00d7}UkXV\u00b6J\u00b0\u00ablNI\u00d1%\u009f\u0005\u00d8\u00c8\u0003\u001d\u00e3\u008f\u008b\u00a8\u00ce\u0006I\u00a7\u0084fcKtd\u00c7\u0006\u0000X\u0094y\u00f5D\u00bd}\u00a6\u00af\u00d0?\u0094\u0016c\u00ba<\u00d7\u0007\u00edc0<\u00c8\u00dc\u00f9[f\u00c3\u0000\u008a\u001bQZ\u009e\u0014\u00bf-\u00d2<\u0096e\u001e\u000b\u00e62\u0019j\u00b5\u0090yw\u0098 t\u00b1\u0082\u00a3\u009c\u00ae\"\u0002\u0014\u00ac2\u00e5\u00c7\u0016\u00f6\u0081\u00e7\u00c5\u0082k+\u00fa\u00b9z*\u00b3\u00c0g\u00e5a\u0094\u00e1\u00c03,6\u0011\u00dc\u00d1\u00f5{x\u00c6\u000e\u00a0\u0005\u00abz\u0090vK7\u00a3c\u0088\u00a2\u00fa\u00c3!N\u00bb\u001c\u00a5<{M\u0092\b\u00a6Y=5\u00b8cA\u00ebw\u00f1\u001c\u00cf\u0016\u0005\u00db|\u00e4w\u00a7Nr\u000f\u0095\u0091']\f\u00f2p\u00cf\u0088\u0007;s\u00b5\u00ac2\u009c\u00d1\u00d3He/]\u00fa\u00d4/kb\u00b2\u00f7\u0099^\u00d6\u0005.\u0095E\u00f7?C\"\u00fa,4\u009bh\u00b1I\u00faDu:\u00968\u0080\u001b!\u0013}/\u00b9..\u009f\u00c7\u00ffQZ\u00ff\u00de\u00b1\u00ba\u0015\u00a2\u009f\u00f6\u00b6\u0090\u00ea\u00d3\u0006\u0093\u001d\u00dc\u00b6\u001a\u00d1\u00a8\u00ac3\u00a1\u00f0O\u0094n]'\u0016\u00b4\u0006\u001b\u001c\u0082N\u00d7\u0013M\u001bX\u0011W";
                var5_15 = 968;
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
                    var4_14 = "\u00bda\u00c0\u00c0&\u00a6\u001bF\u0089\u0089\u00ecK\u009aPbo";
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
        ElytraPlus.lb = var6_12;
        ElytraPlus.mb = new Integer[123];
        var38_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "mode", "getMode()Lsu/catlean/dz;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "twoBee", "getTwoBee()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "lift", "getLift()Lsu/catlean/api/setting/values/Bind;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "onlySpace", "getOnlySpace()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "stopOnGround", "getStopOnGround()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "infDurability", "getInfDurability()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "vertical", "getVertical()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "ncpStrictMode", "getNcpStrictMode()Lsu/catlean/aq;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "antiKick", "getAntiKick()Lsu/catlean/oz;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "xzSpeed", "getXzSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "ySpeed", "getYSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "fireSlot", "getFireSlot()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "accelerate", "getAccelerate()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "accelerateFactor", "getAccelerateFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "fireDelay", "getFireDelay()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "rotate", "getRotate()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "stayMad", "getStayMad()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "keepFlying", "getKeepFlying()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "disableOnFlag", "getDisableOnFlag()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "allowFireSwap", "getAllowFireSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "instantFly", "getInstantFly()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "cruiseControl", "getCruiseControl()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "pitchControl", "getPitchControl()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "factor", "getFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "upSpeed", "getUpSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "downFactor", "getDownFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "stopMotion", "getStopMotion()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "minUpSpeed", "getMinUpSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "forceHeight", "getForceHeight()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "manualHeight", "getManualHeight()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "sneakDownSpeed", "getSneakDownSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "speedLimit", "getSpeedLimit()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "maxSpeed", "getMaxSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "redeployInterval", "getRedeployInterval()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "redeployTimeOut", "getRedeployTimeOut()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "redeployDelay", "getRedeployDelay()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "infiniteMaxHeight", "getInfiniteMaxHeight()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "stopMove", "getStopMove()Lsu/catlean/am;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "swapDelay", "getSwapDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "fireworkDelay", "getFireworkDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "offHandFirework", "getOffHandFirework()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "alternativeFireworkSwap", "getAlternativeFireworkSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "pauseWhileEating", "getPauseWhileEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "minimizeClicks", "getMinimizeClicks()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "boost", "getBoost()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "customSpeed", "getCustomSpeed()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees0", "getDegrees0()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees5", "getDegrees5()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees10", "getDegrees10()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees15", "getDegrees15()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees20", "getDegrees20()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees25", "getDegrees25()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees30", "getDegrees30()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees35", "getDegrees35()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees40", "getDegrees40()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees45", "getDegrees45()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees50", "getDegrees50()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees55", "getDegrees55()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees60", "getDegrees60()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees65", "getDegrees65()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees70", "getDegrees70()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees75", "getDegrees75()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees80", "getDegrees80()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees85", "getDegrees85()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraPlus.class, "degrees90", "getDegrees90()F", 0))};
        ElytraPlus.delegatedProperties = var38_20;
        ElytraPlus.INSTANCE = new ElytraPlus();
        ElytraPlus.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraPlus.INSTANCE, "mode", (Enum)ElytraPlusMode.FIRE_WORK, null, null, 12, null);
        ElytraPlus.twoBee$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "vanilla", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        var39_21 = null;
        var40_22 = 4;
        var43_23 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)();
        ElytraPlus.lift$delegate = SettingFactory.bindSetting$default(ElytraPlus.INSTANCE, "lift", new Bind(0, false, false, 7, null), null, var43_23, var40_22, var39_21);
        ElytraPlus.onlySpace$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "only-space", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.stopOnGround$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "stop-on-ground", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.infDurability$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "inf-durability", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.vertical$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "vertical", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.ncpStrictMode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraPlus.INSTANCE, "ncp-strict", (Enum)ElytraPlusNcpStrictMode.Off, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.antiKick$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraPlus.INSTANCE, "anti-kick", (Enum)ElytraPlusAntiKickMode.Jitter, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.xzSpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "x-z-speed", 1.55f, RangesKt.rangeTo(0.1f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.ySpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "y-speed", 0.47f, RangesKt.rangeTo(0.0f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.fireSlot = SettingFactory.intSetting$default(ElytraPlus.INSTANCE, "fire-slot", 1, new IntRange(1, 9), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.accelerate$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "acceleration", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.accelerateFactor$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "accelerate-factor", 9.0f, RangesKt.rangeTo(0.1f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.fireDelay$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "fire-delay", 1.5f, RangesKt.rangeTo(0.0f, 1.5f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "rotate", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.stayMad$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "ground-safe", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.keepFlying$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "keep-flying", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.disableOnFlag$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "disable-on-flag", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.allowFireSwap = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "allow-fire-swap", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.instantFly = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "instant-fly", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.cruiseControl$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "cruise-control", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.pitchControl$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "pitch-control", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.factor = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "factor", 1.5f, RangesKt.rangeTo(0.1f, 50.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible23(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.upSpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "up-speed", 1.0f, RangesKt.rangeTo(0.01f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible24(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.downFactor$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "glide", 1.0f, RangesKt.rangeTo(0.0f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible25(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.stopMotion$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "stop-motion", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible26(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.minUpSpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "min-up-speed", 0.5f, RangesKt.rangeTo(0.1f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible27(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.forceHeight$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "force-height", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible28(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.manualHeight$delegate = SettingFactory.intSetting$default(ElytraPlus.INSTANCE, "height", 121, new IntRange(1, 256), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible29(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.sneakDownSpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "down-speed", 1.0f, RangesKt.rangeTo(0.01f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible30(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.speedLimit = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "speed-limit", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible31(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.maxSpeed$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "max-speed", 2.5f, RangesKt.rangeTo(0.1f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible32(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.redeployInterval$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "redeploy-interval", 1.0f, RangesKt.rangeTo(0.1f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible33(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.redeployTimeOut$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "redeploy-timeout", 5.0f, RangesKt.rangeTo(0.1f, 20.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible34(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.redeployDelay$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "redeploy-delay", 0.5f, RangesKt.rangeTo(0.1f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible35(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.infiniteMaxHeight$delegate = SettingFactory.intSetting$default(ElytraPlus.INSTANCE, "infinite-max-height", 200, new IntRange(50, 360), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible36(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.stopMove$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraPlus.INSTANCE, "stop-move", (Enum)ElytraPlusStopMoveMode.BLOCK, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible37(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.swapDelay$delegate = SettingFactory.intSetting$default(ElytraPlus.INSTANCE, "elytra-delay", 2, new IntRange(0, 40), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible38(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.fireworkDelay$delegate = SettingFactory.intSetting$default(ElytraPlus.INSTANCE, "firework-delay", 8, new IntRange(0, 40), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible39(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.offHandFirework$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "offhand-firework", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible40(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.alternativeFireworkSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "alt-firework-swap", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible41(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.pauseWhileEating = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "pause-while-eating", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible42(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.minimizeClicks$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "minimize-clicks", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible43(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.boost$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "boost", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible44(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus.customSpeed$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraPlus.INSTANCE, "custom-speed", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible45(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraPlus._0Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "0-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible46(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus._5Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "5-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible47(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees10$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "10-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible48(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus._15Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "15-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible49(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees20$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "20-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible50(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus._25Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "25-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible51(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus._30Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "30-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible52(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees35$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "35-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible53(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus._40Degrees = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "40-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible54(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees45$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "45-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible55(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees50$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "50-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible56(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees55$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "55-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible57(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees60$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "60-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible58(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees65$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "65-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible59(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees70$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "70-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible60(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees75$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "75-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible61(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees80$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "80-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible62(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees85$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "85-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible63(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.degrees90$delegate = SettingFactory.floatSetting$default(ElytraPlus.INSTANCE, "90-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible64(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraPlus.redeployTimer = new Timer();
        ElytraPlus.boostTimer = new Timer();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        p7 = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return p7;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7BD7;
        if (gb[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])hb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    hb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ut", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = fb[n2].getBytes("ISO-8859-1");
            ElytraPlus.gb[n2] = ElytraPlus.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ElytraPlus.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ut" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1C9A;
        if (mb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = lb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])nb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    nb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ut", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ElytraPlus.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ElytraPlus.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ut" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ElytraPlus.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ElytraPlus.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

