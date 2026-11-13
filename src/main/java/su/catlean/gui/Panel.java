/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui;

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
import su.catlean.gui.Panel$setting$1;
import su.catlean.gui.PropertySlot;
import su.catlean.module.Module;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.ThemePreset;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Panel {
    @NotNull
    private String id;
    @NotNull
    private final ThemePreset theme;
    private float x;
    private float y;
    @NotNull
    private Color setting;
    private boolean expanded;
    private boolean g;
    private boolean n;
    private boolean u;
    private float width;
    private float height;
    @NotNull
    private final Animation expandAnimation;
    @NotNull
    private final Animation p;
    @NotNull
    private final Animation R;
    @NotNull
    private final ArrayList slots;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] j;
    private static final Map k;
    private static final long[] l;
    private static final Long[] m;
    private static final Map o;

    public Panel(@NotNull String string, @NotNull ThemePreset themePreset, float f, float f2, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(themePreset, "theme");
        Intrinsics.checkNotNullParameter(color, "setting");
        this.id = string;
        this.theme = themePreset;
        this.x = f;
        this.y = f2;
        this.setting = color;
        this.width = 116.0f;
        this.height = 14.0f;
        this.expandAnimation = new Animation(Easing.OUT_QUINT, 300L);
        this.p = new Animation(Easing.OUT_QUINT, 300L);
        this.R = new Animation(Easing.OUT_QUINT, 300L);
        this.slots = new ArrayList();
        int n = 4;
        int n2 = 0;
        while (n2 < n) {
            int n3;
            int n4 = n3 = n2++;
            float f3 = this.y;
            this.slots.add(new PropertySlot(new Panel$setting$1(this), this.x, f3, n4));
        }
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String string) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Panel.zkmBootstrap("s", (int)20302, (long)(0x2479C33C68CDA2B0L ^ l))));
        this.id = string;
    }

    @NotNull
    public final ThemePreset getTheme() {
        return this.theme;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    @NotNull
    public final Color getSetting() {
        return this.setting;
    }

    public final void setSetting(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.setting = color;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void render(@NotNull DrawContext var1_1, long var2_2, int var4_3, int var5_4) {
        v0 = var2_2 = Panel.b ^ var2_2;
        var6_5 = v0 ^ 107014974328352L;
        v1 = v0 ^ 94830250347726L;
        var8_6 = (int)(v1 >>> 48);
        var9_7 = (int)(v1 << 16 >>> 32);
        var10_8 = (int)(v1 << 48 >>> 48);
        var16_9 = v0 ^ 99504636241724L;
        var23_10 = v0 ^ 98691514261676L;
        v2 = v0 ^ 65910939094003L;
        var25_11 = (int)(v2 >>> 48);
        var26_12 = (int)(v2 << 16 >>> 48);
        var27_13 = (int)(v2 << 32 >>> 32);
        v3 = v0 ^ 2391420136203L;
        var28_14 = (int)(v3 >>> 48);
        var29_15 = v3 << 16 >>> 16;
        var31_16 = v0 ^ 130701774684508L;
        var37_17 = v0 ^ 69484802534116L;
        var39_18 = v0 ^ 75706025700230L;
        var41_19 = v0 ^ 90389039495140L;
        new Module[0];
        Intrinsics.checkNotNullParameter(var1_1, "context");
        v4 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
        var59_20 = v4;
        var69_21 = new Object[]{};
        var71_22 = "hub.theme-editor." + this.id;
        Fonts.getSF_M3().drawStringWithShadow(var1_1, I18n.INSTANCE.translate(var71_22, var69_21), var16_9, this.x - 1.0f, this.y - 1.0f, Theme.INSTANCE.getTextColor());
        this.height = this.expanded != false ? 65.0f : 14.0f;
        this.expandAnimation.animate(this.expanded != false ? 1.0f : 0.0f);
        var60_23 = 1.0f - this.expandAnimation.getValue();
        if (!this.expanded) ** GOTO lbl-1000
        var61_24 = this.x;
        var62_26 = this.x + 25.0f;
        var63_28 = var4_3;
        v5 = var61_24 <= var63_28 ? var63_28 <= var62_26 : false;
        if (!v5) ** GOTO lbl-1000
        var61_24 = this.y + 24.0f;
        var62_26 = this.y + 34.0f;
        var63_28 = var5_4;
        v6 = var61_24 <= var63_28 ? var63_28 <= var62_26 : false;
        if (v6) {
            v7 = 1.0f;
        } else lbl-1000:
        // 3 sources

        {
            v7 = 0.0f;
        }
        this.p.animate(v7);
        if (!this.expanded) ** GOTO lbl-1000
        var61_24 = this.x;
        var62_26 = this.x + 25.0f;
        var63_28 = var4_3;
        v8 = var61_24 <= var63_28 ? var63_28 <= var62_26 : false;
        if (!v8) ** GOTO lbl-1000
        var61_24 = this.y + 38.0f;
        var62_26 = this.y + 47.0f;
        var63_28 = var5_4;
        v9 = var61_24 <= var63_28 ? var63_28 <= var62_26 : false;
        if (v9) {
            v10 = 1.0f;
        } else lbl-1000:
        // 3 sources

        {
            v10 = 0.0f;
        }
        this.R.animate(v10);
        var61_25 = new float[3];
        Color.RGBtoHSB(this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), var61_25);
        v11 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v11, "pose(...)");
        Shapes2D.drawShape$default(v11, (char)var25_11, this.x + this.width - 18.0f, this.y + 11.0f * this.expandAnimation.getValue(), 11.0f, 11.0f * var60_23, 3.0f, 2.5f, 1.0f, 1.0f, this.setting, Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)var26_12, 0.0f, var27_13, 0.0f, 14336, null);
        if (this.expanded) {
            v12 = ClickGui.INSTANCE.r();
            v13 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
            var74_32 = this.expandAnimation.getValue();
            RenderTarget.drawRoundedText$default(v12, (Matrix3x2f)v13, (double)(this.x + this.width) - 17.0, (double)this.y - 2.0, 0.0f, 0.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), var74_32), null, 0.0f, 216, var37_17, null);
            var43_33 = null;
            var45_34 = null;
            var46_36 = 0.0f;
            var47_37 = var59_20;
            var48_39 = new Color(Color.HSBtoRGB(var61_25[0], 1.0f, 1.0f));
            var49_40 = new Color(0);
            Gradient2D.E(this.x + this.width - 48.0f * this.expandAnimation.getValue(), this.y + 10.0f * this.expandAnimation.getValue(), 40.0f * this.expandAnimation.getValue(), 39.5f * this.expandAnimation.getValue(), 3.0f, new Color(0), new Color(-1), var31_16, var49_40, var48_39, var47_37, var46_36, var45_34, var43_33);
            var62_27 = this.slots;
            var64_41 = var62_27.iterator();
            while (var64_41.hasNext()) {
                var65_44 /* !! */  = var64_41.next();
                var66_47 = (Color[])var65_44 /* !! */ ;
                var66_47.setX(this.x);
                var66_47.setY(this.y);
                var66_47.render((char)var8_6, var59_20, var9_7, (char)var10_8, var1_1);
            }
            var64_41 = "%02X%02X%02X";
            var65_44 /* !! */  = new Object[]{this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue()};
            v14 = String.format((String)var64_41, Arrays.copyOf(var65_44 /* !! */ , ((T)var65_44 /* !! */ ).length));
            Intrinsics.checkNotNullExpressionValue(v14, "format(...)");
            var62_27 = v14;
            var63_29 = 3;
            for (var64_42 = 0; var64_42 < var63_29; ++var64_42) {
                var65_45 = var64_42;
                v15 = this.y + 10.0f + (float)var65_45 * 14.0f;
                switch (var65_45) {
                    case 1: {
                        v16 = this.p.getValue();
                        break;
                    }
                    case 2: {
                        v16 = this.R.getValue();
                        break;
                    }
                    default: {
                        v16 = 0.0f;
                    }
                }
                v17 = 2.0f + v16;
                switch (var65_45) {
                    case 1: {
                        v18 = this.p.getValue();
                        break;
                    }
                    case 2: {
                        v18 = this.R.getValue();
                        break;
                    }
                    default: {
                        v18 = 0.0f;
                    }
                }
                var43_33 = null;
                var44_49 = 14336;
                var45_35 = 0.0f;
                var46_36 = 0.0f;
                var47_38 = 0.0f;
                var48_39 = Theme.INSTANCE.getSlidersColor();
                var49_40 = Theme.INSTANCE.getModuleColor();
                var50_50 = 1.0f;
                var51_51 = 0.5f * v18;
                var52_52 = v17;
                var53_53 = 2.0f;
                var54_54 = 11.0f;
                var55_55 = 27.0f;
                var56_56 = v15;
                var57_57 = this.x;
                Shapes2D.drawShape$default(var59_20, (char)var25_11, var57_57, var56_56, var55_55, var54_54, var53_53, var52_52, var51_51, var50_50, var49_40, var48_39, var47_38, (short)var26_12, var46_36, var27_13, var45_35, var44_49, var43_33);
                var67_48 = new String[]{var62_27, "Copy", "Paste"};
                Fonts.getSF_M4().drawStringCentered(var1_1, CollectionsKt.listOf(var67_48).get(var65_45), this.x + 13.5f, this.y + 14.5f + (float)var65_45 * 14.0f, Theme.INSTANCE.getTextColor(), var39_18);
            }
            v19 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v19, "pose(...)");
            var63_30 = new Color[]{RenderUtil.INSTANCE.withAlpha(this.setting, 0.0f), RenderUtil.INSTANCE.withAlpha(this.setting, 0.0f), RenderUtil.INSTANCE.withAlpha(this.setting, 1.0f), RenderUtil.INSTANCE.withAlpha(this.setting, 1.0f)};
            RenderUtil.INSTANCE.drawGradient(v19, this.x + 61.0f, this.y + 10.0f, 2.0f, 39.0f, var63_30, var41_19);
            for (var63_31 = 1; var63_31 < 108; ++var63_31) {
                var64_43 = Math.clamp(1.0f / (107.0f / ((float)var63_31 * this.expandAnimation.getValue() * this.expandAnimation.getValue())), 0.0f, 1.0f);
                var65_46 = Color.getHSBColor(var64_43, 1.0f, 1.0f);
                v20 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v20, "pose(...)");
                v21 = this.x + (float)var63_31;
                var66_47 = new Color[4];
                Intrinsics.checkNotNull(var65_46);
                var66_47[0] = var65_46;
                var66_47[1] = var65_46;
                var66_47[2] = var65_46;
                var66_47[3] = var65_46;
                RenderUtil.INSTANCE.drawGradient(v20, v21, this.y + 55.0f, 1.0f, 2.0f, var66_47, var41_19);
            }
            this.drawAlphaBar(var59_20, (char)var28_14, var4_3, var29_15);
            this.drawColorPicker(var59_20, var4_3, var23_10, var5_4);
            this.drawHueBar(var59_20, var6_5, var5_4);
        }
        this.applyTheme(this.theme);
    }

    private final void drawColorPicker(Matrix3x2fStack matrix3x2fStack, int n, long l, int n2) {
        long l2 = (l = b ^ l) ^ 0x206DFA671864L;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 16 >>> 48);
        int n5 = (int)(l2 << 32 >>> 32);
        float f = this.x + this.width - 48.0f + 2.0f;
        float f2 = this.y + 10.0f + 2.0f;
        float[] fArray = new float[3];
        Color.RGBtoHSB(this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), fArray);
        float f3 = f2 - 4.0f + 40.0f * (1.0f - fArray[2]);
        Color color = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color, "BLACK");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, f - 4.0f + 40.0f * fArray[1], f3, 7.0f, 7.0f, 2.5f, 1.5f, 1.0f, 1.0f, this.setting, color, 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        if (this.g) {
            float f4 = Math.clamp(((float)n - f) / 40.0f, 0.0f, 1.0f);
            float f5 = Math.clamp(1.0f - ((float)n2 - f2) / 40.0f, 0.0f, 1.0f);
            float f6 = (float)this.setting.getAlpha() / 255.0f;
            this.setting = RenderUtil.INSTANCE.withAlpha(new Color(Color.HSBtoRGB(fArray[0], f4, f5)), f6);
        }
    }

    private final void drawHueBar(Matrix3x2fStack matrix3x2fStack, long l, int n) {
        long l2 = (l = b ^ l) ^ 0x18FBCF46B6E8L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        float f = this.y + 11.0f;
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n2, this.x + 58.0f, f - 4.0f + 38.0f * (1.0f - (float)this.setting.getAlpha() / 255.0f), 7.0f, 7.0f, 2.5f, 1.0f, 1.0f, 1.0f, this.setting, RenderUtil.INSTANCE.withAlpha(this.setting, 1.0f), 0.0f, (short)n3, 0.0f, n4, 0.0f, 14336, null);
        if (this.n) {
            float f2 = Math.clamp(1.0f - ((float)n - f) / 39.0f, 0.0f, 1.0f);
            this.setting = RenderUtil.INSTANCE.withAlpha(this.setting, f2);
        }
    }

    private final void drawAlphaBar(Matrix3x2fStack matrix3x2fStack, char c, int n, long l) {
        block3: {
            int n2;
            float[] fArray;
            float f;
            block2: {
                long l2 = ((long)c << 48 | l << 16 >>> 16) ^ b;
                long l3 = l2 ^ 0x7B83597E13C3L;
                int n3 = (int)(l3 >>> 48);
                int n4 = (int)(l3 << 16 >>> 48);
                int n5 = (int)(l3 << 32 >>> 32);
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5539910925461073806L, (long)l2);
                f = this.x;
                fArray = new float[3];
                Color.RGBtoHSB(this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), fArray);
                CallSite callSite2 = callSite;
                Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, f - 3.5f + (float)Panel.zkmBootstrap2("d", (int)900, (long)(0x323F6FFFDD56FEB8L ^ l2)) * fArray[0], this.y + 52.0f, 7.0f, 7.0f, 2.5f, 1.0f, 1.0f, 1.0f, new Color(Color.HSBtoRGB(fArray[0], 1.0f, 1.0f)), new Color(Color.HSBtoRGB(fArray[0], 1.0f, 1.0f)), 0.0f, (short)n4, 0.0f, n5, 0.0f, (int)Panel.zkmBootstrap2("d", (int)11916, (long)(0x545B8D330FABD3B4L ^ l2)), null);
                n2 = this.u;
                if (callSite2 == null) break block2;
                if (n2 == 0) break block3;
                n2 = n;
            }
            float f2 = Math.clamp(((float)n2 - f) / 107.0f, 0.0f, 1.0f);
            float f3 = (float)this.setting.getAlpha() / 255.0f;
            this.setting = RenderUtil.INSTANCE.withAlpha(new Color(Color.HSBtoRGB(f2, fArray[1], fArray[2])), f3);
        }
    }

    public final void isHovered(double d, double d2) {
        block7: {
            block10: {
                block9: {
                    block8: {
                        block6: {
                            float f = this.x;
                            boolean bl = d <= (double)(this.x + this.width) ? (double)f <= d : false;
                            if (bl) {
                                f = this.y;
                                boolean bl2 = d2 <= (double)(this.y + 8.0f) ? (double)f <= d2 : false;
                                if (bl2) {
                                    this.expanded = !this.expanded;
                                    this.n = false;
                                    this.u = false;
                                    this.g = false;
                                    return;
                                }
                            }
                            if (!this.expanded) {
                                return;
                            }
                            Iterable iterable = this.slots;
                            for (Object t : iterable) {
                                PropertySlot propertySlot = (PropertySlot)t;
                                propertySlot.isHovered((int)d, (int)d2);
                            }
                            if (!this.expanded) break block6;
                            float f2 = this.x + 57.0f;
                            boolean bl3 = d <= (double)(this.x + 68.0f) ? (double)f2 <= d : false;
                            if (!bl3) break block6;
                            f2 = this.y + 11.0f;
                            boolean bl4 = d2 <= (double)(this.y + 50.0f) ? (double)f2 <= d2 : false;
                            if (!bl4) break block6;
                            this.n = true;
                            break block7;
                        }
                        if (!this.expanded) break block8;
                        float f = this.x;
                        boolean bl = d <= (double)(this.x + 108.0f) ? (double)f <= d : false;
                        if (!bl) break block8;
                        f = this.y + 53.0f;
                        boolean bl5 = d2 <= (double)(this.y + 58.0f) ? (double)f <= d2 : false;
                        if (!bl5) break block8;
                        this.u = true;
                        break block7;
                    }
                    if (!this.expanded) break block9;
                    float f = this.x + this.width - 48.0f;
                    boolean bl = d <= (double)(this.x + this.width - 8.0f) ? (double)f <= d : false;
                    if (!bl) break block9;
                    f = this.y + 11.0f;
                    boolean bl6 = d2 <= (double)(this.y + 51.0f) ? (double)f <= d2 : false;
                    if (!bl6) break block9;
                    this.g = true;
                    break block7;
                }
                if (!this.expanded) break block10;
                float f = this.x;
                boolean bl = d <= (double)(this.x + 25.0f) ? (double)f <= d : false;
                if (!bl) break block10;
                f = this.y + 24.0f;
                boolean bl7 = d2 <= (double)(this.y + 34.0f) ? (double)f <= d2 : false;
                if (!bl7) break block10;
                Theme.INSTANCE.I(this.setting);
                break block7;
            }
            if (!this.expanded) break block7;
            float f = this.x;
            boolean bl = d <= (double)(this.x + 25.0f) ? (double)f <= d : false;
            if (bl) {
                f = this.y + 38.0f;
                boolean bl8 = d2 <= (double)(this.y + 47.0f) ? (double)f <= d2 : false;
                if (bl8) {
                    this.setting = Theme.INSTANCE.l();
                }
            }
        }
    }

    public final void stopDragging() {
        this.g = false;
        this.u = false;
        this.n = false;
    }

    public final void keyPressed(int n) {
        if (this.expanded) {
            Iterable iterable = this.slots;
            for (Object t : iterable) {
                PropertySlot propertySlot = (PropertySlot)t;
                propertySlot.keyPressed(n);
            }
        }
    }

    public final void charTyped(char c) {
        if (this.expanded) {
            Iterable iterable = this.slots;
            for (Object t : iterable) {
                PropertySlot propertySlot = (PropertySlot)t;
                propertySlot.charTyped(c);
            }
        }
    }

    public final void mouseScrolled(double d, double d2, long l, double d3) {
        block7: {
            Panel panel;
            CallSite callSite;
            long l2;
            block6: {
                l2 = (l = b ^ l) ^ 0x4FAFB49C78CFL;
                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2248095543537237087L, (long)l);
                panel = this;
                if (callSite == null) break block6;
                if (!panel.expanded) break block7;
                panel = this;
            }
            Iterable iterable = panel.slots;
            block0: for (Object t : iterable) {
                PropertySlot propertySlot = (PropertySlot)t;
                propertySlot.mouseScrolled(d, l2, d2, d3);
                do {
                    CallSite callSite2 = callSite;
                    if (l > 0L) {
                        if (callSite2 == null) break block0;
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) continue block0;
                } while (l < 0L);
                break;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void applyTheme(ThemePreset themePreset) {
        block26: {
            block30: {
                block29: {
                    block28: {
                        block33: {
                            block27: {
                                block32: {
                                    block25: {
                                        block24: {
                                            block31: {
                                                String string = this.id;
                                                switch (string.hashCode()) {
                                                    case -817598092: {
                                                        if (string.equals("secondary")) break;
                                                        return;
                                                    }
                                                    case -969751984: {
                                                        if (!string.equals("widget-blur")) {
                                                            return;
                                                        }
                                                        break block24;
                                                    }
                                                    case -1106245566: {
                                                        if (!string.equals("outline")) {
                                                            return;
                                                        }
                                                        break block25;
                                                    }
                                                    case -1852378237: {
                                                        if (!string.equals("inactive-module-outline")) {
                                                            return;
                                                        }
                                                        break block26;
                                                    }
                                                    case -525855409: {
                                                        if (!string.equals("inactive-text")) {
                                                            return;
                                                        }
                                                        break block27;
                                                    }
                                                    case 1267332622: {
                                                        if (!string.equals("inactive-module")) {
                                                            return;
                                                        }
                                                        break block28;
                                                    }
                                                    case -1068784020: {
                                                        if (!string.equals("module")) {
                                                            return;
                                                        }
                                                        break block29;
                                                    }
                                                    case 852059617: {
                                                        if (!string.equals("module-outline")) {
                                                            return;
                                                        }
                                                        break block30;
                                                    }
                                                    case 3027047: {
                                                        if (!string.equals("blur")) {
                                                            return;
                                                        }
                                                        break block31;
                                                    }
                                                    case 3556653: {
                                                        if (!string.equals("text")) {
                                                            return;
                                                        }
                                                        break block32;
                                                    }
                                                    case -2119261262: {
                                                        if (!string.equals("sliders")) {
                                                            return;
                                                        }
                                                        break block33;
                                                    }
                                                    case -314765822: {
                                                        if (!string.equals("primary")) return;
                                                        themePreset.setPrimaryColor(this.setting);
                                                        return;
                                                    }
                                                }
                                                themePreset.setSecondaryColor(this.setting);
                                                return;
                                            }
                                            themePreset.setBlurColor(this.setting);
                                            return;
                                        }
                                        themePreset.setWidgetBlurColor(this.setting);
                                        return;
                                    }
                                    themePreset.setOutlineColor(this.setting);
                                    return;
                                }
                                themePreset.setTextColor(this.setting);
                                return;
                            }
                            themePreset.setNonactiveTextColor(this.setting);
                            return;
                        }
                        Color color = this.setting;
                        themePreset.setSlidersColor(color);
                        return;
                    }
                    themePreset.setNonActiveModuleColor(this.setting);
                    return;
                }
                Color color = this.setting;
                themePreset.setModuleColor(color);
                return;
            }
            themePreset.setModuleOutlineColor(this.setting);
            return;
        }
        themePreset.setNonActiveOutlineModuleColor(this.setting);
        return;
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
                                Panel.b = ZkmKeys.a(-6284521952040941729L, -6362170675702915004L, MethodHandles.lookup().lookupClass()).a(233407246976513L);
                                Panel.h = new HashMap<K, V>(13);
                                var22 = Panel.b ^ 98708708520001L;
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
                                var31_3 = new String[26];
                                var29_4 = 0;
                                var28_5 = "\u00db\u008b\u00cfap\u00e8YV\u009e\u00f4\u00edA\u00e7 \u0085\u000e\u00f5w\u0086\u0080`]L\\\u0010\u00e1\u00b9\u00bc\u007fk\n\u00d2R\u0017+\u00be\u001c\b\b\u00d7t\u0018\u00cf\u00cb\u00a1\u000en!\u00fd\u001f\u0016\u00bb1\u0018\u000f\u0002\u00dbUs(a)\u0017\u0084\u0099^\u0010\u009d}S\u00ca\u00d2/\u0001a\u00a6\u00fe\u0089\u00da\u0084J#{\u0010\u009bUa\u0083h\u00ea\u0097Z\u00d5\u0083\u00d21\u001c\u001aQV\u0010l\u0090\u00dc\u00b1\u00e4\u0099\u00ea\u00e9P\u008ec\u000evE\u0093\u00bc\u0010\u00a7D\u00ca\u0089nx\u0014\u00fa\u00f3\u0017\u007fuB\u000b{\u00af '\u001f\u00ee*\u009c\u00a4?\u0002<^\u0000\u0004\u00886\u00a3\u00d9\u00bc\u00e9\u00e0\u001fmH;\u00b3\u0093\u00b6/ {\u0091\u00d1f \u0082\u0090'M?\u0000\u00e2\u00ba6\u0081\u0090\u00ae\r\u0090\u0096\u00d8\u00f6g1\u00e8e\u000b\u00b5\u0002hd\u00fc\u0080\u0007!\u009c\u00f2\u0010p-D\u001eB\u0011fm\u00a2\u00f8\u00fe\u00d8\u00b9]\u00ce_ \u00f8\u00f7\u001ex\u00eb\u0087\u00d3\u00b6f\u00ce\u00af\u0013\u0084\u0004\u00cc\u00c4\u0080xM\u00d0e\u00fb\u00e9A@\u00ab\u00a0\u00af\u00d1\u00b8i#(\u00e3\u00e24#\u00e3\u00f0P\b\u00002b\u00f7\u00039O\u001d\u00e3jH\u0095\u0085\u00163\u00ff\u0082\u0016 \bv\u00edG\u001c\u0006\u0080\u00c9l0\u0005\u000e\u0002\u0010\u008c\u0094\u00cf\u00a6\u0015H\u00e3\u00c7\u00e3\u00c1\u00e9\u00ac\u00e3H\u009dP\u0018\u0092.\u00b1\u00ff\b'i\u0099\u0098\u00eb\u00bfJ\u00d1+k\u00a9g\u00fd\u00b4II\u00fa\u00ec\b\u0010\u00dcb\u00d2\u00d4\u008f\u00d9l\u009c\u00f9\u001f\u00a4\u0095\u0000\u0004H6(\u000f\u00b9\u00c3J\u00cd\u00d3I\u0081\u0087\u0016e5\u00e4z\u00f7Q\u00ef\u00f7\u00cc\u00cd\u0006\u00141\u00d2 \u00c2\u00f0\\\u0016\u0084\u00a1\u00d3\u00ac\u00b1\u00fdd\u00bc\u00fb\u0003l\u0010\u00c7x\u00ae\u00ad\u00a0\u0098m\u0002\u00cf*\u0082T~\u0095LQ\u0018\u00b2\u00a0=\u00a7\u00b5\u00e8\u00b9N\n\u00dd%\u00e8C\u009ah\u001c \u0000\u00f4K\u00a8\u009d[\u00c3 8h\u009a\u00f9Q\u00a3\u0002\u00e1=T\u00c5T*1T\u0095\u0081\u00bc\u0001\u009c\u00ffr\u00cd\u00d1\u00e8\u009d+\u008f\u00ady\u00a1\u00c9\u0010\f\u00c9\u0095\u00ce\u0015\u0092\u0099\u00a4\u00db\u00b6\u00f7\u00cf\u008cy5\u009c\u0010\u00f8\u00a1V>f\u001bW\u00db\u000f[\u00a8I:\u00ba5\u0097\u0010\u0092\u00c1(b\u00db\u007fM\u00b0t\u00ae\u0095-\u00f3z\nk\u0010\u0089\u000fK\u009f\u0014\u00d7\u008b\u0086N\u00b3\u0098`\u00c1\u00c2\u0080\u001a \u00e9\u008e\f\u008a\u009b%(6\u00cck\u00c4\u00db\u00ad\u0018a\u00fa\u008a\u00e9\u00053\u0097\u00ea\u00ccA\u00ff\u00b2&\u00bc\u00a0\u001e^\u00f3";
                                var30_6 = 567;
                                var27_7 = 24;
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
                                    var31_3[var29_4++] = Panel.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    var28_5 = "\u00e1\u001b\u00bf\u001c\u0018\u00a8\u0019\u0090\u0083\u00d8\u00b0@3 \u00ed\u00cf\u0010\u0000\u0081=\u0007G\u00fb\u00d7*xX\u0099\u001d/\u00d0\\\u00c9";
                                    var30_6 = 33;
                                    var27_7 = 16;
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
                                    var31_3[var29_4++] = Panel.zkmDecodeUtf8(var32_9).intern();
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
                        Panel.c = var31_3;
                        Panel.d = new String[26];
                        Panel.k = new HashMap<K, V>(13);
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
                        var17_12 = new long[10];
                        var14_13 = 0;
                        var15_14 = "\u00f8\u00e0\u00f7\u00ebR\u00ba\u007f(\u001e\b\u0082\u00da;B\u00f12\u00ab\f\u00d5.\u008f}s\u0015#_\u00c0v\u00a3\u001d\u00de\u00fe\u0089-E<\u00d6\u00984\u00de:Q\u0011\u0095\u0010\u0084\u0019*\u00db\u0086\u00966]\u000bUE\u00d6\u00c2O\u00bf\u0098k\u00a2,";
                        var16_15 = 64;
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
                            var15_14 = "\u00e5\r\u00d9g\u00c8\u00a1\u009c\"Qj\u00ce\u00f0\u009a\u00ba\u00cb\u0087";
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
                Panel.i = var17_12;
                Panel.j = new Integer[10];
                Panel.o = new HashMap<K, V>(13);
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
                var4_24 = "\u00ea\u0017v|\u00cc5\u00f4\u0014\u00bd\u00d4\u0082r\u00b1\u0090\u00e4f";
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
        Panel.l = var6_22;
        Panel.m = new Long[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x563E;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/wb", exception);
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
            Panel.d[n2] = Panel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Panel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F1B;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/wb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Panel.j[n2] = n3;
        }
        return j[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Panel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5761;
        if (m[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = Panel.l[n2];
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
                throw new RuntimeException("su/catlean/wb", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            Panel.m[n2] = l4;
        }
        return m[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = Panel.zkmDecryptLong(n, l);
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
            throw new RuntimeException("su/catlean/wb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Panel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Panel.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(Panel.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

