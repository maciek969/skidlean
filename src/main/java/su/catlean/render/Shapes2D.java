/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuTextureView;
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
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.client.render.VertexFormats;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BlurPass;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.RoundRectUniforms;
import su.catlean.render.uniform.ShapeUniformStorage;
import su.catlean.zkm.ZkmKeys;

public final class Shapes2D {
    private static int[] m;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public static final void drawShape(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, @NotNull Color color, @NotNull Color color2, float f9, long l, float f10, float f11) {
        long l2 = (l = a ^ l) ^ 0x25591AAA6396L;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "stack");
        Intrinsics.checkNotNullParameter(color, "colorValue");
        Intrinsics.checkNotNullParameter(color2, "outlineColorValue");
        float f12 = RangesKt.coerceIn(f3 / 10000.0f, 0.0f, 1.0f);
        float f13 = RangesKt.coerceIn(f4 / 10000.0f, 0.0f, 1.0f);
        float f14 = RangesKt.coerceIn(f8, 0.0f, 1.0f);
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 6;
        boolean bl = false;
        int n2 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker), f - 1.0f, f2 + f4 + 2.0f, 0.0f, null).texture(f12, f13).color(new Color(0.0f, 0.0f, f14, f14)), f - 1.0f, f2 - 1.0f, 0.0f, null).texture(f12, f13).color(new Color(0.0f, 0.0f, f14, f14)), f + f3 + 2.0f, f2 - 1.0f, 0.0f, null).texture(f12, f13).color(new Color(0.0f, 0.0f, f14, f14)), f + f3 + 2.0f, f2 + f4 + 2.0f, 0.0f, null).texture(f12, f13).color(new Color(0.0f, 0.0f, f14, f14));
        RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramRectBlurred();
        Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
        float f15 = RenderUtil.INSTANCE.getAlpha(color) * RenderUtil.INSTANCE.t();
        float f16 = RenderUtil.INSTANCE.getAlpha(color2) * RenderUtil.INSTANCE.t();
        GpuBufferSlice gpuBufferSlice = ShapeUniformStorage.INSTANCE.getDynamicUniformStorage().write((DynamicUniformStorage.Uploadable)new RoundRectUniforms(f5, f9, f10, f11, f6 / 2.0f, RenderUtil.INSTANCE.withAlpha(color, f15), RenderUtil.INSTANCE.withAlpha(color2, f16), f7));
        Map<String, GpuTextureView> map = MapsKt.mapOf(TuplesKt.to("Sampler0", BlurPass.INSTANCE.getOutput()));
        Object var38_31 = null;
        BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, null, gpuBufferSlice, null, matrix3x2f, map, 10, null);
    }

    public static void drawShape$default(Matrix3x2fStack matrix3x2fStack, char c, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color, Color color2, float f9, short s, float f10, int n, float f11, int n2, Object object) {
        long l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        long l2 = l ^ 0x10FF0EF29422L;
        if ((n2 & Shapes2D.zkmBootstrap2("p", (int)26117, (long)(0x8C8098E07476752L ^ l))) != 0) {
            f9 = f5;
        }
        if ((n2 & Shapes2D.zkmBootstrap2("p", (int)19734, (long)(0x3244171F4C654C43L ^ l))) != 0) {
            f10 = f5;
        }
        if ((n2 & Shapes2D.zkmBootstrap2("p", (int)9085, (long)(0x25AEFF5C8DA7222BL ^ l))) != 0) {
            f11 = f5;
        }
        Shapes2D.drawShape(matrix3x2fStack, f, f2, f3, f4, f5, f6, f7, f8, color, color2, f9, l2, f10, f11);
    }

    public static void zkmSetFlag(int[] nArray) {
        m = nArray;
    }

    public static int[] zkmGetFlag() {
        return m;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Shapes2D.a = ZkmKeys.a(-2748965304596077913L, 4159641113029925133L, MethodHandles.lookup().lookupClass()).a(141912870959410L);
                        var20 = Shapes2D.a ^ 54647878959256L;
                        Shapes2D.d = new HashMap<K, V>(13);
                        Shapes2D.zkmSetFlag(null);
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "\u009db\u00f2G\u009bw\u00d8\u00fb\u0089o\u00e5\u00a5\u00df\u00fa\u000e\u00d0\u00b5e\u00b0\u00baW\u00bf\u0014\u00ac\u0083:\u00df\u00a4EMK\u00ff\u0010b\u00d0`\u00a0\u0010_o\u00fb\u00056\u00ee\u00b8\u009bm\u00c4J\u0018\u00a7e\u00f7\u00d4\u0002X\u00b4\u009f\u008d3\u009f\u00c1\u0016&\u00e0\u00ae\u00bbr\u009f7\u0093q\u0099#";
                        var17_6 = 74;
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
                            var18_3[var16_4++] = Shapes2D.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "J5\u00d1\u0016\u0084\u00ed1\u00af\u00e1\u0018\u00ae\u00fc4\u00a4wr\u0081&\u00aa\u00f2\u00a5\u008c\u000f[\u0096\u00fah\u0018\u00d4\u00d3\u0007\u00c1k\u00f6I\u00a4?\u0006__(N\u0010\u00a3\u00d8{\u0085\u00e8\u0014\u009fMA8g\u00df\u0096\u00e3\u00b82\u0093\u00ba\u00f3\u00ad\u00c1\n\u008c2\u00faN\u00e9wz\u00b4\u00d3r\u0093\u00f5\u00e4\u00d5\u0086|";
                            var17_6 = 81;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = Shapes2D.zkmDecodeUtf8(var19_9).intern();
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
                Shapes2D.b = var18_3;
                Shapes2D.c = new String[5];
                Shapes2D.g = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "L\u00abo\u0018y\u00f7\u00ed\u00c8\u00aa\u008cE\u00e6s]j\b\u0089'v?\u0000\u00a0yZ";
                var5_15 = 24;
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
                    var4_14 = "lo\u00fb@.\u0019\u0006\u00a8\u00c2\u009bE\u0003Mc\u00cb)";
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
        Shapes2D.e = var6_12;
        Shapes2D.f = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x22D3;
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
                throw new RuntimeException("su/catlean/x1", exception);
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
            Shapes2D.c[n2] = Shapes2D.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Shapes2D.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/x1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x238C;
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
                throw new RuntimeException("su/catlean/x1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Shapes2D.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Shapes2D.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/x1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Shapes2D.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Shapes2D.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

