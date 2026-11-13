/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package su.catlean.ui.hud;

import com.mojang.blaze3d.vertex.VertexFormat;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexFormats;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class CrosshairWidget
extends HudElement {
    @NotNull
    public static final CrosshairWidget F;
    private static float h;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] i;
    private static final Map k;

    private CrosshairWidget(long l, byte by) {
        long l2 = (l << 8 | (long)by << 56 >>> 56) ^ a;
        long l3 = l2 ^ 0x4759A357BC71L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 48 >>> 48);
        super((char)n, "crosshair", (char)n2);
    }

    public final float k() {
        return h;
    }

    public final void S(float f) {
        h = f;
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l ^ 0x4943FD27FB3CL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Color color = Theme.INSTANCE.getGradientColor(0);
        float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f;
        float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f;
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        Intrinsics.checkNotNull(clientPlayerEntity);
        float f3 = 3.0f - clientPlayerEntity.getAttackCooldownProgress(0.5f) * 3.0f + RangesKt.coerceIn(MovementUtil.INSTANCE.getHorizontalSpeed() * 10.0f, 0.0f, 3.0f);
        float f4 = RenderUtil.INSTANCE.lerp(h, f3, RenderUtil.INSTANCE.getTickDelta()) + 7.0f;
        h = f3;
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = 8;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        Color[] colorArray = new Color[1];
        Intrinsics.checkNotNullExpressionValue(Color.BLACK, "BLACK");
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 1.0f, f2 - 6.0f + f4, 2.0f, 4.0f, colorArray);
        colorArray = new Color[]{color};
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 0.5f, f2 - 5.5f + f4, 1.0f, 3.0f, colorArray);
        colorArray = new Color[1];
        Intrinsics.checkNotNullExpressionValue(Color.BLACK, "BLACK");
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 1.0f, f2 + 2.0f - f4, 2.0f, 4.0f, colorArray);
        colorArray = new Color[]{color};
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 0.5f, f2 + 2.5f - f4, 1.0f, 3.0f, colorArray);
        colorArray = new Color[1];
        Intrinsics.checkNotNullExpressionValue(Color.BLACK, "BLACK");
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 6.0f + f4, f2 - 1.0f, 4.0f, 2.0f, colorArray);
        colorArray = new Color[]{color};
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f - 5.5f + f4, f2 - 0.5f, 3.0f, 1.0f, colorArray);
        colorArray = new Color[1];
        Intrinsics.checkNotNullExpressionValue(Color.BLACK, "BLACK");
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f + 2.0f - f4, f2 - 1.0f, 4.0f, 2.0f, colorArray);
        colorArray = new Color[]{color};
        RenderUtil.INSTANCE.gradientQuad(batchedBuffer, f + 2.5f - f4, f2 - 0.5f, 3.0f, 1.0f, colorArray);
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)drawContext.getMatrices()), null, 46, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        CrosshairWidget.a = ZkmKeys.a(5841002753880898457L, -1457486917065574799L, MethodHandles.lookup().lookupClass()).a(12815323864929L);
                        var20 = CrosshairWidget.a ^ 88508942317237L;
                        v0 = var20 ^ 85253599658063L;
                        var22_1 = v0 >>> 8;
                        var24_2 = (int)(v0 << 56 >>> 56);
                        CrosshairWidget.f = new HashMap<K, V>(13);
                        var11_3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v1 = SecretKeyFactory.getInstance("DES");
                        v2 = new byte[8];
                        v3 = v2;
                        v2[0] = (byte)(var20 >>> 56);
                        for (var12_4 = 1; var12_4 < 8; ++var12_4) {
                            v3 = v3;
                            v3[var12_4] = (byte)(var20 << var12_4 * 8 >>> 56);
                        }
                        var11_3.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                        var18_5 = new String[5];
                        var16_6 = 0;
                        var15_7 = "\u008b\u00a7\u00a9\u001b8q\u00c3_\u000f\u00e2\u00adw\u0093h\u00e7\u0099\u0010@-\u00b0>\u0099\u00d0\u0091\u0081Q\u008ce\u0083\u0095>%\u00f4\u0018N\u0096F\u0087\u00f2\u00dd\u0092\u00145,\u00b0\u00cdVw\u00faN_['\"\u00b0!o\u00ad";
                        var17_8 = 58;
                        var14_9 = 16;
                        var13_10 = -1;
lbl25:
                        // 2 sources

                        while (true) {
                            v4 = ++var13_10;
                            v5 = var15_7.substring(v4, v4 + var14_9);
                            v6 = -1;
                            break block13;
                            break;
                        }
