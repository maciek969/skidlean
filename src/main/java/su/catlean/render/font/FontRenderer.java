/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.render.font;

import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.Closeable;
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
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.render.font.GlyphCache;
import su.catlean.render.font.GlyphPage;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class FontRenderer
implements Closeable {
    @NotNull
    private Font font;
    @NotNull
    private Font fallBack;
    private final float size;
    @NotNull
    private final Object2ObjectOpenHashMap M;
    @NotNull
    private final Object2ObjectOpenHashMap w;
    @NotNull
    private final ObjectArrayList pages;
    @NotNull
    private final Char2ObjectArrayMap V;
    private int scaleFactor;
    private float i;
    private static int[] I;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public FontRenderer(@NotNull Font font, @NotNull Font font2, float f) {
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(font2, "fallBack");
        this.font = font;
        this.fallBack = font2;
        this.size = f;
        this.M = new Object2ObjectOpenHashMap();
        this.w = new Object2ObjectOpenHashMap();
        this.pages = new ObjectArrayList();
        this.V = new Char2ObjectArrayMap();
        this.updateScale();
    }

    public final float getSize() {
        return this.i;
    }

    public final void setSize(float f) {
        this.i = f;
    }

    public final void updateScale() {
        this.scaleFactor = Globals.getMc().getWindow().getScaleFactor();
        Font font = this.font.deriveFont(this.size * (float)this.scaleFactor);
        Intrinsics.checkNotNullExpressionValue(font, "deriveFont(...)");
        this.font = font;
        Font font2 = this.fallBack.deriveFont(this.size * (float)this.scaleFactor);
        Intrinsics.checkNotNullExpressionValue(font2, "deriveFont(...)");
        this.fallBack = font2;
    }

    public final void drawStringWithShadow(@NotNull DrawContext drawContext, @NotNull String string, long l, float f, float f2, @NotNull Color color) {
        long l2 = (l = a ^ l) ^ 0x1FA47AC1D171L;
        int n = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        FontRenderer.drawStringWithShadow$default(this, matrix3x2fStack, string, (short)n, f, f2, l3, color, false, 0, 96, null);
    }

    public final void drawString(@NotNull Matrix3x2fStack matrix3x2fStack, long l, @NotNull String string, float f, float f2, @NotNull Color color, boolean bl, int n) {
        long l2 = (l = a ^ l) ^ 0x132E7CEB157AL;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        float f3 = this.i;
        Font font = this.fallBack;
        Font font2 = this.font;
        ObjectArrayList objectArrayList = this.pages;
        Char2ObjectArrayMap char2ObjectArrayMap = this.V;
        float f4 = this.scaleFactor;
        int n2 = n;
        boolean bl2 = bl;
        Color color2 = color;
        float f5 = f2;
        float f6 = f;
        String string2 = string;
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        GlyphCache glyphCache = GlyphCache.INSTANCE;
        this.i += 0.1f;
        Unit unit = Unit.INSTANCE;
        glyphCache.drawStringScaled(matrix3x2fStack2, string2, f6, f5, color2, bl2, n2, f4, l2, char2ObjectArrayMap, objectArrayList, font2, font, f3, this.w);
    }

    public static /* synthetic */ void drawString$default(FontRenderer fontRenderer, Matrix3x2fStack matrix3x2fStack, String string, float f, float f2, long l, Color color, boolean bl, Object object) {
        long l2 = (l = a ^ l) ^ 0x5D173FE84851L;
        String[] stringArray = new String[]{};
        bl = false;
        int n = 0;
        fontRenderer.drawString(matrix3x2fStack, l2, string, f, f2, color, bl, n);
    }

    public final void drawStringWithShadow(long l, @NotNull Matrix3x2fStack matrix3x2fStack, @NotNull String string, float f, float f2, @NotNull Color color, boolean bl, int n) {
        long l2 = (l = a ^ l) ^ 0x473ABFF77315L;
        int n2 = (int)(l2 >>> 32);
        int n3 = (int)(l2 << 32 >>> 40);
        int n4 = (int)(l2 << 56 >>> 56);
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        GlyphCache.INSTANCE.drawString(matrix3x2fStack, string, n2, f, f2, n3, color, bl, n, this.scaleFactor, this.V, (byte)n4, this.pages, this.font, this.fallBack, this.M);
    }

    public static /* synthetic */ void drawStringWithShadow$default(FontRenderer fontRenderer, Matrix3x2fStack matrix3x2fStack, String string, short s, float f, float f2, long l, Color color, boolean bl, int n, int n2, Object object) {
        long l2 = ((long)s << 48 | l << 16 >>> 16) ^ a;
        long l3 = l2 ^ 0x36FAF2564A91L;
        if ((n2 & FontRenderer.zkmBootstrap2("m", (int)10444, (long)(0x2AC61BDDA3183371L ^ l2))) != 0) {
            bl = false;
        }
        if ((n2 & FontRenderer.zkmBootstrap2("m", (int)17759, (long)(0x2F23F01AFE1DEE4L ^ l2))) != 0) {
            n = 0;
        }
        fontRenderer.drawStringWithShadow(l3, matrix3x2fStack, string, f, f2, color, bl, n);
    }

    public final void drawStringCentered(@NotNull Matrix3x2fStack matrix3x2fStack, @NotNull String string, float f, float f2, long l, @NotNull Color color) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x6AF6461D54C5L;
        long l4 = l3 >>> 8;
        int n = (int)(l3 << 56 >>> 56);
        long l5 = l2 ^ 0x367AC01954CEL;
        long l6 = l2 ^ 0x5E85F49D1803L;
        int n2 = (int)(l6 >>> 48);
        long l7 = l6 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        FontRenderer.drawStringWithShadow$default(this, matrix3x2fStack, string, (short)n2, f - this.getWidth(l5, string) / 2.0f, f2 - GlyphCache.INSTANCE.getWidth(string, this.V, this.pages, l4, this.font, (byte)n, this.fallBack, this.scaleFactor) / 2.0f, l7, color, false, 0, 96, null);
    }

    public final void drawStringCentered(@NotNull DrawContext drawContext, @NotNull String string, float f, float f2, @NotNull Color color, long l) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x3572DCFA510DL;
        long l4 = l3 >>> 8;
        int n = (int)(l3 << 56 >>> 56);
        long l5 = l2 ^ 0x69FE5AFE5106L;
        long l6 = l2 ^ 0x1016E7A1DCBL;
        int n2 = (int)(l6 >>> 48);
        long l7 = l6 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        FontRenderer.drawStringWithShadow$default(this, matrix3x2fStack, string, (short)n2, f - this.getWidth(l5, string) / 2.0f, f2 - GlyphCache.INSTANCE.getWidth(string, this.V, this.pages, l4, this.font, (byte)n, this.fallBack, this.scaleFactor) / 2.0f, l7, color, false, 0, 96, null);
    }

    public final void clearCache(int n, char c, short s) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)s << 48 >>> 48) ^ a;
        long l2 = l ^ 0x353890592EL;
        GlyphCache.INSTANCE.flush(this.w, l2);
        this.i = 0.0f;
    }

    public final float getWidth(long l, @NotNull String string) {
        long l2 = (l = a ^ l) ^ 0x78CCFE4F5006L;
        int n = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(string, "s");
        return GlyphCache.INSTANCE.getHeight(string, this.V, this.pages, (char)n, this.font, l3, this.fallBack, this.scaleFactor);
    }

    @Override
    public void close() {
        FontRenderer fontRenderer = this;
        try {
            Object object = fontRenderer;
            ((FontRenderer)object).pages.removeIf(arg_0 -> FontRenderer.invokePredicate(FontRenderer::close$lambda$0, arg_0));
            ((FontRenderer)object).V.clear();
            object = Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Object object = Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    public final void drawString(@NotNull DrawContext drawContext, @NotNull String string, long l, float f, float f2, int n) {
        long l2 = (l = a ^ l) ^ 0x7DA3B844A836L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "s");
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, "WHITE");
        int n2 = n;
        boolean bl = true;
        Color color2 = color;
        float f3 = f2;
        float f4 = f;
        String string2 = string;
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        this.drawStringWithShadow(l2, matrix3x2fStack2, string2, f4, f3, color2, bl, n2);
    }

    public final void drawStringRight(long l, @NotNull DrawContext drawContext, @NotNull String string, float f, float f2, int n) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x5E597309F1L;
        long l4 = l2 ^ 0x23F827E5A79BL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "s");
        this.drawString(drawContext, string, l3, f - this.getWidth(l4, string) / 2.0f, f2, n);
    }

    private static final boolean close$lambda$0(GlyphPage glyphPage) {
        glyphPage.destroy();
        return true;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    public static void zkmSetFlag(int[] nArray) {
        I = nArray;
    }

    public static int[] zkmGetFlag() {
        return I;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        FontRenderer.a = ZkmKeys.a(6736742733309809660L, -9218772071814161005L, MethodHandles.lookup().lookupClass()).a(1594835597882L);
                        var20 = FontRenderer.a ^ 7374643077087L;
                        FontRenderer.d = new HashMap<K, V>(13);
                        FontRenderer.zkmSetFlag(new int[3]);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "\u00bay\u00d3^\u008d\u00cb\u00f6\u00a2o\u0087\u00cd\u00b6\u0095\u00beY\u00c4<\u0005\u00b5_\u0095\u00d7\u00a9\u0081F\u00d2e\u0088\f\u00ed\u00fe\u00ad\u0010\u0092\u00ac\u0086h\u00d0h\u0014\u0097\u00fd_E\u00a7\u00fdv,\u00ca \u00df\u00a2n\u00bc\u0092|-;\u00ab%W\t\u00f7\u008a2T(\u0093\u00dc\u00b0>\u00db\u00c8\u00fa\u001a\u0091\u00e9O\u0091\u00a3\u0007H\u0010\u00ea\u00c6arI\u0094\u00a8Da\u00d1M\u00dd:\u00f0\u0019\u009a A\\\u00ca\u008a\u0097X\u001e!\u00f8&n\u00e0\u00e56?V\u00d3z$\u001e\u00d4\u0015\r.\u00acD\u00993\u00ee\u00e5v\u00c7\u0018rv\u00b4\u000eP\u00c7\u00e1\u0081F\u00c8\u00c9\u0001L\u00c8$\u0086\u00d1v}\u00ee\u0016\u00d9/\u001e\u00181\u00ef\u00bb\u00aa\u0099\u008bD\u00bfT,C\u0083q\u00f1\u0017\u00f0x;\u00bbhq\u00e6\u00f8H\u0010_\u0010\u0099y\"\u00b9\u00b3K\u00db\\B\u00fau\u009b\u00bdZ\u0010\u00a6\u008cY\u00a1\u00af\u00a9\u00ff\u00d9/\u0010T#\u0014\u00da\u007f\u00ba";
                        var17_6 = 216;
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
                            var18_3[var16_4++] = FontRenderer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "6\u00d2/\u00b3\u008b\u00c9\u00b9*\u00c8V\u008d`\u001b\u00f7\u00e8Z\u0010S\u00a8\u00da\u00bd\u0083\u00be;.\u0096\u00ef\u00ccs\u00a9j\u0084-";
                            var17_6 = 33;
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
                            var18_3[var16_4++] = FontRenderer.zkmDecodeUtf8(var19_9).intern();
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
                FontRenderer.b = var18_3;
                FontRenderer.c = new String[11];
                FontRenderer.g = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "Z\u0016\u0018\u00d2\u0010\u0091\u00b9'Fgb\u00c7 \u0000\u0089 K\u0010\u0004\u00b8h\u0099\u00f4\"L\b\u00f6\u00cc\u00bf\u008b\u00aa\u0085";
                var5_15 = 32;
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
                    var4_14 = "\u00bf=\u00e2+\u0093\u00c5\u00fa\u00cb\\\u00a4\u0099\u00f9\u001a\u00a9Mo";
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
        FontRenderer.e = var6_12;
        FontRenderer.f = new Integer[6];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2697;
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
                throw new RuntimeException("su/catlean/c6", exception);
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
            FontRenderer.c[n2] = FontRenderer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FontRenderer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x47E3;
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
                throw new RuntimeException("su/catlean/c6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FontRenderer.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FontRenderer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/c6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FontRenderer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FontRenderer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

