/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraRotationMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Easing;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.PlaceManager;
import su.catlean.util.RandomUtil;
import su.catlean.util.Spring;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.AimPoint;
import su.catlean.util.rotation.AimSettings$WhenMappings;
import su.catlean.util.rotation.AimSettingsFilterLowDeltasMode;
import su.catlean.util.rotation.AimSettingsHoldTimingMode;
import su.catlean.util.rotation.AimSettingsSpeedDistributionMode;
import su.catlean.util.rotation.JitterMode;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AimSettings
extends AimMode {
    @NotNull
    public static final AimSettings INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup advancedOptions$delegate;
    @NotNull
    private static final ModeSetting point$delegate;
    @NotNull
    private static final ModeSetting pointJitter$delegate;
    @NotNull
    private static final FloatSetting pointVRange$delegate;
    @NotNull
    private static final FloatSetting pointHRange$delegate;
    @NotNull
    private static final FloatSetting pointHFreq$delegate;
    @NotNull
    private static final FloatSetting pointVFreq;
    @NotNull
    private static final ModeSetting speedDistribution;
    @NotNull
    private static final FloatSetting minYawSpeed;
    @NotNull
    private static final FloatSetting maxYawSpeed$delegate;
    @NotNull
    private static final FloatSetting minPitchSpeed$delegate;
    @NotNull
    private static final FloatSetting maxPitchSpeed$delegate;
    @NotNull
    private static final ModeSetting jitterDistribution$delegate;
    @NotNull
    private static final ModeSetting yawJitter;
    @NotNull
    private static final ModeSetting pitchJitter;
    @NotNull
    private static final FloatSetting yawAmplMin;
    @NotNull
    private static final FloatSetting pitchAmplMin;
    @NotNull
    private static final FloatSetting yawAmplMax;
    @NotNull
    private static final FloatSetting pitchAmplMax;
    @NotNull
    private static final FloatSetting yawFreqMin;
    @NotNull
    private static final FloatSetting pitchFreqMin;
    @NotNull
    private static final FloatSetting yawFreqMax;
    @NotNull
    private static final FloatSetting pitchFreqMax;
    @NotNull
    private static final FloatSetting smoothHbScale$delegate;
    @NotNull
    private static final FloatSetting yawAcceleration$delegate;
    @NotNull
    private static final FloatSetting pitchAccel;
    @NotNull
    private static final FloatSetting yawDeceleration$delegate;
    @NotNull
    private static final FloatSetting pitchDeceleration$delegate;
    @NotNull
    private static final ModeSetting yawSmoother$delegate;
    @NotNull
    private static final ModeSetting pitchSmoother$delegate;
    @NotNull
    private static final FloatSetting yawAccelerationMin$delegate;
    @NotNull
    private static final FloatSetting pitchAccelerationMin$delegate;
    @NotNull
    private static final BooleanSetting clientLook$delegate;
    @NotNull
    private static final BooleanSetting overShoot$delegate;
    @NotNull
    private static final FloatSetting overshootTrigger$delegate;
    @NotNull
    private static final IntSetting osStreak;
    @NotNull
    private static final FloatSetting overshootValue$delegate;
    @NotNull
    private static final IntSetting overshootMinTicks$delegate;
    @NotNull
    private static final IntSetting overshootMaxTicks$delegate;
    @NotNull
    private static final BooleanSetting headUp$delegate;
    @NotNull
    private static final IntSetting huIntervalMin$delegate;
    @NotNull
    private static final IntSetting huIntervalMax$delegate;
    @NotNull
    private static final IntSetting huTicksMin$delegate;
    @NotNull
    private static final IntSetting huTicksMax$delegate;
    @NotNull
    private static final FloatSetting huSpeedMin;
    @NotNull
    private static final FloatSetting huSpeedMax;
    @NotNull
    private static final BooleanSetting clickJitter;
    @NotNull
    private static final BooleanSetting yaw360$delegate;
    @NotNull
    private static final IntSetting yaw360Chance$delegate;
    @NotNull
    private static final BooleanSetting holdAfterHit;
    @NotNull
    private static final IntSetting holdMin$delegate;
    @NotNull
    private static final IntSetting holdMax$delegate;
    @NotNull
    private static final ModeSetting holdTiming;
    @NotNull
    private static final IntSetting holdChance$delegate;
    @NotNull
    private static final FloatSetting pitchMin$delegate;
    @NotNull
    private static final FloatSetting pitchMax$delegate;
    @NotNull
    private static final FloatSetting pitchITMin$delegate;
    @NotNull
    private static final FloatSetting pitchITMax$delegate;
    @NotNull
    private static final ModeSetting filterLowDeltas$delegate;
    @NotNull
    private static final FloatSetting lowDeltaThreshold$delegate;
    @NotNull
    private static final FloatSetting replaceMin$delegate;
    @NotNull
    private static final FloatSetting replaceMax;
    @NotNull
    private static Vec3d basePointOffset;
    @NotNull
    private static Vec3d jitterOffset;
    @NotNull
    private static Vec3d aimPointOffset;
    @NotNull
    private static Spring yawSpring;
    @NotNull
    private static Spring pitchSpring;
    @NotNull
    private static Spring pointHSpring;
    @NotNull
    private static Spring s;
    @NotNull
    private static Spring pointVSpring;
    private static float yawSpeed;
    private static float pitchSpeed;
    private static int nt;
    private static int n4;
    private static float yawDirection;
    private static float nQ;
    private static int n_;
    private static int o;
    private static int p;
    private static int k;
    private static int q;
    private static float spinAngle;
    private static float randomSign;
    private static int S;
    private static int b;
    private static int nF;
    @NotNull
    private static final List clickPatterns;
    private static int[] Y;
    private static final long bb;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] jb;
    private static final Integer[] kb;
    private static final Map lb;
    private static final long[] mb;
    private static final Long[] ob;
    private static final Map pb;

    private AimSettings() {
    }

    public final boolean isAdvanced() {
        return Aura.INSTANCE.getRotationMode() == AuraRotationMode.ADVANCED;
    }

    @NotNull
    public final SettingParent getAdvancedOptions() {
        return (SettingParent)advancedOptions$delegate.getValue(this, delegatedProperties[0]);
    }

    @NotNull
    public final AimPoint getPoint() {
        return (AimPoint)((Object)point$delegate.getValue(this, delegatedProperties[1]));
    }

    @NotNull
    public final JitterMode getPointJitter() {
        return (JitterMode)((Object)pointJitter$delegate.getValue(this, delegatedProperties[2]));
    }

    public final float getPointVRange() {
        return ((Number)pointVRange$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    public final float getPointHRange() {
        return ((Number)pointHRange$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    public final float getPointHFreq() {
        return ((Number)pointHFreq$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    public final float FS() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 32 | l << 32 >>> 32) ^ bb;
        return ((Number)pointVFreq.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)6500, (long)(0x6ED406927C1087BDL ^ l2))])).floatValue();
    }

    @NotNull
    public final AimSettingsSpeedDistributionMode Fs() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ bb;
        return (AimSettingsSpeedDistributionMode)((Object)speedDistribution.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)7985, (long)(0x1F4013DFD539FFF4L ^ l))]));
    }

    public final float FX() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ bb;
        return ((Number)minYawSpeed.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)24265, (long)(0x3669534356438F06L ^ l))])).floatValue();
    }

    public final float getMaxYawSpeed() {
        return ((Number)maxYawSpeed$delegate.getValue(this, delegatedProperties[9])).floatValue();
    }

    public final float getMinPitchSpeed() {
        return ((Number)minPitchSpeed$delegate.getValue(this, delegatedProperties[10])).floatValue();
    }

    public final float getMaxPitchSpeed() {
        return ((Number)maxPitchSpeed$delegate.getValue(this, delegatedProperties[11])).floatValue();
    }

    @NotNull
    public final AimSettingsSpeedDistributionMode getJitterDistribution() {
        return (AimSettingsSpeedDistributionMode)((Object)jitterDistribution$delegate.getValue(this, delegatedProperties[12]));
    }

    @NotNull
    public final JitterMode FT() {
        long l = 0L;
        l = bb ^ l;
        return (JitterMode)((Object)yawJitter.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)10987, (long)(0x65BB7786468C315AL ^ l))]));
    }

    @NotNull
    public final JitterMode Fd() {
        long l = 0L;
        l = bb ^ l;
        return (JitterMode)((Object)pitchJitter.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)16111, (long)(0x2D37CAF3FFA6F57CL ^ l))]));
    }

    public final float FA() {
        long l = 0L;
        l = bb ^ l;
        return ((Number)yawAmplMin.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)20923, (long)(0x41BD2CA4D9F46B13L ^ l))])).floatValue();
    }

    public final float FJ() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)pitchAmplMin.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)17721, (long)(0x7B54BAFCD34AC375L ^ l))])).floatValue();
    }

    public final float h() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)yawAmplMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)22406, (long)(0x3DB1830D79794B44L ^ l))])).floatValue();
    }

    public final float z() {
        long l = 0L;
        l = bb ^ l;
        return ((Number)pitchAmplMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)12552, (long)(0x4BD93942B2420A38L ^ l))])).floatValue();
    }

    public final float f() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)yawFreqMin.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)11834, (long)(0x351ED3058ED9220BL ^ l))])).floatValue();
    }

    public final float e() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ bb;
        return ((Number)pitchFreqMin.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)28665, (long)(0x3B3B1A8B1A868CC6L ^ l))])).floatValue();
    }

    public final float L() {
        long l = 0L;
        l = bb ^ l;
        return ((Number)yawFreqMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)6968, (long)(0x60D76C44C9F6DA5EL ^ l))])).floatValue();
    }

    public final float F7() {
        long l = 0L;
        l = bb ^ l;
        return ((Number)pitchFreqMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)25250, (long)(0x2BD7B438CF52CE2AL ^ l))])).floatValue();
    }

    public final float getSmoothHbScale() {
        return ((Number)smoothHbScale$delegate.getValue(this, delegatedProperties[23])).floatValue();
    }

    public final float getYawAcceleration() {
        return ((Number)yawAcceleration$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    public final float F4() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)pitchAccel.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)9583, (long)(0x442CB299434F0699L ^ l))])).floatValue();
    }

    public final float getYawDeceleration() {
        return ((Number)yawDeceleration$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    public final float getPitchDeceleration() {
        return ((Number)pitchDeceleration$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    @NotNull
    public final Easing getYawSmoother() {
        return (Easing)((Object)yawSmoother$delegate.getValue(this, delegatedProperties[28]));
    }

    @NotNull
    public final Easing getPitchSmoother() {
        return (Easing)((Object)pitchSmoother$delegate.getValue(this, delegatedProperties[29]));
    }

    public final float getYawAccelerationMin() {
        return ((Number)yawAccelerationMin$delegate.getValue(this, delegatedProperties[30])).floatValue();
    }

    public final float getPitchAccelerationMin() {
        return ((Number)pitchAccelerationMin$delegate.getValue(this, delegatedProperties[31])).floatValue();
    }

    public final boolean getClientLook() {
        return (Boolean)clientLook$delegate.getValue(this, delegatedProperties[32]);
    }

    public final boolean getOverShoot() {
        return (Boolean)overShoot$delegate.getValue(this, delegatedProperties[33]);
    }

    public final float getOvershootTrigger() {
        return ((Number)overshootTrigger$delegate.getValue(this, delegatedProperties[34])).floatValue();
    }

    public final int M() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)osStreak.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)20344, (long)(0x66C22A44CFC331F3L ^ l))])).intValue();
    }

    public final float getOvershootValue() {
        return ((Number)overshootValue$delegate.getValue(this, delegatedProperties[36])).floatValue();
    }

    public final int getOvershootMinTicks() {
        return ((Number)overshootMinTicks$delegate.getValue(this, delegatedProperties[37])).intValue();
    }

    public final int getOvershootMaxTicks() {
        return ((Number)overshootMaxTicks$delegate.getValue(this, delegatedProperties[38])).intValue();
    }

    public final boolean getHeadUp() {
        return (Boolean)headUp$delegate.getValue(this, delegatedProperties[39]);
    }

    public final int getHuIntervalMin() {
        return ((Number)huIntervalMin$delegate.getValue(this, delegatedProperties[40])).intValue();
    }

    public final int getHuIntervalMax() {
        return ((Number)huIntervalMax$delegate.getValue(this, delegatedProperties[41])).intValue();
    }

    public final int getHuTicksMin() {
        return ((Number)huTicksMin$delegate.getValue(this, delegatedProperties[42])).intValue();
    }

    public final int getHuTicksMax() {
        return ((Number)huTicksMax$delegate.getValue(this, delegatedProperties[43])).intValue();
    }

    public final float F5() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ bb;
        return ((Number)huSpeedMin.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)6413, (long)(0x3552DFE604EBDF1BL ^ l))])).floatValue();
    }

    public final float g2() {
        long l = 0L;
        int n = 0;
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ bb;
        return ((Number)huSpeedMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)18768, (long)(0x5C2348905A8B1D59L ^ l2))])).floatValue();
    }

    public final boolean s() {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ bb;
        return (Boolean)clickJitter.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)2634, (long)(0x1C19BCF1C64B9F11L ^ l))]);
    }

    public final boolean getYaw360() {
        return (Boolean)yaw360$delegate.getValue(this, delegatedProperties[47]);
    }

    public final int getYaw360Chance() {
        return ((Number)yaw360Chance$delegate.getValue(this, delegatedProperties[48])).intValue();
    }

    public final boolean v() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ bb;
        return (Boolean)holdAfterHit.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)4035, (long)(0x77D33CD2ED61C315L ^ l))]);
    }

    public final int getHoldMin() {
        return ((Number)holdMin$delegate.getValue(this, delegatedProperties[50])).intValue();
    }

    public final int getHoldMax() {
        return ((Number)holdMax$delegate.getValue(this, delegatedProperties[51])).intValue();
    }

    @NotNull
    public final AimSettingsHoldTimingMode Fj() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ bb;
        return (AimSettingsHoldTimingMode)((Object)holdTiming.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)17540, (long)(0x738CA07EE7E4B854L ^ l))]));
    }

    public final int getHoldChance() {
        return ((Number)holdChance$delegate.getValue(this, delegatedProperties[53])).intValue();
    }

    public final float getPitchMin() {
        return ((Number)pitchMin$delegate.getValue(this, delegatedProperties[54])).floatValue();
    }

    public final float getPitchMax() {
        return ((Number)pitchMax$delegate.getValue(this, delegatedProperties[55])).floatValue();
    }

    public final float getPitchITMin() {
        return ((Number)pitchITMin$delegate.getValue(this, delegatedProperties[56])).floatValue();
    }

    public final float getPitchITMax() {
        return ((Number)pitchITMax$delegate.getValue(this, delegatedProperties[57])).floatValue();
    }

    @NotNull
    public final AimSettingsFilterLowDeltasMode getFilterLowDeltas() {
        return (AimSettingsFilterLowDeltasMode)((Object)filterLowDeltas$delegate.getValue(this, delegatedProperties[58]));
    }

    public final float getLowDeltaThreshold() {
        return ((Number)lowDeltaThreshold$delegate.getValue(this, delegatedProperties[59])).floatValue();
    }

    public final float getReplaceMin() {
        return ((Number)replaceMin$delegate.getValue(this, delegatedProperties[60])).floatValue();
    }

    public final float Fv() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ bb;
        return ((Number)replaceMax.getValue(this, delegatedProperties[AimSettings.zkmBootstrap2("x", (int)15846, (long)(0x2F6D71A299EBAE7FL ^ l))])).floatValue();
    }

    @Override
    @NotNull
    public Rotation calcRotation(@NotNull Entity entity, long l, @NotNull Rotation rotation, boolean bl) {
        int n;
        long l2 = l;
        long l3 = l2 ^ 0x197196555DA8L;
        int n2 = (int)(l3 >>> 48);
        int n3 = (int)(l3 << 16 >>> 32);
        int n4 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x79178FEE3FD5L;
        int n5 = (int)((l2 ^ 0x27227B9570E4L) >>> 32);
        long l5 = l2 ^ 0x453297F2EE4CL;
        Intrinsics.checkNotNullParameter(entity, "target");
        Intrinsics.checkNotNullParameter(rotation, "currentRotation");
        if (this.isHolding()) {
            return this.Fj() == AimSettingsHoldTimingMode.BEFORE_RANDOM ? rotation : this.getJitter(rotation.getYaw(), (short)n2, rotation.getPitch(), n3, (short)n4);
        }
        Vec3d vec3d = this.getAimPoint(l5, entity);
        this.getMicroOffset(entity);
        entity.setBoundingBox(entity.getBoundingBox().expand(this.getSmoothHbScale()));
        boolean bl2 = PlaceManager.INSTANCE.canHitEntity(entity, rotation, Aura.INSTANCE.getAttackRange() + 2.0f, !Aura.INSTANCE.getIgnoreWalls());
        entity.setBoundingBox(entity.getBoundingBox().expand(-((double)this.getSmoothHbScale())));
        if (bl2) {
            yawSpeed -= this.getYawDeceleration();
            pitchSpeed -= this.getPitchDeceleration();
        } else {
            yawSpeed += this.getYawAcceleration();
            pitchSpeed += this.F4();
        }
        yawSpeed = this.clamp(yawSpeed, RangesKt.rangeTo(this.getYawAccelerationMin(), 1.0f), n5);
        pitchSpeed = this.clamp(pitchSpeed, RangesKt.rangeTo(this.getPitchAccelerationMin(), 1.0f), n5);
        double d = Math.toDegrees(Math.atan2(vec3d.z - Globals.getPlayer().getZ(), vec3d.x - Globals.getPlayer().getX())) - 90.0;
        double d2 = -Math.toDegrees(Math.atan2(vec3d.y - (EntityUtil.getPos((Entity)((Entity)Globals.getPlayer())).y + (double)Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose())), Math.hypot(vec3d.x - Globals.getPlayer().getX(), vec3d.z - Globals.getPlayer().getZ())));
        if (this.getOverShoot() && nt > 0) {
            d += (double)(nQ + this.getOvershootValue() * yawDirection);
            var127_15 = nt;
            nt = var127_15 + -1;
        } else if (this.getYaw360() && q > 0) {
            d += (double)((spinAngle += 360.0f / (float)k) * randomSign);
            var127_15 = q;
            q = var127_15 + -1;
        }
        double d3 = MathHelper.wrapDegrees(d - (double)rotation.getYaw());
        double d4 = d2 - (double)rotation.getPitch();
        double d5 = (double)this.randomSpeed(l4, RangesKt.rangeTo(this.FX(), this.getMaxYawSpeed()), false) * ((Number)this.getYawSmoother().a().invoke(Double.valueOf(yawSpeed))).doubleValue();
        double d6 = (double)this.randomSpeed(l4, RangesKt.rangeTo(this.getMinPitchSpeed(), this.getMaxPitchSpeed()), false) * ((Number)this.getPitchSmoother().a().invoke(Double.valueOf(pitchSpeed))).doubleValue();
        float f = (float)this.clamp(Math.abs(d3), RangesKt.rangeTo(0.0, d5));
        float f2 = (float)this.clamp(Math.abs(d4), RangesKt.rangeTo(0.0, d6));
        float f3 = rotation.getYaw() + (d3 > 0.0 ? f : -f);
        float f4 = rotation.getPitch() + (d4 > 0.0 ? f2 : -f2);
        Rotation rotation2 = this.getJitter(f3, (short)n2, f4, n3, (short)n4);
        f3 = rotation2.getYaw();
        f4 = rotation2.getPitch();
        if (this.getOverShoot() && f > this.getOvershootTrigger() && nt <= 0) {
            n = AimSettings.n4;
            AimSettings.n4 = n + 1;
            nQ = 0.0f;
            if (AimSettings.n4 >= this.M()) {
                AimSettings.n4 = 0;
                nt = RandomUtil.f(new IntRange(this.getOvershootMinTicks(), this.getOvershootMaxTicks()), false, null);
                float f5 = yawDirection = f3 - rotation.getYaw() > 0.0f ? 1.0f : -1.0f;
            }
        }
        if (this.getHeadUp()) {
            n = o;
            o = n + 1;
            if (n > p) {
                p = RandomUtil.f(new IntRange(this.getHuIntervalMin(), this.getHuIntervalMax()), false, null);
                o = 0;
                n_ = RandomUtil.f(new IntRange(this.getHuTicksMin(), this.getHuTicksMax()), false, null);
            }
        }
        if (this.getHeadUp() && n_ > 0) {
            n = n_;
            n_ = n + -1;
            f3 += (f4 -= this.randomSpeed(l4, RangesKt.rangeTo(this.F5(), this.g2()), true)) * 0.1f;
        }
        if (this.s() && S >= 0) {
            Pair pair = (Pair)((List)clickPatterns.get(b)).get(4 - S);
            f3 += (float)((Number)pair.getFirst()).doubleValue();
            f4 += (float)((Number)pair.getSecond()).doubleValue();
            int n6 = S;
            S = n6 + -1;
        }
        if (this.getFilterLowDeltas() != AimSettingsFilterLowDeltasMode.OFF) {
            float f6 = rotation.getYaw() - f3;
            float f7 = rotation.getPitch() - f4;
            float f8 = this.getLowDeltaThreshold();
            float f9 = Math.abs(f6);
            boolean bl3 = 0.0f <= f9 ? f9 <= f8 : false;
            if (bl3) {
                f3 = this.getFilterLowDeltas() == AimSettingsFilterLowDeltasMode.REMOVE ? rotation.getYaw() : rotation.getYaw() + RandomUtil.p(RangesKt.rangeTo(this.getReplaceMin(), this.Fv()), false, null);
            }
            f8 = this.getLowDeltaThreshold();
            f9 = Math.abs(f7);
            boolean bl4 = 0.0f <= f9 ? f9 <= f8 : false;
            if (bl4) {
                f4 = this.getFilterLowDeltas() == AimSettingsFilterLowDeltasMode.REMOVE ? rotation.getPitch() : rotation.getPitch() + RandomUtil.p(RangesKt.rangeTo(this.getReplaceMin(), this.Fv()), false, null);
            }
        }
        Rotation rotation3 = Globals.getPlayer().getBoundingBox().intersects(entity.getBoundingBox()) ? new Rotation(f3, this.clamp(f4, RangesKt.rangeTo(this.getPitchITMin(), this.getPitchITMax()), n5), false, null, 12, null) : new Rotation(f3, this.clamp(f4, RangesKt.rangeTo(this.getPitchMin(), this.getPitchMax()), n5), false, null, 12, null);
        return rotation3;
    }

    private final boolean isHolding() {
        int n;
        return this.v() && (nF = (n = nF) + -1) > 0;
    }

    /*
     * Unable to fully structure code
     */
    private final Rotation getJitter(float var1_1, short var2_2, float var3_3, int var4_4, short var5_5) {
        block21: {
            block20: {
                v0 = var6_6 = ((long)var2_2 << 48 | (long)var4_4 << 32 >>> 16 | (long)var5_5 << 48 >>> 48) ^ AimSettings.bb;
                var16_7 = v0 ^ 1302247226445L;
                var35_8 = v0 ^ 53891102538963L;
                var43_9 = (float)(Globals.now() % AimSettings.zkmBootstrap3("a", (int)15333, (long)(4763697910615737935L ^ var6_6))) / 500.0f;
                var44_10 = RandomUtil.p(RangesKt.rangeTo(this.f(), this.L()), false, null);
                var45_11 = RandomUtil.p(RangesKt.rangeTo(this.FA(), this.h()), false, null);
                var46_12 = RandomUtil.p(RangesKt.rangeTo(this.e(), this.F7()), false, null);
                var47_13 = RandomUtil.p(RangesKt.rangeTo(this.FJ(), this.z()), false, null);
                var48_14 = var1_1;
                var42_15 = ZkmReferences.zkmBootstrap("\u00c5", (long)648650617482517211L, (long)var6_6);
                var49_16 = var3_3;
                if (var42_15 != null) ** GOTO lbl17
                switch (AimSettings$WhenMappings.u[this.FT().ordinal()]) {
                    case 1: {
                        var48_14 += (float)Math.sin(var43_9 * var44_10) * var45_11;
lbl17:
                        // 2 sources

                        v1 = var42_15;
                        if (var5_5 >= 0) ** GOTO lbl23
                        if (v1 == null) break block20;
                    }
                    case 2: {
                        var48_14 += AimSettings.yawSpring.update(var16_7) * var45_11;
                        v1 = var42_15;
lbl23:
                        // 2 sources

                        if (var4_4 <= 0) ** GOTO lbl29
                        if (v1 == null) break block20;
                    }
                    case 3: {
                        var48_14 += ((float)Math.sin((float)Globals.getPlayer().age * var44_10) + this.randomSpeed(var35_8, RangesKt.rangeTo(-0.1f, 0.1f), true)) * var45_11;
                        v1 = var42_15;
lbl29:
                        // 2 sources

                        if (var2_2 < 0) ** GOTO lbl35
                        if (v1 == null) break block20;
                    }
                    case 4: {
                        var48_14 += this.randomSpeed(var35_8, RangesKt.rangeTo(-var45_11, var45_11), true);
                        v1 = var42_15;
lbl35:
                        // 2 sources

                        if (var5_5 >= 0) ** GOTO lbl39
                        if (v1 == null) break block20;
                    }
                    case 5: {
                        v1 = var42_15;
lbl39:
                        // 2 sources

                        if (v1 != null) {
                            break;
                        } else {
                            ** GOTO lbl42
                        }
                    }
                    {
lbl42:
                        // 2 sources

                        break block20;
                    }
                }
                throw new NoWhenBranchMatchedException();
            }
            switch (AimSettings$WhenMappings.u[this.Fd().ordinal()]) {
                case 1: {
                    var49_16 += (float)Math.sin(var43_9 * var46_12) * var47_13;
                    v2 = var42_15;
                    if (var5_5 > 0) ** GOTO lbl54
                    if (v2 == null) break block21;
                }
                case 2: {
                    var49_16 += AimSettings.pitchSpring.update(var16_7) * var47_13;
                    v2 = var42_15;
lbl54:
                    // 2 sources

                    if (var2_2 < 0) ** GOTO lbl60
                    if (v2 == null) break block21;
                }
                case 3: {
                    var49_16 += ((float)Math.sin((float)Globals.getPlayer().age * var46_12) + this.randomSpeed(var35_8, RangesKt.rangeTo(-0.1f, 0.1f), true)) * var47_13;
                    v2 = var42_15;
lbl60:
                    // 2 sources

                    if (var4_4 <= 0) ** GOTO lbl66
                    if (v2 == null) break block21;
                }
                case 4: {
                    var49_16 += this.randomSpeed(var35_8, RangesKt.rangeTo(-var47_13, var47_13), true);
                    v2 = var42_15;
lbl66:
                    // 2 sources

                    if (var5_5 >= 0) ** GOTO lbl70
                    if (v2 == null) break block21;
                }
                case 5: {
                    v2 = var42_15;
lbl70:
                    // 2 sources

                    if (v2 != null) {
                        break;
                    } else {
                        ** GOTO lbl73
                    }
                }
                {
lbl73:
                    // 2 sources

                    break block21;
                }
            }
            throw new NoWhenBranchMatchedException();
        }
        return new Rotation(var48_14, var49_16, false, null, (int)AimSettings.zkmBootstrap2("x", (int)10879, (long)(3070687227267349316L ^ var6_6)), null);
    }

    private final float randomSpeed(long l, ClosedRange closedRange, boolean bl) {
        int n = (int)(((l = bb ^ l) ^ 0xF506DC5D19FL) >>> 32);
        if (bl) {
            if (this.getJitterDistribution() == AimSettingsSpeedDistributionMode.FLAT) {
                return RandomUtil.p(closedRange, false, null);
            }
            return ((Number)closedRange.getStart()).floatValue() + (((Number)closedRange.getEndInclusive()).floatValue() - ((Number)closedRange.getStart()).floatValue()) * this.clamp(((float)RandomUtil.nextGaussian() + 3.0f) / 6.0f, RangesKt.rangeTo(0.0f, 1.0f), n);
        }
        if (this.Fs() == AimSettingsSpeedDistributionMode.FLAT) {
            return RandomUtil.p(closedRange, false, null);
        }
        return ((Number)closedRange.getStart()).floatValue() + (((Number)closedRange.getEndInclusive()).floatValue() - ((Number)closedRange.getStart()).floatValue()) * this.clamp(((float)RandomUtil.nextGaussian() + 3.0f) / 6.0f, RangesKt.rangeTo(0.0f, 1.0f), n);
    }

    @Override
    @NotNull
    public Vec3d getAimPoint(long l, @NotNull Entity entity) {
        Vec3d vec3d;
        long l2 = l;
        long l3 = l2 ^ 0xC09ABE29107L;
        long l4 = l2 ^ 0x3C25181CD199L;
        Intrinsics.checkNotNullParameter(entity, "target");
        if (Globals.getPlayer().isGliding()) {
            Vec3d vec3d2 = entity.getEyePos();
            Intrinsics.checkNotNullExpressionValue(vec3d2, "getEyePosition(...)");
            return vec3d2;
        }
        float f = (float)(Globals.now() % 10000L) / 500.0f;
        switch (AimSettings$WhenMappings.u[this.getPointJitter().ordinal()]) {
            case 1: {
                vec3d = new Vec3d((float)Math.sin(f * this.getPointHFreq()) * this.getPointHRange(), (float)Math.sin(f * this.FS()) * this.getPointVRange(), (float)Math.sin(f * this.getPointHFreq()) * this.getPointHRange());
                break;
            }
            case 2: {
                vec3d = new Vec3d(pointHSpring.update(l3) * this.getPointHRange(), s.update(l3) * this.getPointVRange(), pointVSpring.update(l3) * this.getPointHRange());
                break;
            }
            case 3: {
                vec3d = new Vec3d(((float)Math.sin((float)Globals.getPlayer().age * this.getPointHFreq()) + this.randomSpeed(l4, RangesKt.rangeTo(-0.1f, 0.1f), true)) * this.getPointHRange(), ((float)Math.sin((float)Globals.getPlayer().age * this.FS()) + this.randomSpeed(l4, RangesKt.rangeTo(-0.1f, 0.1f), true)) * this.getPointVRange(), ((float)Math.sin((float)Globals.getPlayer().age * this.getPointHFreq()) + this.randomSpeed(l4, RangesKt.rangeTo(-0.1f, 0.1f), true)) * this.getPointHRange());
                break;
            }
            case 4: {
                vec3d = new Vec3d(this.randomSpeed(l4, RangesKt.rangeTo(-this.getPointHRange(), this.getPointHRange()), true), this.randomSpeed(l4, RangesKt.rangeTo(-this.getPointVRange(), this.getPointVRange()), true), this.randomSpeed(l4, RangesKt.rangeTo(-this.getPointHRange(), this.getPointHRange()), true));
                break;
            }
            case 5: {
                Vec3d vec3d3 = Vec3d.ZERO;
                vec3d = vec3d3;
                Intrinsics.checkNotNullExpressionValue(vec3d3, "ZERO");
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        Vec3d vec3d4 = vec3d;
        switch (AimSettings$WhenMappings.x[this.getPoint().ordinal()]) {
            case 1: {
                Vec3d vec3d5 = entity.getBoundingBox().getCenter().add(vec3d4);
                Intrinsics.checkNotNullExpressionValue(vec3d5, "add(...)");
                return vec3d5;
            }
            case 2: {
                Vec3d vec3d6 = entity.getEyePos().add(vec3d4);
                Intrinsics.checkNotNullExpressionValue(vec3d6, "add(...)");
                return vec3d6;
            }
            case 3: {
                Vec3d vec3d7 = EntityUtil.getPos(entity).add(basePointOffset).add(vec3d4);
                Intrinsics.checkNotNullExpressionValue(vec3d7, "add(...)");
                return vec3d7;
            }
            case 4: {
                Vec3d vec3d8 = EntityUtil.getPos(entity).add(aimPointOffset).add(vec3d4);
                Intrinsics.checkNotNullExpressionValue(vec3d8, "add(...)");
                return vec3d8;
            }
            case 5: {
                Box box = entity.getBoundingBox().shrink((double)this.getPointHRange() + 0.1, (double)this.getPointVRange() + 0.2, (double)this.getPointHRange() + 0.1);
                Intrinsics.checkNotNullExpressionValue(box, "contract(...)");
                Box box2 = box;
                return new Vec3d(this.clamp(Globals.getPlayer().getEyePos().x, RangesKt.rangeTo(box2.minX, box2.maxX)), this.clamp(Globals.getPlayer().getEyePos().y, RangesKt.rangeTo(box2.minY, box2.maxY)), this.clamp(Globals.getPlayer().getEyePos().z, RangesKt.rangeTo(box2.minZ, box2.maxZ)));
            }
        }
        throw new NoWhenBranchMatchedException();
    }

    public final double clamp(double d, @NotNull ClosedFloatingPointRange closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "range");
        double d2 = ((Number)closedFloatingPointRange.getStart()).doubleValue();
        double d3 = ((Number)closedFloatingPointRange.getEndInclusive()).doubleValue();
        if (d2 > d3) {
            double d4 = d3;
            d3 = d2;
            d2 = d4;
        }
        if (d < d2) {
            return d2;
        }
        if (d > d3) {
            return d3;
        }
        return d;
    }

    public final float clamp(float f, @NotNull ClosedFloatingPointRange closedFloatingPointRange, int n) {
        float f2;
        block8: {
            float f3;
            float f4;
            block7: {
                long l = 0L;
                long l2 = ((long)n << 32 | l << 32 >>> 32) ^ bb;
                Intrinsics.checkNotNullParameter(closedFloatingPointRange, (String)((Object)AimSettings.zkmBootstrap("k", (int)28189, (long)(0x45CC17617B85251L ^ l2))));
                float f5 = ((Number)closedFloatingPointRange.getStart()).floatValue();
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2615542583285397399L, (long)l2);
                f4 = ((Number)closedFloatingPointRange.getEndInclusive()).floatValue();
                f3 = f5 == f4 ? 0 : (f5 > f4 ? 1 : -1);
                if (callSite == null) {
                    if (f3 > 0) {
                        float f6 = f4;
                        f4 = f5;
                        f5 = f6;
                    }
                    f3 = f == f5 ? 0 : (f < f5 ? -1 : 1);
                }
                if (n <= 0 || callSite != null) break block7;
                if (f3 < 0) {
                    return f5;
                }
                f2 = f;
                if (callSite != null) break block8;
                float f7 = f2 - f4;
                f3 = f7 == 0.0f ? 0 : (f7 > 0.0f ? 1 : -1);
            }
            if (f3 > 0) {
                return f4;
            }
            f2 = f;
        }
        return f2;
    }

    public final int clampInt(int n, @NotNull IntRange intRange, int n2) {
        int n3;
        block8: {
            int n4;
            int n5;
            block7: {
                int n6 = 0;
                int n7 = 0;
                long l = ((long)n6 << 56 | (long)n7 << 32 >>> 8 | (long)n2 << 40 >>> 40) ^ bb;
                Intrinsics.checkNotNullParameter(intRange, (String)((Object)AimSettings.zkmBootstrap("k", (int)28189, (long)(0x45CFF1A76E81925L ^ l))));
                int n8 = intRange.getStart();
                n5 = intRange.getEndInclusive();
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8014287973479775459L, (long)l);
                n3 = n8;
                n4 = n5;
                if (callSite == null) {
                    if (n3 > n4) {
                        int n9 = n5;
                        n5 = n8;
                        n8 = n9;
                    }
                    n3 = n;
                    n4 = n8;
                }
                if (n2 < 0 || callSite != null) break block7;
                if (n3 < n4) {
                    return n8;
                }
                n3 = n;
                if (callSite != null) break block8;
                n4 = n5;
            }
            if (n3 > n4) {
                return n5;
            }
            n3 = n;
        }
        return n3;
    }

    public final void randomizeYaw() {
        if (this.getYaw360() && RandomUtil.f(new IntRange(0, 100), false, null) >= 100 - this.getYaw360Chance()) {
            q = k = RandomUtil.f(new IntRange(7, 9), false, null);
            float f = randomSign = Globals.getPlayer().age % 2 == 0 ? -1.0f : 1.0f;
        }
        if (this.s()) {
            S = 4;
            b = RandomUtil.f(new IntRange(0, 4), false, null);
        }
        if (this.v() && RandomUtil.f(new IntRange(0, 100), false, null) >= 100 - this.getHoldChance()) {
            nF = RandomUtil.f(new IntRange(this.getHoldMin(), this.getHoldMax()), false, null);
        }
        double d = 0.3 - (double)this.getPointHRange() / 2.0;
        double d2 = 1.81 - (double)this.getPointVRange() / 2.0;
        ClosedFloatingPointRange<Double> closedFloatingPointRange = RangesKt.rangeTo(-d, d);
        ClosedFloatingPointRange<Double> closedFloatingPointRange2 = RangesKt.rangeTo(0.0, d2);
        aimPointOffset = new Vec3d(this.clamp(RandomUtil.k(closedFloatingPointRange, false, null), RangesKt.rangeTo(-0.3, 0.3)), this.clamp(RandomUtil.k(closedFloatingPointRange2, false, null), RangesKt.rangeTo(0.0, 1.81)), this.clamp(RandomUtil.k(closedFloatingPointRange, false, null), RangesKt.rangeTo(-0.3, 0.3)));
    }

    public final void getMicroOffset(@NotNull Entity entity) {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        block11: {
            double d7;
            block10: {
                Intrinsics.checkNotNullParameter(entity, "target");
                double d8 = 0.003;
                double d9 = 0.03;
                double d10 = 0.001;
                double d11 = 0.03;
                d7 = this.getPointHRange();
                d6 = entity.getBoundingBox().getLengthX();
                d5 = entity.getBoundingBox().getLengthY();
                d4 = entity.getBoundingBox().getLengthZ();
                if (Intrinsics.areEqual(jitterOffset, Vec3d.ZERO)) {
                    jitterOffset = new Vec3d(RandomUtil.a(-d7, d7, false, null), RandomUtil.a(-d7, d7, false, null), RandomUtil.a(-d7, d7, false, null));
                }
                d3 = AimSettings.jitterOffset.x;
                d2 = AimSettings.jitterOffset.y;
                d = AimSettings.jitterOffset.z;
                if (AimSettings.basePointOffset.x >= (d6 - d7) / 2.0) {
                    d3 = -RandomUtil.a(d8, d9, false, null);
                }
                if (AimSettings.basePointOffset.x <= -(d6 - d7) / 2.0) {
                    d3 = RandomUtil.a(d8, d9, false, null);
                }
                if (AimSettings.basePointOffset.y >= d5) {
                    d2 = -RandomUtil.a(d10, d11, false, null);
                }
                if (AimSettings.basePointOffset.y <= d7) {
                    d2 = RandomUtil.a(d10, d11, false, null);
                }
                if (AimSettings.basePointOffset.z >= (d4 - d7) / 2.0) {
                    d = -RandomUtil.a(d8, d9, false, null);
                }
                if (AimSettings.basePointOffset.z <= -(d4 - d7) / 2.0) {
                    d = RandomUtil.a(d8, d9, false, null);
                }
                if (-0.03 <= d3 ? d3 <= 0.03 : false) break block10;
                if (-0.03 <= d2 ? d2 <= 0.03 : false) break block10;
                boolean bl = -0.03 <= d ? d <= 0.03 : false;
                if (!bl) break block11;
            }
            jitterOffset = new Vec3d(RandomUtil.a(-d7, d7, false, null), RandomUtil.a(-d7, d7, false, null), RandomUtil.a(-d7, d7, false, null));
        }
        jitterOffset = new Vec3d(d3, d2, d);
        Vec3d vec3d = basePointOffset.add(jitterOffset);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        basePointOffset = vec3d;
        basePointOffset = new Vec3d(this.clamp(AimSettings.basePointOffset.x, RangesKt.rangeTo(-d6, d6)), this.clamp(AimSettings.basePointOffset.y, RangesKt.rangeTo(0.0, d5)), this.clamp(AimSettings.basePointOffset.z, RangesKt.rangeTo(-d4, d4)));
        basePointOffset.add(RandomUtil.a(-0.03, 0.03, false, null), 0.0, RandomUtil.a(-0.03, 0.03, false, null));
    }

    private static final boolean _clinit_$lambda$0() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$1() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$2() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$3() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$4() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$5() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$6() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$7() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$8() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$9() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$10() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$11() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$12() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$13() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$14() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$15() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$16() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$17() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$18() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$19() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$20() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$21() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$22() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$23() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$24() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$25() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$26() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$27() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$28() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$29() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$30() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$31() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$32() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$33() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$34() {
        return INSTANCE.isAdvanced() && INSTANCE.getOverShoot();
    }

    private static final boolean _clinit_$lambda$35() {
        return INSTANCE.isAdvanced() && INSTANCE.getOverShoot();
    }

    private static final boolean _clinit_$lambda$36() {
        return INSTANCE.isAdvanced() && INSTANCE.getOverShoot();
    }

    private static final boolean _clinit_$lambda$37() {
        return INSTANCE.isAdvanced() && INSTANCE.getOverShoot();
    }

    private static final boolean _clinit_$lambda$38() {
        return INSTANCE.isAdvanced() && INSTANCE.getOverShoot();
    }

    private static final boolean _clinit_$lambda$39() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$40() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$41() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$42() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$43() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$44() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$45() {
        return INSTANCE.isAdvanced() && INSTANCE.getHeadUp();
    }

    private static final boolean _clinit_$lambda$46() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$47() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$48() {
        return INSTANCE.isAdvanced() && INSTANCE.getYaw360();
    }

    private static final boolean _clinit_$lambda$49() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$50() {
        return INSTANCE.isAdvanced() && INSTANCE.v();
    }

    private static final boolean _clinit_$lambda$51() {
        return INSTANCE.isAdvanced() && INSTANCE.v();
    }

    private static final boolean _clinit_$lambda$52() {
        return INSTANCE.isAdvanced() && INSTANCE.v();
    }

    private static final boolean _clinit_$lambda$53() {
        return INSTANCE.isAdvanced() && INSTANCE.v();
    }

    private static final boolean _clinit_$lambda$54() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$55() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$56() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$57() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$58() {
        return INSTANCE.isAdvanced();
    }

    private static final boolean _clinit_$lambda$59() {
        return INSTANCE.isAdvanced() && INSTANCE.getFilterLowDeltas() != AimSettingsFilterLowDeltasMode.OFF;
    }

    private static final boolean _clinit_$lambda$60() {
        return INSTANCE.isAdvanced() && INSTANCE.getFilterLowDeltas() == AimSettingsFilterLowDeltasMode.REPLACE;
    }

    private static final boolean _clinit_$lambda$61() {
        return INSTANCE.isAdvanced() && INSTANCE.getFilterLowDeltas() == AimSettingsFilterLowDeltasMode.REPLACE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                AimSettings.bb = ZkmKeys.a(3056841102570300966L, 1452607531197523751L, MethodHandles.lookup().lookupClass()).a(255370550449466L);
                                var31 = AimSettings.bb ^ 22816064587081L;
                                AimSettings.hb = new HashMap<K, V>(13);
                                AimSettings.zkmSetFlag(new int[3]);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[197];
                                var27_4 = 0;
                                var26_5 = "\u00ca\u00d9\u009c\ft\u001ak_\u00e5\u00a2S9py\u00b3\u0089[F\u0011t\u001f\u008a\u00b9\u00f7\u00ae\u00a4p\u00ee\u00c2~\u0089\u0010\u0018\u0082\u0004\u009e\u008f\u008e\u0011E\u00ec\u00b3Q\u0018!\u001e\u0010\u00e0\u00a8\u00f7\u00bf\u0081\u0003G\u00f3\u00ff\u0083(M\u00dc\u00d6`\u008dB\u00fc\u00b9\u00e7\u0002\u00e5\u00a1\u00a1\u0015@5\u00ea6\u00ad\u00b4\u00faR\u0017\u00ed\u00de\u00cb\u00f2S\u00d2\u00a8\u0003?\u0088az\u0098\u00d7\u0091\u00bau \u00e5F\u0093e\u00b2\u0019\u0001*d\u00eb2\f2)\\$t\u00c4\u00e4qQ\u00c7\u009d\u00ee\u008b\u0006\u001e\u00d0G\u00d6\u0080\u00e5 \u00e0\u0003\u00c4`\u00b3>\u00c9<\u0005?\u00af(Z;\u00c73\u0084\u00cf\u001bC\u00d4\u0081?\u00ce\u00ebFX\u00d4\u0095\u00b2\u00a7\u00ba0\u0098y\u00eb\u00fas02A\u00a7\u008a\u00e7\u0093g\u00f0\u0082\u0016-$\u00bf\u00a6Xgo\u008f{r^Po\u001du\u00d7\u0087\u00ef{\u00ee\u00d0\u0012:\u008a\"\u00bd|\u00dd[T;\u00d3(\u00a6!\u0091\u00ec\u00c6o\u0010\u00f08\u00b2x\u00c8dQ\u00bd1;\u00f6a\u0003Ep\u00c0*Z\u009a\u0090S\u00e8\u00fc\u00e3CQ\u00cf\u0087\u0091\u00a9\u00b8\u001e\u00a3\u0018\u00f3\u0090':,x\u00cdf_q\u00a6z\u00f7WO\u0098F\u0098\u00bc\u0083\u00f9x{t \u00d3\u00a7\u0096\u00b2\tp2~\u00c9\f\u00f5w\u00f0\u0006\u00ect\u00be\u00afo\u00b9\u00871\u00d1\u00d9\u00e5\u00c6\u00b7\u00b7e\u00f9\u007fS y,\u00a0\u000e\u00d6\u00dc}\u00e1\u00c0\u00f2\u0083Pw~pc\u007fX\u00de\u00ab\u0003\u008fD\u00bc\u00c3\u00bc:\u00d0\u00e7\u00b2\u00a5\u001c\u0018\u001fW\u00e2\u00d3\u0088,\u0004\u00b7\u0014E\u00d0S\u00f3\u00d3^OZ\u00fb\u00153{\u0000t\u001b\u0018\u00f2\u00902y\u001cb=\u00df=\u00b1v\u009d\u00ea\u0092\u00c6\u0011\u00dc$I\u00bb\u0003r\u0083?(\u0088P\u0080\u00ae\u00c7\u0010\u00fe\u00fd\u009e\u00e8\u0015\u00ff\u00c3\u009fZD8\u00f2*\u00e3\u00a9\u0086\u00edR\u00bb\u0084kV-\u00b7e \u00f8\u00f2\u00b8*z-\u00e2\u00e5 e]\u00d4\u00f4#4\u00b0.\u0004\u00c4\u00835 GE\u0092\u0080\u001d55\u00a4\u0005\u008e#\u001e\u0094\u00a3\u0089\u00c8\u00f7)\u00b5(\u0019\u0082Az\u00ff\u0000Y\u00b9\u0089\u0087\u00a27W=a\u0010b\u0003ds\u00a4\u00f8\u00dc\u009e\u00da\u00b6\t\u00dd\u00fa\u00a6\t\u00bd\u00a2\u00b7\u00d4\u00cf/,\u00d8N \u00b9\u00e4 B\u00f4\u00c3l\u00e4\u0007\u00b6\u008d\u00b3\u00f5\u00b8M\u00c6-\u00004q\u008c\u00c5\u00ee\u00dd\u0013\f\u00fbI\u00cd\u008c`u(\u007fk\u00fb3\u0096\u00ccK\u00e0R\u0000uz\u00ff,7)\u000b|l\u008c\u00ab\u00c1\u00f0\u001c\u00c6\f\u00cdK\u00173\u0081C,\u00bd\u00cb\u00a3A@\u0095@ <\u0084\u00de\u00b5\u00b8L7\u00c9#x\t\u00b0\u00a7\u0097xZH\u00bc\"U\u0017\n\u00c1b\u008d\u00b5\u00846 KkD(gu\u00f8\u00dfE\u0083\u0017\u00de,\u009b\u00d7\u00ccD\u00e8\u0019\u0013\u00ea]\u008eF\u00c2\u00ce\u00a5\u008d\u00f1i2\u00b7\u0090\u00c2\u00e0\u008aZ\u0081\u00bd\u00a0\u00c7\u00f6\u00ff\u00bf \u00cd\u00a0\u00cf5\u00c3\u00a1\u00f1mL]\u00a4Z\u00c0\u00c8\f}\u0085\u00dd\u00bd\u0089\u00a8/\u00ff\u0084\u00a4 \u0003v4\u00b7@\u00b1\u0010\u00d2c+\u00de\u00a1\rSV\u00db\u00fb\u00ed\u00f3;(Z\u00a2(\u00b9d\u0006\u00ec\u00e9d\u00df\u00047\u00f1\u00c1J\u0090\u0091\u00de\\\f\u000f_[\u001c\u00c16\u0002SO\u00e2\u00c9\u0000\u0095V\u0013o\u0003n\u0013\u00b3\u0093\u00bb%(\\[\u00f1\u000f|f\u00f0PY\u0084H\u00d5\u00e5\u0095\u0091\u00aa\u00a7L\u0005\u0092\u00f9\u0014\u00b0\u00df\u00a1F\u00c3\u000e\u00acqr\u00c5T\u0016\u0010N\u00d9\u001b\u00a3 (\u00c8\u00e7'\u00e5N\u00cc\b\u0098\u001c\u00c4\u0084\u00cdb\u000fC\u0015wx\u00fd\u00bdW(_b\u00b63\u00ce\u00bdz\u0012\u00e8\u0016,\u00b3\u0087\u00d9K\u0085\u00a8\u00d0\u0018c\u00ab\u00ea\u0081\u001d\u00f062\u00c5\u00c6\u0081\u00c1\u001b\u0000\u00a4\u001b\u00a0\u00b7\u00c0\u00ad\u00b2&\u00a6\u00d4\u0018x\u008f\b\u00a7\u008dVw\u00f2?\u0091\u00b5\u00e4\u00fd\u00ce\u008d\u001f\u00ba\u0095\u0083\u00f7~(\u00a7\u00f2\u0010\u0099\u00c7S\u00b8\u00a3\u00bb\u00fa\u0018\u00df\u00a2`g\u00ed\u0096~r \u00a5\u00c2\u000e\u00d8\ni[\u00f6\u00df\u0092h\u008f\u00b9o\u00adq\u00c7\u00b4\u00ca\u00c1\u00bdMZ\u0018\u00daL\u00c4_\u0098\u0011\u00e0\u009e \u00d7{\u0001\u0095|\u00d6\u00f9\u0018\u00d1\u00bd#\u00f3\u0086\u00fa\u00bce\u0019\u0007\u009d\u009et\u0084\u008a1$\u00e0^n\u0006)\u0099Q\u0018\u00a4W\u0003\t\u00e5w\u00b0w\u0003=~\u00f3@Zm\u00059\u00b1\u0088T#^~p(\u00ba\u00a2\u0092\u009f\u0092\u00e5\u0004\u00f0\u00d2\u0001\u00ba\u00fe \u008bE\u00b6\u00b9\u00b8o'G6\u00d1\u0003D\u00ea\u00bd\u00ceD\u0018\u0080\u00b5\u0096\u001cE\u0012\u00ecC\u00a1\u00e4\u0018\u00c2\u0014\u00d4.\u009e(z\u00f8c\u00c0\u009b\u00ecW}k\u001bcSdP\u0090\u00b3\u00cf+ }\u00fe\u00015pbb\u00d9jE~=z\u00e4&\u00dc\u0088\u008aF3\u0080@&\u00d3\u00feB\u0018\b_\u00d5~y(\u0088\u00bdt\u00ff\u008d\u00e9$\u00ff\u00ed)|\u00cfC\t\u00f6\u0088\u001ab\u00e7\u00b9\u00c5\u00d7\b\u0094\u008bm\u00b73\u00d0\u00e9\u0091%\u00ae\u00aa[\u0099\u0012\u00c1$\u00d6 \u001b\u008e\u008b\u00bf\u00a1\u00a5\u00a2\r1p\u00e9`\u0080\u00d3\u00fe9\u00c4\u0013\u00d1\u0091\u0007\u00dd\u0094\u00f9\u00ee\u001a\u0098\u00d7\u001a[\u001f\u00fc  ^:\u00a1\"\u0005\u00d1\u0091s_O\u009c\u0089\u0005\u00d8\u00c8\u0013\\kC{\u00ac\u0016\f\u00d0\u0004R\u00b2\u00d1\u008fP\u008d \u00c3\u00a8(\u00e6\rq\t\u00fb\u00da\u00a3\u00ac\u0081\u00fd\u0097r\u00a2\u00f8\u00a3\u00a50_d\u00d2M\u0019\u00c8\u00fa\u00a6\u00d5\u0089\u0007\u0001\u0018\u00e3\u0013F\u00c9\u00ea\u00b8\u009f\u00a9~MXi?\u0094\u000fJ\u00d1(\"\u00e1\u00cd!m\u00c1(\u00c1Za\u009cq\u0095\u00f0\u0089\u00fd\u0084\u00f9\u0095\u0000a\u008a\u00ffh]\u00f2z\u00161\u00b8\u0019\u00b2,\u001c\u00b4\u00ef;\u0094\u0016C\u00dd/a\u008c\u0005/\u00f6\u0018\u00f9\u00f2D\u00d2\u00826C)\"L\u0080\u00b1\u00cfh\u00ef\u007fo3\u0088\u001chtj:\u0018\u00f8p^\u00b4U\u00bbt0O\u0007\u0004\u00b0\u00c9\u0087M\u0012)\u0016)\u001d9~y\u00fe e\u0094\u00efi!C\u0015d9IE\u00b6{\u00dc1\u0016\u00ec\u00ee\u001d\u0003\u00b1#\u0000U\u0093hTo\u00bc\u00e8\\\u001a(\u00e4\u00fcU\u0016\u0081\u00d8w\u0098_^\u00eb+\u001d\u00ab\u00bb\u008b\u00b91I#_\u00f4|(\u00fd\u00e2\u0080H\u00d5\u0007\u00f3\u00e1}\u00e5$\u0013@\u00b7\u00eao\u0018\u00fdI\u00e99\\\u001a\u008c\u00b4\u00f9\u0002\u0084\u0004\u0019\u00d38\u0099\u0086Qk\u00e9\u00d4\u0094\t\u008b\u0018[\u00e0\u00eeX\u00a2\u0014\u00fb>\u00c4\u00a6+5\u00da\u00f5p\u00eb\u0092e\u00e9Ub/\u000f\u00c6\u0018\u00e6oR\u00d0\u000f$e\u00c22\u00be\u00d0\u00acg\u00f1c;0\u008a\n\u00b9\u00b5l\u0002\u00ab \u00e0md\u00d4n\u0012\u00ec\u00ed\u00c1>\fMwT\u008c\u00f2\u00c8s\u00f8d!,\u00cc\u0092P/\u0017\u001e\u00cf\u0014&\u00e2(\u00af/\u00fd\u00e8@!WJ3\u00ae\u0085\u00f8s;\u00bb%\u00c7\u00d3\u00e7k\u00df\u00ad\u00f3\u00cer [\u0015\u001f\u00f3\u0019\u008f}~^\u0082\u00ad\u0003\u00bb\u0016\u00188D\u000b\u009d;\u00d8\u00dc\u00f6\u0094\u000f\u00f1\u0003<m\u00b2\u00e9\u00bce\u0084}\u00e4J\u00ed\u0085 >\u00cau\u00aa)\u00a9\u00037@N\u0083\u00a0\u00ac\u00c8\u00d3\u00caG\u001dDa\u00d0\u0084:\u00c5\u00f7\u00a7e\u0003\u00b2%\u00f0<\u0010%\u00b4Y\u00b6M(K\u0006A\u001b%\u00e8\u008b1\u0012\u0010 \u00b7\u00f9@Y\u00a1\u008f/Xw4\u00e3B\u00af\u009f\u00a8\u00d9\u00c0\u00e6\u00b9O\u0090oF\u0017\u00d5\u0091\u00b5\f\u00ccv)\u00df(\u00a1r#3\u00d3\u00fe\u00df\u0098hl\u00d5K\u000b\u0007\u00e3\u008b3\u00c4\u00cc\u00c1(\u00e9M\u0006\u0081[\u00a0d\u00be\u008e\u00c8a\u00bb\u0098\u0002\u00fd\u0003\u0084@\u00ce8\u00cf)\u0018k\u00bcj\u00c7\u001c\u00cf\u008e\u00c8\u00b9)\u00aa8\u0014\u00bck\u0005*\u00ca#x\u008aN\u00ca\u00d2[\u00fc\u00df.\u00e0\u00e0\u008br\u00a8n\u00d3B\u00dd.\u00fe5\u0081\u00fc\u00aa\u00e3\u00d4{2\u00a4Hn\f\u0099\u00d4 \u0081\u008f=QMu\f_\u00e5\u00ac\u00d6?\u008f\u00ff\u0080\u00149\u008c=\u00ab\u00bd&\u00ad'\u00a1\u008c\u008a(\u009av\u000fq@6\u008cU\u0019}\u00956BV\u00a5\u001c\rp\u0014\u00e0\\\u001b*C\u008b\u00ccy\u00e6\u00a7\u00e4\u00ef\u00da\u00e8k\n\u00aeH\u0096=\u00bd4\u0014\u0083\u00d5\u00c5\u009d\u00f6\u00004Y\u0084\u00e1\u0081\u00ddYB\u00cc\u00a3\u00ac\u00c9\u0002D\u00fba\u00a2=\u00ca1\u0019 q\u00f9s\u009c\u00ef!q\u0094\u0086f\u001e\u00a5\u00d6\u00a7\u00a1\u008b\u00e2\u00e8\ba6\u00cc\u0013e$\u0003\u0094\u00fb\u00e8\u00f4K[(p+\u00d1fA\u00c7\u0001\u0087\u008a\u008a\u009a\u00c9\u00aa\u00f7Z\u000e\u0010\u0000\u00f1#2\\3\u00be\u0006'\u00050\u001d\u00d2\u009c\u00d0\u00b4SD\u00b3\u00ea\u00a1\u001e\u00e3 \u00d1\u00d5\u00f9\u00b1bd\u001e\\@o\u00b6o\u008c\u00d1\u00ff\u00aa\b\u00e1}\u0099Xx\u00a8\u00a0e\u00b1\u0083\u001d{JT\u00d0 \u00c2\u00abr\u008d\u0088\u008c)\u00b3\u0087\u00b9\u00b5E+5\f}\u00d5[?q\f\u00cf~\u000b\u00a3|\u001f\u009a\u008e\u0010\u0013:(fVn\u0099!\u00c4$\u00fe\u00eb\u0087\u008e\"8\u0092\u00cd&p!dP\u00c3\u0014!q\u00af:25\u008c\u00d7\u00d3\u001a\u009f,\u00fe$\fe$0(\u00a1\u0097\u00dc1}\\\u009d\u00d9\\S\u007f\u00a1\u0082|\u008d\u00d9F\u00a73\u00a3^n\u00e5\u0086z\u00c5\u0006k=A\u00cb/F\u0095\u00dd\u00d2\u0015\u00e4\u0004\u00b6 \u0081k\u008a{\u00e3\u00d7\u00e6s\u0003h\u00c8\u00cd\u00c3M\u00c6\"\u0016\u00c5\u001d\u00e8:\u00e3\u00e3Y\\\u0016\u00ec\u0013'\u0016\u0084\u00d9\u0018O\u0005B\u00ba!v\u00ee\u00bc*z\u00ae\u00bb\u00e0r\u0011lz\u0012\u0092W\u00dcn{m8\u00e0\u00c7\u0016\u00d2\u0014w\u00ac\u0019\u0097\u009e\fh\u00f6zZ\u00ceV\u0017\u00d4\u008e\u000b\u00c1\u00dfH\u00d5w\u00a1@=\u00e3{\u001f\u00d0\u008fT\u00927o\u00e2N\u00d7\u00c2s\u00d7LBRj\u00b4G#\u00c0uA\u00d1\u00d4 }\u0016\u0016\u00e9\u0087N2!\u00fa\u001d\u00b7`@\u00e4\u00b6\u00ab\u00b10j\u00f9F@\b\u00e9\u0017.-\u00c70l~\u00c4(\u001a\u0095\u00fa\u00de\u0003@(\u0089\u00a3\u0010\u00beu\u0002\u00e9k\u00fdU\u0016\u0099\u00d6\u0005<E\u0010\u00dd\u00aaq8\u00836>\u0082i\u0091\u00bc<\u00ca\u0012\u0017\u00d50=\u00da\u0097\u00ab\u00b1\u0018\u00a5\u008a\u00f5<D\u00b2?\u00137\u00c2\u00da\u00a9\u00ad~\u00a3\u00f1Y-\u00b3i\u00b8\u00caW\u0090c\u00b3B\u00d3U\u00ef>n\u001b\u00ad\u00ffWS\u00c6e\u00f5+\u00fc(\u0099\b\\<\u00f3.\u00bc^\u00a7\u001f\u008b\u0082\u001d`?\u0090\u00ab\u0006\u00ada\u00edy\u00e8\u00b0\u00a5K\u00d4\u00ed\u00ef|{\u0089u\u00a9w\u0088\u0017\u00f3\u0086l\u0018&\u0007\u0094;\u00b9.\u00ab\u0083\u00a6\u0099g5\u00c7\u0086\u0081\u00bc\\\u00dc\u00dd\u0086(\u00c1\u00e5T \u009f\u009dT\u0084\u008308\u00fc<\u0002\u00e4\u00cfl\u0004\u00e8N\u0090D2\u00011\u001cN\u00f9\u009f\u00c2ir\u001d;\u0096S \u00f2\u0080c%\u0088q\u00c6\u008f\u0016\u00aa\u001a[\u0007T\u00873\u00d6\u008f\u00fcj\u00b2yZ\u00beE\u00065(9\u008a\u00ecv \u0094\b\u00aa9\u00c4\u009e\u000f%\u00f7\u00f5x?KJ\u00af\u00a1x\u00c0H0\u00c3\u00c1\bn\u008d\u00ef/#>\u00cc\u0006\u001e <C>w\u0083\u00eb\u00b4^XF\u00e7\u00eau\u0093)U\u008a\u0091\u00c2\u0083\u00fd!D\u00a1\u00b0\u00ca6\u00caH\u0094z\u00a5 \u00cb\u0097\u009d+gc\u0099\u00c9'!\u00ac\u00a1\u0017\u00dce\u00ccJ\u00d7\u00c3 Q>\u00bcu\u00d3\u00a3<\u00d4\u0098\u00ad\u00b78\u0018\u00e9\u00a1\u00fbZF\u00c9\u00deT\u0082I\u0007B\u001c\u0007\u001e\u00be\u0016\u00f2R\u00b0\u009c\u008d\u00cdx(\u00ee\u00f2\u0095!\u00d7mB\u00d1LN\u00c9\u00adl7\u00ba\t\u00a6\u00c3S\u00e3oDp\u00abSG\u00be\u0096\u008b\u00f8]\u00bf\u00f2\u00b8\u0011N}\u00b9J> \u00f1\u00a4\u0005\u00b6\u0002\u0012;\u00cdq[\u00ea\f\u00b2\u0084\u001f:\u00b5\u0005\u009f\u00fe\u0002\u00b9Q\u00b1v?*\u0014\u001f.\u00869 \u00da\u009d\u00133\u00acg\u00a8\u00bc\u00a6&\u0080\u00e8\u00efx\u009d\u00afK\n\u0015O\u00874\u00cf\u00ae\u00f2\tQ\u00a4@\u00ec\u0083r(\u0003\u00c1z\u009b\u00f53U[o\u00bc\u0000WtOT\\\u007f\u00d6\u00a2eH/;m(r2\u001a\u009e\n\u0082^0VB\u00ecg\u0017\u0086\u00f5 V\u00a2b4\u00adH\u0089Vx\u001fn\u00f3\u00c3R\u0012FD\u00f8?\u0002\u00a8\u00ec\u00cbnU\u009b\u0087 \u00e6Y\u00a6\u0091\u0018\u009d\u00c66\u00ba0\u00f9\u001a\u00d1\u0014?x\\\u001a(\u00d6=1\u001f\u00cbB\u00d8\b[\u00c10\u00bd\u00d1I)\u00e4\u001e\u00fb\u00cb3\u00c1\u009b\u00d4\u00c0uS\u007f\u00f4)\u0007\u0091\u00cb\u000b\u0095TR\u0013\u00ac\u0080\u00845\u001c\u00f5h\u009ey *\u00c6\u00ba\u0099:]\u00a4\u00f0\u0092\u00a9u\u00f4\u0018\u000b\u00c8\u00d5~\u0017X\u00ce+ASN\u00ebS\u00e1a\u00d9\u00ff(\u00e1=\u0090\u00bd\u009a\u00ad \u008d\\\u00ea8\u00f7\u0002\u00a90;\u009e\u0098WT\u00b4\u00ceA\u00a1\u0085\u00cb7\u00cc~\u00d5\u00aa\u00a3\u00b9<\fKt%\u00b1\u0018\u0091\u00f5A\u00e2|\u00b6\u001f;_\u009b!9\u008a\u00a16\u0086*)=\u0089\u00df\u00d2\u00a9\u009e(\u00c3\u00dd2*\u00adys\"\u0083\u00f6\u0019/\u0090\u0016s1d\u00d2\u008f_X\u00d4\u00ec\u00b0\u00b9q[\u001eeD6\u00cf\u0012\u00f4\u00ad\u00e3\u00a1\u00df\u00b83(\u00e7\u0015F\u0001<-5\n\u0013&\u00a8\u00ce1\r\u00936\u00e6\u00a1\f\u00bd3;\u008a\u00ea\u0018\u0099e\u00d6\u00e6\u0011\u00c5a\u00afZ\u000f\u0097o[5\u00a0 \u008aP;*\u00ae\u00afwA}x\u00a2\u00d8\u00deg\u00c4=v\u009c\u00f2T\u0085)\u00a5Y\u00faX\u00c4\u00c3\u00d9%\u00a1\u0004 \u00e4\u00d0ro\u00f6A\u007f;\u00ed*\u008f\u0091\u0005\u00ed\u00be\u00d0\u00cf\u00ef9\u00b0~\u00a7n\u0093\u00b8\u00dd\u0087\u009d\u00f7\u00b6\u009b\u00c7 \u00872\u001f\u00fc\u00ec\u0010P\u00a7$-\u00b2J\u00cd\u00f6\u009f\u0002\u00b9\u00d3+O\u00fc\u00de\u009e\u0019\u00cb/@\u00e5r\u00d9\u00eb\u00a50pr]\u009a\u009e\r\u00f2\u0098\u00c8\u00f5\u00cc,&IH\u00f0\u0085tE=\u0017\u00a85\u0019\u0015\u00d2T\u0019\u00f1\u0006O\u00c4L\u0098\u00e5\u00f2\u00f1e\u00ac\u008b\u0097tn\u0015w\u00ff%\u0016\u0010\u0087\u00a2\u0080\u0003\u00c7\u00b9\u00a5\u00f2\u009f\u00e6\u00a0*\u00e4\u00e6\u00ea\u00ae0'\u00d0\u00d2J^Y\u0015.\u00fe]\u00eeB\u0093\u00bf\u00db&\u009b\u009a\u00eb\u00e3\u00aaD\u00d2\u00bc\u00e6\u00863\u00cd\u00a9\u00e4v\u008c\u0087\u00fe%\u00e1\u008c\u00d0\u00fc\u00eb\u0002\u00e6Z\b\u00fb\u00ca\u00ee\u00ce(\u00d7L\u0089\u00c4\u0085&\u00a2\\{q\u00baH\u0012}\u00d6\u00d7\u00b2A\u0086\u0012\u00c0G\u009f}U\u00b1\u0013g\u0011r\u0003\u009a\u00bc\u00ba\u00b3\u00ef\u00e7\u00de\u008c\u0092(ZD:-1&\u00de2\u0014|\u00d0?\u00e2\u008a\u0016\u00f36VU\u00f1\u0091\u00c6l\u00b9\u00cf\u00d2\u00a6p\u00c01\u00ba\u0085Y\u0088~\u00138\u00d2\u00a2_ Pz\u0003Tw9\u00c4l\u00f1\u0095\u00c7o\u00e8\u0001\u00d8\u00fd\u00eaEN]K\\S@\u008f\u0000]MR\u009e\u009b\u001b(\u0097\u0010\u00f4\u00fc\u00e2v\u001dZ\u009d\u00dc\u00b7\u00a8FUO6\n\u009f\u001d\u009365\"E)\u00e6B\u00d9E\u00f4+\u0017O\u001f\b\u0007\u0007\u00c4\u00db^\u00b8G8\u0006\u00cd\u0099R)\u00fe\u0097\u0018U\u00f1\u00abqy\u00c28\u00a7\u00f5\u000617\u00b4!\u00d3\u00d7}&\u00c5h)\u00d16\u0015x\u0097S*\u001dm2:\u00c2l\u0005h\r&\u001f0\u00a3\u001f\u00e0W\u009d\u001dV4wi\u00ef\u00ef\u00d4=\u0096\u00c5;o\u00e4\br\u00130\u00b0?\u00edt\u0091\\\u0094\u00c5\u00b4V]\u00b2.S\u0098\u00a89K.\u00ef\u00999\u009a\u00c8\u00cdeN\u00ac\u00c0{\u00bb\u001c\u00c3\u00c1\u000b\u00a1i\u00dd\u008a\u00cd\u0098\u0013\u0012\u00b7a5\u0084\u00c8\u00cf\u008cr}\u00ea\u00bd\u00fc>5\u0013\u00d3e\b{\u00d6\u00b7E|,3\u00a7\u00b7*`+w\u00b22\u00bcVZH\u00bb\u008bp\u0011\u00e2\u0003\u00ac5\u00f9\u00ba;Hr\u00fd\u00ecL\u009d\u000b\u00b7~\u00ffdl|{\u00ca\u00f3\u008a\u00c3\u001dg 9\u00c2\u0004\u00be\u009f\u00d4\u00daJ\u00c6z\u00e8\u008e\\\u00ba\u00b4\u0018/\u00a4\u0099\"\u00d3Ny\u0018\u00d9\u00c4\u00b6O\u00e1\u00f2EP\u0018wN\u001d\u00c8\u00be\u00f1\u00039\u00cfWQ\u00c35\u001f\u00ea\u00b3k\u00c7?$e\u0004`0 \u00d2\u00fb\u00dc]\u001e\u00bc\u0002\u00afD\u00det\u00ef\u001a\u0005R\u00e0\u00a5{\u001e\u00f7\u0097X\u000fh\u0083\u00f1 \u0002a\u00f0\u0085T(J\u0088\u00f6\u00f5\u00f1l\u00ebp\u00a0\u0080\"\u00d3o\u0098\u00bc\u00c1Kya\u00f2\u00e0\u00af5U\u00db\u009b-wk\u0005\u00869G\u00e8\u00cf\u001c\u0092\u00d9\u00c3\u0097 \u0003\u008cVM\u00d0.\u00f3\u0093\u00ac\u00f7(\u008a\u00a5\u00fd\u00b4^\u00a7\u00ef\u0092\u0082\u0086\u00ed\u0088x\u00ee\u00c6\u00bf\u0014\u00abR\u00aa\u00cd \u00f8\u00cd<\u00f5b\u00ca\u00aeY\u00dd\u00eb\u00a7\u00a6`?\u00bff\u00b4\u0089\u00d4F\u009a}\u00fa\u00fa\u00e2\u00176\u001e\u0098t\u0015:\u0018o!'\u00d8\u00ed\u00ea@\u007f \u00c7s\u00ff\u008a\u00c9<p\u0083\u0010w\u00b81\u00b2\u00c1\u007f\u0018\u001f\u009dL\u00abDsHh\u009bAF\u0084\u00eb\u0085\n\u00a8\u00e6\u001d\u00eb\u00ff\u0018M\u009a\u00b1\u0010\u0098\u00f4\u00a9<t\u00c0K\u0081\u00a5\u00b0\u00b8\u00b5\u00a0_.\u0090(o\u00ad?\u0000 \u00a7I\f\n\u0091A7m\u009f\u00f4\u000f\u00bb\u009d\u0005\u00fa\u00b9\u00df-R\u00e1tM\u00c6.\u00df\u001d\u00af\"\u00dc\u00c3\u00c0kU\u00d00(-@,\u001d\u00fa\u00a7\u00cfW\u0019\u00a8\u00d6\u00e0\u0098\u00fc\u008e\u0001\r\u008d\u0006S^K\u00b2\u00e6](B\u00d8\u00feH\u00be\u0094\u00eb\u008b\u00fd\u00a7\u001ck\u00a5\u00c4 #Q\u00807\u008b\u00cfH\u0081\u00b4\u00cd\u00fc(t\u009bxrD6\u00b1\u00e38\u001d\u0089Va}~\u0096\u00981\u00faQ \u00c2\t\u00fa\u000e\u00b2wZ\t\u00bf\u0097\u0002?\u00bd;\u00b8\u0095\u001f\u009c\u00aa\u0001W\u0082C\u00dft&\u009cK\u0019\u00df\u00f16\u00109\u0094Q1\u00b3D\u00b0\u00db\u001e\u00fc\\8\u00ae\u00a5\u00de\u008c\u0018\u001f\u00fb\u00eed\t\u00c3\u00a4\u00fc\u001f\u007f\u00d2I0[\u00f0Q %}\u00eb\u0010\u00e1\u00e8\u0087 '[.O\u00a9\u0096_\u00cd\u0001RNdG\u00e4\u00b0<\u00f2'\u0094\u00b4\u00e8\u0089>\f\u00a2\u00a7\u0086\u00f9Q{\u00faq\u0018\u00b0P\u00ebq\u008f\u00c9t\u00eb\u00eeS6H\u00a4\u00d1\u00e0\u0017\u00ac\u007f\u00d5\u0098#4\u001b^\u0018\u00d1\u00f5\u009c\u00ff\u00b7\u00ce\u0086)\u00b8b\u0010\u0014\u000e\u00fe\u00b9\u009a\u00c8\u00b1nF\u0095\u00a4\u00e8\u00dd\u0010GF\u009d\u00b2\u0012_\u00c2\u00ef\u00b5r\u0010D^\u00f2\u00d8\u00bd\u0010\u00e3\u00b2n\u00c9\u000e\u001eu\u00dcM\u0085\u0003}\u001a\\\u00b1\u00fcH4\u00c2u\u00b0\u00db\u0010\\\u001b\u00bf\u009e\u00de\u00d0K@\u0004\u00e5j\u00e0\u00efZe\u00b6\u00b0\u00b1fR:8\u001e\u00ac\u00cb\u00d8\u00d1\u0003\u00f4\u00b5 _\u00f5e: \u00e9\u00e7D\"sL\u0002\u00e8\u00a8\u0099\u0098\u0004\u009dp\u008e\u0099\u0099\u0000;\u00d1\u00b3\u00ac\\ <\u0011L\u001c\u00ed\u00f2 \u00a5\u0094\u0080'%\u001c\u00c95\u00e4\u008f\u0013z\u001d\u00b7B\u00d8>\u0083.\u00ee\u00c6\u0011\u00b1\u0004\u00f8;\u0013H\u00d2\u00c2\u00d3\u00a9(\u00edX\u00e5\u00fc\",\u00b1v\u00d1\u00bf\u009d\u00b1O\u00aeD#:\u00dbbx\u0081&mF\u00b7q\u009f\u00e3\u0088\u0087\u0012\u00cd&o\u00c2N9\u00e5\u00ae\u001c\u0010\u00d2\u00a1w\u0093\u00db\u00c9\u00d3/\u00fc\u00cceXx\u00b7m\u00ca\u0018\u00b5o@\u00f4\u0099\u0091\"8[\u0091\u00fd\u00ba\u00ed\u000f&\u0094S:-vS:\u00ac\u00c80\fu\u0011es\u00d8W\u0017T\u00df\u00a1\u00ef;\u00e9r\u00cd3\u00b6\u00db_f\u0019\u00f5\u009e\u0006\u001d\n\u00cc\u0086J\u0094\u00ca\u00db06\u0093\u00aaD\u00f5\u00ef\n4L\u0015\u008715\u0005(\u00bd5\u00a6`b3X\t\u00e0\u00eb\u00e3\u00e9\u0004\u00c2\u001d\u0014|\u00cb\u0017\u00b3b\u0013l\">\u00f28\u000f'gy\u00c8g\u0083\u00e6)V\u0017\u0003<\u0018\u0094QM\u00f4\u00de\u00c3\u0081w\u008c\u00ebPm\u00d2\u00a3 \u00d0J\u00b2\u0091\u00ffs\u0090\u00bd\u00b7\u0010\u00ad\u00d2\u001a\u00e0[\u00b5\u00de\u00b0\u00e3\u00d3U\u00d9_'\u00e3\u00fd V`\u00f2\u00ce\u0084\u00e5\u008d\u00fbJ$\u009a\u00b0\u00a8\u00f1\u0016\u0097\u001c\u00c3\u00a2t]9\u00fc_F\u00da\u00d7\u00bb\u00ac8-\u008f \u00e6F]\u001d\u00ed\u008b\u008f\u0082\u00f42\u0081\u00d5\u0002\u00eeOL\u0013&u\u00efWM6\u00b5\u0011\u0083\u00f5\u001f\u009b\u008e\u00d8\u00fa ~\u008c\u00df\u00bd\u00b3\u00cf\u00a2Zu\u00fa\u00c4i\u00c6\u00d0O\u00f2\u00d2G\u00b8\u00af\u00d7w=\u00928J8\\!4\u0011\n 2\u00a9\u00f6\u007f\u00e5y\u0018\u00ad|\u00b8\u00f6\u00e5-\u0096>\u001c\u00fa\u00e1\u0083\u000b4\u00ad\u00a5\u00c3\u009e\u00fe\u0096i@\u00c35\u00e3\u0018\u00dd8\u00df*\b%S\u009a\u0013\u00ad\u001f\u0016f\u008d2\"\u00fbxa\u0088v\u000b\u0099|(\u0093\u00d0%\u0012\\\u00db\u009c)\u00c4\u0019\u00c1_\u00ad\u00e2(\u0017\u001a}\u00d4\u00f5\u00df\b\u00b9\u00c8\u00e2$D\u00d5,\u008c\u0084\u00d2\u00e1\u0099\u00c0o\u000b\u00cd\u0011-04\u0080\u00db0\u00d3\f2vu\u00b3\u00f5f\u00a4\u00dd\u00d9\u00e3\u00f3\r\u001c\u0013\u001c\u0000<_\u00ba\u0013U\u001a\u00ed\u00b2\u00e6%\u001bC\u00e8\u00ee\tz\u00adt\u00db\u00e1\u00d7j\u0097\u00a9\u0089\u00d6(\u00fd\u00e7=\u00a5\u00b7\u00e6\u00e9\u00df\u00e7Z`V\u0013R1\u00a1\u00c7\u0087\u00ff\\\u000fm\u00ebTw\u00f8}\u00ff\u0080\u0087\u00f4\u00b7R\u00e6\u00de\u0007\u00a9\u00a5\u00d0\u00a6\u0018\u00c0n\u00a4_\u0014\u008d\u0083\u0010\u001d\u001d\u0082\u00d1\u00d4\u00c8\u00e8s\u00d6)\u001c\u00f8NO\u00bdZ \u008d\u00a26\u0080G\u00c4.\u00ee\u00b8\u00e7\u0089\u0014v6u\u0012\u00b4\u008e\u00a5\u00abag\u00d6\u0090DK`ec\u0093\u00a5\u00b3 \u00826w\r\u00a8\u00b47t\u00ee\u00b8\u0018Ni\u00fd\u00a0t\u00be\u001b\u00de\u008c\u0081\u0092+h]y\u00e3\u00d1N\u00d8\u00c4\u00f9@?\u00cbA\u00d4\u008cy\u0013\u0099\u000e\u00ce\u009e\u001d\u00aae\u0081\u00e4\u00dcj\u0019\u0002'\u0011*\u0010WI\u00f5\u0095\u0011`\u0094\u0089%\u00eeJ\u00e8\u00b9k\u001d\u001e\u00cd\u00f4\u00fc%\u009ca\u00af\u00c7o*\u009a\u008e\u0003\u001dP\t\u00de\u00d6|\u000e\u009f\u00df\u00a3\u0094 \u00cc\u0019c\f\u00a1\u00d9\u00c6\u00f2D\r\u00c5\u009a:\u00fbl\u00a4|\u00fd\b\u00998\u000b\u00ccRnS\u00ebp\u00aa5R\u008a(\u0085\u00dc\u00c3|;\u008e\u00df\u0095Y\u00b8:\u00cd~\u001b\u00dcS(c:\u00fa\u00c4\u00b3\u0012\u009b}\u009b\u00df\u0018\u00f2\u009c1\u0019\u00db\u00b1\u00d1\u00aa\u0087X\u00cdN(\u0010\u0090'N\u00c5Q\u00c8\u0083[xR\u00d3\u00d8\u009b&\u001ah\u00f3\u00fbE\u00ad\u00047\u001c\u00ffC\u00b6<\u00d3\r6\u009b\u00e8\u00de]\u0092\u008d\u00b7\u0013\u00c8(\u00c7\u001d[\u00c9ut\u00d2O\u00d5_\u008f\u0082\u00c8HRA\u0093\u0019\u00cdP\u000e(\u00ca$\u00d9\u00bcR)\u00d4\u00e3\u0091T&\u0092\u00a4\u00f5\u0011<\"6(BSHy\u00ed\u001a\u000b+\u00e6\u00ea\u00d1\u00d6\u000b^\u0081\u00f2\u00d2]c\u00bc|\u0098\u008e\u0083\u00dd\u00c3J\u0089\u00f4\u008daP\u00e2\u00e0&G6\u0080\u00b5\u00b1\u0018\u00b46@\u00b3\u00d7!.y51\u0087$\u0003t\u00052MG\u00c9\u00eb;om\u00d6(\u00e3\u00ac\u001d\u00ed:O \u00de\u0082\\vH\u00a1T\u00ca\u00bf1\u00d9i\u00e0\u00deO\u009d\u001c\u00ab?\u00f3\u00c8$\u009c02%\u00e1\u00f2\u00e3\u00b2|\u00d6/(J\u00dd\u00b8\u00bf\u009a\u00d9gp\u008cBP=\u00c0\u00c4\u00a9\u00a0V}2T\u00c5t!\u00d9\u0016\u00d1\u00a1|l\u0095\u0018\u00a1\u00bbi\u0006\u00b4\r\u00c2m3(o\r\u009b\u00a3\u0012\u00ecb\u00a1\u0017{\u00e9qM\u0019%\u00e5\u00d2\u00f2h\u00bc\u0015=%\u0095\u00fd\u00ff\u00e0\u00edF\u0087e\u00bc\u0094\u000f\u00e8\u00bb\u00c7)\u0096\u00c7 \u00f8h\"\u00b8\u0084\u00b8BM\u00d8\u009d\r\u008c\u00e4>\"\u00a5\u00f1\u00ad\u00dc\u00ea\u00cf\u00ff1biO)\u000b}R\u00c2\u0099 [\u001b\\:!,\u00ee\"\u0019Y\u00b2Ap\u001d\u0092\u000e\u0088\u0089\u0003m\u008d\f<P\u00fd\u0095\u00f6\u00f5Px\u00a3\u001b(\u00be\u008e\u009f\u009a\u00c9\u0096\u0018@*\u00fc\u00acb\ne\u00ba?\u00830\u00b5+B\u00f0\u00b16\u0015\u0080\u00a0`\u0013\u00db{\u00f3\u00e2\u00df>0\u00f8\u001f\u008b\u00aa \u001c)\n\u00b4\u0015\u00cb\u00ea\b\u00e0\u00ffc]\u00fck\u000b\u00eb\bj\u00a6\u009c\u00b7\u00f7n\fHM\u00f1\u00ecP\u000b\u0012$0\u00f8o;g\u00db\u0016>L\u00a8\u0012C\u00b8{\b\u0080!\u00f9\u008b\u00c7qj\u000f>\u00b5<\u00bc\u00eb\u0086\u00b1j\u00f0\u00c5j\u0013\u00d7\u00d1/\u00e3\u00ce6\u0099\u0091\\\u0099g\u00d4\u00cc_ z\u00ac=\u00e0\u00eff^\u0083\u00d5\u00ca\u00aef!\u00fan\u0006\u00b4\u00f3\u0085\u00be\\|u\u00c0\u00c6\\\u0090w\u00bbaW\u009b fS&\u00a6J+>\u0081\u00d6\by\u00d4Y\u0093\u00fe\u0012v\u00cb\u00ef\u00a9\u0098\u0002\u0004\u00e3I\u00c8\u00ad\u00c9\t6B<@\u00efb\u00a9\u0002\t\u0006t$\u00bfG\u0086\u000b\u0010\u00d5Q\u00f5\u00c9G\t\u0005|\u0011\u001d\u0085K\u00cb5\u0000\u00a6\u009a5\u0084g\u00e5\u00fc\u000b \u0090H\u00d1\u0016``\u00dd\rI\u00c6:b\u00dc\u008e\u00f75!\u00f0k?\u00c8\u001a\u00de\f\u0002\u00d1K \u00f2\u0012\u00f1\u00db=[\u00a1z\u00ff\u00b1sz\u00f9y\u00b7\u00ef\u00abi\u00e5\u009fN\u00c4N\u0005\n\u00c9\u00c6\u00d0\u00fd\u00b5\u00d2\u00f2\u0018\n\u008e\u0088\u00a1A\u008aU\u0084Ji\u0003j\u00a5\u00e9\u00caV\u0096v\u009b\u00f4\u0088\u00a1j\u0011 \u001as\u00f0Z\u00a6\u00e9u\u009b\u00e7#\u001e\u0099r\u009d\u009d\u00b8#\u0004\u008e\u0081\u00eb4\u0097\u00e0\u00a6\u0085\u001f\u007f\u0087\u00aaDF\u0010\u00a0v\u001da\u0019:\u00fe\u00a8\u0095\u00d9\u009a$\u008b\u0006Q\u0096(\u008c\u000b\u00e3\u0095\u00b8\u00db\u00e9\u00d6\u00de\rCEp\u00b1H\u0018\u00c8\u00f2\u00b3\u00ac\u00d6s\u0082{\u0090)\u00fb^\u00d4\u00ab_\u0082y\u00e0&\u001e\u0005E\u00e9b \u00fcZ\u007f{\u00fe\u00bf/n\u00edw9\u00b0\u00bc.B\u00b5^\u00ee\u0016\u00abG\u000b\u00bd\u00d9:\u00ac\u009fj\u00d7\u00a0\u00c9\u0002 \u00cci\u00d7\u00a3\u00a7\u00cf\u001c\u00e0\u009c\u00f8O_\u00d8G\u00c6\u00fe@\u00aa\u00b5\u00e0J\u001c\u00b9\u000b%\u00c2b8\u008a\u00ee ' \u009b\u00c8s\u0099G`9=\u00fbf^\u001cqD\u00af\u00a0\u0085\f\u0001\u00d6vz{-\u0083\u00a9\u00c0Ur\u0002\u00ff\u001f(}&B\u00a1\u00df\r\u009b\u008f\u009d\u00b6\u0016R\u00e60\u0010\bw\u0085\u00e0\u00e6wW\u0007\f\u007fW\u00c8l1\u00b5\u00a5\u009fxq\u0019\u00e9\u00e8\u00fcM{\u0010NQB\u0081\u00c97\u008cXMoG\u00ae\u00f0!k\u0092\u0018[X{\u00a2b\u0093\u00f67\u0006\u001dq\u0017\u00f6*\u00b0\u00b0\u009f\u00be\u00f1\u00d6F\u0018\u00fb\u00a4(\u00d6\u00b5|\u00a6\u00f3qJgC4\u00e6f\u0088\u00a3]Y\u00eb\u00be\u0013\u00e15\u00e0\u0085\u00a5\u00bf\u00ec\u0083\u00a4r*\u00e6B\u00a6\u00cf\u00f1\u0082\u00d5g\u0099\u00d2 \u00dc\u00fa\u0083\u001d_\u00bai}3\u00b1t\u0091 \u00de\u0000\u00d8\u00d3+\u00ae\u00b8\u00b4\u00cf\u0098\bM\u00a5\u00cf;:\u00e2i^  \u00b8\u00f2\u0011\u00f6cql\u00b1R\u0089^r&bu2\u00e4;\u00f8w\u0001\u008f\u00cc\u00ca\u00e2\u0082m\u00f5|\u00ab\u0091(\u0014\u00a7C\u00a9\b\u00aa\u00c1\u0095\u00fa\u00ab2\u00f5/\u0001{h\u00b2m\u00e9\u00aa/\u00fa\u00d2~\u00c0\u00d26\u0085\u0002\u00f1\u00802+yVYH\u00c0k\u00b4(\u00bf\u00ec]\u00fc\u00a0\u00a3z\u00c0T\u00f3+\u00d3\u0082\u00a09\u0087}\u0004\f\u00bf\"\u00c6\u00ecX\u0001\u00ad\u00c4qQ\u00e3m\u00c0\u0087\u0019i\u00ec\u00e0\u00be\u0086\u00b6\u0010\u0087,\u00f3\u00f24\u0090Z+\u008b\u001c\u008eRS&N\u00d6\u0018\u00e1\u00ed\u00cf\u00c1<\u00a9\u00b2\"<\u008eE\u00b2r\u00bd-\u000b\u00c0\u0098>\u00c7e\u00f4g# \u001f\n\u008d\u0011\u00ffX\u00bfc\u0086\u00b3\u0083\u0081\u0011\u00c4Z\u00f8J\u00e5.\u009d\u0005Xl\t\u0088\u0001\r\u008f\u00d1H\u00bb\u00e2(3\u00a5\u00f0{S<\u00b7F\u00f1\u00cc\u00f0\u00d1>Z\u0014\u00d9\u00ea\u001c`\u00c0 \u001aJ_\u0086u\u0098\u001a\u0012\u00de\u00be\u00c5:\u00ff\u00d7\u00e9\u00d3\u00cdN\u00f9\u0018\u00bbT\u009b\u001b\u00d7\u0005\u00cd\u00cau\u000b\u00de/\u00c4\u001e\u001e3\u00c3\u0002\u00d6_\u00add\u001d\u00198V0\u0092A2\u00cet\u001d\u00e5S#\"I\u00e7\u00e7\b\u00edQ\u00bb\u008e{L\u00bbo{\u0089S\u00e10\u0095\u008f\u00efowH\u008e\u00c8!\u00a6\u0096\u0019\u00aa\u00f8\u00c3\r{xB\u008f\u000b\u00ac\u00d4Q\u00cc\u007f,\u0018~\u008a\u00d8O\u00ef\u0016yVH\u00df\u00cd/=\u00b7B\n\u0001C9\u00d5\u00cd\u00f9j{\u0010\u00fb\u00cf\u00b2\u00c8x|9\u0090\u001e9\u0014789\u00db\u0012\u0018\u00907\u00ea\u00e9\u00dc/;\u000ba\u00a9\u0088\\\u00c0\u0011\u008a\u00ee\u00abY\u00cb=+\u0007wK(\u0011Fp+\u00e7\u00c5\u00ed3\u0012\u0005\u00b1\u001d\u00e7\u009dU\u0010O\u00f3\u00ac\u0083J\u00f2\u008c\u00ef\u0081\u0093\u0094\u0087\u00c5\u00fb\u00e3\u009d\u0086\u00f0\u0096C\u0018\u009eZk(}%=\u0095\u0011\u0003O{\u00cdi\u00d6\u00a7\u00a2\u00fc\u00c8\u0000\u0012mu\u00af\u0093\u00dc\u00f6\u00acE2\u009d-{j\u0097\u0014z.\u00e8\u00a9\u00c6\u00ea\u00d1\u0017 \u00b1\u00eb\u00d4\u00fd\u00ae\u00c0Z\u00cb\u00f7\u00d0d\u0083RBT7\u0095\tE\u008aD\u00de\u009a\u00c3rM\u00cf\u0002\u0018\u00d3\u009dG\u0018\u0090`\u00b6!\u000e\u00b0\u0092\u00fe4r@zMg\u0004\u00b4\u00a8\t\u0016Jb\u00bc\u00804 \u00f6O\u00e4\u0085FK\u0001F\u00e8\u00cc\u00b6\u0094\u0091\u001f\bNQ\u00dfi\u00f81\u0007\u00c8#\u00b2\u00b6H\u00a3#\u00a8\u00d5\u009e(\u0087\u0015\u0087\u0003/1t\"\u00b0\u00d4<Fb\u00b26R\u00ac\u00c9\b\u0011\u0018\u00fe\u00b0L\u00eb\u00e7\u009c0\u00c2\u0096\u00dc\u0003%[}7\u0013\u00be\u00c5\u00bb(|\u0091\u0003\u00c2U\u00f5\u000bg\u00ad\u00ba\u0003\u00aed\u0097Q\u00af^<\u009c\u008f)M\u0010\u00af\u00d4m\u00f8\u00eaB\u009e\u00d0_h\u00fem\u00eaF\u001b|\u009b(\u008e\u00d1\u00f0l4\u001chM~\u0005\u008bA]E0\u00f7<9\u00f7;\u0090\u00a2\u00c0\u00e1\u0019\u00f9\u00ee\u001b\"\u00b4\u009dXTQ\u0010\u00a6\u0093t\u009c\u00bd\u0018C\u00fa\u0019\u00ac\u00d5a\u00e6\u00bd\u00ab\u00825&\u00f4\u00df1\u00ae\u00814\u00fa\u00dfs\u00aa\u008d\u00d6 \u00fed\u00e4\u00b5\u00d4cK\u000f\u00dcZ\u00eeI\u00a8%U\u00b4e\u000b\u00972\u00fd.\u00d3\u0018\u00b8\u00d0\u00f6\u00b3`\u00b0Sl\u0018K|\u0080\u0083\u00957\u008dv\u0011\u00d8\u00daA9\u00ff>\u0086\u00f4xJy\u008c\u00a3m\u00ee\u0018\u00039\u00edb\u001a\u00a7R\u00c7\u00a6\u0098\u00eb\u0085{\u00b5{\u00cc\u0017\u00a4\u00c9|WF\u00ce\u0090\u0018m}Xp\u00eb\u00b0/`:`\u00b7\u00ce\u0090\u0004uO\u00dd\u009e#\\\u00a4M\u00e9\u0013";
                                var28_6 = 6778;
                                var25_7 = 32;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = AimSettings.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "+\"\u001f\u00a2\u001e\u00ea$\u00f8O\u00c4\u00b0m\u00f9_\u00c2\u0082\u00aa\u0012\u00ee\u00f5fc\u0098\u0017\"\u0011\u00a9(\u0091:\u00ef\u00de\u00e10\u00f9%K?\u0006\u0081 X\u00d2\u00efd{s\u00b6\u00ce\u00e2\u0001\u00f9\u00caP\u00f7v\u00e6)\u008b\u00e4B\u0081\u0000\u001bn\u0093\u00b8Q\u00c8\"\u0018\u00d8>";
                                    var28_6 = 73;
                                    var25_7 = 40;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = AimSettings.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        AimSettings.fb = var29_3;
                        AimSettings.gb = new String[197];
                        AimSettings.lb = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[120];
                        var14_13 = 0;
                        var15_14 = "\u00c9\u00b0:r:\u0099\u0080\u009a7\u00ad\u00a4!\u00be[\u00c3O\u00a6\u00eb\u0084\u00ed\n>\u00c8\u00147\u00eb!}\u0098\u00e3C8 \u00a8\u008d\u00fa\u00b4x\u00b1^\u0084\u00ba\u00ae\u0010\u0016\u0099\u00fc\u0014)\u00e7G\u001d,d\u00f6\u0097(\u00a7e\u00ea\u00f0w\u00f9\u00bd\u0010U\bD\u001f\u00f6\u00dd%\u00f3\u00e6\fY\u00dd\u0091\u0093\u0082\u001d\u0082\u001e2\u00ac\n\u00af\u0014\u00c5\u001c\u00df\u00f1\u00f0W\u000e\u00b9s(\u0013IN\u001a\u00f7\u00b4\u0011\u00f1\u00b9\u00c7\u000b f`\u0005\u00bf\u00a5ph\u0017\u009e\u00d9@5\u0003h(RAl\u0018,\u001e\u0095m\u00e3?\t\u00c3@\u008d\u00c8P@#eT#\u00e6\u00d8j\u00e0x\u00ae1e\u00c83_&\u00e4\u00f8K\\'\u00eaP\u0016\u00a6\f\u00fd\u00fd\u00aa\u00b1\u00c3i'\u00b4\u00b5\u00e7\u00e7\u00c7\u00a8X\u0080d\u00c6\u00cf{\u0090\u00a9\u00f1<\u00b5)zP \u00f9\u0092\u00b4D\u00b1G\u00e1POX\u00b8\u00b4m\u00fe\u00db\u0000\u00e3\"\u00a8\u00fb\u00df\u0092d\u0087\u009dV\u00f7\u00f0\t\u0005\u00da\u00b8\u00e9\u00ed5\u00b5\u0085\u00f0\u00f0\u00c3\u0098\u00e6f\u00a3\u001f\u00be\u0089\u00c7\u00b8\u00c6\u009a\u001e@\u00ef&d\u0010\u00ad\u00fa\u00af\u0015\nNR4KL`1\u0098\u00d1\u00ed\u00b4\u00fb\u00c0\u00d1$\u00dcw\u00cc\u001bTE\u00da>\u00b7\u00f8*\u0091t\u0097g6ld\u00d0\u00b0I#^\u00a8\u00c3\u00ee@\u0088\u00b3<\u008b\u00bdG\u0083\u0090\u00d2\u0007.jaT=k\u0098\u00edM.\u001bh\u00cc#?\u000e\u00f3\u00e7P\u001c\u000e\u00a0\u0091\u0098\u00de\u0082\u000fL)\u0087\u00be\u00d8T\u00fe\u00f7\u00ff\u00e8'\u00db\u0084j\u0087\u0096\u0085&<he[f\u00a2\u00da\u00ecM\u00cd\u00dbi\u00f2Z\u00ad\u001f0)\u00e3\r\u00e4\u008d\u0003\u0091\u00b3\u009f\u0080)\u00ba}zK\u00d3\u00a9\u0097\u00a6'\u00a7\u00b5\u00e8\u0002\u00d1\u0012hc\u00d4\u00b95\u00ecxd\u00f3\u00a5Y\u00dc\u000e\u00d2\u00d5M'\u00b4\n\u00f0Q\u00e2\u00038\u00e7\u00bdA\u008c\u0085\u00e9\u00f2\u00ca\u00bc\u00ba\u009e\u00bd\u008b\u00f78\u00b7\u00e0\u00a0\u0093\u00e8\u00c9\u00e3\u00cf\u00e9\u00ed<vJ]nHq\u008b\u00a8\u00db\u00f6\u00c5Yo9\u00fd\t\u00f3\u0097s\u000f\u00e0H1\u00e9\u00dbZ\u0019,\u00cc\u001e\u0090\u00aa\u008c\u0014ato\u00df\b\u001c\u00d0RK\u0084H\u00e7\u00be\u0016\u001bd\u00d4K\u00be\u00c8\u00ffu!q)Oe\u00c0\u0019\u0017\u00c8En\u00e3N\u00d3\u009cu\u008f\u00f1=\u00d1\u008a\u00e5EN\u00bc\u00d2TNf\u00b3q\u0091\u00a2\f\u000e\u0013DUU\u0089\u00f0\u00e5\u00dcgK\u0001j\u00d7;}K\u00b1(\u008c\u00f7\u008bx\u00c8\u00ddw\u00cf\u00a3F\u00ee\u00f1\u00ffqVq-\u0099Y\u0086\u0098\u0012\u00b6\u00b8\u0090\u0080\u000e7Q4iR\u007f\u00c2\u00e8-z\u009d(\u00df\u0010\u00daG\u0090u\u008db<\u0099\u00d07\u0088!\rY<\u00e7>]\u00ae\u008a~\u00a90\u0000FA\u0087\u00c0t\u00e6l\u00a2u`8\u00a0\u0018\u00cdl\u00c9\u009cyV\u0000\u00d1\u009a\u0098\u008eT\u00edj\u00ed6\u0092\u00e7\u009c?<WsG\u00fdk*\u00af\u0014\u00f0\u00869\u00fc\u0083\u00c3\u00f1\u00b5/\u0012\u00c6\u00df\u0010\u00bf`\u00cb\u00a9\u00ad\u001d\u00b3%\u00b9?\u00d4\u00aex\u00d3\u00e0\u0002H%8\u00a4\u00cb\u00c0\u001bc\u00e5\u00ea\u00b8\u0016\u0087#\u00c6w\u000f\u00bc\u008a,\u00b7\u0015\u0097\u00c2\u00dfkI\u00b8\u00dfx\u00eb\u00f0\u0093\u0086\u00ba\u00bf\u00da\u00b3\u0087\u00f8\u0081\"\u00fab\u00f5]L\u00eaGN\u0017\u0095\u00e3\u0087\u0084\u00ee\u00ac\u00e8\u00cb\u001d%\u0017j\u009a\u001f5\u008c\u00b6OMl\u00a6\u0011=\u00efIk>\"\u00c4\u0006S\u00ad\u00eae\u00da\u00a5\f\u00d4 %\u0092\u00a24\u00fd(\u00e5@\u0005_EA\u00d9\u0096\u0092F)zR\u00a8\u00e0\u00b7\u00e0-\\\u00cc\n\n\u0095\u00ec\u00f9\"\u00c1\u00af\u00f2\u00fe\u008e4\u00e6\u0004WT\u00cd\u0016TT\u00c1\u0014\u00fb#K\u00b6LDfK=\u0081Q\u000f7Z\u00fa\u008c$lP\u00a8Z\u00bd&6\u00c4bH\u00e0-\u00bby\u00cc\u00a02\u0080\u00d9\u001b\u0000\u008c\u00e4R)\u00fa\f\"yK\u0011l\u0089\u00fa\u00a05\n\u00f5{\u008d\u00c0I\u00d1V\u0000\u00e9\u0086pP\u009f6 \u00e0j\b*\u00a5\u0019\r;g~\u00efK\u00152\u00b5\u008f\u00e2yZ\u0097\u00de\u009d\u00bf;\u00f1\u0001\u00cb\u00bd\u0095\u00ad\u008b\u00bc\u00dd\u009f\u00d3h0\u00db\u00cc\u00d5\u0092\u00fd\u00a8x";
                        var16_15 = 944;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u008e\u009b9\fgvS\u000f!W=\u00b6\u00a0\u00b3\u00ce[";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl92:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                AimSettings.jb = var17_12;
                AimSettings.kb = new Integer[120];
                AimSettings.pb = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var31 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[2];
                var3_23 = 0;
                var4_24 = "6\u0086\u00f9\u009bH\u0016>\u0084]\u00f9\u00cc!\u00e5\u00be\u00b1G";
                var5_25 = 16;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl127:
                // 1 sources

                while (true) {
                    var6_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var2_26 < var5_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
            v18 = var3_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        AimSettings.mb = var6_22;
        AimSettings.ob = new Long[2];
        var60_30 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AimSettings.class, "advancedOptions", "getAdvancedOptions()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "point", "getPoint()Lsu/catlean/my;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pointJitter", "getPointJitter()Lsu/catlean/ji;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pointVRange", "getPointVRange()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pointHRange", "getPointHRange()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pointHFreq", "getPointHFreq()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pointVFreq", "getPointVFreq()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "speedDistribution", "getSpeedDistribution()Lsu/catlean/jz;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "minYawSpeed", "getMinYawSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "maxYawSpeed", "getMaxYawSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "minPitchSpeed", "getMinPitchSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "maxPitchSpeed", "getMaxPitchSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "jitterDistribution", "getJitterDistribution()Lsu/catlean/jz;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawJitter", "getYawJitter()Lsu/catlean/ji;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchJitter", "getPitchJitter()Lsu/catlean/ji;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawAmplMin", "getYawAmplMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchAmplMin", "getPitchAmplMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawAmplMax", "getYawAmplMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchAmplMax", "getPitchAmplMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawFreqMin", "getYawFreqMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchFreqMin", "getPitchFreqMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawFreqMax", "getYawFreqMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchFreqMax", "getPitchFreqMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "smoothHbScale", "getSmoothHbScale()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawAcceleration", "getYawAcceleration()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchAcceleration", "getPitchAcceleration()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawDeceleration", "getYawDeceleration()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchDeceleration", "getPitchDeceleration()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawSmoother", "getYawSmoother()Lsu/catlean/_s;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchSmoother", "getPitchSmoother()Lsu/catlean/_s;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yawAccelerationMin", "getYawAccelerationMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchAccelerationMin", "getPitchAccelerationMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "clientLook", "getClientLook()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overShoot", "getOverShoot()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overshootTrigger", "getOvershootTrigger()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overshootStreak", "getOvershootStreak()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overshootValue", "getOvershootValue()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overshootMinTicks", "getOvershootMinTicks()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "overshootMaxTicks", "getOvershootMaxTicks()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "headUp", "getHeadUp()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huIntervalMin", "getHuIntervalMin()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huIntervalMax", "getHuIntervalMax()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huTicksMin", "getHuTicksMin()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huTicksMax", "getHuTicksMax()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huSpeedMin", "getHuSpeedMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "huSpeedMax", "getHuSpeedMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "clickJitter", "getClickJitter()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yaw360", "getYaw360()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "yaw360Chance", "getYaw360Chance()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "holdAfterHit", "getHoldAfterHit()Z", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "holdMin", "getHoldMin()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "holdMax", "getHoldMax()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "holdTiming", "getHoldTiming()Lsu/catlean/x3;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "holdChance", "getHoldChance()I", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchMin", "getPitchMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchMax", "getPitchMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchITMin", "getPitchITMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "pitchITMax", "getPitchITMax()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "filterLowDeltas", "getFilterLowDeltas()Lsu/catlean/client/features/modules/pvp/attack/aura/rotations/AdvancedRotation$FilterLowDeltas;", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "lowDeltaThreshold", "getLowDeltaThreshold()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "replaceMin", "getReplaceMin()F", 0)), Reflection.property1(new PropertyReference1Impl(AimSettings.class, "replaceMax", "getReplaceMax()F", 0))};
        AimSettings.delegatedProperties = var60_30;
        AimSettings.INSTANCE = new AimSettings();
        AimSettings.advancedOptions$delegate = SettingFactory.settingGroup(Aura.INSTANCE, "advanced", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Ljava/lang/Boolean;)(), null);
        var62_31 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$1(), ()Ljava/lang/Boolean;)();
        var63_32 = AimSettings.INSTANCE.getAdvancedOptions();
        var64_33 = AimPoint.CENTER;
        var67_34 = "point";
        AimSettings.point$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var67_34, var64_33, var63_32, var62_31);
        var69_35 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$2(), ()Ljava/lang/Boolean;)();
        var70_36 = AimSettings.INSTANCE.getAdvancedOptions();
        var71_37 = JitterMode.NONE;
        var74_38 = "point-jitter";
        AimSettings.pointJitter$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var74_38, var71_37, var70_36, var69_35);
        AimSettings.pointVRange$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "point-v-range", 0.1f, RangesKt.rangeTo(0.01f, 1.8f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$3(), ()Ljava/lang/Boolean;)());
        AimSettings.pointHRange$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "point-h-range", 0.1f, RangesKt.rangeTo(0.01f, 0.3f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$4(), ()Ljava/lang/Boolean;)());
        AimSettings.pointHFreq$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "point-h-freq", 20.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$5(), ()Ljava/lang/Boolean;)());
        AimSettings.pointVFreq = SettingFactory.floatSetting(Aura.INSTANCE, "point-v-freq", 10.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$6(), ()Ljava/lang/Boolean;)());
        var76_39 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$7(), ()Ljava/lang/Boolean;)();
        var77_40 = AimSettings.INSTANCE.getAdvancedOptions();
        var78_41 = AimSettingsSpeedDistributionMode.FLAT;
        var81_42 = "speed-distribution";
        AimSettings.speedDistribution = SettingFactory.modeSetting(Aura.INSTANCE, var81_42, var78_41, var77_40, var76_39);
        AimSettings.minYawSpeed = SettingFactory.floatSetting(Aura.INSTANCE, "min-yaw-speed", 30.0f, RangesKt.rangeTo(0.1f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$8(), ()Ljava/lang/Boolean;)());
        AimSettings.maxYawSpeed$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "max-yaw-speed", 60.0f, RangesKt.rangeTo(0.1f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$9(), ()Ljava/lang/Boolean;)());
        AimSettings.minPitchSpeed$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "min-pitch-speed", 10.0f, RangesKt.rangeTo(0.1f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$10(), ()Ljava/lang/Boolean;)());
        AimSettings.maxPitchSpeed$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "max-pitch-speed", 20.0f, RangesKt.rangeTo(0.1f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$11(), ()Ljava/lang/Boolean;)());
        var83_43 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$12(), ()Ljava/lang/Boolean;)();
        var84_44 = AimSettings.INSTANCE.getAdvancedOptions();
        var85_45 = AimSettingsSpeedDistributionMode.NORMAL;
        var88_46 = "jitter-distribution";
        AimSettings.jitterDistribution$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var88_46, var85_45, var84_44, var83_43);
        var90_47 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$13(), ()Ljava/lang/Boolean;)();
        var91_48 = AimSettings.INSTANCE.getAdvancedOptions();
        var92_49 = JitterMode.NONE;
        var95_50 = "yaw-jitter";
        AimSettings.yawJitter = SettingFactory.modeSetting(Aura.INSTANCE, var95_50, var92_49, var91_48, var90_47);
        var97_51 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$14(), ()Ljava/lang/Boolean;)();
        var98_52 = AimSettings.INSTANCE.getAdvancedOptions();
        var99_53 = JitterMode.NONE;
        var102_54 = "pitch-jitter";
        AimSettings.pitchJitter = SettingFactory.modeSetting(Aura.INSTANCE, var102_54, var99_53, var98_52, var97_51);
        AimSettings.yawAmplMin = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-ampl-min", 5.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$15(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchAmplMin = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-ampl-min", 3.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$16(), ()Ljava/lang/Boolean;)());
        AimSettings.yawAmplMax = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-ampl-max", 10.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$17(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchAmplMax = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-ampl-max", 5.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$18(), ()Ljava/lang/Boolean;)());
        AimSettings.yawFreqMin = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-freq-min", 20.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$19(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchFreqMin = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-freq-min", 10.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$20(), ()Ljava/lang/Boolean;)());
        AimSettings.yawFreqMax = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-freq-max", 40.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$21(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchFreqMax = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-freq-max", 20.0f, RangesKt.rangeTo(1.0f, 30.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$22(), ()Ljava/lang/Boolean;)());
        AimSettings.smoothHbScale$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "smooth-box-expand", 0.0f, RangesKt.rangeTo(0.1f, 5.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$23(), ()Ljava/lang/Boolean;)());
        AimSettings.yawAcceleration$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-accel", 1.0f, RangesKt.rangeTo(0.1f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$24(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchAccel = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-accel", 1.0f, RangesKt.rangeTo(0.1f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$25(), ()Ljava/lang/Boolean;)());
        AimSettings.yawDeceleration$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "yaw-decel", 0.0f, RangesKt.rangeTo(0.0f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$26(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchDeceleration$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-decel", 0.0f, RangesKt.rangeTo(0.0f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$27(), ()Ljava/lang/Boolean;)());
        var104_55 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$28(), ()Ljava/lang/Boolean;)();
        var105_56 = AimSettings.INSTANCE.getAdvancedOptions();
        var106_57 = Easing.LINEAR;
        var109_58 = "yaw-smoother";
        AimSettings.yawSmoother$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var109_58, var106_57, var105_56, var104_55);
        var111_59 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$29(), ()Ljava/lang/Boolean;)();
        var112_60 = AimSettings.INSTANCE.getAdvancedOptions();
        var113_61 = Easing.LINEAR;
        var116_62 = "pitch-smoother";
        AimSettings.pitchSmoother$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var116_62, var113_61, var112_60, var111_59);
        AimSettings.yawAccelerationMin$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "min-factor-yaw", 0.5f, RangesKt.rangeTo(0.0f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$30(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchAccelerationMin$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "min-factor-pitch", 0.5f, RangesKt.rangeTo(0.0f, 1.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$31(), ()Ljava/lang/Boolean;)());
        var118_63 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$32(), ()Ljava/lang/Boolean;)();
        var120_64 = AimSettings.INSTANCE.getAdvancedOptions();
        var121_65 = false;
        var122_66 = "client-look";
        var124_67 = Aura.INSTANCE;
        AimSettings.clientLook$delegate = SettingFactory.booleanSetting(var124_67, var122_66, var121_65, var120_64, var118_63);
        var126_68 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$33(), ()Ljava/lang/Boolean;)();
        var128_69 = AimSettings.INSTANCE.getAdvancedOptions();
        var129_70 = false;
        var130_71 = "over-shoot";
        var132_72 = Aura.INSTANCE;
        AimSettings.overShoot$delegate = SettingFactory.booleanSetting(var132_72, var130_71, var129_70, var128_69, var126_68);
        AimSettings.overshootTrigger$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "os-trigger", 45.0f, RangesKt.rangeTo(20.0f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$34(), ()Ljava/lang/Boolean;)());
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$35(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(1, 10);
        AimSettings.osStreak = SettingFactory.intSetting(Aura.INSTANCE, "os-streak", 3, var59_75, var58_74, var57_73);
        AimSettings.overshootValue$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "os-value", 25.0f, RangesKt.rangeTo(20.0f, 180.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$36(), ()Ljava/lang/Boolean;)());
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$37(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(0, 10);
        AimSettings.overshootMinTicks$delegate = SettingFactory.intSetting(Aura.INSTANCE, "os-min-ticks", 2, var59_75, var58_74, var57_73);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$38(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(0, 10);
        AimSettings.overshootMaxTicks$delegate = SettingFactory.intSetting(Aura.INSTANCE, "os-max-ticks", 2, var59_75, var58_74, var57_73);
        var134_76 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$39(), ()Ljava/lang/Boolean;)();
        var136_77 = AimSettings.INSTANCE.getAdvancedOptions();
        var137_78 = false;
        var138_79 = "head-up";
        var140_80 = Aura.INSTANCE;
        AimSettings.headUp$delegate = SettingFactory.booleanSetting(var140_80, var138_79, var137_78, var136_77, var134_76);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$40(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(5, 400);
        AimSettings.huIntervalMin$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hu-interval-min", 165, var59_75, var58_74, var57_73);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$41(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(5, 400);
        AimSettings.huIntervalMax$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hu-interval-max", 180, var59_75, var58_74, var57_73);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$42(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(1, 20);
        AimSettings.huTicksMin$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hu-ticks-min", 3, var59_75, var58_74, var57_73);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$43(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(1, 20);
        AimSettings.huTicksMax$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hu-ticks-max", 6, var59_75, var58_74, var57_73);
        AimSettings.huSpeedMin = SettingFactory.floatSetting(Aura.INSTANCE, "hu-speed-min", 5.0f, RangesKt.rangeTo(1.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$44(), ()Ljava/lang/Boolean;)());
        AimSettings.huSpeedMax = SettingFactory.floatSetting(Aura.INSTANCE, "hu-speed-max", 36.0f, RangesKt.rangeTo(1.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$45(), ()Ljava/lang/Boolean;)());
        var142_81 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$46(), ()Ljava/lang/Boolean;)();
        var144_82 = AimSettings.INSTANCE.getAdvancedOptions();
        var145_83 = false;
        var146_84 = "click-jitter";
        var148_85 = Aura.INSTANCE;
        AimSettings.clickJitter = SettingFactory.booleanSetting(var148_85, var146_84, var145_83, var144_82, var142_81);
        var150_86 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$47(), ()Ljava/lang/Boolean;)();
        var152_87 = AimSettings.INSTANCE.getAdvancedOptions();
        var153_88 = false;
        var154_89 = "do-360";
        var156_90 = Aura.INSTANCE;
        AimSettings.yaw360$delegate = SettingFactory.booleanSetting(var156_90, var154_89, var153_88, var152_87, var150_86);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$48(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(1, 100);
        AimSettings.yaw360Chance$delegate = SettingFactory.intSetting(Aura.INSTANCE, "do-360-chance", 20, var59_75, var58_74, var57_73);
        var158_91 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$49(), ()Ljava/lang/Boolean;)();
        var160_92 = AimSettings.INSTANCE.getAdvancedOptions();
        var161_93 = false;
        var162_94 = "hold-after-hit";
        var164_95 = Aura.INSTANCE;
        AimSettings.holdAfterHit = SettingFactory.booleanSetting(var164_95, var162_94, var161_93, var160_92, var158_91);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$50(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(0, 15);
        AimSettings.holdMin$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hold-min", 5, var59_75, var58_74, var57_73);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$51(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(0, 15);
        AimSettings.holdMax$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hold-max", 7, var59_75, var58_74, var57_73);
        var166_96 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$52(), ()Ljava/lang/Boolean;)();
        var167_97 = AimSettings.INSTANCE.getAdvancedOptions();
        var168_98 = AimSettingsHoldTimingMode.AFTER_RANDOM;
        var171_99 = "hold-timing";
        AimSettings.holdTiming = SettingFactory.modeSetting(Aura.INSTANCE, var171_99, var168_98, var167_97, var166_96);
        var57_73 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$53(), ()Ljava/lang/Boolean;)();
        var58_74 = AimSettings.INSTANCE.getAdvancedOptions();
        var59_75 = new IntRange(1, 100);
        AimSettings.holdChance$delegate = SettingFactory.intSetting(Aura.INSTANCE, "hold-chance", 100, var59_75, var58_74, var57_73);
        AimSettings.pitchMin$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-min", -90.0f, RangesKt.rangeTo(-90.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$54(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchMax$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-max", 90.0f, RangesKt.rangeTo(-90.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$55(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchITMin$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-in-target-min", -90.0f, RangesKt.rangeTo(-90.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$56(), ()Ljava/lang/Boolean;)());
        AimSettings.pitchITMax$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "pitch-in-target-max", 90.0f, RangesKt.rangeTo(-90.0f, 90.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$57(), ()Ljava/lang/Boolean;)());
        var173_100 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$58(), ()Ljava/lang/Boolean;)();
        var174_101 = AimSettings.INSTANCE.getAdvancedOptions();
        var175_102 = AimSettingsFilterLowDeltasMode.OFF;
        var178_103 = "filter-low-deltas";
        AimSettings.filterLowDeltas$delegate = SettingFactory.modeSetting(Aura.INSTANCE, var178_103, var175_102, var174_101, var173_100);
        AimSettings.lowDeltaThreshold$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "low-delta-threshold", 1.0f, RangesKt.rangeTo(0.0f, 20.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$59(), ()Ljava/lang/Boolean;)());
        AimSettings.replaceMin$delegate = SettingFactory.floatSetting(Aura.INSTANCE, "replace-min", 3.0f, RangesKt.rangeTo(0.0f, 45.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$60(), ()Ljava/lang/Boolean;)());
        AimSettings.replaceMax = SettingFactory.floatSetting(Aura.INSTANCE, "replace-max", 5.0f, RangesKt.rangeTo(0.0f, 45.0f), AimSettings.INSTANCE.getAdvancedOptions(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$61(), ()Ljava/lang/Boolean;)());
        v19 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v19, "ZERO");
        AimSettings.basePointOffset = v19;
        v20 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v20, "ZERO");
        AimSettings.jitterOffset = v20;
        v21 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v21, "ZERO");
        AimSettings.aimPointOffset = v21;
        AimSettings.yawSpring = new Spring(0.0f, 0.0f, null);
        AimSettings.pitchSpring = new Spring(0.0f, 0.0f, null);
        AimSettings.pointHSpring = new Spring(0.0f, 0.0f, null);
        AimSettings.s = new Spring(0.0f, 0.0f, null);
        AimSettings.pointVSpring = new Spring(0.0f, 0.0f, null);
        AimSettings.yawSpeed = 1.0f;
        AimSettings.pitchSpeed = 1.0f;
        AimSettings.p = RandomUtil.P(AimSettings.INSTANCE.getHuIntervalMin(), AimSettings.INSTANCE.getHuIntervalMax(), false, null);
        var60_30 = new List[5];
        var61_104 = new Pair[]{TuplesKt.to(0.15000153, 0.14999962), TuplesKt.to(-0.15000153, -0.14999962), TuplesKt.to(-0.6000061, 0.0), TuplesKt.to(-0.6000061, 0.0), TuplesKt.to(0.45000458, 0.0)};
        var60_30[0] = CollectionsKt.listOf(var61_104);
        var61_104 = new Pair[]{TuplesKt.to(-0.15000153, 0.0), TuplesKt.to(-0.30000305, 0.0), TuplesKt.to(0.30000305, 0.14999962), TuplesKt.to(0.15000153, -0.14999962), TuplesKt.to(0.0, 0.0)};
        var60_30[1] = CollectionsKt.listOf(var61_104);
        var61_104 = new Pair[]{TuplesKt.to(0.15000153, -0.14999962), TuplesKt.to(-0.6000061, 0.0), TuplesKt.to(0.15000153, 0.14999962), TuplesKt.to(-0.15000153, 0.0), TuplesKt.to(0.30000305, 0.0)};
        var60_30[2] = CollectionsKt.listOf(var61_104);
        var61_104 = new Pair[]{TuplesKt.to(-0.6000061, 0.29999924), TuplesKt.to(0.6000061, 0.29999924), TuplesKt.to(-0.30000305, -0.44999886), TuplesKt.to(0.15000153, 0.0), TuplesKt.to(0.0, 0.0)};
        var60_30[3] = CollectionsKt.listOf(var61_104);
        var61_104 = new Pair[]{TuplesKt.to(-0.30000305, 0.0), TuplesKt.to(-0.29999542, 0.6000004), TuplesKt.to(0.30000305, 0.0), TuplesKt.to(-0.7500076, -0.44999886), TuplesKt.to(0.30000305, 0.0)};
        var60_30[4] = CollectionsKt.listOf(var61_104);
        AimSettings.clickPatterns = CollectionsKt.listOf(var60_30);
    }

    public static void zkmSetFlag(int[] nArray) {
        Y = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return Y;
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x195C;
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
                throw new RuntimeException("su/catlean/tp", exception);
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
            AimSettings.gb[n2] = AimSettings.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AimSettings.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x590E;
        if (kb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = jb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])lb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    lb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/tp", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AimSettings.kb[n2] = n3;
        }
        return kb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AimSettings.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x35ED;
        if (ob[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = mb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])pb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    pb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/tp", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            AimSettings.ob[n2] = l4;
        }
        return ob[n2];
    }

    private static long d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = AimSettings.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/tp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AimSettings.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AimSettings.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(AimSettings.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