lbl30:
                        // 1 sources

                        while (true) {
                            var18_5[var16_6++] = CrosshairWidget.zkmDecodeUtf8(var19_11).intern();
                            if ((var13_10 += var14_9) < var17_8) {
                                var14_9 = var15_7.charAt(var13_10);
                                ** continue;
                            }
                            var15_7 = "\u00f5\u0012\b\t\u00dc$\u00fb/\u00fd\u0096\u00d3\u009b\u00f0\u009b\u0091\u00d7\u00e4\u001d\u00fe\u0002<\u00ceSUJ\u00f8\u001b\u00d6\u00bbz\u00d8\u00bf\u0010\u00b5\u008c\u00b9\u000f\u0006\u008e\u00e8\u00cfFe\u00aeb\u0001{\u0012n";
                            var17_8 = 49;
                            var14_9 = 32;
                            var13_10 = -1;
lbl39:
                            // 2 sources

                            while (true) {
                                v7 = ++var13_10;
                                v5 = var15_7.substring(v7, v7 + var14_9);
                                v6 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl44:
                        // 1 sources

                        while (true) {
                            var18_5[var16_6++] = CrosshairWidget.zkmDecodeUtf8(var19_11).intern();
                            if ((var13_10 += var14_9) < var17_8) {
                                var14_9 = var15_7.charAt(var13_10);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_11 = var11_3.doFinal(v5.getBytes("ISO-8859-1"));
                    switch (v6) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl56:
                        // 1 sources

                        ** continue;
                    }
                }
                CrosshairWidget.b = var18_5;
                CrosshairWidget.c = new String[5];
                CrosshairWidget.k = new HashMap<K, V>(13);
                var0_12 = Cipher.getInstance("DES/CBC/NoPadding");
                v8 = SecretKeyFactory.getInstance("DES");
                v9 = new byte[8];
                v10 = v9;
                v9[0] = (byte)(var20 >>> 56);
                for (var1_13 = 1; var1_13 < 8; ++var1_13) {
                    v10 = v10;
                    v10[var1_13] = (byte)(var20 << var1_13 * 8 >>> 56);
                }
                var0_12.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                var6_14 = new long[2];
                var3_15 = 0;
                var4_16 = "\u00d4\u00c7\u00fc\u00bb\u00a4\u0085E\u00ef\u00d7\u00eb\\\u00c8\u00c1E\u0084b";
                var5_17 = 16;
                var2_18 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    var6_14[v11] = ((long)var10_21[0] & 255L) << 56 | ((long)var10_21[1] & 255L) << 48 | ((long)var10_21[2] & 255L) << 40 | ((long)var10_21[3] & 255L) << 32 | ((long)var10_21[4] & 255L) << 24 | ((long)var10_21[5] & 255L) << 16 | ((long)var10_21[6] & 255L) << 8 | (long)var10_21[7] & 255L;
                    if (var2_18 < var5_17) ** continue;
                    break block16;
                    break;
                }
            }
            var7_19 = var4_16.substring(var2_18, var2_18 += 8).getBytes("ISO-8859-1");
            v11 = var3_15++;
            var8_20 = ((long)var7_19[0] & 255L) << 56 | ((long)var7_19[1] & 255L) << 48 | ((long)var7_19[2] & 255L) << 40 | ((long)var7_19[3] & 255L) << 32 | ((long)var7_19[4] & 255L) << 24 | ((long)var7_19[5] & 255L) << 16 | ((long)var7_19[6] & 255L) << 8 | (long)var7_19[7] & 255L;
            var10_21 = var0_12.doFinal(new byte[]{(byte)(var8_20 >>> 56), (byte)(var8_20 >>> 48), (byte)(var8_20 >>> 40), (byte)(var8_20 >>> 32), (byte)(var8_20 >>> 24), (byte)(var8_20 >>> 16), (byte)(var8_20 >>> 8), (byte)var8_20});
            ** while (true)
        }
        CrosshairWidget.g = var6_14;
        CrosshairWidget.i = new Integer[2];
        CrosshairWidget.F = new CrosshairWidget(var22_1, (byte)var24_2);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A68;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/g6", exception);
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
            CrosshairWidget.c[n2] = CrosshairWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrosshairWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x73B1;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/g6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CrosshairWidget.i[n2] = n3;
        }
        return i[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CrosshairWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/g6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrosshairWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CrosshairWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

