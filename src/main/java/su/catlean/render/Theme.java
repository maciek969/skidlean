/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
import java.lang.invoke.CallSite;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigStore;
import su.catlean.render.CurrentTheme;
import su.catlean.render.RenderUtil;
import su.catlean.render.ThemePreset;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Theme {
    @NotNull
    public static final Theme INSTANCE;
    @NotNull
    private static ThemePreset themePreset;
    @NotNull
    private static final Animation animation;
    @NotNull
    private static Color z;
    @NotNull
    private static Color primaryColor;
    @NotNull
    private static Color secondaryColor;
    @NotNull
    private static Color F;
    @NotNull
    private static Color widgetBlurColor;
    @NotNull
    private static Color outlineColor;
    @NotNull
    private static Color textColor;
    @NotNull
    private static Color inactiveTextColor;
    @NotNull
    private static Color slidersColor;
    @NotNull
    private static Color Z;
    @NotNull
    private static Color moduleColor;
    @NotNull
    private static Color moduleOutlineColor;
    @NotNull
    private static Color inactiveModuleOutlineColor;
    private static boolean b;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private Theme() {
    }

    @NotNull
    public final Color l() {
        return z;
    }

    public final void I(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        z = color;
    }

    @NotNull
    public final Color getPrimaryColor() {
        return primaryColor;
    }

    public final void setPrimaryColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA70EC0BB588FFCL ^ l))));
        primaryColor = color;
    }

    @NotNull
    public final Color getSecondaryColor() {
        return secondaryColor;
    }

    public final void setSecondaryColor(@NotNull Color color) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA746AB0357418EL ^ l))));
        secondaryColor = color;
    }

    @NotNull
    public final Color g() {
        return F;
    }

    public final void m(@NotNull Color color) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA7602F91AFF59FL ^ l2))));
        F = color;
    }

    @NotNull
    public final Color getWidgetBlurColor() {
        return widgetBlurColor;
    }

    public final void setWidgetBlurColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA7677A2DE2AE85L ^ l))));
        widgetBlurColor = color;
    }

    @NotNull
    public final Color getOutlineColor() {
        return outlineColor;
    }

    public final void setOutlineColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA7321C59CFFF34L ^ l))));
        outlineColor = color;
    }

    @NotNull
    public final Color getTextColor() {
        return textColor;
    }

    public final void setTextColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA74DA81143B22AL ^ l))));
        textColor = color;
    }

    @NotNull
    public final Color getInactiveTextColor() {
        return inactiveTextColor;
    }

    public final void setInactiveTextColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)6197, (long)(0x5361B69936A0DC22L ^ l))));
        inactiveTextColor = color;
    }

    @NotNull
    public final Color getSlidersColor() {
        return slidersColor;
    }

    public final void setSlidersColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA767170D3B110DL ^ l))));
        slidersColor = color;
    }

    @NotNull
    public final Color i() {
        return Z;
    }

    public final void x(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA742FFCBA8A04CL ^ l))));
        Z = color;
    }

    @NotNull
    public final Color getModuleColor() {
        return moduleColor;
    }

    public final void setModuleColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA71DABDDF248D8L ^ l))));
        moduleColor = color;
    }

    @NotNull
    public final Color getModuleOutlineColor() {
        return moduleOutlineColor;
    }

    public final void setModuleOutlineColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA76AA7158EB2CCL ^ l))));
        moduleOutlineColor = color;
    }

    @NotNull
    public final Color getInactiveModuleOutlineColor() {
        return inactiveModuleOutlineColor;
    }

    public final void setInactiveModuleOutlineColor(@NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)Theme.zkmBootstrap("c", (int)16156, (long)(0x4EA770A2B0340FE6L ^ l))));
        inactiveModuleOutlineColor = color;
    }

    public final void setPreset(@NotNull ThemePreset themePreset) {
        Intrinsics.checkNotNullParameter(themePreset, "newTheme");
        Theme.themePreset = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent();
        ConfigStore.INSTANCE.getThemeConfigFile().setCurrent(themePreset);
        ConfigStore.INSTANCE.getConfigManager().getData().setThemeConfig(themePreset.getId());
        animation.animateTo(0.0f);
    }

    public final void update() {
        animation.animate(1.0f);
        ThemePreset themePreset = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent();
        float f = animation.getValue();
        primaryColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getPrimaryColor(), themePreset.getPrimaryColor(), f);
        secondaryColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getSecondaryColor(), themePreset.getSecondaryColor(), f);
        F = RenderUtil.INSTANCE.blend(Theme.themePreset.getBlurColor(), themePreset.getBlurColor(), f);
        widgetBlurColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getWidgetBlurColor(), themePreset.getWidgetBlurColor(), f);
        outlineColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getOutlineColor(), themePreset.getOutlineColor(), f);
        textColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getTextColor(), themePreset.getTextColor(), f);
        inactiveTextColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getNonactiveTextColor(), themePreset.getNonactiveTextColor(), f);
        slidersColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getSlidersColor(), themePreset.getSlidersColor(), f);
        String[] stringArray = new String[]{};
        Z = RenderUtil.INSTANCE.blend(Theme.themePreset.getNonActiveModuleColor(), themePreset.getNonActiveModuleColor(), f);
        moduleColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getModuleColor(), themePreset.getModuleColor(), f);
        moduleOutlineColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getModuleOutlineColor(), themePreset.getModuleOutlineColor(), f);
        inactiveModuleOutlineColor = RenderUtil.INSTANCE.blend(Theme.themePreset.getNonActiveOutlineModuleColor(), themePreset.getNonActiveOutlineModuleColor(), f);
    }

    @NotNull
    public final Color getGradientColor(int n) {
        int n2 = (int)(((double)Globals.now() / 14.0 + (double)n) % 360.0);
        String[] stringArray = new String[]{};
        n2 = n2 >= 180 ? 360 - n2 : n2;
        return RenderUtil.INSTANCE.blend(primaryColor, secondaryColor, (float)n2 / 360.0f);
    }

    @NotNull
    public final Map applyPreset(@NotNull ThemePreset themePreset) {
        Intrinsics.checkNotNullParameter(themePreset, "theme");
        ThemePreset themePreset2 = themePreset;
        Pair[] pairArray = new Pair[12];
        pairArray[0] = TuplesKt.to("primary", themePreset2.getPrimaryColor());
        pairArray[1] = TuplesKt.to("secondary", themePreset2.getSecondaryColor());
        pairArray[2] = TuplesKt.to("blur", themePreset2.getBlurColor());
        String[] stringArray = new String[]{};
        pairArray[3] = TuplesKt.to("widget-blur", themePreset2.getWidgetBlurColor());
        pairArray[4] = TuplesKt.to("outline", themePreset2.getOutlineColor());
        pairArray[5] = TuplesKt.to("text", themePreset2.getTextColor());
        pairArray[6] = TuplesKt.to("inactive-text", themePreset2.getNonactiveTextColor());
        pairArray[7] = TuplesKt.to("sliders", themePreset2.getSlidersColor());
        pairArray[8] = TuplesKt.to("inactive-module", themePreset2.getNonActiveModuleColor());
        pairArray[9] = TuplesKt.to("module", themePreset2.getModuleColor());
        pairArray[10] = TuplesKt.to("module-outline", themePreset2.getModuleOutlineColor());
        pairArray[11] = TuplesKt.to("inactive-module-outline", themePreset2.getNonActiveOutlineModuleColor());
        return MapsKt.mapOf(pairArray);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            Theme.a = ZkmKeys.a(4683321578115332826L, 3147421709212221365L, MethodHandles.lookup().lookupClass()).a(181501285391719L);
                            var27 = Theme.a ^ 99568535141262L;
                            Theme.e = new HashMap<K, V>(13);
                            Theme.zkmSetFlag(true);
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var27 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[17];
                            var23_4 = 0;
                            var22_5 = "\u0087\u00e2<^WE\u0083\u0005\u00fe\u0016\u00e6E&\u0014<\u001a\u00ab5H\u00ce+\u00cb;\u0001\u00a8\u0086tD\u0082_\u009a\u00d6\u0010\u0080\u00d3/z$c\u00f6\"Gm\u00c4T=\u009e\u00eao \u00af\u00cd\u008d\u0004\u00b7\u0095]\u00cd7\u00ca\u008f\u0013#m\u001c\u00a1\u00b0.\u00d9\u00b1\u0011\u00cd'\u00c0O\u00e8\u00d4\u0089<\u0085\u00c5h\u0010\u00a5\u00cc\u00c8\u00de\u00d1\u0005\u008dW\u00af\u00d0\n\u00fc\u009c/\u0007\u00b4\u0010\u0096\b\u00b8>\u009d\nhW\u00cfm\u00be\u001a\u00f1\u0093\u00bfo\u0010P\u0007M{\u0000\u00fbl\u00e0\u0003c|\u00a8\u00b8|\u00dc\u00a0\u00100\u0093\u008d}i\u00ad\u0083\u00bb}\u008f\u00c46SZ*\u00df\u0010\u00aag\u00c9/\u00b4\u00d2\u0017\u00a5[TX\u00ba\u00064=+ \u009c8q\u00f4_\u0097\u00cdE\u0096\b\u00a1\u0080\u0012\u00a6_\u0094\u00d5\n\u00ae\u00b4\u00fceP\t;u\u00af\u00a2\u0086\u0000\u00f56\u0010\u00fcs\u00c4\u00b3\u0002N\u0014\u0090\u00daWP\u00bdS\u00c3A-\u0018\u0092?\u00d7\u00de\u00cd\u0097\u00a2\u00ffcd\u0001h<8JRo[\u008aK\u0017&W\u0095\u0010\u00b4\u00a0\u00938\u0014\u00b2\u00cf\u00d7\u00a0\u00bdh\u00d0e\u0018L^\u0010\u0019I[\u00a0\u00beT2uLO\u0015\u00c7\u00b0\u0010\u00baV\u0018\u0002\u00f2_\u00d90\u00a7\u00df\u0099\u00ff\u0086C\u0016\u00c4\u0098\u00a7@\u00c7@\u0087\u009d\u00df\u008e\u00eb\u0000 \u00ad\u00ac\u0011&\u00b7I\u00c0\u00eb.o\u009d\u00a7\u00c1W\u008b6\u0090\u00b8+?\u00076\u00a9\"O4\u00f3\u00b5\u00e8?\u00be*";
                            var24_6 = 334;
                            var21_7 = 32;
                            var20_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Theme.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\n`\u00bd\u00b6=\u00a6\u0004\u00af\u0015/\u001d\u00b3\u00075\u00fb\u0094\u00b6?\u0017\u00f8CJ<\u00e1~\u008d\u00a8\u00f2\u0015\u00d3\u00e3\u00df,\u008e\u00ab\u00cf\u00b4\u00b7\u00fb\u00e6\u0010B\u0018]\u008da^5\u009b\u00d8\u00f2\u0006Bi$\u0081\u0095";
                                var24_6 = 57;
                                var21_7 = 40;
                                var20_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Theme.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    Theme.c = var25_3;
                    Theme.d = new String[17];
                    Theme.i = new HashMap<K, V>(13);
                    var7_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var8_11 = 1; var8_11 < 8; ++var8_11) {
                        v9 = v9;
                        v9[var8_11] = (byte)(var27 << var8_11 * 8 >>> 56);
                    }
                    var7_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var13_12 = new long[10];
                    var10_13 = 0;
                    var11_14 = "\u009e_5A9\u00ed\u00bd\u00dd-\u00bc)eT\u00c8i\u00f3\u00cc\u001er\u00fea\u0098\u00d7\u00b2\u00de\u00b0Z\u00b2KP-\u00b6\u009b\u00ea\u00bd\u00b8l\u00b4{\u00d0\u00b2\u00d1)\u007f\u0000\b\u0013\u00052<\u00d8i\u0018k#\u00e5\u0081U\u00ec\u00bd\u00a1\u0019\u00a9\u00d3";
                    var12_15 = 64;
                    var9_16 = 0;
                    while (true) {
                        var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                        v10 = var13_12;
                        v11 = var10_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u00e3\u009f\u00ed33\u00c1X\u009b\u0011[\u0087\u00b0\u0090B\u00cf\u00e7";
                        var12_15 = 16;
                        var9_16 = 0;
                        while (true) {
                            var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                            v10 = var13_12;
                            v11 = var10_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl92:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_18 = v12;
                var17_19 = var7_10.doFinal(new byte[]{(byte)(var15_18 >>> 56), (byte)(var15_18 >>> 48), (byte)(var15_18 >>> 40), (byte)(var15_18 >>> 32), (byte)(var15_18 >>> 24), (byte)(var15_18 >>> 16), (byte)(var15_18 >>> 8), (byte)var15_18});
                v14 = ((long)var17_19[0] & 255L) << 56 | ((long)var17_19[1] & 255L) << 48 | ((long)var17_19[2] & 255L) << 40 | ((long)var17_19[3] & 255L) << 32 | ((long)var17_19[4] & 255L) << 24 | ((long)var17_19[5] & 255L) << 16 | ((long)var17_19[6] & 255L) << 8 | (long)var17_19[7] & 255L;
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
            Theme.g = var13_12;
            Theme.h = new Integer[10];
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{14, -109, 39, -34, 67, -28, 21, -105});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        Theme.INSTANCE = new Theme();
        Theme.themePreset = CurrentTheme.getThemePreset();
        Theme.animation = new Animation(Easing.OUT_EXPO, var0_22);
        v18 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v18, "WHITE");
        Theme.z = v18;
        Theme.primaryColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getPrimaryColor();
        Theme.secondaryColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getSecondaryColor();
        Theme.F = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getBlurColor();
        Theme.widgetBlurColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getWidgetBlurColor();
        Theme.outlineColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getOutlineColor();
        Theme.textColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getTextColor();
        Theme.inactiveTextColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getNonactiveTextColor();
        Theme.slidersColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getSlidersColor();
        Theme.Z = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getNonActiveModuleColor();
        Theme.moduleColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getModuleColor();
        Theme.moduleOutlineColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getModuleOutlineColor();
        Theme.inactiveModuleOutlineColor = ConfigStore.INSTANCE.getThemeConfigFile().getCurrent().getNonActiveOutlineModuleColor();
    }

    public static void zkmSetFlag(boolean bl) {
        b = bl;
    }

    public static boolean zkmGetFlag() {
        return b;
    }

    public static boolean Q() {
        return false;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4FF2;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/jh", exception);
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
            Theme.d[n2] = Theme.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Theme.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x554D;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/jh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Theme.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Theme.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Theme.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Theme.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

