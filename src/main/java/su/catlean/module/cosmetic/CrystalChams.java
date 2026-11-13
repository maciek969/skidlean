/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.gl.WindowFramebuffer;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.CrystalRenderEvent;
import su.catlean.api.event.events.render.CrystalYOffsetEvent;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.SetCrystalScaleEvent;
import su.catlean.api.event.events.world.CrystalCreateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.render.BoxRenderMode;
import su.catlean.render.ChamsColorMode;
import su.catlean.render.ChamsGlowRenderer;
import su.catlean.render.ChamsRenderer;
import su.catlean.render.ModelRenderer;
import su.catlean.render.ShaderFramebuffers;
import su.catlean.render.ShaderMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;

public final class CrystalChams
extends Module {
    @NotNull
    public static final CrystalChams INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup types$delegate;
    @NotNull
    private static final BooleanSetting default$delegate;
    @NotNull
    private static final BooleanSetting box$delegate;
    @NotNull
    private static final BooleanSetting frame$delegate;
    @NotNull
    private static final BooleanSetting shaderVal$delegate;
    @NotNull
    private static final ModeSetting renderMode$delegate;
    @NotNull
    private static final ModeSetting shaderMode$delegate;
    @NotNull
    private static final ColorSetting shaderColor$delegate;
    @NotNull
    private static final ColorSetting fillColor$delegate;
    @NotNull
    private static final ColorSetting lineColor$delegate;
    @NotNull
    private static final BooleanSetting glass$delegate;
    @NotNull
    private static final BooleanSetting borders$delegate;
    @NotNull
    private static final FloatSetting radiusValue$delegate;
    @NotNull
    private static final FloatSetting distanceValue$delegate;
    @NotNull
    private static final FloatSetting glowAlphaValue$delegate;
    @NotNull
    private static final FloatSetting lineWithValue$delegate;
    @NotNull
    private static final FloatSetting alpha$delegate;
    @NotNull
    private static final ColorSetting secondColor$delegate;
    @NotNull
    private static final ModeSetting colorModeValue$delegate;
    @NotNull
    private static final IntSetting glowQualityValue$delegate;
    @NotNull
    private static final FloatSetting glowRadiusValue$delegate;
    @NotNull
    private static final FloatSetting glowAlphaMultValue$delegate;
    @NotNull
    private static final FloatSetting fillAlphaMultValue$delegate;
    @NotNull
    private static final FloatSetting saturation$delegate;
    @NotNull
    private static final FloatSetting brightness$delegate;
    @NotNull
    private static final IntSetting hDistance$delegate;
    @NotNull
    private static final IntSetting vDistance$delegate;
    @NotNull
    private static final BooleanSetting texture$delegate;
    @NotNull
    private static final FloatSetting scale$delegate;
    @NotNull
    private static final BooleanSetting ageScale$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting amplitude$delegate;
    @NotNull
    private static final ShaderFramebuffers framebuffers;
    private static final long b;
    private static final String[] c;
    private static final String[] h;
    private static final Map w;
    private static final long[] x;
    private static final Integer[] J;
    private static final Map N;

    private CrystalChams() {
        super("crystal-chams", Categories.getCosmetic(), null, null);
    }

    private final SettingParent getTypes() {
        return (SettingParent)types$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getDefault() {
        return (Boolean)default$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setDefault(boolean bl) {
        default$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final boolean getBox() {
        return (Boolean)box$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setBox(boolean bl) {
        box$delegate.setValue(this, delegatedProperties[2], bl);
    }

    private final boolean getFrame() {
        return (Boolean)frame$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setFrame(boolean bl) {
        frame$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final boolean getShaderVal() {
        return (Boolean)shaderVal$delegate.getValue(this, delegatedProperties[4]);
    }

    private final void setShaderVal(boolean bl) {
        shaderVal$delegate.setValue(this, delegatedProperties[4], bl);
    }

    private final BoxRenderMode getRenderMode() {
        return (BoxRenderMode)((Object)renderMode$delegate.getValue(this, delegatedProperties[5]));
    }

    private final ShaderMode getShaderMode() {
        return (ShaderMode)((Object)shaderMode$delegate.getValue(this, delegatedProperties[6]));
    }

    private final Color getShaderColor() {
        return (Color)shaderColor$delegate.getValue(this, delegatedProperties[7]);
    }

    private final Color getFillColor() {
        return (Color)fillColor$delegate.getValue(this, delegatedProperties[8]);
    }

    private final Color getLineColor() {
        return (Color)lineColor$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getGlass() {
        return (Boolean)glass$delegate.getValue(this, delegatedProperties[10]);
    }

    private final void setGlass(boolean bl) {
        long l = 0L;
        l = b ^ l;
        glass$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)19526, (long)(0x77CA0A3580AD62F9L ^ l))], bl);
    }

    private final boolean getBorders() {
        return (Boolean)borders$delegate.getValue(this, delegatedProperties[11]);
    }

    private final void setBorders(boolean bl) {
        long l = 0L;
        l = b ^ l;
        borders$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)6503, (long)(0x4316D5F134C6EC62L ^ l))], bl);
    }

    private final float getRadiusValue() {
        return ((Number)radiusValue$delegate.getValue(this, delegatedProperties[12])).floatValue();
    }

    private final void setRadiusValue(float f) {
        long l = 0L;
        l = b ^ l;
        radiusValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)3157, (long)(0x4064AB6643D5F16BL ^ l))], Float.valueOf(f));
    }

    private final float getDistanceValue() {
        return ((Number)distanceValue$delegate.getValue(this, delegatedProperties[13])).floatValue();
    }

    private final void setDistanceValue(float f) {
        long l = 0L;
        l = b ^ l;
        distanceValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)10632, (long)(0x5F79F57ED4ED129FL ^ l))], Float.valueOf(f));
    }

    private final float getGlowAlphaValue() {
        return ((Number)glowAlphaValue$delegate.getValue(this, delegatedProperties[14])).floatValue();
    }

    private final void setGlowAlphaValue(float f) {
        long l = 0L;
        l = b ^ l;
        glowAlphaValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)9635, (long)(0x2B315BB16308A1BL ^ l))], Float.valueOf(f));
    }

    private final float getLineWithValue() {
        return ((Number)lineWithValue$delegate.getValue(this, delegatedProperties[15])).floatValue();
    }

    private final void setLineWithValue(float f) {
        long l = 0L;
        l = b ^ l;
        lineWithValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)11148, (long)(0x6D5D2C82570E536AL ^ l))], Float.valueOf(f));
    }

    private final float getAlpha() {
        return ((Number)alpha$delegate.getValue(this, delegatedProperties[16])).floatValue();
    }

    private final void setAlpha(float f) {
        long l = 0L;
        l = b ^ l;
        alpha$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)10836, (long)(0x68DE73FC4F203B47L ^ l))], Float.valueOf(f));
    }

    private final Color getSecondColor() {
        return (Color)secondColor$delegate.getValue(this, delegatedProperties[17]);
    }

    private final ChamsColorMode getColorModeValue() {
        return (ChamsColorMode)((Object)colorModeValue$delegate.getValue(this, delegatedProperties[18]));
    }

    private final void setColorModeValue(ChamsColorMode chamsColorMode) {
        long l = 0L;
        l = b ^ l;
        colorModeValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)1115, (long)(0x7F79A0A62F769F55L ^ l))], (Object)chamsColorMode);
    }

    private final int getGlowQualityValue() {
        return ((Number)glowQualityValue$delegate.getValue(this, delegatedProperties[19])).intValue();
    }

    private final void setGlowQualityValue(int n) {
        long l = 0L;
        l = b ^ l;
        glowQualityValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)20484, (long)(0x7EA180544BDB9083L ^ l))], n);
    }

    private final float getGlowRadiusValue() {
        return ((Number)glowRadiusValue$delegate.getValue(this, delegatedProperties[20])).floatValue();
    }

    private final void setGlowRadiusValue(float f) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ b;
        glowRadiusValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)30491, (long)(0x7A14E0375B67028L ^ l2))], Float.valueOf(f));
    }

    private final float getGlowAlphaMultValue() {
        return ((Number)glowAlphaMultValue$delegate.getValue(this, delegatedProperties[21])).floatValue();
    }

    private final void setGlowAlphaMultValue(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ b;
        glowAlphaMultValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)10351, (long)(0x2A97F0B5F4C44376L ^ l))], Float.valueOf(f));
    }

    private final float getFillAlphaMultValue() {
        return ((Number)fillAlphaMultValue$delegate.getValue(this, delegatedProperties[22])).floatValue();
    }

    private final void setFillAlphaMultValue(float f) {
        long l = 0L;
        l = b ^ l;
        fillAlphaMultValue$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)21192, (long)(0x490C615E91435A98L ^ l))], Float.valueOf(f));
    }

    private final float getSaturation() {
        return ((Number)saturation$delegate.getValue(this, delegatedProperties[23])).floatValue();
    }

    private final void setSaturation(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ b;
        saturation$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)32577, (long)(0x67555C0A2869C0F8L ^ l))], Float.valueOf(f));
    }

    private final float getBrightness() {
        return ((Number)brightness$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    private final void setBrightness(float f) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ b;
        brightness$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)4626, (long)(0x71B7A649911FC2AL ^ l2))], Float.valueOf(f));
    }

    private final int getHDistance() {
        return ((Number)hDistance$delegate.getValue(this, delegatedProperties[25])).intValue();
    }

    private final void setHDistance(int n) {
        long l = 0L;
        l = b ^ l;
        hDistance$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)9238, (long)(0x70D4451DCB7A9BC1L ^ l))], n);
    }

    private final int getVDistance() {
        return ((Number)vDistance$delegate.getValue(this, delegatedProperties[26])).intValue();
    }

    private final void setVDistance(int n) {
        long l = 0L;
        l = b ^ l;
        vDistance$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)18734, (long)(0x1528B0BCF212CE6AL ^ l))], n);
    }

    public final boolean getTexture() {
        return (Boolean)texture$delegate.getValue(this, delegatedProperties[27]);
    }

    public final void setTexture(boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ b;
        texture$delegate.setValue(this, delegatedProperties[CrystalChams.zkmBootstrap2("o", (int)6686, (long)(0x61E16A9A30878F02L ^ l))], bl);
    }

    private final float getScale() {
        return ((Number)scale$delegate.getValue(this, delegatedProperties[28])).floatValue();
    }

    private final boolean getAgeScale() {
        return (Boolean)ageScale$delegate.getValue(this, delegatedProperties[29]);
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[30])).floatValue();
    }

    private final float getAmplitude() {
        return ((Number)amplitude$delegate.getValue(this, delegatedProperties[31])).floatValue();
    }

    @NotNull
    public final ShaderFramebuffers getFramebuffers() {
        return framebuffers;
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        block2: {
            long l;
            long l2 = l = b ^ 0x1A63404ADB1AL;
            long l3 = l2 ^ 0x2442FD612839L;
            long l4 = l2 ^ 0x3260E9AC0F1CL;
            int n = (int)(l4 >>> 32);
            int n2 = (int)(l4 << 32 >>> 56);
            int n3 = (int)(l4 << 40 >>> 40);
            Intrinsics.checkNotNullParameter(render3DEvent, "e");
            if (this.getShaderVal()) {
                framebuffers.render(render3DEvent.getStack(), true, l3, this.getVDistance(), this.getHDistance());
            }
            if (this.getBox()) {
                ChamsRenderer.INSTANCE.g(this.getRenderMode(), this.getFillColor(), this.getLineColor(), this.getVDistance(), this.getHDistance());
            }
            if (!this.getDefault()) break block2;
            ModelRenderer.INSTANCE.w(n, render3DEvent.getStack(), (byte)n2, this.getRenderMode(), n3, this.getFillColor(), this.getLineColor(), this.getVDistance(), this.getHDistance());
        }
    }

    @Flow
    public final void onRender2D(@NotNull Render2DEvent render2DEvent) {
        block1: {
            long l;
            long l2 = l = b ^ 0x14DBBEFC53A5L;
            long l3 = l2 ^ 0x3D2AE064216EL;
            int n = (int)(l3 >>> 32);
            int n2 = (int)(l3 << 32 >>> 48);
            int n3 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x32468D56493AL;
            long l5 = l4 >>> 8;
            int n4 = (int)(l4 << 56 >>> 56);
            Intrinsics.checkNotNullParameter(render2DEvent, "e");
            if (this.getFrame()) {
                ChamsGlowRenderer.INSTANCE.Y(render2DEvent.getContext(), this.getLineColor(), l5, (byte)n4);
            }
            if (!this.getShaderVal()) break block1;
            framebuffers.applyShader(this.getShaderMode(), this.getShaderColor(), this.getGlass(), this.getBorders(), this.getRadiusValue(), this.getDistanceValue(), n, this.getGlowAlphaValue(), this.getLineWithValue(), this.getColorModeValue().ordinal(), this.getShaderColor(), this.getSecondColor(), this.getGlowQualityValue(), (short)n2, this.getGlowRadiusValue(), this.getGlowAlphaMultValue(), this.getFillAlphaMultValue(), this.getSaturation(), (char)n3, this.getBrightness(), this.getAlpha());
        }
    }

    @Flow
    public final void onCrystalCreate(@NotNull CrystalCreateEvent crystalCreateEvent) {
        Intrinsics.checkNotNullParameter(crystalCreateEvent, "e");
        if (this.getAgeScale()) {
            crystalCreateEvent.cancel();
        }
    }

    @Flow
    public final void onFrameBuffer(@NotNull FrameBufferEvent frameBufferEvent) {
        block0: {
            Intrinsics.checkNotNullParameter(frameBufferEvent, "event");
            if (!this.getShaderVal() || framebuffers.d() == null || !framebuffers.isActive()) break block0;
            WindowFramebuffer windowFramebuffer = framebuffers.d();
            Intrinsics.checkNotNull(windowFramebuffer);
            frameBufferEvent.setFrameBuffer(windowFramebuffer);
            frameBufferEvent.cancel();
        }
    }

    @Flow
    private final void onCrystalRender(CrystalRenderEvent crystalRenderEvent) {
        block0: {
            if (this.getTexture() || framebuffers.isActive()) break block0;
            crystalRenderEvent.cancel();
        }
    }

    @Flow
    private final void onSetCrystalScale(SetCrystalScaleEvent setCrystalScaleEvent) {
        setCrystalScaleEvent.setScale(this.getAnimatedScale(setCrystalScaleEvent.getAge()));
        setCrystalScaleEvent.cancel();
    }

    @Flow
    private final void onCrystalYOffset(CrystalYOffsetEvent crystalYOffsetEvent) {
        crystalYOffsetEvent.setOffset(this.getBounceOffset(crystalYOffsetEvent.getAge()));
        crystalYOffsetEvent.cancel();
    }

    public final float getAnimatedScale(float f) {
        return !this.getAgeScale() ? this.getScale() : this.getScale() * (float)((Number)Easing.OUT_EXPO.a().invoke(RangesKt.coerceIn((double)f / 10.0, 0.0, 1.0))).doubleValue();
    }

    private final float getBounceOffset(float f) {
        float f2 = (float)Math.sin(f * this.getSpeed()) / 2.0f + 0.5f;
        f2 = (f2 * f2 + f2) * this.getAmplitude();
        return f2 - 1.4f;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getBox() || INSTANCE.getDefault();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getShaderMode() == ShaderMode.BLOOM && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getShaderMode() == ShaderMode.BLOOM && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getShaderMode() == ShaderMode.BLOOM && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getShaderMode() == ShaderMode.DEFAULT || INSTANCE.getShaderMode() == ShaderMode.BLOOM;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR;
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible19() {
        return !(INSTANCE.getScale() == 1.0f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        CrystalChams.b = ZkmKeys.a(-8315111676459850183L, 2018271217288651700L, MethodHandles.lookup().lookupClass()).a(52944968709661L);
                        var20 = CrystalChams.b ^ 35394933183818L;
                        CrystalChams.w = new HashMap<K, V>(13);
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
                        var15_5 = "h\u00e5/\u00c4\u00d8\"A=vMei\u00a0\u00d2\r\f\u00ae\u0097\u00f5^\u0012\u00cf\u00b9\n\u009e\u0001[\u00c4*k\u00bc\u0089\u00f7l\u00ba\u00c3\u00a2\u001aZE Z\u0080\u00ee\\\u00e3\u008c\u00e6\u0095j#Pf\u00d2\u001a\u00f1\u00ad\u00f33U\u00ed\u00cb\u0096\u00a7UP\u00f1\u00f0\u00fc\u00f8K\u00e3G\u0010\u00f7&,d\u009a\u00e4D\"\u00abA{\u00d31\u001d})\u0010\u00b9\u00b1\u0003|\u00a3\u00ef\u001d9\u0080\"t+\u00c6\u00ebs\u009d(\"z\u00cc\u00d5\u00f3-\u00cd\u00ebJ\u00b8\u00fe\u0082Yk\u00e9=\u00b7\u00c6<\u00eb\u00ed\u00d7\u00cdP\u00ebeW\u00a6\u00e2\u0019\u009e\u0087\u00dc\u0094t\u00b8u%\u00b1i\u0018Y\u00fb\u0012\u0082\u0096\u0087\u0083\u00c4\u00e1\u0007\u008f\u009e\u00fd\u00e4M\u00bd\u00ab\u0083.\u00ee\u00aa\u007f\u00e4\u00cf(w\u0082\u00b8.\u0017L\u009f\u00f5j\u00b8\u00ab-\u009d\u00a6\u00bc  90\u00c1\u00ca\u0011\u0013AC\u00fc}\u0080\u00ff\u00f3\u00ee\u00a6\u001f\u00ac\u00a7t\u00bbx\"\u0019@}\u00a7\u00b2\u00bcH\u00fa\u00ba;t\u008c\u00c8M\u00e4\u0080QE]\u00a4@\u00c4\u00d4\u0099&\u00cc\u008b\u00d4\u00d6X\u00be \u00a95\u009a\u0004\u0099;\u00a3:V\u00caX\u0093G\u0011\u00128KBA\u00c6\u00a2\u0017p\u0086\u008fR\u000b;\u0004g\u0096_\u00c6t(\u00d6h\u00c8\u0091F\u00ae\u00e3H\u00c2\u009c\u00d2\u009d\u009e \u00e4oD\b8\u00e3\u000eq\u0012;\u00fa\t\u001f3 k\u00d8f\u00caV\"\u00e5|\u000f\u0091Z\u0010+F\u00ca\u0082AW\u00d1\u00af\u0083\u00ad\f`|1\u00a2\u008e8F\u0018]\u0093\u00cdW\u00ed\u00ab\u009c\u00dd\r\u00c4\u001ex_1\u00ebf\u0017\u00bf.\u00b0\u00b6\u0088J\u00af\u00e5\u00af\u008d\u00d2\u00f5Zo\u00bc\u0011:\u00a0\u00a8\u00bb\u00fbq\u00c0e\u0082n<\u00dfe\u00f2\u000b2\u00ba\u00b4\u0019\u0018t \u00bf\u001f\u00bdR\u0096\u00a4'\u0015=&\u00f2&\u00a2\u000b?\u00e5\u00e3\"\u00e1\u00dc4G:\u00ee\u00df\u0093<\u0097NO\u0013p\u0010\u00ab/\u009d\u00c0\u00cf\u0083\u00e2.a\u000eb\u00cc\u0088=\u00aa] \u0082\u00d0\u00eb\u00a6g\u00b6X\u00e1\u0004\u00e8\u0094J\u007f\u0018)m\u0088\u00fcJ\u0004rbU>\n\u00c8 o\u009bU\u00d4\u0085\u0018\u00e8\u008f1\u00b9.\u0082`\u0015}\u00a0\u00d9L\u0081\u00dc\u00f4\u00e4\u0081\u0016\u00e3\u00be\u000e\u00f5\u0088\u000b(\u008b/\u00ba[RI\u00fb\u0001\u00cb\u00e8\u008b\u0091L3\u0087(\u00cb'\u0016\u00fez\u00d5\u00c9Y\u00c6)\u00df,V\u00da\u0019\u00f7Qd\u0092\u00e1\u00ed\u008a\u00b7\u00d1 W\u00ccL\u00cc\u0090\u00b4\u00ea\u00d2\u00c8\u00c4\u00e5/,\u00e3\u009a\u00d9\u008d5\u001d\u0095^\u00ea\u00a9\t=\u00cd\u0018\u00ed#\u00ee\u00c5\u0018(\u00b2%\u001c\b\u00a8.\u00deE\u0019\u0002\u0016,\u0006\u00d6\u00ad\\\u00c4c\u0090{9\u0004@Q\u00fb\u00a9\u009eu*\u00efF]\u00d0\u00b5\u0010>k\u0093\u007fl\u0018\u00eft\u008c\u00f5\u001d\u00c3\u00a0\u00c0S,j\u0019\u00bd\u0080DA\u00f4\u009d\u008d9\f\u00a4\u00c0_ \u00b6\u001d\u00af@\u00a1\u00a8\u00c4!\u00fe\u00b2\u00e8\u00b5+\u00aex\u00fc\u00974K=r|\u0002\u0001\u00d3z\u00ac\u00d1N{r\u00ba\u0010\u00ad\u007f) R\u00a2\u00bd\u00bfW\u0001\u00b8\u00a3\u00a7\u00075\u008c\u0018?\n\u00e4\u001d\u00e6f\u009b\u00e3\u0010\u009a\u00f4\u00fd\u00b7\u00fc\u0082X\fC\u00c2$6\u009f\u0092\u00a1(\u001d\u001c\u00c5\u0093\u00c2\u00ff\u0097E\u00a0\u001a1w\u00ed&\u00fdyg|i\u0092H6\u00cfD\t\u000fX\u0097\u0093\u00eeQ+(\u0011Ys\u000bm\u00f6\u00d0\u0018&j'\u0016>\u0014?\u0099\u00e5\u008a\u001e\u00b2\u00f7q \u009c$\u00b6\u001c\u00a8\u00af \u0090\u00a6 x\u00d7\u0017\u00e0zU\u00faD\u00d3\u0088\u00f2\u00e8\u0097\u008c#\u00d7]\u0095\tn^\u000b\u00ff\u00fe:g\u00d4G9o\u00de\b\u0010L:]\u009cI\u00dbk\u00abT|\u001ds\u00fd\u0016\u00d3\u001a\u0018\u0014x\u00ef\u00c2[\u00ad\u008f\u00cc\u0081\u009e\u008a\u00a1\u0096\u0093:\u00d1\u007fX\u000f\u001aQ\u00c1q\u00a4(\u0016\u00fe\u00be\u00ee\u00df\rt\u0007\u00066\u00bc!\u001c[\u00b7\u00c5e\u0082\u0087\u00fd\u001eM4\u000b\u0091M\u00f6F\u00bd\u00aa\u00e1\u00aa\u008a\u00ca\u0082\u0015\u00b1\u001bE\u00c4\u0018\u00c8\u00dbO\u00b9\u00dfKd\u00cej\u00c1\u00b9L0\u0015\u00c6z3a\u000eRA\u00b3\u008a\u00f80\u00b7\u001d\u00e9]UA\u00e0\u00ea\u0006\u008eR#2*_\"\u009fe^\u0080\u008c\u000eXn\u0088r8\u00cb\u001c\u00e1<\u0003U\u001f\u00cbp\u00a5\u0086\u00af\u0019\u00eb\u00b7+\u0085\u00df\u00f9w\u00bc ;\r^^\u00d7\u00c3frT\u0002\u00c6\u00ea\u0015\u00a3\u0085\u00ce\u00de\u00e2\u00b1J\u00bd\u001f\u00a0\t\u00e0\u00da\u00b5\u00f1b\u0007\u00965 \u00d5\u0011\u00fa\\S\u00dd\u00a0~r\u009bg\u0097\u00b4B\u00cb\u00e0\u00c8\u00e8\u00cc\u00a7/\u000f\u0080|\u00a2~o#G\u0010[\u00f80\u00de4>\u001b\u00030\u00cdE\u009a\u001fL\u00ee\u009a4H\u00cd\u00ec<\u00db\u00ac\u00f6\u00c0f\u00d7aTO\u00dd\u00bd\u00db\u00986i\u00fc\u009b!\u0083\u009c\u00c0\u009b_\u00c2TH\u00df\u0019.\u00c0 \u00c7a\u00e1*\u00e5\u00b0\u009f\u00da\u00dd2\u00ca\u00d0\u001a]gmfY4m\u00bb:\u00ce@\u000eZ\u00f5\u00a5\u009b\u00b9]\u00b3\u0010\u008d\u00f0\u0005\u00a9\u00a2\u00ad\u00d2%\u00cf\u00aa\u00da\u00d5\u00bd]%y\u0018\u001bly\u00d9\u00c4\u007f\u00ffB\u00af\u00d2\u0015 \u009a\u00de\u00b9\n2&\u00c8\n\u0012\u0001\u00fd2 C\u00d1\u008c.\u0007\u0010R\u001e<7\u001a\u00c5\u00b3\u0088\u008e\u00f2\u001di\u00d7\u00e9\u00b5\u0087d\u00d3\u00a0q\u0097\u0013\u00ac\u0016&d \u00e6#\u001e\u00d9M\u00f4\u00e5\u00d0\u00db\u00e2\u008ca\u00be\u0012:\u00feX\u001a\u00f9\u00bd\u008dO\u0006\u00eaM\u001b(\u00f2=\u00e2)\u00df\u0010X\u0014u\u0098xx\u00ca4\u00a3\u0089\u00cb\t`gTW \u00a7o\u00e9\u00c0r\u00a8k\u009f\u00f3\u00ef\n\u0095\u0080\u0080N\b\u0000\u00a7\u0014=\u009f\u0006!3.m\u0018\u001b\u0012w}\u008d \u008e\u0087\u00b43\u000b\u0012\u0089\u00cel\u00c4\u00e4\u009c\u00eb\u0096(\u0003$\u0002\u00efL\u009b~\u008bTG\u0091\u0018\u0086\u0007\u0083\u0099\u00d48z\u0003\r\u00fb\u0097/\u00eaSm\u0080\u009d\u00ec*\u00a8n\u00ef\u0014g\u00bd\u00a4L>\u00ef\u0090a\u00cb\u00ff\u008d\u00c4\u00f9&\u0004C\u009c>\u00d0&!\u00c3-\u00bf\u00d7\u0000\u00df\u0018C\u001b\u0080 \u0093\u009d\u00bf\u00f1\u0080\u00d1\u00ad(\u0086\u0000\u00dc\u0094\u008f\u00cd\u0089\u0089\u0014!%\u00bc0%\u008a\u00b7[\u00ac\tv^\u00d5\u0094\u00be2\u0014\u00e9\u0082\u0096/\u00db\u0097>H\u00be\u00ab\u00cd\u00e0\u00ab\u00db0?\u008b\u00a9\u001c@\u0003Q\u0099s\u0087\u0088\u00ae=\u00fa7Qo\u00a6n\u00d0Z\u008c\u00f5\\\u000583\u0001\u00a6Y\u0005\u00ad6`\u00c4O\u00d2\u0089\u0016\u00ec\u009e\u0006\u00af\u0096\u008e\u0083\u00e9\u0014 <x \u0080\u00e3\u00aa\u00bb+\u00f0\u00ddy\u00bd\u00ef\u0002\u00e6K\u00fc5\u00c7\u001a\u00d7\u00fd\u00b2\u00cd&\u00d8\\\u00fd\u0094\u00b7\u009c4\u00180\u00a2\u00fa\u0015\u0013\u008c(\u00ab\u00de\u0002m\u00e3u\u00f8K^q\u000b\u001ey\u00b9\u0019\u00dbx\u0018e\u00a8\u00e9\u00aaXU\u0081B0\u00e7\u00b6\u00e8\u00db\u00a9\u00f2\u00dePw\u008b\u00a1\u00cf\u009d.+\u0010\u0087r\u008b\u00f4\u0002]\u008d9(\u00c6\u000f)*\u00a3y\u009c(\u00b3\u00ea\u00ab\u0004\u00a1\u0093\u00cb\u00e5\u00fa \u0088=Z\u009d\u0015t\u00e1\u00f8\u00e6\u00f2\u00da\u0099\u00b7\b\u0082\u00c1r\t\u00ca9\u00af<f\u00a4\u00ba\u00c9z*\u00de\u0007\u0010\u0005\u00b0\u0083\u00dd\tt'\u008d%=\\\u00da\u00804\u0084\u00bf\u0010\u0089\u00e3j\f=g\u0015\u00ff&\u0091\u00f4\u00f3d\u000fz\r\u0010v+\u00f1\u00c2\u00e8\u00deF\u0083\u001a\u00fe\u0086K\u0016\u009c\u00ca\u0091\u0010U\u00af\u00b9\u008b\u00a0ezqJ\u00cd\u0014\u008aP\u0012O\u00c987\u0096HM=\u00db\u00ab\u00c3\u008c%\u00c5-Q]\u00b5\u0096m\u0093\u00be_\u00a8\u0088K\fE\u0085:\u00ec\u0085\u009b\u00f6\u0083\u00f8tTl\u0099\u00a5WX\u00f0g\u0087\u00ad\u00ee7\u00f3\u00e7tC\u001d0\u0088<\u00c8!(\u00fa\u008a&\u00a1\u0007\u0088\u00ecQ\u00d2f3|\u00fc\u0095\u007f\u0090\u0010\u00e8\u00bb\u00bex\u00d1\u0013\u0099\u00aej{I\u0090\u00dc&8\u009892vZ\u00e1\u00c9\u008a(q?\u00c9\u00cb\u00bb\u0088\u00e7\u0000\u0017\u0002\u0004\u00e6\u00b8\u007f\u0081\b\u00f1\u0017rHd\u0001\\\u001fs@\u00c2\u0011\u00cf\u00e4I\u001f\u0088\u000e\u0083\u00b6z\f}9 \u00f5^\u00f40\tM\u0013G0\u00bf\u00c6oY\u00b2\u00aeR\u00a2;8}\u00ce\u00ce\u000eQ\u00de\u00b5\u00d1S\u00b5\u0018\u00a5\u008d \u00b5>\u00bd\u00e3\u00b7\br\u009f8\u00afM\u00b7\u0000\u00a6\u00d7]ir9BM\u0015\u00bc\u000e\u009e\u00b7\u0080\u00be\u000e\u00fe\u00cb-\u0010\u00dc\u00fa \u0081\u0089\u001c4\u0093]\u00aa\u001f\u0019T\u00cfdc q\u0092\u0088d\u00be\u0001\u00f0\u00e6\u00e2\u00ba\u00e7:\r\b\u0015\u00a3\u001f\u00909|\u00f3\u0007V}\u00c6}f\\p\u00d4\u00ea\u00c3\u0018\u00ae):\u0093D\u00c41\u00036\u00b1M5\bI\\\u00eb\u00b5\u00b6\u00ae\u00e7\u00a6\u00d3\u00a0o\u0010\u00d8\u00a9>\u00b4\u00fe\u00d3\u00cb\u00a5\u00e34\u00b8\u00e4u\u00a4Wa8\u0089/\u0013\u00af\u0098\u0006\u00dc\u0087&\u00ec\u00bc\u0013,\u001aQI\u00bf\u00d3e\u0004\u00a5\u00a7Gu\u00ce\u007f\u00cb\u00b5\u00a8!\u008a5\u0088\u00ca\u00806\u008e\u00f2^5f&j\u00adlU\u00a1\u00e7PA\u0085J\u0084\u00db\u00f4\u00bb\u0018@\u00ff\u00ef\u00ce\u00cc\u009ad(\u00cb\u0084\u009b)@\u00aa \u00049\u00dc\u0092zD\u00dd\u00f3\u00a3\u0018\u0098$\u0003<\u00e8\u00ff\u00f5|\u00fcF\u00f0`\u00eam\u00d6\u00a5\u00adz\u00ad\u00df;Z\u00f8\u0011\u0010\u00b1'\u0002\u0082I\u00c7,\t\u00f0\u0002V\u00ed\u0012\u001f_\u008a\u0010\u00ff\u00df\u00a1\u00c4:U\u00f2\u00ce\u0014tc\u00f3\u001f)\u00bc\u00c1\u0010t\u00e7\u00d6\t\u00ec\u001fY_\u00d1\u001f\u00c7\u0011~\u0090\u0084\u00e0 \u00cb?\u00b2&\u00bfF\u00bd\u00c6\u0093G\u007f\u0006\u0002\u001cw\u0001\u000b-\u00b8\u00f4oU\u0096]\u0001\u0015n\u00af\u00cd\u000bs\u0004 \u0090\u00bdOa\u00f3\u00e4>\u00d1\"\u00ae\u00e0\"\u0012\u00e9K\u00bd\u000fH\u00c6\u0016\u009a\f\u00f8\u00ff\u00b4\u00c0b\u00f4\t$]\u0092\u0010\u00a0\u0084\u008e\u00abm\nPJm\u00c5\u00e8\b3\u00ef\u0014\u00838^\u00ee\u00a0jx\u00f5\u00f3.\u00b7v\u0098\u00e6~U\u001b\u0083\u00eb\u0002\u00a0\u00e4 \u00d1\u00fa\u00f5\u00bb\u0000?\u00d7$&I\u0015\u008e\u00a6\u00a9S\u0019C\u00d9\u00be\u001b\u0087\u0003\u00b5*\u00ab\u0090}h<\u00e0I\u0006\u00e2\u00e1\u001c\u0018\u0011\u00ef>2\u00ff8$-Y\u00f9\u00e9\u0017\u00ba\f\u00c1d\u00c2V\u00e5z\u00fe\u009a\u0091f@\u00e1\\\u00dc\u00b3r\u00aa/\u00d0\u00b3\u0080f\u00e2\u00bd@\u00ee\u00fb\u00ba:\u00ff\u0091@\u0000*\"\u00ef\u00d4\u0016'\u00a9\u00e4\u00181(\u00c1\r\u00f1qA\u00da\u00c6\u00d0>\u0012\u00b8']%\u00ba=%\u00fc\u00e0@\u00ec\u00ff{\u00e7L\u00b7:\u008c\u0018\u00d8X\u0010\u0015F\u0083\u00d9\u0011v\u00b3K\u0087\u00b2Z\u00a3c\u00d1\u0000`\u0010\u00e4\u0010\u00a5\u00ea\u001f.\b\u00a2\u00f1\u00c5L\u0083\u00b1\u009f\u00a6\u00ca \u009b\u001f\u00b9\u00cc\u00b8\u00a3'\r;\u009f\u00a5\u00e8\u00b6N\u00c93\u00ce\u001e\u00d7\u00d6\u0003gXt\u00c3\u00b0D\u0019e E\u0010 b@\u00ea\u00cc\u00b6\u00bc\u00bd\u0089\b\u001c\u0006\u00f3\u0019\u0091\u00bc=R*2J\u0086\u00ee\u00f8\u00d1\u00d2\u00ec\u00c1EF\u0010\u00fd\u007f l\u00fb<\u00db\u001e\u0097!\u00f9y5\u00f8\u001c\u001c\u00b2\"\u00f9-\u00c7\u00c9(\u00df\u00f7z\u00ca\u00a2\u008b\"F\u0017\u00b9\u00e8\u00af \u00e0ij\u00b7\u00de?\u00a7{\t\u001a\u00c1yF\u00a1j\u00bd\u00d6\u00a3\u0088\u00b9\u00b9q8tq\u00bf2A3\u00ed\u009b\u0003\u0018h\u000f\u00b4\u00dbA\u00d0\u00cePM\u00006\u00d5b.\u0005\u001e\u009f\u00e0&\u00c6\\\u00b3o9(\u00be1\u0092\u008ee\u0084\u00e5vX\u0086\u00cf\u00ad\u009b\u00cfW\u00c3\u00fe\u00e9`e\u0083\u0089\u0005\u00b0\u00d1c\u0004w\u00af\u0082\u001aLv!#\u00be\u0004\u00ac\u00cd- E\u00bb\u0086>\u00de\u00bd#\u0087\u00f6\u008dk[\u00fb\"\u009a\u00b5\u0088\u00ce\u000f\u00e6l\u0083\u00a6\u00b3\u001f\u009e\u00d0\u00f8\bXe\u0082\u0010<\u00d2\u0010\u0004\u0082j{\u00f0\u00d7\u00b7\u0094\u00de\u00cco$\u00b0 \b\u00f0z\r\u0002f\u00e5\u0096h\u00c1WY\u0018v r\u0089\u00ad)\u00dc\u00e5x\u00da?R\u0004M\u0094\u00d5\u0016O\u009c\u0010\u00e0\u00c5:oL\u0019?\u00ac\u00f9\u00fb\u00ccYh\u00fel\u00c0 \u00c5\u00bd\u001e\u00c4\u00c6 \u0010kj\u00df\u00b4\feB\u00d2\u00e2e \u0019^F^\u00d2\b\u009a\u00fc\u00d4\u00fa\u0004or\u00e2\u0010\u00ba?\u0003\u00fc\u00ef#+\u00a5\u008c][\u00e8`\u00c1\u00066\u0018\u00b1\u00ed\u00f3\\x\\\u00c8\u00ebaXx\u0004\u008c@`r\u00dd3\u0090\u009c.\u00f96< f\u001e;\u00c3\u00dd\u0098\u00f4\u00e3\u008d\u009c\u0085\u00a6P\u0096\u00baf\u00bf_\u0087F\u009d\u00daW\u0013\u0086_ Q\u00b3\u00ee\u00f7\u00aa\u0018\u00bb\\Q;4\u00e2\u00a3\u001b\u00c2O\u0082\u00f7\u00c4\u0093\u0004&\u0086\u00c0g\u00ad\u00f5\u00d9=u@<\u00dd{\u00d2>\u0093\u00c0\u009ew\u00fd\u00ee\u0000\u00b4\u00f5p'\u00d1\u00fe\u00cb\u00bf\u00b4\u0085\u009d\u00a5~L\u00bc\u00a3f\u00db\u00fa\u008d\u0080\u00aa\u00b0\u009a\u0088]%\u00c8'/!4]\u0002$i8\u00d3g\u009d\u007fi\u008d\u00f1\u00cd\r]\u00feF\u009f\u00f0\u00f3(\u00d0\u00e2\u00eb,\u00ad\u0090J\u00e2\u00b3`\u0093\u008c\u00ef\u00b5\u00c6\u00b3,\u00b8\f-\u0099\u00bf\u00b3-7\u00d0\u009b\u00ea\u0005[\u008e\u00a4\n\u00a0\u00961\u008b\u001a>\u0017 \t\u00c4\u0087^\u009b\u00fc\u00e3\u00f6^;\u0016dT\u00b7\u009c\u007f\u00f2\u00070\u00cb\u00f1j\u00d5\u0083\u00935\u0002d~\u00eb\u0098l\u0018\u0002\u001e\u0097RLH7\u007f$\\cm\u00bf\u0089\u0013,=\u00b3\u00ce\u0095\u00b2y\u00c8\u0096\u0010\u0007\u00ce\u00dfQ\u00f7\u00b0\u00cf\u00b1X\u0003q\u00e8\u00a2\u00a4\u000fl";
                        var17_6 = 2999;
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
                            var18_3[var16_4++] = CrystalChams.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = ">\u0003\u00c3\u00f3-]\u00bb\u008f+\u00f8\u0017&#\u00fe=\u00a3\u00a7X\u00d9\u00c4\u00a0z]p\u0018u\u008b@\u00e0W~\u00f5\u00e3\u00bb\u007f\u001c\u00ef\u00ac\u00aaf\u0010w$\u00d99\u001d\u00d8\u001e\u0013";
                            var17_6 = 49;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = CrystalChams.zkmDecodeUtf8(var19_9).intern();
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
                CrystalChams.c = var18_3;
                CrystalChams.h = new String[98];
                CrystalChams.N = new HashMap<K, V>(13);
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
                var6_12 = new long[60];
                var3_13 = 0;
                var4_14 = "b7\t0\u00c4\u0010\u00a0e\u00f0\u00e9Z\u0015\"v\u0017\u0084M\u0080\u00f3\u00dd\u0095\u007fa\u0081\u00e2\u00ec\u00fd=\u00ffBX\u00e13\u000426N\u0005\u00008\u00cd\u00dfNO\u00a9!\u00f0\u001cc\u00b3y\u000f\u00f0\u00a0\u00b1\u0083+\u00e3\u00a2\u00ae\u00889)\u00ea\u00eb\u00e8Ta56J\u008e\u009f_\u00a2\r\u00a9\u00d7\u00fd\u00ec~n0\u00e3d$\u00a6\u00b6\u00d3\u00ab\u0005I\u00e7\u00d0\u00d6\u00be!\u0097&W`\u0014D3\u001c\u00a6-%%zC\u00ab\u00f8<\u001bc\u00b3\u008dJ\u000f\u00fa4\u00b1@\u00e2#\u00cb\u00a5\u00f1O}\u0019\u00b0\u00cd\u00c0\f\u00868\u00f4snX\u00a0)\u00cf\u00f9n\u0010ES\u0089c\u00d2Ah9\u0001TL\u00d2\u0086\u00be\u0003\u00ccPj\u00ad\u00a1\u00ee\u00bef\u00c5\u00ba\u0089\u001d\"k\u0096+\u00c3\u00edw\u0084\u00c8\u00e6\u001d\u00a2U-R\u001c+} \u008fE\u00d2\u00eb\u00b0\u00c4\u001d\bD\u00c9co]\u00e7\u009b\u001b\u0011f\u00cd9\u0005\u00a0\u00bf\u00a9\u0011\u00f6;\u000f\u00e9\u00c8\u0001\u00a7e\u0013f\u00d2Uy\u001c\u008b_\u0081\u00bd\u00b8\u00af'KO\u00f4\u00f8z\u0094x\u00f7[\u00db\u0086\u00ff\u009c\u000eS+\u0093~o\u0081\u001fD\u0003\r\u00b4b\u00bbPE\u00e7\u00c95r\u00ef\u00ebv\u001aY#\u0091\u00ee\u0091c$b\u00c5h\u00e6u\u0086\u00c2\u0005\u00b3\u00b6#\n\u00c7\u000e\u00fd\u00a1\u00d9|.\u001c\n\u008f\u00f6\u00ad]\u00da\u000b\u0098 \u000e\u00dd#\u00dcP\rk\u009bjHa\u00ec\u00aa\u00f4\u00d2\u00cd\u0014\t\u00bb\u00d6tc`\u0086\u0081\u00ffN\u00efn\u00ad\u00c6\u00d1\u001d]yt1\u00ed\u00d7\u00ca/\u00c5\u0099\u000b\u0007\u00b3\u00ce`\u00f2\u00b7\u0007(\u0090^\u0094P\u00e4\u00fe\u00f1\u001e\u009d\u00e1\u0007G\u00c4F\u00dd~\\_\u009a\u008f\u0004\u00c1\u0081JG\u00b8\u00c1\u007ff\\\u0018\u0087\u00ba=\u00cfM\u00bfg\u009d\u0002\u00aeR\u00ec%\u00b8\u00f0[\u00f0\u00aa\b^\u0083\u00e5\u00c0<7?/\u0006\u0092\u00af\u0097\u00d6\u008b\u008d~\u00b2\u00ab\u0095J<!e\u0018\u00d9\u00ed\u0089\u00079\u00b6{\u00da\u00e7\u00f7'\u00bf\n^\u0087\u00b1L\u001a\u0090\u00d8\u009a\u00fa\u00f8u\u00ed";
                var5_15 = 464;
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
                    var4_14 = "?\u00e4\u00ab\u0014\u00e4\f\u00e9\u00d4M\u00ea\u00a88\u0086\u00ef\u0012\u00f7";
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
        CrystalChams.x = var6_12;
        CrystalChams.J = new Integer[60];
        var41_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "types", "getTypes()Lsu/catlean/h;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "default", "getDefault()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "box", "getBox()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "frame", "getFrame()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "shaderVal", "getShaderVal()Z", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "renderMode", "getRenderMode()Lsu/catlean/s8;", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "shaderMode", "getShaderMode()Lsu/catlean/rw;", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "shaderColor", "getShaderColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "fillColor", "getFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "lineColor", "getLineColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "glass", "getGlass()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "borders", "getBorders()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "radiusValue", "getRadiusValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "distanceValue", "getDistanceValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "glowAlphaValue", "getGlowAlphaValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "lineWithValue", "getLineWithValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "alpha", "getAlpha()F", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "secondColor", "getSecondColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "colorModeValue", "getColorModeValue()Lsu/catlean/sa;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "glowQualityValue", "getGlowQualityValue()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "glowRadiusValue", "getGlowRadiusValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "glowAlphaMultValue", "getGlowAlphaMultValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "fillAlphaMultValue", "getFillAlphaMultValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "saturation", "getSaturation()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "brightness", "getBrightness()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "hDistance", "getHDistance()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "vDistance", "getVDistance()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CrystalChams.class, "texture", "getTexture()Z", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "scale", "getScale()F", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "ageScale", "getAgeScale()Z", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(CrystalChams.class, "amplitude", "getAmplitude()F", 0))};
        CrystalChams.delegatedProperties = var41_20;
        CrystalChams.INSTANCE = new CrystalChams();
        CrystalChams.types$delegate = SettingFactory.settingGroup$default(CrystalChams.INSTANCE, "types", AuraTargetsMode.BOOLS, null, 4, null);
        CrystalChams.default$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "default", false, CrystalChams.INSTANCE.getTypes(), null, 8, null);
        CrystalChams.box$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "box", false, CrystalChams.INSTANCE.getTypes(), null, 8, null);
        CrystalChams.frame$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "frame", false, CrystalChams.INSTANCE.getTypes(), null, 8, null);
        CrystalChams.shaderVal$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "shader", false, CrystalChams.INSTANCE.getTypes(), null, 8, null);
        CrystalChams.renderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)CrystalChams.INSTANCE, "render-mode", (Enum)BoxRenderMode.BOTH, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.shaderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)CrystalChams.INSTANCE, "shader-mode", (Enum)ShaderMode.DEFAULT, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.shaderColor$delegate = SettingFactory.colorSetting$default(CrystalChams.INSTANCE, "shader-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.fillColor$delegate = SettingFactory.colorSetting$default(CrystalChams.INSTANCE, "fill", new Color(255, 255, 255, 120), null, null, 12, null);
        CrystalChams.lineColor$delegate = SettingFactory.colorSetting$default(CrystalChams.INSTANCE, "line", new Color(255, 255, 255, 255), null, null, 12, null);
        CrystalChams.glass$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "glass", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.borders$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "border", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.radiusValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "radius", 6.0f, RangesKt.rangeTo(0.0f, 15.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.distanceValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "distance", 1.3f, RangesKt.rangeTo(0.0f, 15.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.glowAlphaValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "glow-alpha", 1.7f, RangesKt.rangeTo(1.0f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.lineWithValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "line-width", 4.0f, RangesKt.rangeTo(1.0f, 4.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.alpha$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "alpha", 0.2f, RangesKt.rangeTo(0.0f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.secondColor$delegate = SettingFactory.colorSetting$default(CrystalChams.INSTANCE, "second-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.colorModeValue$delegate = SettingFactory.modeSetting$default((SettingContainer)CrystalChams.INSTANCE, "color-mode", (Enum)ChamsColorMode.RGB, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.glowQualityValue$delegate = SettingFactory.intSetting$default(CrystalChams.INSTANCE, "glow-quality", 2, new IntRange(1, 4), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.glowRadiusValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "glow-radius", 4.0f, RangesKt.rangeTo(1.0f, 16.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.glowAlphaMultValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "glow-alpha-multiplier", 2.0f, RangesKt.rangeTo(0.0f, 4.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.fillAlphaMultValue$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "fill-alpha-multiplier", 0.6f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.saturation$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "saturation", 0.7f, RangesKt.rangeTo(0.0f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.brightness$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "brightness", 2.0f, RangesKt.rangeTo(0.0f, 45.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 8, null);
        CrystalChams.hDistance$delegate = SettingFactory.intSetting$default(CrystalChams.INSTANCE, "max-horizontal-distance", 64, new IntRange(1, 256), null, null, 24, null);
        CrystalChams.vDistance$delegate = SettingFactory.intSetting$default(CrystalChams.INSTANCE, "max-vertical-distance", 32, new IntRange(1, 256), null, null, 24, null);
        CrystalChams.texture$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "texture", true, null, null, 12, null);
        CrystalChams.scale$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "scale", 1.0f, RangesKt.rangeTo(0.1f, 2.0f), null, null, 24, null);
        CrystalChams.ageScale$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalChams.INSTANCE, "age-scale", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)(), 4, null);
        CrystalChams.speed$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "speed", 0.2f, RangesKt.rangeTo(0.0f, 1.0f), null, null, 24, null);
        CrystalChams.amplitude$delegate = SettingFactory.floatSetting$default(CrystalChams.INSTANCE, "amplitude", 0.4f, RangesKt.rangeTo(0.0f, 1.0f), null, null, 24, null);
        CrystalChams.framebuffers = new ShaderFramebuffers();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3AA3;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])w.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    w.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k8", exception);
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
            CrystalChams.h[n2] = CrystalChams.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalChams.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x47CE;
        if (J[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = x[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])N.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    N.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CrystalChams.J[n2] = n3;
        }
        return J[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CrystalChams.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalChams.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CrystalChams.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

