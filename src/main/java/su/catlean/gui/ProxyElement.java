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
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.Element;
import su.catlean.gui.ProxyPanel;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Proxy;
import su.catlean.util.ProxyManager;
import su.catlean.zkm.ZkmKeys;

public final class ProxyElement
extends Element {
    @NotNull
    private Proxy proxy;
    @NotNull
    private final Animation animation;
    private static String[] Y;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map b;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public ProxyElement(@NotNull Proxy proxy) {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        this.proxy = proxy;
        this.animation = new Animation(Easing.OUT_QUINT, h);
    }

    @NotNull
    public final Proxy getProxy() {
        return this.proxy;
    }

    public final void setProxy(@NotNull Proxy proxy) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(proxy, (String)((Object)ProxyElement.zkmBootstrap("e", (int)19849, (long)(0x3D8498AD7D28C13FL ^ l))));
        this.proxy = proxy;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        v0 = var1_1;
        v1 = v0 ^ 122872324218116L;
        var10_7 = (int)(v1 >>> 48);
        var11_8 = (int)(v1 << 16 >>> 48);
        var12_9 = (int)(v1 << 32 >>> 32);
        var15_10 = v0 ^ 15727589290443L;
        var17_11 = v0 ^ 1638017753221L;
        Intrinsics.checkNotNullParameter(var3_2, "context");
        v2 = var4_3 <= var6_5 ? var6_5 <= var4_3 + 70.0f : false;
        if (!v2) ** GOTO lbl-1000
        v3 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 25.0f : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = false;
        }
        var27_12 = v4;
        this.animation.animate(var27_12 != false ? 1.0f : 0.0f);
        if (var27_12) {
            ProxyPanel.INSTANCE.setFocusedElement(this);
        }
        var28_13 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), Intrinsics.areEqual(this.proxy, ProxyManager.INSTANCE.getProxy()) != false ? 1.0f : 0.0f);
        var29_14 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), Intrinsics.areEqual(this.proxy, ProxyManager.INSTANCE.getProxy()) != false ? 1.0f : 0.0f);
        v5 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        Shapes2D.drawShape$default(v5, (char)var10_7, var4_3 + 4.0f, var5_4, 70.0f, 25.0f, 2.0f, 2.5f + this.animation.getValue(), 1.0f, 1.0f, var28_13, var29_14, 0.0f, (short)var11_8, 0.0f, var12_9, 0.0f, 14336, null);
        v6 = Fonts.getSF_M();
        v7 = this.proxy.getId();
        v8 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v8, "WHITE");
        v6.drawStringWithShadow(var3_2, v7, var15_10, var4_3 + 6.0f, var5_4, v8);
        v9 = Fonts.getSF_M();
        v10 = this.proxy.getIp();
        v11 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
        v9.drawStringWithShadow(var3_2, v10, var15_10, var4_3 + 6.0f, var5_4 + 11.0f, RenderUtil.INSTANCE.withAlpha(v11, 0.7f));
        switch (this.proxy.getPing()) {
            case -1: {
                v12 = "-";
                break;
            }
            case -2: {
                v12 = "...";
                break;
            }
            default: {
                v12 = this.proxy.getPing() + "ms";
            }
        }
        var30_15 = v12;
        var31_16 = Fonts.getSF_M4().getWidth(var17_11, var30_15);
        v13 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
        Shapes2D.drawShape$default(v13, (char)var10_7, var4_3 + 67.0f - var31_16, var5_4 + 2.0f, var31_16 + 4.0f, 8.0f, 2.0f, 2.0f, 0.15f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var11_8, 0.0f, var12_9, 0.0f, 14336, null);
        v14 = Fonts.getSF_M4();
        v15 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v15, "WHITE");
        v14.drawStringWithShadow(var3_2, var30_15, var15_10, var4_3 + 68.0f - var31_16, var5_4 + 0.5f, v15);
    }

    @Override
    public boolean isHovered(float f, float f2) {
        return false;
    }

    public static void zkmSetFlag(String[] stringArray) {
        Y = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return Y;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block19: {
            block18: {
                block17: {
                    block16: {
                        block15: {
                            ProxyElement.a = ZkmKeys.a(-8482620632106143659L, -21771227850793055L, MethodHandles.lookup().lookupClass()).a(265861758158731L);
                            var25 = ProxyElement.a ^ 42822058745394L;
                            ProxyElement.b = new HashMap<K, V>(13);
                            ProxyElement.zkmSetFlag(null);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[9];
                            var21_4 = 0;
                            var20_5 = "\u00f1\u00cd\u00f8\u00de\u00c3w\u00e3w_\u00ec\u00b2\u009e\u00d1\u00c9j\u00ca\u0010\u00073\u00c2\u001f\u001b\u00b15&\u00ef\u00f6\u0091\u008d\u00ba\u00f8\u007fp\u0010\u00c9\u009c\u0005 G#\u00ab\u00f1t\u007f\u00c4\\3\u0005X(\u0010\u00c8K\u00a3\u0013\u0089K/]\u00c7\u00a0\f\u00d8G4\u0095\u009a\u0010\u00f3-\u00cc\u00c7Bf\u00aa\u0087+\u00180M|\u00c27\u001f\u0010\u00f21ob\u00edi\u007f_{\u00d9\u00e8\u0088*O\u00f5\u00ba\u0010Ky\u0018l\u00fe\u00ebq\r\u0015s\u00ec\u00e8\u00fb\u00f5\u0010^";
                            var22_6 = 118;
                            var19_7 = 16;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ProxyElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u0082s\u0019\u00d9B\u001a8\u00d3\u00e7xf\u00e3\u0098\u00c3=\u001a\u000e\u00d6s8H\u00c2d\u00a1\u0018\u00de\u00b6\u00ec\u000f\u00b8`x\u0014\u00fb\u008fd.Z\u0019\u0016\u00f6\u008e\u00e4\u00d6\u00bd\u00c2\u00cb\u00faC";
                                var22_6 = 49;
                                var19_7 = 24;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ProxyElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block16;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    ProxyElement.c = var23_3;
                    ProxyElement.d = new String[9];
                    ProxyElement.g = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[2];
                    var8_13 = 0;
                    var9_14 = "\u00d2\u00da\u00cb\u00eb!\u0083\u00b6m\u00f2\u00b1\u0010\u0084\u00d2\u00b9\u0086\u0098";
                    var10_15 = 16;
                    var7_16 = 0;
                    while (true) {
                        break block17;
                        break;
                    }
lbl74:
                    // 1 sources

                    while (true) {
                        var11_12[v10] = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                        if (var7_16 < var10_15) ** continue;
                        break block18;
                        break;
                    }
                }
                var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                v10 = var8_13++;
                var13_18 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                ** while (true)
            }
            ProxyElement.e = var11_12;
            ProxyElement.f = new Integer[2];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v11 = SecretKeyFactory.getInstance("DES");
            v12 = new byte[8];
            v13 = v12;
            v12[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v13 = v13;
                v13[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block19;
lbl97:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v11.generateSecret(new DESKeySpec(v13)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-81, 41, 62, 60, 3, -58, -45, 42});
        ** while (true)
        ProxyElement.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3AF8;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])b.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    b.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/xw", exception);
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
            ProxyElement.d[n2] = ProxyElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4E0C;
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
                throw new RuntimeException("su/catlean/xw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ProxyElement.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ProxyElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/xw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ProxyElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

