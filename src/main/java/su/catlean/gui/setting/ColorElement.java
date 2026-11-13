/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui.setting;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.ClickGui;
import su.catlean.gui.SettingElement;
import su.catlean.gui.SettingSlot;
import su.catlean.module.Module;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ColorElement
extends SettingElement {
    @NotNull
    private final Setting setting;
    private boolean expanded;
    private boolean O;
    private boolean Z;
    private boolean h;
    @NotNull
    private final Animation expandAnimation;
    @NotNull
    private final Animation r;
    @NotNull
    private final Animation B;
    @NotNull
    private final ArrayList slots;
    private static final long b;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] k;
    private static final Map l;
    private static final long[] m;
    private static final Long[] n;
    private static final Map o;

    public ColorElement(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super(setting2);
        this.setting = setting2;
        this.expandAnimation = new Animation(Easing.OUT_QUINT, 300L);
        this.r = new Animation(Easing.OUT_QUINT, 300L);
        this.B = new Animation(Easing.OUT_QUINT, 300L);
        this.slots = new ArrayList();
        int n = 4;
        int n2 = 0;
        while (n2 < n) {
            int n3 = n2++;
            this.slots.add(new SettingSlot(this.getSetting(), this.getX(), this.getY(), n3));
        }
    }

    @Override
    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4, float var6_5) {
        v0 = var4_4;
        var9_6 = v0 ^ 68939168652928L;
        var11_7 = v0 ^ 115096991696004L;
        var18_8 = v0 ^ 133532887051749L;
        v1 = v0 ^ 20797107207379L;
        var20_9 = (int)(v1 >>> 48);
        var21_10 = (int)(v1 << 16 >>> 48);
        var22_11 = (int)(v1 << 32 >>> 32);
        var23_12 = v0 ^ 81750728905703L;
        v2 = v0 ^ 88672139111771L;
        var25_13 = (int)(v2 >>> 48);
        var26_14 = (int)(v2 << 16 >>> 32);
        var27_15 = (int)(v2 << 48 >>> 48);
        var28_16 = v0 ^ 105309425727100L;
        var32_17 = v0 ^ 24369959771588L;
        var34_18 = v0 ^ 120682356120742L;
        var36_19 = v0 ^ 135435160666308L;
        new Module[0];
        Intrinsics.checkNotNullParameter(var1_1, "context");
        v3 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v3, "pose(...)");
        var54_20 = v3;
        v4 = Fonts.getSF_M3();
        v5 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        FontRenderer.drawString$default(v4, v5, this.getSetting().Q(), this.getX(), this.getY(), var11_7, Theme.INSTANCE.getTextColor(), false, null);
        this.setHeight(this.expanded != false ? 65.0f : 14.0f);
        this.expandAnimation.animate(this.expanded != false ? 1.0f : 0.0f);
        var55_21 = 1.0f - this.expandAnimation.getValue();
        if (!this.expanded) ** GOTO lbl-1000
        var56_22 = this.getX();
        var57_24 = this.getX() + 25.0f;
        var58_26 = var2_2;
        v6 = var56_22 <= var58_26 ? var58_26 <= var57_24 : false;
        if (!v6) ** GOTO lbl-1000
        var56_22 = this.getY() + 24.0f;
        var57_24 = this.getY() + 34.0f;
        var58_26 = var3_3;
        v7 = var56_22 <= var58_26 ? var58_26 <= var57_24 : false;
        if (v7) {
            v8 = 1.0f;
        } else lbl-1000:
        // 3 sources

        {
            v8 = 0.0f;
        }
        this.r.animate(v8);
        if (!this.expanded) ** GOTO lbl-1000
        var56_22 = this.getX();
        var57_24 = this.getX() + 25.0f;
        var58_26 = var2_2;
        v9 = var56_22 <= var58_26 ? var58_26 <= var57_24 : false;
        if (!v9) ** GOTO lbl-1000
        var56_22 = this.getY() + 38.0f;
        var57_24 = this.getY() + 47.0f;
        var58_26 = var3_3;
        v10 = var56_22 <= var58_26 ? var58_26 <= var57_24 : false;
        if (v10) {
            v11 = 1.0f;
        } else lbl-1000:
        // 3 sources

        {
            v11 = 0.0f;
        }
        this.B.animate(v11);
        var56_23 = new float[3];
        Color.RGBtoHSB(((Color)this.getSetting().getValue()).getRed(), ((Color)this.getSetting().getValue()).getGreen(), ((Color)this.getSetting().getValue()).getBlue(), var56_23);
        Shapes2D.drawShape$default(var54_20, (char)var20_9, this.getX() + this.getWidth() - 18.0f, this.getY() + 11.0f * this.expandAnimation.getValue(), 11.0f, 11.0f * var55_21, 3.0f, 2.5f, 1.0f, 1.0f, (Color)this.getSetting().getValue(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)var21_10, 0.0f, var22_11, 0.0f, 14336, null);
        if (this.expanded) {
            v12 = ClickGui.INSTANCE.r();
            v13 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
            var64_30 = this.expandAnimation.getValue();
            RenderTarget.drawRoundedText$default(v12, (Matrix3x2f)v13, (double)(this.getX() + this.getWidth()) - 17.0, (double)this.getY() - 2.0, 0.0f, 0.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), var64_30), null, 0.0f, 216, var32_17, null);
            var38_31 = null;
            var40_32 = null;
            var41_34 = 0.0f;
            var42_35 = var54_20;
            var43_37 = new Color(Color.HSBtoRGB(var56_23[0], 1.0f, 1.0f));
            var44_38 = new Color(0);
            Gradient2D.E(this.getX() + this.getWidth() - 48.0f * this.expandAnimation.getValue(), this.getY() + 10.0f * this.expandAnimation.getValue(), 40.0f * this.expandAnimation.getValue(), 39.5f * this.expandAnimation.getValue(), 3.0f, new Color(0), new Color(-1), var28_16, var44_38, var43_37, var42_35, var41_34, var40_32, var38_31);
            var57_25 = this.slots;
            var59_39 = var57_25.iterator();
            while (var59_39.hasNext()) {
                var60_41 /* !! */  = var59_39.next();
                var61_44 = (SettingSlot)var60_41 /* !! */ ;
                var61_44.setX(this.getX());
                var61_44.setY(this.getY());
                var61_44.render(var9_6, var54_20, var1_1);
            }
            var59_39 = "%02X%02X%02X";
            var60_41 /* !! */  = new Object[]{((Color)this.getSetting().getValue()).getRed(), ((Color)this.getSetting().getValue()).getGreen(), ((Color)this.getSetting().getValue()).getBlue()};
            v14 = String.format((String)var59_39, Arrays.copyOf(var60_41 /* !! */ , ((T)var60_41 /* !! */ ).length));
            Intrinsics.checkNotNullExpressionValue(v14, "format(...)");
            var57_25 = v14;
            var58_27 = 3;
            for (var59_40 = 0; var59_40 < var58_27; ++var59_40) {
                var60_42 = var59_40;
                v15 = this.getX();
                v16 = this.getY() + 10.0f + (float)var60_42 * 14.0f;
                switch (var60_42) {
                    case 1: {
                        v17 = this.r.getValue();
                        break;
                    }
                    case 2: {
                        v17 = this.B.getValue();
                        break;
                    }
                    default: {
                        v17 = 0.0f;
                    }
                }
                v18 = 2.0f + v17;
                switch (var60_42) {
                    case 1: {
                        v19 = this.r.getValue();
                        break;
                    }
                    case 2: {
                        v19 = this.B.getValue();
                        break;
                    }
                    default: {
                        v19 = 0.0f;
                    }
                }
                var38_31 = null;
                var39_45 = 14336;
                var40_33 = 0.0f;
                var41_34 = 0.0f;
                var42_36 = 0.0f;
                var43_37 = Theme.INSTANCE.getSlidersColor();
                var44_38 = Theme.INSTANCE.getModuleColor();
                var45_46 = 1.0f;
                var46_47 = 0.5f * v19;
                var47_48 = v18;
                var48_49 = 2.0f;
                var49_50 = 11.0f;
                var50_51 = 27.0f;
                var51_52 = v16;
                var52_53 = v15;
                Shapes2D.drawShape$default(var54_20, (char)var20_9, var52_53, var51_52, var50_51, var49_50, var48_49, var47_48, var46_47, var45_46, var44_38, var43_37, var42_36, (short)var21_10, var41_34, var22_11, var40_33, var39_45, var38_31);
                var62_54 = new String[]{var57_25, "Copy", "Paste"};
                Fonts.getSF_M4().drawStringCentered(var1_1, CollectionsKt.listOf(var62_54).get(var60_42), this.getX() + 13.5f, this.getY() + 14.5f + (float)var60_42 * 14.0f, Theme.INSTANCE.getTextColor(), var34_18);
            }
            v20 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v20, "pose(...)");
            var58_28 = new Color[]{RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), 0.0f), RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), 0.0f), RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), 1.0f), RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), 1.0f)};
            RenderUtil.INSTANCE.drawGradient(v20, this.getX() + 61.0f, this.getY() + 10.0f, 2.0f, 39.0f, var58_28, var36_19);
            var58_29 = 1;
            do {
                var59_40 = var58_29++;
                var60_43 = Math.clamp(1.0f / (107.0f / ((float)var59_40 * this.expandAnimation.getValue() * this.expandAnimation.getValue())), 0.0f, 1.0f);
                var61_44 = Color.getHSBColor(var60_43, 1.0f, 1.0f);
                v21 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v21, "pose(...)");
                v22 = this.getX() + (float)var59_40;
                v23 = this.getY() + 55.0f;
                var62_54 = new Color[4];
                Intrinsics.checkNotNull(var61_44);
                var62_54[0] = var61_44;
                var62_54[1] = var61_44;
                var62_54[2] = var61_44;
                var62_54[3] = var61_44;
                RenderUtil.INSTANCE.drawGradient(v21, v22, v23, 1.0f, 2.0f, (Color[])var62_54, var36_19);
            } while (var58_29 <= 107);
            this.drawAlphaBar((short)var25_13, var54_20, var2_2, var26_14, (short)var27_15);
            this.drawPicker(var18_8, var54_20, var2_2, var3_3);
            this.drawHueBar(var54_20, var3_3, var23_12);
        }
    }

    private final void drawPicker(long l, Matrix3x2fStack matrix3x2fStack, int n, int n2) {
        long l2 = (l = b ^ l) ^ 0x12476642E65CL;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 16 >>> 48);
        int n5 = (int)(l2 << 32 >>> 32);
        float f = this.getX() + this.getWidth() - 48.0f + 2.0f;
        float f2 = this.getY() + 10.0f + 2.0f;
        float[] fArray = new float[3];
        Color.RGBtoHSB(((Color)this.getSetting().getValue()).getRed(), ((Color)this.getSetting().getValue()).getGreen(), ((Color)this.getSetting().getValue()).getBlue(), fArray);
        float f3 = f2 - 4.0f + 40.0f * (1.0f - fArray[2]);
        Color color = (Color)this.getSetting().getValue();
        Color color2 = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color2, "BLACK");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, f - 4.0f + 40.0f * fArray[1], f3, 7.0f, 7.0f, 2.5f, 1.5f, 1.0f, 1.0f, color, color2, 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        if (this.O) {
            float f4 = Math.clamp(((float)n - f) / 40.0f, 0.0f, 1.0f);
            float f5 = Math.clamp(1.0f - ((float)n2 - f2) / 40.0f, 0.0f, 1.0f);
            float f6 = (float)((Color)this.getSetting().getValue()).getAlpha() / 255.0f;
            this.getSetting().setValue(RenderUtil.INSTANCE.withAlpha(new Color(Color.HSBtoRGB(fArray[0], f4, f5)), f6));
        }
    }

    private final void drawHueBar(Matrix3x2fStack matrix3x2fStack, int n, long l) {
        long l2 = (l = b ^ l) ^ 0x216FF926985EL;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        float f = this.getY() + 11.0f;
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n2, this.getX() + 58.0f, f - 4.0f + 38.0f * (1.0f - (float)((Color)this.getSetting().getValue()).getAlpha() / 255.0f), 7.0f, 7.0f, 2.5f, 1.0f, 1.0f, 1.0f, (Color)this.getSetting().getValue(), RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), 1.0f), 0.0f, (short)n3, 0.0f, n4, 0.0f, 14336, null);
        if (this.Z) {
            float f2 = Math.clamp(1.0f - ((float)n - f) / 39.0f, 0.0f, 1.0f);
            this.getSetting().setValue(RenderUtil.INSTANCE.withAlpha((Color)this.getSetting().getValue(), f2));
        }
    }

    private final void drawAlphaBar(short s, Matrix3x2fStack matrix3x2fStack, int n, int n2, short s2) {
        block3: {
            int n3;
            float[] fArray;
            float f;
            block2: {
                long l = ((long)s << 48 | (long)n2 << 32 >>> 16 | (long)s2 << 48 >>> 48) ^ b;
                long l2 = l ^ 0x3B907D54E6E2L;
                int n4 = (int)(l2 >>> 48);
                int n5 = (int)(l2 << 16 >>> 48);
                int n6 = (int)(l2 << 32 >>> 32);
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5051133679507046788L, (long)l);
                f = this.getX();
                fArray = new float[3];
                Color.RGBtoHSB(((Color)this.getSetting().getValue()).getRed(), ((Color)this.getSetting().getValue()).getGreen(), ((Color)this.getSetting().getValue()).getBlue(), fArray);
                CallSite callSite2 = callSite;
                Shapes2D.drawShape$default(matrix3x2fStack, (char)n4, f - 3.5f + (float)ColorElement.zkmBootstrap2("y", (int)22775, (long)(0x46BC2416929FF449L ^ l)) * fArray[0], this.getY() + 52.0f, 7.0f, 7.0f, 2.5f, 1.0f, 1.0f, 1.0f, new Color(Color.HSBtoRGB(fArray[0], 1.0f, 1.0f)), new Color(Color.HSBtoRGB(fArray[0], 1.0f, 1.0f)), 0.0f, (short)n5, 0.0f, n6, 0.0f, (int)ColorElement.zkmBootstrap2("y", (int)8676, (long)(0x1F576EA5095D0D5CL ^ l)), null);
                n3 = this.h;
                if (callSite2 == null) break block2;
                if (n3 == 0) break block3;
                n3 = n;
            }
            float f2 = Math.clamp(((float)n3 - f) / 107.0f, 0.0f, 1.0f);
            float f3 = (float)((Color)this.getSetting().getValue()).getAlpha() / 255.0f;
            this.getSetting().setValue(RenderUtil.INSTANCE.withAlpha(new Color(Color.HSBtoRGB(f2, fArray[1], fArray[2])), f3));
        }
    }

    @Override
    public void mouseClicked(long l, double d, double d2, int n) {
        block6: {
            float f;
            block9: {
                block8: {
                    block7: {
                        block5: {
                            Iterable iterable = this.slots;
                            Iterator iterator2 = iterable.iterator();
                            while (iterator2.hasNext()) {
                                Object t = iterator2.next();
                                SettingSlot settingSlot = (SettingSlot)t;
                                settingSlot.isHovered((int)d, (int)d2);
                            }
                            f = this.getX();
                            boolean bl = d <= (double)(this.getX() + this.getWidth()) ? (double)f <= d : false;
                            if (bl) {
                                f = this.getY();
                                boolean bl2 = d2 <= (double)(this.getY() + 8.0f) ? (double)f <= d2 : false;
                                if (bl2) {
                                    this.expanded = !this.expanded;
                                    this.Z = false;
                                    this.h = false;
                                    this.O = false;
                                    return;
                                }
                            }
                            if (!this.expanded) break block5;
                            f = this.getX() + 57.0f;
                            boolean bl3 = d <= (double)(this.getX() + 68.0f) ? (double)f <= d : false;
                            if (!bl3) break block5;
                            f = this.getY() + 11.0f;
                            boolean bl4 = d2 <= (double)(this.getY() + 50.0f) ? (double)f <= d2 : false;
                            if (!bl4) break block5;
                            this.Z = true;
                            break block6;
                        }
                        if (!this.expanded) break block7;
                        f = this.getX();
                        boolean bl = d <= (double)(this.getX() + 108.0f) ? (double)f <= d : false;
                        if (!bl) break block7;
                        f = this.getY() + 53.0f;
                        boolean bl5 = d2 <= (double)(this.getY() + 58.0f) ? (double)f <= d2 : false;
                        if (!bl5) break block7;
                        this.h = true;
                        break block6;
                    }
                    if (!this.expanded) break block8;
                    f = this.getX() + this.getWidth() - 48.0f;
                    boolean bl = d <= (double)(this.getX() + this.getWidth() - 8.0f) ? (double)f <= d : false;
                    if (!bl) break block8;
                    f = this.getY() + 11.0f;
                    boolean bl6 = d2 <= (double)(this.getY() + 51.0f) ? (double)f <= d2 : false;
                    if (!bl6) break block8;
                    this.O = true;
                    break block6;
                }
                if (!this.expanded) break block9;
                f = this.getX();
                boolean bl = d <= (double)(this.getX() + 25.0f) ? (double)f <= d : false;
                if (!bl) break block9;
                f = this.getY() + 24.0f;
                boolean bl7 = d2 <= (double)(this.getY() + 34.0f) ? (double)f <= d2 : false;
                if (!bl7) break block9;
                Theme.INSTANCE.I((Color)this.getSetting().getValue());
                break block6;
            }
            if (!this.expanded) break block6;
            f = this.getX();
            boolean bl = d <= (double)(this.getX() + 25.0f) ? (double)f <= d : false;
            if (bl) {
                f = this.getY() + 38.0f;
                boolean bl8 = d2 <= (double)(this.getY() + 47.0f) ? (double)f <= d2 : false;
                if (bl8) {
                    this.getSetting().setValue(Theme.INSTANCE.l());
                }
            }
        }
    }

    @Override
    public void mouseReleased(double d, double d2) {
        this.O = false;
        this.h = false;
        this.Z = false;
    }

    @Override
    public void keyPressed(int n, short s, int n2, char c) {
        long l = (long)s << 48 | (long)n2 << 32 >>> 16 | (long)c << 48 >>> 48;
        int n3 = (int)((l ^ 0x3FF0CC60DF06L) >>> 56);
        Iterable iterable = this.slots;
        for (Object t : iterable) {
            SettingSlot settingSlot = (SettingSlot)t;
            settingSlot.keyPressed((byte)n3, n);
        }
    }

    @Override
    public void charTyped(char c) {
        Iterable iterable = this.slots;
        for (Object t : iterable) {
            SettingSlot settingSlot = (SettingSlot)t;
            settingSlot.charTyped(c);
        }
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3) {
        if (!this.expanded) {
            return false;
        }
        boolean bl = false;
        Iterable iterable = this.slots;
        for (Object t : iterable) {
            SettingSlot settingSlot = (SettingSlot)t;
            if (!settingSlot.mouseScrolled(d, d2, d3)) continue;
            bl = true;
        }
        return bl;
    }

    @Override
    public void close() {
        this.O = false;
        this.h = false;
        this.Z = false;
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
                                ColorElement.b = ZkmKeys.a(217593705347506475L, 8108681302133724723L, MethodHandles.lookup().lookupClass()).a(134993480702592L);
                                ColorElement.f = new HashMap<K, V>(13);
                                var22 = ColorElement.b ^ 98005831403160L;
                                var24_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var22 >>> 56);
                                for (var25_2 = 1; var25_2 < 8; ++var25_2) {
                                    v2 = v2;
                                    v2[var25_2] = (byte)(var22 << var25_2 * 8 >>> 56);
                                }
                                var24_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var31_3 = new String[9];
                                var29_4 = 0;
                                var28_5 = "\u00c2.\u0014\u00d7\u00ba\u00c3\u00d4\u0099fz\u000b\u00af\u00c3\t%\u0002\u00108\u00e2\u00d8\u0081\u00cc\u00cf\u00b1h[\u00e3'\u00b6\u00b0xr\u0000\u0018zC\u009aO\u0087(\u00abB\u00b8i@\u00f0\u00b5\u00ef\u00d1dC\u00e1UC\u00aeK-\u00ab\u0010H^Cg\u00c9\u00de\u001di\u00c3o\nq\u00f8\u00835\u00fb \u008ciRx4\u000e*#~c\u0084\u00c0w\u00a3\u00de\u00d4V\u0087\u0013\u00c4\u00d0\u00b8g<o\u00c5.i\u00fd\u0019 \f\u0018\u001b6A%t\u00fc\u00cb&\u00e2\u0017\u00abU\u00fbn:\u00ed\u00f5\u0003\u001c\\'\u00f8\u00ca\\\u0010\u0090\u00df\u008b\u0095*\u009c\u00eceWR\u00ec\u000e\u00d748\u00b4";
                                var30_6 = 150;
                                var27_7 = 16;
                                var26_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var26_8;
                                    v4 = var28_5.substring(v3, v3 + var27_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = ColorElement.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    var28_5 = "M\u00b83K\u008bz\u00cbF\u00df@5\u0019\u0086\u00d6\u008f[\u00ebI\u000e\u0019#\u0092?\u0080\u0010\u001b|\t\u00bb\u0015P\u008a\u00ba\u001d\u00e0\u00a5g\u0018P+-";
                                    var30_6 = 41;
                                    var27_7 = 24;
                                    var26_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var26_8;
                                        v4 = var28_5.substring(v6, v6 + var27_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = ColorElement.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var32_9 = var24_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        ColorElement.d = var31_3;
                        ColorElement.e = new String[9];
                        ColorElement.l = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var22 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var22 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[11];
                        var14_13 = 0;
                        var15_14 = "AD\"\u00a6z.B{\u00ba\u00e0\u00efR\f\u00c7AA1\u00df\u0011\\\u00db]\u008f\t\u00c0\u00ad\u00b6\u0096H\u00cb\u00cdi\u00b8\u00bf\u008c\u00b8\u0086\u00f7\u000f\u0085g\u00ba\b:6\u00f3p\u0085\u00d9\u0082\u000b\u0006&\u000f\u00bf\u00d7\fd\u00eb\u00ef\u001f}`\u00a3\u00ea\u008e\u00fc\u009b\u00cfT\u00fd4";
                        var16_15 = 72;
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
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u0005z`\u0005\u00c4\u0015f\u00c3V;\u009e\u00d3 \u00f1\u00d0\u001a";
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
lbl91:
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
lbl104:
                        // 1 sources

                        ** continue;
                    }
                }
                ColorElement.g = var17_12;
                ColorElement.k = new Integer[11];
                ColorElement.o = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var22 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var22 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[2];
                var3_23 = 0;
                var4_24 = "\u00e4\u00eb\u00ae>\u00b1\u0083P\u00b2e\u00ceT\u0094](\u00e9I";
                var5_25 = 16;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl126:
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
        ColorElement.m = var6_22;
        ColorElement.n = new Long[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE7E;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rx", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            ColorElement.e[n2] = ColorElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ColorElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6BBF;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])ColorElement.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    ColorElement.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ColorElement.k[n2] = n3;
        }
        return k[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ColorElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7F5C;
        if (ColorElement.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = m[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])o.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rx", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            ColorElement.n[n2] = l4;
        }
        return ColorElement.n[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = ColorElement.zkmDecryptLong(n, l);
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
            throw new RuntimeException("su/catlean/rx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ColorElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ColorElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(ColorElement.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

