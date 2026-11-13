/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.EntityAlphaEvent;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.RenderArmorEvent;
import su.catlean.api.event.events.render.RenderNameTagEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.esp.PlayerEspRenderer;
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
import su.catlean.zkm.ZkmKeys;

public final class PlayerEsp
extends Module {
    @NotNull
    public static final PlayerEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup types$delegate;
    @NotNull
    private static final ModeSetting renderMode$delegate;
    @NotNull
    private static final ModeSetting shaderMode$delegate;
    @NotNull
    private static final ModeSetting colorModeValue$delegate;
    @NotNull
    private static final BooleanSetting default$delegate;
    @NotNull
    private static final BooleanSetting box$delegate;
    @NotNull
    private static final BooleanSetting frame$delegate;
    @NotNull
    private static final BooleanSetting shaderVal$delegate;
    @NotNull
    private static final BooleanSetting skeleton$delegate;
    @NotNull
    private static final BooleanSetting extraPart$delegate;
    @NotNull
    private static final ColorSetting shaderColor$delegate;
    @NotNull
    private static final ColorSetting skeletonColor$delegate;
    @NotNull
    private static final ColorSetting skeletonFriendColor$delegate;
    @NotNull
    private static final ColorSetting fillColor$delegate;
    @NotNull
    private static final ColorSetting lineColor$delegate;
    @NotNull
    private static final ColorSetting friendFillColor$delegate;
    @NotNull
    private static final ColorSetting friendLineColor$delegate;
    @NotNull
    private static final ColorSetting sFillColor$delegate;
    @NotNull
    private static final ColorSetting sLineColor$delegate;
    @NotNull
    private static final ColorSetting sFriendFillColor$delegate;
    @NotNull
    private static final ColorSetting sFriendLineColor$delegate;
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
    private static final FloatSetting lineWidthValue$delegate;
    @NotNull
    private static final FloatSetting alpha$delegate;
    @NotNull
    private static final ColorSetting secondColor$delegate;
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
    private static final ShaderFramebuffers framebuffers;
    private static final long x;
    private static final String[] K;
    private static final String[] ab;
    private static final Map fb;
    private static final long[] gb;
    private static final Integer[] hb;
    private static final Map lb;

    private PlayerEsp() {
        super("player-esp", Categories.getEsp(), null, null);
    }

    private final SettingParent getTypes() {
        return (SettingParent)types$delegate.getValue(this, delegatedProperties[0]);
    }

    private final BoxRenderMode getRenderMode() {
        return (BoxRenderMode)((Object)renderMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final ShaderMode getShaderMode() {
        return (ShaderMode)((Object)shaderMode$delegate.getValue(this, delegatedProperties[2]));
    }

    private final ChamsColorMode getColorModeValue() {
        return (ChamsColorMode)((Object)colorModeValue$delegate.getValue(this, delegatedProperties[3]));
    }

    private final void setColorModeValue(ChamsColorMode chamsColorMode) {
        colorModeValue$delegate.setValue(this, delegatedProperties[3], (Object)chamsColorMode);
    }

    private final boolean getDefault() {
        return (Boolean)default$delegate.getValue(this, delegatedProperties[4]);
    }

    private final void setDefault(boolean bl) {
        default$delegate.setValue(this, delegatedProperties[4], bl);
    }

    private final boolean getBox() {
        return (Boolean)box$delegate.getValue(this, delegatedProperties[5]);
    }

    private final void setBox(boolean bl) {
        box$delegate.setValue(this, delegatedProperties[5], bl);
    }

    private final boolean getFrame() {
        return (Boolean)frame$delegate.getValue(this, delegatedProperties[6]);
    }

    private final void setFrame(boolean bl) {
        long l = 0L;
        l = x ^ l;
        frame$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)17783, (long)(0x59F65432C3EE8D79L ^ l))], bl);
    }

    private final boolean getShaderVal() {
        return (Boolean)shaderVal$delegate.getValue(this, delegatedProperties[7]);
    }

    private final void setShaderVal(boolean bl) {
        long l = 0L;
        l = x ^ l;
        shaderVal$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)21826, (long)(0x1D42D270231C78E2L ^ l))], bl);
    }

    private final boolean getSkeleton() {
        return (Boolean)skeleton$delegate.getValue(this, delegatedProperties[8]);
    }

    private final void setSkeleton(boolean bl) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ x;
        skeleton$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)4180, (long)(0x14EC2021278129EL ^ l2))], bl);
    }

    private final boolean getExtraPart() {
        return (Boolean)extraPart$delegate.getValue(this, delegatedProperties[9]);
    }

    private final void setExtraPart(boolean bl) {
        long l = 0L;
        l = x ^ l;
        extraPart$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)21901, (long)(0x4C28072FEF0F55D8L ^ l))], bl);
    }

    private final Color getShaderColor() {
        return (Color)shaderColor$delegate.getValue(this, delegatedProperties[10]);
    }

    private final Color getSkeletonColor() {
        return (Color)skeletonColor$delegate.getValue(this, delegatedProperties[11]);
    }

    private final Color getSkeletonFriendColor() {
        return (Color)skeletonFriendColor$delegate.getValue(this, delegatedProperties[12]);
    }

    private final Color getFillColor() {
        return (Color)fillColor$delegate.getValue(this, delegatedProperties[13]);
    }

    private final Color getLineColor() {
        return (Color)lineColor$delegate.getValue(this, delegatedProperties[14]);
    }

    private final Color getFriendFillColor() {
        return (Color)friendFillColor$delegate.getValue(this, delegatedProperties[15]);
    }

    private final Color getFriendLineColor() {
        return (Color)friendLineColor$delegate.getValue(this, delegatedProperties[16]);
    }

    private final Color getSFillColor() {
        return (Color)sFillColor$delegate.getValue(this, delegatedProperties[17]);
    }

    private final Color getSLineColor() {
        return (Color)sLineColor$delegate.getValue(this, delegatedProperties[18]);
    }

    private final Color getSFriendFillColor() {
        return (Color)sFriendFillColor$delegate.getValue(this, delegatedProperties[19]);
    }

    private final Color getSFriendLineColor() {
        return (Color)sFriendLineColor$delegate.getValue(this, delegatedProperties[20]);
    }

    private final boolean getGlass() {
        return (Boolean)glass$delegate.getValue(this, delegatedProperties[21]);
    }

    private final void setGlass(boolean bl) {
        long l = 0L;
        l = x ^ l;
        glass$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)32526, (long)(0xF6FE05CBD59009FL ^ l))], bl);
    }

    private final boolean getBorders() {
        return (Boolean)borders$delegate.getValue(this, delegatedProperties[22]);
    }

    private final void setBorders(boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ x;
        borders$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)7926, (long)(0x8027F0B66F6B91BL ^ l))], bl);
    }

    private final float getRadiusValue() {
        return ((Number)radiusValue$delegate.getValue(this, delegatedProperties[23])).floatValue();
    }

    private final void setRadiusValue(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 56 | (long)n2 << 32 >>> 8 | (long)n3 << 40 >>> 40) ^ x;
        radiusValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)8199, (long)(0x60707372DCE1C838L ^ l))], Float.valueOf(f));
    }

    private final float getDistanceValue() {
        return ((Number)distanceValue$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    private final void setDistanceValue(float f) {
        long l = 0L;
        l = x ^ l;
        distanceValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)28371, (long)(0x5CB4034968CA38FCL ^ l))], Float.valueOf(f));
    }

    private final float getGlowAlphaValue() {
        return ((Number)glowAlphaValue$delegate.getValue(this, delegatedProperties[25])).floatValue();
    }

    private final void setGlowAlphaValue(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ x;
        glowAlphaValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)3169, (long)(0x596213836C64266EL ^ l))], Float.valueOf(f));
    }

    private final float getLineWidthValue() {
        return ((Number)lineWidthValue$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    private final void setLineWidthValue(float f) {
        long l = 0L;
        l = x ^ l;
        lineWidthValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)18157, (long)(0x43E14BAEB1ADBB79L ^ l))], Float.valueOf(f));
    }

    private final float getAlpha() {
        return ((Number)alpha$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    private final void setAlpha(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ x;
        alpha$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)31346, (long)(0x53803E0979F5E86CL ^ l))], Float.valueOf(f));
    }

    private final Color getSecondColor() {
        return (Color)secondColor$delegate.getValue(this, delegatedProperties[28]);
    }

    private final int getGlowQualityValue() {
        return ((Number)glowQualityValue$delegate.getValue(this, delegatedProperties[29])).intValue();
    }

    private final void setGlowQualityValue(int n) {
        long l = 0L;
        l = x ^ l;
        glowQualityValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)4347, (long)(0x711193219A9206C7L ^ l))], n);
    }

    private final float getGlowRadiusValue() {
        return ((Number)glowRadiusValue$delegate.getValue(this, delegatedProperties[30])).floatValue();
    }

    private final void setGlowRadiusValue(float f) {
        long l = 0L;
        l = x ^ l;
        glowRadiusValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)31158, (long)(0x763FE4334DB43D1DL ^ l))], Float.valueOf(f));
    }

    private final float getGlowAlphaMultValue() {
        return ((Number)glowAlphaMultValue$delegate.getValue(this, delegatedProperties[31])).floatValue();
    }

    private final void setGlowAlphaMultValue(float f) {
        long l = 0L;
        l = x ^ l;
        glowAlphaMultValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)30908, (long)(0x732343216D12C1DL ^ l))], Float.valueOf(f));
    }

    private final float getFillAlphaMultValue() {
        return ((Number)fillAlphaMultValue$delegate.getValue(this, delegatedProperties[32])).floatValue();
    }

    private final void setFillAlphaMultValue(float f) {
        long l = 0L;
        l = x ^ l;
        fillAlphaMultValue$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)5976, (long)(0x1C0B028D735BC8B0L ^ l))], Float.valueOf(f));
    }

    private final float getSaturation() {
        return ((Number)saturation$delegate.getValue(this, delegatedProperties[33])).floatValue();
    }

    private final void setSaturation(float f) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ x;
        saturation$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)16906, (long)(0x73668EB3A2CD6F43L ^ l2))], Float.valueOf(f));
    }

    private final float getBrightness() {
        return ((Number)brightness$delegate.getValue(this, delegatedProperties[34])).floatValue();
    }

    private final void setBrightness(float f) {
        long l = 0L;
        l = x ^ l;
        brightness$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)9988, (long)(0x767E250ADAEFB04BL ^ l))], Float.valueOf(f));
    }

    private final int getHDistance() {
        return ((Number)hDistance$delegate.getValue(this, delegatedProperties[35])).intValue();
    }

    private final void setHDistance(int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ x;
        hDistance$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)32417, (long)(0x7425B059FCCACD1AL ^ l))], n);
    }

    private final int getVDistance() {
        return ((Number)vDistance$delegate.getValue(this, delegatedProperties[36])).intValue();
    }

    private final void setVDistance(int n) {
        long l = 0L;
        l = x ^ l;
        vDistance$delegate.setValue(this, delegatedProperties[PlayerEsp.zkmBootstrap2("i", (int)29995, (long)(0x3B21B886C2A01004L ^ l))], n);
    }

    @NotNull
    public final ShaderFramebuffers getFramebuffers() {
        return framebuffers;
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block6: {
            long l;
            long l2 = l = x ^ 0x15E9540E441BL;
            long l3 = l2 ^ 0x6BEAE77C1C6EL;
            long l4 = l2 ^ 0x32CEA7A43FFL;
            long l5 = l2 ^ 0x52E6D3A7460AL;
            boolean bl = BoxRenderMode.C();
            boolean bl2 = this.getShaderVal();
            if (bl) {
                if (bl2) {
                    framebuffers.render(render3DEvent.getStack(), false, l4, this.getVDistance(), this.getHDistance());
                }
                bl2 = this.getBox();
            }
            if (bl) {
                if (bl2) {
                    Color color = this.getSFriendLineColor();
                    Color color2 = this.getSFriendFillColor();
                    Color color3 = this.getSLineColor();
                    Color color4 = this.getSFillColor();
                    int n = this.getHDistance();
                    int n2 = this.getVDistance();
                    Color color5 = this.getFriendLineColor();
                    Color color6 = this.getFriendFillColor();
                    Color color7 = this.getLineColor();
                    Color color8 = this.getFillColor();
                    BoxRenderMode boxRenderMode = this.getRenderMode();
                    ChamsRenderer.INSTANCE.E(boxRenderMode, color8, color7, color6, color5, n2, n, color4, color3, color2, color);
                }
                bl2 = this.getDefault();
            }
            if (bl) {
                if (bl2) {
                    ModelRenderer.INSTANCE.Z(render3DEvent.getStack(), this.getRenderMode(), this.getFillColor(), this.getLineColor(), this.getFriendFillColor(), this.getFriendLineColor(), l5, this.getVDistance(), this.getHDistance());
                }
                bl2 = this.getSkeleton();
            }
            if (!bl2) break block6;
            PlayerEspRenderer.INSTANCE.renderSkeletons(render3DEvent.getStack(), this.getSkeletonColor(), l3, this.getSkeletonFriendColor(), this.getExtraPart());
        }
    }

    @Flow
    private final void onRenderNameTag(RenderNameTagEvent renderNameTagEvent) {
        block2: {
            boolean bl;
            block3: {
                boolean bl2;
                block1: {
                    bl2 = BoxRenderMode.C();
                    bl = renderNameTagEvent.getState() instanceof PlayerEntityRenderState;
                    if (!bl2) break block1;
                    if (!bl) break block2;
                    bl = this.getShaderVal();
                }
                if (!bl2) break block3;
                if (!bl) break block2;
                bl = framebuffers.isActive();
            }
            if (bl) {
                renderNameTagEvent.cancel();
            }
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        block1: {
            long l;
            long l2 = l = x ^ 0x56F24E935111L;
            long l3 = l2 ^ 0x57E71354D71DL;
            int n = (int)(l3 >>> 32);
            int n2 = (int)(l3 << 32 >>> 48);
            int n3 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x40F47F74E34AL;
            if (this.getFrame()) {
                ChamsGlowRenderer.INSTANCE.w(l4, render2DEvent.getContext(), this.getLineColor(), this.getFriendLineColor());
            }
            if (!this.getShaderVal()) break block1;
            framebuffers.applyShader(this.getShaderMode(), this.getShaderColor(), this.getGlass(), this.getBorders(), this.getRadiusValue(), this.getDistanceValue(), n, this.getGlowAlphaValue(), this.getLineWidthValue(), this.getColorModeValue().ordinal(), this.getShaderColor(), this.getSecondColor(), this.getGlowQualityValue(), (short)n2, this.getGlowRadiusValue(), this.getGlowAlphaMultValue(), this.getFillAlphaMultValue(), this.getSaturation(), (char)n3, this.getBrightness(), this.getAlpha());
        }
    }

    @Flow
    private final void onRenderArmor(RenderArmorEvent renderArmorEvent) {
        block0: {
            if (!this.getShaderVal() || this.getShaderMode() != ShaderMode.MIRROR || framebuffers.isActive()) break block0;
            renderArmorEvent.cancel();
        }
    }

    @Flow
    private final void onEntityAlpha(EntityAlphaEvent entityAlphaEvent) {
        block0: {
            if (!this.getShaderVal() || this.getShaderMode() != ShaderMode.MIRROR || framebuffers.isActive()) break block0;
            entityAlphaEvent.setAlpha(0.0f);
            entityAlphaEvent.cancel();
        }
    }

    @Flow
    private final void onFrameBuffer(FrameBufferEvent frameBufferEvent) {
        block0: {
            if (!this.getShaderVal() || framebuffers.d() == null || !framebuffers.isActive()) break block0;
            frameBufferEvent.setFrameBuffer(framebuffers.d());
            frameBufferEvent.cancel();
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
    }

    private static final boolean settingVisible() {
        return INSTANCE.getBox() || INSTANCE.getDefault();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getSkeleton();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getSkeleton();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getSkeleton();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getBox();
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getBox();
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getBox();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getBox();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getShaderMode() == ShaderMode.BLOOM && INSTANCE.getShaderVal();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible15() {
        boolean bl = BoxRenderMode.C();
        PlayerEsp playerEsp = INSTANCE;
        if (bl) {
            if (playerEsp.getShaderMode() != ShaderMode.BLOOM) return false;
            playerEsp = INSTANCE;
        }
        boolean bl2 = playerEsp.getShaderVal();
        if (!bl) return bl2;
        if (!bl2) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible16() {
        boolean bl = BoxRenderMode.C();
        PlayerEsp playerEsp = INSTANCE;
        if (bl) {
            if (playerEsp.getShaderMode() != ShaderMode.BLOOM) return false;
            playerEsp = INSTANCE;
        }
        boolean bl2 = playerEsp.getShaderVal();
        if (!bl) return bl2;
        if (!bl2) return false;
        return true;
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getShaderMode() == ShaderMode.DEFAULT || INSTANCE.getShaderMode() == ShaderMode.BLOOM;
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getShaderMode() == ShaderMode.MIRROR;
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    private static final boolean settingVisible23() {
        return INSTANCE.getShaderMode() == ShaderMode.DOUBLE && INSTANCE.getShaderVal();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible24() {
        boolean bl = BoxRenderMode.C();
        PlayerEsp playerEsp = INSTANCE;
        if (bl) {
            if (playerEsp.getShaderMode() != ShaderMode.DOUBLE) return false;
            playerEsp = INSTANCE;
        }
        boolean bl2 = playerEsp.getShaderVal();
        if (!bl) return bl2;
        if (!bl2) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible25() {
        boolean bl = BoxRenderMode.C();
        PlayerEsp playerEsp = INSTANCE;
        if (bl) {
            if (playerEsp.getShaderMode() != ShaderMode.DOUBLE) return false;
            playerEsp = INSTANCE;
        }
        boolean bl2 = playerEsp.getShaderVal();
        if (!bl) return bl2;
        if (!bl2) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PlayerEsp.x = ZkmKeys.a(3884966693714653926L, 8469795034082864101L, MethodHandles.lookup().lookupClass()).a(127870359793177L);
                        var20 = PlayerEsp.x ^ 5828686063131L;
                        PlayerEsp.fb = new HashMap<K, V>(13);
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
                        var18_3 = new String[112];
                        var16_4 = 0;
                        var15_5 = "\u0082\u0097\u00e3\u00a1\u00b2i\u00ea\u00e79\u00a0r\u0013\u0001\u00f2\u0093v\u00f5\u00e5b\u00fcH\u008c\u00b7A\u00ce\u00e5O\u00e0wKg\u00bf\u00e9\u00be\u0084\u00ccz\u00a3$*\u00de\f\u000by\u00a2/4Y\u00e3B\u00cc\u00c7\u0093c\u001d\u0016 r\u00f9\u00d8\u00ed\u0010\n\u00d2\u0013\u0012\u00ebc\u001b\u00ef.\u00ab\u0087G\u0000\u00f5\u00ad\u00e6\u009e\u00a7\u00e5h\u008d\u0010zv\u00df\u00c8\u00e3 2\u00ef\u00b4\u001aE\u00af|\u0006\u00dd\u009d\u008f1<y\u00dd\u00d6[w\u0089\u0014\u00b7\u00dd\u00ec`\u0014P\u00e1\u00c0W&\u00eb\u00ef \u00ef\u007f\u00f2\u001d\u0005\u00d6\u00c0\u00a8\u007f)\u00c7\u00b0S|\u00af\u00caUNSRZ\u00ab&\u0019=gsUG\u001a\u0000\u00b5 \u00f6+\u0018\u008a,\u0006\u0099\u00d5\u00e2\u00c4\u00a3\u0082\u00eb$\u0091.\u0014?\u00c2G\u00aa\u0007D'\u00bf\u0096\u008bS\u0086&1&\u0010\u00d5\u00ab\u00d8\u001472$\u00f3\u00e2\u00995\u008f\u00de\u00d8F\u0010 \u00bd\u0010<\\q\u00c9\u00f2}\u00edv\u00f7U\u00d3\u00c0\r\u00ce\u0099\u00f7\b\u00bc\u00f8\u00dby\u00ca\u00ff&\u00b3\u00e0A\u00864l\u0018E\u00f8\u00ca\u00184 b\u00fb\u00d7\u00fa\u00f5\u00ad\u009c\u00ac\u00fd\u00f1V\u00fcL\u00b7\u00e25r\u008a \u0004\u00e8wK\u00bbI\u00c1t~k4\u00da'&\u0081B\u00b14A\u0092\u000fu49b\u008f\u00e7\u00ed-sY\u0000\u0010\u00e6\u0089\u0012\\\u00c2\u00e0\u00c9DN\u00aa\u0080e\u00f3\u00bd}\u009f\u0010\u00bb\u00e9\u0092\u00d3:\u00a9\u0003q\u0014x\u00f48\u0000\u00e6\u00b62H\u001c\u0099\u00c2\u00f7\u00d9\u00c1\u00e2\u0092\u00d7\u00a2Z\u00d0\u00f7\u00d5\u00e6\u0004(\u000f:\u00eb\u000fh\u00b8$\u00aa\u00e8]16\u00f6<\u00b2\u001c\u0010\u007f\u00a3\u001cLy\u00dbS\u00dd5m\u00dd\u00d60n \u0013^\u00f8\u00f9I\u0003\u00e3\u00b6uY\u009cqv\u00ab:\u0096\u00ca\u00dc\u00a1\u0092\u00ea\u00ee\u00f10UB\u00e9t\u00ccjP@\u00f1\u00c5\u00fa6\u0006\u00e6\u00bc\u00ef\u001e\u00be\u00f9\u00ab\u0010\u00d6\u00ad~\u00a4'\u008a\u0016j\u0003\u00e4\b(\u00aa\u0007X\u00ea0(\u0004\\\u00e0C}\u0010\u009f0\u0011 \u008e\u0011}\u0081\u008c\u00b6\u00e7c\u000fc2\u00c4\u0090\u00c5\u001b\u00e1xE\u00eb\u00b6\u0004\u00c9\u00e5\u00cal\u00ae\u0081\u00d7\u00eeuT\u00e7 b\u00de|\u00e6\u00ad\u0096\u00cei\u00b9b\u00b0\u00d4\u00be\u00b3\u00ae\u009d\u00cbI7v\u00db\u00cfV\u00c3\u0084*\u00c4\u00a2\u00ba\u00ca\u001f\u00ba\u0010W\u0001\u007f;\u0083\u00d1\u00fc\u00adt\u008b\u0003Z\u00f1'\u0018\r r\u00a2d\u00bd%\u00ac\u00efL\u00d47\u00d9oC\u00e7\u00d4<w\u00c4\u00c6mA\u00bbL\u00d9\u009d\u00f8[\u00e4\u00ae^2\u00c8 c\u00f7\u00b0l\u00d7\u00fbj\u00a1\u00dc\u00cd\b\u009eG\u00b7\u00bc\u00b7\u0081\u00f77\u001e\u0011\u0001\u00aa\u0084k\u00c2\u0007\u0007NL\u00e8y\u0010\u00f1)4\u008c\u00e0\u00e9\u00df\u00e9\u00f8\u00c7\u00e1\u0017Z\u009b\u0097\u0006(XI\u00f1\u00dfmR\u00f4sc\u0096\u00f5\u001dp\u00f6\u00b8\u00fc\u0091\u00f7^)\\\u00f7AD'\u0095\u00eeH\u00d7\u008a\u0000\u0017\u0099L\u00f6T\u0019\u00cc\u00ec\u009e\u0010C\u00afNb\u0085at|\f\u007f\u00eeT\u00eb.\u0082\u0003@\u00ca>;\u001f\u00ec\u00b0\u0087\u0099\u00e7v\\\u00e9\u00e1\u00c5N\u0093\u0014l\u001c\u00e5z+\n\u00be\u00c5\u00e4\u007f\u0082\u00aa\u0081YX\u008e,\u0004\u00c0\u009bqJ\f`\u0015\u00dfa\u0019*l\u00b7\u00ca\u00a5\u00f1Dv^\u0082\u0017\u00d4.\u00a6\u00be\u001c\u00e9\u0082A0\u0090\u00a4s\"\u008f\u0007\u00fa)Q\u00d6\u0094\u00cd\u00c4C\u00aa\u00f0|\u00d4$\u0096\u00a0\u00a2\u009e\u00be\u0017\u000b\u00b3\u0012*\u0081\u00a0\u0003;\u00df\u00f4O\u00b9`\u0017P\u001eA\u00a9\u00db\u00eb\u00e49D\u0010J<\u00b6\u00bc!\u00d81\u0095\u00f7\u000e\u008e\u00c1e\u0001\u001fr\u0018\u00dc_\u00f2^\u009aJ\u00d5\u00e5j\u001fk\u008f\u00db?\u0098I;\u0006]\u0012{\u0088\u0006R \u0003\u00adV\u009a\u0085\u00b0\u00b8I\u00aa\\\u00e7\u00cbIH\u009fV\u009e\u009a\u00b1\u00d9\u00fe\u00c3)\u0091\u00a9>:\u00dan'\u00e4\u0003 ??,\u0006s:\u00b5\u00ad\u0006a\u00a3&\u00ad\u00b9\u00ac\u00a5\u00bd\u00cd3i;\u008d\u00d5\u00f6\u001c\u001a\u00ec\u00d5\u008d \u0095`0\u0010r`\u00c7\u001cpQ\u0093d\u00e5:\u0019\u00d6|4\u00b2a@a\u00ae.\u00d3\u00b4\u00fb\u0019A;\u00b3o\u0004\u0099\u008f\u00ca\u0011\u00cd]\u00e9O\u00afC\u00fc\u008f\u00e7zg\u0018\u00da\u00b0 \u00fe\u001d\u00ee\u00b4*\u00b3i\u0001b\" \u00ab\u0010\u00c7\u00cb\u00f6C\u0004\u00b7\u00c3\u00b1\u00e2p}\u0013\u0012S\t\u00c9\u009d\u00f5\b@C\u001b\n\rw\u00b7[\u00fb\u00f6\u0094\u001c\"\u00ce\u00bf}\u001d\u00c9\u0099\u0016\u00bfO\u00eb\u0013\u00d6\u00ed!,<\u00bb\u00d2D\u009e\u00e1(O\u00f5\u0005\u0012\u00dbJ\u0087KQ< b(\u0081Tlb\u00d1\u0001\r\u00e2%\u009d/H\u00b7V\u00da\u00e9e\u0010n46x\u00e3\u00ac#\u00c1gE\u0007\u001c\u009d`3H \u0093\u0001\u0086\u0013\u008d\u00aa\u0082\u00cc\u000bp\u009b\u000b\u009f\u00e04\u000b\u0019\u00d5Y\u0094WM\u00e8\u0085\u0086m\u00dc/R\u0099t\u00c0@\u00a1T\u00bc@\u0004~\u00fc\u00ad\u00ed\u00cf{97\u00d2\u00f6o\u0094\u0014\u00a8p*\u0091Lm\u0017\u0004\u00df\u008c\u00ae\u00fb\u00d7\u00118\u0000%\u00c0\u0097\u0016+\u0095\u009cx\u001e \u0006\u0089R\u00cdbf\u00f1?\u0013\u00d8?\u00d1l\u00f33\u0085\u0006\u0013W\u0012\u0010\u0003\u0016\u00b3\u00e9W\u0094EEO\u00e0g\u00cb\u00d5\u00d4$4@b\u008b]\u0085%I\u0002\u00de\u00f1/\t\u00b7\u00b7\u00ac\u0002|\u001f\\( \u001a4\u0007}vt\u00cf\u00b3\u00fa+\u0010\u00a7\u009b\b\n\u0086F\u00c1X\u00d4\u0089\u00e3\u0016\u00fb:Z\u008e\u00a8\u0002\u009f\u00fc#\u00db\u00a6a\u00af\u00cbM?|u\u00e8Mn\u0010\u0082\u009e\u00eaEn\u0081\u00e7\u0016\u00a7V\u00c4G\u00df\u00b7\u0092/8<h\u00ba{^\u0091\u0095\u00c1\u009f\u00a3\u008e\u0084\u0014\u0091\u000f7\u0014\u0004\u00e0\u00e0q~\u000e\u00c3\u009f\u0092\u0019ksB*\u001c\u00c6\u00e9P\u00b3C\u001dI\u0085\u00ec-Y1\u00d2\u00b3\u00db\u00a2AiD\u00d5\u00e5|\u001a\u00bc(.G\u00af\u00a7;\u0095\u00d7\u0087\u00f8\u00fe\u000e \u0001\u0085\u00c8\u00a2k3\u00f0\u00cc_\u00ce\u00a2R\u00c5\u00b2\u0099\u00b8\u0000\u0088\u00da\u00c3+2\u00fd\u00b0\u008fx\u00c5\u00e4 \u00fc@^;\u001c\u00be\u00ad\u00e2\u00b77\r[\u00e2F\u00cej\u00af\u009e\u00fdO/\u00b6\u00b7\u0097\u0005 Ix]\u00fb\u001b\u0093 \u0095\u00ed\u00e8 P\u00da!6\u00bc!k@\u009dqC\u00a1\u008c+\u0003\r{\u00a5\u0013\u0007\n\u00aa\u00e9l\u008b\u00d5?>(\u0089S\u00c4\u0013\u00b0\u00fe\u00f8\u00d9-\u0086$F\u0015\u00b1M\u00ab\u0081\u0092b\u00f3\u00b7\u00db\u0087\u00b3\u00a3Q\u009a\u00e5\"\u00ba\u00db\u009b\u00eb\u0015tb\u008e\u00e2B\u00f7(\u00be\u00f6\u0005\u00a6\u00df\u00f3\u00c1]\u001dV0B\u0097U\u007f\u00cf\u0005K^\u0005\u0093\u00de\rgq\u00ael\u00c0\u00e58\u00e0l\u00ff\u00b4~\u0007\u00c7\u00c2\u00de\u0003\u0018HY\u0097\u00d1\u001fN\\\u00f5\u00bd\u00ba\u00cd\f\u0012\u00f9\u00103(E\u00dai\u00beV\u00d8\u0002 \u00cf\u00b7t\u009cA\u009b\u00abN\u00caB\u009e\u00da\u0083D\u009f$\u00d3\u0092\u0094\u00d50\u00a0\u00c4\\-\u00f9q\u00b5\u00fc\u0013\u00ad\u00b6 s\u0005\u00a2\r\u009aI\u001f\u00a0\u0015ejA\u0095`\u00fe\u00a1\u00d9\u00e8_`\u001b\u00adSe\u00eb)\u00dd\u00fb<h\u00e2\u00d8(G1\u00f5*\u0099\u008d\u001dF2\u00c4t\u00eb-\u00f6\u0007y?\u0099=\u0088n\u0093\\F5\f4\u0099\u00ee\u00a3VC^\u00c3\u00c0\u009c\u00d31\u00d9\u00bd \u0001\u0001\u00e6\u00ae\u00d5n\u0002\u0016z|*7\u00f2i\\\u00fe8\u00f9z`F\u00fc,\u00b9r\u00ff\u009d\u0093\u00be\u00fdY\u0084@\u008c|\u00f3\u00bfF/\n-\u0089\u0004\u00c5\u00a8@k\u00eb\u0012T\u00cf\u0001\u00e8\u009ag\u00be\u00f0\b\u00a7V\u007f\u0093n\u00cc\u009aiu\u00abV\u00cc.\u0099&\u00d7|\u0003\u008f\u00b6\b\u00bb0\u0098\u00b9\u00cf\u000f\u00b4\u00e3\u00ceFt\u00aa.\u00a2w\u00d1\u009d\u00ed Lf\u000b\u00be,\u0005?\u00d0\u0083\u00f8\u00bf \u00e35\u009b-\u008c\u00d9\\K\u0007\u0017-\u00ed{\u00e8uY\u00ff/n\u00e5\u0010&=g)\u0096;\u008f\u00ee\u00cdxV\u0012\u001e3i\u00d9\u0010H\u00c1\u001ck\u00fa.\u0010\u00d61\u001d\u008aQ\u00fcV\u00e7\u0089 \u000f\u00b7)=_N\u00b5\u00fd\u00b3Mo\u00be\u00d9\u00bd\u00e7],\u00c0\u00d2\u00a32\u009eXz\"\u00e3\u00d7Wf\u00c8rL(hw\u00ef\u00bb\u00b4[\u00c8%\u00fc\u00b6M{>\u009e\u00f3\u0092\u00c8\u00ec\u00f4\u008a*k\u008c\u0011Y:9f\u00fdr\u0016a\u00cf\u0011\u00ffu\u00bdkx\u00cc0w\u00860\u001e\u00ca\u0097\u00b03\u00b3\u00b3\u00e1\u00a5\u0094\u0087\u009d\u001e\u00d4\u00be\u00b4\u00c7<%\u00ea\u0014]\u00a5\u008a\u00c6\u0092C^\u001b\u00a2\u00c2\u00a1\u0007\u00f9\u0092\u00e4\u00fe\u00a4\u0013\u009dug\u00b9\u0002\u00e3\u0018\u00a9\u00a3\u00e4\u0099\u00d2\u00bb+\u0005\u00a9\u00d8\u0092\u008c\u00a8\r]n\u00dfv=\u0085y\u0094\u00f99(jD\u00c2\u0082\u00c6f\u00a6a\u0092t\u0087\u009d\u00f4\u0019\u00f0\u0092\u0095N\u0098_\u001as\u00b6\u0086\u0096R\u009d\u00echn\u00b7\u00e1\u00f1\u00c8\u0098\u0086O\u00b5\u00a8\u0084 \u00ba\u0018\u0017\u0000^\u001d\u0005\u008a\u0002\u0015\u00ca)\u00ba\u00a8[9\u00f9\u00b9\b\u00f7\u00b6\u00a3\u00cd\u00cb\u00a6\u0083\u00ae\u00ae\u00033U\u00ee0\u00f3\u00d101|\u00c8j\u00f4\u00f61C\u0096\u0088\u00c5\u00c4\u0092\u00f3\u009a\u0005\u0012\u0085\u00d3+d\u00b3qgX\u00fbHz\u00d9\u00ac\u00c9\u00d1z\u00d0OJ\u00b7\t~)?\u00c1\u00d5:\u00c1(\u00e5\b\u00ce\u009d\u00b6\u00d3\u001e\u009c\u0019`\u0015\u00f3\u00f0\u0013\u00c8\u00e9\u00e5\u00b1\u00de\u00db\u0007\u00b4Z\u00cc\u00fd\u0092\u00bd\u00010qC\u00ae\u00e5L\u00ad\u008b\u00f8J\u008c\u0019 \u00d0|2\u00cd\u00e9[\u00be\u0098y\u00cdMW\u00a5\u0006C\u0085\u00d4\u00c0j\u00beAi\u0006\u0000\u009d\u00e77X2\u00c2z28\u00d8\u00e4H\u0006\u0094\u001b\bM\u00f6\u001a\u00e1p\\p\u00fe,\u0099S\u00b4L\u00f2\u00b9hG\u001f8]\u00a7\r\u00bb\u00a6}~\u0011.sL3z\u00e6\u0017\u00ed\u00df\u00aa\u00e5\u0019\u0090gt\u00c3\u00a2o\u00b2\u00f3\u00f3U\u0018\u00c2\u0015q\u008a>\u001dr\"8\u00e6C\u00a3\u0001\u00cc\u00932^\u00d9\u0007g\u0088\u0013O#PuEe\u0006\u00f7\u00a7B\u00b9\u00ed\u001f#\f\n\u0091\u00d6'\u00d6]\u00fb\u00e4\u0095\u00fc\u009d\u0013j]\u00ff\u00ad\u00e3\u00f2\u008f\u00ba\u008f\u00db\u00f4:\u00f7\u00fc\u0013\u00a1KV\u0092X%\u0000\u009cE\u00fb\u00baP\u00a8\u009fR\u00b0\u009dB:\u00cav\u00fcl\u0085BS\u00ca\u0015\u00c0\b\u00a8Z\u008c\u00a2\u00a3\u0096(}\u00cb\u009e\u00ff\u0010c\u0012m\f\u000bx\u00a6\u00dcr\u00fas\u00a4\u00b7\u0014Q)\u0018\u00c8\u00b5\u00d1O\u00b0\u001c\u00a1I\u0005\u00fb\u0018V7\u0083\u00c1Q\u00b7\u008b\u0087\u008f'\u001b\u000f\u00b9\u0010t\u00fc\u00ab\u00c0YG!\u0087\u00c0\u00f6\u00a0\u0089\u00c4\u008c\u00c8-\u0010\u00bdL\u00d8\"c%i\u00d4\u000b}D\u00aa\u00c1[\u0084N \u00a5\u00965\u0081\u009dQW\u00a5\u00ec\u00ffB\u00e6\u008f\u0080\u00eeq\u00fd\u0003\u0083\u00f7\u00fb\u00fdb7\u009c\u0016\u00da\u0006\u00e7\u0010\u00af@8 \"k\u000e\u00cc\u00ef}I\u00acA\u008ew\u00ad&\u00ee\u00ffb2\u008f\u00b2\u00cd+\u00c3T9\u00b4\u00a7\u00b4\u0082\u00ef6P\u00fdAF\u00d6-\u000fU8\u00dd\u008d\u00f1\u00dd)9\u0091:\u00f3\u009b\u0014r\u009c\u0018d\u00da(1\u0089\u00f7\u0091F~yH\u000eD:\u00ba\u0094\u0082\u00fb\u00e5=Io\u001f\u00aaBnfo\u0081JY\u00f8\u0099|CLbE\u00c5a\u00c1w\u00f78<*\u00c4IykM!`p\u00d8t\u00a7\u0080K\u0018K\u00e1UH#(\u0006=4a\u0003~?\u00e4g\u00f6Y\u00d6\u0019\u00ac\u00c9\u0081/\u00da\u008a\u0018Fy\u0015\u00eeM\u0001p(*\u00ff\u00ab\u00e3\u00d8\u00b2\u0010~1\u0092\u007f\u00ffP\u0088j\u0006D\u00cf\u0018\u0085\u0014\u001cH\u0018\u0000z\u00fb/C\u00c6\u0094\u00c9S\u0098\u00e3\u00ad\u00de\u00fdp\u00da3\u00be\u00af\u0087\u00e2\u00df|\u0081(\u00c0\u00fc\f\u00ca5\u001f\u00895\u0011\u00ee\"p\u00dc\u00e7\u00eaQ\u00cd1[\u00e2\u00cb\u00b8\u00fcQQ%#\u00b2\u00ce\u00f5\u00fd\"\u0095\u0082=m\"!\u00b5\r q\u009f=m\u00ec\u0017\u008d\u009c\u00e4\u00d6y\u00df\u00ba\u00dc\u00d37\u0098\u00f2!\u00938:\u00f5\f\u0080\u0005\u00ff/\u00ab\b9\u00df\u0010\u00d2\u00b1\u00a5\u0017am\u00d4\u0098U\u00f0\u009b\u00cc:b|\u00a6\u0010Pn\u00bc\u0013\u001f\u001a\u00dc\u00bd\u00bcp\u009a\u000e\u0093\u00d9\u00db< \u009d\u00d3\u00b3J\u00afb\u00ec-\u00c3s`\u00f0\u000e\u009a\\\u009b\u00d1\u0011\u00ca\u00ff-\u008d\u00b6/\u00f8\u0083\u00da\u00c8t\u00ee<\u0019\u0010\u00a4}\"\u00c6\u001fDS\u00d2$\u00b3\u00c2\u0014\u009c&\u009f- \u0088_\u00c2DK*\t?\u00f3'\u0097 \u00e5_\u00c0\u00d7\u0005\u00ee\u008f\u008a\u00be\u00b9f\u00b0]s\"\u0018\u00e8Y\u00a9Q (jL\u00ecdL\u0080\u0084w<8\u009b\u0093\u00be\u00b9\u00e7cj\u0098\u00ceI\u00ca\t\u00b6\u00cbS\u00aeM\u0085\u009d\u000b\u001c\u0018\u009b\u00ed\u00d6\u00ea&\u009c\u0089fy\r*Q9W|K\u00e2\u00b8\u00b28\u00b2\u00f7'\u00e1@\u00f0,\u00ed\u00b9\u0080\u00fc\u00d4\u0094\u00a3\u0004\u00ac{\u001eD/\u00e8\u00957NH\u0094%\u00c3-\u00c9\u0006;\u00ab\u00aaw\\\u00cc8\u00b6\u001d\u00e3\u00cf\u0099\u00a8\u00fc-P%\u00f7\u00eauW\u001e\u00e8\u00b47,\u00c7\u007f\u008d&a\u00f6n\u00d55Cp\u0016(\u001d\u008d!\u0002\u0017\u009e\fv\u0010\u00bf\u00cfs`h\u000b\u00ff\u00f6\u00ad\u00b1\u00e3U\u00d3\u0090}\u00ad\u0003\u00bc\u001a\u00b6\u00e4\u00c2\u00e1\u0005\u0007\u00b3\u00e5[\u00bb\u008a\u0012\u0010^\u00c10\u00c2\u00c7FJ\u00e8\u001e\u00d0\u00f2\u00cb\u00ef\u008f\u00a4\\\u0018xu\u00cd\u00af[\u00cb\u0097\u0087H\u0002\u00e6\u0019\u00c0\\\u00e8*\u0015\u00c3g\u00a0\u00fd^ \u00aa0\u00f0\u00a0\b\u00f553\u001d\u00ce\u0091\u00d2\u00bc\u008c+P6w\r\u00eb\u009e8\u00a3\u00ae\u009bD\u009aD\u00e6\u00e1\u0087\u0017\u0092\u007fOs\u00ba\u00e2c\u0019\u00a7m\u00aa\u00bf\u0007\u0018\u00b4\u00c0t>\u0018\u008b\u0090PMd-\u00fc\u00aa\u00ee\u00a3\u00f6X\u001bI)\u00a1n\u009f\u00c6Fn\u0018\u0080\u00bd \f\u001d\u0019x\u000bY\u00d6\u00b5L6B\\\u00e3\u00a1\u00e9\u00c7\u0019\u0004\u00d1\u00cd\n\u0099mL\u00f4&Ke\u0099\u00a1\u0082\u00ce\u0018\u00e8\u0003\u0080I\u0091Srqs\u00b81\u00bb5l\u0086\u00ed\n\r\u0094\u0099\u00d2\rWw(\u00c1\u0001\u0014\u008c7\u00b0f,\u009b\u0090\u00c1i\u00be\u00d4X\u00dd\u00a16\"\u00ecK\u00b94\u00fd\u008dL\u00ea\u00b9:\u0003?p}\u00cf\u001d\u0092M\u00bb\u0080.(\u00fc\u00c1\u00ec\u00de\u00e2'\u0087\u000f\u0000H\u001b6\u009b\u00dc\u00dc\u001a\u00eb\u0016\u0086\u0098#\u009d\u0085\u00fc\u00e6X\u00faaH:t\u00f1\u00c6Xu\t\u00bf\u0010\u00c9\u00d7 \u008c*uSa\u0083nP\u00da\u00b51\u0000\u001c\bj\u00fe\u00da\u00cc\u00c2k\u0001\r\u0097\u0080cX\u00a9w\u0086\\P2\u0018|p\u0084\u0017\u0081\u0098\u00ffDR\u00c3R\u0083\u0094\u00c8\u0005i\u0016I\u00ea\u0015\u00d0\\\u0003\u00fb\u0018o1^\u00b1Y\u001a*\u00a50\u00d9\u00e6[\u0093\u000b\u00f0\u00b4[\u00ce\u00e28Q\u00a0\u00e3\u00f70w\u00e2\u00dax\u00be\u0019h\u00fd\u00f81\u00ed%wZ\u00f5\u00d8\u00a6'\u0090KZ\u0099\u00c1\u00ee\u0015\u00dd\u001cC#\u00b9\u0019P\u001ee\u00f2d1]\u00c3'\u000f6\u0001\u000b\u00f5~T\u007f(_m\u00caF\u0001&\u00a4z\u00aa\u001c\u00ed\u00f4+\u0010%?\u00f7\u00f63\u00ab\u009c}*p\"`\u00d55C\u00eds\u00fa\u00f0\u00ef\u00e3\u00e12\u00dc\u0003^(\bs\u00f8}dpd1\u001d\u00e9l'8\n\u00fb\u00b6\u0006\u00af\b\u0015\u00b0I\u0089m\u008bbC\u00adH\u00c9\u000e4\u0088-\u00ae\u00aa\u00c5H\b\u00ed \u00b1~5\u00c9\u0010L\u00bc\u00b7\u0093;\u0087\u00ff\u00bc\u00af\u009b'L\u0010\u0094\u00b7\u0086\u0015#\u0094\u0095\u00d4\u0006$*]\u0019\u00be \u00b0\u001f\u0081\u0084W`\u00ac\u00cd.Z\u00972We\u00ff\u00da\u00b4\u00ba\u00aaZXD\u00f5>od\u00acXg\u0018\u0000\u00d9 \u0096%\u000b\u00aco\u009eC\u0095b]6\u00ed\u001e\u0003rn{\u009a\u00c4\u0098\u008e\u00ec\u008dZ\u00ba\u00b5\\\u009a\u00a7\u00dc\u00e6y \u00925\u0012\u00d0\u0001\u0087-\u00a8@\u00fe\f\u00da\u00b1\u00d6\u00c2\u00e8\u00ba\u0095\u00d2\u00fd\u009e\u00ed\u00ba\u00e3\u00f1\u00ca\u0002\u00cd7\u0097\u00c7\u00a3(,\u001f\u00f9\u0097\u00cb\u0088\u00c7\u00bc\u00b4\u00ba\u000f\u00aew}&p\u009f\u0093d\u00de\u00c0N#L\u000f\u00bdY\u00105\u009a\u00e0\u0014<q\u00ba\u00af\u00bc\u008e`\u00e0\u0018\u00db\b0\u00f2\u0090;k\u0082t\u00bafK\u0092`;\u00e1@j\u0086Z\u0090\u00eau\u00b0\u0018upY\u00a7\u001fB;\u00c5\u0088\u000f\u0012{\u009b\u00ad<\u00f3a\u00a54G,\u00a7c\u0085\u0018\u00f8C\u009c\u00cb\u0092E\u00f75\u00c5\u00c7\u00bb>\b\u00c9\u00ac6\u00e8\u00f9\u0093\u00c4\u00c4\u008bO\u009c u\u00f7\u001a2\u00aa\u00e7\u0094\u00bb'i\u0000L\u00db\u0000\u00e56\u00d04M\u0089zQ*p\u00f1\u001c\u00fe\u0014\u007fr\u0002# \u00ear\u00b1\u00b7\u0099\u00a4\u00fa7 \u00f4]\u009b\u00e2/;S\u0099\u00c3]\u00d4-/\u00aa?:\u000f\u008dQ\u0015\u00a8\u0005\u00fa =\u00ben\u00e8\"~Z\u0086X\u00cb|9k3\u0092\u00d8r\u00a72\u00a4\u00df{@\u009b\u00b9\u009d\u008a\u00cba\u00fa\u00eb\u0017@\u001e\u00c7qU.-B\u001d\u00fa\u00db\n\u0084\u0089\u00c6\u00a4\u00baF\u00c8\u00e1k\u00fb\u00fb\u00eaF\u00c7\u00e5\u0099\u00a9\u009a^\u0090\u00d9\u00a9\u00ef7$2-*i+\u0094\u0001\u0000(\u008c\u0011\u00be\u00a3\u00a6V\rC&\u008b\u00f6\u0003\u00e8\u00af\b\u00c2\u0089!l";
                        var17_6 = 3853;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = PlayerEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d4\u0013\u0095\u00d6\u00a17\r\\\u007f\u008c\u009a\u0097=\u00e8\u001b\u00ef\u009aC\r+*Q\u0002\u00ab\u00ddG\u00a0p\u00b7\u00b9\u0080d \u00d4\u00da\u00e8\u00ed\u00b8%M'DJbM\u00ba\u00df\u0019\u00af(;\u00a4\u00c3\u00ddG)\u00da\u00dax\u0086\u00d37\u00dd\u009eF";
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
                            var18_3[var16_4++] = PlayerEsp.zkmDecodeUtf8(var19_9).intern();
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
                PlayerEsp.K = var18_3;
                PlayerEsp.ab = new String[112];
                PlayerEsp.lb = new HashMap<K, V>(13);
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
                var6_12 = new long[70];
                var3_13 = 0;
                var4_14 = "SI\u00e5_\u0018\t\u0019\u0099\u008f*\u00ec X\u0087kJ\u00e5\u0017\u00c5\fZb*\u0019/:m=n\u00b4o\u00ac\u0001F\u00c9\u00c8-\u009c\u00daNw\u0012Q\u0087\u00a5\u0000\u00ac\u0012\u00b0\u00d42\u00d5;\u00ba\u0080/o\u009c\f\u00bf\u00b2\t\u00ee\u00f3\u00d2\u00bfp\u001c\u0091ZP\u0090\u00ef\u00cf\u008c]\u00cee\u009b\u00beV\u00c6R\u00a5\u00c8\u0006U[\u00e7\u00d7\u0014\u00bb}\u00d3\u00fe2{\u00a0v`\u00a8u\u0093\u0003E\u0010\u00dbl\u00bfhD\u00ab\u00f1\u0095r\u00de\u0090\u001c!Wrw\u0006\u0082*N\u00e2\u00e5\u00e3?64\u00d5\u0090/\u00f0\f\u0088\u00da\u00bc)\u00d4\u00bb\u009a\u001a\u00bfG\u00de0H\u0007\u00bf\u00e3\u00bc\u00c2\u0019>i\u0014\u0004\u00cbg\u008f\u000b\u0006;\u00d5\u00d0\"\u00d8\u00ff\u00f3\u00ff\u00dd\r\b\u00fd\u00c7\r\u00f5\u00a9Pl\u009d\u00cd\u00e0\u00e4\n\u00e14G\u0013\u0082\u009bp\u0000\u001f\u00dc\u00a0\u00d4\u0080\u001dQ@z\u00daI\u00dc\u00d54\u00c6\u0082O~`*<\u00ad\u00f6\u00ef@*+\u00ac\u00f8fMHxP\u0093|\u0005\u009c\u0090\u00d6\u00b3!\u00f1\u008e\u0004\n\u0099\u00a2%\u00d8\u00ee\u007f\u001f\u00b6\u00e9\u008c\u008fN\u00ffC\u00cbLVt\u0081\u00f2\u00a0K%\u00c0\u00c9\u00da\u0091\u00e2\u0000\\\u00a1~b\u00ab\u00cc\u008f#\u00a86x\u0092\u0012N\u00e4|#N\u0087\u0010\u00ceD\u00d4l\u00af\u009eh\u00a7Q\u00f0\u00c5\u0004H\u0086\u00e7\u00f4\t\u008e\b\u00d52\u00a9\u00cd0\u00ea\u00aa\u00de\u00a6\u00be\u00e8\u0091\u00db>\u00aa\u0095\"\u00cb\u00c3<\u00efk\u0001\u00b6s\r\u0097\u0016\u0090\b\u00c9S\u00cf\u00b3\u00e5\u00bdsv\u00f4\u001d\u00b5u\u0097\u008e\u00e2N\u00ae\u00b6n\u00ac\u0088\u007f\u00f4RI>\u0082\bh\u00c8B\u00c0\u0082H\u0080\u0083b\u00fc\u00ed\u00e1\u0092v\u00a1;A\u00e7\u00c6%\u00cb~\u00b6\u00b0\u00e2\u00138\u009eU!t\u008d\u001f\u0004r\u00eet\u00b5\u00d73O\u00fe\u00e52\u0088\u00cf\u00f0<\u00ba\u00a4\u0014\u0016'\u0094\u00a0\u0015\u00a4\u00c6\u0091\u00aba\u009b\u00f7r\u00fd\u00eas\u00fd\u00df\u00bf\u00cf7\u0011I\u00d9\u00ac\u00b2\u009ew\u00ac3\u00156\u00daai:Ie\u00af\u00ad\u008c\u00d8>\u0099e{\u0085\u00fe\u0014\"g~\u00a3f7\u009c~\u0002\u00e6\u0004\u008c\u00846m\u00f5Yc\u009c\u00be\u008b\u00fc\u0002\n\u00ff\u009c\u00bbG\u00b4\u0087\u00ca\u00fe\u00b3\u0097k0\u009a\u00e2\u00b8\u00f3\u0011F\u00a6B\u0013\u00ect\u00ce\u00b6\u0096z#dz?\u00f1\"\u00a3p\u00fb\u00a5\u00a9>H\u00d8\u00e4\u00d6\u0082\u00b8\u009b\u001bz\u00df0U\u0000";
                var5_15 = 544;
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
                    var4_14 = "\u00ad\u007f(\u00f0\u00ees>nm\u000e\u00a6?\u00ad\u00be\u00ad\u0002";
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
        PlayerEsp.gb = var6_12;
        PlayerEsp.hb = new Integer[70];
        var41_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "types", "getTypes()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "renderMode", "getRenderMode()Lsu/catlean/s8;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "shaderMode", "getShaderMode()Lsu/catlean/rw;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "colorModeValue", "getColorModeValue()Lsu/catlean/sa;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "default", "getDefault()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "box", "getBox()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "frame", "getFrame()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "shaderVal", "getShaderVal()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "skeleton", "getSkeleton()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "extraPart", "getExtraPart()Z", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "shaderColor", "getShaderColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "skeletonColor", "getSkeletonColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "skeletonFriendColor", "getSkeletonFriendColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "fillColor", "getFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "lineColor", "getLineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "friendFillColor", "getFriendFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "friendLineColor", "getFriendLineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "sFillColor", "getSFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "sLineColor", "getSLineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "sFriendFillColor", "getSFriendFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "sFriendLineColor", "getSFriendLineColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "glass", "getGlass()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "borders", "getBorders()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "radiusValue", "getRadiusValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "distanceValue", "getDistanceValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "glowAlphaValue", "getGlowAlphaValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "lineWidthValue", "getLineWidthValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "alpha", "getAlpha()F", 0)), Reflection.property1(new PropertyReference1Impl(PlayerEsp.class, "secondColor", "getSecondColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "glowQualityValue", "getGlowQualityValue()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "glowRadiusValue", "getGlowRadiusValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "glowAlphaMultValue", "getGlowAlphaMultValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "fillAlphaMultValue", "getFillAlphaMultValue()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "saturation", "getSaturation()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "brightness", "getBrightness()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "hDistance", "getHDistance()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PlayerEsp.class, "vDistance", "getVDistance()I", 0))};
        PlayerEsp.delegatedProperties = var41_20;
        PlayerEsp.INSTANCE = new PlayerEsp();
        PlayerEsp.types$delegate = SettingFactory.settingGroup$default(PlayerEsp.INSTANCE, "types", AuraTargetsMode.BOOLS, null, 4, null);
        PlayerEsp.renderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)PlayerEsp.INSTANCE, "render-mode", (Enum)BoxRenderMode.BOTH, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.shaderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)PlayerEsp.INSTANCE, "shader-mode", (Enum)ShaderMode.DEFAULT, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.colorModeValue$delegate = SettingFactory.modeSetting$default((SettingContainer)PlayerEsp.INSTANCE, "color-mode", (Enum)ChamsColorMode.RGB, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.default$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "default", false, PlayerEsp.INSTANCE.getTypes(), null, 8, null);
        PlayerEsp.box$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "box", false, PlayerEsp.INSTANCE.getTypes(), null, 8, null);
        PlayerEsp.frame$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "frame", false, PlayerEsp.INSTANCE.getTypes(), null, 8, null);
        PlayerEsp.shaderVal$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "shader", false, PlayerEsp.INSTANCE.getTypes(), null, 8, null);
        PlayerEsp.skeleton$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "skeleton", false, PlayerEsp.INSTANCE.getTypes(), null, 8, null);
        PlayerEsp.extraPart$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "extra-part", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.shaderColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "shader-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.skeletonColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "skeleton-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.skeletonFriendColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "skeleton-friend-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.fillColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "fill", new Color(255, 255, 255, 120), null, null, 12, null);
        PlayerEsp.lineColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "line", new Color(255, 255, 255, 255), null, null, 12, null);
        PlayerEsp.friendFillColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "friend-fill", new Color(0, 255, 0, 120), null, null, 12, null);
        PlayerEsp.friendLineColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "friend-line", new Color(0, 255, 0, 255), null, null, 12, null);
        PlayerEsp.sFillColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "s-fill", new Color(255, 255, 255, 120), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.sLineColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "s-line", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.sFriendFillColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "s-friend-fill", new Color(0, 255, 0, 120), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.sFriendLineColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "s-friend-line", new Color(0, 255, 0, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.glass$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "glass", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.borders$delegate = SettingFactory.booleanSetting$default((SettingContainer)PlayerEsp.INSTANCE, "border", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.radiusValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "radius", 6.0f, RangesKt.rangeTo(0.1f, 15.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.distanceValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "distance", 1.3f, RangesKt.rangeTo(0.1f, 15.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.glowAlphaValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "glow-alpha", 1.7f, RangesKt.rangeTo(1.0f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.lineWidthValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "line-width", 4.0f, RangesKt.rangeTo(1.0f, 4.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.alpha$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "alpha", 0.2f, RangesKt.rangeTo(0.0f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.secondColor$delegate = SettingFactory.colorSetting$default(PlayerEsp.INSTANCE, "second-color", new Color(255, 255, 255, 255), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)(), 4, null);
        PlayerEsp.glowQualityValue$delegate = SettingFactory.intSetting$default(PlayerEsp.INSTANCE, "glow-quality", 2, new IntRange(1, 4), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.glowRadiusValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "glow-radius", 4.0f, RangesKt.rangeTo(1.0f, 16.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.glowAlphaMultValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "glow-alpha-multiplier", 2.0f, RangesKt.rangeTo(0.0f, 4.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.fillAlphaMultValue$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "fill-alpha-multiplier", 0.6f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible23(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.saturation$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "saturation", 0.7f, RangesKt.rangeTo(0.0f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible24(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.brightness$delegate = SettingFactory.floatSetting$default(PlayerEsp.INSTANCE, "brightness", 2.0f, RangesKt.rangeTo(0.0f, 45.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible25(), ()Ljava/lang/Boolean;)(), 8, null);
        PlayerEsp.hDistance$delegate = SettingFactory.intSetting$default(PlayerEsp.INSTANCE, "max-horizontal-distance", 64, new IntRange(1, 256), null, null, 24, null);
        PlayerEsp.vDistance$delegate = SettingFactory.intSetting$default(PlayerEsp.INSTANCE, "max-vertical-distance", 32, new IntRange(1, 256), null, null, 24, null);
        PlayerEsp.framebuffers = new ShaderFramebuffers();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3991;
        if (ab[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])fb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    fb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = K[n2].getBytes("ISO-8859-1");
            PlayerEsp.ab[n2] = PlayerEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return ab[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x33ED;
        if (hb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = gb[n2];
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
                throw new RuntimeException("su/catlean/k1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlayerEsp.hb[n2] = n3;
        }
        return hb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlayerEsp.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlayerEsp.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

