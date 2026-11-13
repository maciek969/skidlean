/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.CharInput;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.TabBar;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.HudManager;
import su.catlean.ui.hud.WidgetFrame;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class HudEditorScreen
extends Screen {
    @NotNull
    public static final HudEditorScreen INSTANCE;
    @NotNull
    private static final Animation animation;
    private static float dragOffsetX;
    private static float dragOffsetY;
    private static float dragStartX;
    private static float dragStartY;
    private static boolean D;
    private static float mouseX;
    private static float mouseY;
    private static boolean C;
    private static boolean P;
    @NotNull
    private static ArrayList N;
    private static boolean s;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    private HudEditorScreen() {
        super(Text.of("catlean-widget-editor"));
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, float var4_4) {
        v0 = var5_5 = HudEditorScreen.a ^ 91695130820774L;
        v1 = v0 ^ 11994533566457L;
        var11_6 = (int)(v1 >>> 48);
        var12_7 = (int)(v1 << 16 >>> 48);
        var13_8 = (int)(v1 << 32 >>> 32);
        var16_9 = v0 ^ 16899684242074L;
        var18_10 = v0 ^ 49539744380477L;
        var20_11 = v0 ^ 78914775883094L;
        var22_12 = v0 ^ 58206514312415L;
        v2 = new String[]{};
        Intrinsics.checkNotNullParameter(var1_1, "context");
        var27_13 = v2;
        if (Globals.getMc().world == null) {
            ClickGuiScreen.INSTANCE.renderBackground(var1_1, var2_2, var3_3, var4_4);
        }
        super.render(var1_1, var2_2, var3_3, var4_4);
        var28_14 = Globals.getMc().getWindow().getScaledWidth();
        var29_15 = Globals.getMc().getWindow().getScaledHeight();
        if (HudEditorScreen.P) {
            HudEditorScreen.dragStartX = (float)var2_2 - HudEditorScreen.dragOffsetX;
            HudEditorScreen.dragStartY = (float)var3_3 - HudEditorScreen.dragOffsetY;
        }
        var30_16 = HudEditorScreen.dragStartX - 4.0f;
        var31_18 = HudEditorScreen.dragStartX - 4.0f + 100.0f;
        var32_19 = var2_2;
        v3 = var30_16 <= var32_19 ? var32_19 <= var31_18 : false;
        if (!v3) ** GOTO lbl-1000
        var30_16 = HudEditorScreen.dragStartY - 8.0f;
        var31_18 = HudEditorScreen.dragStartY - 5.0f;
        var32_19 = var3_3;
        v4 = var30_16 <= var32_19 ? var32_19 <= var31_18 : false;
        if (v4) {
            v5 = true;
        } else lbl-1000:
        // 2 sources

        {
            v5 = false;
        }
        HudEditorScreen.C = v5;
        HudEditorScreen.animation.animate(HudEditorScreen.C != false ? 1.0f : 0.0f);
        if (HudEditorScreen.dragStartX == -1.0f && HudEditorScreen.dragStartY == -1.0f) {
            HudEditorScreen.dragStartX = (float)var28_14 / 2.0f - 58.0f;
            HudEditorScreen.dragStartY = 50.0f;
        }
        v6 = var1_1.getMatrices();
        v7 = (float)var28_14 - 10.0f;
        v8 = (float)var29_15 - 10.0f;
        v9 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v9, "WHITE");
        RenderUtil.INSTANCE.drawRect(v6, 5.0f, 5.0f, v7, v8, var16_9, v9);
        if (Math.abs((float)var2_2 - HudEditorScreen.mouseX) > 1.0f && Math.abs((float)var3_3 - HudEditorScreen.mouseY) > 1.0f && HudEditorScreen.D && HudManager.INSTANCE.getDraggedElement() == null && !HudEditorScreen.P) {
            var30_16 = Math.min(HudEditorScreen.mouseX, (float)var2_2);
            var31_18 = Math.min(HudEditorScreen.mouseY, (float)var3_3);
            var32_19 = Math.abs(HudEditorScreen.mouseX - (float)var2_2);
            var33_21 = Math.abs(HudEditorScreen.mouseY - (float)var3_3);
            v10 = var1_1.getMatrices();
            v11 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
            RenderUtil.INSTANCE.drawRect(v10, var30_16, var31_18, var32_19, var33_21, var16_9, v11);
        }
        v12 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v12, "pose(...)");
        Shapes2D.drawShape$default(v12, (char)var11_6, HudEditorScreen.dragStartX - 4.0f, HudEditorScreen.dragStartY - 14.0f, 116.0f, (float)HudEditorScreen.N.size() * 15.0f + 15.0f, 7.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var12_7, 0.0f, var13_8, 0.0f, 14336, null);
        v13 = new Color(0x44FFFFFF, true);
        v14 = Color.white;
        Intrinsics.checkNotNullExpressionValue(v14, "white");
        var30_17 = RenderUtil.INSTANCE.blend(v13, RenderUtil.INSTANCE.withAlpha(v14, 0.5f), HudEditorScreen.animation.getValue());
        v15 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v15, "pose(...)");
        Gradient2D.E(HudEditorScreen.dragStartX + 4.0f, HudEditorScreen.dragStartY - 8.0f, 100.0f, 3.0f, 1.5f, var30_17, var30_17, var20_11, var30_17, var30_17, v15, 0.0f, null, null);
        var31_18 = 0.0f;
        var32_20 = HudEditorScreen.N;
        for (T var35_23 : var32_20) {
            var36_24 = (WidgetFrame)var35_23;
            var36_24.render(var1_1, var2_2, var22_12, var3_3, HudEditorScreen.dragStartX, HudEditorScreen.dragStartY + var31_18);
            var31_18 += 15.0f;
        }
        TabBar.INSTANCE.render(var1_1, var2_2, var3_3, var18_10);
    }

    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        String[] stringArray = new String[]{};
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        int n = click.button();
        TabBar.INSTANCE.mouseClicked(d, d2, n);
        D = true;
        mouseX = (float)d;
        mouseY = (float)d2;
        P = C;
        String[] stringArray2 = stringArray;
        dragOffsetX = (float)d - dragStartX;
        dragOffsetY = (float)d2 - dragStartY;
        Iterable iterable = N;
        for (Object t : iterable) {
            WidgetFrame widgetFrame = (WidgetFrame)t;
            widgetFrame.isOverClose(d, d2);
        }
        return super.mouseClicked(click, bl);
    }

    @Override
    public boolean mouseReleased(@NotNull Click click) {
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        click.button();
        D = false;
        P = false;
        TabBar.INSTANCE.r(d, d2);
        return super.mouseReleased(click);
    }

    @Override
    public boolean charTyped(@NotNull CharInput charInput) {
        String[] stringArray = new String[]{};
        Intrinsics.checkNotNullParameter(charInput, "input");
        String[] stringArray2 = stringArray;
        String string = charInput.asString();
        Intrinsics.checkNotNullExpressionValue(string, "codepointAsString(...)");
        char[] cArray = string.toCharArray();
        Intrinsics.checkNotNullExpressionValue(cArray, "toCharArray(...)");
        Character c = ArraysKt.firstOrNull(cArray);
        if (c == null) {
            return super.charTyped(charInput);
        }
        char c2 = c.charValue();
        TabBar.INSTANCE.charTyped(c2);
        return super.charTyped(charInput);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    protected void applyBlur(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    @Override
    public void renderBackground(@NotNull DrawContext drawContext, int n, int n2, float f) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            HudEditorScreen.a = ZkmKeys.a(1191013814943414687L, 2913566728338558870L, MethodHandles.lookup().lookupClass()).a(237942169638529L);
                            var27 = HudEditorScreen.a ^ 70952027345454L;
                            HudEditorScreen.e = new HashMap<K, V>(13);
                            HudEditorScreen.zkmSetFlag(false);
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
                            var25_3 = new String[13];
                            var23_4 = 0;
                            var22_5 = "z\u001d(SV\u0015\u00c1\u008b\u00da\u0082\u00db\u00ad=\u00ca \u0097Q\u00a4\u0085\u0002w\u0093\u00913\u0010\u00eas\u0086_M\"\f\u00d8N\u009c\u00ce]\u00a5\u00a7\u00ae\u00d3(\u00d3X\u00b7|v\u00c8\u00c6\u00ce\u00a5\u00c0\u00d8\u0004\u001b\u00daS<\u00b3\u00fd4\u00c6\u000fV\u0019\u001d\u0097\u0092\u00d3\u00850\u0017\u00cc1s\u00ca\u0093\u0083R\u00def\u00a1\u0010\u001c\u0012u\u00bc\u00ab\u00a2\u00fa\u0091\u00b3\u007f\u008bW\u009ci4\u00dd(\u00e2\u00c5\u00bdy\u0089_\u00d6\u00d1\u00dbB\u00ed\u00a1\u00f6n;\u00fdS'\u00f6\u00af=\u00bfU\u008b\r\u00fb\u001d\u00f8\u00ae\u00c4\u00deg\u0097\u00c1L-S\u00efW\u0004\u0010\u00acc\u00c1\u00f6O\u00dc\u0085\u00cbX\u009b\t%\u00c90\u00a6\f\u0018\u00da\u0085y]=S\u00cd\u0012 ^\u009co<\u00be\u00a5hI\u00c2\u00d2!!]\u00c9m(\u00cb\u00d2\u00c6\u0012/\u00be\u00d6\u00d6\u0019\u00ec\u00db\u00f7\u00f5W\u00c9\u00f8P\u00a4\u00bb-\r'pZ|\u0084-\u00ac\u00eeZc\u0086[b\u00f2y\u0010\b&x\u0010\u00e2?\u00be\u0013\u00e1\u0089\u0088\u00b2\u00c2\u0089\u007f\u0085\u0016\u00c0\u00ac\u00f9\u0010Q\u00cbFJf\u0002.\u0000B?\u00ad\u00d85&\u00dc\u009a\u0010U\u0016\u00fc\u0089\u00b53xX\u00fb\u00d1\u00c2By\u0019<=";
                            var24_6 = 274;
                            var21_7 = 24;
                            var20_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block21;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = HudEditorScreen.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u0019W\u0003\u00bcI]\u00e2\u001e\u00ad\u00ab\u00a0vO\u008d;:\u0010\\,\u00e7<I\u00a8\u0091*r\u00b0@^\u00d5\u0002oF";
                                var24_6 = 33;
                                var21_7 = 16;
                                var20_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = HudEditorScreen.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block22;
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
                    HudEditorScreen.c = var25_3;
                    HudEditorScreen.d = new String[13];
                    HudEditorScreen.i = new HashMap<K, V>(13);
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
                    var13_12 = new long[4];
                    var10_13 = 0;
                    var11_14 = "\u009d\u00f4e\u00c3QZ\u00af\u0002\u008a\u00c6v\u00d8\u008a\u00fc\u0004\u0085";
                    var12_15 = 16;
                    var9_16 = 0;
                    while (true) {
                        var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                        v10 = var13_12;
                        v11 = var10_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block23;
                        break;
                    }
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u0015x,'d\u00db5\u00b1\u0002aS\u00ecv\u00fbP\u0088";
                        var12_15 = 16;
                        var9_16 = 0;
                        while (true) {
                            var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                            v10 = var13_12;
                            v11 = var10_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
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
                        if (var9_16 < var12_15) ** continue;
                        break block24;
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
            HudEditorScreen.f = var13_12;
            HudEditorScreen.g = new Integer[4];
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block25;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{-5, -83, -111, -104, 7, 77, 127, 67});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        HudEditorScreen.INSTANCE = new HudEditorScreen();
        HudEditorScreen.animation = new Animation(Easing.OUT_QUINT, var0_22);
        HudEditorScreen.dragStartX = -1.0f;
        HudEditorScreen.dragStartY = -1.0f;
        HudEditorScreen.mouseX = -1.0f;
        HudEditorScreen.mouseY = -1.0f;
        HudEditorScreen.N = new ArrayList<E>();
        var36_24 = HudManager.INSTANCE.getElements();
        for (T var39_26 : var36_24) {
            var40_27 = (HudElement)var39_26;
            HudEditorScreen.N.add(new WidgetFrame(var40_27));
        }
    }

    public static void zkmSetFlag(boolean bl) {
        s = bl;
    }

    public static boolean zkmGetFlag() {
        return s;
    }

    public static boolean p() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F25;
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
                throw new RuntimeException("su/catlean/rl", exception);
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
            HudEditorScreen.d[n2] = HudEditorScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudEditorScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x39E6;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/rl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HudEditorScreen.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HudEditorScreen.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudEditorScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HudEditorScreen.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

