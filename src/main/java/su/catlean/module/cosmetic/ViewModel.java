/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Quaternionfc
 */
package su.catlean.module.cosmetic;

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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.HandModifyEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ViewModel
extends Module {
    @NotNull
    public static final ViewModel INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting syncHands$delegate;
    @NotNull
    private static final SettingGroup mainHand$delegate;
    @NotNull
    private static final FloatSetting x$delegate;
    @NotNull
    private static final FloatSetting y$delegate;
    @NotNull
    private static final FloatSetting z$delegate;
    @NotNull
    private static final FloatSetting scale$delegate;
    @NotNull
    private static final FloatSetting yaw$delegate;
    @NotNull
    private static final FloatSetting pitch$delegate;
    @NotNull
    private static final FloatSetting roll$delegate;
    @NotNull
    private static final BooleanSetting rotateYaw$delegate;
    @NotNull
    private static final BooleanSetting rotatePitch$delegate;
    @NotNull
    private static final BooleanSetting rotateRoll$delegate;
    @NotNull
    private static final FloatSetting rotationSpeed$delegate;
    @NotNull
    private static final SettingGroup offHand$delegate;
    @NotNull
    private static final FloatSetting offX$delegate;
    @NotNull
    private static final FloatSetting offY$delegate;
    @NotNull
    private static final FloatSetting offZ$delegate;
    @NotNull
    private static final FloatSetting scaleOff$delegate;
    @NotNull
    private static final FloatSetting offYaw$delegate;
    @NotNull
    private static final FloatSetting offPitch$delegate;
    @NotNull
    private static final FloatSetting offRoll$delegate;
    @NotNull
    private static final BooleanSetting offRotateYaw$delegate;
    @NotNull
    private static final BooleanSetting offRotatePitch$delegate;
    @NotNull
    private static final BooleanSetting offRotateRoll$delegate;
    @NotNull
    private static final FloatSetting offRotationSpeed$delegate;
    @NotNull
    private static final SettingGroup eatMod$delegate;
    @NotNull
    private static final FloatSetting eatX$delegate;
    @NotNull
    private static final FloatSetting eatY$delegate;
    @NotNull
    private static final FloatSetting eatShake$delegate;
    private static float pitch;
    private static float yaw;
    private static float roll;
    private static float offPitch;
    private static float offYaw;
    private static float offRoll;
    private static final long m;
    private static final String[] t;
    private static final String[] u;
    private static final Map C;
    private static final long[] I;
    private static final Integer[] ab;
    private static final Map fb;

    private ViewModel() {
        super("view-model", Categories.getCosmetic(), null, null);
    }

    private final boolean getSyncHands() {
        return (Boolean)syncHands$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSyncHands(boolean bl) {
        syncHands$delegate.setValue(this, delegatedProperties[0], bl);
    }

    private final SettingParent getMainHand() {
        return (SettingParent)mainHand$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setMainHand(SettingParent settingParent) {
        mainHand$delegate.setValue(this, delegatedProperties[1], settingParent);
    }

    private final float getX() {
        return ((Number)x$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final void setX(float f) {
        x$delegate.setValue(this, delegatedProperties[2], Float.valueOf(f));
    }

    private final float getY() {
        return ((Number)y$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final void setY(float f) {
        y$delegate.setValue(this, delegatedProperties[3], Float.valueOf(f));
    }

    private final float getZ() {
        return ((Number)z$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    private final void setZ(float f) {
        z$delegate.setValue(this, delegatedProperties[4], Float.valueOf(f));
    }

    public final float getScale() {
        return ((Number)scale$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    public final void setScale(float f) {
        block0: {
            long l = 0L;
            l = m ^ l;
            ZkmReferences.zkmBootstrap("\u00c5", (long)2320528421048298716L, (long)l);
            scale$delegate.setValue(this, delegatedProperties[5], Float.valueOf(f));
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)2306874488422481554L, (long)l) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", "K07vO", (long)2362851222541570028L, (long)l);
        }
    }

    private final float getYaw() {
        return ((Number)yaw$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    private final void setYaw(float f) {
        yaw$delegate.setValue(this, delegatedProperties[6], Float.valueOf(f));
    }

    private final float getPitch() {
        return ((Number)pitch$delegate.getValue(this, delegatedProperties[7])).floatValue();
    }

    private final void setPitch(float f) {
        pitch$delegate.setValue(this, delegatedProperties[7], Float.valueOf(f));
    }

    private final float getRoll() {
        return ((Number)roll$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final void setRoll(float f) {
        roll$delegate.setValue(this, delegatedProperties[8], Float.valueOf(f));
    }

    private final boolean getRotateYaw() {
        return (Boolean)rotateYaw$delegate.getValue(this, delegatedProperties[9]);
    }

    private final void setRotateYaw(boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ m;
        rotateYaw$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)25996, (long)(0x55C0B2262E27EAB7L ^ l))], bl);
    }

    private final boolean getRotatePitch() {
        return (Boolean)rotatePitch$delegate.getValue(this, delegatedProperties[10]);
    }

    private final void setRotatePitch(boolean bl) {
        long l = 0L;
        l = m ^ l;
        rotatePitch$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)13007, (long)(0x7129626D1906AFA3L ^ l))], bl);
    }

    private final boolean getRotateRoll() {
        return (Boolean)rotateRoll$delegate.getValue(this, delegatedProperties[11]);
    }

    private final void setRotateRoll(boolean bl) {
        long l = 0L;
        l = m ^ l;
        rotateRoll$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)13947, (long)(0x105C2A1B27B2457DL ^ l))], bl);
    }

    private final float getRotationSpeed() {
        return ((Number)rotationSpeed$delegate.getValue(this, delegatedProperties[12])).floatValue();
    }

    private final void setRotationSpeed(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ m;
        rotationSpeed$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)29059, (long)(0x2D44BAEB945126E6L ^ l))], Float.valueOf(f));
    }

    private final SettingParent getOffHand() {
        return (SettingParent)offHand$delegate.getValue(this, delegatedProperties[13]);
    }

    private final void setOffHand(SettingParent settingParent) {
        long l = 0L;
        l = m ^ l;
        offHand$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)15544, (long)(0x26A3DBC791E1C06L ^ l))], settingParent);
    }

    private final float getOffX() {
        return ((Number)offX$delegate.getValue(this, delegatedProperties[14])).floatValue();
    }

    private final void setOffX(float f) {
        offX$delegate.setValue(this, delegatedProperties[14], Float.valueOf(f));
    }

    private final float getOffY() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ m;
        return ((Number)offY$delegate.getValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)25372, (long)(0xDA289D0215D3084L ^ l))])).floatValue();
    }

    private final void setOffY(float f) {
        offY$delegate.setValue(this, delegatedProperties[15], Float.valueOf(f));
    }

    private final float getOffZ() {
        return ((Number)offZ$delegate.getValue(this, delegatedProperties[16])).floatValue();
    }

    private final void setOffZ(float f) {
        offZ$delegate.setValue(this, delegatedProperties[16], Float.valueOf(f));
    }

    private final float getScaleOff() {
        return ((Number)scaleOff$delegate.getValue(this, delegatedProperties[17])).floatValue();
    }

    private final void setScaleOff(float f) {
        scaleOff$delegate.setValue(this, delegatedProperties[17], Float.valueOf(f));
    }

    private final float getOffYaw() {
        return ((Number)offYaw$delegate.getValue(this, delegatedProperties[18])).floatValue();
    }

    private final void setOffYaw(float f) {
        offYaw$delegate.setValue(this, delegatedProperties[18], Float.valueOf(f));
    }

    private final float getOffPitch() {
        return ((Number)offPitch$delegate.getValue(this, delegatedProperties[19])).floatValue();
    }

    private final void setOffPitch(float f) {
        offPitch$delegate.setValue(this, delegatedProperties[19], Float.valueOf(f));
    }

    private final float getOffRoll() {
        return ((Number)offRoll$delegate.getValue(this, delegatedProperties[20])).floatValue();
    }

    private final void setOffRoll(float f) {
        offRoll$delegate.setValue(this, delegatedProperties[20], Float.valueOf(f));
    }

    private final boolean getOffRotateYaw() {
        return (Boolean)offRotateYaw$delegate.getValue(this, delegatedProperties[21]);
    }

    private final void setOffRotateYaw(boolean bl) {
        long l = 0L;
        l = m ^ l;
        offRotateYaw$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)32509, (long)(0x7304F43721EDAB39L ^ l))], bl);
    }

    private final boolean getOffRotatePitch() {
        return (Boolean)offRotatePitch$delegate.getValue(this, delegatedProperties[22]);
    }

    private final void setOffRotatePitch(boolean bl) {
        long l = 0L;
        l = m ^ l;
        offRotatePitch$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)21319, (long)(0x344A25DDDDC9FCAEL ^ l))], bl);
    }

    private final boolean getOffRotateRoll() {
        return (Boolean)offRotateRoll$delegate.getValue(this, delegatedProperties[23]);
    }

    private final void setOffRotateRoll(boolean bl) {
        long l = 0L;
        l = m ^ l;
        offRotateRoll$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)18119, (long)(0x5D44E770BF664BD1L ^ l))], bl);
    }

    private final float getOffRotationSpeed() {
        return ((Number)offRotationSpeed$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    private final void setOffRotationSpeed(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ m;
        offRotationSpeed$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)31824, (long)(0x32672C5CE378BA54L ^ l))], Float.valueOf(f));
    }

    private final SettingParent getEatMod() {
        return (SettingParent)eatMod$delegate.getValue(this, delegatedProperties[25]);
    }

    private final void setEatMod(SettingParent settingParent) {
        long l = 0L;
        l = m ^ l;
        eatMod$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)29361, (long)(0x72F75E8EB77A674BL ^ l))], settingParent);
    }

    private final float getEatX() {
        return ((Number)eatX$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    private final void setEatX(float f) {
        long l = 0L;
        l = m ^ l;
        eatX$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)29588, (long)(0x121564D79309EE5AL ^ l))], Float.valueOf(f));
    }

    private final float getEatY() {
        return ((Number)eatY$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    private final void setEatY(float f) {
        long l = 0L;
        l = m ^ l;
        eatY$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)11254, (long)(0x5AB42CAE00177A9L ^ l))], Float.valueOf(f));
    }

    private final float getEatShake() {
        return ((Number)eatShake$delegate.getValue(this, delegatedProperties[28])).floatValue();
    }

    private final void setEatShake(float f) {
        long l = 0L;
        l = m ^ l;
        eatShake$delegate.setValue(this, delegatedProperties[ViewModel.zkmBootstrap2("a", (int)3221, (long)(0x3B5D05CF4AB9496CL ^ l))], Float.valueOf(f));
    }

    private final float wrapAngleDelta(float f, float f2) {
        return f - f2 <= 180.0f && f - f2 > -180.0f ? f - f2 : 180.0f;
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        pitch = this.getPitch();
        yaw = this.getYaw();
        roll = this.getRoll();
        offPitch = this.getOffPitch();
        offYaw = this.getOffYaw();
        offRoll = this.getOffRoll();
        if (this.getRotateYaw()) {
            float f = this.getRotationSpeed();
            float f2 = this.getYaw();
            this.setYaw(this.wrapAngleDelta(f2, f));
        }
        if (this.getRotatePitch()) {
            float f = this.getRotationSpeed();
            float f3 = this.getPitch();
            this.setPitch(this.wrapAngleDelta(f3, f));
        }
        if (this.getRotateRoll()) {
            float f = this.getRotationSpeed();
            float f4 = this.getRoll();
            this.setRoll(this.wrapAngleDelta(f4, f));
        }
        if (this.getOffRotateYaw()) {
            float f = this.getOffRotationSpeed();
            float f5 = this.getOffYaw();
            this.setOffYaw(this.wrapAngleDelta(f5, f));
        }
        if (this.getOffRotatePitch()) {
            float f = this.getOffRotationSpeed();
            float f6 = this.getOffPitch();
            this.setOffPitch(this.wrapAngleDelta(f6, f));
        }
        if (this.getOffRotateRoll()) {
            float f = this.getOffRotationSpeed();
            float f7 = this.getOffRoll();
            this.setOffRoll(this.wrapAngleDelta(f7, f));
        }
    }

    @Flow
    private final void onHandModify(HandModifyEvent handModifyEvent) {
        if (this.getSyncHands()) {
            this.setOffX(this.getX());
            float f = this.getY();
            this.setOffY(f);
            this.setOffZ(this.getZ());
            this.setScaleOff(this.getScale());
            this.setOffYaw(-this.getYaw());
            this.setOffPitch(this.getPitch());
            this.setOffRoll(-this.getRoll());
        }
        if (handModifyEvent.getEating()) {
            this.applyEatTransform(handModifyEvent.getStack(), RenderState.INSTANCE.getTickDelta(), handModifyEvent.getHand(), handModifyEvent.getItem());
            handModifyEvent.cancel();
        } else if (handModifyEvent.getHand() == Hand.MAIN_HAND) {
            handModifyEvent.getStack().translate(this.getX(), this.getY(), this.getZ());
            handModifyEvent.getStack().scale(this.getScale(), this.getScale(), this.getScale());
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(RenderUtil.INSTANCE.lerp(pitch, this.getPitch(), Math.abs(pitch - this.getPitch()) > 90.0f ? 1.0f : RenderState.INSTANCE.getTickDelta())));
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(RenderUtil.INSTANCE.lerp(yaw, this.getYaw(), Math.abs(yaw - this.getYaw()) > 90.0f ? 1.0f : RenderState.INSTANCE.getTickDelta())));
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(RenderUtil.INSTANCE.lerp(roll, this.getRoll(), Math.abs(roll - this.getRoll()) > 90.0f ? 1.0f : RenderState.INSTANCE.getTickDelta())));
        } else {
            handModifyEvent.getStack().translate(-this.getOffX(), this.getOffY(), this.getOffZ());
            handModifyEvent.getStack().scale(this.getScaleOff(), this.getScaleOff(), this.getScaleOff());
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(RenderUtil.INSTANCE.lerp(offPitch, this.getOffPitch(), RenderState.INSTANCE.getTickDelta())));
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(RenderUtil.INSTANCE.lerp(offYaw, this.getOffYaw(), RenderState.INSTANCE.getTickDelta())));
            handModifyEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(RenderUtil.INSTANCE.lerp(offRoll, this.getOffRoll(), RenderState.INSTANCE.getTickDelta())));
        }
    }

    private final void applyEatTransform(MatrixStack matrixStack, float f, Hand hand, ItemStack itemStack) {
        float f2;
        float f3 = (float)Globals.getPlayer().getItemUseTimeLeft() - f + 1.0f;
        float f4 = f3 / (float)itemStack.getMaxUseTime(Globals.getPlayer());
        if (f4 < 0.8f) {
            f2 = MathHelper.abs((float)Math.cos(f3 / 4.0f * (float)Math.PI) * this.getEatShake());
            matrixStack.translate(0.0f, f2, 0.0f);
        }
        f2 = 1.0f - (float)Math.pow(f4, 27.0);
        int n = hand == Hand.MAIN_HAND ? 1 : -1;
        matrixStack.translate(f2 * 0.6f * (float)n * this.getEatX(), f2 * -0.5f * this.getEatY(), f2 * 0.0f);
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)n * f2 * 90.0f));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f2 * 10.0f));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)n * f2 * 30.0f));
    }

    public final void applyMainHandOffset(@NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "matrices");
        if (this.getEnabled()) {
            matrixStack.translate(this.getX(), this.getY(), this.getZ());
        }
    }

    public final void applyOffHandOffset(@NotNull MatrixStack matrixStack) {
        block3: {
            MatrixStack matrixStack2;
            block2: {
                long l = 0L;
                l = m ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)340127160723703888L, (long)l);
                matrixStack2 = matrixStack;
                if (callSite == null) break block2;
                Intrinsics.checkNotNullParameter(matrixStack2, (String)((Object)ViewModel.zkmBootstrap("m", (int)32059, (long)(0x320DCAAEC1DEB794L ^ l))));
                if (!this.getEnabled()) break block3;
                matrixStack2 = matrixStack;
            }
            matrixStack2.translate(-this.getOffX(), this.getOffY(), this.getOffZ());
        }
    }

    public final void undoMainHandOffset(@NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "matrices");
        if (this.getEnabled()) {
            matrixStack.translate(-this.getX(), -this.getY(), -this.getZ());
        }
    }

    public final void undoOffHandOffset(@NotNull MatrixStack matrixStack) {
        block3: {
            MatrixStack matrixStack2;
            block2: {
                long l = 0L;
                l = m ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-6101166318812223556L, (long)l);
                matrixStack2 = matrixStack;
                if (callSite == null) break block2;
                Intrinsics.checkNotNullParameter(matrixStack2, (String)((Object)ViewModel.zkmBootstrap("m", (int)6824, (long)(0x33F714912EBBFF9BL ^ l))));
                if (!this.getEnabled()) break block3;
                matrixStack2 = matrixStack;
            }
            matrixStack2.translate(this.getOffX(), -this.getOffY(), -this.getOffZ());
        }
    }

    private static final boolean settingVisible() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible2() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible3() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible4() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible5() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible6() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible7() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible8() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible9() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible10() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible11() {
        return !INSTANCE.getSyncHands();
    }

    private static final boolean settingVisible12() {
        return !INSTANCE.getSyncHands();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ViewModel.m = ZkmKeys.a(6778799673540910630L, 2944933690479015323L, MethodHandles.lookup().lookupClass()).a(44947597034944L);
                        var20 = ViewModel.m ^ 92133040001168L;
                        ViewModel.C = new HashMap<K, V>(13);
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
                        var18_3 = new String[84];
                        var16_4 = 0;
                        var15_5 = "\u00d1\f\u0087\u00f9\u0017\u00e1\u00a4\u00c9K\u00c8\u000e\u0007\u00c6)e\u00e4\u008f2\u00ef\u009aJ\u00d4\u00a9\u0000\u00cex\u00ea\n\u00a8\u00cd.&-4W(W\u00c4*\u00b5 \u00bb\u00e5\u00b4\u00d6\u0081\u00f9\u00db(\u00b1\u00a3O\u00a2Lqk/7x\u00e0\u0090\u0086a\u00f1<\u000471\u00f8\u00f4\u00c2\u0083/\u0018\u0089O\u00f9m\u00d9\u000eo8\u001eYBu\u0093\u00d1\u00faf\u0080\u00ff\u0082B\u00f0O\u0084\u00fa\u0010\u0014\u0006r\u000b\u00e0\u00d8\u001eQ\u00b0Z\u0084\u00ef\u0080Z\u00c2\u008b \u00a7\u0099\u00d41\u00f8\u00b7\u00a3\u0085Nt\u008b\u00b4\u0081;W\u00cd\u00db&\u00b6\u0001\u0099\u009d\u0003D\u0017e-i[U\u00b0\u00c2\u0010=\u00edEj\u009f\u00d6\u00f1\u00fd\u00921\u00e3>?\u00b5\u00b6\u00bd\u0018\u00d0\u00cbc\u00d6\u00f7\u00cc%\u0002\u00f9\u0005\u00c7\u00f9;\u0089\u008a\u00b4\u0080\u0083E+\u00bd\u00c0\u00b6\u00af\u0010XEOO\u00936Te\u00cf\u00b8'\u0012fu\u00a8X \u0081\u00e0*\u001b\u00b2\u00a6M\u00fe\u00feJ\u001fz\u00d0\u00c0\u0002UF:\u00bd\u00ec\u0002k\u009b\u008a\u00d0\u00ed\u00aa\u0005\u00ca\n$\u00da\u0018\u00a5D\u00d8gk\u0093\u00a3[{\u00e2\u00aa\u0089H\u00c7W\u0085\u00c8{\u008e\u0013\u0012\u00ea0\u00ea \u0015y\u00b5\u008bs6\u00e9b\u008e8\u00ea)\u0098\u009d\u00ff\u00e0y\u000b\u0002\u00ec\u00e8S\u0013\u00b5\u00f1\u0019[\u00e6\u0086\u00f3\u0017^ \u00a3\u00e5uM&l\u00dc\u00ce\u00c1\u0099\\A\u00fbT]\u008eGDG\u00d3\u000e4\u00a5x\u00f5+\u00c3K\f\u00b4}Y\u0018\u000fAfNv\u0007\u0086\u00f6\u00fb\u00c1Q\u00d7\u0097\u0006\u00d3 \u00d0s\u00a9\"s\tq\u00a2 \u009es\r\u00a8\u00e2\u00bfhS\u001a\u009a\u00e4C\u009474<\u00eb\u001c\u0092CBe(\u001d\u001f\u000e\u00f8\u00d8\u00903\u00fbT F\u0084?\u0085Q]E\u0095\u0086o\u00d4\u00d0\u0097\u001bWC\u009bU\u00ec\u00e4\u0019\u0004\u00be\u0006wP#\u0012\u00d1\u00dd\u000b\u009a03\u00b9\u001f\u00cah\u00161$NZ\n\u00a6\u0006t\\\u00ef\u00dd~\u0082J9K\u00bf\u00cfD\u00dcW\u00fa\u00b8NJ\u00da\u00ac_\u00f9\u0012\u00be\u008d\u00cfS\u00b8_\u00f2T\u0005\u008a\u009c\u00d3 R\u001fG\u0017\u0015\u0017\u00b0C9\u00e1\u0087\u00e4\u000b\u000f\u009c]#\u00b9\u0012O(\u00e4\u00ee0 s,\u0018\u0081\u008b\u00e6\u00f8\u0018\u00e7\u00f0LS\u00ab\u00bb7\u0087\u00dc\u00ee\u00d8\u0000\u00a0y\u0015\u001aKK\u00bbdp\u00da\u0094\u00f8\u0018\u00c4\u001a\u0011\u0085\u00bd.6r[\u000b\u00fe\u0006\f\t5\u00cf\t\u00b4\u00f70`\u0082\u009d\u00d9\u0018\u008f\rs\u00b8\u0098q\u00ad\u00eda\u00e8&n<\u00a2\u00f8\u001b?\u00ca\u0086\u00f2W+:\r\u0010\u00a5\u00ba\u00dcK\u00c2\u001b{7\u00d1\u0097\u00e4BiN\u009b\u0088 \u008b\u00ea\u00d3\u00f1\u0081\u00cf{\u00d24]\u008d\u00ceo0\u0011t\u00ce\u00d3\u00e8jH`\u0097\u00a3\u00a8\u00f1b\u00cdl\u00a8&\u0014 \u00e2^5\u00b6\u0088\u0013ls\u00fc 3I\u00c3Xs<\u00bc[\u00f4&+OW[%8\u0080\u00e9z\u00c0\u00e3\u0085 \u0080\u0019\u00c9\u00df\u00af\u0007\u0099\u00f6K\u001e\u00b6#\u00cb\u00da!\u009f\u008f\u008fq\u00c5\u00dbB7Q\u000exO\u008e\u00e8\u0003\u007f\"(yZ\u0082^\u00f3\u000f\u00b9\u0094\u00dd\u00cb\u00a8\u0011\u0002=\u00976\u00101.\u008c\u00ee\u00f7\u00b1[\u00fe\u00de=\u00c5\u00ac\u0018\u001e/\u00a2x\u008c/\u009a\u00f5\u00e0\u00e4 ,\u0007\u0097@2\u00a8d1\u00df\u00e1\u00f3\u0099v\u0096\u00bfk\u00dc\u00acR\u00bc\u0000\u00a4v\u0088\u00c9 z\u000f\u001e\u0013\u00f1\u00ab(\u0017\u00db\u00ed\u00b1f \u00f2n\u00e4\u00a7\u00b2u\u00d0XC\u0095\u00a2\u0081\u00d0\u00e8p\u00de\u00b9\u0084\u00e6\u0085TH\u0007\u00e3\u00865y\u001c\r\u0016y_\u00b1\u00c2\u0018r\u00a1\u00fa\u00a0{\u00a5i\u00a2%\u00b7\u00e7)\u00e9\u0006\u00de\u0088\u0090\u0016ygF\t\u00bc\u0094\u0018&\u00d7:(K\"\u00e0\t&\u00f1$;\u00c2\u007f\u0097\u00e9\u00f6^\u00f91\u0092\u00c6\u00e9X0\u00d1\u00a7\u001a\u00ccMBF\u00c6=>\u00f8\u0006\u001fg\u00d5M\u00c6\u00c7B\u00fb\u00b2YU\u00c9\u0087t,o\u00e7\u0000\u00ef\u008e\u00bd\u00c4\u00c9\u00d5\u00ffP\u00059t\u00a8\u008cOT\u00bcc: z\u0082'\u00cd.\u00b8/\u00a8n\u009cw\nqL\f_?\u00b3\u00bd?W\u00e8\u0091i\u0017:m\u00c4G8\u00c3\u00d3\u0018$\u00e3\u0091_\u00d2\u00a4\u00ff\u0092\u0080\u0016\u0090\u00d5\u00ddTB\u00ceUd\u00b9\u00c5\u00881b\u0086 \u000eD\u0014\u00faz\u00a9A\u00a5\u00f5\u00b3\u0092\t_$\u001dVX\bqk\u0011{\u0005\u0081\u00fa\u00d55\u00ec\u00d0\u00b5\u001e\u009d \u00a5\u0090\u00e4\u00e5\u00ff\u00e2\u00b4>\u0090\u00cd\u00e2\u00b4v\u00f7\u00a8{ReW\u00d5)\u00b2\r\u009e\u0089*\u00d0\u008d\u00db\u009f\u00dc\u00bd\u0010{\u00f9\u00de\u00d5K\u0091\u00d1_\u00dd\u00db\u00d4D&\u00fd\\\u00cc\u0010\u00d4\u000e\u00b56\u00b3\u00d0s\u00ef\u0088\u0000p\b\u0019#\u00fe.05k^\u0010s\u0085ji\u0095 \u0014\u001a\u00d7\u0089z\u00d32\u009c\u00185\u0098\u00fd2OZh\u00deE\u00d9\u008a\u008bRd\u0013\u00df\u00c6\u00cbu\u00c9&}_e\u008dJ\u008a\u00fc\u00b8\u0010\u00ad\u00e9\u00c6)\u00a0?\u00a4aj\u0094\u00fc5\u0087(66 \u00a6f\u0089;\u009d\u0002\u00d3\u00ceD\u0081\u0003\u0014=\u00fe\"+A\u008e\u00914\u00a3\u008f\u00dafv-\u00acbyR\\\u0003 \u0089\bO\u00df\u00d9\u00cd\u00dbD}@\"x\u00ad\u0084F\u00a6\u00a1\u00f4C\u00e5\u00a1`s\u00ee\u00b3d2\u008d0\u007f\u0092\u00d3\u0010\u00d5\u00e8\u00f8\u00c7p\u0014\u008d\u008eR\u00a6\u00aa^\u00d6\u0010D\u0017\u0010u\u00f7\u0001ZA\u0094@A\u0014\u00b6\u001eZ\u0006;\u001c-\u0010\u0081\u009a\u00f6\u0093\u00af\u00d5\u0012\u00f6\u0018\u009b\u0093\u00a9\u00a98\u00c8n\u0018\u00a4B#\u0018Q\u00e6Q\u00ce\u0096Kf\u00c0\u0015\u00df\u009f\u00fc\nB\u00d0\u0098t\u0083\u00b0\u00cc\u0018\r\u008e\u008c\u0082\u0093d@c_W\u00af!\u00f8OV\u00ab\u000b\u00a2\u001d\u0004\u00f8\u0019,o \u00c6\u00fa\u00ff\u00e0\u00b6?\u0099\u00c3\u00f0\u0081\u00bbP\u00d2U]i\u009eS\u0094/\u0084Pa\u00af\u009aj\u0013p\f.h#\u0010\u009a3\u0001\u00fe\u00c2Z\u00e7\u0007\u00f7\u00d9\u0087\u001b\t\u00d5q\u009b\u00187\u0094)l\u0096\u00dc\u00c7\u00da\u0086\u00ce(\u00f6m1\u00034|\u0081\u00c1\u00ae\u00b3\u0091\u000f\u0004 ?\u00bf\u00f1\u0094X\u0092\u001e\u008b\u0007\u00cdB\u00e6\u00ce\u00b9\u008d\u00c6w;\u001b\u00ac\u00a1\u00c3c\u00c8$\u00ebJ\u00e2{\u00b6N\u00e8\u0010x\u00f6\u009b\u00ccM\u00ae\u009c\u00e6\u000e(\u00e3r\u00ce\t@x\u0010\u0000\u00dc\u00fb\u0002\u00ce\u00a3\u00de\u00a5\u0089\u0092Z\u00b7\u0080\u00b8\u0004\u008c \u00d5\u0017D9OU< \u00c8]\u0098H%W\u001e\u00be\u0090e\u00c4\u00eb\u00980\u00ea\u00fag\u0005\u009d\u00f8\u00c2\u00b5\u00c5g\u0018\u001bb\u000b$\u0098\n\u0092\u00dfY\u000f\u0095\u00b0\u00f1\u00bdZ\u0015HuV\r\u00d6\u0011I\f\u0010j\u0083\u00c1\u00ee\u00b3\u00f7k\u00db\nK\u001b\u0013\u00ed\u001c<\u0081\u0010\u00daU\u008d\u00d3Q\u00b0\u009a\u000e\u00f5\u00c9\u00b9o\\}G\u0004\u0018V\u00bd2Y9p\u009d\u0012\u0017\u009c\u001e\u00f8$\u009er\u00e3!\u00bfV\u0091\u00adgK_\u0010-\u00a7yAD\u009e\u008b\u007fEz\u0000x9\u00b4=3 \u00e03\u0096\u00c4\u000el\u000e7\u0001\u00db\u00c9\u008e\u009eU\u0015\u00f7r\u000b\u0091\u0081a.T\u00ce \u00e2h\t\u00c9\u00f6\u0093\u008a(\u00b6\u00a2\u00d9\u0002\u00ce\u00e3\u00e2W\u00a3\u00e6\u00a6\u00a3g\u00e4\u001c\u0095Y[\u0084@A\u00aa\u00e0\u00f6*k\u0088K$Jn\u00d8;\\P1\u0016@\u0081\u00f28_Y&,\u00d5\u0002~.[\u009cE\u0011\u0013MK\u00ee\u0013\u00d6BO`p\u0018\u00c1\u00c6\u0003\u00d5\u00d9\u00f4\u0015\u00ef\u007f\u00e6u\u00dd\u00c6\u0081'\u000f?\u00a9\u0019\u0016\u00c2\u009c\u008f\u00b3^\u00c4\u00f0\u0018\u007f\u00e68B\u0019 #6\u00b0\u00ffIa^\u00b9\u00c10\u0010\u009a\u00dcB\u0016L\u008c0\u00a4W\u00a4P\u0014\u00a7\n\u00c8\u0086$\u000f\u000e\u00f3\u0097 \u00cb1.\u0005\u00be\u00ab\u00aa~M\u00e0\u0082D[%I\u0081\u00f4@q\u00b8\u00b6\u00e5/^\u00d5\u0003\u00be\u00f3\u00fa\u00fa&\u00b6\u0010\u00d9i\u00fc]\u00ecv\u00ad\u009a\u00c3\u009c\u00ac\u00f6*\u0098\u00e7\u008b\u0018\u0015\u00c69\u009b\u00c6\u0098\u00bcE3\u0003|~* .\u008d\u000e\u0099\u00d2\u00cd\u0082\u00d6?\u00e9\u0010\u00f5\b\u00c4\u0093j&1a+\u009d\u00bf\u00ed\u00b8\u00cf\u00b2\u00b3\u0010\u00c3#\u00c6\u001fe+4a\u008a\u0006g\u00f6\u00d0\u0007\u001f\t\u0010f\u001b\u00b16U\u0017\u00b2*X\u0001\u0011\u00cb\u001dH\u00d2N N\n\u00fa\u0090#\u001a\u0085\u00d0d\u0014\u00fd\u00eb~\u00d1\u0004-~\u00e2^\u00c7\u0002r\u00f1\u001bM\u00e5\b\u00fa\u00a0\u008a\u0086\u00f0\u0018\u0000x+\u00f39\u00fe\u0084\u0001D9\u00aa\u00cf\u00b8\u009cs.\u00c8\u00c4\u0012\b\u00f8vJE\u0010'\u000b\u0002Dm\u00b9z\u009c\u00ad\u00b8\u00bc\u00d7\u00den\u008a\u00d1\u0010\u0084\u00b2\tZ\u00ef&\u00d1\u00fa\u00ca\u00a3\u00a5)\u0085\u00a2\u00af7\u0018\u0087Z\u00d1\u001f\u00cc\u009a>\u00b7d\u008d\u00e8\u009f\u00bd\u009cS>\u00f8\u00c2\u00c6\u0017h\u0085\u00d4j(\u0016\u0011\u0007g\u0000\u00b8\u00a4\u00a0D\u007f\u0095\u0091DY\u00fcw<\u00a5\u00f1\u00e5]K\u0003*O\u00bb\u0010|e\u00cc\u00d7@PEIt/\u00ecJ? \u00d4\u00de\u00a6\u00f2\u00d33\u00bd\u00b74<\u00b5~\u00b3\u00db\u00f0b\u00ad\u00af;\u00a6\u00c8\u00cfR\u000b0\u008fQ\u00ab\\\u00b1\u0081I\u0010\u00a4\u00958`\u00e0\u00df\u00dd\u00af\u00bc>k\u0011\u00c7\u00feJB\u0010\u00d1\u00cc}\u00cc\u00ceJk\u00d1\u00b9U\nc\u00fa\u00b5\u00c3R Y\u00d7)}\u001c\u00f3\u00ecH`~\u00c7Fi\u008f<N\u008a\u0081\u0084\u0085\u00d5\u00de\u00ca\u0004+\u00aa\u00a2x\u00cbg_q(\u00dc\u00f8\u009fd'\u00f8\u0001\u00dbCM\u0000\u00a2\u00c0\u0003\u0091\u0013S/\u0084@`\u0002\u00aa\u00e1y\u00dd\u00fa[\u00aa(\u00abX\u00e8L\u000b(\u00c3\u009c\u00af\u00a0\u0010W{3\u00feR}\u00d54\u00f1mF\u00ede\u00bblV\u0010{\u00a6V\u001b9\u00bf<\u00eek\u00be\u00fa\u00f5\u00f1\u00de3,(\u00cdI\u00a6\u00fd@uf;\u00a4\u001fJ\u0097)nE\u00fb\u00d2EM7>\u00deZE\u0082\u00af\u008brJ\u00af\u00a3\u00e6\u00d8 \u00e3\u00b7\u0002[\u001f\u00ab\u0010\u00c9\u001c\u00ed0\u00cf\u0088\u00a8l\u0089\u00e8mN)\u008b@\u00c2";
                        var17_6 = 2257;
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
                            var18_3[var16_4++] = ViewModel.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0096\u00f1m\u00c4b\u0093\u009a\u008d\u000e\u00c0^,\u0090\u00a6\u00f4\u00ab\u00f6\u00b1\u00a1\u00bf\u00d3\u0015w\u00a2\u0003\u00e0\u008ag>Vhp(\u00bd\u00b7\u0081\n\u00cb\u00ab]\u0086\u00e2\u00ed\u00f9^\u008c!\u008dw\u0006\u00f5[\u0016e\u00c2\u00a4\u008a\u00b6\u001c\u0003\u00ef\u00c0\u00a7\u008a`;\u00c2\u00c8w\u0001\u007fo\u00a3";
                            var17_6 = 73;
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
                            var18_3[var16_4++] = ViewModel.zkmDecodeUtf8(var19_9).intern();
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
                ViewModel.t = var18_3;
                ViewModel.u = new String[84];
                ViewModel.fb = new HashMap<K, V>(13);
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
                var6_12 = new long[47];
                var3_13 = 0;
                var4_14 = "G{\u0097S\u00cde\u00b9\u00c8#\u00e5\u0097\u00b0\u0081\u00bco4\u00a7\u0007\u00d7pve\u00ac\u0081\u00a4[\u009f\u00d2\u00b2\u0098X\u0089\u00dc\u009f@\u00bcp\u00f0\u00ad\u00fb&\u000fo\u00d1\u00ed\u0096\u008a\u00e6kL\u00c4\u00c4\u0089\u009bE\u0005\u0082\f\u0019s\u00ef\u00c4\u00d3\u0087_}1Le\u0011\u0094Q\u00ba\u0003\u00b6/\u00b8\u00f3\u001e\u001b$\u0082\u00e4B:\u001c\u00e4\u00a9WM\u00cc\u00fc\u008f\u0014q\u00bf+\u00a9+\u008a\u009eG\u0004\u00e0\u00beq\u00d3D\u00ff\u008e\u00b27\u00ba\u00ca\u00ef\u000f&\u00be\u0099\u00ed\u001e\u009f\u00c1\u00d0\u0006ZI\u00873\n\u00a2\u00da\u00c8z\u001c\u00d23\u00edl\u00d2\u0084\u00cd\u0099\u00bf\u00142\u0019\u0005\u0098\u00bb\u0098\u00e8\u008d\u007f\u00d9\u00e04v\u00a2-\u00e2\u009cb1\u0002\u000e\u0013-\u001c\u001f\u00d3\u00c0V\u00a1\u008a'\u0083\u00f4H6\u008b\u00d0Y\u00d3q>l\u009e\u00f7\u00c9\u00f2\u00a8>\u00ab\u00c70_=Li=S\u0082\u0019A\u00ae\"\b\u0080hlU7\u00bcf\u00ec\u00c9\b\u0005\u00cf\u001d\u00b3b\u0085-_K\u00dc;$q-H\u00a0\u00cb\u009a\u001cM\u00e8z\u00f90\u00d2\u001e\u00dd@=\u00eas\u0097\u00e87q\u00bd\u008b\u0093YCC\u00da+\u008f\u0019\u00df\u0084`\u0081\u00b5Z,\u00a8\u00cf\u00ddP\u0081\u00b3\u00d8\u00bfn\u0014\u00ff\u00a1\u00a264'\u0091e^\u009e\u0005\u00e6P\u0004\u00b8\u00f83\u00be\u00b7y\u009c\u0086\u0002\u008ag\u00d2\u00d4o\u0094\u001d4\u00fd\u00b7\u00dd\u0087\u00af{Y\u00c5\u00bf^\u00b2\u0000_\u00e1#9\u00dd5}\u00ed\u0011x\u00acZ\u00ed\t\u001e<\u00f3\u00b2FM\t\u00ccK\u00dfqK\u00aa\u000ew\u00b4@\u00eb\u008c\u0081]\u0002\u00aa";
                var5_15 = 360;
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
                    var4_14 = "\u00a0\u0004\u0017y\u00bc0\u00c0\u0007\u00c3\u00ae\u0007\u00c3\u0099\u00c6q\"";
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
        ViewModel.I = var6_12;
        ViewModel.ab = new Integer[47];
        var42_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "syncHands", "getSyncHands()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "mainHand", "getMainHand()Lsu/catlean/h;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "x", "getX()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "y", "getY()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "z", "getZ()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "scale", "getScale()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "yaw", "getYaw()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "pitch", "getPitch()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "roll", "getRoll()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "rotateYaw", "getRotateYaw()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "rotatePitch", "getRotatePitch()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "rotateRoll", "getRotateRoll()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "rotationSpeed", "getRotationSpeed()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offHand", "getOffHand()Lsu/catlean/h;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offX", "getOffX()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offY", "getOffY()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offZ", "getOffZ()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "scaleOff", "getScaleOff()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offYaw", "getOffYaw()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offPitch", "getOffPitch()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offRoll", "getOffRoll()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offRotateYaw", "getOffRotateYaw()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offRotatePitch", "getOffRotatePitch()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offRotateRoll", "getOffRotateRoll()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "offRotationSpeed", "getOffRotationSpeed()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "eatMod", "getEatMod()Lsu/catlean/h;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "eatX", "getEatX()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "eatY", "getEatY()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ViewModel.class, "eatShake", "getEatShake()F", 0))};
        ViewModel.delegatedProperties = var42_20;
        ViewModel.INSTANCE = new ViewModel();
        ViewModel.syncHands$delegate = SettingFactory.booleanSetting$default((SettingContainer)ViewModel.INSTANCE, "sync-hands", true, null, null, 12, null);
        ViewModel.mainHand$delegate = SettingFactory.settingGroup$default(ViewModel.INSTANCE, "main-hand", null, null, 6, null);
        var43_21 = null;
        var44_22 = 16;
        var45_23 = null;
        var46_24 = ViewModel.INSTANCE.getMainHand();
        var47_25 = RangesKt.rangeTo(-3.0f, 3.0f);
        ViewModel.x$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "x", 0.0f, var47_25, var46_24, var45_23, var44_22, var43_21);
        var50_26 = null;
        var51_27 = 16;
        var52_28 = null;
        var53_29 = ViewModel.INSTANCE.getMainHand();
        var54_30 = RangesKt.rangeTo(-3.0f, 3.0f);
        ViewModel.y$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "y", 0.0f, var54_30, var53_29, var52_28, var51_27, var50_26);
        var57_31 = null;
        var58_32 = 16;
        var59_33 = null;
        var60_34 = ViewModel.INSTANCE.getMainHand();
        var61_35 = RangesKt.rangeTo(-3.0f, 3.0f);
        ViewModel.z$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "z", 0.0f, var61_35, var60_34, var59_33, var58_32, var57_31);
        var64_36 = null;
        var65_37 = 16;
        var66_38 = null;
        var67_39 = ViewModel.INSTANCE.getMainHand();
        var68_40 = RangesKt.rangeTo(0.1f, 1.5f);
        ViewModel.scale$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "scale", 1.0f, var68_40, var67_39, var66_38, var65_37, var64_36);
        var71_41 = null;
        var72_42 = 16;
        var73_43 = null;
        var74_44 = ViewModel.INSTANCE.getMainHand();
        var75_45 = RangesKt.rangeTo(-180.0f, 180.0f);
        ViewModel.yaw$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "yaw", 0.0f, var75_45, var74_44, var73_43, var72_42, var71_41);
        var78_46 = null;
        var79_47 = 16;
        var80_48 = null;
        var81_49 = ViewModel.INSTANCE.getMainHand();
        var82_50 = RangesKt.rangeTo(-180.0f, 180.0f);
        ViewModel.pitch$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "pitch", 0.0f, var82_50, var81_49, var80_48, var79_47, var78_46);
        var85_51 = null;
        var86_52 = 16;
        var87_53 = null;
        var88_54 = ViewModel.INSTANCE.getMainHand();
        var89_55 = RangesKt.rangeTo(-180.0f, 180.0f);
        ViewModel.roll$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "roll", 0.0f, var89_55, var88_54, var87_53, var86_52, var85_51);
        ViewModel.rotateYaw$delegate = SettingFactory.booleanSetting$default((SettingContainer)ViewModel.INSTANCE, "rotate-yaw", false, ViewModel.INSTANCE.getMainHand(), null, 8, null);
        ViewModel.rotatePitch$delegate = SettingFactory.booleanSetting$default((SettingContainer)ViewModel.INSTANCE, "rotate-pitch", false, ViewModel.INSTANCE.getMainHand(), null, 8, null);
        ViewModel.rotateRoll$delegate = SettingFactory.booleanSetting$default((SettingContainer)ViewModel.INSTANCE, "rotate-roll", false, ViewModel.INSTANCE.getMainHand(), null, 8, null);
        var92_56 = null;
        var93_57 = 16;
        var94_58 = null;
        var95_59 = ViewModel.INSTANCE.getMainHand();
        var96_60 = RangesKt.rangeTo(1.0f, 5.0f);
        ViewModel.rotationSpeed$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "rotation-speed", 1.0f, var96_60, var95_59, var94_58, var93_57, var92_56);
        ViewModel.offHand$delegate = SettingFactory.settingGroup$default(ViewModel.INSTANCE, "off-hand", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 2, null);
        ViewModel.offX$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-x", 0.0f, RangesKt.rangeTo(-3.0f, 3.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)());
        ViewModel.offY$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-y", 0.0f, RangesKt.rangeTo(-3.0f, 3.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)());
        ViewModel.offZ$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-z", 0.0f, RangesKt.rangeTo(-3.0f, 3.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)());
        ViewModel.scaleOff$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-scale", 1.0f, RangesKt.rangeTo(0.1f, 1.5f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)());
        ViewModel.offYaw$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-yaw", 0.0f, RangesKt.rangeTo(-180.0f, 180.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)());
        ViewModel.offPitch$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-pitch", 0.0f, RangesKt.rangeTo(-180.0f, 180.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)());
        ViewModel.offRoll$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-roll", 0.0f, RangesKt.rangeTo(-180.0f, 180.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)());
        var99_61 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)();
        var100_62 = ViewModel.INSTANCE.getOffHand();
        var101_63 = false;
        var102_64 = "off-rotate-yaw";
        var103_65 = ViewModel.INSTANCE;
        ViewModel.offRotateYaw$delegate = SettingFactory.booleanSetting(var103_65, var102_64, var101_63, var100_62, var99_61);
        var106_66 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)();
        var107_67 = ViewModel.INSTANCE.getOffHand();
        var108_68 = false;
        var109_69 = "off-rotate-pitch";
        var110_70 = ViewModel.INSTANCE;
        ViewModel.offRotatePitch$delegate = SettingFactory.booleanSetting(var110_70, var109_69, var108_68, var107_67, var106_66);
        var113_71 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)();
        var114_72 = ViewModel.INSTANCE.getOffHand();
        var115_73 = false;
        var116_74 = "off-rotate-roll";
        var117_75 = ViewModel.INSTANCE;
        ViewModel.offRotateRoll$delegate = SettingFactory.booleanSetting(var117_75, var116_74, var115_73, var114_72, var113_71);
        ViewModel.offRotationSpeed$delegate = SettingFactory.floatSetting((SettingContainer)ViewModel.INSTANCE, "off-rotation-speed", 1.0f, RangesKt.rangeTo(1.0f, 5.0f), ViewModel.INSTANCE.getOffHand(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)());
        ViewModel.eatMod$delegate = SettingFactory.settingGroup$default(ViewModel.INSTANCE, "eating", null, null, 6, null);
        var120_76 = null;
        var121_77 = 16;
        var122_78 = null;
        var123_79 = ViewModel.INSTANCE.getEatMod();
        var124_80 = RangesKt.rangeTo(-1.0f, 2.0f);
        ViewModel.eatX$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "eat-x", 1.0f, var124_80, var123_79, var122_78, var121_77, var120_76);
        var127_81 = null;
        var128_82 = 16;
        var129_83 = null;
        var130_84 = ViewModel.INSTANCE.getEatMod();
        var131_85 = RangesKt.rangeTo(-1.0f, 2.0f);
        ViewModel.eatY$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "eat-y", 1.0f, var131_85, var130_84, var129_83, var128_82, var127_81);
        var134_86 = null;
        var135_87 = 16;
        var136_88 = null;
        var137_89 = ViewModel.INSTANCE.getEatMod();
        var138_90 = RangesKt.rangeTo(0.0f, 0.2f);
        ViewModel.eatShake$delegate = SettingFactory.floatSetting$default(ViewModel.INSTANCE, "eat-shake", 0.005f, var138_90, var137_89, var136_88, var135_87, var134_86);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F45;
        if (u[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])C.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    C.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k9", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = t[n2].getBytes("ISO-8859-1");
            ViewModel.u[n2] = ViewModel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return u[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ViewModel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F92;
        if (ab[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = I[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])fb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    fb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ViewModel.ab[n2] = n3;
        }
        return ab[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ViewModel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ViewModel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ViewModel.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

