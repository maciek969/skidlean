/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 */
package su.catlean.gui;

import java.awt.Color;
import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import su.catlean.config.ConfigStore;
import su.catlean.gui.Panel;
import su.catlean.module.Module;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.ThemePreset;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class ClickGui {
    @NotNull
    public static final ClickGui INSTANCE;
    private static float i;
    private static float x;
    private static float originX;
    private static float originY;
    @Nullable
    private static ThemePreset hoveredPreset;
    @Nullable
    private static ThemePreset selectedPreset;
    @NotNull
    private static List panels;
    private static float scroll;
    @NotNull
    private static String A;
    private static boolean y;
    private static boolean w;
    @NotNull
    private static final Animation E;
    @NotNull
    private static final Animation k;
    @NotNull
    private static final Animation l;
    @NotNull
    private static final RenderTarget m;
    @NotNull
    private static final RenderTarget B;
    private static Module[] X;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long[] h;
    private static final Long[] n;
    private static final Map o;

    private ClickGui() {
    }

    public final float T() {
        return i;
    }

    public final void A(float f) {
        i = f;
    }

    public final float v() {
        return x;
    }

    public final void w(float f) {
        x = f;
    }

    @NotNull
    public final RenderTarget r() {
        return B;
    }

    /*
     * Unable to fully structure code
     */
    public final void render(@NotNull DrawContext var1_1, int var2_2, long var3_3, int var5_4) {
        block13: {
            block12: {
                v0 = var3_3 = ClickGui.a ^ var3_3;
                var10_5 = v0 ^ 14800330375967L;
                var18_6 = v0 ^ 98778104166712L;
                var27_7 = v0 ^ 72519171026239L;
                v1 = v0 ^ 61964527916535L;
                var31_8 = (int)(v1 >>> 48);
                var32_9 = (int)(v1 << 16 >>> 48);
                var33_10 = (int)(v1 << 32 >>> 32);
                var36_11 = v0 ^ 129150931853144L;
                var40_12 = v0 ^ 66636875347168L;
                var42_13 = v0 ^ 78561485829506L;
                var48_14 = v0 ^ 89732556483040L;
                v2 = new Module[]{};
                Intrinsics.checkNotNullParameter(var1_1, "context");
                var65_15 = v2;
                ClickGui.l.animate(ClickGui.y != false ? 1.0f : 0.0f);
                var66_16 = ClickGui.originX + ClickGui.i - 21.0f;
                var67_19 = ClickGui.originX + ClickGui.i - 7.0f;
                var68_22 = var2_2;
                v3 = var66_16 <= var68_22 ? var68_22 <= var67_19 : false;
                if (!v3) ** GOTO lbl-1000
                var66_16 = ClickGui.originY + 31.0f;
                var67_19 = ClickGui.originY + 45.0f;
                var68_22 = var5_4;
                v4 = var66_16 <= var68_22 ? var68_22 <= var67_19 : false;
                if (v4 && ClickGui.y && ClickGui.selectedPreset == null) {
                    v5 = 1.0f;
                } else lbl-1000:
                // 2 sources

                {
                    v5 = 0.0f;
                }
                ClickGui.k.animate(v5);
                v6 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
                Shapes2D.drawShape$default(v6, (char)var31_8, ClickGui.originX, ClickGui.originY, ClickGui.i, ClickGui.x, 5.0f, 0.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
                v7 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v7, "pose(...)");
                var67_19 = ClickGui.originX + 5.0f;
                var68_22 = ClickGui.originX + 20.0f;
                var69_24 = var2_2;
                v8 = var67_19 <= var69_24 ? var69_24 <= var68_22 : false;
                if (!v8) ** GOTO lbl-1000
                var67_19 = ClickGui.originY + 6.0f;
                var68_22 = ClickGui.originY + 21.0f;
                var69_24 = var5_4;
                v9 = var67_19 <= var69_24 ? var69_24 <= var68_22 : false;
                if (v9) {
                    v10 = Theme.INSTANCE.g().brighter().brighter().brighter().brighter().brighter();
                } else lbl-1000:
                // 2 sources

                {
                    v10 = Theme.INSTANCE.getOutlineColor().brighter();
                }
                var66_17 = v10;
                Intrinsics.checkNotNull(var66_17);
                v11 = Theme.INSTANCE.getOutlineColor().brighter();
                Intrinsics.checkNotNullExpressionValue(v11, "brighter(...)");
                var50_28 = null;
                var51_29 = 14336;
                var52_30 = 0.0f;
                var53_31 = 0.0f;
                var54_32 = 0.0f;
                var55_33 = v11;
                var56_34 = var66_17;
                var57_35 = 1.0f;
                var58_36 = 0.0f;
                var59_37 = 1.0f;
                var60_38 = 3.0f;
                var61_39 = 15.0f;
                var62_40 = 15.0f;
                var63_41 = ClickGui.originY + 6.0f;
                var64_42 = ClickGui.originX + 5.0f;
                Shapes2D.drawShape$default(v7, (char)var31_8, var64_42, var63_41, var62_40, var61_39, var60_38, var59_37, var58_36, var57_35, (Color)var56_34, var55_33, var54_32, (short)var32_9, var53_31, var33_10, var52_30, var51_29, var50_28);
                v12 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v12, "pose(...)");
                RenderTarget.drawRoundedText$default(ClickGui.m, (Matrix3x2f)v12, (double)ClickGui.originX + 7.5, (double)ClickGui.originY + 7.5, 0.0f, 0.0f, null, null, 0.0f, 248, var40_12, null);
                Fonts.getSF_SB().drawStringRight(var10_5, var1_1, "Theme Editor", ClickGui.originX + ClickGui.i / 2.0f, ClickGui.originY + 5.0f, 10);
                v13 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
                var66_17 = new Color[]{new Color(0x44FFFFFF, true)};
                RenderUtil.INSTANCE.drawGradient(v13, ClickGui.originX + 6.0f, ClickGui.originY + 24.0f, ClickGui.i - 12.0f, 0.5f, (Color[])var66_17, var48_14);
                ClickGui.hoveredPreset = null;
                if (ClickGui.selectedPreset != null) break block12;
                v14 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
                Shapes2D.drawShape$default(v14, (char)var31_8, ClickGui.originX + 4.0f, ClickGui.originY + 28.0f, ClickGui.i - 8.0f, 20.0f, 4.0f, 0.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
                v15 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v15, "pose(...)");
                Shapes2D.drawShape$default(v15, (char)var31_8, ClickGui.originX + ClickGui.i - 21.0f, ClickGui.originY + 31.0f, 14.0f, 14.0f, 3.0f, 0.0f, 0.2f + ClickGui.k.getValue() / 10.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
                v16 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v16, "pose(...)");
                var66_17 = new Color[]{Theme.INSTANCE.getInactiveTextColor()};
                RenderUtil.INSTANCE.drawGradient(v16, ClickGui.originX + ClickGui.i - 14.0f, ClickGui.originY + 35.0f, 1.0f, 6.0f, (Color[])var66_17, var48_14);
                v17 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v17, "pose(...)");
                var66_17 = new Color[]{Theme.INSTANCE.getInactiveTextColor()};
                RenderUtil.INSTANCE.drawGradient(v17, ClickGui.originX + ClickGui.i - 16.5f, ClickGui.originY + 37.5f, 6.0f, 1.0f, (Color[])var66_17, var48_14);
                Fonts.getSF_M2().drawStringWithShadow(var1_1, ClickGui.A + (System.currentTimeMillis() % 1000L > 500L && ClickGui.y != false ? "_" : ""), var18_6, ClickGui.originX + 10.0f, ClickGui.originY + 31.5f, RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveTextColor(), Theme.INSTANCE.getTextColor(), ClickGui.l.getValue()));
                ClickGui.x = (float)(ConfigStore.INSTANCE.getThemeConfigFile().Z().size() * 24) + 52.0f;
                var66_17 = ConfigStore.INSTANCE.getThemeConfigFile().Z();
                var68_23 = 0;
                var69_25 = var66_17.iterator();
                while (var69_25.hasNext()) {
                    var70_43 = var69_25.next();
                    if ((var71_45 = var68_23++) < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    var72_47 = (ThemePreset)var70_43;
                    var73_48 = var71_45;
                    var75_49 = ClickGui.originX + 4.0f;
                    var76_50 = ClickGui.originX + 4.0f + ClickGui.i - 8.0f;
                    var77_51 = var2_2;
                    v18 = var75_49 <= var77_51 ? var77_51 <= var76_50 : false;
                    if (v18) {
                        var75_49 = ClickGui.originY + 52.0f + (float)(var73_48 * 24);
                        var76_50 = ClickGui.originY + 72.0f + (float)(var73_48 * 24);
                        var77_51 = var5_4;
                        v19 = var75_49 <= var77_51 ? var77_51 <= var76_50 : false;
                        if (v19) {
                            ClickGui.hoveredPreset = var72_47;
                        }
                    }
                    v20 = var1_1.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(v20, "pose(...)");
                    Shapes2D.drawShape$default(v20, (char)var31_8, ClickGui.originX + 4.0f, ClickGui.originY + 52.0f + (float)(var73_48 * 24), ClickGui.i - 8.0f, 20.0f, 4.0f, 2.0f, 0.0f, 1.0f, var72_47.getBlurColor(), var72_47.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
                    var75_49 = Intrinsics.areEqual(ConfigStore.INSTANCE.getThemeConfigFile().getCurrent(), var72_47) != false ? 0.55f : (Intrinsics.areEqual(ClickGui.hoveredPreset, var72_47) != false ? 0.5f : 0.4f);
                    v21 = ClickGui.originY + 52.0f + (float)(var73_48 * 24);
                    v22 = RenderUtil.INSTANCE.withAlpha(var72_47.getPrimaryColor(), var75_49);
                    v23 = RenderUtil.INSTANCE.withAlpha(var72_47.getSecondaryColor(), var75_49);
                    v24 = RenderUtil.INSTANCE.withAlpha(var72_47.getOutlineColor(), var75_49);
                    v25 = var1_1.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(v25, "pose(...)");
                    Gradient2D.E(ClickGui.originX + 4.0f, v21, ClickGui.i - 8.0f, 20.0f, 4.0f, v22, v23, var36_11, RenderUtil.INSTANCE.withAlpha(var72_47.getBlurColor(), var75_49), v24, v25, 0.0f, null, null);
                    Fonts.getSF_M2().drawStringCentered(var1_1, var72_47.getId(), ClickGui.originX + 4.0f + (ClickGui.i / 2.0f - 4.0f), ClickGui.originY + 61.0f + (float)(var73_48 * 24), var72_47.getTextColor(), var42_13);
                }
                break block13;
            }
            v26 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v26, "pose(...)");
            v27 = ClickGui.selectedPreset;
            Intrinsics.checkNotNull(v27);
            v28 = v27.getBlurColor();
            v29 = ClickGui.selectedPreset;
            Intrinsics.checkNotNull(v29);
            Shapes2D.drawShape$default(v26, (char)var31_8, ClickGui.originX + 4.0f, ClickGui.originY + 28.0f, ClickGui.i - 8.0f, 20.0f, 4.0f, 0.0f, 0.1f, 1.0f, v28, v29.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
            v30 = Fonts.getSF_M2();
            v31 = ClickGui.selectedPreset;
            Intrinsics.checkNotNull(v31);
            v32 = v31.getId();
            v33 = ClickGui.selectedPreset;
            Intrinsics.checkNotNull(v33);
            v30.drawStringWithShadow(var1_1, v32, var18_6, ClickGui.originX + 10.0f, ClickGui.originY + 31.5f, RenderUtil.INSTANCE.withAlpha(v33.getPrimaryColor(), 0.5f));
            v34 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v34, "pose(...)");
            Shapes2D.drawShape$default(v34, (char)var31_8, ClickGui.originX + ClickGui.i - 19.0f, ClickGui.originY + 33.0f, 10.5f, 10.5f, 2.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
            v35 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v35, "pose(...)");
            RenderTarget.drawRoundedText$default(ClickGui.B, (Matrix3x2f)v35, (double)(ClickGui.originX + ClickGui.i) - 19.0, (double)ClickGui.originY + 32.0, 0.0f, 0.0f, Theme.INSTANCE.getTextColor(), null, 0.0f, 216, var40_12, null);
            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(ClickGui.originX, ClickGui.originY + 52.0f, ClickGui.originX + ClickGui.i, ClickGui.originY + ClickGui.x));
            var66_18 = 0.0f;
            var67_20 = ClickGui.panels;
            for (T var70_44 : var67_20) {
                var71_46 = (Panel)var70_44;
                var71_46.setX(ClickGui.originX + 5.0f);
                var71_46.setY(ClickGui.originY + 52.0f + var66_18 + ClickGui.scroll);
                var71_46.render(var1_1, var27_7, var2_2, var5_4);
                var66_18 += var71_46.getHeight() + 2.0f;
            }
            var67_21 = ClickGui.originX + 5.0f;
            var68_22 = ClickGui.originX + 115.0f;
            var69_27 = var2_2;
            v36 = var67_21 <= var69_27 ? var69_27 <= var68_22 : false;
            if (!v36) ** GOTO lbl-1000
            var67_21 = ClickGui.originY + 52.0f + var66_18 + ClickGui.scroll;
            var68_22 = ClickGui.originY + 64.0f + var66_18 + ClickGui.scroll;
            var69_27 = var5_4;
            v37 = var67_21 <= var69_27 ? var69_27 <= var68_22 : false;
            if (v37) {
                v38 = true;
            } else lbl-1000:
            // 2 sources

            {
                v38 = false;
            }
            ClickGui.w = v38;
            ClickGui.E.animate(ClickGui.w != false ? 1.0f : 0.0f);
            v39 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v39, "pose(...)");
            v40 = Color.RED;
            Intrinsics.checkNotNullExpressionValue(v40, "RED");
            var79_52 = 0.5f + ClickGui.E.getValue() / 5.0f;
            v41 = RenderUtil.INSTANCE.withAlpha(v40, var79_52);
            v42 = Color.RED;
            Intrinsics.checkNotNullExpressionValue(v42, "RED");
            Shapes2D.drawShape$default(v39, (char)var31_8, ClickGui.originX + 5.0f, ClickGui.originY + 52.0f + var66_18 + ClickGui.scroll, 110.0f, 12.0f, 3.0f, 2.0f, 0.4f, 1.0f, v41, v42, 0.0f, (short)var32_9, 0.0f, var33_10, 0.0f, 14336, null);
            var83_53 = new Object[]{};
            var85_54 = "hub.theme-editor.delete";
            Fonts.getSF_M3().drawStringCentered(var1_1, I18n.INSTANCE.translate(var85_54, var83_53), ClickGui.originX + 5.0f + 55.0f, ClickGui.originY + 57.0f + var66_18 + ClickGui.scroll, Theme.INSTANCE.getTextColor(), var42_13);
            RenderUtil.INSTANCE.endScissor(var1_1);
            if (!((var66_18 += 16.0f) == 0.0f)) {
                ClickGui.scroll = Math.clamp(ClickGui.scroll, -var66_18 + 206.0f, 0.0f);
            }
            ClickGui.x = 262.0f;
        }
        ClickGui.originX = (float)Globals.getMc().getWindow().getScaledWidth() - ClickGui.i - 5.0f;
        ClickGui.originY = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - ClickGui.x / 2.0f;
    }

    public final void mouseClicked(double d, double d2, int n) {
        Module[] moduleArray = new Module[]{};
        if (selectedPreset != null && w) {
            List list = ConfigStore.INSTANCE.getThemeConfigFile().Z();
            ThemePreset themePreset = selectedPreset;
            Intrinsics.checkNotNull(themePreset);
            list.remove(themePreset);
            selectedPreset = null;
            return;
        }
        float f = originX + 5.0f;
        float f2 = originX + 20.0f;
        float f3 = (float)d;
        boolean bl = f <= f3 ? f3 <= f2 : false;
        if (bl) {
            f = originY + 6.0f;
            f2 = originY + 21.0f;
            f3 = (float)d2;
            boolean bl2 = f <= f3 ? f3 <= f2 : false;
            if (bl2) {
                File file;
                File file2 = file = ConfigStore.INSTANCE.getThemeConfigFile().U();
                Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                operatingSystem.open(file);
            }
        }
        float f4 = originX + i - 21.0f;
        boolean bl3 = d <= (double)(originX + i - 7.0f) ? (double)f4 <= d : false;
        if (bl3) {
            f4 = originY + 31.0f;
            boolean bl4 = d2 <= (double)(originY + 45.0f) ? (double)f4 <= d2 : false;
            if (bl4 && y && selectedPreset == null) {
                this.confirmName();
                return;
            }
        }
        f4 = originX + 4.0f;
        boolean bl5 = d <= (double)(originX + 4.0f + i - 8.0f) ? (double)f4 <= d : false;
        if (bl5) {
            f4 = originY + 28.0f;
            boolean bl6 = d2 <= (double)(originY + 48.0f) ? (double)f4 <= d2 : false;
            if (bl6) {
                ThemePreset themePreset = selectedPreset;
                if (themePreset != null) {
                    ThemePreset themePreset2 = themePreset;
                    selectedPreset = null;
                    return;
                }
                if (y) {
                    y = false;
                    A = "Enter name";
                } else {
                    y = true;
                    A = "";
                }
                return;
            }
        }
        Iterable iterable = panels;
        for (Object t : iterable) {
            Panel panel = (Panel)t;
            panel.isHovered(d, d2);
        }
        if (n == 0) {
            ThemePreset themePreset = hoveredPreset;
            if (themePreset != null) {
                var23_14 = themePreset;
                Theme.INSTANCE.setPreset((ThemePreset)var23_14);
            }
        } else {
            ThemePreset themePreset = hoveredPreset;
            if (themePreset != null) {
                var23_14 = themePreset;
                panels.clear();
                selectedPreset = hoveredPreset;
                int n2 = 0;
                ThemePreset themePreset3 = selectedPreset;
                Intrinsics.checkNotNull(themePreset3);
                Map map = Theme.INSTANCE.applyPreset(themePreset3);
                Iterator iterator2 = map.entrySet().iterator();
                while (iterator2.hasNext()) {
                    Map.Entry entry;
                    Map.Entry entry2 = entry = iterator2.next();
                    String string = (String)entry2.getKey();
                    ThemePreset themePreset4 = selectedPreset;
                    Intrinsics.checkNotNull(themePreset4);
                    Color color = (Color)entry2.getValue();
                    float f5 = originY + 55.0f + (float)n2 * 16.0f;
                    float f6 = originX + 4.0f;
                    panels.add(new Panel(string, themePreset4, f6, f5, color));
                    ++n2;
                }
            }
        }
    }

    public final void mouseReleased(double d, double d2) {
        Iterable iterable = panels;
        Module[] moduleArray = new Module[]{};
        for (Object t : iterable) {
            Panel panel = (Panel)t;
            panel.stopDragging();
        }
    }

    public final void keyPressed(int n) {
        Iterable iterable = panels;
        Iterator iterator2 = iterable.iterator();
        Module[] moduleArray = new Module[]{};
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            Panel panel = (Panel)t;
            panel.keyPressed(n);
        }
        if (y) {
            switch (n) {
                case 259: {
                    A = StringsKt.dropLast(A, 1);
                    break;
                }
                case 257: {
                    this.confirmName();
                    break;
                }
                case 256: {
                    y = false;
                    A = "Enter name";
                    break;
                }
            }
        }
    }

    private final void confirmName() {
        long l = 0L;
        l = a ^ l;
        Module[] moduleArray = new Module[]{};
        if (StringsKt.isBlank(A)) {
            y = false;
            A = "Enter name";
            return;
        }
        Theme theme = Theme.INSTANCE;
        Color color = theme.getInactiveModuleOutlineColor();
        Color color2 = theme.getModuleOutlineColor();
        Color color3 = theme.getModuleColor();
        Color color4 = theme.i();
        Color color5 = theme.getSlidersColor();
        Color color6 = theme.getInactiveTextColor();
        Color color7 = theme.getTextColor();
        Color color8 = theme.getOutlineColor();
        ConfigStore.INSTANCE.getThemeConfigFile().Z().add(new ThemePreset(A, theme.getPrimaryColor(), theme.getSecondaryColor(), theme.g(), theme.getWidgetBlurColor(), color8, color7, color6, color5, color4, color3, color2, color));
        y = false;
        A = "Enter name";
    }

    public final void charTyped(char c) {
        block1: {
            Iterable iterable = panels;
            Module[] moduleArray = new Module[]{};
            for (Object t : iterable) {
                Panel panel = (Panel)t;
                panel.charTyped(c);
            }
            if (!y) break block1;
            A = A + c;
        }
    }

    public final boolean mouseScrolled(double d, double d2, double d3) {
        float f = originX;
        Module[] moduleArray = new Module[]{};
        boolean bl = d <= (double)(originX + i) ? (double)f <= d : false;
        if (bl) {
            f = originY;
            boolean bl2 = d2 <= (double)(originY + x) ? (double)f <= d2 : false;
            if (bl2) {
                scroll += (float)d3 * 10.0f;
                f = 14.0f;
                Iterable iterable = panels;
                for (Object t : iterable) {
                    Panel panel = (Panel)t;
                    f += panel.getHeight() + 2.0f;
                }
                if (f == 14.0f) {
                    return false;
                }
                scroll = Math.clamp(scroll, -f + 206.0f, 0.0f);
                return true;
            }
        }
        return false;
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
                                ClickGui.a = ZkmKeys.a(1428862826641159312L, -8649840468328195377L, MethodHandles.lookup().lookupClass()).a(205450255537806L);
                                var31 = ClickGui.a ^ 51998135344951L;
                                ClickGui.d = new HashMap<K, V>(13);
                                ClickGui.zkmSetFlag(new Module[2]);
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
                                var29_3 = new String[12];
                                var27_4 = 0;
                                var26_5 = "\u00c8\u0091\u00b2\u001e\u00b6\u00ef\f\u000f\u0081?]\u00c0\n\u00e7\u00c4\u00c8\u0018c\u00db\u00c4K\u00ee\u001c\u00acQ[1I\u001f\u00b9\u00c2\u00c9q\u00e7\u00afJ\u0004\u00b6\u0014\u00f6p \u00cfWz\u008b7hPuR\u00c3\u00c4E\u00d0\u00e8q\u00df\u001e6\u00d2\u00ca\u00aa\u00f6\u00e8\u00f3\u00ef\u00ecMa\u0011\u0001\u00ca\u00de\u0018\u0094Q=\u00ee\u00a6\u00d4\u0016\u0080\u00dax\u00f8zf\u00edE\u008c\u00d9\b4TQ(\u00e2\u0004\u0010T\u00c4\u00c1\u00eb\u00cfx\u00c3R1M9\u0001\u00bd\u0006F\u0083 \u00a0F`\u00b8\u00ca\u00d6I\u0001t\u007f1\u0091\u00f2\u00e7\u00daD\u0088\u0083T\u00d7N\u00c7?\u00fc0Z\u00f8\u00c9\u008d\u00e8\u00f3\u00db\u0010\u00da\u00fdR\u0018_\u00d9\u00e4\u00d7\u0007\u0017\u0083\u0001\u008e\u0084\u00ad\u00f5\u0018\n t\b\u00ec\u00fa\u00bc&\u00de\u00a44R\u00eeRo\u0093\u00bek\u00e2\b\u0080\u00c5$\u009d \u0019\u0018D\u00a1\u00d0\u00b2\u00fd\u00d7\u00d6\u00a3\u00afn\u00c5\u00cc\u0013\u00df\u0016\u00a8\u0080\u00d2\u00c1\u00ac\u00b6\u0099\u00a1\u00a8\u00b7\u00d7e\u00b3\u00d9\u00b1(\u00fa\u00a6\u00c2\u00a2\u00bf\u0003\u00deCR\u00bfq\u00bd\u009fKw\u008d\u00ee\u00e5o\u00e0~\u001aXG\u00b6!\u00d4\u001c\u0083Z\u00aa\u00a3tM\u00c8I\u0096\u00ff\u00f0\u001e";
                                var28_6 = 265;
                                var25_7 = 16;
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
                                    var29_3[var27_4++] = ClickGui.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00c1\r\u00c6\u00f6\u00c3\u0010\u00862\u00fcEl\u0018\u00fe\u00a6\u00de\u00caln96\u0002\u00a2\u00c7\u00bb\u0010\u00d3\u00aeA\u0081\u00ca\u0092\u0004bd:\r\u00e4\u00d5\u0002\u0001l";
                                    var28_6 = 41;
                                    var25_7 = 24;
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
                                    var29_3[var27_4++] = ClickGui.zkmDecodeUtf8(var30_9).intern();
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
                        ClickGui.b = var29_3;
                        ClickGui.c = new String[12];
                        ClickGui.g = new HashMap<K, V>(13);
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
                        var17_12 = new long[16];
                        var14_13 = 0;
                        var15_14 = "\u00ae\u0080\u00ec\u000e\\\u00ed\u00c0+\u00c3\u00a8?\u00c3\u00eb\u00c7F\u00f9\u0007z[\u0096\u00b5\u00b1\u001c\u00dc\u0011\u00c6\u009d\u00d7\u00ae\u001d\u0090\u00af\u00cd\u00f1N\u00ab-[3\u009c3\u00fa\u00ae\u00f8\u00b5\u00ea+\u0087;\u0015\u0019\u00d3\u00f7c\u00cf\u0014\u0099\u00fdp\u00be2\u00fd\u00d3e\u00ee\u00a0\u008b\u0097\u00d51^\u00c1\u0097\u00f2P\u00e2\\`\u00e6_\u00b4\u0084n&\u00bb-\rd\u00a9m~\u0083\u00b4\u00a7\u00f8\u00f6x>XZ\u00ec\u0001\u009a\u008c\u00af\u00ad\u00cd\u00d1\u00eb\u001a\u00ca|";
                        var16_15 = 112;
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
                            var15_14 = "2\u0092G\u00a7i\u00ba\u00ff\u00ebJ\u00a8\u0004\u00d4\u00fb\u00bc\u00df\u0018";
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
                ClickGui.e = var17_12;
                ClickGui.f = new Integer[16];
                ClickGui.o = new HashMap<K, V>(13);
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
                var6_22 = new long[3];
                var3_23 = 0;
                var4_24 = "\u0002J\u00c5\u00eb\u00f4\u00f3\u00e9\u00f9\u00d4\u00fa\u00bb\u008e;\u00fd\u00ccxS\u0085\u00f0s\u0004&\u0096\u0005";
                var5_25 = 24;
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
        ClickGui.h = var6_22;
        ClickGui.n = new Long[3];
        ClickGui.INSTANCE = new ClickGui();
        ClickGui.i = 120.0f;
        ClickGui.x = 270.0f;
        ClickGui.originX = (float)Globals.getMc().getWindow().getScaledWidth() - ClickGui.i - 5.0f;
        ClickGui.originY = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - ClickGui.x / 2.0f;
        ClickGui.panels = new ArrayList<E>();
        ClickGui.A = "Enter name";
        ClickGui.E = new Animation(Easing.OUT_QUINT, 200L);
        ClickGui.k = new Animation(Easing.OUT_QUINT, 200L);
        ClickGui.l = new Animation(Easing.OUT_QUINT, 200L);
        ClickGui.m = new RenderTarget("panels/folder", 10, 10);
        ClickGui.B = new RenderTarget("back", 10, 10);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        X = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return X;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xBF0;
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
                throw new RuntimeException("su/catlean/wv", exception);
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
            ClickGui.c[n2] = ClickGui.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ClickGui.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5EF4;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/wv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ClickGui.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ClickGui.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6FEF;
        if (ClickGui.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
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
                throw new RuntimeException("su/catlean/wv", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            ClickGui.n[n2] = l4;
        }
        return ClickGui.n[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = ClickGui.zkmDecryptLong(n, l);
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
            throw new RuntimeException("su/catlean/wv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ClickGui.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ClickGui.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(ClickGui.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

