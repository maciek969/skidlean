/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.Element;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.zkm.ZkmKeys;

public final class SwitchElement
extends Element {
    private final float width;
    @NotNull
    private String first;
    @NotNull
    private String second;
    @NotNull
    private KMutableProperty0 ref;
    @NotNull
    private KMutableProperty0 hoverRef;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public SwitchElement(float f, @NotNull String string, @NotNull String string2, @NotNull KMutableProperty0 kMutableProperty0, @NotNull KMutableProperty0 kMutableProperty02) {
        Intrinsics.checkNotNullParameter(string, "first");
        Intrinsics.checkNotNullParameter(string2, "second");
        Intrinsics.checkNotNullParameter(kMutableProperty0, "ref");
        Intrinsics.checkNotNullParameter(kMutableProperty02, "hoverRef");
        this.width = f;
        this.first = string;
        this.second = string2;
        this.ref = kMutableProperty0;
        this.hoverRef = kMutableProperty02;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        v0 = var1_1;
        v1 = v0 ^ 122872324218116L;
        var8_7 = (int)(v1 >>> 48);
        var9_8 = (int)(v1 << 16 >>> 48);
        var10_9 = (int)(v1 << 32 >>> 32);
        var13_10 = v0 ^ 18592005668209L;
        var15_11 = v0 ^ 6627968639251L;
        Intrinsics.checkNotNullParameter(var3_2, "context");
        v2 = var4_3 <= var6_5 ? var6_5 <= var4_3 + (this.width - 88.0f) : false;
        if (!v2) ** GOTO lbl-1000
        v3 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 13.0f : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = var36_12 = false;
        }
        if (var36_12) {
            this.hoverRef.set(this);
        }
        v5 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        v6 = var36_12 != false ? 2.0f : 0.0f;
        v7 = Theme.INSTANCE.getWidgetBlurColor().darker();
        Intrinsics.checkNotNullExpressionValue(v7, "darker(...)");
        var20_13 = null;
        var21_14 = 14336;
        var22_15 = 0.0f;
        var23_16 = 0.0f;
        var24_17 = 0.0f;
        var25_18 = Theme.INSTANCE.getOutlineColor();
        var26_19 = v7;
        var27_20 = 1.0f;
        var28_21 = 0.0f;
        var29_22 = v6;
        var30_23 = 2.0f;
        var31_24 = 13.0f;
        var32_25 = this.width - 88.0f;
        var33_26 = var5_4;
        var34_27 = var4_3;
        Shapes2D.drawShape$default(v5, (char)var8_7, var34_27, var33_26, var32_25, var31_24, var30_23, var29_22, var28_21, var27_20, var26_19, var25_18, var24_17, (short)var9_8, var23_16, var10_9, var22_15, var21_14, var20_13);
        v8 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v8, "pose(...)");
        var37_28 /* !! */  = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(v8, var4_3 + (this.width - 88.0f) / 2.0f, var5_4 + 2.0f, 0.5f, 9.0f, var37_28 /* !! */ , var15_11);
        v9 = Fonts.getSF_M();
        if (((Boolean)this.ref.get()).booleanValue()) {
            v10 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v10, "WHITE");
            v11 = RenderUtil.INSTANCE.withAlpha(v10, 0.5f);
        } else {
            v11 = Color.WHITE;
        }
        var37_28 /* !! */  = v11;
        Intrinsics.checkNotNull(var37_28 /* !! */ );
        v9.drawStringCentered(var3_2, this.first, var4_3 + (this.width - 88.0f) * 0.25f, var5_4 + 5.0f, (Color)var37_28 /* !! */ , var13_10);
        v12 = Fonts.getSF_M();
        if (!((Boolean)this.ref.get()).booleanValue()) {
            v13 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v13, "WHITE");
            v14 = RenderUtil.INSTANCE.withAlpha(v13, 0.5f);
        } else {
            v14 = Color.WHITE;
        }
        var37_28 /* !! */  = v14;
        Intrinsics.checkNotNull(var37_28 /* !! */ );
        v12.drawStringCentered(var3_2, this.second, var4_3 + (this.width - 88.0f) * 0.75f, var5_4 + 5.0f, (Color)var37_28 /* !! */ , var13_10);
    }

    @Override
    public boolean isHovered(float f, float f2) {
        this.ref.set((Boolean)this.ref.get() == false);
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        SwitchElement.b = ZkmKeys.a(-2087992730846593787L, 4347984991775075098L, MethodHandles.lookup().lookupClass()).a(16389103837583L);
                        SwitchElement.e = new HashMap<K, V>(13);
                        var11 = SwitchElement.b ^ 39086991433629L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[10];
                        var18_4 = 0;
                        var17_5 = "0K7I\u00b0\u0085{\u00f2\u00a4y[\u00d1\u00d0\u00cc\u00c8\u00cf\u00989\u00e2\u009a\u00c1\u000f\u0003\u00b1 \u001b\u00d0\u00fc\u00c5\u001d\u00840z\u00c4\u0018u\u00af\u0086\u00fe\u00ffw\u00e2\u0017\u00d0V\u00eb\u00ad\u00ee\u00e3\u00f7\u009a\u00fe\u0088\u001a\u009d\u00e2\f\u0010R\u00fe\u00a5,(\u00c7\u0016\u00dd`\u00d0\u00baLs[\u00a1f\u0010\u00da\u00fdb\u00b5C\u00149\u0016_6v\u00c8\u00ec\u00b0\u0091\u00ac \u00a8\u00a9>\u00f7B\u009ay\u00b5\u00c5\u00d0m\u0003\u00f8\u00d9\r&\u0007\u00c2\u001a\u0093\u0084\u009dw\u00e7lM\u0086,\"\u00de\u00e9\u00a7\u0010!\u00df\u00a1\u0088\u009f<\u008f\u00b5U\u00a8 q\u0003\u0088\u00054\u0010\u00aa5\u00b1BI\u00d1\u00a7\u0081\u0090a\u008a\u0006\u00a5\u00fe\u00bc^\u0010\u0015{\u00db\u0013\u00e8$\u00837!\u00c3\u001aR\u00f2\u0096T\u00d8";
                        var19_6 = 175;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SwitchElement.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00d5\u00a0tx\u00e6\u0089\t\u0018\u00f4\u0007\u00b6A\u00f8\u0093\u00b6; \u001eA\u00ae\u00e0\u00a3\u008b)\u00cd\u00f9\u0004\u00f8\u00f7\u0012\u00act\u0013\u00e9\u00aa^rl\u00bd\u00ae\b \u00faW`\u00e2\u00cb\u0010&";
                            var19_6 = 49;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SwitchElement.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                SwitchElement.c = var20_3;
                SwitchElement.d = new String[10];
                SwitchElement.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "\u00ab,\u00cfx\u00c5G'\u00fc\u00d9\u00a7\u00e1d\u00dd\u001c\u00c1\u0019";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        SwitchElement.f = var6_12;
        SwitchElement.g = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6629;
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
                throw new RuntimeException("su/catlean/xq", exception);
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
            SwitchElement.d[n2] = SwitchElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SwitchElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x191F;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/xq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SwitchElement.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SwitchElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/xq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SwitchElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SwitchElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

